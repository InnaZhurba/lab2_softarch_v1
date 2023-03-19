package com.example.lab2_softarch_v1.clients;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.collection.IQueue;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

public class HZ_client2 {

    public static void main(String[] args) {
        //mapHZ();
        boundedQueue();
    }

    public static void mapHZ() {
        //the Hazelcast Client + connect to an already running Hazelcast Cluster
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setClusterName("my-hazelcast-cluster");
        HazelcastInstance hz = HazelcastClient.newHazelcastClient(clientConfig);

        // Get the Distributed Map from Cluster.
        IMap map = hz.getMap("my-distributed-map2");

        //put in map values with keys from 1 to 1000
        for (int i = 1; i <= 1000; i++) {
            map.put(i, "value" + i);
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
        IQueue<String> queue = hz.getQueue("my-distributed-queue");
        // read all queue and show
        for (int i = 0; i < 1000; i++) {
            System.out.println("read client 2: " + queue.poll());
        }

        // perform shutdown
        hz.getLifecycleService().shutdown();

    }
}





//Standard Put and Get.
//map.put("key3", "value3");
//map.get("key3");
//Concurrent Map methods, optimistic updating
//map.putIfAbsent("somekey", "somevalue");
//map.replace("key3", "value3", "newvalue_from_client2");
// Shutdown this Hazelcast client
//hz.shutdown();