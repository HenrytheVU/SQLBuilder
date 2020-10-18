package sqlbuilder;

import java.util.Collections;
import java.util.List;

public class InsertInto extends AbstractQuery {
    public InsertInto(StringBuilder query, List<Object> params) {
        super(query, params);
    }

    public Values values(Object... values) {
        query.append(" VALUES (");
        for (int i = 0; i < values.length; i++) {
            if (i == values.length - 1) {
                query.append("?)");
            } else {
                query.append("?, ");
            }
        }
        Collections.addAll(params, values);
        return new Values(query, params);
    }
}
