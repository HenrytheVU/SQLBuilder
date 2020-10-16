package sql;

import java.util.List;

public class IsNull extends AbstractQuery {
    public IsNull(StringBuilder query, List<Object> params) {
        super(query, params);
    }
}
