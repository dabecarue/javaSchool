package nearsoft.javaschool.url.shortener.dao;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import nearsoft.javaschool.url.shortener.factory.MongoFactory;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UrlShortenerDaoTest {

	@InjectMocks
	UrlShortenerDao urlShortenerDao;
	
	@Spy
	MongoFactory mongoFactory;
	
	@Test
	public void getAliasTest() {
		Assert.assertEquals(urlShortenerDao.getAlias("https://espanol.yahoo.com/?p=us"),"AcZ4GOp");
	}	

	@Test
	public void getUrlTest() {
		Assert.assertEquals(urlShortenerDao.getUrl("AcZ4GOp"),"https://espanol.yahoo.com/?p=us");
	}

}
