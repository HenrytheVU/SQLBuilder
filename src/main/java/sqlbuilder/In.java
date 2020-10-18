package sqlbuilder;

import java.util.List;

public class In extends AbstractQuery {
    public In(StringBuilder query, List<Object> params) {
        super(query, params);
    }
}
