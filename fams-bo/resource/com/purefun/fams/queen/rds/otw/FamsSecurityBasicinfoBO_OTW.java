package com.purefun.fams.queen.rds.otw;

import com.purefun.fams.queen.rds.FamsSecurityBasicinfoBO;
import com.purefun.fams.queen.rds.pro.FamsSecurityBasicinfoBO_PRO;
import com.google.protobuf.InvalidProtocolBufferException;
import com.purefun.fams.core.bo.commom.ICommon_OTW;
import com.google.protobuf.Any;
import com.google.protobuf.util.Timestamps;

public class FamsSecurityBasicinfoBO_OTW implements ICommon_OTW {
    FamsSecurityBasicinfoBO_PRO.FamsSecurityBasicinfoBO.Builder builder = null;
    FamsSecurityBasicinfoBO bo = null;

    public FamsSecurityBasicinfoBO_OTW() {
        builder = FamsSecurityBasicinfoBO_PRO.FamsSecurityBasicinfoBO.newBuilder();
        bo= new FamsSecurityBasicinfoBO();
        builder.setUuid(bo.uuid);
        builder.setBoid(bo.boid);
    }

    public FamsSecurityBasicinfoBO_OTW(byte[] message) throws InvalidProtocolBufferException {
        builder = FamsSecurityBasicinfoBO_PRO.FamsSecurityBasicinfoBO.newBuilder();
        bo= new FamsSecurityBasicinfoBO();
        FamsSecurityBasicinfoBO_PRO.FamsSecurityBasicinfoBO receive = FamsSecurityBasicinfoBO_PRO.FamsSecurityBasicinfoBO.parseFrom(message);
        setId(receive.getId());
        setExch(receive.getExch());
        setSecurityId(receive.getSecurityId());
        setTsId(receive.getTsId());
        setExchangeId(receive.getExchangeId());
        setSecurityName(receive.getSecurityName());
        setArea(receive.getArea());
        setIndustry(receive.getIndustry());
        setMarketType(receive.getMarketType());
        setCurrency(receive.getCurrency());
        setStatus(receive.getStatus());
        setListDate(receive.getListDate());
        setHtFlag(receive.getHtFlag());
        setModifyTime(new java.util.Date(receive.getModifyTime().getSeconds() * 1000));
        setCreateTime(new java.util.Date(receive.getCreateTime().getSeconds() * 1000));
        setUuid(receive.getUuid());
        setBoid(receive.getBoid());
    }

