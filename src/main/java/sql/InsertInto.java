package sql;

import java.util.Collections;
import java.util.List;

public class InsertInto extends AbstractQuery {
    public InsertInto(StringBuilder query, List<Object> params) {
        super(query, params);
    }

    public Values values(Object... input) {
        query.append(" VALUES (");
        for (int i = 0; i < input.length; i++) {
            if (i == input.length - 1) {
                query.append("?)");
            } else {
                query.append("?, ");
            }
        }
        Collections.addAll(params, input);
        return new Values(query, params);
    }
}
