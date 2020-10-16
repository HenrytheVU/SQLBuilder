package sql;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class From extends BaseSQL {
    public From(StringBuilder query, List<Object> params) {
        super(query, params);
    }

    public ForBusinessTimeAsOf forBusinessTimeAsOf(String startIncl) {
        query.append(" FOR BUSINESS_TIME AS OF ?");
        params.add(startIncl);
        return new ForBusinessTimeAsOf(query, params);
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

    public ForPortionOfBusinessTimeFrom forPortionOfBusinessTimeFrom(String startIncl) {
        query.append(" FOR PORTION OF BUSINESS_TIME FROM ?");
        params.add(startIncl);
        return new ForPortionOfBusinessTimeFrom(query, params);
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

    public Join join(String table) {
        query.append(" JOIN ").append(table);
        return new Join(query, params);
    }

    public LeftJoin leftJoin(String table) {
        query.append(" LEFT JOIN ").append(table);
        return new LeftJoin(query, params);
    }

    public RightJoin rightJoin(String table) {
        query.append(" RIGHT JOIN ").append(table);
        return new RightJoin(query, params);
    }

    public Union union() {
        query.append(" UNION ");
        return new Union(query, params);
    }

    public UnionAll unionAll() {
        query.append(" UNION ALL ");
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
}
