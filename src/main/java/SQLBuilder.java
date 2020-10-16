import elements.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SQLBuilder {

    public static Select select(String... cols) {
        final StringBuilder query = new StringBuilder();
        final List<Object> params = new ArrayList<>();
        query.append("SELECT ");
        if (cols.length > 0) {
            String colsJoining = Stream.of(cols).collect(Collectors.joining(", "));
        } else {
            query.append("*");
        }
        return new Select(query, params);
    }
}
