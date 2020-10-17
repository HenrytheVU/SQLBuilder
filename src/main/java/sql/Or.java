package sql;

import java.util.List;

public class Or extends AbstractCondition {
    public Or(StringBuilder query, List<Object> params) {
        super(query, params);
    }

}
