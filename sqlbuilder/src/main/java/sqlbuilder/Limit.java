package sqlbuilder;

import java.util.List;

public class Limit extends AbstractQuery {
	public Limit(StringBuilder query, List<Object> params) {
		super(query, params);
	}
}
