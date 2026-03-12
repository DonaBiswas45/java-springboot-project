package com.example.Apigateway;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Component;

@Component
public class RateLimitter {

    private Map<String, AtomicInteger> requestCounts = new ConcurrentHashMap<>();
    private Map<String, Long> requestTimestamps = new ConcurrentHashMap<>();

    private static final int MAX_REQUESTS = 5;
    private static final long TIME_WINDOW = 10000;

    public boolean isAllowed(String ipAddress) {
        long currentTime = System.currentTimeMillis();

        requestTimestamps.putIfAbsent(ipAddress, currentTime);
        requestCounts.putIfAbsent(ipAddress, new AtomicInteger(0));

        long lastRequestTime = requestTimestamps.get(ipAddress);

        if (currentTime - lastRequestTime > TIME_WINDOW) {
            requestCounts.get(ipAddress).set(0);
            requestTimestamps.put(ipAddress, currentTime);
        }

        int count = requestCounts.get(ipAddress).incrementAndGet();
        return count <= MAX_REQUESTS;
    }
}