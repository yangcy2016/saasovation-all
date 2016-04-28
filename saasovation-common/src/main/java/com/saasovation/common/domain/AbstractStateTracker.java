package com.saasovation.common.domain;

/**
 * @author : huanghy
 * @create : 2016/4/19 0019 ÏÂÎç 4:11
 * @since : ${VERSION}
 */
public abstract class AbstractStateTracker implements  StateTracker{

    private long beginTrackMills;
    private long id;
    private volatile boolean isCompleted;
    private long timeout;


    public AbstractStateTracker(long id) {
        this(id,0);
    }

    public AbstractStateTracker(long id,long timeout) {
        this.beginTrackMills = System.currentTimeMillis();
        this.id = id;
        this.timeout = timeout;
    }

    public long id() {
        return this.id;
    }

    public long beginTrackMills() {
        return this.beginTrackMills;
    }

    public boolean isCompleted() {
        return this.isCompleted;
    }

    public void setCompleted(){
        this.isCompleted = true;
    }

    public boolean hasTimedOut() {
        return timeout==0 ? false:System.currentTimeMillis() > beginTrackMills+timeout;
    }
}
