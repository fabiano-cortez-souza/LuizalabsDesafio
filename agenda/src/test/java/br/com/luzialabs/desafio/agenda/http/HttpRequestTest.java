package br.com.luzialabs.desafio.agenda.http;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({HttpRequest.class, URL.class, HttpURLConnection.class}) 
class HttpRequestTest {

	String url = "http://example.com";
	URL urlMock;
	
	@InjectMocks
	private HttpRequest httpRequest;
	
	@BeforeEach
	public void setUp() throws MalformedURLException {
		initMocks(this);
		 httpRequest = new HttpRequest();
		 httpRequest.setjUnit(true);
		 urlMock = new URL(url);
	}
	
	@Test
	void testSuccess() throws Exception {

	    String response = "<!doctype html><html><head>    <title>Example Domain</title>    <meta charset=\"utf-8\" />    <meta http-equiv=\"Content-type\" content=\"text/html; charset=utf-8\" />    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />    <style type=\"text/css\">    body {        background-color: #f0f0f2;        margin: 0;        padding: 0;        font-family: -apple-system, system-ui, BlinkMacSystemFont, \"Segoe UI\", \"Open Sans\", \"Helvetica Neue\", Helvetica, Arial, sans-serif;            }    div {        width: 600px;        margin: 5em auto;        padding: 2em;        background-color: #fdfdff;        border-radius: 0.5em;        box-shadow: 2px 3px 7px 2px rgba(0,0,0,0.02);    }    a:link, a:visited {        color: #38488f;        text-decoration: none;    }    @media (max-width: 700px) {        div {            margin: 0 auto;            width: auto;        }    }    </style>    </head><body><div>    <h1>Example Domain</h1>    <p>This domain is for use in illustrative examples in documents. You may use this    domain in literature without prior coordination or asking for permission.</p>    <p><a href=\"https://www.iana.org/domains/example\">More information...</a></p></div></body></html>";
	                   
		URL mockURL = PowerMockito.mock(URL.class);
	    HttpURLConnection mockConnection = PowerMockito.mock(HttpURLConnection.class);
	    httpRequest.setUrl(urlMock);
	    
	    PowerMockito.whenNew(URL.class).withArguments(url).thenReturn(mockURL);
	    PowerMockito.whenNew(URL.class).withArguments(url).thenReturn(mockURL);
	    SocketTimeoutException expectedException = new SocketTimeoutException();

	    PowerMockito.when(mockConnection.getResponseCode()).thenThrow(expectedException);
	    Object obj = httpRequest.sendPostRequest(new Object(), url, url);
	    
	    assertEquals(response, obj.toString());
	}

}
