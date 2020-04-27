/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.king.mongodb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.ListCollectionsIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

@Service
public class MongoDBServiceImpl {
	private MongoClient mongoClient = null;

	@Value(value = "${mongodb.host}")
	private String host;

	@Value(value = "${mongodb.port}")
	private int port;

	@PostConstruct
	public void getMongoClientNoCheck() {
		/**
		 * MongoClient 是线程安全的，可以在多个线程中共享同一个实例 一个 MongoClient 相当于一个客户端，一个客户端可以有多个连接
		 */
		try {
			MongoClientOptions.Builder build = new MongoClientOptions.Builder();
			/** 与目标数据库能够建立的最大连接数为50 */
			build.connectionsPerHost(50);

			/** 如果当前所有的连接都在使用中，则每个连接上可以有50个线程排队等待 */
			build.threadsAllowedToBlockForConnectionMultiplier(50);

			build.maxWaitTime(1000 * 60 * 2);

			/** 设置与数据库建立连接时最长时间为1分钟 */
			build.connectTimeout(1000 * 60 * 1);
			MongoClientOptions mongoClientOptions = build.build();
			ServerAddress serverAddress = new ServerAddress(host, port);

			mongoClient = new MongoClient(serverAddress, mongoClientOptions);
		} catch (MongoException e) {
			e.printStackTrace();
		}
	}

	public MongoDatabase getDatabase(String databaseName) {
		return mongoClient.getDatabase(databaseName);
	}

	public MongoCollection<Document> getcollection(String databaseName, String collectionName) {
		return getDatabase(databaseName).getCollection(collectionName);
	}

	public <T> List<T> getValues(String databaseName, String collectionName, Bson filter, Class<T> clazz) {
		FindIterable<Document> documents = getcollection(databaseName, collectionName).find(filter)
				.sort(new BasicDBObject("date", 1));

		MongoCursor<Document> iterator = documents.iterator();
		List<T> ret = new ArrayList<T>();
		while (iterator.hasNext()) {
			ret.add(JSON.toJavaObject(JSON.parseObject(iterator.next().toJson()), clazz));
		}

		return ret;
	}

	public void tt() {
		MongoDatabase db = mongoClient.getDatabase("fams_stock_bar_银行");
		ListCollectionsIterable<Document> it = db.listCollections();

		MongoCursor<Document> innerit = it.iterator();
		while (innerit.hasNext()) {
			Document t = innerit.next();
			String collectionName = t.getString("name");
			MongoCollection<Document> collection = db.getCollection(collectionName);

			Bson filter = Filters.gte("date", "20200101");
			FindIterable<Document> documents = collection.find(filter);
			MongoCursor<Document> iterator = documents.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next().toJson());
			}
		}
	}

}