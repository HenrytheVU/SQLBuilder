package sqlbuilder;

import java.time.LocalDate;
import java.util.List;

public class AbstractJoin extends AbstractQuery {
	public AbstractJoin(StringBuilder query, List<Object> params) {
		super(query, params);
	}

	public On on(String col) {
		query.append(" ON ").append(col);
		return new On(query, params);
	}

	public Using using(String... cols) {
		query.append(" USING (").append(String.join(", ", cols)).append(")");
		return new Using(query, params);
	}

	public As as(String alias) {
		query.append(" AS ").append(alias);
		return new As(query, params);
	}

	public AbstractJoin forBusinessTimeAsOf(LocalDate startIncl) {
		query.append(" FOR BUSINESS_TIME AS OF ?");
		params.add(startIncl.toString());
		return this;
	}
}
