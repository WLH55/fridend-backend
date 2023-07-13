package com.yupi.yupo.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author WLH
 * @verstion 1.0
 * 用户加入队伍请求体
 */
@Data
public class TeamJoinRequest implements Serializable {


    private static final long serialVersionUID = -6042048417509359680L;

    /**
     * id
     */
    private long teamId;


    /**
     * 密码
     */
    private String password;
}
