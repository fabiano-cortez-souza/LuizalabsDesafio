package br.com.luzialabs.desafio.agenda.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.luzialabs.desafio.agenda.enums.ErrorTypeEnum;

@Component
public class HttpRequest {

    private boolean jUnit = false;
	private int responseCode;
	private URL url; 
	
	public String sendPostRequest(Object payload, String address, String contentType) throws IOException {
		Map<String, String> headers = prepareRestHeaders(contentType);

		String body = payload.toString();
		
		if(!jUnit) {
	        url = new URL(address);
		}
		
		try {
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setConnectTimeout(60000);
			
            for (Entry<String, String> entry : headers.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                con.setRequestProperty(key, value);
            }
			
			con.setDoOutput(true);
			OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream(), StandardCharsets.UTF_8);
	
			wr.write(body);
			wr.flush();
			wr.close();
			this.responseCode = con.getResponseCode();
			return getResponseBody(con);
		} catch(SocketTimeoutException socketTimeoutException) {
			return new ApiResponse(ErrorTypeEnum.HTTP_CLIENT_TIMEOUT).toString();
		}
	}
	
	private String getResponseBody(HttpURLConnection con) throws IOException {
		if (isSuccessResponse(con))
			return extractStreamText(con.getInputStream());
		else
			return extractStreamText(con.getErrorStream());
	}
	
	private boolean isSuccessResponse(HttpURLConnection con) throws IOException {
		return con.getResponseCode() == HttpURLConnection.HTTP_OK || con.getResponseCode() == HttpURLConnection.HTTP_ACCEPTED;
	}
	
	private String extractStreamText(InputStream inputStream) throws IOException {
		BufferedReader bufferedInput = new BufferedReader(new InputStreamReader(inputStream));

		String inputLine;
		StringBuilder responseBuilder = new StringBuilder();

		while ((inputLine = bufferedInput.readLine()) != null)
			responseBuilder.append(inputLine);

		bufferedInput.close();

		return responseBuilder.toString();
	}

	
	public Map<String, String> prepareRestHeaders(String contentType){
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-type", contentType);
		return headers;
	}
	
	@ExceptionHandler({MalformedURLException.class, IOException.class})
	public String invalidURL(MalformedURLException malformedURLException) {
		return malformedURLException.getMessage();
	}
	
	@ExceptionHandler(IOException.class)
	public String responseError(IOException iOException) {
		return iOException.getMessage();
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

    public boolean isjUnit() {
        return jUnit;
    }
    public void setjUnit(boolean jUnit) {
        this.jUnit = jUnit;
    }

    public URL getUrl() {
        return url;
    }
    public void setUrl(URL url) {
        this.url = url;
    }
}
