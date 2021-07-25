package org.shaneking.ling.zero.util.concurrent.locks;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.shaneking.ling.zero.lang.AC0;
import org.shaneking.ling.zero.lang.Integer0;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

//File protocol like NFS, GlusterFS
@Slf4j
public class DistributedFileLock implements Lockable {
  @Getter
  private String fp;//fullPath,filePath
  @Getter
  private int releases = 3;

  private RandomAccessFile randomAccessFile;
  private FileChannel fileChannel;
  private FileLock fileLock;

  public DistributedFileLock() {
  }

  public DistributedFileLock(String fp) {
    this.fp = fp;
  }

  public DistributedFileLock(String fp, int releases) {
    this.fp = fp;
    this.releases = releases;
  }

  @Override
  public DistributedFileLock lock(int tryTimes, int intervalSeconds) {
    try {
      randomAccessFile = new RandomAccessFile(fp, "rw");
      fileChannel = randomAccessFile.getChannel();
      fileLock = fileChannel.tryLock();
      int times = 0;
      while (fileLock == null && times++ < tryTimes) {
        Thread.sleep(intervalSeconds * 1000);
        fileLock = fileChannel.tryLock();
      }
    } catch (Exception e) {
      log.error(fp, e);
      unlock();
    }
    return this;
  }

  @Override
  public boolean locked() {
    return fileLock == null;
  }

  @Override
  public void unlock() {
    try {
      close();
    } catch (Exception e) {
      log.error(fp, e);
    }
  }

  @Override
  public void close() throws Exception {
    AC0.close(fileLock, Integer0.lt2d(releases, 3));
    AC0.close(fileChannel, Integer0.lt2d(releases, 3));
    AC0.close(randomAccessFile, Integer0.lt2d(releases, 3));
  }
}
