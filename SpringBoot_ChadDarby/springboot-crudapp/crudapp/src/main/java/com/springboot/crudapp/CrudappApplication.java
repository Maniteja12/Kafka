package com.springboot.crudapp;

import com.springboot.crudapp.dao.AppDao;
import com.springboot.crudapp.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CrudappApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudappApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(AppDao appDao){
		return runner->{
			//createCourseAndStudents(appDao);
			//findCourseAndStudents(appDao);
			//findStudentAndCourses(appDao);
			addMoreCoursesForStudent(appDao);
		};
	}

	private void addMoreCoursesForStudent(AppDao appDao) {
		int id =2;
		Student studnet = appDao.findCourseAndStudentByStudentId(id);

		Course tempCourse = new Course("Rubix Cube");
		Course tempCourse1 = new Course("Game Dev");

		studnet.addCourse(tempCourse);
		studnet.addCourse(tempCourse1);

		System.out.println("saving student ::"+studnet);
		System.out.println("assoc courses ::"+studnet.getCourses());

		appDao.update(studnet);
	}

	private void findStudentAndCourses(AppDao appDao) {
		int id=2;
		Student student = appDao.findCourseAndStudentByStudentId(id);
		System.out.println("Student is ::"+student);
		System.out.println("Assoc courses are  ::"+student.getCourses());
	}

	private void findCourseAndStudents(AppDao appDao) {
		int id = 10;
		Course tempCourse = appDao.findCourseAndStudentsByCourseId(id);
		System.out.println("Course us ::"+tempCourse);
		System.out.println("Students are "+tempCourse.getStudents());
	}

	private void createCourseAndStudents(AppDao appDao) {
		Course course = new Course("Pacman");
		Student student1 = new Student("John","Doe","doe@gmail.com");
		Student student2 = new Student("Will","Pablo","pablo@gmail.com");

		course.addStudent(student2);
		course.addStudent(student1);
		System.out.println("Saving the course::"+course);
		System.out.println("Assosiated student :: "+course.getStudents());

		appDao.save(course);

		System.out.println("Done");
	}

	private void fetchCourseAndReviews(AppDao appDao) {
		int id = 10;
		Course tempCourse = appDao.findCourseAndReviewsByCourseId(id);

		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());
		System.out.println("Done");
	}

	private void saveCourseAndReview(AppDao appDao) {
		Course course = new Course("Pacman");
		course.addReview(new Review("Great Course"));
		course.addReview(new Review("Cool course"));
		course.addReview(new Review("Bad Course"));

		System.out.println("Course details::"+course);
		System.out.println("Reviews list "+course.getReviews());
		appDao.save(course);
	}

	private void updateInstructor(AppDao appDao) {
		int id = 1;
		Instructor instructor = appDao.findInstructorById(id);
		instructor.setLastName("TESTER");
		appDao.update(instructor);
		System.out.println("DONE");
	}

	private void findInstructorWithCoursesJoinFetch(AppDao appDao) {
		int id = 3;
		System.out.println("Finding instructor id");
		Instructor instructor = appDao.findInstructotByIdJoinFetch(id);

		System.out.println("Instructor ::"+ instructor);
		System.out.println("associated course ::"+instructor.getCourses());
		System.out.println("Done");
	}

	private void findCoursesForInstructor(AppDao appDao) {
		int id = 3;
		System.out.println("Finding instructor id");
		Instructor instructor = appDao.findInstructorById(id);
		System.out.println("Instructor is:: "+instructor);

		//since courses are lazy loaded find the courses
		List<Course> courseList = appDao.findCoursesById(id);
		instructor.setCourses(courseList);
		System.out.println("Course list is::" + instructor.getCourses());
	}

	private void findInstructorWithCourses(AppDao appDao) {
		int id = 3;
		System.out.println("Finding instructor id");
		Instructor instructor = appDao.findInstructorById(id);
		System.out.println("Instructor is:: "+instructor);
		System.out.println("Courses are ::"+ instructor.getCourses());
	}

	private void createInstructorWithCourses(AppDao appDao) {
		Instructor instructor = new Instructor("Julia","Anna","anna@gmail.com");
		InstructorDetail instructorDetail = new InstructorDetail("http://www.abc.com","Jog");
		instructor.setInstructorDetail(instructorDetail);
		Course course = new Course("CS1");
		Course course1 = new Course("Bowling");

		instructor.add(course);
		instructor.add(course1);

		System.out.println("Saving instructor::"+instructor);
		System.out.println("Saving instructor courses::"+instructor.getCourses());
		appDao.save(instructor);
		System.out.println("Done");
	}

	private void findInstructorDetail(AppDao appDao) {
		int id =1;
		InstructorDetail instructorDetail = appDao.findByInstructorDetailId(id);
		System.out.println("Instructor Detail ::"+instructorDetail);
		System.out.println("Instructor info ::"+instructorDetail.getInstructor());
	}

	private void deleteInstructor(AppDao appDao) {
		int id=2;
		appDao.deleteInstructorById(id);
	}

	private void findInstructor(AppDao appDao) {
		int id =1;
		Instructor instructor = appDao.findInstructorById(id);
		System.out.println("Instructor details are :"+ instructor);
	}

	private void createConstructor(AppDao appDao) {
		/*Instructor instructor = new Instructor("Alex","Darby","darby@gmail.com");
		InstructorDetail instructorDetail = new InstructorDetail("http://www.abc.com","Code");
*/
		Instructor instructor = new Instructor("Madhu","Patel","patel@gmail.com");
		InstructorDetail instructorDetail = new InstructorDetail("http://www.abc.com","Dance");
		instructor.setInstructorDetail(instructorDetail);

		System.out.println("Saving instructor: "+ instructor);
		appDao.save(instructor);
		System.out.println("DONE");
	}

}
