package sqlbuilder;

import java.util.List;

public class NotIn extends AbstractQuery {
	public NotIn(StringBuilder query, List<Object> params) {
		super(query, params);
	}
}
