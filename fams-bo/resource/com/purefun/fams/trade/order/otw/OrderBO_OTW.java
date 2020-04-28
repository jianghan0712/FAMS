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
    }

    public OrderBO_OTW(byte[] message) throws InvalidProtocolBufferException {
        builder = OrderBO_PRO.OrderBO.newBuilder();
        bo= new OrderBO();
        OrderBO_PRO.OrderBO receive = OrderBO_PRO.OrderBO.parseFrom(message);
        setAccount(receive.getAccount());
        setSecurity_code(receive.getSecurityCode());
        setExch(receive.getExch());
        setSecurity_type(receive.getSecurityType());
        setTradeDate(receive.getTradeDate());
        setOrderId(receive.getOrderId());
        setOrderStatus(receive.getOrderStatus());
        setOrderRejectedReson(receive.getOrderRejectedReson());
        setOrderType(receive.getOrderType());
        setOrderPrice(receive.getOrderPrice());
        setOrderVolume(receive.getOrderVolume());
        setDirection(receive.getDirection());
        setExecStatus(receive.getExecStatus());
        setWithdrawFlag(receive.getWithdrawFlag());
        setWithdrawVolume(receive.getWithdrawVolume());
        setUuid(receive.getUuid());
        setBoid(receive.getBoid());
    }

    public OrderBO_OTW(OrderBO bofrom){
        builder = OrderBO_PRO.OrderBO.newBuilder();
        bo= new OrderBO();
        setAccount(bofrom.account);
        setSecurity_code(bofrom.security_code);
        setExch(bofrom.exch);
        setSecurity_type(bofrom.security_type);
        setTradeDate(bofrom.tradeDate);
        setOrderId(bofrom.orderId);
        setOrderStatus(bofrom.orderStatus);
        setOrderRejectedReson(bofrom.orderRejectedReson);
        setOrderType(bofrom.orderType);
        setOrderPrice(bofrom.orderPrice);
        setOrderVolume(bofrom.orderVolume);
        setDirection(bofrom.direction);
        setExecStatus(bofrom.execStatus);
        setWithdrawFlag(bofrom.withdrawFlag);
        setWithdrawVolume(bofrom.withdrawVolume);
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

    public java.lang.String getTradeDate() {
        return builder.getTradeDate();
    }

    public void setTradeDate(java.lang.String tradeDate) {
        bo.tradeDate = tradeDate;
        builder.setTradeDate(tradeDate);
    }

    public java.lang.String getOrderId() {
        return builder.getOrderId();
    }

    public void setOrderId(java.lang.String orderId) {
        bo.orderId = orderId;
        builder.setOrderId(orderId);
    }

    public java.lang.String getOrderStatus() {
        return builder.getOrderStatus();
    }

    public void setOrderStatus(java.lang.String orderStatus) {
        bo.orderStatus = orderStatus;
        builder.setOrderStatus(orderStatus);
    }

    public java.lang.String getOrderRejectedReson() {
        return builder.getOrderRejectedReson();
    }

    public void setOrderRejectedReson(java.lang.String orderRejectedReson) {
        bo.orderRejectedReson = orderRejectedReson;
        builder.setOrderRejectedReson(orderRejectedReson);
    }

    public java.lang.String getOrderType() {
        return builder.getOrderType();
    }

    public void setOrderType(java.lang.String orderType) {
        bo.orderType = orderType;
        builder.setOrderType(orderType);
    }

    public double getOrderPrice() {
        return builder.getOrderPrice();
    }

    public void setOrderPrice(double orderPrice) {
        bo.orderPrice = orderPrice;
        builder.setOrderPrice(orderPrice);
    }

    public long getOrderVolume() {
        return builder.getOrderVolume();
    }

    public void setOrderVolume(long orderVolume) {
        bo.orderVolume = orderVolume;
        builder.setOrderVolume(orderVolume);
    }

    public java.lang.String getDirection() {
        return builder.getDirection();
    }

    public void setDirection(java.lang.String direction) {
        bo.direction = direction;
        builder.setDirection(direction);
    }

    public java.lang.String getExecStatus() {
        return builder.getExecStatus();
    }

    public void setExecStatus(java.lang.String execStatus) {
        bo.execStatus = execStatus;
        builder.setExecStatus(execStatus);
    }

    public boolean getWithdrawFlag() {
        return builder.getWithdrawFlag();
    }

    public void setWithdrawFlag(boolean withdrawFlag) {
        bo.withdrawFlag = withdrawFlag;
        builder.setWithdrawFlag(withdrawFlag);
    }

    public long getWithdrawVolume() {
        return builder.getWithdrawVolume();
    }

    public void setWithdrawVolume(long withdrawVolume) {
        bo.withdrawVolume = withdrawVolume;
        builder.setWithdrawVolume(withdrawVolume);
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
        return "OrderBO_OTW ["+
            "uuid = " + getUuid() +"," +
            "boid = " + getBoid() +"," +
            "account = " + getAccount() +"," +
            "security_code = " + getSecurity_code() +"," +
            "exch = " + getExch() +"," +
            "security_type = " + getSecurity_type() +"," +
            "tradeDate = " + getTradeDate() +"," +
            "orderId = " + getOrderId() +"," +
            "orderStatus = " + getOrderStatus() +"," +
            "orderRejectedReson = " + getOrderRejectedReson() +"," +
            "orderType = " + getOrderType() +"," +
            "orderPrice = " + getOrderPrice() +"," +
            "orderVolume = " + getOrderVolume() +"," +
            "direction = " + getDirection() +"," +
            "execStatus = " + getExecStatus() +"," +
            "withdrawFlag = " + getWithdrawFlag() +"," +
            "withdrawVolume = " + getWithdrawVolume() +"," +
         "]";
    }
}
