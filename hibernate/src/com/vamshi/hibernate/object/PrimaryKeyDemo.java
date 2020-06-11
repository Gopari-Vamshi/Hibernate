package com.vamshi.hibernate.object;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.vamshi.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

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
				
				//create 3 student objects
				System.out.println("Creating 3 student object...");
				Student tempStudent = new Student("kala", "gopari","Kala@gmail.com");
				Student tempStudent1 = new Student("mahesh", "pangera","mahesh@gmail.com");
				Student tempStudent2 = new Student("arjun", "pangera","arjun@gmail.com");
				
				//start transaction
				session.beginTransaction();
				
				//save the student object
				System.out.println("Saving the transaction..");
				session.save(tempStudent);
				session.save(tempStudent1);
				session.save(tempStudent2);
				
				//commit transaction
				session.getTransaction().commit();
				
				System.out.println("DONE!");
			}finally {
				factory.close();
			}
	}

}
