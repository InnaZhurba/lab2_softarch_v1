package com.example.lab2_softarch_v1.clients;

public class HZ_client_parallel {

    public static void main(String[] args) {
        Thread thread1 = new Thread(HZ_client1::boundedQueue);
        Thread thread2 = new Thread(HZ_client2::boundedQueue);
        Thread thread3 = new Thread(HZ_client3::boundedQueue);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
