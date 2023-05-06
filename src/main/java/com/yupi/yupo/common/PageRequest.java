package com.yupi.yupo.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author WLH
 * @verstion 1.0
 *
 * @Decription 分页请求封装类
 */
@Data
public class PageRequest implements Serializable {

    private static final long serialVersionUID = -8412736527395179573L;
    /**
     *  当前页码
     */

    protected int pageNum = 1;
    /**
     * 每页数量
     */

    protected int pageSize = 10;
}
