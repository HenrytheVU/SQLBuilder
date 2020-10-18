package sqlbuilder;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static sqlbuilder.SQLBuilder.*;

public class SQLBuilderTest {

    @Test
    void selectFrom() {
        String expected = "SELECT CustomerName, City FROM Customers";
        AbstractQuery sql = select("CustomerName", "City").from("Customers");
        assertEquals(expected, sql.getQuery());

        String expected1 = "SELECT * FROM Customers";
        AbstractQuery sql1 = select().from("Customers");
        assertEquals(expected1, sql1.getQuery());
    }

    @Test
    void selectDistinctFrom() {
        String expected = "SELECT DISTINCT Country FROM Customers";
        AbstractQuery sql = selectDistinct("Country").from("Customers");
        assertEquals(expected, sql.getQuery());

        String expected1 = "SELECT DISTINCT * FROM Customers";
        AbstractQuery sql1 = selectDistinct().from("Customers");
        assertEquals(expected1, sql1.getQuery());
    }

    @Test
    void selectCountFrom() {
        String expected = "SELECT COUNT (ProductID) FROM Products";
        AbstractQuery sql = selectCount("ProductID").from("Products");
        assertEquals(expected, sql.getQuery());

        String expected1 = "SELECT COUNT (*) FROM Products";
        AbstractQuery sql1 = selectCount().from("Products");
        assertEquals(expected1, sql1.getQuery());
    }

    @Test
    void selectCountDistinctFrom() {
        String expected = "SELECT COUNT (DISTINCT Country) FROM Customers";
        AbstractQuery sql = selectCountDistinct("Country").from("Customers");
        assertEquals(expected, sql.getQuery());
    }

    @Test
    void selectFromWhereEqual() {
        String expected = "SELECT * FROM Customers WHERE Country = 'Mexico'";
        String expectedWithPlaceHolders = "SELECT * FROM Customers WHERE Country = ?";

        AbstractQuery sql = select().from("Customers").where("Country").equal("Mexico");
        assertEquals(expected, sql.getQuery());
        assertEquals(expectedWithPlaceHolders, sql.toString());
        assertEquals(expectedWithPlaceHolders, sql.getQueryWithPlaceHolders());
        assertEquals(1, sql.getParamObjects().length);
        assertEquals("Mexico", sql.getParamObjects()[0]);

        String expected1 = "SELECT * FROM Customers WHERE CustomerID = 1";
        AbstractQuery sql1 = select().from("Customers").where("CustomerID").equal(1);
        assertEquals(expected1, sql1.getQuery());
        assertEquals(1, sql1.getParamObjects().length);
        assertEquals(1, sql1.getParamObjects()[0]);
    }

    @Test
    void selectFromWhereNotEqual() {
        String expected = "SELECT * FROM Customers WHERE Country <> 'Mexico'";
        AbstractQuery sql = select().from("Customers").where("Country").notEqual("Mexico");
        assertEquals(expected, sql.getQuery());
        assertEquals(1, sql.getParamObjects().length);
        assertEquals("Mexico", sql.getParamObjects()[0]);

        String expected1 = "SELECT * FROM Customers WHERE CustomerID <> 1";
        AbstractQuery sql1 = select().from("Customers").where("CustomerID").notEqual(1);
        assertEquals(expected1, sql1.getQuery());
        assertEquals(1, sql1.getParamObjects().length);
        assertEquals(1, sql1.getParamObjects()[0]);
    }

    @Test
    void selectFromWhereGreaterThan() {
        String expected = "SELECT * FROM Customers WHERE Country > 'Mexico'";
        AbstractQuery sql = select().from("Customers").where("Country").greaterThan("Mexico");
        assertEquals(expected, sql.getQuery());
        assertEquals(1, sql.getParamObjects().length);
        assertEquals("Mexico", sql.getParamObjects()[0]);

        String expected1 = "SELECT * FROM Customers WHERE CustomerID > 1";
        AbstractQuery sql1 = select().from("Customers").where("CustomerID").greaterThan(1);
        assertEquals(expected1, sql1.getQuery());
        assertEquals(1, sql1.getParamObjects().length);
        assertEquals(1, sql1.getParamObjects()[0]);
    }

