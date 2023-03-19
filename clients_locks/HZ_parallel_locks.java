package com.example.lab2_softarch_v1.clients_locks;

import com.example.lab2_softarch_v1.clients.HZ_client1;
import com.example.lab2_softarch_v1.clients.HZ_client2;
import com.example.lab2_softarch_v1.clients.HZ_client3;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import java.io.Serializable;

public class HZ_parallel_locks {

    public static void main(String[] args) {
        for(int i=0; i<3;i++) {
            Thread thread1 = new Thread(HZ_client_racy::racy);
            Thread thread2 = new Thread(HZ_client_lock_pessimistic::pessimistic);
            Thread thread3 = new Thread(HZ_client_lock_optimistic::optimistic);

            thread1.start();
            thread2.start();
            thread3.start();
        }
    }
}

