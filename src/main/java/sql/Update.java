package sql;

import com.sun.istack.internal.NotNull;

import java.time.LocalDate;
import java.util.List;

public class Update extends AbstractQuery {
    public Update(StringBuilder query, List<Object> params) {
        super(query, params);
    }

    public Set set(@NotNull final List<String> cols, final List<Object> values) {
        query.append(" SET ");
        for (int i = 0; i < cols.size(); i++) {
            if (i == cols.size() - 1) {
                query.append(cols.get(i)).append(" = ?");
            } else {
                query.append(cols.get(i)).append(" = ?, ");
            }
        }
        params.addAll(values);
        return new Set(query, params);
    }

    public Set set(@NotNull final String col, final Object value) {
        query.append(" SET ").append(col).append(" = ?");
        params.add(value);
        return new Set(query, params);
    }

    public ForPortionOfBusinessTimeFrom forPortionOfBusinessTimeFrom(LocalDate date) {
        query.append(" FOR PORTION OF BUSINESS_TIME FROM ?");
        params.add(date.toString());
        return new ForPortionOfBusinessTimeFrom(query, params);
    }
}
