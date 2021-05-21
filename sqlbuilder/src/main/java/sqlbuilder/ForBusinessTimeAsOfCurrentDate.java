package sqlbuilder;

import java.util.List;

public class ForBusinessTimeAsOfCurrentDate extends AbstractQuery {
	public ForBusinessTimeAsOfCurrentDate(StringBuilder query, List<Object> params) {
		super(query, params);
	}

	public AbstractCondition where(String col) {
		query.append(" WHERE ").append(col);
		return new AbstractCondition(query, params);
	}
}
