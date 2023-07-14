package com.yupi.yupo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.yupo.common.BaseResponse;
import com.yupi.yupo.common.ErrorCode;
import com.yupi.yupo.common.ResultUtils;
import com.yupi.yupo.exception.BusinessException;
import com.yupi.yupo.model.domain.User;
import com.yupi.yupo.model.request.UserLoginRequest;
import com.yupi.yupo.model.request.UserRegisterRequest;
import com.yupi.yupo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.yupi.yupo.contant.UserConstant.USER_LOGIN_STATE;

/**
 * 用户接口
 *
 *
 */
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"http://120.79.55.209/"})//允许跨域,前端端口
@Slf4j
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private RedisTemplate redisTemplate;

    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String planetCode = userRegisterRequest.getPlanetCode();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword, planetCode)) {
            return null;
        }
        long result = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        return ResultUtils.success(result);
    }

    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.userLogin(userAccount, userPassword, request);
        return ResultUtils.success(user);
    }

    @PostMapping("/logout")
    public BaseResponse<Integer> userLogout(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        int result = userService.userLogout(request);
        return ResultUtils.success(result);
    }
//8EF4B2F296BEDD10973CAF4FD5EECB73
//    8EF4B2F296BEDD10973CAF4FD5EECB73
//    8EF4B2F296BEDD10973CAF4FD5EECB73
//    @GetMapping("/current")
//    public BaseResponse<User> getCurrentUser(HttpServletRequest request) {
//        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
//        User currentUser = (User) userObj;
//        if (currentUser == null) {
//            throw new BusinessException(ErrorCode.NOT_LOGIN);
//        }
//        long userId = currentUser.getId();
//        // TODO 校验用户是否合法
//        User user = userService.getById(userId);
//        //脱敏用户信息
//        User safetyUser = userService.getSafetyUser(user);
//        return ResultUtils.success(safetyUser);
//    }
    @GetMapping("/current")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(USER_LOGIN_STATE);
        if(user == null){
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
        long userId = user.getId();
        User user1 = userService.getById(userId);
        User safetyUser = userService.getSafetyUser(user1);
        return ResultUtils.success(safetyUser);
    }

    @GetMapping("/search")
    public BaseResponse<List<User>> searchUsers(String username, HttpServletRequest request) {
        if (!userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            queryWrapper.like("username", username);
        }
        List<User> userList = userService.list(queryWrapper);
        List<User> list = userList.stream().map(user -> userService.getSafetyUser(user)).collect(Collectors.toList());
        return ResultUtils.success(list);
    }


    @GetMapping("/search/tags")
    public BaseResponse<List<User>> searchUsersByTags(@RequestParam(required = false) List<String> tagnamelist) {
        if(CollectionUtils.isEmpty(tagnamelist)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        List<User> userList = userService.searchUserByTags(tagnamelist);

        return ResultUtils.success(userList);
    }
//    @GetMapping("/recommend")
//    public BaseResponse<Page<User>> recommendUser(long pageSize, long pageNum ,HttpServletRequest request) {
//        //推荐用户
//        User loginUser = userService.getUserlogin(request);
//        String redisKey = String.format("yupo:user:recommend:%s", loginUser.getId());
//        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
//        //如果有缓存，直接读缓存
//        Page<User> userPage = (Page<User>) valueOperations.get(redisKey);
//        if(userPage != null){
//            return ResultUtils.success(userPage);
//        }
//        //如果没有缓存，查询数据库
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        userPage = userService.page(new Page<>(pageNum, pageSize), queryWrapper);
//        //写缓存
//        try {
//            valueOperations.set(redisKey,userPage,10000, TimeUnit.MILLISECONDS);
//        } catch (Exception e) {//打上注解@slf4j 他是lombok的注解，可以自动生成日志对象,可以使用log.error();
//            log.error("redis set key error",e);
//        }
//        return ResultUtils.success(userPage);
//    }
    @GetMapping("/recommend")
    public BaseResponse<Page<User>> recommendUser(long pageSize,long pageNum,HttpServletRequest request){
        User user = userService.getUserlogin(request);
        String redisKey = String.format("wlh:user:recommend:%s",user.getId());
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        //有缓存就读缓存
        Page<User> userPage = (Page<User>) valueOperations.get(redisKey);
        if(userPage != null){
            return ResultUtils.success(userPage);
        }
        //没有缓存就查询数据库
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();//查出所有的用户
        userPage = userService.page(new Page(pageNum,pageSize),queryWrapper);

        //写缓存
        try{
            valueOperations.set(redisKey,userPage,10000,TimeUnit.MILLISECONDS);
        }catch(Exception e){
            log.error("redis set key error",e);
        }
        return ResultUtils.success(userPage);
    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@RequestBody long id, HttpServletRequest request) {
        if (!userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean b = userService.removeById(id);
        return ResultUtils.success(b);
    }
//    @PostMapping("/update")
//    public BaseResponse<Integer> updateUser(@RequestBody User user,HttpServletRequest request) {
//        //验证参数是否为空
//        if (user == null) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//        User loginUser = userService.getUserlogin(request);
//
//        int result = userService.updateUser(user,loginUser);
//        return ResultUtils.success(result);
//    }
    @PostMapping("/update")
    public BaseResponse<Integer> updateUser(@RequestBody User user,HttpServletRequest request){
        if(user == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User loginUser = userService.getUserlogin(request);
        int result = userService.updateUser(user,loginUser);
        return ResultUtils.success(result);

    }

    /**
     * 获取最相似的用户
     * @param num
     * @param request
     * @return
     */
    @GetMapping("/match")
   public BaseResponse<List<User>> matchUsers(long num,HttpServletRequest request){
        if(num <=0 || num > 20){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //这边可能会有缓存的问题，因为数据存在session里面
        User user  = userService.getUserlogin(request);
        return ResultUtils.success(userService.matchUsers(num,user));
   }


}
