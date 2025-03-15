package com.springboot.cruddemo;

import com.springboot.cruddemo.dao.StudentDAO;
import com.springboot.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner -> {
			//createStudent(studentDAO);
			//readStudent(studentDAO);
			//queryForStudent(studentDAO);
			//queryForStudentByLastName(studentDAO);
			//updateTheStudent(studentDAO);
			deleteStudent(studentDAO);
		};
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int studentId = 2;
		studentDAO.delete(studentId);
	}

	private void updateTheStudent(StudentDAO studentDAO) {
		int studentId = 1;
		Student theStudent = studentDAO.findById(studentId);
		System.out.println("Updating student");
		theStudent.setFirstName("Maniteja");
		studentDAO.updateStudent(theStudent);
		System.out.println("Updated student record ::"+theStudent);
	}

	private void queryForStudentByLastName(StudentDAO studentDAO) {
		List<Student> studentList = studentDAO.findByLastName("Duck");
		for(Student temp : studentList)
			System.out.println(temp);
	}

	private void queryForStudent(StudentDAO studentDAO) {
		List<Student> studentList = studentDAO.findAll();
		for(Student temp : studentList)
			System.out.println(temp);

	}

	private void readStudent(StudentDAO studentDAO) {
		Student student = new Student("Nick","Duck","def@gm.com");
		System.out.println("saving student");
		studentDAO.save(student);

		int id = student.getId();
		System.out.println("Id saved is "+ id);

		Student myStudent = studentDAO.findById(id);

		System.out.println("Found the student " +myStudent);
	}

	private void createStudent(StudentDAO studentDAO) {

		Student student = new Student("Mani","Vallala","abc@gm.com");
		System.out.println("saving student");
		studentDAO.save(student);

		System.out.println("Saved student, generated id is "+student.getId());
	}


}
