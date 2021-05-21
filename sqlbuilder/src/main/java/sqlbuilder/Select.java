package sqlbuilder;

import java.util.List;

public class Select extends AbstractQuery {
	public Select(StringBuilder query, List<Object> params) {
		super(query, params);
	}

	public From from(String table) {
		query.append(" FROM ").append(table);
		return new From(query, params);
	}

	public From from(String table, String alias) {
		query.append(" FROM ").append(table).append(" ").append(alias);
		return new From(query, params);
	}

	public Count count() {
		query.append(", COUNT (*)");
		return new Count(query, params);
	}

	public Count count(String col) {
		query.append(", COUNT (").append(col).append(")");
		return new Count(query, params);
	}

	public As as(String alias) {
		query.append(" AS ").append(alias);
		return new As(query, params);
	}

	public Comma comma(String col) {
		query.append(", ").append(col);
		return new Comma(query, params);
	}

	public Comma comma() {
		query.append(",");
		return new Comma(query, params);
	}

}
