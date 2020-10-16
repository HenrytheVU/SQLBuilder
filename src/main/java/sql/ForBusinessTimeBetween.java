package sql;

import java.time.LocalDate;
import java.util.List;

public class ForBusinessTimeBetween extends BaseSQL {
    public ForBusinessTimeBetween(StringBuilder query, List<Object> params) {
        super(query, params);
    }

    public And and(LocalDate endExcl) {
        query.append(" AND ?");
        params.add(endExcl.toString());
        return new And(query, params);
    }
}
