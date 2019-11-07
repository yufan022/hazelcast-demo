package info.yufan.hazelcastclient1;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastInstanceAware;

import java.io.Serializable;
import java.util.concurrent.Callable;

public class Echo implements Callable<String>, Serializable, HazelcastInstanceAware {
    String input = null;
    Integer account;
    private transient HazelcastInstance hazelcastInstance;

    public Echo() {
    }

    @Override
    public void setHazelcastInstance(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    public Echo(String input, Integer account) {
        this.input = input;
        this.account = account;
    }

    @Override
    public String call() {
        System.out.println("hello");
        return hazelcastInstance.getCluster().getLocalMember().toString() + ":" + input + " client:" + account;

    }
}