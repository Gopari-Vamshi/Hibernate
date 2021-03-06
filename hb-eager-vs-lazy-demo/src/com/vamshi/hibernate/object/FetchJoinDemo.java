package com.vamshi.hibernate.object;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.vamshi.hibernate.demo.entity.Instructor;
import com.vamshi.hibernate.demo.entity.InstructorDetail;
import com.vamshi.hibernate.demo.entity.Course;

public class FetchJoinDemo {

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
			
			//option 2: hibernate query with HQL
						
			
			//get the instructor from db
			int theId = 1;
			
			Query<Instructor> query =
					session.createQuery("select i from Instructor i "
									+"JOIN FETCH i.courses "
									+"where i.id=:theInstructorId", 
							Instructor.class);
			
			//set parameter on query
			query.setParameter("theInstructorId", theId);
			
			//execute query and get instructor
			Instructor tempInstructor = query.getSingleResult();
			
			System.out.println("Vamshi:Instructor: "+tempInstructor);
			
			//commit transaction
			session.getTransaction().commit();
			
			//close the session
			session.close();
			
			System.out.println("\nVamshi: the sesion is now closed\n");
			
			// option 1: call the getter method while session is open.
			
			
			//get course for the instructor
			System.out.println("Vamshi: Courses: " + tempInstructor.getCourses());
			
			System.out.println("Vamshi: DONE!");
		}finally {
			
			session.close();
			factory.close();
		}
	
	}

}
