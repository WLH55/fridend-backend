package com.yupi.yupo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yupi.yupo.model.domain.UserTeam;
import com.yupi.yupo.mapper.UserTeamMapper;
import com.yupi.yupo.service.UserTeamService;
import org.springframework.stereotype.Service;

/**
* @author WLH
* @description 针对表【user_team(用户队伍关系)】的数据库操作Service实现
* @createDate 2023-05-06 15:05:50
*/
@Service
public class UserTeamServiceImpl extends ServiceImpl<UserTeamMapper, UserTeam>
    implements UserTeamService{

}




