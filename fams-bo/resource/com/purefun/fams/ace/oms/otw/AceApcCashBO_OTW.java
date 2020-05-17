package com.purefun.fams.ace.oms.otw;

import com.purefun.fams.ace.oms.AceApcCashBO;
import com.purefun.fams.ace.oms.pro.AceApcCashBO_PRO;
import com.google.protobuf.InvalidProtocolBufferException;
import com.purefun.fams.core.bo.commom.ICommon_OTW;
import com.google.protobuf.Any;
import com.google.protobuf.util.Timestamps;

public class AceApcCashBO_OTW implements ICommon_OTW {
    AceApcCashBO_PRO.AceApcCashBO.Builder builder = null;
    AceApcCashBO bo = null;

    public AceApcCashBO_OTW() {
        builder = AceApcCashBO_PRO.AceApcCashBO.newBuilder();
        bo= new AceApcCashBO();
        builder.setUuid(bo.uuid);
        builder.setBoid(bo.boid);
    }

    public AceApcCashBO_OTW(byte[] message) throws InvalidProtocolBufferException {
        builder = AceApcCashBO_PRO.AceApcCashBO.newBuilder();
        bo= new AceApcCashBO();
        AceApcCashBO_PRO.AceApcCashBO receive = AceApcCashBO_PRO.AceApcCashBO.parseFrom(message);
        setUuid(receive.getUuid());
        setBoid(receive.getBoid());
    }

    public AceApcCashBO_OTW(AceApcCashBO bofrom){
        builder = AceApcCashBO_PRO.AceApcCashBO.newBuilder();
        bo= new AceApcCashBO();
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
    public AceApcCashBO getBo() { 
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
        return "AceApcCashBO_OTW ["+
            "uuid = " + getUuid() +"," +
            "boid = " + getBoid() +"," +
         "]";
    }
}
