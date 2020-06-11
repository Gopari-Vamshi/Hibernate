package com.vamshi.hibernate.object;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.vamshi.hibernate.demo.entity.Instructor;
import com.vamshi.hibernate.demo.entity.InstructorDetail;
import com.vamshi.hibernate.demo.entity.Course;

public class CreateCoursesDemo {

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
			
			//create some courses
			Course tempCourse1 = new Course("Air Guitar - the Ultimate Guide");
			Course tempCourse2 =new Course("The Java Masterclasss");
			
			//add courses to instructor
			tempInstructor.add(tempCourse1);
			tempInstructor.add(tempCourse2);
			
			//save the courses
			session.save(tempCourse1);
			session.save(tempCourse2);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("DONE!");
		}finally {
			
			session.close();
			factory.close();
		}
	
	}

}