    @Test
    void selectFromWhereGreaterOrEqual() {
        String expected = "SELECT * FROM Customers WHERE Country >= 'Mexico'";
        AbstractQuery sql = select().from("Customers").where("Country").greaterOrEqual("Mexico");
        assertEquals(expected, sql.getQuery());
        assertEquals(1, sql.getParamObjects().length);
        assertEquals("Mexico", sql.getParamObjects()[0]);

        String expected1 = "SELECT * FROM Customers WHERE CustomerID >= 1";
        AbstractQuery sql1 = select().from("Customers").where("CustomerID").greaterOrEqual(1);
        assertEquals(expected1, sql1.getQuery());
        assertEquals(1, sql1.getParamObjects().length);
        assertEquals(1, sql1.getParamObjects()[0]);
    }

    @Test
    void selectFromWhereLessThan() {
        String expected = "SELECT * FROM Customers WHERE Country < 'Mexico'";
        AbstractQuery sql = select().from("Customers").where("Country").lessThan("Mexico");
        assertEquals(expected, sql.getQuery());
        assertEquals(1, sql.getParamObjects().length);
        assertEquals("Mexico", sql.getParamObjects()[0]);

        String expected1 = "SELECT * FROM Customers WHERE CustomerID < 10";
        AbstractQuery sql1 = select().from("Customers").where("CustomerID").lessThan(10);
        assertEquals(expected1, sql1.getQuery());
        assertEquals(1, sql1.getParamObjects().length);
        assertEquals(10, sql1.getParamObjects()[0]);
    }

    @Test
    void selectFromWhereLessOrEqual() {
        String expected = "SELECT * FROM Customers WHERE Country <= 'Mexico'";
        AbstractQuery sql = select().from("Customers").where("Country").lessOrEqual("Mexico");
        assertEquals(expected, sql.getQuery());
        assertEquals(1, sql.getParamObjects().length);
        assertEquals("Mexico", sql.getParamObjects()[0]);

        String expected1 = "SELECT * FROM Customers WHERE CustomerID <= 1";
        AbstractQuery sql1 = select().from("Customers").where("CustomerID").lessOrEqual(1);
        assertEquals(expected1, sql1.getQuery());
        assertEquals(1, sql1.getParamObjects().length);
        assertEquals(1, sql1.getParamObjects()[0]);
    }

    @Test
    void selectFromWhereEqAndNeAndLtAndGtAndLeAndGe() {
        String expected = "SELECT * FROM Customers WHERE Country = 'Mexico' AND City <> 'CityName' AND Price < 50 AND Unit > 'UnitDescription' AND Name <= 'Alice' AND ID >= 99";
        AbstractQuery sql = select().from("Customers")
                .where("Country").eq("Mexico")
                .and("City").ne("CityName")
                .and("Price").lt(50)
                .and("Unit").gt("UnitDescription")
                .and("Name").le("Alice")
                .and("ID").ge(99);
        assertEquals(expected, sql.getQuery());
    }

    @Test
    void selectFromOrderBy() {
        String expected = "SELECT * FROM Customers ORDER BY Country";
        AbstractQuery sql = select().from("Customers").orderBy("Country");
        assertEquals(expected, sql.getQuery());

        String expected1 = "SELECT * FROM Customers ORDER BY Country ASC";
        AbstractQuery sql1 = select().from("Customers").orderBy("Country").asc();
        assertEquals(expected1, sql1.getQuery());

        String expected2 = "SELECT * FROM Customers ORDER BY Country DESC";
        AbstractQuery sql2 = select().from("Customers").orderBy("Country").desc();
        assertEquals(expected2, sql2.getQuery());

        String expected3 = "SELECT * FROM Customers ORDER BY Country, CustomerName";
        AbstractQuery sql3 = select().from("Customers").orderBy("Country", "CustomerName");
        assertEquals(expected3, sql3.getQuery());
    }

    @Test
    void selectFromWhereIsNotNull() {
        String expected = "SELECT CustomerName, ContactName, Address FROM Customers WHERE Address IS NOT NULL";
        AbstractQuery sql = select("CustomerName", "ContactName", "Address")
                .from("Customers").where("Address").isNotNull();
        assertEquals(expected, sql.getQuery());
    }

