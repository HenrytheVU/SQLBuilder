package sql;

import com.sun.istack.internal.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Or extends BaseSQL {
    public Or(StringBuilder query, List<Object> params) {
        super(query, params);
    }

    public Equal equal(String input) {
        query.append(" = ?");
        params.add(input);
        return new Equal(query, params);
    }

    public Equal eq(String input) {
        query.append(" = ?");
        params.add(input);
        return new Equal(query, params);
    }

    public Equal equal(Integer input) {
        query.append(" = ?");
        params.add(input);
        return new Equal(query, params);
    }

    public Equal eq(Integer input) {
        query.append(" = ?");
        params.add(input);
        return new Equal(query, params);
    }

    public NotEqual notEqual(String input) {
        query.append(" <> ?");
        params.add(input);
        return new NotEqual(query, params);
    }

    public NotEqual ne(String input) {
        query.append(" <> ?");
        params.add(input);
        return new NotEqual(query, params);
    }

    public NotEqual notEqual(Integer input) {
        query.append(" <> ?");
        params.add(input);
        return new NotEqual(query, params);
    }

    public NotEqual ne(Integer input) {
        query.append(" <> ?");
        params.add(input);
        return new NotEqual(query, params);
    }

    public GreaterThan greaterThan(String input) {
        query.append(" > ?");
        params.add(input);
        return new GreaterThan(query, params);
    }

    public GreaterThan gt(String input) {
        query.append(" > ?");
        params.add(input);
        return new GreaterThan(query, params);
    }

    public GreaterThan greaterThan(Integer input) {
        query.append(" > ?");
        params.add(input);
        return new GreaterThan(query, params);
    }

    public GreaterThan gt(Integer input) {
        query.append(" > ?");
        params.add(input);
        return new GreaterThan(query, params);
    }

    public GreaterThan greaterOrEqual(String input) {
        query.append(" >= ?");
        params.add(input);
        return new GreaterThan(query, params);
    }

    public GreaterThan ge(String input) {
        query.append(" >= ?");
        params.add(input);
        return new GreaterThan(query, params);
    }

    public GreaterThan greaterOrEqual(Integer input) {
        query.append(" >= ?");
        params.add(input);
        return new GreaterThan(query, params);
    }

    public GreaterThan ge(Integer input) {
        query.append(" >= ?");
        params.add(input);
        return new GreaterThan(query, params);
    }

    public LessThan lessThan(String input) {
        query.append(" < ?");
        params.add(input);
        return new LessThan(query, params);
    }

    public LessThan lt(String input) {
        query.append(" < ?");
        params.add(input);
        return new LessThan(query, params);
    }

    public LessThan lessThan(Integer input) {
        query.append(" < ?");
        params.add(input);
        return new LessThan(query, params);
    }

    public LessThan lt(Integer input) {
        query.append(" < ?");
        params.add(input);
        return new LessThan(query, params);
    }

    public LessThan lessOrEqual(String input) {
        query.append(" <= ?");
        params.add(input);
        return new LessThan(query, params);
    }

    public LessThan le(String input) {
        query.append(" <= ?");
        params.add(input);
        return new LessThan(query, params);
    }

    public LessThan lessOrEqual(Integer input) {
        query.append(" <= ?");
        params.add(input);
        return new LessThan(query, params);
    }

    public LessThan le(Integer input) {
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

    public In in(@NotNull final String... filters) {
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

    public In in(@NotNull final List<String> filters) {
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

    public Like like(String pattern) {
        query.append(" LIKE ?");
        params.add(pattern);
        return new Like(query, params);
    }

    public Like startsWith(String pattern) {
        query.append(" LIKE ?");
        params.add(pattern + "%");
        return new Like(query, params);
    }

    public Like endsWith(String pattern) {
        query.append(" LIKE ?");
        params.add("%" + pattern);
        return new Like(query, params);
    }

    public Like contains(String pattern) {
        query.append(" LIKE ?");
        params.add("%" + pattern + "%");
        return new Like(query, params);
    }

    public Between between(Object value1, Object value2) {
        query.append(" BETWEEN ? AND ?");
        params.addAll(Arrays.asList(value1, value2));
        return new Between(query, params);
    }
}
