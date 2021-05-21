package sqlbuilder;

import java.util.List;

public class Using extends From {
	public Using(StringBuilder query, List<Object> params) {
		super(query, params);
	}
}
