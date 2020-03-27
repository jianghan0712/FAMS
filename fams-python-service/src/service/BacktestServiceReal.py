# encoding=utf-8

import sys
import pandas as pd
import pymysql as mysql
import pymongo as mgdb

from src.core.PService.PyService import PyService
from src.core.log.PyPLogger import PyPLogger
from src.service.strategy.StrategyContainer import StrategyContainer
from src.service.datacontainer.MdContainer import MdContainer
from src.service.engine.EventEnginer import EventEnginer
from src.service.engine.EventType import *
from src.service.engine.Event import Event
from src.core.db.MySQLHandler import MySQLHandler
from src.core.db.MongoDBHandler import MongoDBHandler

# 显示所有列
pd.set_option('display.max_columns', None)
# 显示所有行
pd.set_option('display.max_rows', None)
# 设置value的显示长度为100，默认为50
pd.set_option('max_colwidth', 1000)
pd.set_option('display.width', 4000)


class BacktestService(PyService):
    '''
    classdocs
    '''

    def __init__(self, serviceName, env, instance):
        super(BacktestService, self).__init__(serviceName, env, instance)

        self.log = PyPLogger(BacktestService)
        self.strategy = None
        self.datacontainer = None
        self.enginer = None

    # ----------------------------------------------------------------------
    def initService(self):
        super(BacktestService, self).initService()

        self.configs = dict(super(BacktestService, self).getConfigBean('main', 'Strategy'))

        self.__mysqlClient = mysql.connect(host=self.getConfigBean('db', 'mysql', 'host'),
                                           user=self.getConfigBean('db', 'mysql', 'user'),
                                           password=self.getConfigBean('db', 'mysql', 'password'),
                                           port=int(self.getConfigBean('db', 'mysql', 'port')),
                                           database=self.getConfigBean('db', 'mysql', 'database'))
        self.mysql = MySQLHandler(self.__mysqlClient)
        self.__mongoClient = mgdb.MongoClient(self.getConfigBean('db', 'mongodb', 'mongoHost'),
                                              int(self.getConfigBean('db', 'mongodb', 'mongoPort')))
        self.mongodb = MongoDBHandler(self.__mongoClient)

        self.__init_EventEnginer()  # 初始化事件引擎
        self.__init_MdContainer(self.configs)  # 初始化数据容器
        self.__init_StrategyContainer(self.configs)  # 初始化策略容器

        self.enginer.AddEventListener(EVENT_MD_BAR, self.strategy.on_bar)
        self.enginer.AddEventListener(EVENT_TRADE_NEWORDER, self.strategy.on_order)
        # self.enginer.AddEventListener(EVENT_COMMAND_NEXT_STOCK, self.datacontainer.receiveCommand)
        self.enginer.Start()

    # ----------------------------------------------------------------------
    def startService(self):
        super(BacktestService, self).startService()
        self.strategy.runHistoryBar()

    # ----------------------------------------------------------------------

    def __init_StrategyContainer(self, configs):
        '''
        @note: 根据config,加载指定的strategy
        '''
        modul = getattr(__import__('strategy.' + self.configs['name']), self.configs['name'])
        c = getattr(modul, self.configs['name'])
        self.strategy = c(self.log, self.enginer, self.mysql, **dict(configs))

    # ----------------------------------------------------------------------
    def __init_MdContainer(self, configs):
        self.datacontainer = MdContainer(self.log, self.enginer, self.mysql, self.__mongoClient, **dict(configs))

    # ----------------------------------------------------------------------
    def __init_EventEnginer(self):
        self.enginer = EventEnginer(self.log)


service = BacktestService('BacktestService', "DEV", "1")
service.initService()
service.startService()
