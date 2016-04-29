package com.saasovation.common.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * @author : huanghy
 * @create : 2016/4/19 0019 ÏÂÎç 3:35
 * @since : ${VERSION}
 */
public abstract class AbstractDomainEvent implements DomainEvent {
    @Expose
    @SerializedName("id")
    private long id;

    @Expose
    @SerializedName("occurredOn")
    private Date occurredOn;

    private long version;

    public AbstractDomainEvent() {
        this(0L,new Date());
    }
    public AbstractDomainEvent(long id, Date occurred) {
        this.id = id;
        this.occurredOn = occurred;
    }

    public long id() {
        return this.id;
    }

    public Date occurredOn() {
        return this.occurredOn;
    }
	public long version() {
		return this.version;
	}

}
