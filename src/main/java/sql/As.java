package sql;

import java.util.List;

public class As extends AbstractQuery {
    public As(StringBuilder query, List<Object> params) {
        super(query, params);
    }

    public From from(String table) {
        query.append(", ").append(table);
        return new From(query, params);
    }

    public Where where(String col) {
        query.append(" WHERE ").append(col);
        return new Where(query, params);
    }
}