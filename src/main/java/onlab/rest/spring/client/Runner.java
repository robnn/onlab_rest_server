package onlab.rest.spring.client;

public class Runner {
    public static void main(String[] args){
        RestClient restClient = new RestClient();
        restClient.getInstituteListInLoop();
        RestClient.getInstituteListInNewThread();
    }
}
