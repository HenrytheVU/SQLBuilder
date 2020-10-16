package sql;

import java.time.LocalDate;
import java.util.List;

public class DeleteFrom extends AbstractQuery {
    public DeleteFrom(StringBuilder query, List<Object> params) {
        super(query, params);
    }

    public ForPortionOfBusinessTimeFrom forPortionOfBusinessTimeFrom(LocalDate startIncl) {
        query.append(" FOR PORTION OF BUSINESS_TIME FROM ?");
        params.add(startIncl.toString());
        return new ForPortionOfBusinessTimeFrom(query, params);
    }

    public Where where(String col) {
        query.append(" WHERE ").append(col);
        return new Where(query, params);
    }
}
