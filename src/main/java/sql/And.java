package sql;

import java.util.List;

public class And extends AbstractCondition {
    public And(StringBuilder query, List<Object> params) {
        super(query, params);
    }
}
