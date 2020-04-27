package com.purefun.fams.md.otw;

import com.purefun.fams.md.MdStockSnapshotTempletBO;
import com.purefun.fams.md.pro.MdStockSnapshotTempletBO_PRO;
import com.google.protobuf.InvalidProtocolBufferException;
import com.purefun.fams.core.bo.commom.ICommon_OTW;
import com.google.protobuf.Any;

public class MdStockSnapshotTempletBO_OTW implements ICommon_OTW {
    MdStockSnapshotTempletBO_PRO.MdStockSnapshotTempletBO.Builder builder = null;
    MdStockSnapshotTempletBO bo = null;

    public MdStockSnapshotTempletBO_OTW() {
        builder = MdStockSnapshotTempletBO_PRO.MdStockSnapshotTempletBO.newBuilder();
        bo= new MdStockSnapshotTempletBO();
        builder.setUuid(bo.uuid);
        builder.setBoid(bo.boid);
        builder.setDestination(bo.destination);
    }

    public MdStockSnapshotTempletBO_OTW(byte[] message) throws InvalidProtocolBufferException {
        builder = MdStockSnapshotTempletBO_PRO.MdStockSnapshotTempletBO.newBuilder();
        bo= new MdStockSnapshotTempletBO();
        MdStockSnapshotTempletBO_PRO.MdStockSnapshotTempletBO receive = MdStockSnapshotTempletBO_PRO.MdStockSnapshotTempletBO.parseFrom(message);
        setSecurity_code(receive.getSecurityCode());
        setExch(receive.getExch());
        setDateTime(receive.getDateTime());
        setOpen(receive.getOpen());
        setPreClose(receive.getPreClose());
        setLastPrice(receive.getLastPrice());
        setHigh(receive.getHigh());
        setLow(receive.getLow());
        setBuy1Price(receive.getBuy1Price());
        setBuy1Qty(receive.getBuy1Qty());
        setBuy2Price(receive.getBuy2Price());
        setBuy2Qty(receive.getBuy2Qty());
        setBuy3Price(receive.getBuy3Price());
        setBuy3Qty(receive.getBuy3Qty());
        setBuy4Price(receive.getBuy4Price());
        setBuy4Qty(receive.getBuy4Qty());
        setBuy5Price(receive.getBuy5Price());
        setBuy5Qty(receive.getBuy5Qty());
        setSell1Price(receive.getSell1Price());
        setSell1Qty(receive.getSell1Qty());
        setSell2Price(receive.getSell2Price());
        setSell2Qty(receive.getSell2Qty());
        setSell3Price(receive.getSell3Price());
        setSell3Qty(receive.getSell3Qty());
        setSell4Price(receive.getSell4Price());
        setSell4Qty(receive.getSell4Qty());
        setSell5Price(receive.getSell5Price());
        setSell5Qty(receive.getSell5Qty());
        setTotalVolume(receive.getTotalVolume());
        setTotalAmont(receive.getTotalAmont());
        setUuid(receive.getUuid());
        setBoid(receive.getBoid());
        setDestination(receive.getDestination());
    }

