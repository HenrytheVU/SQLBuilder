package sql;

import java.util.List;

public class Not extends AbstractQuery {
    public Not(StringBuilder query, List<Object> params) {
        super(query, params);
    }

    public Exists exists(final AbstractQuery subQuery) {
        query.append(" EXISTS (").append(subQuery.getQueryWithPlaceHolders()).append(")");
        params.addAll(subQuery.getParams());
        return new Exists(query, params);
    }
}
