from pip._internal.utils import typing


class Subject(object):
    def __init__(self):
        super(Subject, self).__init__()
        self._handler_dict = defaultdict(list)

    def register(self, topic: typing.Hashable, handler: typing.Callable):
        if not callable(handler):
            raise ValueError('Handler should be a callable object')
        if handler not in self._handler_dict[topic]:
            self._handler_dict[topic].append(handler)

    def unregister(self, topic: typing.Hashable, handler: typing.Callable):
        if handler in self._handler_dict[topic]:
            self._handler_dict[topic].remove(handler)
            if not self._handler_dict[topic]:
                del self._handler_dict[topic]

    def notify(self, topic: typing.Hashable, msg):
        if topic in self._handler_dict:
            for handler in self._handler_dict[topic]:
                handler(msg)


class Subscriber(object):

    def __init__(self):
        super(Subscriber, self).__init__()
        self._topic_handler_list = []
        self._subject: Subject = None

    def subscribe(self, topic: typing.Hashable, handler: typing.Callable):
        self._topic_handler_list.append((topic, handler))
        self._subject.register(topic, handler)