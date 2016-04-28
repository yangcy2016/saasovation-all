package com.saasovation.common.domain;

public abstract class ConcurrencySafeEntity {
	//乐观并发委派标识
	protected String identity;
	//乐观并发版本号
	protected long version;
}
