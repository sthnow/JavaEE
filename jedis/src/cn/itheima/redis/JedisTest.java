package cn.itheima.redis;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisTest {

	@Test
	public void testJedis1() throws Exception{
		//创建和redis的连接
		Jedis jedis = new Jedis("192.168.93.88", 6379);
		
		//存入
		jedis.set("key2", "2");
		//取出
		System.out.println(jedis.get("key2"));
		//关闭
		jedis.close();
	}

	@Test
	public void testJedisPool() throws Exception{
		//创建连接池
		JedisPool pool = new JedisPool("192.168.93.88", 6379);
		//获取连接
		Jedis jedis = pool.getResource();
		//存入
		jedis.set("key3", "aaa");
		//取出
		System.out.println(jedis.get("key3"));
		//使用连接时,连接使用完后一定要关闭,关闭后连接会自动回到连接池供别人使用,如果一直不关闭则连接被耗尽之后就会死机
		jedis.close();
		//关闭连接池
		pool.close();
	}
}
