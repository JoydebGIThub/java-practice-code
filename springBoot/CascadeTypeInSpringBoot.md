## What is CascadeType?
- Cascade Entity define how **child entity** will `behave` we `perform any oppration` on **parent entity**.
- When want to save the `parent object` do we want to save the `child` along with the `parent` and when we update the `parent` do we want to update the `child` also. So any operation we do on `parent` do we want to do that on `child` that will be defined by the **Cascade Type**.
- If we don't use any casecade then the data will not be reflect/update/delete in the child table

### CasecadeType:
1. PERSIST
- If we want to save the child along with the parant then we can use "**PERSIST**" otherwise when we save it will skip the child.
- it talk about the **save** operation
```java
@OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
@JsonManagedReference
private List<Address> address;
```

2. MERGE
- When we want to update the parent entity along with the child do we want to reflect that on the child or not
- it talk about the **update** operation
```java
@OneToMany(mappedBy = "customer", cascade ={CascadeType.PERSIST, CascadeType.MERGE})
@JsonManagedReference
private List<Address> address;
```

3. REMOVE
- it talk about the **delete** operation
```java
@OneToMany(mappedBy = "customer", cascade ={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
@JsonManagedReference
private List<Address> address;
```

4. REFRESH
- When i load the parent and in memory I modify the column on child and it will refelect or not
- If we want to refresh the parent along with the child
```java
@OneToMany(mappedBy = "customer", cascade ={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH})
@JsonManagedReference
private List<Address> address;
```

5. DETACH
- when we detach(not delete in database only detach entity from the context) something from the perant do we need to child
```java
@OneToMany(mappedBy = "customer", cascade ={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH})
@JsonManagedReference
private List<Address> address;
```

6. ALL
- When we need to deal with all the senario then we can use "**ALL**"
```java
@OneToMany(mappedBy = "customer", cascade =CascadeType.ALL)
@JsonManagedReference
private List<Address> address;
```
