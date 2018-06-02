package com.dev.wuxl.jedis_demo;

import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:wu.xuanle@immomo.com">wu.xuanle</a>
 * @create 18/5/30
 */
public class Demo3 {

  public static void main(String[] args) {
    JedisPoolConfig config = new JedisPoolConfig();
    config.setMaxTotal(3);
    config.setMaxIdle(3);
    config.setMinIdle(0);
    config.setBlockWhenExhausted(true);
    config.setMaxWaitMillis(2000);
    List<JedisShardInfo> infos = new ArrayList<JedisShardInfo>();
    infos.add(new JedisShardInfo("localhost", 6379));
    infos.add(new JedisShardInfo("localhost", 6380));
    infos.add(new JedisShardInfo("localhost", 6371));
    ShardedJedisPool pool = new ShardedJedisPool(config, infos);
    ShardedJedis jedis = pool.getResource();
    for(int i=0; i<10; i++){
      jedis.set("key_test1_" + i, ""+i);
    }
    for(int i=0; i<10; i++){
      Client client = jedis.getShard("key_test1_" + i).getClient();
      System.out.println(i + " " + client.getHost() + ": " + client.getPort());
    }
//    System.out.println(jedis.get("key_test_03"));

  }



}
