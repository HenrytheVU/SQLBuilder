package sqlbuilder;

import java.time.LocalDate;
import java.util.List;

public class ForPortionOfBusinessTimeFrom extends AbstractQuery {
    public ForPortionOfBusinessTimeFrom(StringBuilder query, List<Object> params) {
        super(query, params);
    }

    public To to(LocalDate endExcl) {
        query.append(" TO ?");
        params.add(endExcl.toString());
        return new To(query, params);
    }
}
