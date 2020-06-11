package com.vamshi.hibernate.object;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.vamshi.hibernate.demo.entity.Instructor;
import com.vamshi.hibernate.demo.entity.InstructorDetail;
import com.vamshi.hibernate.demo.entity.Course;

public class GetInstructorCoursesDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			
			
			//start transaction
			session.beginTransaction();
			
			//get the instructor from db
			int theId = 1;
			Instructor tempInstructor = session.get(Instructor.class , theId);
			
			System.out.println("Instructor: "+tempInstructor);
			
			//get course for the instructor
			System.out.println("Courses: " + tempInstructor.getCourses());
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("DONE!");
		}finally {
			
			session.close();
			factory.close();
		}
	
	}

}
