package com.uriel.courses.ws.config;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang3.StringUtils;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.santoyo.uriel.tws.schemas.Course;
import com.santoyo.uriel.tws.schemas.GetModuleRequest;
import com.santoyo.uriel.tws.schemas.GetModuleResponse;
import com.santoyo.uriel.tws.schemas.GetStudentRequest;
import com.santoyo.uriel.tws.schemas.GetStudentResponse;
import com.santoyo.uriel.tws.schemas.Module;
import com.santoyo.uriel.tws.schemas.Student;

/**
 * 
 * @author Uriel Santoyo
 *
 */
@Endpoint
public class CoursesServiceEndpoint {

	@PayloadRoot(namespace = CoursesWSConfig.COURSES_WS_NAMESPACE, localPart = "getStudentRequest")
	@ResponsePayload
	public GetStudentResponse getStudentInfo(@RequestPayload GetStudentRequest id) {
		if (StringUtils.isNotBlank(id.getId())) {
			Student student = new Student();
			student.setFirstName("Uriel");
			student.setLastName("Santoyo");
			student.setId("056");
			GetStudentResponse response = new GetStudentResponse();
			response.setStudent(student);
			return response;
		} else {
			throw new IllegalArgumentException("Student id must be informed.");
		}
	}
	
	@PayloadRoot(namespace = CoursesWSConfig.COURSES_WS_NAMESPACE, localPart = "getModuleRequest")
	@ResponsePayload
	public GetModuleResponse getModuleInfo(@RequestPayload GetModuleRequest moduleRequest){
		if(StringUtils.isNotBlank(moduleRequest.getId()) && moduleRequest.getModuleNumber().intValue() > 0){
			Module module = new Module();
			module.setNumber(moduleRequest.getModuleNumber());
			module.setModuleDuration(new BigInteger("4"));
			module.setInitDate(buildGregorianCalendar("20160801"));
			module.getCourses().addAll(buildCoursesList());
			
			GetModuleResponse moduleResponse = new GetModuleResponse();
			moduleResponse.setModule(module);
			return moduleResponse;
		} else {
			throw new IllegalArgumentException("No proper parameters were received.");
		}
		
	}
	
	/**
	 * Build an instance of {@link XMLGregorianCalendar}/
	 * 
	 * @param date String date with format yyyyMMdd
	 * @return {@link XMLGregorianCalendar}
	 */
	private XMLGregorianCalendar buildGregorianCalendar(String date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		XMLGregorianCalendar xmlGc = null;
		try {
			Date parsedDate = sdf.parse(date);
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(parsedDate);
			xmlGc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
		} catch (ParseException | DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		return xmlGc;
	}
	
	private List<Course> buildCoursesList(){
		List<Course> coursesList = new ArrayList<>();
		Course cal = new Course();
		cal.setName("Calculo I");
		cal.setGrade(new BigDecimal("8.3"));
		cal.setOportinity(new BigInteger("1"));
		
		Course fis = new Course();
		fis.setName("Fisica I");
		fis.setGrade(new BigDecimal("8.0"));
		fis.setOportinity(new BigInteger("1"));
		
		coursesList.add(cal);
		coursesList.add(fis);
		
		return coursesList;
	}
}
