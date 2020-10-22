package sqlbuilder;

import java.util.List;

public class Set extends AbstractQuery {
    public Set(StringBuilder query, List<Object> params) {
        super(query, params);
    }

    public Set set(final String col, final Object value) {
        query.append(", ").append(col).append(" = ?");
        params.add(value);
        return new Set(query, params);
    }

    public AbstractCondition where(final String col) {
        query.append(" WHERE ").append(col);
        return new AbstractCondition(query, params);
    }
}
