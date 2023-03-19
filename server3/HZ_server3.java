package com.example.lab2_softarch_v1.server3;

import com.hazelcast.collection.IQueue;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import java.io.Serializable;

public class HZ_server3 {
    public static void main(String[] args) {
        //Config config = new Config();
        //config.getNetworkConfig().setPort(5703);

        /*config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
        config.getNetworkConfig().getJoin().getTcpIpConfig().setEnabled(true);
        config.getNetworkConfig().getJoin().getTcpIpConfig().addMember("localhost:5701");
        config.getNetworkConfig().getJoin().getTcpIpConfig().addMember("localhost:5702");
        config.getNetworkConfig().getJoin().getTcpIpConfig().addMember("localhost:5703");*/
        // Start the Hazelcast Cluster Member
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        // Get the Distributed Map from Cluster.
        IMap map = hz.getMap("my-distributed-map4");

        //create bounded queue
        IQueue queue = hz.getQueue("my-distributed-queue");

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
