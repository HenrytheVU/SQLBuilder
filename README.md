# SQLBuilder
A Java SQLBuilder with fluent API for writing type-safe SQL queries

# Usage:
#### SELECT
```java
String expected = "SELECT * FROM Customers WHERE Country NOT IN ('Germany', 'France', 'UK')";
AbstractQuery sql = select().from("Customers").where("Country").notIn("Germany", "France", "UK");
assertEquals(expected, sql.getQuery());
        
String expectedWithPlaceHolders = "SELECT * FROM Customers WHERE Country NOT IN (?, ?, ?)";
assertEquals(expectedWithPlaceHolders, sql.getQueryWithPlaceHolders());
```
#### INSERT INTO
````java
String expected = "INSERT INTO Customers (CustomerName, ContactName, Address, City, PostalCode) VALUES ('Cardinal', 'Tom B. Erichsen', 'Skagen 21', 'Stavanger', 4006)";
AbstractQuery sql = insertInto("Customers")
    .columns("CustomerName", "ContactName", "Address", "City", "PostalCode")
    .values("Cardinal", "Tom B. Erichsen", "Skagen 21", "Stavanger", 4006);
assertEquals(expected, sql.getQuery());

String expectedWithPlaceHolders = "INSERT INTO Customers (CustomerName, ContactName, Address, City, PostalCode) VALUES (? ,? ,? ,? ,?)";
assertEquals(expected, sql.getQueryWithPlaceHolders());
````

#### UPDATE
````java
String expected = "UPDATE Customers SET ContactName = 'Alfred Schmidt', City = 'Frankfurt', Price = 99 WHERE CustomerID = 1";
String expectedWithPlaceHolders = "UPDATE Customers SET ContactName = ?, City = ?, Price = ? WHERE CustomerID = ?";
AbstractQuery sql = update("Customers")
        .set("ContactName", "Alfred Schmidt")
        .set("City", "Frankfurt")
        .set("Price", 99)
        .where("CustomerID").eq(1);
assertEquals(expected, sql.getQuery());
assertEquals(expectedWithPlaceHolders, sql.toString());
````

#### DELETE
````java
String expected = "DELETE FROM Customers WHERE CustomerName = 'Alfreds Futterkiste'";
AbstractQuery sql = deleteFrom("Customers")
    .where("CustomerName").eq("Alfreds Futterkiste");
assertEquals(expected, sql.getQuery());

String expectedWithPlaceHolders = "DELETE FROM Customers WHERE CustomerName = ?";
assertEquals(expectedWithPlaceHolders, sql.getQueryWithPlaceHolders());
````