package com.purefun.fams.ace.oms.otw;

import com.purefun.fams.ace.oms.AceApcCashStatementBO;
import com.purefun.fams.ace.oms.pro.AceApcCashStatementBO_PRO;
import com.google.protobuf.InvalidProtocolBufferException;
import com.purefun.fams.core.bo.commom.ICommon_OTW;
import com.google.protobuf.Any;
import com.google.protobuf.util.Timestamps;

public class AceApcCashStatementBO_OTW implements ICommon_OTW {
    AceApcCashStatementBO_PRO.AceApcCashStatementBO.Builder builder = null;
    AceApcCashStatementBO bo = null;

    public AceApcCashStatementBO_OTW() {
        builder = AceApcCashStatementBO_PRO.AceApcCashStatementBO.newBuilder();
        bo= new AceApcCashStatementBO();
        builder.setUuid(bo.uuid);
        builder.setBoid(bo.boid);
    }

    public AceApcCashStatementBO_OTW(byte[] message) throws InvalidProtocolBufferException {
        builder = AceApcCashStatementBO_PRO.AceApcCashStatementBO.newBuilder();
        bo= new AceApcCashStatementBO();
        AceApcCashStatementBO_PRO.AceApcCashStatementBO receive = AceApcCashStatementBO_PRO.AceApcCashStatementBO.parseFrom(message);
        setUuid(receive.getUuid());
        setBoid(receive.getBoid());
    }

    public AceApcCashStatementBO_OTW(AceApcCashStatementBO bofrom){
        builder = AceApcCashStatementBO_PRO.AceApcCashStatementBO.newBuilder();
        bo= new AceApcCashStatementBO();
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
    public AceApcCashStatementBO getBo() { 
        return bo;
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
        return "AceApcCashStatementBO_OTW ["+
            "uuid = " + getUuid() +"," +
            "boid = " + getBoid() +"," +
         "]";
    }
}
