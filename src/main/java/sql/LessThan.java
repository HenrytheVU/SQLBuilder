package sql;

import java.util.List;

public class LessThan extends AbstractQuery {
    public LessThan(StringBuilder query, List<Object> params) {
        super(query, params);
    }

    public And and(String col) {
        query.append(" AND ").append(col);
        return new And(query, params);
    }
}
