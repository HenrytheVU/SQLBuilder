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
}
