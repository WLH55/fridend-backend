package com.yupi.yupo.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author WLH
 * @verstion 1.0
 * 用户退出队伍请求体
 */
@Data
public class TeamQuitRequest implements Serializable {


    private static final long serialVersionUID = -1422074412871534610L;
    /**
     * id
     */
    private long teamId;

}
