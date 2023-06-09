package com.yupi.yupo.job;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.yupo.model.domain.User;
import com.yupi.yupo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author WLH
 * @verstion 1.0
 */
@Component
@Slf4j
public class PreCacheJob {
    @Resource
    private UserService userService;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private RedissonClient redissonClient;


    //重点用户
    private List<Long> mainUserList = Arrays.asList(1L,2L,3L,4L,5L,6L,7L,8L,9L,10L);

    //每天执行，预热推荐用户
//    @Scheduled(cron = "0 32 22 * * *")
//    public void doCacheRecommentUser() {
//        RLock lock = redissonClient.getLock("WLH:precachejob:docache:lock");
//        try {
//            if(lock.tryLock(0,-1,TimeUnit.MILLISECONDS)){
//                System.out.println("getLock: " + Thread.currentThread().getId());
//                for (Long id : mainUserList) {
//                    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//                    Page<User> userPage = userService.page(new Page<>(1, 10), queryWrapper);
//                    String redisKey = String.format("yupo:user:recommend:%s", id);
//                    ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
//                    //写缓存
//                    try {
//                        valueOperations.set(redisKey, userPage, 30000, TimeUnit.MILLISECONDS);
//                    } catch (Exception e) {//打上注解@slf4j 他是lombok的注解，可以自动生成日志对象,可以使用log.error();
//                        log.error("redis set key error", e);
//                    }
//                }
//            }
//        } catch (InterruptedException e) {
//            log.error("doCacheRecommendUser error", e);
//        } finally {
//            // 只能释放自己的锁
//            if (lock.isHeldByCurrentThread()) {
//                System.out.println("unLock: " + Thread.currentThread().getId());
//                lock.unlock();
//            }
//        }
//
//    }
    @Scheduled(cron = "0 32 22 * * *")
    public void doCacheRecommentUser(){
        RLock lock = redissonClient.getLock("WLH:precachejob:docache:lock");
        try{
            if(lock.tryLock(0,-1,TimeUnit.MILLISECONDS)){
                System.out.println("getLock: "+ Thread.currentThread().getId());
                for(Long userid:mainUserList){
                    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                    Page<User> userPage = userService.page(new Page<>(1,10),queryWrapper);
                    String redisKey = String.format("yupo:user:recommend:%s",userid);
                    ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
                    //写缓存
                    try{
                        valueOperations.set(redisKey,userPage,30000,TimeUnit.MILLISECONDS);

                    }catch( Exception e){
                        log.error("redis set key error",e);
                    }
                }
            }

        }catch (InterruptedException e){
            log.error("doCacheRecommendUser error",e);
    }finally {
            //只能释放自己的锁
            if(lock.isHeldByCurrentThread()){
                System.out.println("unLock: "+Thread.currentThread().getId());
                lock.unlock();
            }
        }
        }


}
