package sqlbuilder;

import java.util.List;

public class Offset extends AbstractQuery {
	public Offset(StringBuilder query, List<Object> params) {
		super(query, params);
	}

	public FetchNextRowsOnly fetchNextRowsOnly(int n) {
		query.append(" FETCH NEXT ").append(n).append(" ROWS ONLY");
		return new FetchNextRowsOnly(query, params);
	}
}
