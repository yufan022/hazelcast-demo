package info.yufan.hazelcastclient1;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.*;
import com.hazelcast.map.impl.tx.TransactionalMapProxySupport;
import com.hazelcast.transaction.TransactionContext;
import com.hazelcast.transaction.TransactionOptions;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class TransactionalMember {
    public static void main(String[] args) throws Exception {
        //        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
        ClientConfig clientConfig = new ClientConfig();
        HazelcastInstance instance = HazelcastClient.newHazelcastClient(clientConfig);
        //                AtomicLong count = new AtomicLong(0);
        AtomicLong count = new AtomicLong((Long) instance.getMap("mymap").get("test"));
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                while (true) {
                    TransactionOptions options = new TransactionOptions()
                            .setTransactionType(TransactionOptions.TransactionType.TWO_PHASE).setTimeout(2, TimeUnit.SECONDS);
                    TransactionContext context = instance.newTransactionContext(options);
                    //                    ILock lock = instance.getLock(String.valueOf("1"));
                    //                    lock.lock();
                    try {

                        context.beginTransaction();

                        //                        TransactionalQueue queue = context.getQueue("myqueue");
                        TransactionalMap map = context.getMap("mymap");
                        //                        TransactionalSet set = context.getSet("myset");
                        //                        Object obj = queue.poll();

                        // process obj
                        Long test1 = (Long) map.getForUpdate("test");
                        long l = test1 + 1;
                        map.put("test", l);
//                        System.out.println(map.get("test"));
                        //                        set.add("value");
                        // do other things
                        if (finalI == 3) {
//                            throw new RuntimeException();
                        }
                        //1573040813060
                        //1573040812866
                        context.commitTransaction();
                        System.out.println("count : " + count.incrementAndGet() + " ; ts : " + System.currentTimeMillis());
                        //                        lock.unlock();
                    } catch (Throwable t) {
                        t.printStackTrace();
                        try {
                            context.rollbackTransaction();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        //                        lock.unlock();
                        System.out.println("unlock");
                    } finally {

                    }
                    //                    context.rollbackTransaction();
                }
            }).start();
        }
    }
}
/*
 * begin mm trans
 * lock mm account
 * validate mm acount
 * catch{
 * begin db trans
 * insert order
 * insert trans
 * commit db
 * }
 * update mm account
 * commit mm trans
 * unlock mm account
 * */


/*
 * begin mm trans
 * lock mm account
 * validate mm acount
 * catch{
 * begin db trans
 * insert order
 * insert trans
 *
 * update mm account
 * commit mm trans
 *
 * commit db trans
 * } rollback {
 *   mm account
 *   unlock mm account
 *   return;
 * }
 * unlock mm account
 * */