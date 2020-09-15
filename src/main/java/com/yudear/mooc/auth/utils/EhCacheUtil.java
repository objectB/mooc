package com.yudear.mooc.auth.utils;

import com.yudear.mooc.common.exception.BizException;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;

import java.net.URL;

/**
 * 缓存工具类
 */
public class EhCacheUtil {

    private static final Logger log = LoggerFactory.getLogger(EhCacheUtil.class);


    private static URL url = ClassUtils.getDefaultClassLoader().getResource("ehcache.xml");

    private CacheManager manager;

    private static EhCacheUtil ehCache;

    public static final String TOKEN_CACHE = "user_token";
    public static final String USER_TOKEN_KEY = "user_token_key";


    /**
     * 获得缓存配置管理
     * @param path
     */
    private EhCacheUtil(URL path) {
        try {
            manager = CacheManager.create(path);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取配置文件错误：{}",e.getMessage());
        }
    }

    /**
     * 初始化缓存管理类
     * @return
     */
    public static EhCacheUtil getInstance() {
        try {
            if (ehCache== null) {
                ehCache= new EhCacheUtil(url);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ehCache;
    }

    /**
     * 获取Cache类
     * @param cacheName
     * @return
     */
    public Cache getCache(String cacheName) {
        return manager.getCache(cacheName);
    }

    /**
     * 添加缓存数据
     * @param cacheName
     * @param key
     * @param value
     */
    public void put(String cacheName, String key, Object value) {
        try {
            Cache cache = manager.getCache(cacheName);
            Element element = new Element(key, value);
            cache.put(element);
            log.error("添加缓存失败：{}","fefe");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("添加缓存失败：{}",e.getMessage());
        }
    }

    /**
     * 获取缓存数据
     * @param cacheName
     * @param key
     * @return
     */
    public Object get(String cacheName, String key) {
        try {
            Cache cache = manager.getCache(cacheName);
            Element element = cache.get(key);
            return element == null ? null : element.getObjectValue();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取缓存数据失败：{}",e.getMessage());
            return null;
        }
    }

    /**
     * 删除缓存数据
     * @param cacheName
     * @param key
     */
    public void delete(String cacheName, String key) {
        try {
            Cache cache = manager.getCache(cacheName);
            cache.remove(key);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}


