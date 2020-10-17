package sql;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractQuery {

    final StringBuilder query;
    final List<Object> params;

    public AbstractQuery(StringBuilder query, List<Object> params) {
        this.query = query;
        this.params = params;
    }

    public List<Object> getParams() {
        return params;
    }

    public Object[] getParamObjects() {
        return params.toArray();
    }

    public String getQuery() {
        AtomicInteger counter = new AtomicInteger();
        return Stream.of(query.toString().split(" ")).map(s -> {
            if (s.contains("?")) {
                s = s.replace("?", toString(this.params.get(counter.getAndIncrement())));
            }
            return s;
        }).collect(Collectors.joining(" "));
    }

    public String getQueryWithPlaceHolders() {
        return query.toString();
    }

    private String toString(Object obj) {
        if (obj instanceof String || obj instanceof LocalDate) {
            return String.format("'%s'", obj.toString());
        }
        if (obj instanceof Integer || obj instanceof Long || obj instanceof Short) {
            return obj.toString();
        }
        throw new RuntimeException(String.format("Not supported type: %s", obj.getClass()));
    }
}
