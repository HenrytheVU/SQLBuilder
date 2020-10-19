package sqlbuilder;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UnionAll extends AbstractQuery {
    public UnionAll(StringBuilder query, List<Object> params) {
        super(query, params);
    }

    public Select select(final String... cols) {
        query.append(" SELECT ");
        if (cols.length > 0) {
            String colsJoining = Stream.of(cols).collect(Collectors.joining(", "));
            query.append(colsJoining);
        } else {
            query.append("*");
        }
        return new Select(query, params);
    }
}
