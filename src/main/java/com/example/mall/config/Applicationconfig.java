package com.example.mall.config;

import java.util.Collections;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.example.mall.domain.Product;
import com.example.mall.dto.ProductDTO;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EntityScan("com.example.mall.domain")
@EnableMongoRepositories(basePackages="com.example.mall.repo.mongo")
@Slf4j
@EnableSwagger2
@Configuration
@EnableMongoAuditing
public class Applicationconfig {
	
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.example.mall.controller"))
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("Mall  Information API", "Mall Information API", "v1", "Terms of service",
				new Contact("Sumit Bhardwaj", "www.example.com", "sumit.bhardwaj@company.com"), "License of API",
				"API license URL", Collections.emptyList());
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			if (!log.isDebugEnabled()) {
				return;
			}
			log.debug("Beans Loaded by Spring-------------------");

			String[] beanNames = ctx.getBeanDefinitionNames();
			for (String beanName : beanNames) {
				log.debug(beanName);
			}
			log.debug("-----------------------------------------");
		};
	}
	
	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
	    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
	    modelMapper.createTypeMap(Product.class, ProductDTO.class);
	    return modelMapper;
	}
	
	@Bean
    MongoTransactionManager transactionManager(MongoDbFactory dbFactory) {  
        return new MongoTransactionManager(dbFactory);
    }

}
