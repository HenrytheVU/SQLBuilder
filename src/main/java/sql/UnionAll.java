package sql;

import java.util.List;

public class UnionAll extends AbstractQuery {
    public UnionAll(StringBuilder query, List<Object> params) {
        super(query, params);
    }
}
