package com.example.lab2_softarch_v1.server1;

import com.hazelcast.collection.IQueue;
import com.hazelcast.collection.impl.queue.QueueService;
import com.hazelcast.config.Config;
import com.hazelcast.config.QueueConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import java.io.Serializable;

public class HZ_server {
    public static void main(String[] args) throws InterruptedException {

        HazelcastInstance hz = Hazelcast.newHazelcastInstance();//(config);

        // Get the Distributed Map from Cluster.
        IMap map = hz.getMap("my-distributed-map4");
        IMap map1 = hz.getMap("my-distributed-map3");// for locks
        IMap map2 = hz.getMap("my-distributed-map2");;// for locks

        // Put values in the map
        //map.put("key", "value");
        //map.put("anotherkey", "anothervalue");
        Value v = new Value();
        v.amount = 1;
        map.put("1", v);
        // Print out the size and contents of the map
        System.out.println("Map Size:" + map.size());

        // show all values and keys
        for (Object key : map.keySet()) {
            System.out.println("Key: " + key + " Value: " + map.get(key));
        }


// perform shutdown
        //hz.getLifecycleService().shutdown();
    }
    static class Value implements Serializable {
        public int amount;
    }
}

//create bounded queue and some
        /*Config config = new Config();
        QueueConfig queueConfig = new QueueConfig();
        queueConfig.setName("my-distributed-queue");
        queueConfig.setMaxSize(1000); // встановити максимальний розмір черги
        config.addQueueConfig(queueConfig);*/

// write in the queue
        /*IQueue<String> distributedQueue = hz.getQueue( "my-distributed-queue");
        for (int i = 0; i < 4000; i++) {
            System.out.println("write: " + "item" + i);
            distributedQueue.put(("item" + i));
        }*/