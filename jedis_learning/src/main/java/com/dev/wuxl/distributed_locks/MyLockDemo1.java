package com.dev.wuxl.distributed_locks;

import redis.clients.jedis.Jedis;

import java.util.Random;

/**
 * @author <a href="mailto:wu.xuanle@immomo.com">wu.xuanle</a>
 * @create 18/5/31
 */
public class MyLockDemo1 {

  final static Random random = new Random();
  public static void main(String[] args) throws InterruptedException {
    final MyLock myLock = new MyLock();
    final int[] a = {0};
//    System.out.println(jedis.info());
    for(int i=0; i<10; i++){
      final int index = i;
      new Thread(new Runnable() {
        @Override public void run() {
          int inc = index;
          if(random.nextBoolean()) {
            inc = -1 * inc;
          }
          Jedis jedis = new Jedis();
          if(a[0]+inc<0){
            myLock.tryGetLock(jedis, "MyLockDemo1_key", "1", 10);
          }
          a[0] += inc;
//          try {
//            Thread.sleep(random.nextInt(10));
//          } catch (InterruptedException e) {
//            e.printStackTrace();
//          }
//          System.out.println(index);
          myLock.releaseLock(jedis, "MyLockDemo1_key", "1");
        }
      }).start();
      Thread.sleep(10);
    }
    System.out.println(a[0]);
  }




}
