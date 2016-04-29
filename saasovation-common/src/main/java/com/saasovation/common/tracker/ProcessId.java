package com.saasovation.common.tracker;

/**
 * @author : huanghy
 * @create : 2016/4/29 0029 上午 10:32
 * @since : ${VERSION}
 */
public class ProcessId {
    private String id;

    public ProcessId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static ProcessId existingProcessId(String pid) {
        return new ProcessId(pid);
    }

    public static String newProcessId() {
        return "process-"+System.currentTimeMillis();
    }
}
