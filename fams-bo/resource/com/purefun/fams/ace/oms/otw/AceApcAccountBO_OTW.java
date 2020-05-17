package com.purefun.fams.ace.oms.otw;

import com.purefun.fams.ace.oms.AceApcAccountBO;
import com.purefun.fams.ace.oms.pro.AceApcAccountBO_PRO;
import com.google.protobuf.InvalidProtocolBufferException;
import com.purefun.fams.core.bo.commom.ICommon_OTW;
import com.google.protobuf.Any;
import com.google.protobuf.util.Timestamps;

public class AceApcAccountBO_OTW implements ICommon_OTW {
    AceApcAccountBO_PRO.AceApcAccountBO.Builder builder = null;
    AceApcAccountBO bo = null;

    public AceApcAccountBO_OTW() {
        builder = AceApcAccountBO_PRO.AceApcAccountBO.newBuilder();
        bo= new AceApcAccountBO();
        builder.setUuid(bo.uuid);
        builder.setBoid(bo.boid);
    }

    public AceApcAccountBO_OTW(byte[] message) throws InvalidProtocolBufferException {
        builder = AceApcAccountBO_PRO.AceApcAccountBO.newBuilder();
        bo= new AceApcAccountBO();
        AceApcAccountBO_PRO.AceApcAccountBO receive = AceApcAccountBO_PRO.AceApcAccountBO.parseFrom(message);
        setUuid(receive.getUuid());
        setBoid(receive.getBoid());
    }

    public AceApcAccountBO_OTW(AceApcAccountBO bofrom){
        builder = AceApcAccountBO_PRO.AceApcAccountBO.newBuilder();
        bo= new AceApcAccountBO();
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
    public AceApcAccountBO getBo() { 
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
        return "AceApcAccountBO_OTW ["+
            "uuid = " + getUuid() +"," +
            "boid = " + getBoid() +"," +
         "]";
    }
}
