package com.yupi.yupo.config;

//import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc //该注解是Springfox-swagger框架提供的使用Swagger注解，该注解必须加
@Profile({"dev", "test"})   //版本控制访问  只允许在dev,test环境下访问
public class SwaggerConfig {
    //注入bean
     @Bean(value = "defaultApi2")
//@Bean

    public Docket defaultApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                //使用链式调用
                .apiInfo(apiInfo())
                .select()
                //标注你控制器的位置
                .apis(RequestHandlerSelectors.basePackage("com.yupi.yupo.controller"))
                //可以选着部分接口，也可以全部
                .paths(PathSelectors.any())
                .build();

    }

    /**
     * api信息
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("伙伴匹配")
                .description("伙伴匹配接口文档")
                .termsOfServiceUrl("http://github.com/wlh555552")
                .contact(new Contact("wlh", "http://github.com/wlh555552", "2109236440@qq.com"))
                .version("1.0")
                .build();
    }

}