    @Test
    void insertIntoValues() {
        String expected = "INSERT INTO Customers (CustomerName, City, Country, Price) VALUES ('Cardinal', 'Stavanger', 'Norway', 99)";
        String expectedWithPlaceHolders = "INSERT INTO Customers (CustomerName, City, Country, Price) VALUES (?, ?, ?, ?)";
        AbstractQuery sql = SQLBuilder
                .insertInto("Customers", "CustomerName", "City", "Country", "Price")
                .values("Cardinal", "Stavanger", "Norway", 99);
        assertEquals(expected, sql.getQuery());
        assertEquals(expectedWithPlaceHolders, sql.toString());
        assertEquals(expectedWithPlaceHolders, sql.getQueryWithPlaceHolders());
        Object[] expectedParams = new Object[]{"Cardinal", "Stavanger", "Norway", 99};
        assertTrue(deepEqual(expectedParams, sql.getParamObjects()));
    }

    @Test
    void updateSet() {
        String expected = "UPDATE Customers SET ContactName = 'Alfred Schmidt', City = 'Frankfurt', Price = 99 WHERE CustomerID = 1";
        String expectedWithPlaceHolders = "UPDATE Customers SET ContactName = ?, City = ?, Price = ? WHERE CustomerID = ?";
        AbstractQuery sql = update("Customers")
                .set("ContactName", "Alfred Schmidt")
                .set("City", "Frankfurt")
                .set("Price", 99)
                .where("CustomerID").eq(1);
        assertEquals(expected, sql.getQuery());
        assertEquals(expectedWithPlaceHolders, sql.toString());
        assertEquals(expectedWithPlaceHolders, sql.getQueryWithPlaceHolders());
        AbstractQuery sql1 = update("Customers")
                .set(Arrays.asList("ContactName", "City", "Price"), Arrays.asList("Alfred Schmidt", "Frankfurt", 99))
                .where("CustomerID").eq(1);
        assertEquals(expected, sql1.getQuery());

        AbstractQuery sql2 = update("Customers")
                .set(Arrays.asList("ContactName", "City"), Arrays.asList("Alfred Schmidt", "Frankfurt"))
                .set("Price", 99)
                .where("CustomerID").eq(1);
        assertEquals(expected, sql2.getQuery());
    }

    @Test
    void selectFromGroupBy() {
        String expected = "SELECT working_area, COUNT (*) FROM agents GROUP BY working_area";
        AbstractQuery sql = select("working_area").count().from("agents").groupBy("working_area");
        assertEquals(expected, sql.getQuery());

        String expected1 = "SELECT working_area, COUNT (id) FROM agents GROUP BY working_area";
        AbstractQuery sql1 = select("working_area").count("id").from("agents").groupBy("working_area");
        assertEquals(expected1, sql1.getQuery());
    }

    @Test
    void selectFromForBusinessTimeAsOfWhere() {
        String expected = "SELECT PRICE FROM STOCK FOR BUSINESS_TIME AS OF '2020-01-01' WHERE STOCK_NAME = 'APPLE INC'";
        AbstractQuery sql = select("PRICE").from("STOCK")
                .forBusinessTimeAsOf(LocalDate.of(2020, 1, 1))
                .where("STOCK_NAME").eq("APPLE INC");
        assertEquals(expected, sql.getQuery());
    }

    @Test
    void updateForPortionBusinessTimeFromToWhere() {
        String expected = "UPDATE inventory FOR PORTION OF BUSINESS_TIME FROM '2015-06-01' TO '2016-09-01' SET price = 900000 WHERE id = 1111";
        AbstractQuery sql = update("inventory")
                .forPortionOfBusinessTimeFrom(LocalDate.of(2015, 6, 1))
                .to(LocalDate.of(2016, 9, 1))
                .set("price", 900000)
                .where("id").eq(1111);
        assertEquals(expected, sql.getQuery());
    }

    @Test
    void selectFromForBusinessTimeFromTo() {
        String expected = "SELECT * FROM policy FOR BUSINESS_TIME FROM '2014-01-01' TO '2016-01-01' WHERE id = 1414";
        AbstractQuery sql = select().from("policy")
                .forBusinessTimeFrom(LocalDate.of(2014, 1, 1))
                .to(LocalDate.of(2016, 1, 1))
                .where("id").eq(1414);
        assertEquals(expected, sql.getQuery());
    }

