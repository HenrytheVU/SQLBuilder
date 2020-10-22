package sqlbuilder;

import java.util.List;

public class Comma extends AbstractQuery {
    public Comma(StringBuilder query, List<Object> params) {
        super(query, params);
    }

    public As as(String alias) {
        query.append(" AS ").append(alias);
        return new As(query, params);
    }

    public From from(String table) {
        query.append(" FROM ").append(table);
        return new From(query, params);
    }

    public AbstractCondition where(String col) {
        query.append(" WHERE ").append(col);
        return new AbstractCondition(query, params);
    }

    public Count count(String col) {
        query.append(" COUNT (").append(col).append(")");
        return new Count(query, params);
    }
}
