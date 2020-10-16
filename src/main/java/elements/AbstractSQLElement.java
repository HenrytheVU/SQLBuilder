package elements;

import java.util.List;

public abstract class AbstractSQLElement {

    final StringBuilder query;
    final List<Object> params;

    public AbstractSQLElement(StringBuilder query, List<Object> params) {
        this.query = query;
        this.params = params;
    }

    public List<Object> getParams() {
        return params;
    }

    public String getQuery() {
        return query.toString();
    }

    public String getQueryWithPlaceHolders() {
        return query.toString();
    }
}
