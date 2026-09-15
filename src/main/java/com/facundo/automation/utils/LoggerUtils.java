package com.facundo.automation.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtils {
    private static final Logger logger = LoggerFactory.getLogger(LoggerUtils.class);

    public LoggerUtils() {
    }

    public static void start(String stage) {
        logger.info("=================================================================================================");
        logger.info("✅ START - {}", stage);
        logger.info("==========================================================================================\n");

    }

    public static void end(String stage) {
        logger.info("=================================================================================================");
        logger.info("✅ END - {}", stage);
        logger.info("==========================================================================================\n");
    }

    public static void success(String message) {
        logger.info("✅ {}", message);
    }

    public static void error(String message) {
        logger.warn("❌ {}", message);
    }

    public static void info(String message) {
        logger.info("💡 {}", message);
    }
}
