package org.shaneking.ling.zero.util.concurrent.locks;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.shaneking.ling.zero.lang.AC0;
import org.shaneking.ling.zero.lang.Integer0;
import org.shaneking.ling.zero.lang.String0;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Objects;

//File protocol like NFS, GlusterFS
@Slf4j
public class DistributedFileLock implements DistributedLockable {
  @Getter
  private String lockKey;//fullPath,filePath
  @Getter
  @Setter
  private int releases = 3;

  private RandomAccessFile randomAccessFile;
  private FileChannel fileChannel;
  private FileLock fileLock;

  @Override
  public DistributedFileLock lock(String lockKey, int tryTimes, int intervalSeconds) {
    this.lockKey = String0.nullOrEmptyTo(this.lockKey, lockKey);
    if (Objects.equals(this.lockKey, lockKey)) {
      try {
        if (!locked()) {
          File lockFile = new File(this.lockKey);
          lockFile.getParentFile().mkdirs();
          randomAccessFile = new RandomAccessFile(lockFile, "rw");
          fileChannel = randomAccessFile.getChannel();
          int times = 0;
          while (fileLock == null && times < tryTimes) {
            if (times > 0) Thread.sleep(intervalSeconds * 1000);
            try {
              fileLock = fileChannel.tryLock();
            } catch (Exception e) {
              ///ignore
            }
            times++;
          }
        }
      } catch (Exception e) {
        log.error(this.lockKey, e);
        unlock();
      }
      return this;
    } else {
      throw new IllegalArgumentException(lockKey);
    }
  }

  @Override
  public boolean locked() {
    return fileLock != null;
  }

  @Override
  public void unlock() {
    try {
      close();
    } catch (Exception e) {
      log.error(this.lockKey, e);
    }
  }

  @Override
  public void close() throws Exception {
    AC0.close(fileLock, Integer0.lt2d(releases, 3));
    AC0.close(fileChannel, Integer0.lt2d(releases, 3));
    AC0.close(randomAccessFile, Integer0.lt2d(releases, 3));
    this.lockKey = null;
  }
}
