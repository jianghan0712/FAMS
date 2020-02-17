package com.purefun.fams.core.bo.otw;

import com.purefun.fams.core.bo.TestBO2;
import com.purefun.fams.core.bo.pro.TestBO2_PRO;
import com.google.protobuf.InvalidProtocolBufferException;
import com.purefun.fams.core.bo.commom.ICommon_OTW;
import com.google.protobuf.Any;

public class TestBO2_OTW implements ICommon_OTW {
    TestBO2_PRO.TestBO2.Builder builder = null;
    TestBO2 bo = null;

    public TestBO2_OTW() {
        builder = TestBO2_PRO.TestBO2.newBuilder();
        bo= new TestBO2();
        builder.setUuid(bo.uuid);
        builder.setBoid(bo.boid);
        builder.setDestination(bo.destination);
    }

    public TestBO2_OTW(byte[] message) throws InvalidProtocolBufferException {
        builder = TestBO2_PRO.TestBO2.newBuilder();
        bo= new TestBO2();
        TestBO2_PRO.TestBO2 receive = TestBO2_PRO.TestBO2.parseFrom(message);
        setWorkid(receive.getWorkid());
        setHomeaddress(receive.getHomeaddress());
        setUuid(receive.getUuid());
        setBoid(receive.getBoid());
        setDestination(receive.getDestination());
    }

    public TestBO2_OTW(TestBO2 bofrom){
        builder = TestBO2_PRO.TestBO2.newBuilder();
        bo= new TestBO2();
        setWorkid(bofrom.workid);
        setHomeaddress(bofrom.homeaddress);
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
    public TestBO2 getBo() { 
        return bo;
    }

    public java.lang.String getWorkid() {
        return builder.getWorkid();
    }

    public void setWorkid(java.lang.String workid) {
        bo.workid = workid;
        builder.setWorkid(workid);
    }

    public java.lang.String getHomeaddress() {
        return builder.getHomeaddress();
    }

    public void setHomeaddress(java.lang.String homeaddress) {
        bo.homeaddress = homeaddress;
        builder.setHomeaddress(homeaddress);
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
        return "TestBO2_OTW ["+
            "uuid = " + getUuid() +"," +
            "boid = " + getBoid() +"," +
            "destination = " + getDestination() +"," +
            "workid = " + getWorkid() +"," +
            "homeaddress = " + getHomeaddress() +"," +
         "]";
    }
}
