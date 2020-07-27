package nearsoft.javaschool.url.shortener.dao;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import nearsoft.javaschool.url.shortener.dto.UrlRequest;
import nearsoft.javaschool.url.shortener.factory.MongoFactory;

@Component
public class UrlShortenerDao {
	 static String db_name = "urlShortener", db_collection = "alias";
	 
	 @Autowired
	 MongoFactory mongoFactory;

		public String getAlias(String url) {
			MongoDatabase database= mongoFactory.getDataBase(db_name);
			MongoCollection<Document> coll = database.getCollection(db_collection);
			BasicDBObject query = new BasicDBObject();
		    query.put("url", url);
		    Document firstDocument = coll.find(query).first();
		    if(firstDocument==null)
		    	return null;
		    return firstDocument.getString("alias");
		}	

		public boolean createAlias(UrlRequest urlRequest, String aliasCreated) {
			MongoDatabase database= mongoFactory.getDataBase(db_name);
			Document doc = new Document()
					.append("url", urlRequest.getUrl())
					.append("alias", aliasCreated);
			MongoCollection<Document> coll = database.getCollection(db_collection);
		    coll.insertOne(doc);
		    return true;
		}

		public String getUrl(String alias) {
			MongoDatabase database= mongoFactory.getDataBase(db_name);
			MongoCollection<Document> coll = database.getCollection(db_collection);
			BasicDBObject query = new BasicDBObject();
		    query.put("alias", alias);
		    Document firstDocument = coll.find(query).first();
		    if(firstDocument==null)
		    	return null;
		    return firstDocument.getString("url");
		}
		
}

