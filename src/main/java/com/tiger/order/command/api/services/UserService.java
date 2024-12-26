package com.tiger.order.command.api.services;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheStats;

import java.util.concurrent.TimeUnit;

public class UserService {
    private final Cache<Long, User> userCache = CacheBuilder.newBuilder()
            .maximumSize(10000)
            .expireAfterWrite(30, TimeUnit.MINUTES)
            .recordStats() // Bật ghi nhận thống kê
            .build();


    public User getUser(Long userId) {
        try {
            // Lấy từ cache, nếu không có sẽ load từ database
            return userCache.get(userId, () -> loadUserFromDB(userId));
        } catch (Exception e) {
            throw new RuntimeException("Error loading user", e);
        }
    }

    private User loadUserFromDB(Long userId) {
        // Logic load từ database
        return new User();
    }

    public void reportCache() {
        // Lấy thống kê
        CacheStats stats = userCache.stats();
        System.out.println("Hit rate: " + stats.hitRate());
        System.out.println("Miss rate: " + stats.missRate());
        System.out.println("Load success count: " + stats.loadSuccessCount());
    }
}
