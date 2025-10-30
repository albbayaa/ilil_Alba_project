package com.ilil.alba.util;

import java.util.concurrent.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 일반 스레드 vs 가상 스레드의 성능을 비교하는 유틸리티
 * Java 21의 가상 스레드를 사용하여 성능 차이를 가시적으로 보여줍니다.
 */
public class VirtualThreadPerformanceTest {

    /**
     * 성능 테스트 결과를 담는 클래스
     */
    public static class PerformanceResult {
        public String threadType;
        public int taskCount;
        public long executionTimeMs;
        public double tasksPerSecond;
        public String formattedResult;

        public PerformanceResult(String threadType, int taskCount, long executionTimeMs) {
            this.threadType = threadType;
            this.taskCount = taskCount;
            this.executionTimeMs = executionTimeMs;
            this.tasksPerSecond = (double) taskCount * 1000 / executionTimeMs;
            this.formattedResult = formatResult();
        }

        private String formatResult() {
            return String.format(
                    " [%s] : " +
                    " 작업 수: %,d개 , " +
                    " 실행 시간: %,dms , " +
                    " 처리량: %.2f tasks/sec",
                    threadType, taskCount, executionTimeMs, tasksPerSecond
            );
        }

        @Override
        public String toString() {
            return formattedResult;
        }
    }

    /**
     * 일반 스레드 풀(ThreadPool)로 작업 실행
     */
    public static PerformanceResult testWithFixedThreadPool(int taskCount, long taskDurationMs)
            throws InterruptedException {
        long startTime = System.currentTimeMillis();

        // CPU 코어 수의 2배 정도로 스레드 풀 크기 설정
        int threadPoolSize = Math.max(10, Runtime.getRuntime().availableProcessors() * 2);
        // 스레드 threadPoolSize 개와 작업 대기 큐가 존재
        ExecutorService executorService = Executors.newFixedThreadPool(threadPoolSize);

        // “모든 작업이 끝날 때까지 기다리는” 도구
        CountDownLatch latch = new CountDownLatch(taskCount);

        for (int i = 0; i < taskCount; i++) {
            executorService.submit(() -> {
                try {
                    // I/O 작업 시뮬레이션
                    // 얘 자체가 인터럽트를 발생한다.
                    Thread.sleep(taskDurationMs);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    latch.countDown();
                }
            });
        }

        // 모든 작업이 완료될 때까지 대기
        // latch의 카운트가 0이 될 때까지 이 줄에서 프로그램이 멈춤
        latch.await();
        executorService.shutdown();

        long endTime = System.currentTimeMillis();
        return new PerformanceResult("고정 스레드 풀 (FixedThreadPool)",
                taskCount, endTime - startTime);
    }

    /**
     * 가상 스레드(Virtual Thread)로 작업 실행
     */
    public static PerformanceResult testWithVirtualThreads(int taskCount, long taskDurationMs)
            throws InterruptedException {
        long startTime = System.currentTimeMillis();

        // 가상 스레드 Executor 생성
        ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();

        CountDownLatch latch = new CountDownLatch(taskCount);

        for (int i = 0; i < taskCount; i++) {
            executorService.submit(() -> {
                try {
                    // I/O 작업 시뮬레이션
                    Thread.sleep(taskDurationMs);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    latch.countDown();
                }
            });
        }

        // 모든 작업이 완료될 때까지 대기
        latch.await();
        executorService.shutdown();

        long endTime = System.currentTimeMillis();
        return new PerformanceResult("가상 스레드 (VirtualThread)", taskCount,
                endTime - startTime);
    }

    /**
     * 성능 비교 리포트 생성
     */
    public static class ComparisonReport {
        public List<PerformanceResult> results;
        public String summary;

        public ComparisonReport(List<PerformanceResult> results) {
            this.results = results;
            this.summary = generateSummary();
        }

        private String generateSummary() {
            if (results.isEmpty()) return "";

            PerformanceResult fastest = results.get(0);
            for (PerformanceResult result : results) {
                if (result.executionTimeMs < fastest.executionTimeMs) {
                    fastest = result;
                }
            }

            StringBuilder sb = new StringBuilder();
            sb.append("\n📊 === 성능 비교 결과 ===\n\n");

            for (PerformanceResult result : results) {
                sb.append(result).append("\n\n");
            }

            double speedup = (double) results.get(0).executionTimeMs / fastest.executionTimeMs;
            sb.append(String.format("⚡ 최고 성능: %s (%.2fx 빠름)\n", fastest.threadType, speedup));

            return sb.toString();
        }

        @Override
        public String toString() {
            return summary;
        }
    }

    /**
     * 메모리 사용량 측정
     */
    public static long getMemoryUsageMB() {
        Runtime runtime = Runtime.getRuntime();
        return (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024);
    }

}