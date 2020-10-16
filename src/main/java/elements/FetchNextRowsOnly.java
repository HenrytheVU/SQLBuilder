package elements;

import java.util.List;

public class FetchNextRowsOnly extends AbstractSQLElement {
    public FetchNextRowsOnly(StringBuilder query, List<Object> params) {
        super(query, params);
    }
}
