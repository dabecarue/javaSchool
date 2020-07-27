package nearsoft.javaschool.url.shortener;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import nearsoft.javaschool.url.shortener.dao.UrlShortenerDao;
import nearsoft.javaschool.url.shortener.dto.AliasResponse;
import nearsoft.javaschool.url.shortener.dto.UrlRequest;
import nearsoft.javaschool.url.shortener.util.UrlUtility;




@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@RestController
public class ShortenerApplication {
	
    private static Logger log = LogManager.getLogger(ShortenerApplication.class);
    private static final String REDIRECT_FAIL_PAGE = "src/main/resources/static";

	public static void main(String[] args) {
		SpringApplication.run(ShortenerApplication.class, args);
	}
	
	@Autowired
	UrlShortenerDao urlDao;
	
	@Autowired
	UrlUtility urlUtility;
	
	 @RequestMapping(method=RequestMethod.POST)
	 public ResponseEntity<AliasResponse> createAlias(@RequestBody UrlRequest urlRequest){
		 AliasResponse aliasResponse= new AliasResponse();
		 if(StringUtils.isEmpty((urlRequest.getUrl()))) {
			 aliasResponse.setErrorMessage("url cannot be empty");
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(aliasResponse);
		 }
		 
		 String aliasRetreived=urlDao.getAlias(urlRequest.getUrl());
		 if(aliasRetreived!=null)
			 aliasResponse.setAlias(aliasRetreived);
		 else {
			String aliasCreated= urlUtility.createAlias(urlRequest.getUrl());
			 urlDao.createAlias(urlRequest,aliasCreated);
			 aliasResponse.setAlias(aliasCreated);
		 }

	        return  ResponseEntity.status(HttpStatus.OK).body(aliasResponse);
	    }
	 
	 @RequestMapping(value="/{alias}", method=RequestMethod.GET)
	 public RedirectView getUrlAlias(@PathVariable("alias") String alias, RedirectAttributes view ){
		 String urlRetreived=urlDao.getUrl(alias);
		 view.addFlashAttribute("flashAttribute", "redirectWithRedirectView");
	     view.addAttribute("attribute", "redirectWithRedirectView");
		 if(urlRetreived==null) {
			 RedirectView rv=new RedirectView("https://google.com");
			 rv.setStatusCode(HttpStatus.NOT_FOUND);
			 return rv;
		 }
		 return new RedirectView(urlRetreived);

	    }

}
