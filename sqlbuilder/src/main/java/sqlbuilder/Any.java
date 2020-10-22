package sqlbuilder;

import java.util.List;

public class Any extends AbstractQuery {
    public Any(StringBuilder query, List<Object> params) {
        super(query, params);
    }
}
