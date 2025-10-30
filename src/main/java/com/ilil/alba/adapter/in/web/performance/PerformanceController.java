package com.ilil.alba.adapter.in.web.performance;

import com.ilil.alba.util.VirtualThreadPerformanceTest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 가상 스레드와 일반 스레드의 성능을 비교하는 REST API
 */
@RestController
@RequestMapping("/api/performance")
public class PerformanceController {

    /**
     * 가상 스레드 방식 성능 테스트
     * GET /api/performance/virtual-thread?tasks=1000&duration=100
     */
    @GetMapping("/virtual-thread")
    public Map<String, Object> testVirtualThread(
            @RequestParam(defaultValue = "1000") int tasks,
            @RequestParam(defaultValue = "100") long duration) throws InterruptedException {

        long memoryBefore = VirtualThreadPerformanceTest.getMemoryUsageMB();
        VirtualThreadPerformanceTest.PerformanceResult result =
            VirtualThreadPerformanceTest.testWithVirtualThreads(tasks, duration);
        long memoryAfter = VirtualThreadPerformanceTest.getMemoryUsageMB();

        Map<String, Object> response = new HashMap<>();
        response.put("testType", "Virtual Thread");
        response.put("result", result);
        response.put("memoryBefore", memoryBefore);
        response.put("memoryAfter", memoryAfter);
        response.put("memoryDifference", memoryAfter - memoryBefore);

        return response;
    }

    /**
     * 고정 스레드 풀 성능 테스트
     * GET /api/performance/fixed-thread-pool?tasks=1000&duration=100
     */
    @GetMapping("/fixed-thread-pool")
    public Map<String, Object> testFixedThreadPool(
            @RequestParam(defaultValue = "1000") int tasks,
            @RequestParam(defaultValue = "100") long duration) throws InterruptedException {

        long memoryBefore = VirtualThreadPerformanceTest.getMemoryUsageMB();
        VirtualThreadPerformanceTest.PerformanceResult result =
            VirtualThreadPerformanceTest.testWithFixedThreadPool(tasks, duration);
        long memoryAfter = VirtualThreadPerformanceTest.getMemoryUsageMB();

        Map<String, Object> response = new HashMap<>();
        response.put("testType", "Fixed Thread Pool");
        response.put("result", result);
        response.put("memoryBefore", memoryBefore);
        response.put("memoryAfter", memoryAfter);
        response.put("memoryDifference", memoryAfter - memoryBefore);

        return response;
    }
}