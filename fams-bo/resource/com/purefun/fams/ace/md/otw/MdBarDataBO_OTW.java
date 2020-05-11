package com.purefun.fams.ace.md.otw;

import com.purefun.fams.ace.md.MdBarDataBO;
import com.purefun.fams.ace.md.pro.MdBarDataBO_PRO;
import com.google.protobuf.InvalidProtocolBufferException;
import com.purefun.fams.core.bo.commom.ICommon_OTW;
import com.google.protobuf.Any;
import com.google.protobuf.util.Timestamps;

public class MdBarDataBO_OTW implements ICommon_OTW {
    MdBarDataBO_PRO.MdBarDataBO.Builder builder = null;
    MdBarDataBO bo = null;

    public MdBarDataBO_OTW() {
        builder = MdBarDataBO_PRO.MdBarDataBO.newBuilder();
        bo= new MdBarDataBO();
        builder.setUuid(bo.uuid);
        builder.setBoid(bo.boid);
    }

    public MdBarDataBO_OTW(byte[] message) throws InvalidProtocolBufferException {
        builder = MdBarDataBO_PRO.MdBarDataBO.newBuilder();
        bo= new MdBarDataBO();
        MdBarDataBO_PRO.MdBarDataBO receive = MdBarDataBO_PRO.MdBarDataBO.parseFrom(message);
        setSecurity_code(receive.getSecurityCode());
        setExch(receive.getExch());
        setSecurity_type(receive.getSecurityType());
        setDate(receive.getDate());
        setOpen(receive.getOpen());
        setHigh(receive.getHigh());
        setLow(receive.getLow());
        setClose(receive.getClose());
        setVolume(receive.getVolume());
        setChange(receive.getChange());
        setPre_close(receive.getPreClose());
        setPct_chg(receive.getPctChg());
        setAmount(receive.getAmount());
        setUuid(receive.getUuid());
        setBoid(receive.getBoid());
    }

    public MdBarDataBO_OTW(MdBarDataBO bofrom){
        builder = MdBarDataBO_PRO.MdBarDataBO.newBuilder();
        bo= new MdBarDataBO();
        setSecurity_code(bofrom.security_code);
        setExch(bofrom.exch);
        setSecurity_type(bofrom.security_type);
        setDate(bofrom.date);
        setOpen(bofrom.open);
        setHigh(bofrom.high);
        setLow(bofrom.low);
        setClose(bofrom.close);
        setVolume(bofrom.volume);
        setChange(bofrom.change);
        setPre_close(bofrom.pre_close);
        setPct_chg(bofrom.pct_chg);
        setAmount(bofrom.amount);
        setUuid(bofrom.uuid);
        setBoid(bofrom.boid);
    }

    public byte[] serial() {
        return builder.build().toByteArray();
    }

    @Override
    public com.google.protobuf.GeneratedMessageV3.Builder getBuilder() { 
        return builder;
    }

    @Override
    public MdBarDataBO getBo() { 
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

    public java.lang.String getDate() {
        return builder.getDate();
    }

    public void setDate(java.lang.String date) {
        bo.date = date;
        builder.setDate(date);
    }

    public double getOpen() {
        return builder.getOpen();
    }

    public void setOpen(double open) {
        bo.open = open;
        builder.setOpen(open);
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

    public double getClose() {
        return builder.getClose();
    }

    public void setClose(double close) {
        bo.close = close;
        builder.setClose(close);
    }

    public long getVolume() {
        return builder.getVolume();
    }

    public void setVolume(long volume) {
        bo.volume = volume;
        builder.setVolume(volume);
    }

    public double getChange() {
        return builder.getChange();
    }

    public void setChange(double change) {
        bo.change = change;
        builder.setChange(change);
    }

    public double getPre_close() {
        return builder.getPreClose();
    }

    public void setPre_close(double pre_close) {
        bo.pre_close = pre_close;
        builder.setPreClose(pre_close);
    }

    public double getPct_chg() {
        return builder.getPctChg();
    }

    public void setPct_chg(double pct_chg) {
        bo.pct_chg = pct_chg;
        builder.setPctChg(pct_chg);
    }

    public double getAmount() {
        return builder.getAmount();
    }

    public void setAmount(double amount) {
        bo.amount = amount;
        builder.setAmount(amount);
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

    public String toString() {
        return "MdBarDataBO_OTW ["+
            "uuid = " + getUuid() +"," +
            "boid = " + getBoid() +"," +
            "security_code = " + getSecurity_code() +"," +
            "exch = " + getExch() +"," +
            "security_type = " + getSecurity_type() +"," +
            "date = " + getDate() +"," +
            "open = " + getOpen() +"," +
            "high = " + getHigh() +"," +
            "low = " + getLow() +"," +
            "close = " + getClose() +"," +
            "volume = " + getVolume() +"," +
            "change = " + getChange() +"," +
            "pre_close = " + getPre_close() +"," +
            "pct_chg = " + getPct_chg() +"," +
            "amount = " + getAmount() +"," +
         "]";
    }
}
