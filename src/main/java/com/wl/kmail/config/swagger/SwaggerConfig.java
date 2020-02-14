package com.wl.kmail.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.*;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger配置类
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
//	@Autowired
//	SystemConfig systemConfig;
 
    @Bean
    public Docket docket(){
//    	ParameterBuilder ticketPar = new ParameterBuilder();
//        List<Parameter> pars = new ArrayList<Parameter>();
//    	ticketPar.name("ticket").description("用户token")
//    	.modelRef(new ModelRef("string")).parameterType("header")
//    	.required(false).build(); //header中的ticket参数非必填，传空也可以
//    	pars.add(ticketPar.build());
//
//    	List<ResponseMessage> responseMessageList = new ArrayList<>();
//    	for (HttpCode httpCode : HttpCode.values()) {
//    		responseMessageList.add(new ResponseMessageBuilder().code(httpCode.getCode()).message(httpCode.getMsg()).build());
//		}
    	
        return new Docket(DocumentationType.SWAGGER_2)
//        		.globalResponseMessage(RequestMethod.GET, responseMessageList)
//                .globalResponseMessage(RequestMethod.POST, responseMessageList)
//                .globalResponseMessage(RequestMethod.PUT, responseMessageList)
//                .globalResponseMessage(RequestMethod.DELETE, responseMessageList)
        		.apiInfo(apiInfo()).select()
//                   当前包路径
                   .apis(RequestHandlerSelectors.basePackage("com.wl.kmail"))
                    .paths(PathSelectors.any()).build();
        
 
    }
    
    
//构建api文档的详细信息函数
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                //页面标题
                    .title("Spring Boot 使用 Swagger2 构建RESTful API")
                //创建人
                    .contact(new Contact("Koty", "https://github.com/Koty1209", "koty1209@qq.com"))
                 //版本号
                    .version("1.0")
                //描述
                    .description("API 描述")
                    .build();
    }
}
