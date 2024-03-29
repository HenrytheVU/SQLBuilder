package sqlbuilder;

import sqlbuilder.function.Function;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public class From extends AbstractQuery {
	public From(StringBuilder query, List<Object> params) {
		super(query, params);
	}

	public From forBusinessTimeAsOf(LocalDate startIncl) {
		query.append(" FOR BUSINESS_TIME AS OF ?");
		params.add(startIncl.toString());
		return this;
	}

	public From forBusinessTimeAsOfCurrentDate() {
		query.append(" FOR BUSINESS_TIME AS OF CURRENT DATE");
		return this;
	}

	public ForBusinessTimeFrom forBusinessTimeFrom(LocalDate startIncl) {
		query.append(" FOR BUSINESS_TIME FROM ?");
		params.add(startIncl.toString());
		return new ForBusinessTimeFrom(query, params);
	}

	public ForPortionOfBusinessTimeFrom forPortionOfBusinessTimeFrom(LocalDate startIncl) {
		query.append(" FOR PORTION OF BUSINESS_TIME FROM ?");
		params.add(startIncl.toString());
		return new ForPortionOfBusinessTimeFrom(query, params);
	}

	public AbstractCondition where(String... cols) {
		query.append(" WHERE ").append(String.join("||", cols));
		return new AbstractCondition(query, params);
	}

	public AbstractCondition where(Function function) {
		params.addAll(function.getParams());
		query.append(" WHERE ").append(function);
		return new AbstractCondition(query, params);
	}

	public AbstractCondition where() {
		query.append(" WHERE");
		return new AbstractCondition(query, params);
	}

	public AbstractJoin join(String table) {
		query.append(" JOIN ").append(table);
		return new AbstractJoin(query, params);
	}

	public AbstractJoin join(String table, String alias) {
		query.append(" JOIN ").append(table).append(" ").append(alias);
		return new AbstractJoin(query, params);
	}

	public AbstractJoin leftJoin(String table) {
		query.append(" LEFT JOIN ").append(table);
		return new AbstractJoin(query, params);
	}

	public AbstractJoin leftJoin(String table, String alias) {
		query.append(" LEFT JOIN ").append(table).append(" ").append(alias);
		return new AbstractJoin(query, params);
	}

	public AbstractJoin rightJoin(String table) {
		query.append(" RIGHT JOIN ").append(table);
		return new AbstractJoin(query, params);
	}

	public AbstractJoin rightJoin(String table, String alias) {
		query.append(" RIGHT JOIN ").append(table).append(" ").append(alias);
		return new AbstractJoin(query, params);
	}

	public AbstractJoin fullJoin(String table) {
		query.append(" FULL JOIN ").append(table);
		return new AbstractJoin(query, params);
	}

	public AbstractJoin fullJoin(String table, String alias) {
		query.append(" FULL JOIN ").append(table).append(" ").append(alias);
		return new AbstractJoin(query, params);
	}

	public Union union() {
		query.append(" UNION");
		return new Union(query, params);
	}

	public UnionAll unionAll() {
		query.append(" UNION ALL");
		return new UnionAll(query, params);
	}

	public OrderBy orderBy(String... cols) {
		if (cols.length > 0) {
			String colsJoining = Stream.of(cols).collect(joining(", "));
			query.append(" ORDER BY ").append(colsJoining);
		} else {
			query.append(" ORDER BY");
		}
		return new OrderBy(query, params);
	}

	public GroupBy groupBy(String col) {
		query.append(" GROUP BY ").append(col);
		return new GroupBy(query, params);
	}

	public As as(String alias) {
		query.append(" AS ").append(alias);
		return new As(query, params);
	}

	public Limit limit(int val) {
		query.append(" LIMIT ?");
		params.add(val);
		return new Limit(query, params);
	}

	public Comma comma(String table) {
		query.append(", ").append(table);
		return new Comma(query, params);
	}
}
