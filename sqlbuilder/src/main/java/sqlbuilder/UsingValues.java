package sqlbuilder;

import java.util.List;

public class UsingValues extends AbstractQuery {
	public UsingValues(StringBuilder query, List<Object> params) {
		super(query, params);
	}

	public IntoCols intoCols(String... cols) {
		query.append(" (").append(String.join(", ", cols)).append(")");
		return new IntoCols(query, params);
	}
}
