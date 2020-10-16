package sql;

import java.util.List;

public class Select extends AbstractQuery {

    public Select(StringBuilder query, List<Object> params) {
        super(query, params);
    }

    public From from(String table) {
        query.append(" FROM ").append(table);
        return new From(query, params);
    }

    public Count count() {
        query.append(", COUNT (*)");
        return new Count(query, params);
    }

    public Count count(String col) {
        query.append(", COUNT (").append(col).append(")");
        return new Count(query, params);
    }

}
