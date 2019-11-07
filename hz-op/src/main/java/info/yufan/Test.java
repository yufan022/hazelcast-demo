package info.yufan;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastInstanceAware;

import java.io.Serializable;
import java.util.concurrent.Callable;

/**
 * @author yufan
 */
public class Test implements Callable<String>, Serializable, HazelcastInstanceAware {
    String input = null;
    Integer account;
    private transient HazelcastInstance hazelcastInstance;

    public Test() {
    }

    @Override
    public void setHazelcastInstance(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    public Test(String input, Integer account) {
        this.input = input;
        this.account = account;
    }

    @Override
    public String call() {
        System.out.println("hello");
        return hazelcastInstance.getCluster().getLocalMember().toString() + ":" + input + " server:" + account;

    }
}