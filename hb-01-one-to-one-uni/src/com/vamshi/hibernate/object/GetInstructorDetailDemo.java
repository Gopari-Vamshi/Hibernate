package com.vamshi.hibernate.object;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.vamshi.hibernate.demo.entity.Instructor;
import com.vamshi.hibernate.demo.entity.InstructorDetail;

public class GetInstructorDetailDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			
			//start transaction
			session.beginTransaction();
			
			//get the instructor detail object
			int theId = 2;
			
			InstructorDetail tempInstructorDetail = 
					session.get(InstructorDetail.class, theId);
			
			//print the instructor detail
			System.out.println("tempInstructorDetails: "+ tempInstructorDetail);
			
			//print the associated constructor
			System.out.println("the Associated instructor: " + 
								tempInstructorDetail.getInstructor());
			
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("DONE!");
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//handle connection leak issue]
			session.close();
			factory.close();
		}
	
	}

}
