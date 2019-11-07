package info.yufan.hazelcastclient1;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;

import java.util.concurrent.Future;

public class MasterMember {
    //    public static void main(String[] args) throws Exception {
    //        HazelcastInstance instance = Hazelcast.newHazelcastInstance();
    //        IExecutorService executorService = instance.getExecutorService("executorService");
    //        Future<String> future = executorService.submit(new Echo("myinput"));
    //         while it is executing, do some useful stuff
    // when ready, get the result of your execution
    //        String result = future.get();
    //    }
    public static void main(String[] args) throws Exception {
        ClientConfig clientConfig = new ClientConfig();
        HazelcastInstance instance = HazelcastClient.newHazelcastClient(clientConfig);
        IExecutorService executorService = instance.getExecutorService("executorService");
        Future<String> future = executorService.submit(new Echo("myinput",10));
        // while it is executing, do some useful stuff
        // when ready, get the result of your execution
        String result = future.get();
    }
}