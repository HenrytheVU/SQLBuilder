package sqlbuilder;

import java.util.List;

public class Exists extends AbstractQuery {
    public Exists(StringBuilder query, List<Object> params) {
        super(query, params);
    }
}
