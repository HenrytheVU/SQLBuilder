package elements;

import java.util.List;

public class GreaterThan extends AbstractSQLElement {
    public GreaterThan(StringBuilder query, List<Object> params) {
        super(query, params);
    }
}
