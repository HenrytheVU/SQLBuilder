package sqlbuilder;

import java.util.List;

public class Like extends AbstractQuery {
    public Like(StringBuilder query, List<Object> params) {
        super(query, params);
    }
}
