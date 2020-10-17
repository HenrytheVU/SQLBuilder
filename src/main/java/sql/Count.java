package sql;

import java.util.List;

public class Count extends AbstractQuery {
    public Count(StringBuilder query, List<Object> params) {
        super(query, params);
    }

    public From from(String table) {
        query.append(" FROM ").append(table);
        return new From(query, params);
    }

    public As as(String alias) {
        query.append(" AS ").append(alias);
        return new As(query, params);
    }

    public Asc asc() {
        query.append(" ASC");
        return new Asc(query, params);
    }

    public Desc desc() {
        query.append(" DESC");
        return new Desc(query, params);
    }
}
