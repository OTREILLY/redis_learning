package com.dev.wuxl.jedis_demo;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="mailto:wu.xuanle@immomo.com">wu.xuanle</a>
 * @create 18/5/31
 */
public class Demo4 {

  public static void main(String[] args) {
    JedisPoolConfig config = new JedisPoolConfig();
    config.setMaxTotal(3);
    config.setMaxIdle(3);
    config.setMinIdle(0);
    config.setBlockWhenExhausted(true);
    config.setMaxWaitMillis(2000);
    Set<HostAndPort> servers = new HashSet<HostAndPort>();
    servers.add(new HostAndPort("localhost", 6379));
    servers.add(new HostAndPort("localhost", 6380));
    servers.add(new HostAndPort("localhost", 6371));
    JedisCluster cluster = new JedisCluster(servers, config);
    cluster.set("key_test_04", "1");
    System.out.println(cluster.get("key_test_04"));


  }

}
