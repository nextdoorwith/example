package ndw.slf4j.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	public static void main(String[] args) {
		new LogTest().doSomething();
	}

	public void doSomething() {

		int arg1 = 100;
		String arg2 = "abc";

		logger.info("myname is {}", logger.getName());

		logger.trace("trace:");
		logger.debug("debug: {}", arg1);
		logger.info("info: {}", arg2);
		logger.warn("warn: {}, {}", arg1, arg2);
		logger.error("error: {}, {}", arg1, arg2);

		logger.info("引数なしの場合: {}");
		logger.info("引数が多すぎる場合: {}", arg1, arg2);

		if (logger.isTraceEnabled()) {
			logger.trace("trace is enabled!");
		}

	}
}
