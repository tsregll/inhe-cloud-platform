package com.inhe.config;

import java.io.IOException;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * 拦截器，时区转换
 * @author JXINHE1548
 */
@Configuration
public class DateConfig {

	@Autowired
	private RequestMappingHandlerAdapter requestMappingHandlerAdapter;
	
	@Value("${spring.mvc.date-format}")
	private String dateFormat;
	
	/**
	 * 客户端到服务器，普通请求
	 */
	@PostConstruct
	public void addConversionConfig() {
	    ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) requestMappingHandlerAdapter.getWebBindingInitializer();
	    if (initializer.getConversionService() != null) {
	    	GenericConversionService genericConversionService = (GenericConversionService) initializer.getConversionService();
	    	genericConversionService.addConverter(new DateConverter(dateFormat));
	    }
	}
	
	/**
     * Json序列化和反序列化转换器，用于转换Post请求体中的json以及将我们的对象序列化为返回响应的json
     * 使用@RequestBody注解的对象中的Date类型将从这里被转换
     */
    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        //服务器到客户端(交给SpringMVC自己处理)
        /*javaTimeModule.addSerializer(Date.class, new JsonSerializer<Date>() {
            @Override
            public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
                SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT);
                String formattedDate = formatter.format(date);
                jsonGenerator.writeString(formattedDate);
            }
        });*/
        //客户端到服务器(body)
        javaTimeModule.addDeserializer(Date.class, new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
                return new DateConverter(dateFormat).convert(jsonParser.getText());
            }
        });
        objectMapper.registerModule(javaTimeModule);
        return objectMapper;
    }
}
