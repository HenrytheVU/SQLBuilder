package sql;

import com.sun.istack.internal.NotNull;

import java.util.List;

public class Set extends AbstractQuery {
    public Set(StringBuilder query, List<Object> params) {
        super(query, params);
    }

    public Set set(@NotNull final String col, final Object value) {
        query.append(", ").append(col).append(" = ?");
        params.add(value);
        return new Set(query, params);
    }

    public Where where(@NotNull final String col) {
        query.append(" WHERE ").append(col);
        return new Where(query, params);
    }
}
