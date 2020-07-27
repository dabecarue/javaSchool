package nearsoft.javaschool.url.shortener;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import nearsoft.javaschool.url.shortener.dao.UrlShortenerDao;
import nearsoft.javaschool.url.shortener.dto.AliasResponse;
import nearsoft.javaschool.url.shortener.dto.UrlRequest;
import nearsoft.javaschool.url.shortener.util.UrlUtility;

import static org.mockito.Mockito.when;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class ShortenerApplicationTests {
	
	@InjectMocks private ShortenerApplication shortenerApplication;
	
	@Mock
	UrlShortenerDao urlDao;
	
	@Mock
	UrlUtility urlUtility;


	@Test
	void contextLoads() {
	}
	
	@Before
	public void init() {
		 MockitoAnnotations.initMocks(this);
	}
	
	 @Test
	 public void createAliasTest(){
		 when(urlDao.createAlias(Mockito.any(UrlRequest.class), Mockito.anyString())).thenReturn(true);
		 when(urlDao.getAlias(Mockito.anyString())).thenReturn("UHVff");	
		 UrlRequest rquest=new UrlRequest();
		 rquest.setUrl("https://google.com");
		 ResponseEntity<AliasResponse> response= shortenerApplication.createAlias(rquest);
		 assertEquals(response.getStatusCode(), HttpStatus.OK);
		 assertEquals(response.getBody().getAlias(), "UHVff");
	    }
	 
	 @Test
	 public void createAliasTestAliasNull(){
		 when(urlDao.createAlias(Mockito.any(UrlRequest.class), Mockito.anyString())).thenReturn(true);
		 when(urlDao.getAlias(Mockito.anyString())).thenReturn("UHVff");	
		 UrlRequest rquest=new UrlRequest();
		 rquest.setUrl(null);
		 ResponseEntity<AliasResponse> response= shortenerApplication.createAlias(rquest);
		 assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
		 assertNotNull(response.getBody().getErrorMessage());
	    }
	 
	 @Test
	 public void getUrlAlias(){
		 RedirectAttributes attributes;
		 attributes=Mockito.mock(RedirectAttributes.class);
		 when(urlDao.getUrl(Mockito.anyString())).thenReturn("https://google.com");
		 RedirectView rv=shortenerApplication.getUrlAlias("UHVff", attributes);
		 assertNotNull(rv.getUrl());
	    }
	 

}

