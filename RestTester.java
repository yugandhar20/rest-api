package com.rest;

import java.io.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


public class RestTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		HttpClient httpClient = new DefaultHttpClient();
		
		try
		{
			HttpGet httpGetRequest = new HttpGet("http://localhost:8080/users/3");
		      HttpResponse httpResponse = httpClient.execute(httpGetRequest);
		      System.out.println("----------------------------------------");
		      System.out.println(httpResponse.getStatusLine());
		      System.out.println("----------------------------------------");
		      HttpEntity entity = httpResponse.getEntity();
		      byte[] buffer = new byte[1024];
		      if (entity != null) {
		        InputStream inputStream = entity.getContent();
		        try {
		          int bytesRead = 0;
		          BufferedInputStream bis = new BufferedInputStream(inputStream);
		          while ((bytesRead = bis.read(buffer)) != -1) {
		            String chunk = new String(buffer, 0, bytesRead);
		            System.out.println(" output>>>>>>>>>>   " + chunk);
		          }
		        } catch (Exception e) {
		          e.printStackTrace();
		        } finally {
		          try { inputStream.close(); } catch (Exception ignore) {}
		        }
		      }
		}catch(Exception e){
			e.printStackTrace();
		} finally {
		      httpClient.getConnectionManager().shutdown();
		    }
	}

}
