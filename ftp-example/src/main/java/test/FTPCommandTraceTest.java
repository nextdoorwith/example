package test;

import java.io.IOException;

import org.apache.commons.net.ProtocolCommandEvent;
import org.apache.commons.net.ProtocolCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FTPCommandTraceTest {

	private static final String FTP_SERVER = "192.168.220.3";
	private static final String FTP_USER_NAME = "valuser";
	private static final String FTP_USER_PASS = "valuser";

	private static Logger logger = LoggerFactory.getLogger(FTPCommandTraceTest.class);

	public static void main(String[] args) throws IOException {

		FTPClient client = new FTPClient();
		client.addProtocolCommandListener(new ExampleCommandListener());

		// FTPサーバ操作例
		client.connect(FTP_SERVER);
		client.login(FTP_USER_NAME, FTP_USER_PASS);
		for (FTPFile file : client.listFiles(".")) {
			logger.debug("name={}", file.getName());
		}
	}

}

class ExampleCommandListener implements ProtocolCommandListener {

	private static Logger logger = LoggerFactory.getLogger(ExampleCommandListener.class);

	public void protocolCommandSent(ProtocolCommandEvent event) {
		logger.trace("> " + event.getCommand());
	}

	public void protocolReplyReceived(ProtocolCommandEvent event) {
		StringBuilder sb = new StringBuilder();
		logger.trace("< " + event.getMessage());
	}

}
