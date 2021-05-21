package sqlbuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderBy extends AbstractQuery {
	public enum SortOrder {
		ASC("ASC"), DESC("DESC");

		String sqlRep;

		SortOrder(String sqlRep) {
			this.sqlRep = sqlRep;
		}

		static Map<String, SortOrder> validSortOrders = new HashMap<>();

		static {
			validSortOrders.put("ASC", ASC);
			validSortOrders.put("DESC", DESC);
		}

		public static SortOrder getEnum(String sqlRep) {
			if (!validSortOrders.containsKey(sqlRep.toUpperCase())) {
				return ASC;
			}
			return validSortOrders.get(sqlRep.toUpperCase());
		}

		public String getSqlRep() {
			return this.sqlRep;
		}

		@Override
		public String toString() {
			return this.sqlRep;
		}
	}

	public OrderBy(StringBuilder query, List<Object> params) {
		super(query, params);
	}

	public FetchNextRowsOnly fetchNexRowsOnly(int n) {
		query.append(" FETCH NEXT ").append(n).append(" ROWS ONLY");
		return new FetchNextRowsOnly(query, params);
	}

	public Offset offset(int n) {
		query.append(" OFFSET ").append(n).append(" ROWS");
		return new Offset(query, params);
	}

	public OrderBy sortOrder(SortOrder sortOrder) {
		query.append(" " + sortOrder.getSqlRep());
		return new OrderBy(query, params);
	}

	public Count count(String col) {
		query.append(" COUNT (").append(col).append(")");
		return new Count(query, params);
	}
}
