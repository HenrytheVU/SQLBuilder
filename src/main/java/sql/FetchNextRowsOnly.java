package sql;

import java.util.List;

public class FetchNextRowsOnly extends BaseSQL {
    public FetchNextRowsOnly(StringBuilder query, List<Object> params) {
        super(query, params);
    }
}