    @Test
    void deleteFromForPortionOfBusinessTimeFromToWhere() {
        String expected = "DELETE FROM policy_info FOR PORTION OF BUSINESS_TIME FROM '2008-06-15' TO '2008-08-15' WHERE policy_id = 'A123'";
        AbstractQuery sql = deleteFrom("policy_info")
                .forPortionOfBusinessTimeFrom(LocalDate.of(2008, 6, 15))
                .to(LocalDate.of(2008, 8, 15))
                .where("policy_id").eq("A123");
        assertEquals(expected, sql.getQuery());
    }

    @Test
    void deleteFromWhere() {
        String expected = "DELETE FROM Customers WHERE CustomerName = 'Alfreds Futterkiste'";
        AbstractQuery sql = deleteFrom("Customers").where("CustomerName").eq("Alfreds Futterkiste");
        assertEquals(expected, sql.getQuery());
    }

    @Test
    void selectFromWhereLike() {
        String expected = "SELECT * FROM Customers WHERE CustomerName LIKE 'a%'";
        AbstractQuery sql = select().from("Customers").where("CustomerName").startsWith("a");
        assertEquals(expected, sql.getQuery());

        String expected1 = "SELECT * FROM Customers WHERE CustomerName LIKE '%a123'";
        AbstractQuery sql1 = select().from("Customers").where("CustomerName").endsWith("a123");
        assertEquals(expected1, sql1.getQuery());

        String expected2 = "SELECT * FROM Customers WHERE CustomerName LIKE '%a123%'";
        AbstractQuery sql2 = select().from("Customers").where("CustomerName").contains("a123");
        assertEquals(expected2, sql2.getQuery());

        String expected3 = "SELECT * FROM Customers WHERE CustomerName LIKE 'a123'";
        AbstractQuery sql3 = select().from("Customers").where("CustomerName").like("a123");
        assertEquals(expected3, sql3.getQuery());
    }

    @Test
    void selectFromWhereIn() {
        String expected = "SELECT * FROM Customers WHERE Country IN ('Germany', 'France', 'UK')";
        AbstractQuery sql = select().from("Customers").where("Country").in("Germany", "France", "UK");
        assertEquals(expected, sql.getQuery());

        AbstractQuery sql1 = select().from("Customers").where("Country").in(Arrays.asList("Germany", "France", "UK"));
        assertEquals(expected, sql1.getQuery());

    }

    @Test
    void selectFromWhereNotIn() {
        String expected = "SELECT * FROM Customers WHERE Country NOT IN ('Germany', 'France', 'UK')";
        AbstractQuery sql = select().from("Customers").where("Country").notIn("Germany", "France", "UK");
        assertEquals(expected, sql.getQuery());

        AbstractQuery sql1 = select().from("Customers").where("Country").notIn(Arrays.asList("Germany", "France", "UK"));
        assertEquals(expected, sql1.getQuery());
    }

    @Test
    void selectFromWhereBetweenAndNotIn() {
        String expected = "SELECT * FROM Products WHERE Price BETWEEN 10 AND 20 AND CategoryID NOT IN (1, 2, 3)";
        AbstractQuery sql = select().from("Products").where("Price").between(10, 20).and("CategoryID").notIn(1, 2, 3);
        assertEquals(expected, sql.getQuery());
    }

    @Test
    void selectFromWhereBetweenOrNotIn() {
        String expected = "SELECT * FROM Products WHERE Price BETWEEN 10 AND 20 OR CategoryID NOT IN (1, 2, 3)";
        AbstractQuery sql = select().from("Products").where("Price").between(10, 20).or("CategoryID").notIn(1, 2, 3);
        assertEquals(expected, sql.getQuery());
    }

