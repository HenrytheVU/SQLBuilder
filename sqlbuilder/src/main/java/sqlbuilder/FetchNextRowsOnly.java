package sqlbuilder;

import java.util.List;

public class FetchNextRowsOnly extends AbstractQuery {
	public FetchNextRowsOnly(StringBuilder query, List<Object> params) {
		super(query, params);
	}
}
