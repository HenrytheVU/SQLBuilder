package sql;

import java.util.List;

public class GroupBy extends AbstractQuery {
    public GroupBy(StringBuilder query, List<Object> params) {
        super(query, params);
    }

    public OrderBy orderByCount(String col) {
        query.append(" ORDER BY COUNT (").append(col).append(")");
        return new OrderBy(query, params);
    }

    public Having havingCount(String col) {
        query.append(" HAVING COUNT (").append(col).append(")");
        return new Having(query, params);
    }

}
