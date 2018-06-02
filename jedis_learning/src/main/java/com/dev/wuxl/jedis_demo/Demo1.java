package com.dev.wuxl.jedis_demo;

import redis.clients.jedis.Jedis;

/**
 * @author <a href="mailto:wu.xuanle@immomo.com">wu.xuanle</a>
 * @create 18/5/30
 */
public class Demo1 {

  public static void main(String[] args) {
    Jedis jedis = new Jedis("localhost", 6379);
    jedis.set("test_key_0", "1");
    System.out.println(jedis.get("test_key_0"));
  }

}