    @Test
    void selectFromAsWhereAnd() {
        String expected = "SELECT o.OrderID, o.OrderDate, c.CustomerName FROM Customers AS c, Orders AS o WHERE c.CustomerName = 'Around the Horn' AND c.CustomerID = o.CustomerID";
        AbstractQuery sql = select("o.OrderID", "o.OrderDate", "c.CustomerName")
                .from("Customers").as("c").comma("Orders").as("o")
                .where("c.CustomerName").eq("Around the Horn")
                .and("c.CustomerID").eqCol("o.CustomerID");
        assertEquals(expected, sql.getQuery());

        String expected1 = "SELECT o.OrderID, o.OrderDate, c.CustomerName FROM Customers AS c, Orders AS o WHERE c.CustomerName = 'Around the Horn' AND c.CustomerID <> o.CustomerID";
        AbstractQuery sql1 = select("o.OrderID", "o.OrderDate", "c.CustomerName")
                .from("Customers").as("c").comma("Orders").as("o")
                .where("c.CustomerName").eq("Around the Horn")
                .and("c.CustomerID").neCol("o.CustomerID");
        assertEquals(expected1, sql1.getQuery());

        String expected2 = "SELECT o.OrderID, o.OrderDate, c.CustomerName FROM Customers AS c, Orders AS o WHERE c.CustomerName = 'Around the Horn' AND c.CustomerID > o.CustomerID";
        AbstractQuery sql2 = select("o.OrderID", "o.OrderDate", "c.CustomerName")
                .from("Customers").as("c").comma("Orders").as("o")
                .where("c.CustomerName").eq("Around the Horn")
                .and("c.CustomerID").gtCol("o.CustomerID");
        assertEquals(expected2, sql2.getQuery());

        String expected3 = "SELECT o.OrderID, o.OrderDate, c.CustomerName FROM Customers AS c, Orders AS o WHERE c.CustomerName = 'Around the Horn' AND c.CustomerID < o.CustomerID";
        AbstractQuery sql3 = select("o.OrderID", "o.OrderDate", "c.CustomerName")
                .from("Customers").as("c").comma("Orders").as("o")
                .where("c.CustomerName").eq("Around the Horn")
                .and("c.CustomerID").ltCol("o.CustomerID");
        assertEquals(expected3, sql3.getQuery());

        String expected4 = "SELECT o.OrderID, o.OrderDate, c.CustomerName FROM Customers AS c, Orders AS o WHERE c.CustomerName = 'Around the Horn' AND c.CustomerID >= o.CustomerID";
        AbstractQuery sql4 = select("o.OrderID", "o.OrderDate", "c.CustomerName")
                .from("Customers").as("c").comma("Orders").as("o")
                .where("c.CustomerName").eq("Around the Horn")
                .and("c.CustomerID").geCol("o.CustomerID");
        assertEquals(expected4, sql4.getQuery());

        String expected5 = "SELECT o.OrderID, o.OrderDate, c.CustomerName FROM Customers AS c, Orders AS o WHERE c.CustomerName = 'Around the Horn' AND c.CustomerID <= o.CustomerID";
        AbstractQuery sql5 = select("o.OrderID", "o.OrderDate", "c.CustomerName")
                .from("Customers").as("c").comma("Orders").as("o")
                .where("c.CustomerName").eq("Around the Horn")
                .and("c.CustomerID").leCol("o.CustomerID");
        assertEquals(expected5, sql5.getQuery());
    }

    @Test
    void selectFromJoinOn() {
        String expected = "SELECT Orders.OrderID, Customers.CustomerName, Orders.OrderDate FROM Orders JOIN Customers ON Customers.CustomerID = Orders.CustomerID AND Customers.CustomerName > 'Joerg'";
        AbstractQuery sql = select("Orders.OrderID", "Customers.CustomerName", "Orders.OrderDate")
                .from("Orders").join("Customers").on("Customers.CustomerID").eqCol("Orders.CustomerID")
                .and("Customers.CustomerName").gt("Joerg");
        assertEquals(expected, sql.getQuery());
    }

    @Test
    void selectFromJoinOnWithAliases() {
        String expected = "SELECT o.OrderID, c.CustomerName, o.OrderDate FROM Orders o JOIN Customers c ON o.CustomerID = c.CustomerID AND c.CustomerName <= 'Joerg'";
        AbstractQuery sql = select("o.OrderID", "c.CustomerName", "o.OrderDate")
                .from("Orders", "o").join("Customers", "c")
                .on("o.CustomerID").eqCol("c.CustomerID")
                .and("c.CustomerName").le("Joerg");
        assertEquals(expected, sql.getQuery());

        String expected1 = "SELECT o.OrderID, c.CustomerName, o.OrderDate FROM Orders AS o JOIN Customers AS c ON o.CustomerID = c.CustomerID AND c.CustomerName >= 'Joerg'";
        AbstractQuery sql1 = select("o.OrderID", "c.CustomerName", "o.OrderDate")
                .from("Orders").as("o").join("Customers").as("c")
                .on("o.CustomerID").eqCol("c.CustomerID")
                .and("c.CustomerName").ge("Joerg");
        assertEquals(expected1, sql1.getQuery());
    }

