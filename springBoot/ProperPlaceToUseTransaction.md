## Where to use @Transactional
- We should use **@Transactional** at the **service layer**, not at the controller or repository level.
### Why?
Because:
- Service layer is where you **plan or coordinate** multiple repository/database calls.
- It provides a single place to handle transactions, rollback, and exceptions.
### ✅ How to use @Transactional with multiple databases
- By default, **@Transactional** uses the **primary transaction manager**.
- To use a specific transaction manager, specify it explicitly using the transactionManager attribute.
```java
@Service
public class MyService {

    @Autowired
    private PrimaryRepository primaryRepo;

    @Autowired
    private SecondaryRepository secondaryRepo;

    // For PRIMARY database
    @Transactional("primaryTransactionManager")
    public void savePrimaryData() {
        primaryRepo.save(...);
        // If exception occurs, rollback will happen only in primary DB
    }

    // For SECONDARY database
    @Transactional("secondaryTransactionManager")
    public void saveSecondaryData() {
        secondaryRepo.save(...);
        // If exception occurs, rollback will happen only in secondary DB
    }

    // For combined operations (note: this won’t rollback across DBs!)
    public void saveToBothDatabases() {
        savePrimaryData();  // uses primary TM
        saveSecondaryData();  // uses secondary TM
    }
}

```
#### ⚠️ Important Note: No global rollback across two DBs
Spring doesn't support global transactions across multiple databases (2PC) out-of-the-box unless you configure a JTA transaction manager (like Atomikos or Narayana). That’s advanced and should be used only if atomic rollback across DBs is mandatory.
