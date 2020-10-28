package geek.homework;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class MyHttpClient {

    public static void main(String[] args) {
        try {
            HttpClient client = new DefaultHttpClient(); 
            HttpGet request = new HttpGet("http://localhost:8088/api/hello");
            HttpResponse response = client.execute(request);
            int status = response.getStatusLine().getStatusCode();
            System.out.println(status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}