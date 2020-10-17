package sql;

import com.sun.istack.internal.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class AbstractCondition extends AbstractQuery {
    public AbstractCondition(StringBuilder query, List<Object> params) {
        super(query, params);
    }

    public Equal equal(Object input) {
        query.append(" = ?");
        params.add(input);
        return new Equal(query, params);
    }

    public Equal eq(Object input) {
        query.append(" = ?");
        params.add(input);
        return new Equal(query, params);
    }

    public Equal eqAny(AbstractQuery subQuery) {
        query.append(" = ANY (").append(subQuery.getQueryWithPlaceHolders()).append(")");
        params.addAll(subQuery.getParams());
        return new Equal(query, params);
    }

    public Equal eqAll(AbstractQuery subQuery) {
        query.append(" = ALL (").append(subQuery.getQueryWithPlaceHolders()).append(")");
        params.addAll(subQuery.getParams());
        return new Equal(query, params);
    }

    public NotEqual notEqual(Object input) {
        query.append(" <> ?");
        params.add(input);
        return new NotEqual(query, params);
    }

    public NotEqual ne(Object input) {
        query.append(" <> ?");
        params.add(input);
        return new NotEqual(query, params);
    }

    public GreaterThan greaterThan(Object input) {
        query.append(" > ?");
        params.add(input);
        return new GreaterThan(query, params);
    }

    public GreaterThan gt(Object input) {
        query.append(" > ?");
        params.add(input);
        return new GreaterThan(query, params);
    }

    public GreaterThan greaterOrEqual(Object input) {
        query.append(" >= ?");
        params.add(input);
        return new GreaterThan(query, params);
    }

    public GreaterThan ge(Object input) {
        query.append(" >= ?");
        params.add(input);
        return new GreaterThan(query, params);
    }

    public LessThan lessThan(Object input) {
        query.append(" < ?");
        params.add(input);
        return new LessThan(query, params);
    }

    public LessThan lt(Object input) {
        query.append(" < ?");
        params.add(input);
        return new LessThan(query, params);
    }

    public LessThan lessOrEqual(Object input) {
        query.append(" <= ?");
        params.add(input);
        return new LessThan(query, params);
    }

    public LessThan le(Object input) {
        query.append(" <= ?");
        params.add(input);
        return new LessThan(query, params);
    }

    public IsNull isNull() {
        query.append(" IS NULL");
        return new IsNull(query, params);
    }

    public IsNotNull isNotNull() {
        query.append(" IS NOT NULL");
        return new IsNotNull(query, params);
    }

    public In in(@NotNull final Object... filters) {
        query.append(" IN (");
        for (int i = 0; i < filters.length; i++) {
            if (i == filters.length - 1) {
                query.append("?)");
            } else {
                query.append("?, ");
            }
        }
        Collections.addAll(params, filters);
        return new In(query, params);
    }

    public In in(@NotNull final List<Object> filters) {
        query.append(" IN (");
        for (int i = 0; i < filters.size(); i++) {
            if (i == filters.size() - 1) {
                query.append("?)");
            } else {
                query.append("?, ");
            }
        }
        params.addAll(filters);
        return new In(query, params);
    }

    public In notIn(@NotNull final Object... filters) {
        query.append(" NOT IN (");
        for (int i = 0; i < filters.length; i++) {
            if (i == filters.length - 1) {
                query.append("?)");
            } else {
                query.append("?, ");
            }
        }
        Collections.addAll(params, filters);
        return new In(query, params);
    }

    public In notIn(@NotNull final List<Object> filters) {
        query.append(" NOT IN (");
        for (int i = 0; i < filters.size(); i++) {
            if (i == filters.size() - 1) {
                query.append("?)");
            } else {
                query.append("?, ");
            }
        }
        params.addAll(filters);
        return new In(query, params);
    }

    public In in(AbstractQuery subQuery) {
        query.append(" IN (").append(subQuery.query).append(")");
        return new In(query, params);
    }

    public Like like(Object pattern) {
        query.append(" LIKE ?");
        params.add(pattern);
        return new Like(query, params);
    }

    public Like startsWith(Object pattern) {
        query.append(" LIKE ?");
        params.add(pattern + "%");
        return new Like(query, params);
    }

    public Like endsWith(Object pattern) {
        query.append(" LIKE ?");
        params.add("%" + pattern);
        return new Like(query, params);
    }

    public Like contains(Object pattern) {
        query.append(" LIKE ?");
        params.add("%" + pattern + "%");
        return new Like(query, params);
    }

    public Between between(Object value1, Object value2) {
        query.append(" BETWEEN ? AND ?");
        params.addAll(Arrays.asList(value1, value2));
        return new Between(query, params);
    }

    public Union union() {
        query.append(" UNION ");
        return new Union(query, params);
    }

    public UnionAll unionAll() {
        query.append(" UNION ALL ");
        return new UnionAll(query, params);
    }

    public OrderBy orderBy(String col) {
        query.append(" ORDER BY ").append(col);
        return new OrderBy(query, params);
    }

    public Equal eqCol(String col) {
        query.append(" = ").append(col);
        return new Equal(query, params);
    }

    public Equal neCol(String col) {
        query.append(" <> ").append(col);
        return new Equal(query, params);
    }

    public Equal gtCol(String col) {
        query.append(" > ").append(col);
        return new Equal(query, params);
    }

    public Equal geCol(String col) {
        query.append(" >= ").append(col);
        return new Equal(query, params);
    }

    public Equal ltCol(String col) {
        query.append(" < ").append(col);
        return new Equal(query, params);
    }

    public Equal leCol(String col) {
        query.append(" <= ").append(col);
        return new Equal(query, params);
    }

    public Not not() {
        query.append(" NOT");
        return new Not(query, params);
    }

    public Exists exists(AbstractQuery subQuery) {
        query.append(" EXISTS (").append(subQuery.getQueryWithPlaceHolders()).append(")");
        params.addAll(subQuery.getParams());
        return new Exists(query, params);
    }
}
