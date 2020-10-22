package sqlbuilder;

import java.util.List;

public class IsNotNull extends AbstractQuery {
    public IsNotNull(StringBuilder query, List<Object> params) {
        super(query, params);
    }
}
