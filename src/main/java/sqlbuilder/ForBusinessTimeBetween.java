package sqlbuilder;

import java.time.LocalDate;
import java.util.List;

public class ForBusinessTimeBetween extends AbstractQuery {
    public ForBusinessTimeBetween(StringBuilder query, List<Object> params) {
        super(query, params);
    }

    public AbstractCondition and(LocalDate endExcl) {
        query.append(" AND ?");
        params.add(endExcl.toString());
        return new AbstractCondition(query, params);
    }
}
