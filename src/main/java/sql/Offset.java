package sql;

import java.util.List;

public class Offset extends BaseSQL {
    public Offset(StringBuilder query, List<Object> params) {
        super(query, params);
    }

    public FetchNextRowsOnly fetchNextRowsOnly(Integer n) {
        query.append(" FETCH NEXT ").append(n).append(" ROWS ONLY");
        return new FetchNextRowsOnly(query, params);
    }
}