    @Test
    void selectFromLeftJoinOnOrderBy() {
        String expected = "SELECT Customers.CustomerName, Orders.OrderID FROM Customers LEFT JOIN Orders ON Customers.CustomerID = Orders.CustomerID ORDER BY Customers.CustomerName";
        AbstractQuery sql = select("Customers.CustomerName", "Orders.OrderID")
                .from("Customers").leftJoin("Orders")
                .on("Customers.CustomerID").eqCol("Orders.CustomerID")
                .orderBy("Customers.CustomerName");
        assertEquals(expected, sql.getQuery());
    }

    @Test
    void selectFromRightJoinOnOrderBy() {
        String expected = "SELECT Orders.OrderID, Employees.LastName, Employees.FirstName FROM Orders RIGHT JOIN Employees ON Orders.EmployeeID = Employees.EmployeeID ORDER BY Orders.OrderID";
        AbstractQuery sql = select("Orders.OrderID", "Employees.LastName", "Employees.FirstName")
                .from("Orders").rightJoin("Employees").on("Orders.EmployeeID").eqCol("Employees.EmployeeID")
                .orderBy("Orders.OrderID");
        assertEquals(expected, sql.getQuery());
    }

    @Test
    void selectFromFullOuterJoinOnOrderBy() {
        String expected = "SELECT Customers.CustomerName, Orders.OrderID FROM Customers FULL JOIN Orders ON Customers.CustomerID = Orders.CustomerID ORDER BY Customers.CustomerName";
        AbstractQuery sql = select("Customers.CustomerName", "Orders.OrderID")
                .from("Customers").fullJoin("Orders")
                .on("Customers.CustomerID").eqCol("Orders.CustomerID")
                .orderBy("Customers.CustomerName");
        assertEquals(expected, sql.getQuery());
    }

    @Test
    void selectAsAsFromWhereAndOrderBy() {
        String expected = "SELECT A.CustomerName AS CustomerName1, B.CustomerName AS CustomerName2, A.City FROM Customers A, Customers B WHERE A.CustomerID <> B.CustomerID AND A.City = B.City ORDER BY A.City";
        AbstractQuery sql = select("A.CustomerName").as("CustomerName1")
                .comma("B.CustomerName").as("CustomerName2")
                .comma("A.City")
                .from("Customers A").comma("Customers B")
                .where("A.CustomerID").neCol("B.CustomerID")
                .and("A.City").eqCol("B.City")
                .orderBy("A.City");
        assertEquals(expected, sql.getQuery());
    }

    @Test
    void selectFromUnionSelectFromOrderBy() {
        String expected = "SELECT City FROM Customers UNION SELECT City FROM Suppliers ORDER BY City";
        AbstractQuery sql = select("City").from("Customers").union().select("City").from("Suppliers").orderBy("City");
        assertEquals(expected, sql.getQuery());

        String expected1 = "SELECT City FROM Customers UNION ALL SELECT City FROM Suppliers ORDER BY City";
        AbstractQuery sql1 = select("City").from("Customers").unionAll().select("City").from("Suppliers").orderBy("City");
        assertEquals(expected1, sql1.getQuery());

        String expected2 = "SELECT City, Country FROM Customers WHERE Country = 'Germany' UNION SELECT City, Country FROM Suppliers WHERE Country = 'Germany' ORDER BY City";
        AbstractQuery sql2 = select("City", "Country").from("Customers")
                .where("Country").eq("Germany")
                .union()
                .select("City", "Country").from("Suppliers")
                .where("Country").eq("Germany")
                .orderBy("City");
        assertEquals(expected2, sql2.getQuery());

        String expected3 = "SELECT City, Country FROM Customers WHERE Country = 'Germany' UNION ALL SELECT City, Country FROM Suppliers WHERE Country = 'Germany' ORDER BY City";
        AbstractQuery sql3 = select("City", "Country").from("Customers")
                .where("Country").eq("Germany")
                .unionAll()
                .select("City", "Country").from("Suppliers")
                .where("Country").eq("Germany")
                .orderBy("City");
        assertEquals(expected3, sql3.getQuery());
    }

