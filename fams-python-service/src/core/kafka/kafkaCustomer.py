# coding=utf-8
import json
from kafka import KafkaConsumer

from src.com.purefun.fams.core.bo.otw.TestBO_OTW import TestBO_OTW


def consumer():
    com = KafkaConsumer('fams.core.rpc.testone', bootstrap_servers=['localhost:9092'])
    for msg in com:
        #         value=json.loads(msg.value)
        bo_otw = TestBO_OTW(msg.value)
        rec = "%s:%d:%d: key=%s value=%s" % (msg.topic, msg.partition, msg.offset, msg.key, bo_otw.getAge())
        print(rec)


if __name__ == "__main__":
    consumer()
