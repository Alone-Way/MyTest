package com.along.studymongo.util;

/**
 * ID生成器
 * @author ryan
 * @version V1.0
 */
public class IDGeneratory {
    public static Long getId() {
        return IdWorker.inctance().get();
    }
}
