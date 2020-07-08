package com.hibernate.demo;

import com.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;




public class CreateStudentDemo {
    public static void main(String[] args) {

        SessionFactory  sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try{
            System.out.println("Creating student : ");
            Student studentTemp = new Student("Yevhen", "Khikhlatyi", "khikhlatyi.y@outlook.com");
            System.out.println(studentTemp);

            //start a transaction
            System.out.println("Starting transaction");
            session.beginTransaction();

            //save the student object
            System.out.println("Saving the student...");
            session.save(studentTemp);

            //commit transaction
            System.out.println("Commit transaction");
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {
            sessionFactory.close();
        }
    }
}
