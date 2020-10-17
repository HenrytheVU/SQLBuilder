package sql;

import java.util.List;

public class LeftJoin extends AbstractJoin {
    public LeftJoin(StringBuilder query, List<Object> params) {
        super(query, params);
    }
}
