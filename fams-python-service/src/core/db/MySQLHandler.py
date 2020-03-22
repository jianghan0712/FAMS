


class MySQLHandler(object):
    '''
    classdocs
    '''

    def __init__(self, mysqlClient = None):
        '''
        Constructor
        '''
        self.__mysqlClient = mysqlClient

    #----------------------------------------------------------------------     
    def executeMySQL(self, sql = None):
        try:
            with self.__mysqlClient.cursor() as cursor:
                cursor.execute(sql)
                self.__mysqlClient.commit()
                datalist=cursor.fetchall()
                return datalist
        except Exception as e:
            print(e.args)
    
    #----------------------------------------------------------------------     
    def getData(self, selectSQL = None):
        if selectSQL is None:
            return None
        try:
            with self.__mysqlClient.cursor() as cursor:
                cursor.execute(selectSQL)
                datalist=cursor.fetchall()
                return datalist
        except Exception as e:
            print(e.args)
