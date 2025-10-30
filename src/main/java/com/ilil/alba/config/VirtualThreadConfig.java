package com.ilil.alba.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 가상 스레드(Virtual Thread) 설정
 * Java 21부터 지원되는 가상 스레드를 Spring 애플리케이션에 통합합니다.
 */
@Configuration
public class VirtualThreadConfig {

    /**
     * 가상 스레드 Executor 빈 생성
     * 매우 가벼운 가상 스레드를 사용하여 고성능 동시 작업 처리
     */
    @Bean(name = "virtualThreadExecutor")
    public ExecutorService virtualThreadExecutor() {
        return Executors.newVirtualThreadPerTaskExecutor();
    }

    /**
     * 고정 스레드 풀 Executor 빈 생성 (비교용)
     */
    @Bean(name = "fixedThreadPoolExecutor")
    public ExecutorService fixedThreadPoolExecutor() {
        int coreCount = Runtime.getRuntime().availableProcessors();
        return Executors.newFixedThreadPool(coreCount * 2);
    }

    /**
     * 캐시 스레드 풀 Executor 빈 생성 (비교용)
     */
    @Bean(name = "cachedThreadPoolExecutor")
    public ExecutorService cachedThreadPoolExecutor() {
        return Executors.newCachedThreadPool();
    }

    /**
     * 스레드 풀 정보 로깅
     */
    public static class ThreadPoolInfo {
        private static final int CORE_COUNT = Runtime.getRuntime().availableProcessors();

        public static void printThreadPoolInfo() {
            System.out.println("==========================================");
            System.out.println("📊 Virtual Thread Configuration");
            System.out.println("==========================================");
            System.out.println("🔧 CPU Core Count: " + CORE_COUNT);
            System.out.println("🟢 Virtual Thread: Per-task executor (매우 가벼움)");
            System.out.println("🔴 Fixed Thread Pool: " + (CORE_COUNT * 2) + " threads");
            System.out.println("🟡 Cached Thread Pool: 필요에 따라 동적 생성");
            System.out.println("==========================================");
        }
    }
}