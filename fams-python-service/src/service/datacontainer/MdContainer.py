#encoding=utf-8
from threading import *

import copy
import pymongo as mgdb
import pandas as pd

from src.service.engine.Event import Event
from src.service.engine.EventType import *

DB_prefix = 'fams_stock_bar_'


class MdContainer(object):
    DEFAULT_CONFIG = {
        'bartype' : 'dayBar',
        'dbtype' : 'mongodb',
        'timebegin' : '20180101',
        'timeend' : 'lastday',
        'industry' : ['all'],
        'stockcode': [],
        'dbhost' : 'localhost',
        'dbport' : 27017
    }

    #----------------------------------------------------------------------    
    def __init__(self, log, eventEnginer, mysql, mongoClient, **configs):
        self.log = log
        self.dbclient = None
        self.__eventEnginer = eventEnginer  
                     
        extra_configs = set(configs).difference(self.DEFAULT_CONFIG)
        
        if extra_configs:
            self.log.error("Unrecognized configs: %s" % (extra_configs,))
        
        self.config = copy.copy(self.DEFAULT_CONFIG)
        self.config.update(configs)
        
        self.stock_pool = {self.config['industry']:[self.config['stockcode']]}
        self.mysql = mysql
        self.__mongoClient = mongoClient
        

    #----------------------------------------------------------------------    
    def getBarFromMongo(self,industry=None,stock_code=None):
        collection = self.__mongoClient[industry][stock_code]
        data = collection.find({'date':{'$gte':'20190101'}}).sort([('date', 1)])
#             
        return pd.DataFrame(list(data))

    #----------------------------------------------------------------------    
    def runHistoryBar(self):
        if self.stock_pool is not None:
            for k,v in self.stock_pool.items(): 
                for s in v:
                    key ={}
                    key['industry'] = k
                    key['stockcode'] = s
                    timer = Timer(0, self.__pubBar(key, self.getBarFromMongo(DB_prefix + k, s))) 
                    timer.start()   
    
    #----------------------------------------------------------------------    
    def receiveCommand(self, event):
        key = event.key
        self.__pubBar(key, self.getBarFromMongo(DB_prefix + key['industry'], key['stockcode']))
        
    #----------------------------------------------------------------------        
    def __pubBar(self, key, pdData):
        event = Event(type_=EVENT_MD_BAR, key=key, data=pdData)
        self.__eventEnginer.SendEvent(event)
    

     
                  

 
                        
        
         
        