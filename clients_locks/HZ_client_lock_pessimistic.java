package com.example.lab2_softarch_v1.clients_locks;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import java.io.Serializable;

public class HZ_client_lock_pessimistic {

    public static void pessimistic() {
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        IMap<String, Value> map = hz.getMap( "my-distributed-map4" );
        String key = "1";
        map.put( key, new Value() );
        System.out.println( "pess Starting" );
        for ( int k = 0; k < 1000; k++ ) {
            map.lock( key );
            try {
                Value value = map.get( key );
                try {
                    Thread.sleep( 10 );
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                value.amount++;
                map.put( key, value );
            } finally {
                map.unlock( key );
            }
        }
        System.out.println( "pess Finished! Result = " + map.get( key ).amount );
    }

    static class Value implements Serializable {
        public int amount;
    }
}
