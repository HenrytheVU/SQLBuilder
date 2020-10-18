package sqlbuilder;

import com.sun.istack.internal.NotNull;

import java.util.List;

public class To extends AbstractQuery {
    public To(StringBuilder query, List<Object> params) {
        super(query, params);
    }

    public AbstractCondition where(String col) {
        query.append(" WHERE ").append(col);
        return new AbstractCondition(query, params);
    }

    public Set set(@NotNull final String col, final Object value) {
        query.append(" SET ").append(col).append(" = ?");
        params.add(value);
        return new Set(query, params);
    }
}
