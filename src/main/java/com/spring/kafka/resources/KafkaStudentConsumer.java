package com.spring.kafka.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.kafka.model.Student;

@RestController
@RequestMapping("/kafka")
public class KafkaStudentConsumer {

	
	List<Student> studentList = new ArrayList<Student>();
	
	@KafkaListener(groupId = "sar-1",topics = "kafka_example",containerFactory = "kafkaListenerContainerFactory")
	public void getStudentFromTopic(Student student){
		studentList.add(student);
	}
	
	@GetMapping("/consume/all")
	public List<Student> getAllStudents(){
		return studentList;
	}
}
