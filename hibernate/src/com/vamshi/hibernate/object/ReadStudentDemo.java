package com.vamshi.hibernate.object;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.vamshi.hibernate.demo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			//use the session object to save java object
			
			//create a student object
			System.out.println("Creating a new student object...");
			Student tempStudent = new Student("muffin", "goofy","goofy@gmail.com");
			
			//start transaction
			session.beginTransaction();
			
			//save the student object
			System.out.println("Saving the transaction..");
			System.out.println(tempStudent);
			session.save(tempStudent);
			
			//commit transaction
			session.getTransaction().commit();
			
			//find out the student's id : primary key
			System.out.println("Saved Student. Generated id:"+ tempStudent.getId());
			
			//get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//retrieve student based on the id: primary key
			System.out.println("\nGetting student with id: "+ tempStudent.getId());
			
			Student myStudent = session.get(Student.class, tempStudent.getId());
			
			System.out.println("get Complete: " + myStudent);
			
			//commit the transaction
			session.getTransaction().commit();
			
			
			System.out.println("DONE!");
		}finally {
			factory.close();
		}
	
	}

}
