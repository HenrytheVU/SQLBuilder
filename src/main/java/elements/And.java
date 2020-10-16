package elements;

import java.util.List;

public class And extends AbstractSQLElement {
    public And(StringBuilder query, List<Object> params) {
        super(query, params);
    }
}
