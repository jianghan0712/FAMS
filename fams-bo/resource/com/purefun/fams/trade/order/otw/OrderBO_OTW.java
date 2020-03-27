package com.purefun.fams.trade.order.otw;

import com.purefun.fams.trade.order.OrderBO;
import com.purefun.fams.trade.order.pro.OrderBO_PRO;
import com.google.protobuf.InvalidProtocolBufferException;
import com.purefun.fams.core.bo.commom.ICommon_OTW;
import com.google.protobuf.Any;

public class OrderBO_OTW implements ICommon_OTW {
    OrderBO_PRO.OrderBO.Builder builder = null;
    OrderBO bo = null;

    public OrderBO_OTW() {
        builder = OrderBO_PRO.OrderBO.newBuilder();
        bo= new OrderBO();
        builder.setUuid(bo.uuid);
        builder.setBoid(bo.boid);
        builder.setDestination(bo.destination);
    }

    public OrderBO_OTW(byte[] message) throws InvalidProtocolBufferException {
        builder = OrderBO_PRO.OrderBO.newBuilder();
        bo= new OrderBO();
        OrderBO_PRO.OrderBO receive = OrderBO_PRO.OrderBO.parseFrom(message);
        setAccount(receive.getAccount());
        setSecurity_code(receive.getSecurityCode());
        setExch(receive.getExch());
        setSecurity_type(receive.getSecurityType());
        setDirection(receive.getDirection());
        setPrice(receive.getPrice());
        setVolume(receive.getVolume());
        setWithdrawFlag(receive.getWithdrawFlag());
        setUuid(receive.getUuid());
        setBoid(receive.getBoid());
        setDestination(receive.getDestination());
    }

    public OrderBO_OTW(OrderBO bofrom){
        builder = OrderBO_PRO.OrderBO.newBuilder();
        bo= new OrderBO();
        setAccount(bofrom.account);
        setSecurity_code(bofrom.security_code);
        setExch(bofrom.exch);
        setSecurity_type(bofrom.security_type);
        setDirection(bofrom.direction);
        setPrice(bofrom.price);
        setVolume(bofrom.volume);
        setWithdrawFlag(bofrom.withdrawFlag);
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
    public OrderBO getBo() { 
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

    public double getPrice() {
        return builder.getPrice();
    }

    public void setPrice(double price) {
        bo.price = price;
        builder.setPrice(price);
    }

    public long getVolume() {
        return builder.getVolume();
    }

    public void setVolume(long volume) {
        bo.volume = volume;
        builder.setVolume(volume);
    }

    public boolean getWithdrawFlag() {
        return builder.getWithdrawFlag();
    }

    public void setWithdrawFlag(boolean withdrawFlag) {
        bo.withdrawFlag = withdrawFlag;
        builder.setWithdrawFlag(withdrawFlag);
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
        return "OrderBO_OTW ["+
            "uuid = " + getUuid() +"," +
            "boid = " + getBoid() +"," +
            "destination = " + getDestination() +"," +
            "account = " + getAccount() +"," +
            "security_code = " + getSecurity_code() +"," +
            "exch = " + getExch() +"," +
            "security_type = " + getSecurity_type() +"," +
            "direction = " + getDirection() +"," +
            "price = " + getPrice() +"," +
            "volume = " + getVolume() +"," +
            "withdrawFlag = " + getWithdrawFlag() +"," +
         "]";
    }
}
