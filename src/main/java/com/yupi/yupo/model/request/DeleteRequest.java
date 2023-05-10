package com.yupi.yupo.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author WLH
 * @verstion 1.0
 * 删除请求体封装类
 */
@Data
public class DeleteRequest implements Serializable {
    private static final long serialVersionUID = 6778290312660738397L;
    private long id;
}
