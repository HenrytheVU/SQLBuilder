package sql;

import java.util.List;

public class Between extends AbstractQuery {
    public Between(StringBuilder query, List<Object> params) {
        super(query, params);
    }

    public And and(String col) {
        query.append(" AND ").append(col);
        return new And(query, params);
    }

    public Or or(String col) {
        query.append(" OR ").append(col);
        return new Or(query, params);
    }
}
