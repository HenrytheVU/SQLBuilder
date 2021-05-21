package sqlbuilder;

import java.time.LocalDate;
import java.util.List;

public class Set extends AbstractQuery {
	public Set(StringBuilder query, List<Object> params) {
		super(query, params);
	}

	public Set set(String col, Object value) {
		query.append(", ").append(col).append(" = ?");
		if (value instanceof LocalDate) {
			params.add(value.toString());
		} else {
			params.add(value);
		}
		return new Set(query, params);
	}

	public AbstractCondition where(String col) {
		query.append(" WHERE ").append(col);
		return new AbstractCondition(query, params);
	}
}
