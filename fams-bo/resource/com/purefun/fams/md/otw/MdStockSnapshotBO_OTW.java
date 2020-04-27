package com.purefun.fams.md.otw;

import com.purefun.fams.md.MdStockSnapshotBO;
import com.purefun.fams.md.pro.MdStockSnapshotBO_PRO;
import com.google.protobuf.InvalidProtocolBufferException;
import com.purefun.fams.core.bo.commom.ICommon_OTW;
import com.google.protobuf.Any;

public class MdStockSnapshotBO_OTW implements ICommon_OTW {
    MdStockSnapshotBO_PRO.MdStockSnapshotBO.Builder builder = null;
    MdStockSnapshotBO bo = null;

    public MdStockSnapshotBO_OTW() {
        builder = MdStockSnapshotBO_PRO.MdStockSnapshotBO.newBuilder();
        bo= new MdStockSnapshotBO();
        builder.setUuid(bo.uuid);
        builder.setBoid(bo.boid);
        builder.setDestination(bo.destination);
    }

    public MdStockSnapshotBO_OTW(byte[] message) throws InvalidProtocolBufferException {
        builder = MdStockSnapshotBO_PRO.MdStockSnapshotBO.newBuilder();
        bo= new MdStockSnapshotBO();
        MdStockSnapshotBO_PRO.MdStockSnapshotBO receive = MdStockSnapshotBO_PRO.MdStockSnapshotBO.parseFrom(message);
        setSecurity_code(receive.getSecurityCode());
        setExch(receive.getExch());
        setSecurity_type(receive.getSecurityType());
        setCnName(receive.getCnName());
        setDateTime(receive.getDateTime());
        setOpen(receive.getOpen());
        setPreClose(receive.getPreClose());
        setLastPrice(receive.getLastPrice());
        setHigh(receive.getHigh());
        setLow(receive.getLow());
        setChange(receive.getChange());
        setChangePer(receive.getChangePer());
        setBuyPriceList(receive.getBuyPriceList());
        setBuyQtyList(receive.getBuyQtyList());
        setSellPriceList(receive.getSellPriceList());
        setSellQtyList(receive.getSellQtyList());
        setTotalVolume(receive.getTotalVolume());
        setTotalAmont(receive.getTotalAmont());
        setUuid(receive.getUuid());
        setBoid(receive.getBoid());
        setDestination(receive.getDestination());
    }

    public MdStockSnapshotBO_OTW(MdStockSnapshotBO bofrom){
        builder = MdStockSnapshotBO_PRO.MdStockSnapshotBO.newBuilder();
        bo= new MdStockSnapshotBO();
        setSecurity_code(bofrom.security_code);
        setExch(bofrom.exch);
        setSecurity_type(bofrom.security_type);
        setCnName(bofrom.cnName);
        setDateTime(bofrom.dateTime);
        setOpen(bofrom.open);
        setPreClose(bofrom.preClose);
        setLastPrice(bofrom.lastPrice);
        setHigh(bofrom.high);
        setLow(bofrom.low);
        setChange(bofrom.change);
        setChangePer(bofrom.changePer);
        setBuyPriceList(bofrom.buyPriceList);
        setBuyQtyList(bofrom.buyQtyList);
        setSellPriceList(bofrom.sellPriceList);
        setSellQtyList(bofrom.sellQtyList);
        setTotalVolume(bofrom.totalVolume);
        setTotalAmont(bofrom.totalAmont);
        setUuid(bofrom.uuid);
        setBoid(bofrom.boid);
        setDestination(bofrom.destination);
    }

    public byte[] serial() {
        return builder.build().toByteArray();
    }

    @Override
    public com.google.protobuf.GeneratedMessageV3.Builder getBuilder() { 
        return builder;
    }

    @Override
    public MdStockSnapshotBO getBo() { 
        return bo;
    }

    public java.lang.String getSecurity_code() {
        return builder.getSecurityCode();
    }

    public void setSecurity_code(java.lang.String security_code) {
        bo.security_code = security_code;
        builder.setSecurityCode(security_code);
    }

    public java.lang.String getExch() {
        return builder.getExch();
    }

    public void setExch(java.lang.String exch) {
        bo.exch = exch;
        builder.setExch(exch);
    }

    public java.lang.String getSecurity_type() {
        return builder.getSecurityType();
    }

    public void setSecurity_type(java.lang.String security_type) {
        bo.security_type = security_type;
        builder.setSecurityType(security_type);
    }

    public java.lang.String getCnName() {
        return builder.getCnName();
    }

    public void setCnName(java.lang.String cnName) {
        bo.cnName = cnName;
        builder.setCnName(cnName);
    }

