/**
 * 
 */
package com.purefun.fams.ace.md;

import java.util.ArrayList;
import java.util.List;

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

	public long dateTime;// yyyyMMddhhmmss

	public double open;

	public double preClose;

	public double lastPrice;// 最新价格

	public double high;

	public double low;

	public List<Double> buyPriceList = new ArrayList<Double>();// 五档买行情，买一->买五

	public List<Long> buyQtyList = new ArrayList<Long>();// 五档买挂单量，买一->买五

	public List<Double> sellPriceList = new ArrayList<Double>();// 五档买行情，卖1->卖5

	public List<Long> sellQtyList = new ArrayList<Long>();// 五档卖挂单量，卖1->卖5

	public long totalVolume;// 已成交量，以手为单位

	public double totalAmont;// 已成交金额，元为单位

}
