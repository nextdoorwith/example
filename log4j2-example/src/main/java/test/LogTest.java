package test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {

	public static void main(String[] args) {

		Logger logger;

		logger = LoggerFactory.getLogger("a.b.c1");
		log(logger);
		logger = LoggerFactory.getLogger("a.b.c2");
		log(logger);
		logger = LoggerFactory.getLogger("a.b.c3");
		log(logger);
		logger = LoggerFactory.getLogger("a.b.c4");
		log(logger);
		logger = LoggerFactory.getLogger("a.b.c5");
		log(logger);
		logger = LoggerFactory.getLogger("a.b.c6");
		log(logger);

	}

	public static void log(Logger logger) {
		logger.trace("TRACE test");
		logger.debug("DEBUG test");
		logger.info("INFO  test");
		logger.warn("WARN  test");
		logger.error("ERROR test");
	}

}
