package sktest.ling.zero.util.concurrent.locks;

import org.junit.jupiter.api.Test;
import org.shaneking.ling.zero.util.UUID0;
import org.shaneking.ling.zero.util.concurrent.locks.DistributedFileLock;
import org.shaneking.ling.zero.util.concurrent.locks.DistributedLockable;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class DistributedFileLockTest {
  @Test
  void test() {
    String lockKey = "/tmp/org.shaneking.ling.zero/sktest.ling.zero.util.concurrent.locks.DistributedFileLockTest.lock";
    new File(lockKey).delete();

    DistributedLockable distributedLock = new DistributedFileLock();
    distributedLock.lock(lockKey, 1, 1);

    DistributedLockable distributedLock2 = new DistributedFileLock();
    distributedLock2.lock(lockKey, 1, 1);

    assertTrue(distributedLock.locked());
    assertFalse(distributedLock2.locked());
    assertThrows(IllegalArgumentException.class, () -> distributedLock.lock("/tmp/org.shaneking.ling.zero/" + UUID0.cUl33(), 1, 1));

    distributedLock2.unlock();
    distributedLock.unlock();
  }
}
