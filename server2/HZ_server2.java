package com.example.lab2_softarch_v1.server2;

import com.hazelcast.collection.IQueue;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import java.io.Serializable;

public class HZ_server2 {
    public static void main(String[] args) {
        //Config config = new Config();
        //config.getNetworkConfig().setPort(5702).setPortAutoIncrement(true).setPortCount(20);

        /*config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
        config.getNetworkConfig().getJoin().getTcpIpConfig().setEnabled(true);
        config.getNetworkConfig().getJoin().getTcpIpConfig().addMember("localhost:5701");
        config.getNetworkConfig().getJoin().getTcpIpConfig().addMember("localhost:5702");
        config.getNetworkConfig().getJoin().getTcpIpConfig().addMember("localhost:5703");*/
        // create 3 instances of hazelcast
        HazelcastInstance hz1 = Hazelcast.newHazelcastInstance();
        HazelcastInstance hz2 = Hazelcast.newHazelcastInstance();
        HazelcastInstance hz3 = Hazelcast.newHazelcastInstance();

        //create maps new
        IMap<String, Value>  map1 = hz1.getMap("my-distributed-map4");
        IMap map2 = hz2.getMap("my-distributed-map2");
        IMap map3 = hz3.getMap("my-distributed-map2");

        //create bounded queue
        IQueue queue = hz1.getQueue("my-distributed-queue");

        // Put values in the maps
        //map1.put("key1", "value1");
        Value v = new Value();
        v.amount = 1;
        map1.put("key1", v);
        map2.put("key2", "value2");
        map3.put("key3", "value3");

        System.out.println("Map1 Size:" + map1.size());
        System.out.println("Map2 Size:" + map2.size());
        System.out.println("Map3 Size:" + map3.size());

    }
    static class Value implements Serializable {
        public int amount;
    }
}
