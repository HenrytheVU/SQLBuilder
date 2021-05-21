package sqlbuilder;

import java.util.List;

public class As extends AbstractQuery {
	public As(StringBuilder query, List<Object> params) {
		super(query, params);
	}

	public From from(String table) {
		query.append(" FROM ").append(table);
		return new From(query, params);
	}

	public AbstractCondition where(String col) {
		query.append(" WHERE ").append(col);
		return new AbstractCondition(query, params);
	}

	public AbstractJoin join(String table) {
		query.append(" JOIN ").append(table);
		return new AbstractJoin(query, params);
	}

	public AbstractJoin leftJoin(String table) {
		query.append(" LEFT JOIN ").append(table);
		return new AbstractJoin(query, params);
	}

	public AbstractJoin rightJoin(String table) {
		query.append(" RIGHT JOIN ").append(table);
		return new AbstractJoin(query, params);
	}

	public AbstractJoin fullJoin(String table) {
		query.append(" FULL JOIN ").append(table);
		return new AbstractJoin(query, params);
	}

	public On on(String col) {
		query.append(" ON ").append(col);
		return new On(query, params);
	}

	public Comma comma(String col) {
		query.append(", ").append(col);
		return new Comma(query, params);
	}

	public Using using(String... cols) {
		query.append(" USING (").append(String.join(", ", cols)).append(")");
		return new Using(query, params);
	}
}