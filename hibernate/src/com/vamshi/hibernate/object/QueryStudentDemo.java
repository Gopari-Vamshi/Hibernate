package com.vamshi.hibernate.object;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.vamshi.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			
			
			//start transaction
			session.beginTransaction();
			
			//query students
			List<Student> theStudents = session.createQuery("from Student ").getResultList();
						
			//display the students
			displayStudents(theStudents);
			
			//query last name: pangera
			theStudents = session.createQuery("from Student s where s.lastName='pangera'").getResultList();
			
			//result for lastname='pangera'
			System.out.println("\n\nStudents with lastname of pangera:");
			displayStudents(theStudents);
			
			//query student lastname: 'pangera' or firstname='kala'
			theStudents = session.createQuery("from Student s where"
					+ " s.lastName='pangera' or firstName='kala'").getResultList();
			
			System.out.println("\n\nlastname:'pangera' or firstname:'kala'");
			displayStudents(theStudents);
			
			
			//query student to get whose email is ending with @vamshi.com
			theStudents = session.createQuery("from Student s where s.email LIKE '%vamshi.com'").getResultList();
			System.out.println("\n\nWhose emal ends with vamshi.com");
			displayStudents(theStudents);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("DONE!");
		}finally {
			factory.close();
		}
	
	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent: theStudents) {
			System.out.println(tempStudent);
		}
	}

}
