package info.yufan.hazelcastclient1;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Map;

@SpringBootApplication
public class HazelcastClient1Application {

    public static void main(String[] args) {
        SpringApplication.run(HazelcastClient1Application.class, args);
    }

    @PostConstruct
    public void test() {
        ClientConfig clientConfig = new ClientConfig();
        HazelcastInstance instance = HazelcastClient.newHazelcastClient(clientConfig);
        Map map = instance.getMap("hello");
        System.out.println(map.toString());


    }
}
