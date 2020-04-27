/**
 * 
 */
package com.purefun.fams.md;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import com.purefun.fams.core.bo.BaseBO;
import com.purefun.fams.core.bo.tool.fstpbo;

/**
 * 快照股票行情
 * 
 * @Classname: MdStockSnapshotBO
 * @Description:
 * @author jianghan
 * @date 2020-04-27 19:50:32
 */
@fstpbo(boid = 1101L, destination = "fams.md.snapshot.stock")
public class MdStockSnapshotBO extends BaseBO {

	/** @Fields: */
	private static final long serialVersionUID = 1183593459149813531L;

	@QuerySqlField
	public String security_code;

	@QuerySqlField
	public String exch;

	@QuerySqlField
	public String security_type;

	public String cnName;

	public String dateTime;// yyyyMMddhhmmss

	public double open;

	public double preClose;

	public double lastPrice;

	public double high;

	public double low;

	public double change;// 与前收盘价的变化即：lastPrice-preClose

	public double changePer;// 变动百分比：(lastPrice-preClose)/preClose *100%

	public String buyPriceList;// 五档买行情，买一->买五，以^做隔断

	public String buyQtyList;// 五档买挂单量，买一->买五，以^做隔断

	public String sellPriceList;// 五档买行情，卖五->卖一，以^做隔断

	public String sellQtyList;// 五档卖挂单量，卖五->卖一，以^做隔断

	public long totalVolume;// 已成交量，以手为单位

	public double totalAmont;// 已成交金额，元为单位

}
