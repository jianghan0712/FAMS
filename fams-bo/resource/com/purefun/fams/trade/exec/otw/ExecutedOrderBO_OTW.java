package com.purefun.fams.trade.exec.otw;

import com.purefun.fams.trade.exec.ExecutedOrderBO;
import com.purefun.fams.trade.exec.pro.ExecutedOrderBO_PRO;
import com.google.protobuf.InvalidProtocolBufferException;
import com.purefun.fams.core.bo.commom.ICommon_OTW;
import com.google.protobuf.Any;

public class ExecutedOrderBO_OTW implements ICommon_OTW {
    ExecutedOrderBO_PRO.ExecutedOrderBO.Builder builder = null;
    ExecutedOrderBO bo = null;

    public ExecutedOrderBO_OTW() {
        builder = ExecutedOrderBO_PRO.ExecutedOrderBO.newBuilder();
        bo= new ExecutedOrderBO();
        builder.setUuid(bo.uuid);
        builder.setBoid(bo.boid);
        builder.setDestination(bo.destination);
    }

    public ExecutedOrderBO_OTW(byte[] message) throws InvalidProtocolBufferException {
        builder = ExecutedOrderBO_PRO.ExecutedOrderBO.newBuilder();
        bo= new ExecutedOrderBO();
        ExecutedOrderBO_PRO.ExecutedOrderBO receive = ExecutedOrderBO_PRO.ExecutedOrderBO.parseFrom(message);
        setAccount(receive.getAccount());
        setSecurity_code(receive.getSecurityCode());
        setExch(receive.getExch());
        setSecurity_type(receive.getSecurityType());
        setDirection(receive.getDirection());
        setOrderId(receive.getOrderId());
        setTradeDate(receive.getTradeDate());
        setExecuteId(receive.getExecuteId());
        setExecutePrice(receive.getExecutePrice());
        setExecuteVolume(receive.getExecuteVolume());
        setUuid(receive.getUuid());
        setBoid(receive.getBoid());
        setDestination(receive.getDestination());
    }

    public ExecutedOrderBO_OTW(ExecutedOrderBO bofrom){
        builder = ExecutedOrderBO_PRO.ExecutedOrderBO.newBuilder();
        bo= new ExecutedOrderBO();
        setAccount(bofrom.account);
        setSecurity_code(bofrom.security_code);
        setExch(bofrom.exch);
        setSecurity_type(bofrom.security_type);
        setDirection(bofrom.direction);
        setOrderId(bofrom.orderId);
        setTradeDate(bofrom.tradeDate);
        setExecuteId(bofrom.executeId);
        setExecutePrice(bofrom.executePrice);
        setExecuteVolume(bofrom.executeVolume);
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
    public ExecutedOrderBO getBo() { 
        return bo;
    }

    public java.lang.String getAccount() {
        return builder.getAccount();
    }

    public void setAccount(java.lang.String account) {
        bo.account = account;
        builder.setAccount(account);
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

    public java.lang.String getDirection() {
        return builder.getDirection();
    }

    public void setDirection(java.lang.String direction) {
        bo.direction = direction;
        builder.setDirection(direction);
    }

    public java.lang.String getOrderId() {
        return builder.getOrderId();
    }

    public void setOrderId(java.lang.String orderId) {
        bo.orderId = orderId;
        builder.setOrderId(orderId);
    }

    public java.lang.String getTradeDate() {
        return builder.getTradeDate();
    }

    public void setTradeDate(java.lang.String tradeDate) {
        bo.tradeDate = tradeDate;
        builder.setTradeDate(tradeDate);
    }

    public java.lang.String getExecuteId() {
        return builder.getExecuteId();
    }

    public void setExecuteId(java.lang.String executeId) {
        bo.executeId = executeId;
        builder.setExecuteId(executeId);
    }

    public double getExecutePrice() {
        return builder.getExecutePrice();
    }

    public void setExecutePrice(double executePrice) {
        bo.executePrice = executePrice;
        builder.setExecutePrice(executePrice);
    }

    public long getExecuteVolume() {
        return builder.getExecuteVolume();
    }

    public void setExecuteVolume(long executeVolume) {
        bo.executeVolume = executeVolume;
        builder.setExecuteVolume(executeVolume);
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
        return "ExecutedOrderBO_OTW ["+
            "uuid = " + getUuid() +"," +
            "boid = " + getBoid() +"," +
            "destination = " + getDestination() +"," +
            "account = " + getAccount() +"," +
            "security_code = " + getSecurity_code() +"," +
            "exch = " + getExch() +"," +
            "security_type = " + getSecurity_type() +"," +
            "direction = " + getDirection() +"," +
            "orderId = " + getOrderId() +"," +
            "tradeDate = " + getTradeDate() +"," +
            "executeId = " + getExecuteId() +"," +
            "executePrice = " + getExecutePrice() +"," +
            "executeVolume = " + getExecuteVolume() +"," +
         "]";
    }
}
