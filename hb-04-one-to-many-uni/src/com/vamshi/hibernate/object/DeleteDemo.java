package com.vamshi.hibernate.object;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.vamshi.hibernate.demo.entity.Instructor;
import com.vamshi.hibernate.demo.entity.InstructorDetail;

public class DeleteDemo {

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
			
			//get the instructor by primary key
			int theId = 1;
			Instructor tempInstructor =
					session.get(Instructor.class, theId);
			
			System.out.println("found the instructor:" + tempInstructor);
			
			//delete that instructors
			if(tempInstructor != null) {
				System.out.println("deleting :" + tempInstructor);
				
				//note: will also delete associated details object
				//because of CascaseType.ALL
				session.delete(tempInstructor);
			}
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("DONE!");
		}finally {
			factory.close();
		}
	
	}

}
