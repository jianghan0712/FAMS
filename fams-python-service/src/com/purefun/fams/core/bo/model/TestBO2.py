import uuid

class TestBO2(object):

    def __init__(self):
        self.workid = ''
        self.homeaddress = ''
        self.uuid = str(uuid.uuid1())
        self.boid = 3
        self.destination = "fams.core.rpc.testtwo"
