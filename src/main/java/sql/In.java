package sql;

import java.util.List;

public class In extends BaseSQL {
    public In(StringBuilder query, List<Object> params) {
        super(query, params);
    }
}
