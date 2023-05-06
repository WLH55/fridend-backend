package com.yupi.yupo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.yupo.model.domain.Team;
import com.yupi.yupo.model.domain.User;

/**
* @author WLH
* @description 针对表【team(队伍)】的数据库操作Service
* @createDate 2023-05-06 14:45:20
*/
public interface TeamService extends IService<Team> {

    /**
     *   添加队伍
     * @param team
     * @param loginUser
     * @return
     */
    long addTeam(Team team, User loginUser);

}
