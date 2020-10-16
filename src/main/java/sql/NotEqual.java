package sql;

import java.util.List;

public class NotEqual extends AbstractQuery {
    public NotEqual(StringBuilder query, List<Object> params) {
        super(query, params);
    }

    public And and(String col) {
        query.append(" AND ").append(col);
        return new And(query, params);
    }
}
