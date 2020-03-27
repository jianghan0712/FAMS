package com.purefun.fams.core.bo.otw;

import com.purefun.fams.core.bo.TestBO;
import com.purefun.fams.core.bo.pro.TestBO_PRO;
import com.google.protobuf.InvalidProtocolBufferException;
import com.purefun.fams.core.bo.commom.ICommon_OTW;
import com.google.protobuf.Any;

public class TestBO_OTW implements ICommon_OTW {
    TestBO_PRO.TestBO.Builder builder = null;
    TestBO bo = null;

    public TestBO_OTW() {
        builder = TestBO_PRO.TestBO.newBuilder();
        bo= new TestBO();
        builder.setUuid(bo.uuid);
        builder.setBoid(bo.boid);
        builder.setDestination(bo.destination);
    }

    public TestBO_OTW(byte[] message) throws InvalidProtocolBufferException {
        builder = TestBO_PRO.TestBO.newBuilder();
        bo= new TestBO();
        TestBO_PRO.TestBO receive = TestBO_PRO.TestBO.parseFrom(message);
        setUsername(receive.getUsername());
        setAge(receive.getAge());
        setUuid(receive.getUuid());
        setBoid(receive.getBoid());
        setDestination(receive.getDestination());
    }

    public TestBO_OTW(TestBO bofrom){
        builder = TestBO_PRO.TestBO.newBuilder();
        bo= new TestBO();
        setUsername(bofrom.username);
        setAge(bofrom.age);
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
    public TestBO getBo() { 
        return bo;
    }

    public java.lang.String getUsername() {
        return builder.getUsername();
    }

    public void setUsername(java.lang.String username) {
        bo.username = username;
        builder.setUsername(username);
    }

    public int getAge() {
        return builder.getAge();
    }

    public void setAge(int age) {
        bo.age = age;
        builder.setAge(age);
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
        return "TestBO_OTW ["+
            "uuid = " + getUuid() +"," +
            "boid = " + getBoid() +"," +
            "destination = " + getDestination() +"," +
            "username = " + getUsername() +"," +
            "age = " + getAge() +"," +
         "]";
    }
}
