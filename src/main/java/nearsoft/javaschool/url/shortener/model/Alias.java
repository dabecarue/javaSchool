package nearsoft.javaschool.url.shortener.model;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class Alias {

	private ObjectId id;
  //  @BsonProperty(value = "url")
    private String url;
 //   @BsonProperty(value = "alias")
    private String alias;
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
    
    
}
