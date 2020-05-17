package com.purefun.fams.ace.oms.otw;

import com.purefun.fams.ace.oms.AceApcPositionBO;
import com.purefun.fams.ace.oms.pro.AceApcPositionBO_PRO;
import com.google.protobuf.InvalidProtocolBufferException;
import com.purefun.fams.core.bo.commom.ICommon_OTW;
import com.google.protobuf.Any;
import com.google.protobuf.util.Timestamps;

public class AceApcPositionBO_OTW implements ICommon_OTW {
    AceApcPositionBO_PRO.AceApcPositionBO.Builder builder = null;
    AceApcPositionBO bo = null;

    public AceApcPositionBO_OTW() {
        builder = AceApcPositionBO_PRO.AceApcPositionBO.newBuilder();
        bo= new AceApcPositionBO();
        builder.setUuid(bo.uuid);
        builder.setBoid(bo.boid);
    }

    public AceApcPositionBO_OTW(byte[] message) throws InvalidProtocolBufferException {
        builder = AceApcPositionBO_PRO.AceApcPositionBO.newBuilder();
        bo= new AceApcPositionBO();
        AceApcPositionBO_PRO.AceApcPositionBO receive = AceApcPositionBO_PRO.AceApcPositionBO.parseFrom(message);
        setUuid(receive.getUuid());
        setBoid(receive.getBoid());
    }

    public AceApcPositionBO_OTW(AceApcPositionBO bofrom){
        builder = AceApcPositionBO_PRO.AceApcPositionBO.newBuilder();
        bo= new AceApcPositionBO();
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
    public AceApcPositionBO getBo() { 
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
        return "AceApcPositionBO_OTW ["+
            "uuid = " + getUuid() +"," +
            "boid = " + getBoid() +"," +
         "]";
    }
}
