package sqlbuilder;

import java.util.List;

public class To extends From {
	public To(StringBuilder query, List<Object> params) {
		super(query, params);
	}

	public Set set(final String col, final Object value) {
		query.append(" SET ").append(col).append(" = ?");
		params.add(value);
		return new Set(query, params);
	}
}
