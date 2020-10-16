package sql;

import java.util.List;

public class FullOuterJoin extends AbstractQuery {
    public FullOuterJoin(StringBuilder query, List<Object> params) {
        super(query, params);
    }

    public On on(String leftTableCol, String rightTableCol) {
        query.append(" ON ").append(leftTableCol).append(" = ").append(rightTableCol);
        return new On(query, params);
    }
}