    public java.lang.String getDateTime() {
        return builder.getDateTime();
    }

    public void setDateTime(java.lang.String dateTime) {
        bo.dateTime = dateTime;
        builder.setDateTime(dateTime);
    }

    public double getOpen() {
        return builder.getOpen();
    }

    public void setOpen(double open) {
        bo.open = open;
        builder.setOpen(open);
    }

    public double getPreClose() {
        return builder.getPreClose();
    }

    public void setPreClose(double preClose) {
        bo.preClose = preClose;
        builder.setPreClose(preClose);
    }

    public double getLastPrice() {
        return builder.getLastPrice();
    }

    public void setLastPrice(double lastPrice) {
        bo.lastPrice = lastPrice;
        builder.setLastPrice(lastPrice);
    }

    public double getHigh() {
        return builder.getHigh();
    }

    public void setHigh(double high) {
        bo.high = high;
        builder.setHigh(high);
    }

    public double getLow() {
        return builder.getLow();
    }

    public void setLow(double low) {
        bo.low = low;
        builder.setLow(low);
    }

    public double getChange() {
        return builder.getChange();
    }

    public void setChange(double change) {
        bo.change = change;
        builder.setChange(change);
    }

    public double getChangePer() {
        return builder.getChangePer();
    }

    public void setChangePer(double changePer) {
        bo.changePer = changePer;
        builder.setChangePer(changePer);
    }

    public java.lang.String getBuyPriceList() {
        return builder.getBuyPriceList();
    }

    public void setBuyPriceList(java.lang.String buyPriceList) {
        bo.buyPriceList = buyPriceList;
        builder.setBuyPriceList(buyPriceList);
    }

    public java.lang.String getBuyQtyList() {
        return builder.getBuyQtyList();
    }

    public void setBuyQtyList(java.lang.String buyQtyList) {
        bo.buyQtyList = buyQtyList;
        builder.setBuyQtyList(buyQtyList);
    }

    public java.lang.String getSellPriceList() {
        return builder.getSellPriceList();
    }

    public void setSellPriceList(java.lang.String sellPriceList) {
        bo.sellPriceList = sellPriceList;
        builder.setSellPriceList(sellPriceList);
    }

    public java.lang.String getSellQtyList() {
        return builder.getSellQtyList();
    }

    public void setSellQtyList(java.lang.String sellQtyList) {
        bo.sellQtyList = sellQtyList;
        builder.setSellQtyList(sellQtyList);
    }

    public long getTotalVolume() {
        return builder.getTotalVolume();
    }

    public void setTotalVolume(long totalVolume) {
        bo.totalVolume = totalVolume;
        builder.setTotalVolume(totalVolume);
    }

    public double getTotalAmont() {
        return builder.getTotalAmont();
    }

    public void setTotalAmont(double totalAmont) {
        bo.totalAmont = totalAmont;
        builder.setTotalAmont(totalAmont);
    }

    public java.lang.String getUuid() {
        return builder.getUuid();
    }

    public void setUuid(java.lang.String uuid) {
        bo.uuid = uuid;
        builder.setUuid(uuid);
    }

    public long getBoid() {
        return builder.getBoid();
    }

    public void setBoid(long boid) {
        bo.boid = boid;
        builder.setBoid(boid);
    }

    public java.lang.String getDestination() {
        return builder.getDestination();
    }

    public void setDestination(java.lang.String destination) {
        bo.destination = destination;
        builder.setDestination(destination);
    }

    public String toString() {
        return "MdStockSnapshotBO_OTW ["+
            "uuid = " + getUuid() +"," +
            "boid = " + getBoid() +"," +
            "destination = " + getDestination() +"," +
            "security_code = " + getSecurity_code() +"," +
            "exch = " + getExch() +"," +
            "security_type = " + getSecurity_type() +"," +
            "cnName = " + getCnName() +"," +
            "dateTime = " + getDateTime() +"," +
            "open = " + getOpen() +"," +
            "preClose = " + getPreClose() +"," +
            "lastPrice = " + getLastPrice() +"," +
            "high = " + getHigh() +"," +
            "low = " + getLow() +"," +
            "change = " + getChange() +"," +
            "changePer = " + getChangePer() +"," +
            "buyPriceList = " + getBuyPriceList() +"," +
            "buyQtyList = " + getBuyQtyList() +"," +
            "sellPriceList = " + getSellPriceList() +"," +
            "sellQtyList = " + getSellQtyList() +"," +
            "totalVolume = " + getTotalVolume() +"," +
            "totalAmont = " + getTotalAmont() +"," +
         "]";
    }
}
