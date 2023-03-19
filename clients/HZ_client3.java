package com.example.lab2_softarch_v1.clients;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.collection.IQueue;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

public class HZ_client3 {

    public static void main(String[] args) {
        // mapHZ();
        boundedQueue();
    }
    public static void mapHZ() {
        // Start the Hazelcast Client and connect to an already running Hazelcast Cluster on 127.0.0.1
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setClusterName("my-hazelcast-cluster");
        HazelcastInstance hz = HazelcastClient.newHazelcastClient(clientConfig);

        // Get the Distributed Map from Cluster.
        IMap map = hz.getMap("my-distributed-map2");
        //Standard Put and Get.
        map.put("key3", "value3");
        map.get("key3");
        //Concurrent Map methods, optimistic updating
        map.putIfAbsent("somekey", "somevalue");
        map.replace("key3", "value3", "newvalue_from_client3");
        // Shutdown this Hazelcast client
        //hz.shutdown();

        System.out.println(map.get("key3"));
        System.out.println("Member of cluster: " +
                hz.getCluster().getMembers());
        // perform shutdown
        hz.getLifecycleService().shutdown();

    }
    public static void boundedQueue() {
        // Start the Hazelcast Client and connect to an already running Hazelcast Cluster on
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setClusterName("my-hazelcast-cluster");
        HazelcastInstance hz = HazelcastClient.newHazelcastClient(clientConfig);

        // bounded queue
        IQueue<String> queue = hz.getQueue("my-distributed-queue");
        // read all queue and show
        for (int i = 0; i < 1000; i++) {
            System.out.println("read client 3: " +queue.poll());
        }

        // perform shutdown
        hz.getLifecycleService().shutdown();

    }
}
