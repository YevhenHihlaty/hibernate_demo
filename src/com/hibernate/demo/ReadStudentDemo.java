package com.hibernate.demo;

import com.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class ReadStudentDemo {
    public static void main(String[] args) {

        SessionFactory  sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try{
            System.out.println("Creating student : ");
            Student studentTemp = new Student("Daffy", "Duck", "duck.d@outlook.com");

            //start a transaction
            session.beginTransaction();

            //save the student object
            System.out.println("Saving the student...");
            session.save(studentTemp);

            //commit transaction
            System.out.println("Commit transaction");

            session.getTransaction().commit();

            System.out.println("Saved!");

            // find out the student`s id: primary key
            System.out.println("Saved student. Generated id: " + studentTemp.getId());

            // start new session
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            //retrieve student based on the id: primary key
            System.out.println("\nGetting student with id: " + studentTemp.getId());

            Student newStudent = session.get(Student.class, studentTemp.getId());

            System.out.println("Complete: " + newStudent);

            session.getTransaction().commit();
            System.out.println("Done!");
        }
        finally {
            sessionFactory.close();
        }
    }
}
