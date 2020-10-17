package sql;

import java.util.List;

public class Where extends AbstractCondition {
    public Where(StringBuilder query, List<Object> params) {
        super(query, params);
    }
}
