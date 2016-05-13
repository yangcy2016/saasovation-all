package com.saasovation.common.port.adapter.messaging.rabbitmq;

public class ConnectionSettings {
	private String userName = "guest";
	private String password = "guest";
	private String host = "172.28.250.91";
	private int port = 5672;

	/**
	 *quality of service
	 */
	private int qos = 100;

	/*second**/
	private long retryInterval = 5;

	private int headBeat = 5;

	private static final ConnectionSettings instance = new ConnectionSettings();

	public static ConnectionSettings instance() {
		return instance;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}


	public long getRetryInterval() {
		return retryInterval;
	}

	public void setRetryInterval(long retryInterval) {
		this.retryInterval = retryInterval;
	}

	public int getQos() {
		return qos;
	}

	public void setQos(int qos) {
		this.qos = qos;
	}

	public int getHeadBeat() {
		return headBeat;
	}

	public void setHeadBeat(int headBeat) {
		this.headBeat = headBeat;
	}
}
