#coding=UTF-8

from datetime import datetime
import time

import tushare as ts
import pandas as pd
import pymongo as mgdb
import pymysql as mysql
import talib as ta
import numpy as np

from src.core.PService.PyService import PyService
from src.core.log.PyPLogger import PyPLogger
from src.core.db.MySQLHandler import MySQLHandler
from src.core.db.MongoDBHandler import MongoDBHandler
from src.core.md.MdData import MdBarData




#显示所有列
pd.set_option('display.max_columns', None)
#显示所有行
pd.set_option('display.max_rows', None)
#设置value的显示长度为100，默认为50
pd.set_option('max_colwidth',1000)
pd.set_option('display.width', 4000)

class TuShareService(PyService):
    '''
    classdocs
    '''

    def __init__(self, serviceName=None, env=None, instance=None):
        '''
        Constructor
        '''
        super(TuShareService, self).__init__(serviceName, env, instance)    
        ts.set_token('e323a605bc2f837c7ca48059133b9335be9b2ad562c61a9ac60fb4f8')
        self.pro = ts.pro_api()   
        self.log = PyPLogger(TuShareService)
        

        
    def initService(self):
        super(TuShareService, self).initService()
        self.url = self.getConfigBean('main', 'envinfo', 'url')
        
        self.__mysqlClient = mysql.connect(host=self.getConfigBean('db', 'mysql', 'host'),
                                               user=self.getConfigBean('db', 'mysql', 'user'),
                                               password=self.getConfigBean('db', 'mysql', 'password'),
                                               port=int(self.getConfigBean('db', 'mysql', 'port')),
                                               database=self.getConfigBean('db', 'mysql', 'database'))
        self.mysql = MySQLHandler(self.__mysqlClient)
        self.__mongoClient = mgdb.MongoClient(self.getConfigBean('db', 'mongodb', 'mongoHost'),
                                      int(self.getConfigBean('db', 'mongodb', 'mongoPort')))
        self.mongodb = MongoDBHandler(self.__mongoClient)
        
    def startService(self):
        super(TuShareService, self).startService()
    
    
    #----------------------------------------------------------------------     
    def queryHoliday(self, year=''):
        '''
        @note: 从tushare获取交易日历，并存入mysql-->fams_exchange
        @param year: 年份 xxxx 
        '''
        
        if year=='' or year is None:
            startDate = str(datetime.datetime.now().year)+'0101'
            endDate = str(datetime.datetime.now().year)+'1231'
        else:
            startDate = year+'0101'
            endDate = year+'1231'
        exchList = self.mysql.getData("select exch,ts_name from fams_exchange")       
        
        for eachExch in exchList:
            df = self.pro.trade_cal(exchange=eachExch[1], start_date=startDate, end_date=endDate, fields='exchange,cal_date,is_open,pretrade_date')
            df['exchange']=eachExch[0]
            df.ix[df['is_open']==0,'is_open']='FALSE'
            df.ix[df['is_open']==1,'is_open']='TRUE'
             
            for row in df.iterrows():          
                sql = "insert into fams_holiday(date,exch,open_flag) values ('" + row['cal_date'] + "','" + row['exchange'] + "','" + row['is_open'] + "')"
                print(sql)
                self.mysql.executeMySQL(sql)
                
    #----------------------------------------------------------------------     
    def querySecurityBasicInfo(self):
        '''
        @note: 从tushare获取证券基础信息，并存入mysql-->fams_security_basicinfo
        '''
        df = self.pro.stock_basic(fields='ts_code,symbol,name,area,industry,market,exchange,curr_type,list_status,list_date,is_hs')

        df.ix[df['exchange']=='SSE','security_id']=df['symbol']+'.sh'
        df.ix[df['exchange']=='SZSE','security_id']=df['symbol']+'.sz'
        df.ix[df['exchange']=='SSE','exch']='sh'
        df.ix[df['exchange']=='SZSE','exch']='sz'
        for row in df.iterrows():
            sql = ("insert into fams_security_basicinfo(exch,security_id,ts_id,exchange_id,security_name,area,industry,market_type,currency,status,list_date,ht_flag) "     
                " values ('" + row['exch'] + "','" +row['security_id'] + "','" + row['ts_code']  + "','" +  row['symbol'] + "','" + row['name']+ "','" + row['area']+ "','" +row['industry'] + "','" + 
                               row['market'] + "','" + row['curr_type']  + "','" + row['list_status'] + "','" + row['list_date'] + "','" + row['is_hs'] + "')") 
            print(sql)
            self.mysql.executeMySQL(sql)
    
    #----------------------------------------------------------------------    
    def getStockHisBarFromTushare(self, industry=[], stock_code=None, start_date='20140101',end_date=''):
        industList = []
        if industry is None or len(industry)<=0:
            industList = self.mysql.executeMySQL("select industry from fams_industry where pull_flag='Y'")           
        else:
            industList = industry
        
        industryName = None
        start_flag = False
        for value in industList:           
            if industry is None or len(industry)<=0:
                industryName = value[0]
            else:
                industryName = value
        
            stockCodeList = self.mysql.getData("select ts_id from fams_security_basicinfo where industry='" + industryName + "'")
            for eachStockCode in stockCodeList:
                if start_flag==False and eachStockCode[0] != stock_code  :
                    self.log.info('jump stockcode {}',eachStockCode[0])
                    continue
                else:
                    start_flag = True
                
                if start_flag:
                    self._getStockHisBarFromTushare(industryName, eachStockCode[0], start_date, end_date)
                    time.sleep(5)
            self.mysql.getData("update fams_industry set pull_flag='N'  where industry='" + industryName + "'")
            
    def _getStockHisBarFromTushare(self, industry=None, stock_code=None, start_date='20140101',end_date=''):
            data = ts.pro_bar(ts_code=stock_code, adj='qfq',start_date=start_date,end_date=end_date)
            collection = self.__mongoClient['fams_stock_bar_' + industry][stock_code]
            collection.ensure_index([('date', mgdb.ASCENDING)], unique=True)
            for i in range(len(data)):
                bar = MdBarData()  
                bar.date = data.iloc[i].trade_date    
                bar.open = data.iloc[i].open
                bar.high = data.iloc[i].high
                bar.low = data.iloc[i].low
                bar.close = data.iloc[i].close
                bar.volume = data.iloc[i].vol
                bar.change = data.iloc[i].change  
                bar.pct_chg = data.iloc[i].pct_chg
                bar.amount = data.iloc[i].amount
                flt = {'date': bar.date} 
                collection.update_one(flt, {'$set':bar.__dict__}, upsert=True) 
            self.log.info('get industry = {}, stockcode={} successful' ,industry, stock_code)
    
    #----------------------------------------------------------------------        
    def initIndustry(self):   
        industList = self.mysql.executeMySQL('select industry from fams_security_basicinfo group by industry') 
        today =  datetime.strftime(datetime.now(), "%Y%m%d")
        i = 0
        for value in industList: 
            industryName = value[0]
            print(industryName)
            sql = "insert into fams_industry(industry,pull_flag,last_update_date) values ('" + industryName + "','N','" +today+"')"
            self.mysql.executeMySQL(sql)
    
    def getLastBarFromMongo(self,industry=None,stock_code=None):
        collection = self.__mongoClient['fams_stock_bar_' + industry][stock_code]
        data = collection.find().sort([('date', -1)]).limit(1)
