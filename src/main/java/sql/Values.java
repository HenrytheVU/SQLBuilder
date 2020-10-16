package sql;

import java.util.List;

public class Values extends AbstractQuery {
    public Values(StringBuilder query, List<Object> params) {
        super(query, params);
    }
}
