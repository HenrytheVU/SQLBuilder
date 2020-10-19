package sqlbuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AbstractCondition extends AbstractQuery {
    public AbstractCondition(StringBuilder query, List<Object> params) {
        super(query, params);
    }

    public AbstractOperator equal(Object value) {
        query.append(" = ?");
        params.add(value);
        return new AbstractOperator(query, params);
    }

    public AbstractOperator eq(Object value) {
        query.append(" = ?");
        params.add(value);
        return new AbstractOperator(query, params);
    }

    public AbstractOperator eqAny(AbstractQuery subQuery) {
        query.append(" = ANY (").append(subQuery).append(")");
        params.addAll(subQuery.getParams());
        return new AbstractOperator(query, params);
    }

    public AbstractOperator eqAll(AbstractQuery subQuery) {
        query.append(" = ALL (").append(subQuery).append(")");
        params.addAll(subQuery.getParams());
        return new AbstractOperator(query, params);
    }

    public AbstractOperator notEqual(Object value) {
        query.append(" <> ?");
        params.add(value);
        return new AbstractOperator(query, params);
    }

    public AbstractOperator ne(Object value) {
        query.append(" <> ?");
        params.add(value);
        return new AbstractOperator(query, params);
    }

    public AbstractOperator greaterThan(Object value) {
        query.append(" > ?");
        params.add(value);
        return new AbstractOperator(query, params);
    }

    public AbstractOperator gt(Object value) {
        query.append(" > ?");
        params.add(value);
        return new AbstractOperator(query, params);
    }

    public AbstractOperator greaterOrEqual(Object value) {
        query.append(" >= ?");
        params.add(value);
        return new AbstractOperator(query, params);
    }

    public AbstractOperator ge(Object value) {
        query.append(" >= ?");
        params.add(value);
        return new AbstractOperator(query, params);
    }

    public AbstractOperator lessThan(Object value) {
        query.append(" < ?");
        params.add(value);
        return new AbstractOperator(query, params);
    }

    public AbstractOperator lt(Object value) {
        query.append(" < ?");
        params.add(value);
        return new AbstractOperator(query, params);
    }

    public AbstractOperator lessOrEqual(Object value) {
        query.append(" <= ?");
        params.add(value);
        return new AbstractOperator(query, params);
    }

    public AbstractOperator le(Object value) {
        query.append(" <= ?");
        params.add(value);
        return new AbstractOperator(query, params);
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

    public AbstractOperator isNull() {
        query.append(" IS NULL");
        return new AbstractOperator(query, params);
    }

    public AbstractOperator isNotNull() {
        query.append(" IS NOT NULL");
        return new AbstractOperator(query, params);
    }

    public AbstractOperator in(final Object... filters) {
        query.append(" IN (");
        for (int i = 0; i < filters.length; i++) {
            if (i == filters.length - 1) {
                query.append("?)");
            } else {
                query.append("?, ");
            }
        }
        Collections.addAll(params, filters);
        return new AbstractOperator(query, params);
    }

    public AbstractOperator in(final List<Object> filters) {
        query.append(" IN (");
        for (int i = 0; i < filters.size(); i++) {
            if (i == filters.size() - 1) {
                query.append("?)");
            } else {
                query.append("?, ");
            }
        }
        params.addAll(filters);
        return new AbstractOperator(query, params);
    }

    public AbstractOperator notIn(final Object... filters) {
        query.append(" NOT IN (");
        for (int i = 0; i < filters.length; i++) {
            if (i == filters.length - 1) {
                query.append("?)");
            } else {
                query.append("?, ");
            }
        }
        Collections.addAll(params, filters);
        return new AbstractOperator(query, params);
    }

    public AbstractOperator notIn(final List<Object> filters) {
        query.append(" NOT IN (");
        for (int i = 0; i < filters.size(); i++) {
            if (i == filters.size() - 1) {
                query.append("?)");
            } else {
                query.append("?, ");
            }
        }
        params.addAll(filters);
        return new AbstractOperator(query, params);
    }

    public AbstractOperator in(AbstractQuery subQuery) {
        query.append(" IN (").append(subQuery.query).append(")");
        return new AbstractOperator(query, params);
    }

    public AbstractOperator like(Object pattern) {
        query.append(" LIKE ?");
        params.add(pattern);
        return new AbstractOperator(query, params);
    }

    public AbstractOperator startsWith(Object pattern) {
        query.append(" LIKE ?");
        params.add(pattern + "%");
        return new AbstractOperator(query, params);
    }

    public AbstractOperator endsWith(Object pattern) {
        query.append(" LIKE ?");
        params.add("%" + pattern);
        return new AbstractOperator(query, params);
    }

    public AbstractOperator contains(Object pattern) {
        query.append(" LIKE ?");
        params.add("%" + pattern + "%");
        return new AbstractOperator(query, params);
    }

    public AbstractOperator between(Object value1, Object value2) {
        query.append(" BETWEEN ? AND ?");
        params.addAll(Arrays.asList(value1, value2));
        return new AbstractOperator(query, params);
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

    public Exists exists(AbstractQuery subQuery) {
        query.append(" EXISTS (").append(subQuery).append(")");
        params.addAll(subQuery.getParams());
        return new Exists(query, params);
    }

    public Exists notExists(AbstractQuery subQuery) {
        query.append(" NOT EXISTS (").append(subQuery).append(")");
        params.addAll(subQuery.getParams());
        return new Exists(query, params);
    }
}
