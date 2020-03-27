import uuid

class TestBO(object):

    def __init__(self):
        self.username = ''
        self.age = 0
        self.uuid = str(uuid.uuid1())
        self.boid = 2
        self.destination = "fams.core.rpc.testone"
