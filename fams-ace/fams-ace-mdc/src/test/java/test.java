
/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */

/**
 * @Classname: test
 * @Description: 
 * @author jianghan
 * @date 2020-04-26 19:34:21
 */

import Ths.JDIBridge;

public class test {

	public static void main(String[] args) {

		System.out.println(System.getProperty("java.library.path"));

		System.load(
				"D:\\Download\\DataInterface_free_Windows_20200420\\DataInterface_free_Windows\\bin\\x64\\iFinDJava_x64.dll");

		int ret = -1;
		if (args.length > 0) {
			System.out.println("login with cn account");
			//
			ret = JDIBridge.THS_iFinDLogin("终端测试", "123456");
			// ret = JDIBridge.THS_iFinDLogin("zucc005", "758680");
		} else {
			System.out.println("login with en account");
			// ret = JDIBridge.THS_iFinDLogin("ifind_e001", "ifind_e001");
		}
		int a = 0;
		if (ret != 0) {
			while (true) {
				// Scanner sc = new Scanner(System.in);
				// sc.next();

				System.out.print(++a);
				ret = JDIBridge.THS_iFinDLogin("jianghan0712", "jianghan0712");
				System.out.println("THS_iFinDLogin ==> " + ret);

				String strResulthis = JDIBridge.THS_HistoryQuotes("002632.SZ", "close",
						"period:D,pricetype:1,rptcategory:0,fqdate:1900-01-01,hb:YSHB,fill:Omit", "2017-07-09",
						"2017-07-09");
				System.out.println("THS_iFinDhis ==> " + strResulthis);

				String strResulsnap = JDIBridge.THS_Snapshot("300033.SZ,600000.SH", "preClose;open", "fill:Previous",
						"2011-06-10 09:30:00", "2011-06-10 10:10:00");
				System.out.println("THS_iFinDhis ==> " + strResulthis);

				strResulthis = JDIBridge.THS_HistoryQuotes("0001.HK,0002.HK,0003.HK,0004.HK,0005.HK,0006.HK", "close",
						"period:D,pricetype:1,rptcategory:0,fqdate:1900-01-01,hb:YSHB,fill:Previous", "2017-07-09",
						"2017-07-09");
				System.out.println("THS_iFinDhis ==> " + strResulthis);

				String strResult = JDIBridge.THS_BasicData("300033.SZ", "ths_spj_stock", "2016-08-31,10,100");
				System.out.println("THS_BasicData ==> " + strResult);

				strResult = JDIBridge.THS_BasicData("600000.SH,600004.SH,600006.SH,600007.SH,600008.SH",
						"ths_gpjc_stock;ths_gpdm_stock;ths_thsdm_stock", ";;");
				System.out.println("THS_BasicData ==> " + strResult);
//			
				strResult = JDIBridge.THS_HistoryQuotes("300033.SZ,600000.SH", "open;low;high;close",
						"period:W,pricetype:1,rptcategory:1,fqdate:19000101,hb:MHB", "2016-10-10", "2016-11-19");
				System.out.println("THS_HistoryQuotes ==> " + strResult);
//			
//			strResult = JDIBridge.THS_DataStatistics();
//			System.out.println("THS_DataStatistics ==> " + strResult);
//			
//			strResult = JDIBridge.THS_DateSequence("300033.SZ,600000.SH", "open;high", "CPS:0,Days:Tradedays,Fill:Previous,Interval:D,Currency:ORIGINAL", "2016-09-18", "2016-10-18");
//			System.out.println("THS_DateSequence ==> " + strResult);
//			
				strResult = JDIBridge.THS_DataPool("block", "2016-10-18;001005010", "date:Y,security_name:Y,thscode:Y");
				System.out.println("THS_DataPool ==> " + strResult);
//			
//				strResult = JDIBridge.THS_HighFrequencceSequence("600000.SH",
//						"open;high;low;BBI=BBI_day1:4,BBI_day3:13", "CPS:0,MaxPoints:50000,Fill:Previous,Interval:1",
//						"2016-10-18 09:15:00", "2016-10-18 15:15:00");
//				System.out.println("THS_HighFrequencceSequence ==> " + strResult);

				strResult = JDIBridge.THS_RealtimeQuotes("600266.SH",
						"close;open;high;low;new;avg;change;price;turnover;volume;mrj1;mrl1;mcj1;mcl1;mrj2;mrl2;mcj2;mcl2;mrj3;mrl3;mcj3;mcl3;mrj4;mrl4;mcj4;mcl4;mrj5;mrl5;mcj5;mcl5;cc;zjlx;zjcd;zf;ccl");
				System.out.println("THS_RealtimeQuotes ==> " + strResult);
//			
				// strResult = JDIBridge.THS_EDBQuery("M001889667", "2016-05-19", "2017-05-19");
				// System.out.println("THS_EDBQuery ==> " + strResult);

				strResult = JDIBridge.THS_EDBQuery("M001889667", "2016-05-19", "2017-05-19");
				System.out.println("THS_EDBQuery ==> " + strResult);

				strResult = JDIBridge.THS_DateSerial("300033.SZ",
						"ths_qspj_stock;ths_kpj_stock;ths_zgj_stock;ths_zdj_stock;ths_spj_stock",
						"100,2017-12-13;100,2017-12-13;100,2017-12-13;100,2017-12-13;100,2017-12-13",
						"Days:Tradedays,Fill:Previous,Interval:D", "2017-11-13", "2017-12-13");
				System.out.println("THS_DateSerial ==> " + strResult);

				strResult = JDIBridge.THS_GetErrorInfo(0);
				System.out.println("THS_GetErrorInfo ==> " + strResult);

				strResult = JDIBridge.THS_DateQuery("SSE", "dateType:trade,period:D,dateFormat:0", "2016-07-21",
						"2016-08-21");
				System.out.println("THS_DateQuery ==> " + strResult);

//				strResult = JDIBridge.THS_DataOffset("SSE", "dateType:trade,period:W,offset:-10,dateFormat:0",
//						"2016-08-21");
//				System.out.println("THS_DataOffset ==> " + strResult);

				strResult = JDIBridge.THS_DateCount("SSE", "dateType:trade,period:D,dateFormat:0", "2016-07-21",
						"2016-08-21");
				System.out.println("THS_DateCount ==> " + strResult);

				strResult = JDIBridge.THS_DataStatistics();
				System.out.println("Datastatistics ==> " + strResult);

				JDIBridge.THS_iFinDLogout();
				System.out.println("THS_iFinDLogout ==> ");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// strResult = JDIBridge.THS_EDBQuery("M001889667", "2016-05-19", "2017-05-19");
				// System.out.println("THS_EDBQuery ==> " + strResult);

			}
//			strResult = JDIBridge.THS_GetErrorInfo(0);
//			System.out.println("THS_GetErrorInfo ==> " + strResult);
//			
//			strResult = JDIBridge.THS_DateQuery("SSE", "dateType:trade,period:D,dateFormat:0", "2016-07-21", "2016-08-21");
//			System.out.println("THS_DateQuery ==> " + strResult);
//			
//			strResult = JDIBridge.THS_DataOffset("SSE", "dateType:trade,period:W,offset:-10,dateFormat:0", "2016-08-21");
//			System.out.println("THS_DataOffset ==> " + strResult);
//			
//			strResult = JDIBridge.THS_DateCount("SSE", "dateType:trade,period:D,dateFormat:0", "2016-07-21", "2016-08-21");
//			System.out.println("THS_DateCount ==> " + strResult);
//			
//			// asynchronous invoke sample
//			boolean bRet = JDIBridge.THS_AsyBasicData("300033.SZ", "ths_spj_stock", "2016-08-31,10,100", new JDIBridge.JDICallBack() {
//				@Override
//				public void OnDataReturned(String dataContent) {
//					// TODO Auto-generated method stub
//					System.out.println("THS_AsyBasicData ==> " + dataContent);
//				}
//			});
//			System.out.println("Request THS_AsyBasicData ==> " + bRet);
//			
//			bRet = JDIBridge.THS_AsyHistoryQuotes("300033.SZ,600000.SH", "open;low;high;close", "period:W,pricetype:1,rptcategory:1,fqdate:19000101,hb:MHB", "2016-10-10", "2016-11-19", new JDIBridge.JDICallBack() {
//				@Override
//				public void OnDataReturned(String dataContent) {
//					// TODO Auto-generated method stub
//					System.out.println("THS_AsyHistoryQuotes ==> " + dataContent);
//				}
//			});
//			System.out.println("Request THS_AsyHistoryQuotes ==> " + bRet);
//			
//			bRet = JDIBridge.THS_AsyDateSequence("300033.SZ,600000.SH", "open;high", "CPS:0,Days:Tradedays,Fill:Previous,Interval:D,Currency:ORIGINAL", "2016-09-18", "2016-10-18", new JDIBridge.JDICallBack() {
//				@Override
//				public void OnDataReturned(String dataContent) {
//					// TODO Auto-generated method stub
//					System.out.println("THS_AsyDateSequence ==> " + dataContent);
//				}
//			});
//			System.out.println("Request THS_AsyDateSequence ==> " + bRet);
//			
//			bRet = JDIBridge.THS_AsyDataPool("block", "2016-10-18;001005010", "date:Y,security_name:Y,thscode:Y", new JDIBridge.JDICallBack() {
//				@Override
//				public void OnDataReturned(String dataContent) {
//					// TODO Auto-generated method stub
//					System.out.println("THS_AsyDataPool ==> " + dataContent);
//				}
//			});
//			System.out.println("Request THS_AsyDataPool ==> " + bRet);
//			
//			bRet = JDIBridge.THS_AsyHighFrequencceSequence("600000.SH", "open;high;low;BBI=BBI_day1:4,BBI_day3:13", "CPS:0,MaxPoints:50000,Fill:Previous,Interval:1", "2016-10-18 09:15:00", "2016-10-18 15:15:00", new JDIBridge.JDICallBack() {
//				@Override
//				public void OnDataReturned(String dataContent) {
//					// TODO Auto-generated method stub
//					System.out.println("THS_AsyHighFrequencceSequence ==> " + dataContent);
//				}
//			});
//			System.out.println("Request THS_AsyHighFrequencceSequence ==> " + bRet);
//			
//			bRet = JDIBridge.THS_AsyRealtimeQuotes("300033.SZ,600008.SH", "close;open;high;low;mcl1;mcl5;mcl9;mrl10;cc", "pricetype:1", new JDIBridge.JDICallBack() {
//				@Override
//				public void OnDataReturned(String dataContent) {
//					// TODO Auto-generated method stub
//					System.out.println("THS_AsyRealtimeQuotes ==> " + dataContent);
//				}
//			});
//			System.out.println("Request THS_AsyRealtimeQuotes ==> " + bRet);
//			
//			bRet = JDIBridge.THS_AsyEDBQuery("M001620326", "2000-01-01", "2016-01-01", new JDIBridge.JDICallBack() {
//				@Override
//				public void OnDataReturned(String dataContent) {
//					// TODO Auto-generated method stub
//					System.out.println("THS_AsyEDBQuery ==> " + dataContent);
//				}
//			});
//			System.out.println("Request THS_AsyEDBDataQuery ==> " + bRet);

//			bRet = JDIBridge.THS_AsyDateSerial("300033.SZ", "ths_qspj_stock;ths_kpj_stock;ths_zgj_stock;ths_zdj_stock;ths_spj_stock", "100,2017-12-13;100,2017-12-13;100,2017-12-13;100,2017-12-13;100,2017-12-13","Days:Tradedays,Fill:Previous,Interval:D","2017-11-13","2017-12-13" ,new JDIBridge.JDICallBack() {
//			@Override
//			public void OnDataReturned(String dataContent) {
//				// TODO Auto-generated method stub
//				System.out.println("THS_AsyDateSerialQuery ==> " + dataContent);
//			}
//		});
//		System.out.println("Request THS_AsyDateSerialQuery ==> " + bRet);

			// quote push
			// int httpIdx = JDIBridge.THS_QuotesPushing("@GC0Y.CMX",
			// "close;open;high;low;mcl1;mcl5;mcl9;mrl10;cc", "pricetype:1", new
			// JDIBridge.JDICallBack() {
			// @Override
			// public void OnDataReturned(String dataContent) {
			// TODO Auto-generated method stub
			// System.out.println("THS_QuotesPushing ==> " + dataContent);
			// }
			// });
			// System.out.println("Request THS_QuotesPushing ==> " + httpIdx);

			// System.out.println("Press key to stop push...");
			// Scanner sc = new Scanner(System.in);
			// sc.next();

			// JDIBridge.THS_UnQuotesPushing(httpIdx, "@GC0Y.CMX",
			// "close;open;high;low;mcl1;mcl5;mcl9;mrl10;cc");

			// System.out.println("press any char key to quit...:)");
			// sc.next();
			// sc.close();
			// asynchronous invoke sample end

			// ret = JDIBridge.THS_iFinDLogout();

			// System.out.println("THS_iFinDLogout ==> " + ret);
		} else {
			System.out.println("Login failed == > " + ret);
		}
	}

}
