package com.yupi.yupo.scripts;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author WLH
 * @verstion 1.0
 */
@Data
public class XingQiuTableUserInfo {
    /**
     * 成员编号
     */
    @ExcelProperty("成员编号")
    private String id;

    @ExcelProperty("成员昵称")
    private String username;

}