    @Test
    void selectCountFromGroupByOrderByCountDesc() {
        String expected = "SELECT COUNT (CustomerID), Country FROM Customers GROUP BY Country ORDER BY COUNT (CustomerID) DESC";
        AbstractQuery sql = selectCount("CustomerID").comma("Country").from("Customers").groupBy("Country").orderByCount("CustomerID").desc();
        assertEquals(expected, sql.getQuery());

        String expected1 = "SELECT Shippers.ShipperName, COUNT (Orders.OrderID) AS NumberOfOrders FROM Orders LEFT JOIN Shippers ON Orders.ShipperID = Shippers.ShipperID GROUP BY ShipperName";
        AbstractQuery sql1 = select("Shippers.ShipperName").count("Orders.OrderID").as("NumberOfOrders")
                .from("Orders")
                .leftJoin("Shippers").on("Orders.ShipperID").eqCol("Shippers.ShipperID")
                .groupBy("ShipperName");
        assertEquals(expected1, sql1.getQuery());
    }

    @Test
    void testSelectCountFromGroupByHavingCountOrderByCountDesc() {
        String expected = "SELECT COUNT (CustomerID), Country FROM Customers GROUP BY Country HAVING COUNT (CustomerID) > 5 ORDER BY COUNT (CustomerID) DESC";
        AbstractQuery sql = selectCount("CustomerID").comma("Country").from("Customers").groupBy("Country").havingCount("CustomerID").gt(5).orderByCount("CustomerID").desc();
        assertEquals(expected, sql.getQuery());
        AbstractQuery sql1 = selectCount("CustomerID").comma("Country").from("Customers").groupBy("Country").havingCount("CustomerID").gt(5).orderBy().count("CustomerID").desc();
        assertEquals(expected, sql1.getQuery());
    }

    @Test
    void selectCountAsFromJoinOnGroupByHavingCount() {
        String expected = "SELECT E.LastName, COUNT (O.OrderID) AS NumberOfOrders FROM (Orders JOIN Employees ON O.EmployeeID = E.EmployeeID) GROUP BY LastName HAVING COUNT (O.OrderID) > 10";
        AbstractQuery aq = select("E.LastName").comma().count("O.OrderID").as("NumberOfOrders")
                .from("(Orders").join("Employees").on("O.EmployeeID").eqCol("E.EmployeeID)")
                .groupBy("LastName").havingCount("O.OrderID").gt(10);
        assertEquals(expected, aq.getQuery());
    }

    @Test
    void selectFromWhereInSelectDistinctFrom() {
        String expected = "SELECT * FROM users WHERE id IN (SELECT DISTINCT user_id FROM questions)";
        AbstractQuery aq = select().from("users")
                .where("id").in(selectDistinct("user_id").from("questions"));
        assertEquals(expected, aq.getQuery());
    }

    @Test
    void selectFromWhereExistsSelectFromWhereAnd() {
        String expected = "SELECT SupplierName FROM Suppliers WHERE EXISTS (SELECT ProductName FROM Products WHERE Products.SupplierID = Suppliers.supplierID AND Price >= 20)";
        AbstractQuery aq = select("SupplierName").from("Suppliers")
                .where().exists(
                        select("ProductName").from("Products")
                                .where("Products.SupplierID").eqCol("Suppliers.supplierID")
                                .and("Price").ge(20)
                );
        assertEquals(expected, aq.getQuery());

        String expected1 = "SELECT SupplierName FROM Suppliers WHERE NOT EXISTS (SELECT ProductName FROM Products WHERE Products.SupplierID = Suppliers.supplierID AND Price < 20)";
        AbstractQuery aq1 = select("SupplierName").from("Suppliers")
                .where().not().exists(
                        select("ProductName").from("Products")
                                .where("Products.SupplierID").eqCol("Suppliers.supplierID")
                                .and("Price").lt(20)
                );
        assertEquals(expected1, aq1.getQuery());
    }

    @Test
    void selectAvgFrom() {
        String expected = "SELECT AVG (Price) FROM Products";
        AbstractQuery aq = selectAvg("Price").from("Products");
        assertEquals(expected, aq.getQuery());
    }

    @Test
    void selectedSum() {
        String expected = "SELECT SUM (Quantity) FROM OrderDetails";
        AbstractQuery aq = selectSum("Quantity").from("OrderDetails");
        assertEquals(expected, aq.getQuery());
    }

