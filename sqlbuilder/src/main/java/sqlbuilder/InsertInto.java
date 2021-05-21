package sqlbuilder;

import sqlbuilder.exception.BadSQLSyntaxException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InsertInto extends AbstractQuery {
	public InsertInto(StringBuilder query, List<Object> params) {
		super(query, params);
	}

	public Values values(Object... values) {
		if (values.length == 0) {
			return valuesAsPlaceHolders();
		}

		query.append(" VALUES (");
		query.append(Stream.of(values).map(v -> "?").collect(Collectors.joining(", ")));
		query.append(")");

		final List<Object> sanitizedValues = Stream.of(values).map(v -> {
			if (v instanceof LocalDate) {
				v = v.toString();
			}
			return v;
		}).collect(Collectors.toList());

		params.addAll(sanitizedValues);
		return new Values(query, params);
	}

	public Values valuesWithNumberOfPlaceHolders(int noPlaceHolders) {
		if (noPlaceHolders <= 0) {
			throw new BadSQLSyntaxException("Number of placeholders must be > 0!");
		}
		query.append(" VALUES (");
		for (int i = 1; i <= noPlaceHolders; i++) {
			if (i == noPlaceHolders) {
				query.append("?)");
			} else {
				query.append("?, ");
			}
		}
		return new Values(query, params);
	}

	public Values valuesAsPlaceHolders() {
		query.append(" VALUES (");
		int noCols = getNoColsToBeInserted(query.toString());

		for (int i = 0; i < noCols; i++) {
			if (i == noCols - 1) {
				query.append("?)");
			} else {
				query.append("?, ");
			}
		}
		return new Values(query, params);
	}

	public InsertInto columns(String... cols) {
		String colsJoining = String.join(", ", cols);
		query.append(" (").append(colsJoining).append(")");
		return new InsertInto(query, params);
	}

	private int getNoColsToBeInserted(String insertIntoQuery) {
		if (!insertIntoQuery.contains(")")) {
			throw new BadSQLSyntaxException(
				"Number of columns must be > 0! Please specify at least a column name after INSERT INTO table_name (column1, column2...)");
		}
		String colsStrippedLeft = insertIntoQuery.split("\\(")[1];
		String colsStripped = colsStrippedLeft.split("\\)")[0];
		return colsStripped.split(", ").length;
	}
}
