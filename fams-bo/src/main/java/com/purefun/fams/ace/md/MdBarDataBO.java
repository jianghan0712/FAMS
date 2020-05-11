/**
 * 
 */
package com.purefun.fams.ace.md;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import com.purefun.fams.core.bo.BaseBO;
import com.purefun.fams.core.bo.tool.fstpbo;

/**
 * @author jiang
 *
 */
@fstpbo(boid = 1001L, destination = "fams.md.bar.stock")
public class MdBarDataBO extends BaseBO {

	/**
	 * @Fields:
	 */
	private static final long serialVersionUID = -4907652091493470134L;

	@QuerySqlField
	public String security_code;

	@QuerySqlField
	public String exch;

	@QuerySqlField
	public String security_type;

	@QuerySqlField
	public String date;

	@QuerySqlField
	public double open;

	@QuerySqlField
	public double high;

	@QuerySqlField
	public double low;

	@QuerySqlField
	public double close;

	@QuerySqlField
	public long volume;

	@QuerySqlField
	public double change;

	@QuerySqlField
	public double pre_close;

	@QuerySqlField
	public double pct_chg;

	@QuerySqlField
	public double amount;

}