    @Test
    void selectFromWhereAnySelectFromWhere() {
        String expected = "SELECT ProductName FROM Products WHERE ProductID = ANY (SELECT ProductID FROM OrderDetails WHERE Quantity = 10)";
        AbstractQuery aq = select("ProductName").from("Products")
                .where("ProductID").eqAny(
                        select("ProductID").from("OrderDetails").where("Quantity").eq(10)
                );
        assertEquals(expected, aq.getQuery());
    }

    @Test
    void selectFromWhereAllSelectFromWhere() {
        String expected = "SELECT ProductName FROM Products WHERE ProductID = ALL (SELECT ProductID FROM OrderDetails WHERE Quantity = 10)";
        AbstractQuery aq = select("ProductName").from("Products")
                .where("ProductID").eqAll(
                        select("ProductID").from("OrderDetails").where("Quantity").eq(10)
                );
        assertEquals(expected, aq.getQuery());
    }

    @Test
    void selectFromLimit() {
        String expected = "SELECT * FROM Customers LIMIT 3";
        AbstractQuery aq = select().from("Customers").limit(3);
        assertEquals(expected, aq.getQuery());
    }

    @Test
    void insertIntoValuesWithPlaceHolders() {
        String expected = "INSERT INTO books (title, author, date) VALUES (?, ?, ?)";
        AbstractQuery aq = insertInto("books", "title", "author", "date").values();
        assertEquals(expected, aq.getQueryWithPlaceHolders());

        AbstractQuery aq1 = insertInto("books", "title", "author", "date").valuesAsPlaceHolders();
        assertEquals(expected, aq1.getQueryWithPlaceHolders());
    }

    @Test
    void insertIntoColumnsWithPlaceHolders() {
        String expected = "INSERT INTO Customers (CustomerName, ContactName, Address, City, PostalCode, Country) VALUES (?, ?, ?, ?, ?, ?)";
        AbstractQuery aq = insertInto("Customers").columns("CustomerName", "ContactName", "Address", "City", "PostalCode", "Country").values();
        assertEquals(expected, aq.getQueryWithPlaceHolders());
        AbstractQuery aqValuesAsPlaceHolders = insertInto("Customers").columns("CustomerName", "ContactName", "Address", "City", "PostalCode", "Country").valuesAsPlaceHolders();
        assertEquals(expected, aqValuesAsPlaceHolders.getQueryWithPlaceHolders());

        String expected1 = "INSERT INTO Customers (CustomerName, ContactName, Address, City, PostalCode, Country) VALUES ('Cardinal', 'Tom B. Erichsen', 'Skagen 21', 'Stavanger', 4006, 'Norway')";
        AbstractQuery aq1 = insertInto("Customers").columns("CustomerName", "ContactName", "Address", "City", "PostalCode", "Country").values("Cardinal", "Tom B. Erichsen", "Skagen 21", "Stavanger", 4006, "Norway");

        assertEquals(expected1, aq1.getQuery());

        String expected2 = "INSERT INTO Customers VALUES ('Cardinal', 'Tom B. Erichsen', 'Skagen 21', 'Stavanger', 4006, 'Norway')";
        AbstractQuery aq2 = insertInto("Customers").values("Cardinal", "Tom B. Erichsen", "Skagen 21", "Stavanger", 4006, "Norway");
        assertEquals(expected2, aq2.getQuery());

        String expected3 = "INSERT INTO Customers VALUES (?)";
        AbstractQuery aq3 = insertInto("Customers").valuesWithNumberOfPlaceHolders(1);
        assertEquals(expected3, aq3.getQuery());

        String expected4 = "INSERT INTO Customers VALUES (?, ?, ?, ?, ?, ?)";
        AbstractQuery aq4 = insertInto("Customers").valuesWithNumberOfPlaceHolders(6);
        assertEquals(expected4, aq4.getQuery());

        assertThrows(BadSQLSyntaxException.class, () -> insertInto("Customers").valuesWithNumberOfPlaceHolders(0));
        assertThrows(BadSQLSyntaxException.class, () -> insertInto("Customers").valuesWithNumberOfPlaceHolders(-11));

    }

    private static boolean deepEqual(Object[] expected, Object[] actual) {
        if (expected.length != actual.length) {
            return false;
        }
        for (int i = 0; i < expected.length; i++) {
            if (!expected[i].equals(actual[i])) {
                return false;
            }
        }
        return true;
    }

}
