package nearsoft.javaschool.url.shortener.util;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import junit.framework.Assert;
import nearsoft.javaschool.url.shortener.dao.UrlShortenerDao;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UrlUtilityTest {
	
	@InjectMocks
	UrlUtility urlUtility;
	
	@Mock
	UrlShortenerDao urlDao;
	
	@Test
	public void createAliasGoogle() {
		 when(urlDao.getUrl(Mockito.anyString())).thenReturn(null);
		 Assert.assertEquals(urlUtility.createAlias("https://drive.google.com/drive/my-drive").length(), 5);
	}
	
	@Test
	public void createAliasYahoo() {
		 when(urlDao.getUrl(Mockito.anyString())).thenReturn(null);
		 Assert.assertEquals(urlUtility.createAlias("https://auctions.yahoo.co.jp/").length(), 7);
	}
	
	@Test
	public void createAliasGeneric() {
		 when(urlDao.getUrl(Mockito.anyString())).thenReturn(null);
		 Assert.assertEquals(urlUtility.createAlias("https://es.wikipedia.org/wiki/5_de_mayo"), "httpsswkpdrgwkdmy");
	}
	

}
