package nearsoft.javaschool.url.shortener.util;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nearsoft.javaschool.url.shortener.dao.UrlShortenerDao;


@Component
public class UrlUtility {
	
	@Autowired
	UrlShortenerDao dao;
	
	static final String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static final String ALPHA_NUMERIC = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static SecureRandom random = new SecureRandom();

	public String createAlias(String url) {
		String alias="";
		if(url.contains("google.")) {		
			StringBuilder sb = new StringBuilder(5);
			   for( int i = 0; i <5; i++ ) 
			      sb.append( ALPHA.charAt( random.nextInt(ALPHA.length()) ) );
			   alias=sb.toString();
		}
		else {
			if(url.contains("yahoo.")) {
				StringBuilder sb = new StringBuilder(7);
				for( int i = 0; i <7; i++ ) 
						      sb.append( ALPHA_NUMERIC.charAt( random.nextInt(ALPHA_NUMERIC.length()) ) );
						   alias=sb.toString();
						  
			}
			else {
				alias=url.replaceAll("[^a-zA-Z]", "").replaceAll("[AaEeIiOoUu]", "");  
			}
		}
		if(dao.getUrl(alias)!=null)
			return createAlias(url);
		
		return alias;
	}

}
