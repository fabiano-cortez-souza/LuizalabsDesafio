package br.com.luzialabs.desafio.agenda.connector;

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

import br.com.luzialabs.desafio.agenda.http.HttpRequest;

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

		URL mockURL = PowerMockito.mock(URL.class);
	    HttpURLConnection mockConnection = PowerMockito.mock(HttpURLConnection.class);
	    httpRequest.setUrl(urlMock);
	    
	    PowerMockito.whenNew(URL.class).withArguments(url).thenReturn(mockURL);
	    PowerMockito.whenNew(URL.class).withArguments(url).thenReturn(mockURL);
	    SocketTimeoutException expectedException = new SocketTimeoutException();

	    PowerMockito.when(mockConnection.getResponseCode()).thenThrow(expectedException);
	    Object obj = httpRequest.sendPostRequest(new Object(), url, url);
	}

}
