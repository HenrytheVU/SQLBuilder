package sqlbuilder;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class IntoCols extends AbstractQuery {
	public IntoCols(StringBuilder query, List<Object> params) {
		super(query, params);
	}

	public OnPrimaryKey onPrimaryKey(String primaryKey) {
		String queryStmt = query.toString();
		String colsCsv = String.join(", ", extractCols(queryStmt));
		String whenMatched = generateWhenMatched(queryStmt, primaryKey);
		String whenNotMatchedValues = generateWhenNotMatchedValues(queryStmt);

		query.append(" ON")
			.append(" TAB.").append(primaryKey).append(" = MRG.").append(primaryKey)
			.append(" WHEN MATCHED THEN UPDATE SET ").append(whenMatched)
			.append(" WHEN NOT MATCHED THEN INSERT (").append(colsCsv).append(")")
			.append(" VALUES (").append(whenNotMatchedValues).append(")");
		return new OnPrimaryKey(query, params);
	}

	private String generateWhenMatched(String stmt, String primaryKey) {
		return extractCols(stmt).stream()
			.filter(c -> !primaryKey.equals(c))
			.map(c -> "TAB." + c + " = MRG." + c)
			.collect(joining(", "));
	}

	private String generateWhenNotMatchedValues(String stmt) {
		return extractCols(stmt).stream().map(c -> "MRG." + c).collect(joining(", "));
	}

	private List<String> extractCols(String stmt) {
		String[] cols = stmt.split("AS MRG \\(")[1].replace(")", "").split(", ");
		return Arrays.asList(cols);
	}
}
