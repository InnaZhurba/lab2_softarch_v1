package com.example.lab2_softarch_v1.clients;

//import hazelcast packages
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.collection.IQueue;
import com.hazelcast.config.Config;
import com.hazelcast.config.QueueConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

public class HZ_client1 {
    public static void main(String[] args) {
        //mapHZ();
        boundedQueue();
    }

    public static void mapHZ (){
        // Start the Hazelcast Client and connect to an already running Hazelcast Cluster on 127.0.0.1
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setClusterName("my-hazelcast-cluster");
        HazelcastInstance hz = HazelcastClient.newHazelcastClient(clientConfig);

        HazelcastInstance hz1 = HazelcastClient.newHazelcastClient(clientConfig);
        HazelcastInstance hz2 = HazelcastClient.newHazelcastClient(clientConfig);


        // Get the Distributed Map from Cluster.
        IMap map = hz.getMap("my-distributed-map3");
        //Standard Put and Get.
        //map.put("key4", "value3");
        //map.get("key3");
        //Concurrent Map methods, optimistic updating
        //map.putIfAbsent("somekey", "somevalue");
        //map.replace("key3", "value3", "newvalue_from_client1");
        // Shutdown this Hazelcast client
        //hz.shutdown();

        //System.out.println(map.get("key3"));

        //get all the keys and values
        System.out.println("Map Size:" + map.size());

        // show all the keys with their values
        for (Object key : map.keySet()) {
            System.out.println("Key: " + key + "\n Value: " + map.get(key));
        }


        // perform shutdown
        hz.getLifecycleService().shutdown();
    }

    public static void boundedQueue() {
        // Start the Hazelcast Client and connect to an already running Hazelcast Cluster on
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setClusterName("my-hazelcast-cluster");
        HazelcastInstance hz = HazelcastClient.newHazelcastClient(clientConfig);

        // bounded queue
        Config config = new Config();

        int maxSize = config.getQueueConfig("my-distributed-queue").getMaxSize();
        System.out.println("Maximum size of the queue: " + maxSize);

        IQueue<String> queue = hz.getQueue("my-distributed-queue");

        // add 1000 items to the queue
        for (int i = 0; i < 4000; i++) {
            System.out.println("write: " + "item" + i);
            queue.offer("item" + i);
        }

        // perform shutdown
        hz.getLifecycleService().shutdown();

    }
}
