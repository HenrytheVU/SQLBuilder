package sql;

import java.util.List;

public class ForBusinessTimeAsOf extends AbstractQuery {
    public ForBusinessTimeAsOf(StringBuilder query, List<Object> params) {
        super(query, params);
    }

    public Where where(String col) {
        query.append(" WHERE ").append(col);
        return new Where(query, params);
    }
}