'''
Created on 2019年10月20日

@author: A
'''

import pymongo as mgdb

class MongoDBHandler(object):
    '''
    classdocs
    '''


    def __init__(self, mongdbClient = None):
        '''
        Constructor
        '''
        self.__mongdbClient = mongdbClient
        
    
    def update(self, collection, db):
        collection = self.__mongoClient[collection][db]
        collection.ensure_index([('date', mgdb.ASCENDING)], unique=True)
    
        