package sql;

import java.util.List;

public class RightJoin extends AbstractQuery {
    public RightJoin(StringBuilder query, List<Object> params) {
        super(query, params);
    }

    public On on(String leftTableCol, String rightTableCol) {
        query.append(" ON ").append(leftTableCol).append(" = ").append(rightTableCol);
        return new On(query, params);
    }
}
