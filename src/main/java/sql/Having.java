package sql;

import java.util.List;

public class Having extends AbstractCondition {
    public Having(StringBuilder query, List<Object> params) {
        super(query, params);
    }
}
