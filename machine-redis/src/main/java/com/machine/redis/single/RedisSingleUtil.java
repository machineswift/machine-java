package com.machine.redis.single;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;

public class RedisSingleUtil {
	private static Jedis jedis;

	public static Jedis createJedis(String host, int port) {
		jedis = new Jedis(host, port);
		return jedis;
	}

	public static Jedis createJedis(String host, int port, String password) {
		jedis = new Jedis(host, port);
		if (!password.isEmpty())
			jedis.auth(password);
		return jedis;
	}

	static {
		jedis =createJedis("192.168.100.120", 6379);
	}
	
	public void testString() {
        //-----添加数据----------  
        jedis.set("name","bhz");//向key-->name中放入了value-->xinxin  
        System.out.println(jedis.get("name"));//执行结果：xinxin  
        
        jedis.append("name", " is my lover"); //拼接
        System.out.println(jedis.get("name")); 
        
        jedis.del("name");  //删除某个键
        System.out.println(jedis.get("name"));
        //设置多个键值对
        jedis.mset("name","bhz","age","27","qq","174754613");
        jedis.incr("age"); //进行加1操作
        System.out.println(jedis.get("name") + "-" + jedis.get("age") + "-" + jedis.get("qq"));
    }
	
	 /**
     * redis操作Map
     */
    public void testMap() {
        //-----添加数据----------  
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "xinxin");
        map.put("age", "22");
        map.put("qq", "123456");
        jedis.hmset("user",map);
        //取出user中的name，执行结果:[minxr]-->注意结果是一个泛型的List  
        //第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变参数  
        List<String> rsmap = jedis.hmget("user", "name", "age", "qq");
        System.out.println(rsmap);  
        //删除map中的某个键值  
        jedis.hdel("user","age");
        System.out.println(jedis.hmget("user", "age")); //因为删除了，所以返回的是null  
        System.out.println(jedis.hlen("user")); //返回key为user的键中存放的值的个数2 
        System.out.println(jedis.exists("user"));//是否存在key为user的记录 返回true  
        System.out.println(jedis.hkeys("user"));//返回map对象中的所有key  
        System.out.println(jedis.hvals("user"));//返回map对象中的所有value 
  
        Iterator<String> iter=jedis.hkeys("user").iterator();  
        while (iter.hasNext()){  
            String key = iter.next();  
            System.out.println(key+":"+jedis.hmget("user",key));  
        }  
    }
    
    /** 
     * jedis操作List 
     */  
    public void testList(){  
        //开始前，先移除所有的内容  
        jedis.del("java framework");  
        System.out.println(jedis.lrange("java framework",0,-1));  
        //先向key java framework中存放三条数据  
        jedis.lpush("java framework","spring");  
        jedis.lpush("java framework","struts");  
        jedis.lpush("java framework","hibernate");  
        //再取出所有数据jedis.lrange是按范围取出，  
        // 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有  
        System.out.println(jedis.lrange("java framework",0,-1));  
        
        jedis.del("java framework");
        jedis.rpush("java framework","spring");  
        jedis.rpush("java framework","struts");  
        jedis.rpush("java framework","hibernate"); 
        System.out.println(jedis.lrange("java framework",0,-1));
    }  
    
    /** 
     * jedis操作Set 
     */  
    public void testSet(){  
        //添加  
        jedis.sadd("userset","liuling");  
        jedis.sadd("userset","xinxin");  
        jedis.sadd("userset","ling");  
        jedis.sadd("userset","zhangxinxin");
        jedis.sadd("userset","who");  
        //移除noname  
        jedis.srem("userset","who");  
        System.out.println(jedis.smembers("userset"));//获取所有加入的value  
        System.out.println(jedis.sismember("userset", "who"));//判断 who 是否是user集合的元素  
        System.out.println(jedis.srandmember("userset"));  
        System.out.println(jedis.scard("userset"));//返回集合的元素个数  
    } 
	public static void main(String[] args)  {
		RedisSingleUtil util = new RedisSingleUtil();
		util.testString();
		util.testMap();
		util.testList();
		util.testSet();
	}
}