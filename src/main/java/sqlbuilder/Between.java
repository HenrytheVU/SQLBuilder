package sqlbuilder;

import java.util.List;

public class Between extends AbstractQuery {
    public Between(StringBuilder query, List<Object> params) {
        super(query, params);
    }

    public AbstractCondition and(String col) {
        query.append(" AND ").append(col);
        return new AbstractCondition(query, params);
    }

    public AbstractCondition or(String col) {
        query.append(" OR ").append(col);
        return new AbstractCondition(query, params);
    }
}
