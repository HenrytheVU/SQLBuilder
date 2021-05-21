package sqlbuilder;

import sqlbuilder.function.Function;

import java.util.List;

public class AbstractOperator extends AbstractQuery {
	public AbstractOperator(StringBuilder query, List<Object> params) {
		super(query, params);
	}

	public AbstractCondition and(String... col) {
		query.append(" AND ").append(String.join("||", col));
		return new AbstractCondition(query, params);
	}

	public AbstractCondition and(Function function) {
		query.append(" AND ").append(function);
		params.addAll(function.getParams());
		return new AbstractCondition(query, params);
	}

	public AbstractCondition or(String... col) {
		query.append(" OR ").append(String.join("||", col));
		return new AbstractCondition(query, params);
	}

	public AbstractCondition or(Function function) {
		query.append(" OR ").append(function);
		params.addAll(function.getParams());
		return new AbstractCondition(query, params);
	}

	public From noop() {
		return new From(query, params);
	}
}
