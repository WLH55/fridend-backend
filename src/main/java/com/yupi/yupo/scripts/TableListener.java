package com.yupi.yupo.scripts;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import lombok.extern.slf4j.Slf4j;

// 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
@Slf4j
public class TableListener implements ReadListener<XingQiuTableUserInfo> {






    public void invoke(XingQiuTableUserInfo data, AnalysisContext context) {
        System.out.println(data);

    }

    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println("已解析完成");
    }

}