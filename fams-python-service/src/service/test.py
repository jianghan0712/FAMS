from pyecharts import options as opts
from pyecharts.charts import Kline

import numpy as np
import pandas as pd
import pymongo as mgdb
import pymysql as mysql


#显示所有列
pd.set_option('display.max_columns', None)
#显示所有行
pd.set_option('display.max_rows', None)
#设置value的显示长度为100，默认为50
pd.set_option('max_colwidth',1000)
pd.set_option('display.width', 4000)

mongoClient = mgdb.MongoClient('localhost', 27017)
collection = mongoClient['fams_stock_bar_IT设备']['000021.SZ']
data = collection.find({'date':{'$gte':'20190101'}}).sort([('date', 1)])
data = pd.DataFrame(list(data))

x_data = np.array(data['date']).tolist()#list
y_data = np.array(data[['open','close','low','high']]).tolist()


# print(x_data)
# x_data = ["2017-10-24", "2017-10-25", "2017-10-26", "2017-10-27"]
# y_data = [[20, 30, 10, 35], [40, 35, 30, 55], [33, 38, 33, 40], [40, 40, 32, 42]]
# 
# (
#     Candlestick(init_opts=opts.InitOpts(width="1440px", height="720px"))
#     .add_xaxis(xaxis_data=x_data)
#     .add_yaxis(series_name="", y_axis=y_data)
#     .set_series_opts()
#     .set_global_opts(
#         yaxis_opts=opts.AxisOpts(
#             splitline_opts=opts.SplitLineOpts(
#                 is_show=True, linestyle_opts=opts.LineStyleOpts(width=1)
#             )
#         )
#     )
#     .render("basic_candlestick.html")
# )
c = (
    Kline()
    .add_xaxis(x_data)
    .add_yaxis("kline", y_data)
    .set_global_opts(
        xaxis_opts=opts.AxisOpts(is_scale=True),
        yaxis_opts=opts.AxisOpts(
            is_scale=True,
            splitarea_opts=opts.SplitAreaOpts(
                is_show=True, areastyle_opts=opts.AreaStyleOpts(opacity=1)
            ),
        ),
        datazoom_opts=[opts.DataZoomOpts(type_="inside")],
        title_opts=opts.TitleOpts(title="Kline-DataZoom-inside"),
    )
    .render("kline_datazoom_inside.html")
)
