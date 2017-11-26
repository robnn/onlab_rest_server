package onlab.rest.spring.client;


import com.google.common.base.Stopwatch;
import onlab.rest.spring.model.Institute;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.HttpStatus;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RestClient implements Runnable{
    Client client;
    WebTarget webTarget;
    Invocation.Builder invocationBuilder;
    public RestClient(){
        client = ClientBuilder.newClient();
        webTarget = client.target("http://192.168.0.104:8080/Onlab-REST/institute/");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

    }
    public void getInstituteListInLoop(){
        Stopwatch stopwatch = Stopwatch.createStarted();
        for(int i = 0; i < 100; i++) {
            Response response = invocationBuilder.get();
            if(response.getStatus() != HttpStatus.OK.value()){
                System.out.println("error" + response.getStatus());
            }
        }
        System.out.println("Finished, took: " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + "ms");
    }

    public static void getInstituteListInNewThread(){
        Stopwatch stopwatch = Stopwatch.createStarted();
        Thread[] threads = new Thread[100];
        for(int i = 0 ; i<100; i ++ ) {
            Thread t = new Thread(new RestClient());
            threads[i] = t;
            t.start();
        }
        for (Thread thread : threads)
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        System.out.println("Finished all threads, took: " + stopwatch.elapsed(TimeUnit.MILLISECONDS) + "ms");

    }

    @Override
    public void run() {
        Response response = invocationBuilder.get();
        if(response.getStatus() != HttpStatus.OK.value()){
            System.out.println("error " + response.getStatus());
        }
    }
}
