## Why @ConditionalOnProperty
- In our application can have 1000 of `Beans`. When the application context will comming up 1000 `Bean` will be created in application context, and this will create a problem.
- So, when the application context comming up we don't need all the bean at once, so we can initialize those `Beans` which we need in that time
- And its condition is handle by the **ConditionalOnProperty**

## What is @ConditionalOnProperty?
- Bean is created conditionally

## Scenarios to Handle
1. We want to create only one bean. either `MySqlConnection` or `NoSQLConnection`
2. We have 2 components sharing same db, but 1 needs MySQLConnection and other needs NoSqlConnection

## Implementation:
```java
@ConditionalOnProperty(
		prefix = "nosqlconnection",
		value = "enable",
		havingValue = "true",
		matchIfMissing = false
		)
public class NoSQLConnection {}

@ConditionalOnProperty(
		prefix = "mysqlconnection",
		value = "enable",
		havingValue = "create",
		matchIfMissing = false
		)
public class MySQLConnection {}

```
- here prefix + value = create the key. The key for which it will try to find the configuaration. It will try to find configaration inside `application.properties`
- and havingValue will be the value of that key
- So if the value of the key is true then you create the `Bean`
- if the key configaration is not present in the `application.properties` then depending on the `matchIfMissing` value it will `inject the bean`(true) or `not inject the bean`(false). Its a fallback macanisiom
```properties
nosqlconnection.enabled= true
mysqlconnection.enable = create
```
- also in `@Autowired` we need to use `required = false` because if the key is false or not present it will mark the bean as null.
```java
@Autowired(required = false)
	MySQLConnection mySQLConnection;
	@Autowired(required = false)
	NoSQLConnection noSQLConnection;
```
### Advantages:
- Toggling of feature
- Avoid cluttering application context with unnecessary beans
- Save memory
- Reduce application startup time

### Disadvances:
- Misconfiguration can happned
- Code complexity incress when over use
- Complexity in mapping



