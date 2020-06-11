package com.vamshi.hibernate.object;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.vamshi.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		//create session
		Session session = factory.getCurrentSession();
		
		try {
		
			int studentId = 1;
			
			//get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//retrieve student based on the id: primary key
			System.out.println("\nGetting student with id: "+ studentId);
			
			Student myStudent = session.get(Student.class, studentId);
			
			System.out.println("Updating Student...");
			myStudent.setFirstName("Scobby");
			
			//commit the transaction
			session.getTransaction().commit();
			
			//NEWCODE
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//update email
			System.out.println("\n\nupdating email for all students");
			
			session.createQuery("update Student set email='vamshi@gmail.com'").executeUpdate();
			
			
			session.getTransaction().commit();
			
			System.out.println("DONE!");
		}finally {
			factory.close();
		}
	
	}

}
