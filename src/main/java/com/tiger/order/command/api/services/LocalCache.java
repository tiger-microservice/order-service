package com.tiger.order.command.api.services;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.tiger.order.command.api.dtos.events.OrderCreatedEvent;

import java.util.concurrent.TimeUnit;

/**
 * Lợi ích chính của Guava Cache:
 *
 * Hiệu năng cao: Được tối ưu hóa cho hiệu suất cao và ít lock
 * Linh hoạt: Nhiều tùy chọn cấu hình
 * Tự động cleanup: Tự động xóa các entry hết hạn
 * Thread-safe: An toàn cho môi trường đa luồng
 * Monitoring: Cung cấp thống kê chi tiết
 * Dễ sử dụng: API đơn giản và trực quan
 * Một số best practices:
 *
 * Luôn xác định kích thước tối đa cache
 * Sử dụng expiration policy phù hợp
 * Xử lý exceptions khi load data
 * Monitoring hiệu suất cache qua stats
 * Tránh cache quá nhiều dữ liệu không cần thiết
 */
public class LocalCache {
    private final static Cache<Long, OrderCreatedEvent> orderLocalCache = CacheBuilder.newBuilder()
            .initialCapacity(100)              // Dung lượng khởi tạo
            .maximumSize(1000)                 // Kích thước tối đa
            .maximumWeight(10000)              // Trọng số tối đa
            .concurrencyLevel(10)              // Mức độ truy cập đồng thời
            .expireAfterAccess(5, TimeUnit.MINUTES)  // Hết hạn sau lần truy cập cuối
            .expireAfterWrite(10, TimeUnit.MINUTES)  // Hết hạn sau khi ghi
            .weakKeys()                        // Weak references cho keys
            .weakValues()                      // Weak references cho values
            .softValues()                      // Soft references cho values
            .recordStats()                     // Ghi nhận thống kê
            .build();
}
