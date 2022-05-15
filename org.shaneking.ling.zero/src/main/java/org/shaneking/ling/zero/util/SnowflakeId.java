package org.shaneking.ling.zero.util;

//https://zhuanlan.zhihu.com/p/85837641
public class SnowflakeId {
//  public final long START = 1420041600000L;//2015-01-01
  public final long START = 1651968000000L;//2022-05-08
  public final long workerIdBits = 5L;
  public final long datacenterIdBits = 5L;
  public final long sequenceBits = 12L;
  private final long maxWorkerId = -1L ^ (-1L << workerIdBits);//31=2^workerIdBits-1
  private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);//31=2^workerIdBits-1
  private final long workerIdShift = sequenceBits;//12
  private final long datacenterIdShift = sequenceBits + workerIdBits;//17=12+5
  private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;//22=12+5+5
  private final long sequenceMask = -1L ^ (-1L << sequenceBits);//4095=2^sequenceBits-1 (0b111111111111=0xfff=4095)
  private long workerId;//0~31
  private long datacenterId;//0~31
  private long sequence = 0L;//0~4095
  private long lastTimestamp = -1L;

  public SnowflakeId(long workerId, long datacenterId) {
    if (workerId > maxWorkerId || workerId < 0) {
      throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
    }
    if (datacenterId > maxDatacenterId || datacenterId < 0) {
      throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
    }
    this.workerId = workerId;
    this.datacenterId = datacenterId;
  }

  public synchronized long nextId() {
    long timestamp = timeGen();
    if (timestamp < lastTimestamp) {
      throw new RuntimeException(String.format("Clock moved backwards. Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
    }
    if (lastTimestamp == timestamp) {//same timestamp
      sequence = (sequence + 1) & sequenceMask;
      if (sequence == 0) {//over, block to next timestamp
        timestamp = tilNextMillis(lastTimestamp);
      }
    } else {//difference timestamp
      sequence = 0L;
    }
    lastTimestamp = timestamp;
    // 64 bits
    return ((timestamp - START) << timestampLeftShift) //
      | (datacenterId << datacenterIdShift) //
      | (workerId << workerIdShift) //
      | sequence;//
  }

  //block to next timestamp
  protected long tilNextMillis(long lastTimestamp) {
    long timestamp = timeGen();
    while (timestamp <= lastTimestamp) {
      timestamp = timeGen();
    }
    return timestamp;
  }

  protected long timeGen() {
    return System.currentTimeMillis();
  }
}