#         ({'date':{'$gte':'20200305'}})    
        return pd.DataFrame(list(data))
#         return data

    def getBarFromMongo(self,industry=None,stock_code=None):
        collection = self.__mongoClient['fams_stock_bar_' + industry][stock_code]
        data = collection.find({'date':{'$gte':'20190101'}}).sort([('date', 1)])
#             
        return pd.DataFrame(list(data))

    def updateBarFromTS(self, industry=[], stock_code=None, start_date='20140101',end_date=''):
        industList = []
        if industry is None or len(industry)<=0:
            industList = self.mysql.executeMySQL("select industry from fams_industry where pull_flag='Y'")           
        else:
            industList = industry
        
        industryName = None
        start_flag = False
        for value in industList:           
            if industry is None or len(industry)<=0:
                industryName = value[0]
            else:
                industryName = value
            
            stockCodeList = self.mysql.getData("select ts_id from fams_security_basicinfo where industry='" + industryName + "'")
            for eachStockCode in stockCodeList:
#                 if stock_code is None:
#                     start_flag = True
                if start_flag==False and eachStockCode[0] != stock_code  :
                    self.log.info('jump stockcode {}',eachStockCode[0])
                    continue
                else:
                    start_flag = True
                
                if start_flag:
                    data = self.getLastBarFromMongo(industryName, eachStockCode[0]) 
                    if data.empty:
                        self._getStockHisBarFromTushare(industryName, eachStockCode[0], start_date, end_date)
                    else:
                        self._getStockHisBarFromTushare(industryName, eachStockCode[0], data.ix[0,'date'], end_date)                 
                    
                    time.sleep(5)
            self.mysql.getData("update fams_industry set pull_flag='N'  where industry='" + industryName + "'")
        
        
        
    def updateBarfromTs(self, industry=[], stock_code=None, start_date='20140101',end_date=''):
        pass
        

service = TuShareService(serviceName='TuShareService', env='DEV', instance='1')
service.initService()
service.startService()
# data = service.getLastBarFromMongo(industry='塑料',stock_code='000859.SZ')
service.updateBarFromTS(stock_code='300805.SZ')
# data = service.getBarFromMongo(industry='塑料',stock_code='000859.SZ')
# print(data.ix[0,'date'])
# service.initIndustry()
#service.getStockHisBarFromTushare(stock_code='300452.SZ')
# service.queryHoliday()
# service.querySecurityBasicInfo()



# service.getAllStockHis(breakStockCode = '300462')
# raw_input()     
# data = service.getBarFromMongo(industry='保险', stock_code='000627.SZ')
# data['RSI']=ta.RSI(np.array(data['close']), timeperiod=14)
# print(data)




        