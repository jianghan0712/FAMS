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
public class MdStockSnapshotTempletBO extends BaseBO {

	/** @Fields: */
	private static final long serialVersionUID = 1183593459149813531L;

	@QuerySqlField
	public String security_code;

	@QuerySqlField
	public String exch;

	public long dateTime;// yyyyMMddhhmmss

	public double open;

	public double preClose;

	public double lastPrice;// 最新价格

	public double high;

	public double low;

	public double buy1Price;

	public long buy1Qty;

	public double buy2Price;

	public long buy2Qty;

	public double buy3Price;

	public long buy3Qty;

	public double buy4Price;

	public long buy4Qty;

	public double buy5Price;

	public long buy5Qty;

	public double sell1Price;

	public long sell1Qty;

	public double sell2Price;

	public long sell2Qty;

	public double sell3Price;

	public long sell3Qty;

	public double sell4Price;

	public long sell4Qty;

	public double sell5Price;

	public long sell5Qty;

	public long totalVolume;// 已成交量，以手为单位

	public double totalAmont;// 已成交金额，元为单位

}
