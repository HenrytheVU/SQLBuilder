import org.junit.jupiter.api.Test;
import sql.AbstractQuery;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SQLBuilderTest {

    @Test
    void testSelect() {
        String expected = "SELECT CustomerName, City FROM Customers";
        AbstractQuery sql = SQLBuilder.select("CustomerName", "City").from("Customers");
        assertEquals(expected, sql.getQuery());

        String expected1 = "SELECT * FROM Customers";
        AbstractQuery sql1 = SQLBuilder.select().from("Customers");
        assertEquals(expected1, sql1.getQuery());
    }

    @Test
    void testSelectDistinct() {
        String expected = "SELECT DISTINCT Country FROM Customers";
        AbstractQuery sql = SQLBuilder.selectDistinct("Country").from("Customers");
        assertEquals(expected, sql.getQuery());

        String expected1 = "SELECT DISTINCT * FROM Customers";
        AbstractQuery sql1 = SQLBuilder.selectDistinct().from("Customers");
        assertEquals(expected1, sql1.getQuery());
    }

    @Test
    void testSelectCount() {
        String expected = "SELECT COUNT (ProductID) FROM Products";
        AbstractQuery sql = SQLBuilder.selectCount("ProductID").from("Products");
        assertEquals(expected, sql.getQuery());

        String expected1 = "SELECT COUNT (*) FROM Products";
        AbstractQuery sql1 = SQLBuilder.selectCount().from("Products");
        assertEquals(expected1, sql1.getQuery());

    }

    @Test
    void testSelectCountDistinct() {
        String expected = "SELECT COUNT (DISTINCT Country) FROM Customers";
        AbstractQuery sql = SQLBuilder.selectCountDistinct("Country").from("Customers");
        assertEquals(expected, sql.getQuery());
    }

    @Test
    void testSelectFromWhereEqual() {
        String expected = "SELECT * FROM Customers WHERE Country = 'Mexico'";
        AbstractQuery sql = SQLBuilder.select().from("Customers").where("Country").equal("Mexico");
        assertEquals(expected, sql.getQuery());
        assertEquals(1, sql.getParamObjects().length);
        assertEquals("Mexico", sql.getParamObjects()[0]);

        String expected1 = "SELECT * FROM Customers WHERE CustomerID = 1";
        AbstractQuery sql1 = SQLBuilder.select().from("Customers").where("CustomerID").equal(1);
        assertEquals(expected1, sql1.getQuery());
        assertEquals(1, sql1.getParamObjects().length);
        assertEquals(1, sql1.getParamObjects()[0]);
    }

    @Test
    void testSelectFromWhereNotEqual() {
        String expected = "SELECT * FROM Customers WHERE Country <> 'Mexico'";
        AbstractQuery sql = SQLBuilder.select().from("Customers").where("Country").notEqual("Mexico");
        assertEquals(expected, sql.getQuery());
        assertEquals(1, sql.getParamObjects().length);
        assertEquals("Mexico", sql.getParamObjects()[0]);

        String expected1 = "SELECT * FROM Customers WHERE CustomerID <> 1";
        AbstractQuery sql1 = SQLBuilder.select().from("Customers").where("CustomerID").notEqual(1);
        assertEquals(expected1, sql1.getQuery());
        assertEquals(1, sql1.getParamObjects().length);
        assertEquals(1, sql1.getParamObjects()[0]);
    }

    @Test
    void testSelectFromWhereGreaterThan() {
        String expected = "SELECT * FROM Customers WHERE Country > 'Mexico'";
        AbstractQuery sql = SQLBuilder.select().from("Customers").where("Country").greaterThan("Mexico");
        assertEquals(expected, sql.getQuery());
        assertEquals(1, sql.getParamObjects().length);
        assertEquals("Mexico", sql.getParamObjects()[0]);

        String expected1 = "SELECT * FROM Customers WHERE CustomerID > 1";
        AbstractQuery sql1 = SQLBuilder.select().from("Customers").where("CustomerID").greaterThan(1);
        assertEquals(expected1, sql1.getQuery());
        assertEquals(1, sql1.getParamObjects().length);
        assertEquals(1, sql1.getParamObjects()[0]);
    }

    @Test
    void testSelectFromWhereGreaterOrEqual() {
        String expected = "SELECT * FROM Customers WHERE Country >= 'Mexico'";
        AbstractQuery sql = SQLBuilder.select().from("Customers").where("Country").greaterOrEqual("Mexico");
        assertEquals(expected, sql.getQuery());
        assertEquals(1, sql.getParamObjects().length);
        assertEquals("Mexico", sql.getParamObjects()[0]);

        String expected1 = "SELECT * FROM Customers WHERE CustomerID >= 1";
        AbstractQuery sql1 = SQLBuilder.select().from("Customers").where("CustomerID").greaterOrEqual(1);
        assertEquals(expected1, sql1.getQuery());
        assertEquals(1, sql1.getParamObjects().length);
        assertEquals(1, sql1.getParamObjects()[0]);
    }

    @Test
    void testSelectFromWhereLessThan() {
        String expected = "SELECT * FROM Customers WHERE Country < 'Mexico'";
        AbstractQuery sql = SQLBuilder.select().from("Customers").where("Country").lessThan("Mexico");
        assertEquals(expected, sql.getQuery());
        assertEquals(1, sql.getParamObjects().length);
        assertEquals("Mexico", sql.getParamObjects()[0]);

        String expected1 = "SELECT * FROM Customers WHERE CustomerID < 10";
        AbstractQuery sql1 = SQLBuilder.select().from("Customers").where("CustomerID").lessThan(10);
        assertEquals(expected1, sql1.getQuery());
        assertEquals(1, sql1.getParamObjects().length);
        assertEquals(10, sql1.getParamObjects()[0]);
    }

    @Test
    void testSelectFromWhereLessOrEqual() {
        String expected = "SELECT * FROM Customers WHERE Country <= 'Mexico'";
        AbstractQuery sql = SQLBuilder.select().from("Customers").where("Country").lessOrEqual("Mexico");
        assertEquals(expected, sql.getQuery());
        assertEquals(1, sql.getParamObjects().length);
        assertEquals("Mexico", sql.getParamObjects()[0]);

        String expected1 = "SELECT * FROM Customers WHERE CustomerID <= 1";
        AbstractQuery sql1 = SQLBuilder.select().from("Customers").where("CustomerID").lessOrEqual(1);
        assertEquals(expected1, sql1.getQuery());
        assertEquals(1, sql1.getParamObjects().length);
        assertEquals(1, sql1.getParamObjects()[0]);
    }

    @Test
    void testSelectFromWhereEqAndNeAndLtAndGtAndLeAndGe() {
        String expected = "SELECT * FROM Customers WHERE Country = 'Mexico' AND City <> 'CityName' AND Price < 50 AND Unit > 'UnitDescription' AND Name <= 'Alice' AND ID >= 99";
        AbstractQuery sql = SQLBuilder.select().from("Customers")
                .where("Country").eq("Mexico")
                .and("City").ne("CityName")
                .and("Price").lt(50)
                .and("Unit").gt("UnitDescription")
                .and("Name").le("Alice")
                .and("ID").ge(99);
        assertEquals(expected, sql.getQuery());
    }

    @Test
    void testSelectFromOrderBy() {
        String expected = "SELECT * FROM Customers ORDER BY Country";
        AbstractQuery sql = SQLBuilder.select().from("Customers").orderBy("Country");
        assertEquals(expected, sql.getQuery());

        String expected1 = "SELECT * FROM Customers ORDER BY Country ASC";
        AbstractQuery sql1 = SQLBuilder.select().from("Customers").orderBy("Country").asc();
        assertEquals(expected1, sql1.getQuery());

        String expected2 = "SELECT * FROM Customers ORDER BY Country DESC";
        AbstractQuery sql2 = SQLBuilder.select().from("Customers").orderBy("Country").desc();
        assertEquals(expected2, sql2.getQuery());

        String expected3 = "SELECT * FROM Customers ORDER BY Country, CustomerName";
        AbstractQuery sql3 = SQLBuilder.select().from("Customers").orderBy("Country", "CustomerName");
        assertEquals(expected3, sql3.getQuery());
    }

    @Test
    void testSelectFromWhereIsNotNull() {
        String expected = "SELECT CustomerName, ContactName, Address FROM Customers WHERE Address IS NOT NULL";
        AbstractQuery sql = SQLBuilder.select("CustomerName", "ContactName", "Address")
                .from("Customers").where("Address").isNotNull();
        assertEquals(expected, sql.getQuery());
    }

    @Test
    void testInsertInto() {
        String expected = "INSERT INTO Customers (CustomerName, City, Country, Price) VALUES ('Cardinal', 'Stavanger', 'Norway', 99)";
        AbstractQuery sql = SQLBuilder
                .insertInto("Customers", "CustomerName", "City", "Country", "Price")
                .values("Cardinal", "Stavanger", "Norway", 99);
        assertEquals(expected, sql.getQuery());
        Object[] expectedParams = new Object[]{"Cardinal", "Stavanger", "Norway", 99};
        assertTrue(deepEqual(expectedParams, sql.getParamObjects()));
    }

    @Test
    void testUpdate() {
        String expected = "UPDATE Customers SET ContactName = 'Alfred Schmidt', City = 'Frankfurt', Price = 99 WHERE CustomerID = 1";
        AbstractQuery sql = SQLBuilder.update("Customers")
                .set("ContactName", "Alfred Schmidt")
                .set("City", "Frankfurt")
                .set("Price", 99)
                .where("CustomerID").eq(1);
        assertEquals(expected, sql.getQuery());

        AbstractQuery sql1 = SQLBuilder.update("Customers")
                .set(Arrays.asList("ContactName", "City", "Price"), Arrays.asList("Alfred Schmidt", "Frankfurt", 99))
                .where("CustomerID").eq(1);
        assertEquals(expected, sql1.getQuery());

        AbstractQuery sql2 = SQLBuilder.update("Customers")
                .set(Arrays.asList("ContactName", "City"), Arrays.asList("Alfred Schmidt", "Frankfurt"))
                .set("Price", 99)
                .where("CustomerID").eq(1);
        assertEquals(expected, sql2.getQuery());
    }

    @Test
    void testSelectFromGroupBy() {
        String expected = "SELECT working_area, COUNT (*) FROM agents GROUP BY working_area";
        AbstractQuery sql = SQLBuilder.select("working_area").count().from("agents").groupBy("working_area");
        assertEquals(expected, sql.getQuery());

        String expected1 = "SELECT working_area, COUNT (id) FROM agents GROUP BY working_area";
        AbstractQuery sql1 = SQLBuilder.select("working_area").count("id").from("agents").groupBy("working_area");
        assertEquals(expected1, sql1.getQuery());
    }

    @Test
    void testSelectFromForBusinessTimeAsOfWhere() {
        String expected = "SELECT PRICE FROM STOCK FOR BUSINESS_TIME AS OF '2020-01-01' WHERE STOCK_NAME = 'APPLE INC'";
        AbstractQuery sql = SQLBuilder.select("PRICE").from("STOCK")
                .forBusinessTimeAsOf(LocalDate.of(2020, 1, 1))
                .where("STOCK_NAME").eq("APPLE INC");
        assertEquals(expected, sql.getQuery());
    }

    @Test
    void testUpdateForPortionBusinessTimeFromToWhere() {
        String expected = "UPDATE inventory FOR PORTION OF BUSINESS_TIME FROM '2015-06-01' TO '2016-09-01' SET price = 900000 WHERE id = 1111";
        AbstractQuery sql = SQLBuilder.update("inventory")
                .forPortionOfBusinessTimeFrom(LocalDate.of(2015, 6, 1))
                .to(LocalDate.of(2016, 9, 1))
                .set("price", 900000)
                .where("id").eq(1111);
        assertEquals(expected, sql.getQuery());
    }

    @Test
    void testSelectFromForBusinessTimeFromTo() {
        String expected = "SELECT * FROM policy FOR BUSINESS_TIME FROM '2014-01-01' TO '2016-01-01' WHERE id = 1414";
        AbstractQuery sql = SQLBuilder.select().from("policy")
                .forBusinessTimeFrom(LocalDate.of(2014, 1, 1))
                .to(LocalDate.of(2016, 1, 1))
                .where("id").eq(1414);
        assertEquals(expected, sql.getQuery());
    }

    @Test
    void testDeleteFromForPortionOfBusinessTimeFromToWhere() {
        String expected = "DELETE FROM policy_info FOR PORTION OF BUSINESS_TIME FROM '2008-06-15' TO '2008-08-15' WHERE policy_id = 'A123'";
        AbstractQuery sql = SQLBuilder.deleteFrom("policy_info")
                .forPortionOfBusinessTimeFrom(LocalDate.of(2008, 6, 15))
                .to(LocalDate.of(2008, 8, 15))
                .where("policy_id").eq("A123");
        assertEquals(expected, sql.getQuery());
    }

    @Test
    void testDeleteFromWhere() {
        String expected = "DELETE FROM Customers WHERE CustomerName = 'Alfreds Futterkiste'";
        AbstractQuery sql = SQLBuilder.deleteFrom("Customers").where("CustomerName").eq("Alfreds Futterkiste");
        assertEquals(expected, sql.getQuery());
    }

    @Test
    void testSelectFromWhereLike() {
        String expected = "SELECT * FROM Customers WHERE CustomerName LIKE 'a%'";
        AbstractQuery sql = SQLBuilder.select().from("Customers").where("CustomerName").startsWith("a");
        assertEquals(expected, sql.getQuery());

        String expected1 = "SELECT * FROM Customers WHERE CustomerName LIKE '%a123'";
        AbstractQuery sql1 = SQLBuilder.select().from("Customers").where("CustomerName").endsWith("a123");
        assertEquals(expected1, sql1.getQuery());

        String expected2 = "SELECT * FROM Customers WHERE CustomerName LIKE '%a123%'";
        AbstractQuery sql2 = SQLBuilder.select().from("Customers").where("CustomerName").contains("a123");
        assertEquals(expected2, sql2.getQuery());

        String expected3 = "SELECT * FROM Customers WHERE CustomerName LIKE 'a123'";
        AbstractQuery sql3 = SQLBuilder.select().from("Customers").where("CustomerName").like("a123");
        assertEquals(expected3, sql3.getQuery());
    }

    @Test
    void testSelectFromWhereIn() {
        String expected = "SELECT * FROM Customers WHERE Country IN ('Germany', 'France', 'UK')";
        AbstractQuery sql = SQLBuilder.select().from("Customers").where("Country").in("Germany", "France", "UK");
        assertEquals(expected, sql.getQuery());

        AbstractQuery sql1 = SQLBuilder.select().from("Customers").where("Country").in(Arrays.asList("Germany", "France", "UK"));
        assertEquals(expected, sql1.getQuery());

    }

    @Test
    void testSelectFromWhereNotIn() {
        String expected = "SELECT * FROM Customers WHERE Country NOT IN ('Germany', 'France', 'UK')";
        AbstractQuery sql = SQLBuilder.select().from("Customers").where("Country").notIn("Germany", "France", "UK");
        assertEquals(expected, sql.getQuery());

        AbstractQuery sql1 = SQLBuilder.select().from("Customers").where("Country").notIn(Arrays.asList("Germany", "France", "UK"));
        assertEquals(expected, sql1.getQuery());
    }

    @Test
    void testSelectFromWhereBetweenAndNotIn() {
        String expected = "SELECT * FROM Products WHERE Price BETWEEN 10 AND 20 AND CategoryID NOT IN (1, 2, 3)";
        AbstractQuery sql = SQLBuilder.select().from("Products").where("Price").between(10, 20).and("CategoryID").notIn(1, 2, 3);
        assertEquals(expected, sql.getQuery());
    }

    @Test
    void testSelectFromWhereBetweenOrNotIn() {
        String expected = "SELECT * FROM Products WHERE Price BETWEEN 10 AND 20 OR CategoryID NOT IN (1, 2, 3)";
        AbstractQuery sql = SQLBuilder.select().from("Products").where("Price").between(10, 20).or("CategoryID").notIn(1, 2, 3);
        assertEquals(expected, sql.getQuery());
    }

    @Test
    void testSelectFromAsWhereAnd() {
        String expected = "SELECT o.OrderID, o.OrderDate, c.CustomerName FROM Customers AS c, Orders AS o WHERE c.CustomerName = 'Around the Horn' AND c.CustomerID = o.CustomerID";
        AbstractQuery sql = SQLBuilder.select("o.OrderID", "o.OrderDate", "c.CustomerName")
                .from("Customers").as("c").comma("Orders").as("o")
                .where("c.CustomerName").eq("Around the Horn")
                .and("c.CustomerID").eqCol("o.CustomerID");
        assertEquals(expected, sql.getQuery());

        String expected1 = "SELECT o.OrderID, o.OrderDate, c.CustomerName FROM Customers AS c, Orders AS o WHERE c.CustomerName = 'Around the Horn' AND c.CustomerID <> o.CustomerID";
        AbstractQuery sql1 = SQLBuilder.select("o.OrderID", "o.OrderDate", "c.CustomerName")
                .from("Customers").as("c").comma("Orders").as("o")
                .where("c.CustomerName").eq("Around the Horn")
                .and("c.CustomerID").neCol("o.CustomerID");
        assertEquals(expected1, sql1.getQuery());

        String expected2 = "SELECT o.OrderID, o.OrderDate, c.CustomerName FROM Customers AS c, Orders AS o WHERE c.CustomerName = 'Around the Horn' AND c.CustomerID > o.CustomerID";
        AbstractQuery sql2 = SQLBuilder.select("o.OrderID", "o.OrderDate", "c.CustomerName")
                .from("Customers").as("c").comma("Orders").as("o")
                .where("c.CustomerName").eq("Around the Horn")
                .and("c.CustomerID").gtCol("o.CustomerID");
        assertEquals(expected2, sql2.getQuery());

        String expected3 = "SELECT o.OrderID, o.OrderDate, c.CustomerName FROM Customers AS c, Orders AS o WHERE c.CustomerName = 'Around the Horn' AND c.CustomerID < o.CustomerID";
        AbstractQuery sql3 = SQLBuilder.select("o.OrderID", "o.OrderDate", "c.CustomerName")
                .from("Customers").as("c").comma("Orders").as("o")
                .where("c.CustomerName").eq("Around the Horn")
                .and("c.CustomerID").ltCol("o.CustomerID");
        assertEquals(expected3, sql3.getQuery());

        String expected4 = "SELECT o.OrderID, o.OrderDate, c.CustomerName FROM Customers AS c, Orders AS o WHERE c.CustomerName = 'Around the Horn' AND c.CustomerID >= o.CustomerID";
        AbstractQuery sql4 = SQLBuilder.select("o.OrderID", "o.OrderDate", "c.CustomerName")
                .from("Customers").as("c").comma("Orders").as("o")
                .where("c.CustomerName").eq("Around the Horn")
                .and("c.CustomerID").geCol("o.CustomerID");
        assertEquals(expected4, sql4.getQuery());

        String expected5 = "SELECT o.OrderID, o.OrderDate, c.CustomerName FROM Customers AS c, Orders AS o WHERE c.CustomerName = 'Around the Horn' AND c.CustomerID <= o.CustomerID";
        AbstractQuery sql5 = SQLBuilder.select("o.OrderID", "o.OrderDate", "c.CustomerName")
                .from("Customers").as("c").comma("Orders").as("o")
                .where("c.CustomerName").eq("Around the Horn")
                .and("c.CustomerID").leCol("o.CustomerID");
        assertEquals(expected5, sql5.getQuery());
    }

    @Test
    void testSelectFromJoinOn() {
        String expected = "SELECT Orders.OrderID, Customers.CustomerName, Orders.OrderDate FROM Orders JOIN Customers ON Customers.CustomerID = Orders.CustomerID AND Customers.CustomerName > 'Joerg'";
        AbstractQuery sql = SQLBuilder.select("Orders.OrderID", "Customers.CustomerName", "Orders.OrderDate")
                .from("Orders").join("Customers").on("Customers.CustomerID").eqCol("Orders.CustomerID")
                .and("Customers.CustomerName").gt("Joerg");
        assertEquals(expected, sql.getQuery());
    }

    @Test
    void testSelectFromJoinOnWithAliases() {
        String expected = "SELECT o.OrderID, c.CustomerName, o.OrderDate FROM Orders o JOIN Customers c ON o.CustomerID = c.CustomerID AND c.CustomerName <= 'Joerg'";
        AbstractQuery sql = SQLBuilder.select("o.OrderID", "c.CustomerName", "o.OrderDate")
                .from("Orders", "o").join("Customers", "c")
                .on("o.CustomerID").eqCol("c.CustomerID")
                .and("c.CustomerName").le("Joerg");
        assertEquals(expected, sql.getQuery());

        String expected1 = "SELECT o.OrderID, c.CustomerName, o.OrderDate FROM Orders AS o JOIN Customers AS c ON o.CustomerID = c.CustomerID AND c.CustomerName >= 'Joerg'";
        AbstractQuery sql1 = SQLBuilder.select("o.OrderID", "c.CustomerName", "o.OrderDate")
                .from("Orders").as("o").join("Customers").as("c")
                .on("o.CustomerID").eqCol("c.CustomerID")
                .and("c.CustomerName").ge("Joerg");
        assertEquals(expected1, sql1.getQuery());
    }

    @Test
    void testSelectFromLeftJoinOnOrderBy() {
        String expected = "SELECT Customers.CustomerName, Orders.OrderID FROM Customers LEFT JOIN Orders ON Customers.CustomerID = Orders.CustomerID ORDER BY Customers.CustomerName";
        AbstractQuery sql = SQLBuilder.select("Customers.CustomerName", "Orders.OrderID")
                .from("Customers").leftJoin("Orders")
                .on("Customers.CustomerID").eqCol("Orders.CustomerID")
                .orderBy("Customers.CustomerName");
        assertEquals(expected, sql.getQuery());
    }

    @Test
    void testSelectFromRightJoinOnOrderBy() {
        String expected = "SELECT Orders.OrderID, Employees.LastName, Employees.FirstName FROM Orders RIGHT JOIN Employees ON Orders.EmployeeID = Employees.EmployeeID ORDER BY Orders.OrderID";
        AbstractQuery sql = SQLBuilder.select("Orders.OrderID", "Employees.LastName", "Employees.FirstName")
                .from("Orders").rightJoin("Employees").on("Orders.EmployeeID").eqCol("Employees.EmployeeID")
                .orderBy("Orders.OrderID");
        assertEquals(expected, sql.getQuery());
    }

    @Test
    void testSelectFromFullOuterJoinOnOrderBy() {
        String expected = "SELECT Customers.CustomerName, Orders.OrderID FROM Customers FULL JOIN Orders ON Customers.CustomerID = Orders.CustomerID ORDER BY Customers.CustomerName";
        AbstractQuery sql = SQLBuilder.select("Customers.CustomerName", "Orders.OrderID")
                .from("Customers").fullJoin("Orders")
                .on("Customers.CustomerID").eqCol("Orders.CustomerID")
                .orderBy("Customers.CustomerName");
        assertEquals(expected, sql.getQuery());
    }

    @Test
    void testSelectAsAsFromWhereAndOrderBy() {
        String expected = "SELECT A.CustomerName AS CustomerName1, B.CustomerName AS CustomerName2, A.City FROM Customers A, Customers B WHERE A.CustomerID <> B.CustomerID AND A.City = B.City ORDER BY A.City";
        AbstractQuery sql = SQLBuilder.select("A.CustomerName").as("CustomerName1")
                .comma("B.CustomerName").as("CustomerName2")
                .comma("A.City")
                .from("Customers A").comma("Customers B")
                .where("A.CustomerID").neCol("B.CustomerID")
                .and("A.City").eqCol("B.City")
                .orderBy("A.City");
        assertEquals(expected, sql.getQuery());
    }

    @Test
    void testSelectFromUnionSelectFromOrderBy() {
        String expected = "SELECT City FROM Customers UNION SELECT City FROM Suppliers ORDER BY City";
        AbstractQuery sql = SQLBuilder.select("City").from("Customers").union().select("City").from("Suppliers").orderBy("City");
        assertEquals(expected, sql.getQuery());

        String expected1 = "SELECT City FROM Customers UNION ALL SELECT City FROM Suppliers ORDER BY City";
        AbstractQuery sql1 = SQLBuilder.select("City").from("Customers").unionAll().select("City").from("Suppliers").orderBy("City");
        assertEquals(expected1, sql1.getQuery());

        String expected2 = "SELECT City, Country FROM Customers WHERE Country = 'Germany' UNION SELECT City, Country FROM Suppliers WHERE Country = 'Germany' ORDER BY City";
        AbstractQuery sql2 = SQLBuilder.select("City", "Country").from("Customers")
                .where("Country").eq("Germany")
                .union()
                .select("City", "Country").from("Suppliers")
                .where("Country").eq("Germany")
                .orderBy("City");
        assertEquals(expected2, sql2.getQuery());

        String expected3 = "SELECT City, Country FROM Customers WHERE Country = 'Germany' UNION ALL SELECT City, Country FROM Suppliers WHERE Country = 'Germany' ORDER BY City";
        AbstractQuery sql3 = SQLBuilder.select("City", "Country").from("Customers")
                .where("Country").eq("Germany")
                .unionAll()
                .select("City", "Country").from("Suppliers")
                .where("Country").eq("Germany")
                .orderBy("City");
        assertEquals(expected3, sql3.getQuery());
    }

    @Test
    void testSelectCountFromGroupByOrderByCountDesc() {
        String expected = "SELECT COUNT (CustomerID), Country FROM Customers GROUP BY Country ORDER BY COUNT (CustomerID) DESC";
        AbstractQuery sql = SQLBuilder.selectCount("CustomerID").comma("Country").from("Customers").groupBy("Country").orderByCount("CustomerID").desc();
        assertEquals(expected, sql.getQuery());

        String expected1 = "SELECT Shippers.ShipperName, COUNT (Orders.OrderID) AS NumberOfOrders FROM Orders LEFT JOIN Shippers ON Orders.ShipperID = Shippers.ShipperID GROUP BY ShipperName";
        AbstractQuery sql1 = SQLBuilder.select("Shippers.ShipperName").count("Orders.OrderID").as("NumberOfOrders")
                .from("Orders")
                .leftJoin("Shippers").on("Orders.ShipperID").eqCol("Shippers.ShipperID")
                .groupBy("ShipperName");
        assertEquals(expected1, sql1.getQuery());
    }

    @Test
    void testSelectCountFromGroupByHavingCountOrderByCountDesc() {
        String expected = "SELECT COUNT (CustomerID), Country FROM Customers GROUP BY Country HAVING COUNT (CustomerID) > 5 ORDER BY COUNT (CustomerID) DESC";
        AbstractQuery sql = SQLBuilder.selectCount("CustomerID").comma("Country").from("Customers").groupBy("Country").havingCount("CustomerID").gt(5).orderByCount("CustomerID").desc();
        assertEquals(expected, sql.getQuery());
        AbstractQuery sql1 = SQLBuilder.selectCount("CustomerID").comma("Country").from("Customers").groupBy("Country").havingCount("CustomerID").gt(5).orderBy().count("CustomerID").desc();
        assertEquals(expected, sql1.getQuery());
    }

    @Test
    void testSelectCountAsFromJoinOnGroupByHavingCount() {
        String expected = "SELECT E.LastName, COUNT(O.OrderID) AS NumberOfOrders FROM (Orders INNER JOIN Employees ON O.EmployeeID = E.EmployeeID) GROUP BY LastName HAVING COUNT(O.OrderID) > 10";
        // TODO
    }

    @Test
    void testSelectFromWhereInSelectDistinctFrom() {
        String expected = "SELECT * FROM users WHERE id IN (SELECT DISTINCT user_id FROM questions)";
        AbstractQuery aq = SQLBuilder.select().from("users")
                .where("id").in(SQLBuilder.selectDistinct("user_id").from("questions"));
        assertEquals(expected, aq.getQuery());
    }

    @Test
    void testSelectFromWhereExistsSelectFromWhereAnd() {
        String expected = "SELECT SupplierName FROM Suppliers WHERE EXISTS (SELECT ProductName FROM Products WHERE Products.SupplierID = Suppliers.supplierID AND Price >= 20)";
        AbstractQuery aq = SQLBuilder.select("SupplierName").from("Suppliers")
                .where().exists(
                        SQLBuilder.select("ProductName").from("Products")
                                .where("Products.SupplierID").eqCol("Suppliers.supplierID")
                                .and("Price").ge(20)
                );
        assertEquals(expected, aq.getQuery());

        String expected1 = "SELECT SupplierName FROM Suppliers WHERE NOT EXISTS (SELECT ProductName FROM Products WHERE Products.SupplierID = Suppliers.supplierID AND Price < 20)";
        AbstractQuery aq1 = SQLBuilder.select("SupplierName").from("Suppliers")
                .where().not().exists(
                        SQLBuilder.select("ProductName").from("Products")
                                .where("Products.SupplierID").eqCol("Suppliers.supplierID")
                                .and("Price").lt(20)
                );
        assertEquals(expected1, aq1.getQuery());
    }

    @Test
    void selectAvgFrom() {
        String expected = "SELECT AVG (Price) FROM Products";
        AbstractQuery aq = SQLBuilder.selectAvg("Price").from("Products");
        assertEquals(expected, aq.getQuery());
    }

    @Test
    void testSelectedSum() {
        String expected = "SELECT SUM (Quantity) FROM OrderDetails";
        AbstractQuery aq = SQLBuilder.selectSum("Quantity").from("OrderDetails");
        assertEquals(expected, aq.getQuery());
    }

    @Test
    void selectFromWhereAnySelectFromWhere() {
        String expected = "SELECT ProductName FROM Products WHERE ProductID = ANY (SELECT ProductID FROM OrderDetails WHERE Quantity = 10)";
        AbstractQuery aq = SQLBuilder.select("ProductName").from("Products")
                .where("ProductID").eqAny(
                        SQLBuilder.select("ProductID").from("OrderDetails").where("Quantity").eq(10)
                );
        assertEquals(expected, aq.getQuery());
    }

    @Test
    void selectFromWhereAllSelectFromWhere() {
        String expected = "SELECT ProductName FROM Products WHERE ProductID = ALL (SELECT ProductID FROM OrderDetails WHERE Quantity = 10)";
        AbstractQuery aq = SQLBuilder.select("ProductName").from("Products")
                .where("ProductID").eqAll(
                        SQLBuilder.select("ProductID").from("OrderDetails").where("Quantity").eq(10)
                );
        assertEquals(expected, aq.getQuery());
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
