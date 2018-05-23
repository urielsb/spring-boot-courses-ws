package com.uriel.courses.ws.config;

import java.util.Properties;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.soap.server.endpoint.SoapFaultDefinition;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * 
 * @author Uriel Santoyo
 *
 */
@Component
@EnableWs
@ComponentScan
@EnableAutoConfiguration
public class CoursesWSConfig {
	
	public static final String COURSES_WS_NAMESPACE = "http://uriel.santoyo.com/tws/schemas";

	@Bean(name = "courses")
	public DefaultWsdl11Definition coursesDefinition(XsdSchema coursesSchema){
		DefaultWsdl11Definition serviceDefinition = new DefaultWsdl11Definition();
		serviceDefinition.setTargetNamespace(COURSES_WS_NAMESPACE);
		serviceDefinition.setSchema(coursesSchema);
		serviceDefinition.setPortTypeName("CoursesDetailPort");
		serviceDefinition.setLocationUri("/");
		
		return serviceDefinition;
	}

	@Bean
	public XsdSchema getSchemaWsDefinition(){
		return new SimpleXsdSchema(new ClassPathResource("coursesSchema.xsd"));
	}
	
	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> dispatcherServlet(ApplicationContext ctx){
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(ctx);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<MessageDispatcherServlet>(servlet, "/*");
	}
	
	@Bean
	public SoapFaultMappingExceptionResolver getExceptionResolver(){
		SoapFaultMappingExceptionResolver resolver = new SoapFaultMappingExceptionResolver();
		
		SoapFaultDefinition defaultSoapFault = new SoapFaultDefinition();
		defaultSoapFault.setFaultCode(SoapFaultDefinition.SERVER);
		resolver.setDefaultFault(defaultSoapFault);
		
		Properties errorMappings = new Properties();
		errorMappings.setProperty(IllegalArgumentException.class.getName(), SoapFaultDefinition.CLIENT.toString());
		resolver.setExceptionMappings(errorMappings);
		resolver.setOrder(1);
		
		return resolver;
	}
}
