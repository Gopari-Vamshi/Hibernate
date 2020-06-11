package com.vamshi.hibernate.object;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.vamshi.hibernate.demo.entity.Instructor;
import com.vamshi.hibernate.demo.entity.InstructorDetail;

public class CreateDemo {

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
			/*
			//create the objects
			Instructor tempInstructor = new Instructor("vamshi","gopari","vamshi@gmail.com");
			
			InstructorDetail tempInstructorDetail = new InstructorDetail("https://www.vamshi.com/youtube","love2code");
			*/
			Instructor tempInstructor = new Instructor("kala","gopari","lingam@gmail.com");
			
			InstructorDetail tempInstructorDetail = new InstructorDetail("https://www.lingam.com/youtbe","football");
			
			//associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			//start transaction
			session.beginTransaction();
			
			
			//save the instructor note: this will ALSO save the details object because of CasecadeType.ALL
			System.out.println("Saving INstructor: "+ tempInstructor);
			session.save(tempInstructor);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("DONE!");
		}finally {
			factory.close();
		}
	
	}

}