    public MdStockSnapshotTempletBO_OTW(MdStockSnapshotTempletBO bofrom){
        builder = MdStockSnapshotTempletBO_PRO.MdStockSnapshotTempletBO.newBuilder();
        bo= new MdStockSnapshotTempletBO();
        setSecurity_code(bofrom.security_code);
        setExch(bofrom.exch);
        setDateTime(bofrom.dateTime);
        setOpen(bofrom.open);
        setPreClose(bofrom.preClose);
        setLastPrice(bofrom.lastPrice);
        setHigh(bofrom.high);
        setLow(bofrom.low);
        setBuy1Price(bofrom.buy1Price);
        setBuy1Qty(bofrom.buy1Qty);
        setBuy2Price(bofrom.buy2Price);
        setBuy2Qty(bofrom.buy2Qty);
        setBuy3Price(bofrom.buy3Price);
        setBuy3Qty(bofrom.buy3Qty);
        setBuy4Price(bofrom.buy4Price);
        setBuy4Qty(bofrom.buy4Qty);
        setBuy5Price(bofrom.buy5Price);
        setBuy5Qty(bofrom.buy5Qty);
        setSell1Price(bofrom.sell1Price);
        setSell1Qty(bofrom.sell1Qty);
        setSell2Price(bofrom.sell2Price);
        setSell2Qty(bofrom.sell2Qty);
        setSell3Price(bofrom.sell3Price);
        setSell3Qty(bofrom.sell3Qty);
        setSell4Price(bofrom.sell4Price);
        setSell4Qty(bofrom.sell4Qty);
        setSell5Price(bofrom.sell5Price);
        setSell5Qty(bofrom.sell5Qty);
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
    public MdStockSnapshotTempletBO getBo() { 
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

    public double getBuy1Price() {
        return builder.getBuy1Price();
    }

    public void setBuy1Price(double buy1Price) {
        bo.buy1Price = buy1Price;
        builder.setBuy1Price(buy1Price);
    }

    public long getBuy1Qty() {
        return builder.getBuy1Qty();
    }

    public void setBuy1Qty(long buy1Qty) {
        bo.buy1Qty = buy1Qty;
        builder.setBuy1Qty(buy1Qty);
    }

    public double getBuy2Price() {
        return builder.getBuy2Price();
    }

    public void setBuy2Price(double buy2Price) {
        bo.buy2Price = buy2Price;
        builder.setBuy2Price(buy2Price);
    }

    public long getBuy2Qty() {
        return builder.getBuy2Qty();
    }

    public void setBuy2Qty(long buy2Qty) {
        bo.buy2Qty = buy2Qty;
        builder.setBuy2Qty(buy2Qty);
    }

    public double getBuy3Price() {
        return builder.getBuy3Price();
    }

    public void setBuy3Price(double buy3Price) {
        bo.buy3Price = buy3Price;
        builder.setBuy3Price(buy3Price);
    }

    public long getBuy3Qty() {
        return builder.getBuy3Qty();
    }

    public void setBuy3Qty(long buy3Qty) {
        bo.buy3Qty = buy3Qty;
        builder.setBuy3Qty(buy3Qty);
    }

    public double getBuy4Price() {
        return builder.getBuy4Price();
    }

    public void setBuy4Price(double buy4Price) {
        bo.buy4Price = buy4Price;
        builder.setBuy4Price(buy4Price);
    }

    public long getBuy4Qty() {
        return builder.getBuy4Qty();
    }

    public void setBuy4Qty(long buy4Qty) {
        bo.buy4Qty = buy4Qty;
        builder.setBuy4Qty(buy4Qty);
    }

    public double getBuy5Price() {
        return builder.getBuy5Price();
    }

    public void setBuy5Price(double buy5Price) {
        bo.buy5Price = buy5Price;
        builder.setBuy5Price(buy5Price);
    }

    public long getBuy5Qty() {
        return builder.getBuy5Qty();
    }

    public void setBuy5Qty(long buy5Qty) {
        bo.buy5Qty = buy5Qty;
        builder.setBuy5Qty(buy5Qty);
    }

    public double getSell1Price() {
        return builder.getSell1Price();
    }

    public void setSell1Price(double sell1Price) {
        bo.sell1Price = sell1Price;
        builder.setSell1Price(sell1Price);
    }

    public long getSell1Qty() {
        return builder.getSell1Qty();
    }

    public void setSell1Qty(long sell1Qty) {
        bo.sell1Qty = sell1Qty;
        builder.setSell1Qty(sell1Qty);
    }

    public double getSell2Price() {
        return builder.getSell2Price();
    }

    public void setSell2Price(double sell2Price) {
        bo.sell2Price = sell2Price;
        builder.setSell2Price(sell2Price);
    }

    public long getSell2Qty() {
        return builder.getSell2Qty();
    }

    public void setSell2Qty(long sell2Qty) {
        bo.sell2Qty = sell2Qty;
        builder.setSell2Qty(sell2Qty);
    }

    public double getSell3Price() {
        return builder.getSell3Price();
    }

    public void setSell3Price(double sell3Price) {
        bo.sell3Price = sell3Price;
        builder.setSell3Price(sell3Price);
    }

    public long getSell3Qty() {
        return builder.getSell3Qty();
    }

    public void setSell3Qty(long sell3Qty) {
        bo.sell3Qty = sell3Qty;
        builder.setSell3Qty(sell3Qty);
    }

    public double getSell4Price() {
        return builder.getSell4Price();
    }

    public void setSell4Price(double sell4Price) {
        bo.sell4Price = sell4Price;
        builder.setSell4Price(sell4Price);
    }

    public long getSell4Qty() {
        return builder.getSell4Qty();
    }

    public void setSell4Qty(long sell4Qty) {
        bo.sell4Qty = sell4Qty;
        builder.setSell4Qty(sell4Qty);
    }

    public double getSell5Price() {
        return builder.getSell5Price();
    }

    public void setSell5Price(double sell5Price) {
        bo.sell5Price = sell5Price;
        builder.setSell5Price(sell5Price);
    }

    public long getSell5Qty() {
        return builder.getSell5Qty();
    }

    public void setSell5Qty(long sell5Qty) {
        bo.sell5Qty = sell5Qty;
        builder.setSell5Qty(sell5Qty);
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
        return "MdStockSnapshotTempletBO_OTW ["+
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
            "buy1Price = " + getBuy1Price() +"," +
            "buy1Qty = " + getBuy1Qty() +"," +
            "buy2Price = " + getBuy2Price() +"," +
            "buy2Qty = " + getBuy2Qty() +"," +
            "buy3Price = " + getBuy3Price() +"," +
            "buy3Qty = " + getBuy3Qty() +"," +
            "buy4Price = " + getBuy4Price() +"," +
            "buy4Qty = " + getBuy4Qty() +"," +
            "buy5Price = " + getBuy5Price() +"," +
            "buy5Qty = " + getBuy5Qty() +"," +
            "sell1Price = " + getSell1Price() +"," +
            "sell1Qty = " + getSell1Qty() +"," +
            "sell2Price = " + getSell2Price() +"," +
            "sell2Qty = " + getSell2Qty() +"," +
            "sell3Price = " + getSell3Price() +"," +
            "sell3Qty = " + getSell3Qty() +"," +
            "sell4Price = " + getSell4Price() +"," +
            "sell4Qty = " + getSell4Qty() +"," +
            "sell5Price = " + getSell5Price() +"," +
            "sell5Qty = " + getSell5Qty() +"," +
            "totalVolume = " + getTotalVolume() +"," +
            "totalAmont = " + getTotalAmont() +"," +
         "]";
    }
}
