package com.purefun.fams.core.bo.otw;

import com.purefun.fams.core.bo.BaseBO;
import com.purefun.fams.core.bo.pro.BaseBO_PRO;
import com.google.protobuf.InvalidProtocolBufferException;
import com.purefun.fams.core.bo.commom.ICommon_OTW;
import com.google.protobuf.Any;

public class BaseBO_OTW implements ICommon_OTW {
    BaseBO_PRO.BaseBO.Builder builder = null;
    BaseBO bo = null;

    public BaseBO_OTW() {
        builder = BaseBO_PRO.BaseBO.newBuilder();
        bo= new BaseBO();
        builder.setUuid(bo.uuid);
        builder.setBoid(bo.boid);
    }

    public BaseBO_OTW(byte[] message) throws InvalidProtocolBufferException {
        builder = BaseBO_PRO.BaseBO.newBuilder();
        bo= new BaseBO();
        BaseBO_PRO.BaseBO receive = BaseBO_PRO.BaseBO.parseFrom(message);
        setUuid(receive.getUuid());
        setBoid(receive.getBoid());
    }

    public BaseBO_OTW(BaseBO bofrom){
        builder = BaseBO_PRO.BaseBO.newBuilder();
        bo= new BaseBO();
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
    public BaseBO getBo() { 
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
        return "BaseBO_OTW ["+
            "uuid = " + getUuid() +"," +
            "boid = " + getBoid() +"," +
         "]";
    }
}
