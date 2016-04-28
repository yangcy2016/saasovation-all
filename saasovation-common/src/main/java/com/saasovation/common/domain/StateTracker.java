package com.saasovation.common.domain;

/**
 * @author : huanghy
 * @create : 2016/4/19 0019 обнГ 3:59
 * @since : ${VERSION}
 */
public interface StateTracker {
    long id();

    long beginTrackMills();

    boolean isCompleted();

    boolean hasTimedOut();

}
