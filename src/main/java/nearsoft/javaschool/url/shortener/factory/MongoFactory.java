package nearsoft.javaschool.url.shortener.factory;
 
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
 
@Component
public class MongoFactory {
 
    private static Logger log = LogManager.getLogger(MongoFactory.class);
 
    private static MongoClient mongo;
 
    private MongoFactory() { }
 
    // Returns a mongo instance.
    public MongoClient getMongo() {
    	MongoClientURI uri = new MongoClientURI(
   			 "mongodb+srv://userdbjava:LRFS3KN0ShSJej4E@cluster0.c7ixp.mongodb.net/urlShortener?retryWrites=true&w=majority"); 
         if (mongo == null) {
            try {
                mongo = new MongoClient(uri);                                                                      
            } catch (MongoException ex) {
                log.error(ex);
            }
        }
        return mongo;
    }
 
    // Fetches the mongo database.
    public MongoDatabase getDataBase(String db_name) {  
    	if(mongo==null)
    		getMongo();
        return mongo.getDatabase(db_name);
    }
 
    // Fetches the collection from the mongo database.
    public MongoCollection<Document> getCollection(String db_name, String db_collection) {
        return getDataBase(db_name).getCollection(db_collection);
    }
}
