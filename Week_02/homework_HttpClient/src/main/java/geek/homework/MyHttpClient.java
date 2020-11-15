package geek.homework;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class MyHttpClient extends HttpClientBuilder{

    public static void main(String[] args) {
        try {
            HttpClient client = new MyHttpClient().build();
            HttpGet request = new HttpGet("http://baidu.com");
            HttpResponse response = client.execute(request);
            int status = response.getStatusLine().getStatusCode();
            System.out.println(status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}