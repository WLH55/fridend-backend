package com.yupi.yupo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.yupo.model.VO.TeamUserVO;
import com.yupi.yupo.model.domain.Team;
import com.yupi.yupo.model.domain.User;
import com.yupi.yupo.model.dto.TeamQuery;
import com.yupi.yupo.model.request.TeamJoinRequest;
import com.yupi.yupo.model.request.TeamUpdateRequest;

import java.util.List;

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

    /**
     * 搜索队伍
     * @param teamQuery
     * @param isAdmin
     * @return
     */
    List<TeamUserVO> listTeams(TeamQuery teamQuery, boolean isAdmin);

    /**
     * 更新队伍信息
     * @param team
     * @param loginUser
     * @return
     */

    boolean updateTeam(TeamUpdateRequest team, User loginUser);

    /**
     * 用户加入队伍
     * @param teamJoinRequest
     * @param loginUser
     * @return
     */
    boolean joinTeam(TeamJoinRequest teamJoinRequest, User loginUser);

}
