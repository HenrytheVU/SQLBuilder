package sqlbuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SQLBuilder {

    public static Select select(String... cols) {
        final StringBuilder query = new StringBuilder();
        final List<Object> params = new ArrayList<>();
        query.append("SELECT ");
        if (cols.length > 0) {
            String colsJoining = Stream.of(cols).collect(Collectors.joining(", "));
            query.append(colsJoining);
        } else {
            query.append("*");
        }
        return new Select(query, params);
    }

    public static Select selectDistinct(String... cols) {
        final StringBuilder query = new StringBuilder();
        final List<Object> params = new ArrayList<>();
        query.append("SELECT DISTINCT ");
        if (cols.length > 0) {
            String colsJoining = Stream.of(cols).collect(Collectors.joining(", "));
            query.append(colsJoining);
        } else {
            query.append("*");
        }
        return new Select(query, params);
    }

    public static Select selectCount() {
        final StringBuilder query = new StringBuilder();
        final List<Object> params = new ArrayList<>();
        query.append("SELECT COUNT (*)");
        return new Select(query, params);
    }

    public static Select selectCount(String col) {
        final StringBuilder query = new StringBuilder();
        final List<Object> params = new ArrayList<>();
        query.append("SELECT COUNT (").append(col).append(")");
        return new Select(query, params);
    }

    public static Select selectAvg(String col) {
        final StringBuilder query = new StringBuilder();
        final List<Object> params = new ArrayList<>();
        query.append("SELECT AVG (").append(col).append(")");
        return new Select(query, params);
    }

    public static Select selectSum(String col) {
        final StringBuilder query = new StringBuilder();
        final List<Object> params = new ArrayList<>();
        query.append("SELECT SUM (").append(col).append(")");
        return new Select(query, params);
    }

    public static Select selectCountDistinct(String col) {
        final StringBuilder query = new StringBuilder();
        final List<Object> params = new ArrayList<>();
        query.append("SELECT COUNT (DISTINCT ").append(col).append(")");
        return new Select(query, params);
    }

    public static InsertInto insertInto(String table, String... cols) {
        final StringBuilder query = new StringBuilder();
        final List<Object> params = new ArrayList<>();
        query.append("INSERT INTO ").append(table);
        if (cols.length > 0) {
            String colsJoining = Stream.of(cols).collect(Collectors.joining(", "));
            query.append(" (").append(colsJoining).append(")");
        }
        return new InsertInto(query, params);
    }

    public static DeleteFrom deleteFrom(String table) {
        final StringBuilder query = new StringBuilder();
        final List<Object> params = new ArrayList<>();
        query.append("DELETE FROM ").append(table);
        return new DeleteFrom(query, params);
    }

    public static Update update(String table) {
        final StringBuilder query = new StringBuilder();
        final List<Object> params = new ArrayList<>();
        query.append("UPDATE ").append(table);
        return new Update(query, params);
    }

}
