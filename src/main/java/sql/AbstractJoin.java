package sql;

import java.util.List;

public class AbstractJoin extends AbstractQuery {
    public AbstractJoin(StringBuilder query, List<Object> params) {
        super(query, params);
    }

    public On on(String col) {
        query.append(" ON ").append(col);
        return new On(query, params);
    }

    public As as(String alias) {
        query.append(" AS ").append(alias);
        return new As(query, params);
    }
}
