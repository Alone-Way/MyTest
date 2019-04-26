package com.along.studymongo.util;

import java.net.InetAddress;

/**
 * 42位的时间前缀+10位的节点标识+12位的sequence避免并发的数字（12位不够用时强制得到新的时间前缀）
 * <p>
 * <b>对系统时间的依赖性非常强，需要关闭ntp的时间同步功能，或者当检测到ntp时间调整后，拒绝分配id。
 * 
 * @author sumory.wu
 * @date 2012-2-26 下午6:40:28
 */
public class IdWorker {

	private static long workerId;
	private static long datacenterId;
	private static SnowflakeIdWorker work;

	private volatile static IdWorker singleton;

	private IdWorker() {
	}

	public static IdWorker inctance() {
		if (singleton == null) {
			synchronized (IdWorker.class) {
				if (singleton == null) {
					long sum = ip();
					workerId = getWorkerId(sum);
					datacenterId = getDatacenterId(sum);
					work = new SnowflakeIdWorker(workerId, datacenterId);
					singleton = new IdWorker();
				}
			}
		}
		return singleton;
	}

	public Long get() {
		return work.nextId();
	}

	/**
	 * 获取workerId 取 sum二进制的前5位
	 * 
	 * @param sum
	 * @return
	 */
	private static long getWorkerId(long sum) {
		if (sum > 1023 || sum < 0) {
			return 0;
		}

		if (sum < 32) {// 0到31(11111) 最多5位 直接返回
			return 31;
		}
		// 32到1023
		return sum >> 5;
	}

	/**
	 * 取 sum二进制的后5位
	 * @param sum
	 * @return
	 */
	private static long getDatacenterId(long sum) {
		if (sum > 1023 || sum < 0) {
			return 0;
		}
		
		if (sum < 32) {// 0到31(11111) 最多5位 返回0
			return 0;
		}
		return sum & 31;
	}

	private static long ip() {
		try {
			String ip = InetAddress.getLocalHost().getHostAddress();

			String[] arr = ip.split("\\.");
			long sum = 0;
			for (String num : arr) {
				sum += Long.parseLong(num);
			}
			return sum;
		} catch (Exception e) {
			return 0;
		}
	}

}