from src.core.common.ICommon_OTW import ICommon_OTW
from src.com.purefun.fams.core.bo.pro import TestBO2_pb2
from src.com.purefun.fams.core.bo.model.TestBO2 import TestBO2

class TestBO2_OTW(ICommon_OTW):

    def __init__(self, byteMsg = None):
        self._bo_pro = TestBO2_pb2.TestBO2()
        self._bo = TestBO2()

        if byteMsg is not None:
            self._bo_pro.ParseFromString(byteMsg)
            self.__setDataFromPB()
        else:
            self.__setDataFromBO()

    def __setDataFromBO(self):
        self._bo_pro.workid = self._bo.workid
        self._bo_pro.homeaddress = self._bo.homeaddress
        self._bo_pro.uuid = self._bo.uuid
        self._bo_pro.boid = self._bo.boid
        self._bo_pro.destination = self._bo.destination

    def __setDataFromPB(self):
        self._bo.workid = self._bo_pro.workid
        self._bo.homeaddress = self._bo_pro.homeaddress
        self._bo.uuid = self._bo_pro.uuid
        self._bo.boid = self._bo_pro.boid
        self._bo.destination = self._bo_pro.destination

    def getBO(self):
        return self._bo

    def getProBO(self):
        return self._bo_pro

    def getWorkid(self):
        return self._bo.workid

    def setWorkid(self, workid):
        self._bo.workid = workid
        self._bo_pro.workid = workid

    def getHomeaddress(self):
        return self._bo.homeaddress

    def setHomeaddress(self, homeaddress):
        self._bo.homeaddress = homeaddress
        self._bo_pro.homeaddress = homeaddress

    def getUuid(self):
        return self._bo.uuid

    def setUuid(self, uuid):
        self._bo.uuid = uuid
        self._bo_pro.uuid = uuid

    def getBoid(self):
        return self._bo.boid

    def setBoid(self, boid):
        self._bo.boid = boid
        self._bo_pro.boid = boid

    def getDestination(self):
        return self._bo.destination

    def setDestination(self, destination):
        self._bo.destination = destination
        self._bo_pro.destination = destination

    def toString(self):
        return "TestBO2_OTW ["+"workid = " + self.getWorkid() +"," +"homeaddress = " + self.getHomeaddress() +"," +"uuid = " + self.getUuid() +"," +"boid = " + str(self.getBoid()) +"," +"destination = " + self.getDestination() +"," +"]"
