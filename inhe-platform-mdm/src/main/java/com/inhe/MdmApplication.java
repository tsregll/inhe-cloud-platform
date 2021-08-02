package com.inhe;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.inhe.mdm.vee.RuleService;


@SpringBootApplication
@EnableFeignClients
@MapperScan(value = "com.inhe.*.dao")
public class MdmApplication implements ApplicationRunner{
	
	@Value("${spring.datasource.timeZone}")
	private String timeZone;
	
	@Autowired
    RuleService ruleService;
	
	public static void main(String[] args) {
		SpringApplication.run(MdmApplication.class, args);
	}
	
	@Override
    public void run(ApplicationArguments args) throws Exception {       
        ruleService.init();
    }
	
	@PostConstruct
    void started() {
      TimeZone.setDefault(TimeZone.getTimeZone(timeZone));
	}
}
