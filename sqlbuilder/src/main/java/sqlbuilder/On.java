package sqlbuilder;

import java.util.List;

public class On extends AbstractQuery {
	public On(StringBuilder query, List<Object> params) {
		super(query, params);
	}

	public AbstractOperator eqCol(String col) {
		query.append(" = ").append(col);
		return new AbstractOperator(query, params);
	}

	public AbstractOperator neCol(String col) {
		query.append(" <> ").append(col);
		return new AbstractOperator(query, params);
	}

	public AbstractOperator gtCol(String col) {
		query.append(" > ").append(col);
		return new AbstractOperator(query, params);
	}

	public AbstractOperator geCol(String col) {
		query.append(" >= ").append(col);
		return new AbstractOperator(query, params);
	}

	public AbstractOperator ltCol(String col) {
		query.append(" < ").append(col);
		return new AbstractOperator(query, params);
	}

	public AbstractOperator leCol(String col) {
		query.append(" <= ").append(col);
		return new AbstractOperator(query, params);
	}

}
