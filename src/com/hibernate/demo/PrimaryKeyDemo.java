package com.hibernate.demo;

import com.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try{
            System.out.println("Creating student : ");
            Student john = new Student("John", "Doe", "doe.j@outlook.com");
            Student mary = new Student("Mary", "Public", "public.m@outlook.com");
            Student bonita = new Student("Bonita", "Applebum", "applebum.b@outlook.com");


            //start a transaction
            System.out.println("Starting transaction");
            session.beginTransaction();

            //save the student object
            System.out.println("Saving the student...");
            session.save(john);
            session.save(mary);
            session.save(bonita);

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
