package info.yufan.hzserver1;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.Map;
import java.util.Queue;

public class HazelcastGetStartServerSlave {
    public static void main(String[] args) {
        //创建一个 hazelcastInstance实例
        HazelcastInstance instance = Hazelcast.newHazelcastInstance();
        Map<String, Long> clusterMap = instance.getMap("mymap");
        Queue<String> clusterQueue = instance.getQueue("MyQueue");
        
        System.out.println("Map Value:" + clusterMap.get("test"));
        System.out.println("Queue Size :" + clusterQueue.size());
        System.out.println("Queue Value 1:" + clusterQueue.poll());
        System.out.println("Queue Value 2:" + clusterQueue.poll());
        System.out.println("Queue Size :" + clusterQueue.size());
    }
}
