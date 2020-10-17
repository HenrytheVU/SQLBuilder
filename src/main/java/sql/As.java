package sql;

import java.util.List;

public class As extends AbstractQuery {
    public As(StringBuilder query, List<Object> params) {
        super(query, params);
    }

    public From from(String table) {
        query.append(" FROM ").append(table);
        return new From(query, params);
    }

    public Where where(String col) {
        query.append(" WHERE ").append(col);
        return new Where(query, params);
    }

    public Join join(String table) {
        query.append(" JOIN ").append(table);
        return new Join(query, params);
    }

    public On on(String col) {
        query.append(" ON ").append(col);
        return new On(query, params);
    }

    public Comma comma(String col) {
        query.append(", ").append(col);
        return new Comma(query, params);
    }
}