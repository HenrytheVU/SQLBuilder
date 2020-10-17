package sql;

import java.util.List;

public class OrderBy extends AbstractQuery {
    public OrderBy(StringBuilder query, List<Object> params) {
        super(query, params);
    }

    public FetchNextRowsOnly fetchNexRowsOnly(int n) {
        query.append(" FETCH NEXT ").append(n).append(" ROWS ONLY");
        return new FetchNextRowsOnly(query, params);
    }

    public Offset offset(Integer n) {
        query.append(" OFFSET ").append(n).append(" ROWS");
        return new Offset(query, params);
    }

    public Asc asc() {
        query.append(" ASC");
        return new Asc(query, params);
    }

    public Desc desc() {
        query.append(" DESC");
        return new Desc(query, params);
    }

    public Count count(String col) {
        query.append(" COUNT (").append(col).append(")");
        return new Count(query, params);
    }
}
