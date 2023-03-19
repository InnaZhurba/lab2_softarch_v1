package com.example.lab2_softarch_v1.clients;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

public class HZ_client4 {
    public static void main(String[] args) {
        // create map for 3 existing instances
        // put values in the map
// print out the size and contents of the map

        // Start the Hazelcast Client and connect to an already running Hazelcast Cluster on
        HazelcastInstance hz1 = HazelcastClient.newHazelcastClient();

        IMap map1 = hz1.getMap("my-distributed-map2");
        IMap map2 = hz1.getMap("my-distributed-map2");
        IMap map3 = hz1.getMap("my-distributed-map2");

        // Put values in the maps
        map1.put("key111", "value1");
        map2.put("key222", "value2");
        map3.put("key333", "value3");

        // show the size of the map
        System.out.println("Map1 Size:" + map1.size());
        System.out.println("Map2 Size:" + map2.size());
        System.out.println("Map3 Size:" + map3.size());

        //close
        hz1.getLifecycleService().shutdown();
    }
}
