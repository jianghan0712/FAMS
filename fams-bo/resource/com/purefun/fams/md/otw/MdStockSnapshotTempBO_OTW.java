package com.purefun.fams.md.otw;

import com.purefun.fams.md.MdStockSnapshotTempBO;
import com.purefun.fams.md.pro.MdStockSnapshotTempBO_PRO;
import com.google.protobuf.InvalidProtocolBufferException;
import com.purefun.fams.core.bo.commom.ICommon_OTW;
import com.google.protobuf.Any;

public class MdStockSnapshotTempBO_OTW implements ICommon_OTW {
    MdStockSnapshotTempBO_PRO.MdStockSnapshotTempBO.Builder builder = null;
    MdStockSnapshotTempBO bo = null;

    public MdStockSnapshotTempBO_OTW() {
        builder = MdStockSnapshotTempBO_PRO.MdStockSnapshotTempBO.newBuilder();
        bo= new MdStockSnapshotTempBO();
        builder.setUuid(bo.uuid);
        builder.setBoid(bo.boid);
        builder.setDestination(bo.destination);
    }

    public MdStockSnapshotTempBO_OTW(byte[] message) throws InvalidProtocolBufferException {
        builder = MdStockSnapshotTempBO_PRO.MdStockSnapshotTempBO.newBuilder();
        bo= new MdStockSnapshotTempBO();
        MdStockSnapshotTempBO_PRO.MdStockSnapshotTempBO receive = MdStockSnapshotTempBO_PRO.MdStockSnapshotTempBO.parseFrom(message);
        setSecurity_code(receive.getSecurityCode());
        setExch(receive.getExch());
        setDateTime(receive.getDateTime());
        setOpen(receive.getOpen());
        setPreClose(receive.getPreClose());
        setLastPrice(receive.getLastPrice());
        setHigh(receive.getHigh());
        setLow(receive.getLow());
        setBuyPriceList(receive.getBuyPriceListList());
        setBuyQtyList(receive.getBuyQtyListList());
        setSellPriceList(receive.getSellPriceListList());
        setSellQtyList(receive.getSellQtyListList());
        setTotalVolume(receive.getTotalVolume());
        setTotalAmont(receive.getTotalAmont());
        setUuid(receive.getUuid());
        setBoid(receive.getBoid());
        setDestination(receive.getDestination());
    }

    public MdStockSnapshotTempBO_OTW(MdStockSnapshotTempBO bofrom){
        builder = MdStockSnapshotTempBO_PRO.MdStockSnapshotTempBO.newBuilder();
        bo= new MdStockSnapshotTempBO();
        setSecurity_code(bofrom.security_code);
        setExch(bofrom.exch);
        setDateTime(bofrom.dateTime);
        setOpen(bofrom.open);
        setPreClose(bofrom.preClose);
        setLastPrice(bofrom.lastPrice);
        setHigh(bofrom.high);
        setLow(bofrom.low);
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
    public MdStockSnapshotTempBO getBo() { 
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

    public long getDateTime() {
        return builder.getDateTime();
    }

    public void setDateTime(long dateTime) {
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

    public java.util.List getBuyPriceList() {
        return builder.getBuyPriceListList();
    }

    public void setBuyPriceList(java.util.List buyPriceList) {
        bo.buyPriceList = buyPriceList;
        builder.addAllBuyPriceList(buyPriceList);
    }

    public java.util.List getBuyQtyList() {
        return builder.getBuyQtyListList();
    }

    public void setBuyQtyList(java.util.List buyQtyList) {
        bo.buyQtyList = buyQtyList;
        builder.addAllBuyQtyList(buyQtyList);
    }

    public java.util.List getSellPriceList() {
        return builder.getSellPriceListList();
    }

    public void setSellPriceList(java.util.List sellPriceList) {
        bo.sellPriceList = sellPriceList;
        builder.addAllSellPriceList(sellPriceList);
    }

    public java.util.List getSellQtyList() {
        return builder.getSellQtyListList();
    }

    public void setSellQtyList(java.util.List sellQtyList) {
        bo.sellQtyList = sellQtyList;
        builder.addAllSellQtyList(sellQtyList);
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
        return "MdStockSnapshotTempBO_OTW ["+
            "uuid = " + getUuid() +"," +
            "boid = " + getBoid() +"," +
            "destination = " + getDestination() +"," +
            "security_code = " + getSecurity_code() +"," +
            "exch = " + getExch() +"," +
            "dateTime = " + getDateTime() +"," +
            "open = " + getOpen() +"," +
            "preClose = " + getPreClose() +"," +
            "lastPrice = " + getLastPrice() +"," +
            "high = " + getHigh() +"," +
            "low = " + getLow() +"," +
            "buyPriceList = " + getBuyPriceList() +"," +
            "buyQtyList = " + getBuyQtyList() +"," +
            "sellPriceList = " + getSellPriceList() +"," +
            "sellQtyList = " + getSellQtyList() +"," +
            "totalVolume = " + getTotalVolume() +"," +
            "totalAmont = " + getTotalAmont() +"," +
         "]";
    }
}
