package com.example.lab2_softarch_v1.clients_locks;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import java.io.Serializable;

public class HZ_client_lock_optimistic {

    public static void optimistic(){
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        IMap<String, Value> map = hz.getMap( "my-distributed-map2" );
        String key = "1";
        map.put( key, new Value() );
        System.out.println( "opt Starting" );
        for ( int k = 0; k < 1000; k++ ) {
            //if ( k % 10 == 0 ) System.out.println( "opt At: " + k );
            for (; ; ) {
                Value oldValue = map.get( key );
                Value newValue = new Value( oldValue );
                try {
                    Thread.sleep( 10 );
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                newValue.amount++;
                if ( map.replace( key, oldValue, newValue ) )
                    break;
            }
        }
        System.out.println( "opt Finished! Result = " + map.get( key ).amount );
    }

    static class Value implements Serializable {
        public int amount;

        public Value() {
        }

        public Value( Value that ) {
            this.amount = that.amount;
        }

        public boolean equals( Object o ) {
            if ( o == this ) return true;
            if ( !( o instanceof Value ) ) return false;
            Value that = ( Value ) o;
            return that.amount == this.amount;
        }
    }
}
