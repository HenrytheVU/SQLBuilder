package sql;

import java.util.List;

public class NotIn extends BaseSQL {
    public NotIn(StringBuilder query, List<Object> params) {
        super(query, params);
    }
}
