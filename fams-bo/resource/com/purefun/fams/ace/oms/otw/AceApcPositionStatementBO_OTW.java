package com.purefun.fams.ace.oms.otw;

import com.purefun.fams.ace.oms.AceApcPositionStatementBO;
import com.purefun.fams.ace.oms.pro.AceApcPositionStatementBO_PRO;
import com.google.protobuf.InvalidProtocolBufferException;
import com.purefun.fams.core.bo.commom.ICommon_OTW;
import com.google.protobuf.Any;
import com.google.protobuf.util.Timestamps;

public class AceApcPositionStatementBO_OTW implements ICommon_OTW {
    AceApcPositionStatementBO_PRO.AceApcPositionStatementBO.Builder builder = null;
    AceApcPositionStatementBO bo = null;

    public AceApcPositionStatementBO_OTW() {
        builder = AceApcPositionStatementBO_PRO.AceApcPositionStatementBO.newBuilder();
        bo= new AceApcPositionStatementBO();
        builder.setUuid(bo.uuid);
        builder.setBoid(bo.boid);
    }

    public AceApcPositionStatementBO_OTW(byte[] message) throws InvalidProtocolBufferException {
        builder = AceApcPositionStatementBO_PRO.AceApcPositionStatementBO.newBuilder();
        bo= new AceApcPositionStatementBO();
        AceApcPositionStatementBO_PRO.AceApcPositionStatementBO receive = AceApcPositionStatementBO_PRO.AceApcPositionStatementBO.parseFrom(message);
        setUuid(receive.getUuid());
        setBoid(receive.getBoid());
    }

    public AceApcPositionStatementBO_OTW(AceApcPositionStatementBO bofrom){
        builder = AceApcPositionStatementBO_PRO.AceApcPositionStatementBO.newBuilder();
        bo= new AceApcPositionStatementBO();
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
    public AceApcPositionStatementBO getBo() { 
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
        return "AceApcPositionStatementBO_OTW ["+
            "uuid = " + getUuid() +"," +
            "boid = " + getBoid() +"," +
         "]";
    }
}
