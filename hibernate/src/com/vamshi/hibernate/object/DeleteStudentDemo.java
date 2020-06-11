package com.vamshi.hibernate.object;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.vamshi.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		//create session
		Session session = factory.getCurrentSession();
		
		try {
		
			int studentId = 5;
			
			//get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//retrieve student based on the id: primary key
			System.out.println("\nGetting student with id: "+ studentId);
			
			Student myStudent = session.get(Student.class, studentId);
			
			//delete the student id=4
			System.out.println("Delete student id=2");
			session.createQuery("delete from Student where id=2").executeUpdate();
			
			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("DONE!");
		}finally {
			factory.close();
		}
	
	}

}
