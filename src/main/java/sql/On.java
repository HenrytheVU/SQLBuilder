package sql;

import java.util.List;

public class On extends AbstractQuery {
    public On(StringBuilder query, List<Object> params) {
        super(query, params);
    }

    public Equal eqCol(String col) {
        query.append(" = ").append(col);
        return new Equal(query, params);
    }

    public Equal neCol(String col) {
        query.append(" <> ").append(col);
        return new Equal(query, params);
    }

    public Equal gtCol(String col) {
        query.append(" > ").append(col);
        return new Equal(query, params);
    }

    public Equal geCol(String col) {
        query.append(" >= ").append(col);
        return new Equal(query, params);
    }

    public Equal ltCol(String col) {
        query.append(" < ").append(col);
        return new Equal(query, params);
    }

    public Equal leCol(String col) {
        query.append(" <= ").append(col);
        return new Equal(query, params);
    }

}
