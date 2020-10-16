package sql;

import java.util.List;

public class GreaterThan extends AbstractQuery {
    public GreaterThan(StringBuilder query, List<Object> params) {
        super(query, params);
    }

    public And and(String col) {
        query.append(" AND ").append(col);
        return new And(query, params);
    }
}
