import org.junit.jupiter.api.Test;
import sql.BaseSQL;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SQLBuilderTest {

    @Test
    void testSelect() {
        String expected = "SELECT CustomerName, City FROM Customers";
        BaseSQL sql = SQLBuilder.select("CustomerName", "City").from("Customers");
        assertEquals(expected, sql.getQuery());

        String expected1 = "SELECT * FROM Customers";
        BaseSQL sql1 = SQLBuilder.select().from("Customers");
        assertEquals(expected1, sql1.getQuery());
    }

    @Test
    void testSelectDistinct() {
        String expected = "SELECT DISTINCT Country FROM Customers";
        BaseSQL sql = SQLBuilder.selectDistinct("Country").from("Customers");
        assertEquals(expected, sql.getQuery());

        String expected1 = "SELECT DISTINCT * FROM Customers";
        BaseSQL sql1 = SQLBuilder.selectDistinct().from("Customers");
        assertEquals(expected1, sql1.getQuery());
    }

    @Test
    void testSelectCount() {
        String expected = "SELECT COUNT (ProductID) FROM Products";
        BaseSQL sql = SQLBuilder.selectCount("ProductID").from("Products");
        assertEquals(expected, sql.getQuery());

        String expected1 = "SELECT COUNT (*) FROM Products";
        BaseSQL sql1 = SQLBuilder.selectCount().from("Products");
        assertEquals(expected1, sql1.getQuery());

    }

    @Test
    void testSelectCountDistinct() {
        String expected = "SELECT COUNT (DISTINCT Country) FROM Customers";
        BaseSQL sql = SQLBuilder.selectCountDistinct("Country").from("Customers");
        assertEquals(expected, sql.getQuery());
    }

    @Test
    void testSelectFromWhereEqual() {
        String expected = "SELECT * FROM Customers WHERE Country = 'Mexico'";
        BaseSQL sql = SQLBuilder.select().from("Customers").where("Country").equal("Mexico");
        assertEquals(expected, sql.getQuery());
        assertEquals(1, sql.getParamObjects().length);
        assertEquals("Mexico", sql.getParamObjects()[0]);

        String expected1 = "SELECT * FROM Customers WHERE CustomerID = 1";
        BaseSQL sql1 = SQLBuilder.select().from("Customers").where("CustomerID").equal(1);
        assertEquals(expected1, sql1.getQuery());
        assertEquals(1, sql1.getParamObjects().length);
        assertEquals(1, sql1.getParamObjects()[0]);
    }

    @Test
    void testSelectFromWhereNotEqual() {
        String expected = "SELECT * FROM Customers WHERE Country <> 'Mexico'";
        BaseSQL sql = SQLBuilder.select().from("Customers").where("Country").notEqual("Mexico");
        assertEquals(expected, sql.getQuery());
        assertEquals(1, sql.getParamObjects().length);
        assertEquals("Mexico", sql.getParamObjects()[0]);

        String expected1 = "SELECT * FROM Customers WHERE CustomerID <> 1";
        BaseSQL sql1 = SQLBuilder.select().from("Customers").where("CustomerID").notEqual(1);
        assertEquals(expected1, sql1.getQuery());
        assertEquals(1, sql1.getParamObjects().length);
        assertEquals(1, sql1.getParamObjects()[0]);
    }

    @Test
    void testSelectFromWhereGreaterThan() {
        String expected = "SELECT * FROM Customers WHERE Country > 'Mexico'";
        BaseSQL sql = SQLBuilder.select().from("Customers").where("Country").greaterThan("Mexico");
        assertEquals(expected, sql.getQuery());
        assertEquals(1, sql.getParamObjects().length);
        assertEquals("Mexico", sql.getParamObjects()[0]);

        String expected1 = "SELECT * FROM Customers WHERE CustomerID > 1";
        BaseSQL sql1 = SQLBuilder.select().from("Customers").where("CustomerID").greaterThan(1);
        assertEquals(expected1, sql1.getQuery());
        assertEquals(1, sql1.getParamObjects().length);
        assertEquals(1, sql1.getParamObjects()[0]);
    }

    @Test
    void testSelectFromWhereGreaterOrEqual() {
        String expected = "SELECT * FROM Customers WHERE Country >= 'Mexico'";
        BaseSQL sql = SQLBuilder.select().from("Customers").where("Country").greaterOrEqual("Mexico");
        assertEquals(expected, sql.getQuery());
        assertEquals(1, sql.getParamObjects().length);
        assertEquals("Mexico", sql.getParamObjects()[0]);

        String expected1 = "SELECT * FROM Customers WHERE CustomerID >= 1";
        BaseSQL sql1 = SQLBuilder.select().from("Customers").where("CustomerID").greaterOrEqual(1);
        assertEquals(expected1, sql1.getQuery());
        assertEquals(1, sql1.getParamObjects().length);
        assertEquals(1, sql1.getParamObjects()[0]);
    }

    @Test
    void testSelectFromWhereLessThan() {
        String expected = "SELECT * FROM Customers WHERE Country < 'Mexico'";
        BaseSQL sql = SQLBuilder.select().from("Customers").where("Country").lessThan("Mexico");
        assertEquals(expected, sql.getQuery());
        assertEquals(1, sql.getParamObjects().length);
        assertEquals("Mexico", sql.getParamObjects()[0]);

        String expected1 = "SELECT * FROM Customers WHERE CustomerID < 10";
        BaseSQL sql1 = SQLBuilder.select().from("Customers").where("CustomerID").lessThan(10);
        assertEquals(expected1, sql1.getQuery());
        assertEquals(1, sql1.getParamObjects().length);
        assertEquals(10, sql1.getParamObjects()[0]);
    }

    @Test
    void testSelectFromWhereLessOrEqual() {
        String expected = "SELECT * FROM Customers WHERE Country <= 'Mexico'";
        BaseSQL sql = SQLBuilder.select().from("Customers").where("Country").lessOrEqual("Mexico");
        assertEquals(expected, sql.getQuery());
        assertEquals(1, sql.getParamObjects().length);
        assertEquals("Mexico", sql.getParamObjects()[0]);

        String expected1 = "SELECT * FROM Customers WHERE CustomerID <= 1";
        BaseSQL sql1 = SQLBuilder.select().from("Customers").where("CustomerID").lessOrEqual(1);
        assertEquals(expected1, sql1.getQuery());
        assertEquals(1, sql1.getParamObjects().length);
        assertEquals(1, sql1.getParamObjects()[0]);
    }

    @Test
    void testSelectFromWhereEqAndNeAndLtAndGtAndLeAndGe() {
        String expected = "SELECT * FROM Customers WHERE Country = 'Mexico' AND City <> 'CityName' AND Price < 50 AND Unit > 'UnitDescription' AND Name <= 'Alice' AND ID >= 99";
        BaseSQL sql = SQLBuilder.select().from("Customers")
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
        BaseSQL sql = SQLBuilder.select().from("Customers").orderBy("Country");
        assertEquals(expected, sql.getQuery());

        String expected1 = "SELECT * FROM Customers ORDER BY Country ASC";
        BaseSQL sql1 = SQLBuilder.select().from("Customers").orderBy("Country").asc();
        assertEquals(expected1, sql1.getQuery());

        String expected2 = "SELECT * FROM Customers ORDER BY Country DESC";
        BaseSQL sql2 = SQLBuilder.select().from("Customers").orderBy("Country").desc();
        assertEquals(expected2, sql2.getQuery());

        String expected3 = "SELECT * FROM Customers ORDER BY Country, CustomerName";
        BaseSQL sql3 = SQLBuilder.select().from("Customers").orderBy("Country", "CustomerName");
        assertEquals(expected3, sql3.getQuery());
    }

    @Test
    void testSelectFromWhereIsNotNull() {
        String expected = "SELECT CustomerName, ContactName, Address FROM Customers WHERE Address IS NOT NULL";
        BaseSQL sql = SQLBuilder.select("CustomerName", "ContactName", "Address")
                .from("Customers").where("Address").isNotNull();
        assertEquals(expected, sql.getQuery());
    }

    @Test
    void testInsertInto() {
        String expected = "INSERT INTO Customers (CustomerName, City, Country, Price) VALUES ('Cardinal', 'Stavanger', 'Norway', 99)";
        BaseSQL sql = SQLBuilder
                .insertInto("Customers", "CustomerName", "City", "Country", "Price")
                .values("Cardinal", "Stavanger", "Norway", 99);
        assertEquals(expected, sql.getQuery());
        Object[] expectedParams = new Object[]{"Cardinal", "Stavanger", "Norway", 99};
        assertTrue(deepEqual(expectedParams, sql.getParamObjects()));
    }

    @Test
    void testUpdate() {
        String expected = "UPDATE Customers SET ContactName = 'Alfred Schmidt', City = 'Frankfurt', Price = 99 WHERE CustomerID = 1";
        BaseSQL sql = SQLBuilder.update("Customers")
                .set("ContactName", "Alfred Schmidt")
                .set("City", "Frankfurt")
                .set("Price", 99)
                .where("CustomerID").eq(1);
        assertEquals(expected, sql.getQuery());

        BaseSQL sql1 = SQLBuilder.update("Customers")
                .set(Arrays.asList("ContactName", "City", "Price"), Arrays.asList("Alfred Schmidt", "Frankfurt", 99))
                .where("CustomerID").eq(1);
        assertEquals(expected, sql1.getQuery());

        BaseSQL sql2 = SQLBuilder.update("Customers")
                .set(Arrays.asList("ContactName", "City"), Arrays.asList("Alfred Schmidt", "Frankfurt"))
                .set("Price", 99)
                .where("CustomerID").eq(1);
        assertEquals(expected, sql2.getQuery());
    }

    @Test
    void testSelectFromGroupBy() {
        String expected = "SELECT working_area, COUNT (*) FROM agents GROUP BY working_area";
        BaseSQL sql = SQLBuilder.select("working_area").count().from("agents").groupBy("working_area");
        assertEquals(expected, sql.getQuery());

        String expected1 = "SELECT working_area, COUNT (id) FROM agents GROUP BY working_area";
        BaseSQL sql1 = SQLBuilder.select("working_area").count("id").from("agents").groupBy("working_area");
        assertEquals(expected1, sql1.getQuery());
    }

    @Test
    void testSelectFromForBusinessTimeAsOfWhere() {
        String expected = "SELECT PRICE FROM STOCK FOR BUSINESS_TIME AS OF '2020-01-01' WHERE STOCK_NAME = 'APPLE INC'";
        BaseSQL sql = SQLBuilder.select("PRICE").from("STOCK")
                .forBusinessTimeAsOf(LocalDate.of(2020, 1, 1))
                .where("STOCK_NAME").eq("APPLE INC");
        assertEquals(expected, sql.getQuery());
    }

    @Test
    void testUpdateForPortionBusinessTimeFromToWhere() {
        String expected = "UPDATE inventory FOR PORTION OF BUSINESS_TIME FROM '2015-06-01' TO '2016-09-01' SET price = 900000 WHERE id = 1111";
        BaseSQL sql = SQLBuilder.update("inventory")
                .forPortionOfBusinessTimeFrom(LocalDate.of(2015, 6, 1))
                .to(LocalDate.of(2016, 9, 1))
                .set("price", 900000)
                .where("id").eq(1111);
        assertEquals(expected, sql.getQuery());
    }

    @Test
    void testSelectFromForBusinessTimeFromTo() {
        String expected = "SELECT * FROM policy FOR BUSINESS_TIME FROM '2014-01-01' TO '2016-01-01' WHERE id = 1414";
        BaseSQL sql = SQLBuilder.select().from("policy")
                .forBusinessTimeFrom(LocalDate.of(2014, 1, 1))
                .to(LocalDate.of(2016, 1, 1))
                .where("id").eq(1414);
        assertEquals(expected, sql.getQuery());
    }

    @Test
    void testDeleteFromForPortionOfBusinessTimeFromToWhere() {
        String expected = "DELETE FROM policy_info FOR PORTION OF BUSINESS_TIME FROM '2008-06-15' TO '2008-08-15' WHERE policy_id = 'A123'";
        BaseSQL sql = SQLBuilder.deleteFrom("policy_info")
                .forPortionOfBusinessTimeFrom(LocalDate.of(2008, 6, 15))
                .to(LocalDate.of(2008, 8, 15))
                .where("policy_id").eq("A123");
        assertEquals(expected, sql.getQuery());
    }

    @Test
    void testDeleteFromWhere() {
        String expected = "DELETE FROM Customers WHERE CustomerName = 'Alfreds Futterkiste'";
        BaseSQL sql = SQLBuilder.deleteFrom("Customers").where("CustomerName").eq("Alfreds Futterkiste");
        assertEquals(expected, sql.getQuery());
    }

    @Test
    void testSelectFromWhereLike() {
        String expected = "SELECT * FROM Customers WHERE CustomerName LIKE 'a%'";
        BaseSQL sql = SQLBuilder.select().from("Customers").where("CustomerName").startsWith("a");
        assertEquals(expected, sql.getQuery());

        String expected1 = "SELECT * FROM Customers WHERE CustomerName LIKE '%a123'";
        BaseSQL sql1 = SQLBuilder.select().from("Customers").where("CustomerName").endsWith("a123");
        assertEquals(expected1, sql1.getQuery());

        String expected2 = "SELECT * FROM Customers WHERE CustomerName LIKE '%a123%'";
        BaseSQL sql2 = SQLBuilder.select().from("Customers").where("CustomerName").contains("a123");
        assertEquals(expected2, sql2.getQuery());

        String expected3 = "SELECT * FROM Customers WHERE CustomerName LIKE 'a123'";
        BaseSQL sql3 = SQLBuilder.select().from("Customers").where("CustomerName").like("a123");
        assertEquals(expected3, sql3.getQuery());
    }

    @Test
    void testSelectFromWhereIn() {
        String expected = "SELECT * FROM Customers WHERE Country IN ('Germany', 'France', 'UK')";
        BaseSQL sql = SQLBuilder.select().from("Customers").where("Country").in("Germany", "France", "UK");
        assertEquals(expected, sql.getQuery());

        BaseSQL sql1 = SQLBuilder.select().from("Customers").where("Country").in(Arrays.asList("Germany", "France", "UK"));
        assertEquals(expected, sql1.getQuery());

    }

    @Test
    void testSelectFromWhereNotIn() {
        String expected = "SELECT * FROM Customers WHERE Country NOT IN ('Germany', 'France', 'UK')";
        BaseSQL sql = SQLBuilder.select().from("Customers").where("Country").notIn("Germany", "France", "UK");
        assertEquals(expected, sql.getQuery());

        BaseSQL sql1 = SQLBuilder.select().from("Customers").where("Country").notIn(Arrays.asList("Germany", "France", "UK"));
        assertEquals(expected, sql1.getQuery());
    }

    @Test
    void testSelectFromWhereBetweenAndNotIn() {
        String expected = "SELECT * FROM Products WHERE Price BETWEEN 10 AND 20 AND CategoryID NOT IN (1, 2, 3)";
        BaseSQL sql = SQLBuilder.select().from("Products").where("Price").between(10, 20).and("CategoryID").notIn(1, 2, 3);
        assertEquals(expected, sql.getQuery());
    }

    @Test
    void testSelectFromWhereBetweenOrNotIn() {
        String expected = "SELECT * FROM Products WHERE Price BETWEEN 10 AND 20 OR CategoryID NOT IN (1, 2, 3)";
        BaseSQL sql = SQLBuilder.select().from("Products").where("Price").between(10, 20).or("CategoryID").notIn(1, 2, 3);
        assertEquals(expected, sql.getQuery());
    }

    @Test
    void testSelectFromAsWhereAnd() {
        String expected = "SELECT o.OrderID, o.OrderDate, c.CustomerName FROM Customers AS c, Orders AS o WHERE c.CustomerName = 'Around the Horn' AND c.CustomerID = o.CustomerID";
        BaseSQL sql = SQLBuilder.select("o.OrderID", "o.OrderDate", "c.CustomerName")
                .from("Customers").as("c")
                .from("Orders").as("o")
                .where("c.CustomerName").eq("Around the Horn")
                .and("c.CustomerID").eqCol("o.CustomerID");
        assertEquals(expected, sql.getQuery());

        String expected1 = "SELECT o.OrderID, o.OrderDate, c.CustomerName FROM Customers AS c, Orders AS o WHERE c.CustomerName = 'Around the Horn' AND c.CustomerID <> o.CustomerID";
        BaseSQL sql1 = SQLBuilder.select("o.OrderID", "o.OrderDate", "c.CustomerName")
                .from("Customers").as("c")
                .from("Orders").as("o")
                .where("c.CustomerName").eq("Around the Horn")
                .and("c.CustomerID").neCol("o.CustomerID");
        assertEquals(expected1, sql1.getQuery());

        String expected2 = "SELECT o.OrderID, o.OrderDate, c.CustomerName FROM Customers AS c, Orders AS o WHERE c.CustomerName = 'Around the Horn' AND c.CustomerID > o.CustomerID";
        BaseSQL sql2 = SQLBuilder.select("o.OrderID", "o.OrderDate", "c.CustomerName")
                .from("Customers").as("c")
                .from("Orders").as("o")
                .where("c.CustomerName").eq("Around the Horn")
                .and("c.CustomerID").gtCol("o.CustomerID");
        assertEquals(expected2, sql2.getQuery());

        String expected3 = "SELECT o.OrderID, o.OrderDate, c.CustomerName FROM Customers AS c, Orders AS o WHERE c.CustomerName = 'Around the Horn' AND c.CustomerID < o.CustomerID";
        BaseSQL sql3 = SQLBuilder.select("o.OrderID", "o.OrderDate", "c.CustomerName")
                .from("Customers").as("c")
                .from("Orders").as("o")
                .where("c.CustomerName").eq("Around the Horn")
                .and("c.CustomerID").ltCol("o.CustomerID");
        assertEquals(expected3, sql3.getQuery());

        String expected4 = "SELECT o.OrderID, o.OrderDate, c.CustomerName FROM Customers AS c, Orders AS o WHERE c.CustomerName = 'Around the Horn' AND c.CustomerID >= o.CustomerID";
        BaseSQL sql4 = SQLBuilder.select("o.OrderID", "o.OrderDate", "c.CustomerName")
                .from("Customers").as("c")
                .from("Orders").as("o")
                .where("c.CustomerName").eq("Around the Horn")
                .and("c.CustomerID").geCol("o.CustomerID");
        assertEquals(expected4, sql4.getQuery());

        String expected5 = "SELECT o.OrderID, o.OrderDate, c.CustomerName FROM Customers AS c, Orders AS o WHERE c.CustomerName = 'Around the Horn' AND c.CustomerID <= o.CustomerID";
        BaseSQL sql5 = SQLBuilder.select("o.OrderID", "o.OrderDate", "c.CustomerName")
                .from("Customers").as("c")
                .from("Orders").as("o")
                .where("c.CustomerName").eq("Around the Horn")
                .and("c.CustomerID").leCol("o.CustomerID");
        assertEquals(expected5, sql5.getQuery());
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
