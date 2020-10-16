package elements;

import java.util.List;

public class Where extends AbstractSQLElement {
    public Where(StringBuilder query, List<Object> params) {
        super(query, params);
    }

    public OrderBy orderBy(String col) {
        query.append(" ORDER BY ").append(col);
        return new OrderBy(query, params);
    }

}
