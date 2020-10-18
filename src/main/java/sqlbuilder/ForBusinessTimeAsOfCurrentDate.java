package sqlbuilder;

import java.util.List;

public class ForBusinessTimeAsOfCurrentDate extends AbstractQuery {
    public ForBusinessTimeAsOfCurrentDate(StringBuilder query, List<Object> params) {
        super(query, params);
    }
}
