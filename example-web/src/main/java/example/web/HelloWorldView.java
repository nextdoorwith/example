package example.web;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * サンプルページ
 */
@Named
@RequestScoped
public class HelloWorldView {

	/** ロガー */
	private Logger logger = LoggerFactory.getLogger(getClass());

	/** 初期化 */
	public void init() {
		logger.info("初期化");
	}

	/** 実行ボタンアクション */
	public String execute() {
		logger.info("実行");
		return null;
	}

}
