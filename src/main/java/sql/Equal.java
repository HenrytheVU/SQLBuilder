package sql;

import java.util.List;

public class Equal extends AbstractQuery {
    public Equal(StringBuilder query, List<Object> params) {
        super(query, params);
    }

    public And and(String col) {
        query.append(" AND ").append(col);
        return new And(query, params);
    }

    public OrderBy orderBy(String col) {
        query.append(" ORDER BY ").append(col);
        return new OrderBy(query, params);
    }

    public Or or(String col) {
        query.append(" OR ").append(col);
        return new Or(query, params);
    }

}
