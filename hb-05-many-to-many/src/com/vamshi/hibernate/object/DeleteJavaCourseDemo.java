package com.vamshi.hibernate.object;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.vamshi.hibernate.demo.entity.Instructor;
import com.vamshi.hibernate.demo.entity.InstructorDetail;
import com.vamshi.hibernate.demo.entity.Review;
import com.vamshi.hibernate.demo.entity.Student;
import com.vamshi.hibernate.demo.entity.Course;

public class DeleteJavaCourseDemo {

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
			
			//get the Java course from db
			int courseId=12;
			Course tempCourse = session.get(Course.class, courseId);
			//delete the course
			System.out.println("Deleting the course..." + tempCourse);
			session.delete(tempCourse);
			
			
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("DONE!");
		}finally {
			
			session.close();
			factory.close();
		}
	
	}

}
