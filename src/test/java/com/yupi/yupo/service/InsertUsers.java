package com.yupi.yupo.service;

import com.yupi.yupo.mapper.UserMapper;
import com.yupi.yupo.model.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author WLH
 * @verstion 1.0
 * @description 用户插入单元测试，注意打包时要删掉或忽略，不然打一次包就插入一次
 */
@SpringBootTest
public class InsertUsers {
    @Resource
//    private UserMapper userMapper;
    private UserService userService;
    //CPU密集型任务，分配的核心线程数为CPU核数 -1
    //IO密集型任务，分配的核心数可以大于CPU核心数
    private ExecutorService executorService = new ThreadPoolExecutor(60,1000,10000, TimeUnit.MINUTES,new ArrayBlockingQueue<>(10000));
    @Test
    public void doInsertUsers(){
        StopWatch stopWatch = new StopWatch();
        System.out.println("foossdfsdf");
        stopWatch.start();
        final int INSERT_NUM = 100000;
        List<User> userList =  new ArrayList<>();
        for(int i = 0; i < INSERT_NUM; i++ ){
            User user = new User();

            user.setUsername("LH");
            user.setUserAccount("wlh2");
            user.setAvatarUrl("https://www.baidu.com/img/bd_logo1.png?where=super");
            user.setTags("[]");
            user.setGender(0);
            user.setUserPassword("12324242");
            user.setPhone("23124124");
            user.setEmail("31241213@qq.com");
            user.setUserStatus(0);
            user.setUserRole(0);
            user.setPlanetCode("11111");
            userList.add(user);



        }
        //20秒10000条
        userService.saveBatch(userList,10000);//使用mybatis-plus的批量插入
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());


    }



    /**
     * 并发批量插入用户
     */

    @Test
    public void doConcurrencyInsertUsers(){
        StopWatch stopWatch = new StopWatch();
        System.out.println("foossdfsdf");
        stopWatch.start();
        final int INSERT_NUM = 100000;
        // 分十组
        int j = 0;
        //批量插入数据的大小
        int batchSize = 5000;
        List<CompletableFuture<Void>> futureList = new ArrayList<>();
        // i 要根据数据量和插入批量来计算需要循环的次数。（鱼皮这里直接取了个值，会有问题,我这里随便写的）
        for (int i = 0; i < INSERT_NUM/batchSize; i++) {
            List<User> userList = new ArrayList<>();
            while (true) {
                j++;
                User user = new User();
                user.setUsername("LH");
                user.setUserAccount("wlh2");
                user.setAvatarUrl("https://www.baidu.com/img/bd_logo1.png?where=super");
                user.setTags("[]");
                user.setGender(0);
                user.setUserPassword("12324242");
                user.setPhone("23124124");
                user.setEmail("31241213@qq.com");
                user.setUserStatus(0);
                user.setUserRole(0);
                user.setPlanetCode("11111");
                userList.add(user);
                if (j % batchSize == 0 ){
                    break;
                }


            }
            //异步执行
            CompletableFuture<Void> future = CompletableFuture.runAsync(() ->{
                System.out.println("ThreadName：" + Thread.currentThread().getName());
                userService.saveBatch(userList,batchSize);
            },executorService);
            futureList.add(future);
        }
        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[]{})).join();

        stopWatch.stop();
        System.out.println( stopWatch.getLastTaskTimeMillis());


    }
}
