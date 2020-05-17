package com.purefun.fams.queen.rds.otw;

import com.purefun.fams.queen.rds.QueenRdsStockBO;
import com.purefun.fams.queen.rds.pro.QueenRdsStockBO_PRO;
import com.google.protobuf.InvalidProtocolBufferException;
import com.purefun.fams.core.bo.commom.ICommon_OTW;
import com.google.protobuf.Any;
import com.google.protobuf.util.Timestamps;

public class QueenRdsStockBO_OTW implements ICommon_OTW {
    QueenRdsStockBO_PRO.QueenRdsStockBO.Builder builder = null;
    QueenRdsStockBO bo = null;

    public QueenRdsStockBO_OTW() {
        builder = QueenRdsStockBO_PRO.QueenRdsStockBO.newBuilder();
        bo= new QueenRdsStockBO();
        builder.setUuid(bo.uuid);
        builder.setBoid(bo.boid);
    }

    public QueenRdsStockBO_OTW(byte[] message) throws InvalidProtocolBufferException {
        builder = QueenRdsStockBO_PRO.QueenRdsStockBO.newBuilder();
        bo= new QueenRdsStockBO();
        QueenRdsStockBO_PRO.QueenRdsStockBO receive = QueenRdsStockBO_PRO.QueenRdsStockBO.parseFrom(message);
        setUuid(receive.getUuid());
        setBoid(receive.getBoid());
    }

    public QueenRdsStockBO_OTW(QueenRdsStockBO bofrom){
        builder = QueenRdsStockBO_PRO.QueenRdsStockBO.newBuilder();
        bo= new QueenRdsStockBO();
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
    public QueenRdsStockBO getBo() { 
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
        return "QueenRdsStockBO_OTW ["+
            "uuid = " + getUuid() +"," +
            "boid = " + getBoid() +"," +
         "]";
    }
}
