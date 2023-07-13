package com.yupi.yupo.scripts;

import com.yupi.yupo.mapper.UserMapper;
import com.yupi.yupo.model.domain.User;
import com.yupi.yupo.service.UserService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

/**
 * @author WLH
 * @verstion 1.0
 */
@Component//加入容器bean
public class InsertUsers {
    @Resource
//    private UserService userService;
    private UserMapper userMapper;
    /**
     * 批量插入用户
     */
//    @Scheduled(initialDelay = 5000 , fixedRate = Long.MAX_VALUE)
    public void doInsertUsers(){
        StopWatch stopWatch = new StopWatch();
        System.out.println("foossdfsdf");
        stopWatch.start();
        final int INSERT_NUM = 1000;

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
            userMapper.insert(user);


        }
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());


    }

}
