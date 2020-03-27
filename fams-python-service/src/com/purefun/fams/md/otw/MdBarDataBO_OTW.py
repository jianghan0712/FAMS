from src.core.common.ICommon_OTW import ICommon_OTW
from src.com.purefun.fams.md.pro import MdBarDataBO_pb2
from src.com.purefun.fams.md.model.MdBarDataBO import MdBarDataBO

class MdBarDataBO_OTW(ICommon_OTW):

    def __init__(self, byteMsg = None):
        self._bo_pro = MdBarDataBO_pb2.MdBarDataBO()
        self._bo = MdBarDataBO()

        if byteMsg is not None:
            self._bo_pro.ParseFromString(byteMsg)
            self.__setDataFromPB()
        else:
            self.__setDataFromBO()

    def __setDataFromBO(self):
        self._bo_pro.security_code = self._bo.security_code
        self._bo_pro.exch = self._bo.exch
        self._bo_pro.security_type = self._bo.security_type
        self._bo_pro.date = self._bo.date
        self._bo_pro.open = self._bo.open
        self._bo_pro.high = self._bo.high
        self._bo_pro.low = self._bo.low
        self._bo_pro.close = self._bo.close
        self._bo_pro.volume = self._bo.volume
        self._bo_pro.change = self._bo.change
        self._bo_pro.pre_close = self._bo.pre_close
        self._bo_pro.pct_chg = self._bo.pct_chg
        self._bo_pro.amount = self._bo.amount
        self._bo_pro.uuid = self._bo.uuid
        self._bo_pro.boid = self._bo.boid
        self._bo_pro.destination = self._bo.destination

    def __setDataFromPB(self):
        self._bo.security_code = self._bo_pro.security_code
        self._bo.exch = self._bo_pro.exch
        self._bo.security_type = self._bo_pro.security_type
        self._bo.date = self._bo_pro.date
        self._bo.open = self._bo_pro.open
        self._bo.high = self._bo_pro.high
        self._bo.low = self._bo_pro.low
        self._bo.close = self._bo_pro.close
        self._bo.volume = self._bo_pro.volume
        self._bo.change = self._bo_pro.change
        self._bo.pre_close = self._bo_pro.pre_close
        self._bo.pct_chg = self._bo_pro.pct_chg
        self._bo.amount = self._bo_pro.amount
        self._bo.uuid = self._bo_pro.uuid
        self._bo.boid = self._bo_pro.boid
        self._bo.destination = self._bo_pro.destination

    def getBO(self):
        return self._bo

    def getProBO(self):
        return self._bo_pro

    def getSecurity_code(self):
        return self._bo.security_code

    def setSecurity_code(self, security_code):
        self._bo.security_code = security_code
        self._bo_pro.security_code = security_code

    def getExch(self):
        return self._bo.exch

    def setExch(self, exch):
        self._bo.exch = exch
        self._bo_pro.exch = exch

    def getSecurity_type(self):
        return self._bo.security_type

    def setSecurity_type(self, security_type):
        self._bo.security_type = security_type
        self._bo_pro.security_type = security_type

    def getDate(self):
        return self._bo.date

    def setDate(self, date):
        self._bo.date = date
        self._bo_pro.date = date

    def getOpen(self):
        return self._bo.open

    def setOpen(self, open):
        self._bo.open = open
        self._bo_pro.open = open

    def getHigh(self):
        return self._bo.high

    def setHigh(self, high):
        self._bo.high = high
        self._bo_pro.high = high

    def getLow(self):
        return self._bo.low

    def setLow(self, low):
        self._bo.low = low
        self._bo_pro.low = low

    def getClose(self):
        return self._bo.close

    def setClose(self, close):
        self._bo.close = close
        self._bo_pro.close = close

    def getVolume(self):
        return self._bo.volume

    def setVolume(self, volume):
        self._bo.volume = volume
        self._bo_pro.volume = volume

    def getChange(self):
        return self._bo.change

    def setChange(self, change):
        self._bo.change = change
        self._bo_pro.change = change

    def getPre_close(self):
        return self._bo.pre_close

    def setPre_close(self, pre_close):
        self._bo.pre_close = pre_close
        self._bo_pro.pre_close = pre_close

    def getPct_chg(self):
        return self._bo.pct_chg

    def setPct_chg(self, pct_chg):
        self._bo.pct_chg = pct_chg
        self._bo_pro.pct_chg = pct_chg

    def getAmount(self):
        return self._bo.amount

    def setAmount(self, amount):
        self._bo.amount = amount
        self._bo_pro.amount = amount

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
        return "MdBarDataBO_OTW ["+"security_code = " + self.getSecurity_code() +"," +"exch = " + self.getExch() +"," +"security_type = " + self.getSecurity_type() +"," +"date = " + self.getDate() +"," +"open = " + str(self.getOpen()) +"," +"high = " + str(self.getHigh()) +"," +"low = " + str(self.getLow()) +"," +"close = " + str(self.getClose()) +"," +"volume = " + str(self.getVolume()) +"," +"change = " + str(self.getChange()) +"," +"pre_close = " + str(self.getPre_close()) +"," +"pct_chg = " + str(self.getPct_chg()) +"," +"amount = " + str(self.getAmount()) +"," +"uuid = " + self.getUuid() +"," +"boid = " + str(self.getBoid()) +"," +"destination = " + self.getDestination() +"," +"]"
