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
@fstpbo(boid = 1101L, destination = "fams.ace.oms.trade.order")
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

	public String tradeDate;

	@QuerySqlField
	public String orderId;// 委托单号

	public String orderStatus;// 新委托NEW /

	public String orderRejectedReson;// 订单被拒绝原因

	public String orderType;// 委托类型：LIMIT等

	public double orderPrice;// 委托价格

	public long orderVolume;// 委托数量

	public String direction;// 交易方向

	public String execStatus;// 成交状态：无成交PENDING /全部成交FULL/ 部分成交PARTIAL /全部撤回 WITHDRAW /部分撤回 PART-WITHDRAW

	public boolean withdrawFlag;

	public long withdrawVolume;// 撤单数量

}
