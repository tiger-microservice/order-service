# Các laại local cache
Ehcache, Guava cache, Caffeine Cache

# Lợi ích của Guava Cache và Best Practices

## Lợi ích chính

1. **Hiệu năng cao**
    - Được tối ưu hóa cho hiệu suất cao
    - Cơ chế lock tối thiểu
    - Xử lý nhanh cho cả read và write operations

2. **Linh hoạt**
    - Nhiều tùy chọn cấu hình
    - Dễ dàng tùy chỉnh theo nhu cầu
    - Hỗ trợ nhiều kiểu dữ liệu

3. **Tự động cleanup**
    - Tự động xóa các entry hết hạn
    - Quản lý bộ nhớ hiệu quả
    - Giảm thiểu memory leaks

4. **Thread-safe**
    - An toàn cho môi trường đa luồng
    - Hỗ trợ concurrent access
    - Cơ chế đồng bộ hóa hiệu quả

5. **Monitoring**
    - Cung cấp thống kê chi tiết
    - Theo dõi hiệu suất cache
    - Dễ dàng debug và tối ưu

6. **Dễ sử dụng**
    - API đơn giản và trực quan
    - Documentation đầy đủ
    - Tích hợp dễ dàng

## Best Practices

1. **Xác định kích thước cache phù hợp**
    - Luôn set maximumSize hoặc maximumWeight
    - Cân nhắc memory footprint
    - Tránh cache quá nhiều dữ liệu

2. **Cấu hình expiration policy**
    - Sử dụng expireAfterWrite cho data có tính time-based
    - Sử dụng expireAfterAccess cho data access-based
    - Đặt thời gian expire phù hợp với business logic

3. **Xử lý exceptions**
    - Implement error handling cho cache loading
    - Có fallback mechanism khi cache fail
    - Log các lỗi quan trọng

4. **Monitoring và maintenance**
    - Sử dụng recordStats() để theo dõi metrics
    - Kiểm tra hit rate và miss rate
    - Điều chỉnh cấu hình dựa trên metrics

5. **Tối ưu hóa performance**
    - Cấu hình concurrencyLevel phù hợp
    - Sử dụng initialCapacity hợp lý
    - Tránh cache những object quá lớn

6. **Quản lý memory**
    - Sử dụng weakKeys()/weakValues() khi cần
    - Cân nhắc softValues() cho cache lớn
    - Định kỳ kiểm tra memory usage

7. **Testing**
    - Unit test cache behavior
    - Test concurrent access
    - Verify expiration policies

8. **Documentation**
    - Document cache configuration
    - Ghi chú các assumptions
    - Cập nhật documentation khi thay đổi