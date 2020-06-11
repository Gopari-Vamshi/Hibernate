package com.vamshi.hibernate.object;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.vamshi.hibernate.demo.entity.Instructor;
import com.vamshi.hibernate.demo.entity.InstructorDetail;
import com.vamshi.hibernate.demo.entity.Review;
import com.vamshi.hibernate.demo.entity.Student;
import com.vamshi.hibernate.demo.entity.Course;

public class CreateCourseAndStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.addAnnotatedClass(Review.class)
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			
			//start transaction
			session.beginTransaction();
			
			//create a course
			Course tempCourse = new Course("Football-Get some cool and easy tricks");
						
			//save the course
			System.out.println("\nSaving the course...");
			session.save(tempCourse);
			System.out.println("Saved the Course: " + tempCourse);
			
			//create students
			Student tempStudent1 = new Student("John","Wick","John@gmail.com");
			Student tempStudent2 = new Student("chirst","Tyler","tyler@gmail.com");
			
			//add student to the course
			tempCourse.addStudent(tempStudent1);
			tempCourse.addStudent(tempStudent2);
			
			//save the students
			System.out.println("\nsaving the student....");
			session.save(tempStudent1);
			session.save(tempStudent2);
			System.out.println("Saved students :" + tempCourse.getStudents());
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("DONE!");
		}finally {
			
			session.close();
			factory.close();
		}
	
	}

}
