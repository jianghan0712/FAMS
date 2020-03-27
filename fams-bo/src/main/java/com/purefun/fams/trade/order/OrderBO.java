package com.purefun.fams.trade.order;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import com.purefun.fams.core.bo.BaseBO;
import com.purefun.fams.core.bo.tool.fstpbo;

/**
 * 
 * @Classname: OrderBO
 * @Description:
 * @author 015979
 * @date 2020-03-27 11:39:46
 */
@fstpbo(boid = 1101L, destination = "fams.trade.order")
public class OrderBO extends BaseBO {

	/**
	 * @Fields:
	 */
	private static final long serialVersionUID = 86111866099903675L;

	@QuerySqlField
	public String account;

	@QuerySqlField
	public String security_code;

	@QuerySqlField
	public String exch;

	@QuerySqlField
	public String security_type;

	public String direction;

	public double price;

	public long volume;

	public boolean withdrawFlag;

}
