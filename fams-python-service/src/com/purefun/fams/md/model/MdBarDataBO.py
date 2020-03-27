import uuid

class MdBarDataBO(object):

    def __init__(self):
        self.security_code = ''
        self.exch = ''
        self.security_type = ''
        self.date = ''
        self.open = 0.0
        self.high = 0.0
        self.low = 0.0
        self.close = 0.0
        self.volume = 0
        self.change = 0.0
        self.pre_close = 0.0
        self.pct_chg = 0.0
        self.amount = 0.0
        self.uuid = str(uuid.uuid1())
        self.boid = 1001
        self.destination = "fams.md.bar.stock"
