package com.vamshi.hibernate.object;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.vamshi.hibernate.demo.entity.Instructor;
import com.vamshi.hibernate.demo.entity.InstructorDetail;
import com.vamshi.hibernate.demo.entity.Review;
import com.vamshi.hibernate.demo.entity.Student;
import com.vamshi.hibernate.demo.entity.Course;

public class AddCoursesForChirstDemo {

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
			
			//get student chirst from db
			int theId = 1;
			Student tempStudent = session.get(Student.class, theId);
			
			System.out.println("\nLoaded student:"+tempStudent);
			System.out.println("Courses : "+ tempStudent.getCourses());
			
			//create more courses
			Course tempCourse1 = new Course("Master Ruby programming");
			Course tempCourse2 = new Course("Master python programming");
			Course tempCourse3 = new Course("Master Java programming");
			
			
			//add student to course
			tempCourse1.addStudent(tempStudent);
			tempCourse2.addStudent(tempStudent);
			tempCourse3.addStudent(tempStudent);
			
			//save the course
			session.save(tempCourse1);
			session.save(tempCourse2);
			session.save(tempCourse3);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("DONE!");
		}finally {
			
			session.close();
			factory.close();
		}
	
	}

}