    public FamsSecurityBasicinfoBO_OTW(FamsSecurityBasicinfoBO bofrom){
        builder = FamsSecurityBasicinfoBO_PRO.FamsSecurityBasicinfoBO.newBuilder();
        bo= new FamsSecurityBasicinfoBO();
        setId(bofrom.id);
        setExch(bofrom.exch);
        setSecurityId(bofrom.securityId);
        setTsId(bofrom.tsId);
        setExchangeId(bofrom.exchangeId);
        setSecurityName(bofrom.securityName);
        setArea(bofrom.area);
        setIndustry(bofrom.industry);
        setMarketType(bofrom.marketType);
        setCurrency(bofrom.currency);
        setStatus(bofrom.status);
        setListDate(bofrom.listDate);
        setHtFlag(bofrom.htFlag);
        setModifyTime(bofrom.modifyTime);
        setCreateTime(bofrom.createTime);
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
    public FamsSecurityBasicinfoBO getBo() { 
        return bo;
    }

    public java.lang.Long getId() {
        return builder.getId();
    }

    public void setId(java.lang.Long id) {
        bo.id = id;
        builder.setId(id== null ? 0: id);
    }

    public java.lang.String getExch() {
        return builder.getExch();
    }

    public void setExch(java.lang.String exch) {
        bo.exch = exch;
        builder.setExch(exch);
    }

    public java.lang.String getSecurityId() {
        return builder.getSecurityId();
    }

    public void setSecurityId(java.lang.String securityId) {
        bo.securityId = securityId;
        builder.setSecurityId(securityId);
    }

    public java.lang.String getTsId() {
        return builder.getTsId();
    }

    public void setTsId(java.lang.String tsId) {
        bo.tsId = tsId;
        builder.setTsId(tsId);
    }

    public java.lang.String getExchangeId() {
        return builder.getExchangeId();
    }

    public void setExchangeId(java.lang.String exchangeId) {
        bo.exchangeId = exchangeId;
        builder.setExchangeId(exchangeId);
    }

    public java.lang.String getSecurityName() {
        return builder.getSecurityName();
    }

    public void setSecurityName(java.lang.String securityName) {
        bo.securityName = securityName;
        builder.setSecurityName(securityName);
    }

    public java.lang.String getArea() {
        return builder.getArea();
    }

    public void setArea(java.lang.String area) {
        bo.area = area;
        builder.setArea(area);
    }

    public java.lang.String getIndustry() {
        return builder.getIndustry();
    }

    public void setIndustry(java.lang.String industry) {
        bo.industry = industry;
        builder.setIndustry(industry);
    }

    public java.lang.String getMarketType() {
        return builder.getMarketType();
    }

    public void setMarketType(java.lang.String marketType) {
        bo.marketType = marketType;
        builder.setMarketType(marketType);
    }

    public java.lang.String getCurrency() {
        return builder.getCurrency();
    }

    public void setCurrency(java.lang.String currency) {
        bo.currency = currency;
        builder.setCurrency(currency);
    }

    public java.lang.String getStatus() {
        return builder.getStatus();
    }

    public void setStatus(java.lang.String status) {
        bo.status = status;
        builder.setStatus(status);
    }

    public java.lang.String getListDate() {
        return builder.getListDate();
    }

    public void setListDate(java.lang.String listDate) {
        bo.listDate = listDate;
        builder.setListDate(listDate);
    }

    public java.lang.String getHtFlag() {
        return builder.getHtFlag();
    }

    public void setHtFlag(java.lang.String htFlag) {
        bo.htFlag = htFlag;
        builder.setHtFlag(htFlag);
    }

    public java.util.Date getModifyTime() {
        return new java.util.Date(builder.getModifyTime().getSeconds() * 1000);
    }

    public void setModifyTime(java.util.Date modifyTime) {
        bo.modifyTime = modifyTime;
        builder.setModifyTime(modifyTime== null ? Timestamps.fromMillis(new java.util.Date().getTime()): Timestamps.fromMillis(modifyTime.getTime()));
    }

    public java.util.Date getCreateTime() {
        return new java.util.Date(builder.getCreateTime().getSeconds() * 1000);
    }

    public void setCreateTime(java.util.Date createTime) {
        bo.createTime = createTime;
        builder.setCreateTime(createTime== null ? Timestamps.fromMillis(new java.util.Date().getTime()): Timestamps.fromMillis(createTime.getTime()));
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
        return "FamsSecurityBasicinfoBO_OTW ["+
            "uuid = " + getUuid() +"," +
            "boid = " + getBoid() +"," +
            "id = " + getId() +"," +
            "exch = " + getExch() +"," +
            "securityId = " + getSecurityId() +"," +
            "tsId = " + getTsId() +"," +
            "exchangeId = " + getExchangeId() +"," +
            "securityName = " + getSecurityName() +"," +
            "area = " + getArea() +"," +
            "industry = " + getIndustry() +"," +
            "marketType = " + getMarketType() +"," +
            "currency = " + getCurrency() +"," +
            "status = " + getStatus() +"," +
            "listDate = " + getListDate() +"," +
            "htFlag = " + getHtFlag() +"," +
            "modifyTime = " + getModifyTime() +"," +
            "createTime = " + getCreateTime() +"," +
         "]";
    }
}
