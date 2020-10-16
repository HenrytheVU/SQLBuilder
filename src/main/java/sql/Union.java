package sql;

import com.sun.istack.internal.NotNull;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Union extends AbstractQuery {
    public Union(StringBuilder query, List<Object> params) {
        super(query, params);
    }

    public Select select(@NotNull final String... cols) {
        query.append("SELECT ");
        if (cols.length > 0) {
            String colsJoining = Stream.of(cols).collect(Collectors.joining(", "));
            query.append(colsJoining);
        } else {
            query.append("*");
        }
        return new Select(query, params);
    }
}
