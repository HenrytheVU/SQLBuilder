package sqlbuilder;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InsertInto extends AbstractQuery {
    public InsertInto(StringBuilder query, List<Object> params) {
        super(query, params);
    }

    public Values values(Object... values) {
        if (values.length == 0) {
            return valuesAsPlaceHolders();
        }

        query.append(" VALUES (");
        for (int i = 0; i < values.length; i++) {
            if (i == values.length - 1) {
                query.append("?)");
            } else {
                query.append("?, ");
            }
        }
        Collections.addAll(params, values);
        return new Values(query, params);
    }

    public Values valuesWithNumberOfPlaceHolders(Integer noPlaceHolders) {
        if (noPlaceHolders <= 0) {
            throw new BadSQLSyntaxException("Number of placeholders must be >= 0!");
        }
        query.append(" VALUES (");
        for (int i = 1; i <= noPlaceHolders; i++) {
            if (i == noPlaceHolders) {
                query.append("?)");
            } else {
                query.append("?, ");
            }
        }
        return new Values(query, params);
    }

    public Values valuesAsPlaceHolders() {
        query.append(" VALUES (");
        int noCols = getNoColsToBeInserted(query.toString());
        for (int i = 0; i < noCols; i++) {
            if (i == noCols - 1) {
                query.append("?)");
            } else {
                query.append("?, ");
            }
        }
        return new Values(query, params);
    }

    public InsertInto columns(String... cols) {
        String colsJoining = Stream.of(cols).collect(Collectors.joining(", "));
        query.append(" (").append(colsJoining).append(")");
        return new InsertInto(query, params);
    }

    private Integer getNoColsToBeInserted(String insertIntoQuery) {
        String colsStrippedLeft = insertIntoQuery.split("\\(")[1];
        String colsStripped = colsStrippedLeft.split("\\)")[0];
        final int noCols = colsStripped.split(", ").length;
        return noCols;
    }
}
