package sql;

import java.util.List;

public class NotEqual extends AbstractOperator {
    public NotEqual(StringBuilder query, List<Object> params) {
        super(query, params);
    }
}
