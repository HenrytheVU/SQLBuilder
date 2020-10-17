package sql;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class From extends AbstractQuery {
    public From(StringBuilder query, List<Object> params) {
        super(query, params);
    }

    public ForBusinessTimeAsOf forBusinessTimeAsOf(LocalDate startIncl) {
        query.append(" FOR BUSINESS_TIME AS OF ?");
        params.add(startIncl.toString());
        return new ForBusinessTimeAsOf(query, params);
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

    public Where where(String col) {
        query.append(" WHERE ").append(col);
        return new Where(query, params);
    }

    public Where where() {
        query.append(" WHERE");
        return new Where(query, params);
    }

    public Join join(String table) {
        query.append(" JOIN ").append(table);
        return new Join(query, params);
    }

    public Join join(String table, String alias) {
        query.append(" JOIN ").append(table).append(" ").append(alias);
        return new Join(query, params);
    }

    public LeftJoin leftJoin(String table) {
        query.append(" LEFT JOIN ").append(table);
        return new LeftJoin(query, params);
    }

    public LeftJoin leftJoin(String table, String alias) {
        query.append(" LEFT JOIN ").append(table).append(" ").append(alias);
        return new LeftJoin(query, params);
    }

    public RightJoin rightJoin(String table) {
        query.append(" RIGHT JOIN ").append(table);
        return new RightJoin(query, params);
    }

    public RightJoin rightJoin(String table, String alias) {
        query.append(" RIGHT JOIN ").append(table).append(" ").append(alias);
        return new RightJoin(query, params);
    }

    public FullJoin fullJoin(String table) {
        query.append(" FULL JOIN ").append(table);
        return new FullJoin(query, params);
    }

    public FullJoin fullJoin(String table, String alias) {
        query.append(" FULL JOIN ").append(table).append(" ").append(alias);
        return new FullJoin(query, params);
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
            String colsJoining = Stream.of(cols).collect(Collectors.joining(", "));
            query.append(" ORDER BY ").append(colsJoining);
        } else {
            query.append(" ORDER BY ").append(cols);
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

    public Comma comma(String table) {
        query.append(", ").append(table);
        return new Comma(query, params);
    }
}
