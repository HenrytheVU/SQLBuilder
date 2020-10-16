package elements;

import java.util.List;

public class Select extends AbstractSQLElement {

    public Select(StringBuilder query, List<Object> params) {
        super(query, params);
    }

    public From from(String table) {
        query.append(" FROM ").append(table);
        return new From(query, params);
    }
}
