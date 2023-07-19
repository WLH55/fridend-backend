## MatchMate 是什么？

-  MatchMate 是一个基于Vue3+Spring Boot2的移动端网站，旨在帮助人们找到适合各种活动的合作伙伴，例如运动、爱好或商业企业。 
-  该网站使用复杂的匹配算法，考虑用户的偏好、兴趣、技能以及位置和可用性。 
-  用户可以创建个人资料，搜索潜在的合作伙伴，并通过网站与他们进行沟通。 
-  用户可以创建或加入群组，找到属于自己的小圈子。 
-  MatchMate 易于使用、可靠且安全，已经帮助数千人找到了他们的完美匹配。

 
## 在线体验
[http://120.79.55.209/](http://120.79.55.209/)

- 账号：wqx11
- 密码：12345678
## 
## 项目演示
（项目截图）
![image.png](https://cdn.nlark.com/yuque/0/2023/png/28467887/1689339619150-5e32f2d4-41f3-4e00-a19c-104913785d67.png#averageHue=%23f6f7f7&clientId=ud932392d-83fe-4&from=paste&height=652&id=uc72c8a88&originHeight=815&originWidth=685&originalType=binary&ratio=1.25&rotation=0&showTitle=false&size=87133&status=done&style=none&taskId=u0db2a864-42ca-454f-b974-35ffdc2b3f7&title=&width=548)
![image.png](https://cdn.nlark.com/yuque/0/2023/png/28467887/1689339632285-d058a8e4-4371-453b-b964-7005192481b0.png#averageHue=%23d6d9b2&clientId=ud932392d-83fe-4&from=paste&height=624&id=u7b7e0fb0&originHeight=780&originWidth=673&originalType=binary&ratio=1.25&rotation=0&showTitle=false&size=47263&status=done&style=none&taskId=ua8666486-e6b3-465e-bfa5-0d0e96693cc&title=&width=538.4)
![image.png](https://cdn.nlark.com/yuque/0/2023/png/28467887/1689339640892-891d3635-a8d4-4aed-bf1c-b9c6ec400725.png#averageHue=%23dadcb1&clientId=ud932392d-83fe-4&from=paste&height=650&id=u86159021&originHeight=813&originWidth=601&originalType=binary&ratio=1.25&rotation=0&showTitle=false&size=47988&status=done&style=none&taskId=u6c6db4c2-4670-4e10-aa60-eafb15e11b0&title=&width=480.8)
![image.png](https://cdn.nlark.com/yuque/0/2023/png/28467887/1689339648517-7f7842f1-bb9e-46ec-bb27-1f34fcb2321b.png#averageHue=%23eed4a8&clientId=ud932392d-83fe-4&from=paste&height=657&id=ub375ca42&originHeight=821&originWidth=615&originalType=binary&ratio=1.25&rotation=0&showTitle=false&size=26324&status=done&style=none&taskId=u0a775d1e-db4b-46c1-9509-95d258f4753&title=&width=492)
![image.png](https://cdn.nlark.com/yuque/0/2023/png/28467887/1689339660030-61c1031a-5e7c-41d7-89fa-f2d21fc5fd99.png#averageHue=%23f7f9db&clientId=ud932392d-83fe-4&from=paste&height=635&id=u422c3d86&originHeight=794&originWidth=720&originalType=binary&ratio=1.25&rotation=0&showTitle=false&size=20850&status=done&style=none&taskId=u46993496-5d82-4acf-a45d-7a0fa2cfb9e&title=&width=576)
![image.png](https://cdn.nlark.com/yuque/0/2023/png/28467887/1689339672464-17fd3877-7ea7-49d7-a888-6ca86dbd9149.png#averageHue=%23726751&clientId=ud932392d-83fe-4&from=paste&height=682&id=u53b19217&originHeight=852&originWidth=622&originalType=binary&ratio=1.25&rotation=0&showTitle=false&size=34131&status=done&style=none&taskId=ucba23f13-e3e6-49ac-a351-327fb196383&title=&width=497.6) 	
## 项目实现亮点
### 实现分布式session登陆
#### 传统单点登陆
假如我们项目部署在两台服务器上，我们称之为A服务器和B服务器。这时我们用户登录A服务器，随之用户的session(用户登陆信息)也存在了A服务器。但是如果我们用户也去登了B服务器，但是我们B服务器上没有用户的信息，所以用户此时在B服务器这还得重新登录，降低了用户体验。

![](README.assets/image-20230712183458590.png#id=IlzE8&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=none&title=)![image.png](https://cdn.nlark.com/yuque/0/2023/png/28467887/1689162079984-baf89361-22b1-4c19-a851-a180f9ad76c9.png#averageHue=%23fafafa&clientId=u33b1bf8f-a817-4&from=paste&height=757&id=u0e051877&originHeight=946&originWidth=1089&originalType=binary&ratio=1.25&rotation=0&showTitle=false&size=164444&status=done&style=none&taskId=ua3a39cbf-d1a0-45ec-bf70-7cc29caf9d2&title=&width=871.2)
#### 分布式session登录
	所以此时我们就可以考虑能不能把用户的session信息存到一个公有的数据库里，当我们需要这个session信息时，我们都让它从我们这个公有的数据库里面拿。

![image.png](https://cdn.nlark.com/yuque/0/2023/png/28467887/1689162096727-29311029-f5f6-4d1e-b75f-d79c0e313688.png#averageHue=%23fafafa&clientId=u33b1bf8f-a817-4&from=paste&height=698&id=uda24e1cf&originHeight=873&originWidth=1198&originalType=binary&ratio=1.25&rotation=0&showTitle=false&size=170056&status=done&style=none&taskId=u0111bd4c-806e-4475-826c-a39bba7d070&title=&width=958.4)

#### 分布式session登录实现步骤

1.  引入redis依赖，安装redis 
2.  引入 spring-session 和 redis 的整合，使得自动将 session 存储到 redis 中 
3.  修改 spring-session 存储配置 `spring.session.store-type`
默认是 none，表示存储在单台服务器
store-type: redis，表示从 redis 读写 session 

### 引入redis缓存
	当用户第一次登录我们的系统时，我们可能需要执行整个数据库的查询操作。这时候用户的等待时间不可避免的被拉长，但是当用户第二次刷新我们的系统，我们可以引入redis缓存，将用户第一次登录所需要的信息存到我们的redis缓存中，因为redis是一个基于内存存取的数据库，所以存取效率特别高。这时候，用户的等待时间也会随着存取效率的提升而减小，从而提高用户的体验。
### 缓存预热
	提前预知我们的重点用户可能进行的操作，并将操作的结果提前存储在我们的redis缓存中，提高重点用户的体验
#### 实现步骤

1. 开启spring boot 的Scheduler定时任务
2. 在要进行缓存预热的方法上加上[@Scheduled(cron ](/Scheduled(cron ) = "0 32 22 _ _ *")注解，并使用cron表达式指定方法执行的频率
#### 定时任务可能引起的问题
	当我们的项目部署在多台服务器上，也就是每台服务器上都有一个定时任务的代码，在每天的同一时间开始同时"打鸣"。这就会造成资源的浪费或者脏数据的产生。
#### 控制定时任务
	使用redisson分布式锁控制定时任务的执行，保证同一时间只有一台服务器抢到锁并执行定时任务。
#### 分布式锁实现步骤

1.  引入Redisson依赖 
```xml
<dependency>
   <groupId>org.redisson</groupId>
   <artifactId>redisson</artifactId>
   <version>3.19.1</version>
</dependency>
```

2.  编写RedissonConfig文件 
3.  在开启定时任务方法里编写分布式锁代码 
```java
  @Scheduled(cron = "0 12 1 * * *")   //自己设置时间测试
    public void doCacheRecommendUser() {
    //获取一个分布式锁对象
        RLock lock = redissonClient.getLock("shayu:precachejob:docache:lock");

        try {
            // 只有一个线程能获取到锁
            //lock.tryLock(0, -1, TimeUnit.MILLISECONDS)
            //Redisson 的 tryLock 方法默认会启用看门狗机制
            //看门狗机制会在获取锁成功后启动一个后台线程，定期检查当前线程是否持有锁，如果持有锁，则会自动续期锁的持有时间，从而避免锁的过期和释放。
            //第一个参数 0 表示等待获取锁的时间，如果为 0，则表示不等待直接返回获取锁的结果。
            //第二个参数 -1 表示锁的持有时间，如果为 -1，则表示锁的持有时间为无限长。则看门狗就会一直运行下去，直到当前线程释放锁
            //第三个参数 TimeUnit.MILLISECONDS 表示时间单位为毫秒。
            if (lock.tryLock(0, -1, TimeUnit.MILLISECONDS)) {
                
                    //写缓存,默认30s过期
                    try {
                       
                    } catch (Exception e) {
                       
                    }
                }
            }
        } catch (InterruptedException e) {
            log.error("doCacheRecommendUser error", e);
        } finally {
            // 只能释放自己的锁
            if (lock.isHeldByCurrentThread()) {
                System.out.println("unLock: " + Thread.currentThread().getId());
                lock.unlock();
            }
        }

    }
}
```
## 技术栈
### 前端

1. Vue 3 开发框架（提高页面开发的效率）
2. Vant UI（基于 Vue 的移动端组件库）（React 版 Zent）
3. Vite 2（打包工具，快！）
4. Nginx 来单机部署
### 后端

1. Java 编程语言 + SpringBoot 框架
2. SpringMVC + MyBatis + MyBatis Plus（提高开发效率）
3. MySQL 数据库
4. Redis 缓存
5. Swagger + Knife4j 接口文档
## 项目结构

-  sql 
   - create_table.sql:项目建表sql
-  src 
   -  main 
      -  java 
         -  common :通用工具包 
            - BaseResponse : 通用返回类
            - ErrorCode ：错误码枚举类
            - PageRequest：分页请求封装类
            - ResultUtils：返回工具类
         -  config 
            - SwaggerConfig : 接口文档配置
            - RedisTempleConfig：自定义redis序列化
            - RedissonConfig：redisson配置
            - WebMvcConfg：配置跨域
         -  contant：常量包 
            -  UserConstant: 用户常量类 
> 通过使用UserConstant接口，可以将用户相关的常量进行统一管理，从而方便在代码中进行使用

         -  controller 
            - TeamController：队伍处理逻辑接口
            - UserController：用户处理逻辑接口
         -  exception：异常处理 
            -  BusinessException:自定义异常类 
> 通过使用BusinessException类，可以方便地处理业务异常，并将异常信息进行统一管理。在代码中，可以通过抛出BusinessException异常来表示业务异常，并在异常处理中进行相应的处理。

            -  GlobalExceptionHandler：全局异常处理器 
> @RestControllerAdvice注解来标识该类为全局异常处理器,通过使用
> @ExceptionHandler(BusinessException.class) 和@ExceptionHandler(RuntimeException.class)来分别自动调用业务异常处理方法和运行时异常处理方法。

         -  job 
            -  PreCacheJob 
> 使用redisson分布式锁和RedisTemplate来实现缓存预热。提高重点用户的体验感

         -  mapper：Mybatis Plus数据访问接口 
            - TagMapper
            - TeamMapper
            - UserMapper
            - UserTeamMapper
         -  model:模型层 
            -  domain:实体 
               - Team：队伍实体类
               - User:用户实体类
               - UserTeam：用户队伍关系实体类
            -  dto：数据传输 
               - TeamQuery：队伍查询封装类
            -  enums 
               -  TeamStatusEnum 
> 队伍状态枚举类，0-公开 1-私有 2-加密

            -  request：参数请求包 
> 为什么需要请求参数包装类？
>             1. 请求参数名称 / 类型和实体类不一样
>             2. 有一些参数用不到，如果要自动生成接口文档，会增加理解成本
>             3. 有些字段需要隐藏，不能返回给前端

               - DeleteRequest：删除请求体
               - TeamAddRequest：用户创建队伍请求体
               - TeamJoinRequest：用户加入队伍请求体
               - TeamQuitRequest：用户退出队伍请求体
               - TeamUpdateRequest：队伍更新请求体
               - UserLoginRequest：用户登录请求体
               - UserRegisterRequest：用户注册请求体
            -  VO 
               - TeamUserVO: 队伍和用户信息封装类
               - UserVO: 用户包装类（脱敏）
         -  scripts 
            -  XingQiuTableUserInfo 
> 将表格与对象关联起来

            -  InsertUsers 
> 批量插入用户

            -  ImportExcel 
> 导入表格，两种方式读
>  
>             1. 绑定监听器
>             2. 同步读

 

            -  TableListener 
> 读Excel监听器，在读文件的时候绑定监听器

 

         -  service:业务逻辑实现层 
            - impl 
               - TeamServiceImpl： 队伍接口实现类
               - UserServiceImpl：用户接口实现类
               - UserTeamServiceImpl：用户队伍接口实现类
            - TeamService：队伍业务接口
            - UserService：用户业务接口
            - UserTeamService：用户队伍业务接口
         -  utils：工具包 
            -  AlgorithmUtils 
> 编辑距离算法，实现智能匹配相似用户

         -  yupoApplication: Spring Boot 应用程序的启动类 
      -  resource：资源文件 
         -  mapper: 存放mybatis的sql配置文件 
         -  application.yml 
> Spring Boot主配置文件,这个文件通常包含了应用程序的基本配置，例如数据库连接、日志记录、端口号等信息。这个文件中的配置适用于所有的环境，包括开发、测试和生产环境。

         -  application-prod.yml 
> 针对生产环境的配置文件。这个文件中包含了一些特定于生产环境的配置信息，例如数据库连接池大小、缓存配置、安全配置等。这个文件中的配置会覆盖 `application.yml` 中的相同配置，以适应生产环境的需求。

   -  test: Spring Boot的单元测试 

## 项目部署

1.  执行建表sql文件
![](README.assets/image-20230712193349240.png#id=kbCov&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=none&title=) ![image.png](https://cdn.nlark.com/yuque/0/2023/png/28467887/1689162312176-58af3c17-8fb2-48de-8c98-adceffe68f73.png#averageHue=%23283742&clientId=u33b1bf8f-a817-4&from=paste&height=42&id=u11763987&originHeight=52&originWidth=482&originalType=binary&ratio=1.25&rotation=0&showTitle=false&size=2446&status=done&style=none&taskId=u55ff2488-9c05-499f-a47f-7f28bb2e995&title=&width=385.6)
2.  下载maven依赖 
3.  配置application.yml文件 

```yaml
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/你的数据库名字
    username: 你的数据库账号
    password: 你的数据库密码
    
    
    
   redis:
    port: 你的redis端口
    host: 你的redis地址
    database: 1  #默认是0
```

4.  启动yupoApplication

![image.png](https://cdn.nlark.com/yuque/0/2023/png/28467887/1689162340394-eead87ac-a127-4f75-91cb-72852d021c26.png#averageHue=%233f4345&clientId=u33b1bf8f-a817-4&from=paste&height=35&id=u7c65ca26&originHeight=44&originWidth=455&originalType=binary&ratio=1.25&rotation=0&showTitle=false&size=4904&status=done&style=none&taskId=ufbb85acd-1916-4633-91ba-038ef8b68f5&title=&width=364)
![](README.assets/image-20230712193828650.png#id=QIAqI&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=none&title=) 
