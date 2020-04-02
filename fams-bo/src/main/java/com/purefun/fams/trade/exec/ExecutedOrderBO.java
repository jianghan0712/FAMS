package com.purefun.fams.trade.exec;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import com.purefun.fams.core.bo.BaseBO;
import com.purefun.fams.core.bo.tool.fstpbo;

@fstpbo(boid = 1501L, destination = "fams.trade.execute")
public class ExecutedOrderBO extends BaseBO {

	/**
	 * @Fields:
	 */
	private static final long serialVersionUID = 7007615505684771091L;

	@QuerySqlField
	public String account;

	@QuerySqlField
	public String security_code;

	@QuerySqlField
	public String exch;

	@QuerySqlField
	public String security_type;

	public String direction;// 买卖方向

	@QuerySqlField
	public String orderId;// 委托单号

	public String tradeDate;

	public String executeId; // 成交编号

	public double executePrice;// 当次成交价格

	public long executeVolume;// 当次成交数量

}
