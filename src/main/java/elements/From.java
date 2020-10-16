package elements;

import java.time.LocalDate;
import java.util.List;

public class From extends AbstractSQLElement {
    public From(StringBuilder query, List<Object> params) {
        super(query, params);
    }

    public ForBusinessTimeAsOf forBusinessTimeAsOf(String input) {
        params.add(input);
        query.append(" FOR BUSINESS_TIME AS OF ?");
        return new ForBusinessTimeAsOf(query, params);
    }

    public ForBusinessTimeAsOf forBusinessTimeAsOf(LocalDate input) {
        params.add(input.toString());
        query.append(" FOR BUSINESS_TIME AS OF ?");
        return new ForBusinessTimeAsOf(query, params);
    }


}
