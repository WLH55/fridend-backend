package com.yupi.yupo.scripts;

import com.alibaba.excel.EasyExcel;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author WLH
 * @verstion 1.0
 * 导入星球用户
 */
public class ImportXingQiuUser {
    public static void main(String[] args) {
        String fileName = "E:\\down\\testExcel.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 同步读取会自动finish
        List<XingQiuTableUserInfo> userInfolist =
                EasyExcel.read(fileName).head(XingQiuTableUserInfo.class).sheet().doReadSync();
        System.out.println("总数 = " + userInfolist.size());
        Map<String, List<XingQiuTableUserInfo>> listmap =
                userInfolist.stream()
                        .filter(userinfo -> StringUtils.isNotEmpty(userinfo.getUsername()))
                        .collect(Collectors.groupingBy(XingQiuTableUserInfo::getUsername));

        for (Map.Entry<String, List<XingQiuTableUserInfo>> stringListEntry : listmap.entrySet()) {
            if (stringListEntry.getValue().size() > 1) {
                System.out.println("username = " + stringListEntry.getKey());
                System.out.println("1");
            }
        }
        System.out.println("不重复昵称数 = " + listmap.keySet().size());
    }


}
