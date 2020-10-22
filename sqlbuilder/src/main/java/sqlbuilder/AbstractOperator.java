package sqlbuilder;

import java.util.List;

public class AbstractOperator extends AbstractQuery {
    public AbstractOperator(StringBuilder query, List<Object> params) {
        super(query, params);
    }

    public AbstractCondition and(String col) {
        query.append(" AND ").append(col);
        return new AbstractCondition(query, params);
    }

    public AbstractCondition or(String col) {
        query.append(" OR ").append(col);
        return new AbstractCondition(query, params);
    }

    public OrderBy orderBy(String col) {
        query.append(" ORDER BY ").append(col);
        return new OrderBy(query, params);
    }

    public OrderBy orderByCount(String col) {
        query.append(" ORDER BY COUNT (").append(col).append(")");
        return new OrderBy(query, params);
    }

    public OrderBy orderBy() {
        query.append(" ORDER BY");
        return new OrderBy(query, params);
    }

    public Union union() {
        query.append(" UNION");
        return new Union(query, params);
    }

    public UnionAll unionAll() {
        query.append(" UNION ALL");
        return new UnionAll(query, params);
    }

    public GroupBy groupBy(String col) {
        query.append(" GROUP BY ").append(col);
        return new GroupBy(query, params);
    }

    public Any any(AbstractQuery subSelect) {
        query.append(" ANY (").append(subSelect.getQueryWithPlaceHolders()).append(")");
        params.addAll(subSelect.getParams());
        return new Any(query, params);
    }
}
