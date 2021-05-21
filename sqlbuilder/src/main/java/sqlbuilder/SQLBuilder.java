package sqlbuilder;

import sqlbuilder.function.Function;

import java.util.ArrayList;
import java.util.List;

public class SQLBuilder {

	public static Select select(String... cols) {
		final StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<>();
		query.append("SELECT ");
		if (cols.length > 0) {
			String colsJoining = String.join(", ", cols);
			query.append(colsJoining);
		} else {
			query.append("*");
		}
		return new Select(query, params);
	}

	public static Select select(Function function) {
		final StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<>(function.getParams());
		query.append("SELECT ").append(function);
		return new Select(query, params);
	}

	public static Select selectDistinct(String... cols) {
		final StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<>();
		query.append("SELECT DISTINCT ");
		if (cols.length > 0) {
			String colsJoining = String.join(", ", cols);
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
			String colsJoining = String.join(", ", cols);
			query.append(" (").append(colsJoining).append(")");
		}
		return new InsertInto(query, params);
	}

	public static InsertInto insertInto(String table) {
		final StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<>();
		query.append("INSERT INTO ").append(table);
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

	public static InsertOrUpdateInto insertOrUpdateInto(String table) {
		final StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<>();
		query.append("MERGE INTO ").append(table).append(" AS TAB");
		return new InsertOrUpdateInto(query, params);
	}

}
