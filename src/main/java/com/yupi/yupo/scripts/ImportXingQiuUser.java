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
//        System.out.println("userInfolist = " + userInfolist);
        System.out.println("总数 = " + userInfolist.size());
//        Map<String, List<XingQiuTableUserInfo>> listmap =
//                userInfolist.stream()
//                        .filter(userinfo -> StringUtils.isNotEmpty(userinfo.getUsername()))
//                        .collect(Collectors.groupingBy(XingQiuTableUserInfo::getUsername));
        Map<String,List<XingQiuTableUserInfo>> listmap = userInfolist.stream()
                .filter(userinfo -> StringUtils.isNotEmpty(userinfo.getUsername()
                        )).collect(Collectors.groupingBy(XingQiuTableUserInfo::getUsername));
        System.out.println(listmap);//{鱼皮=[XingQiuTableUserInfo(id=1, username=鱼皮)], 沙鱼1=[XingQiuTableUserInfo(id=932, username=沙鱼1)], 沙鱼2=[XingQiuTableUserInfo(id=933, username=沙鱼2)], 沙鱼5=[XingQiuTableUserInfo(id=936, username=沙鱼5)], 沙鱼6=[XingQiuTableUserInfo(id=937, username=沙鱼6)], 沙鱼3=[XingQiuTableUserInfo(id=934, username=沙鱼3)], 沙鱼4=[XingQiuTableUserInfo(id=935, username=沙鱼4)], 沙鱼7=[XingQiuTableUserInfo(id=938, username=沙鱼7)], 沙鱼=[XingQiuTableUserInfo(id=931, username=沙鱼)]}


        for (Map.Entry<String, List<XingQiuTableUserInfo>> stringListEntry : listmap.entrySet()) {
            if (stringListEntry.getValue().size() > 1) {
                System.out.println("username = " + stringListEntry.getKey());
                System.out.println("1");
            }
        }
        System.out.println("不重复昵称数 = " + listmap.keySet().size());
    }


}
