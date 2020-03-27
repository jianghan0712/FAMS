# encoding=utf-8
import pandas as pd
from src.service.strategy.StrategyContainer import StrategyContainer
import talib as ta


class RSI(StrategyContainer):
    def __init__(self, log, eventEnginer, mysql, **configs):
        super(RSI, self).__init__(log, eventEnginer, mysql, **configs)

    def computeBar(self, event):
        print(event.key)
        industry = event.key['industry']
        code = event.key['stockcode']
        stock_data = event.data

        ####    计算指标,由继承的类实现
        # ----------------------------------------------------------------------
        stock_data['RSI14'] = ta.RSI(stock_data['close'], 14)
        # stock_data['ma_long'] = stock_data['close'].rolling(window=26).mean()
        stock_data.ix[0, 'position'] = 0
        # print(stock_data)

        stock_data.ix[(stock_data['RSI14'].shift(2) < 20) & (stock_data['RSI14'].shift(1) > 20) & (
                stock_data['open'] != stock_data['close']), 'position'] = 1
        stock_data.ix[(stock_data['RSI14'].shift(2) > 65) & (stock_data['RSI14'].shift(1) < 65) & (
                stock_data['open'] != stock_data['close']), 'position'] = 0

        stock_data['position'].fillna(method='ffill', inplace=True)
        stock_data['position'].fillna(0, inplace=True)

        ####     统计交易明细，即计算各类收益指标
        # ----------------------------------------------------------------------
        StrategyContainer.tradeSim(self, industry, code, stock_data)
