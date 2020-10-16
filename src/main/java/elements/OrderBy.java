package elements;

import java.util.List;

public class OrderBy extends AbstractSQLElement {
    public OrderBy(StringBuilder query, List<Object> params) {
        super(query, params);
    }

    public FetchNextRowsOnly fetchNexRowsOnly(int n) {
        query.append(" FETCH NEXT ").append(n).append(" ROWS ONLY");
        return new FetchNextRowsOnly(query, params);
    }
}
