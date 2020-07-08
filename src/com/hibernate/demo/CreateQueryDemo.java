package com.hibernate.demo;

import com.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class CreateQueryDemo {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();
            List<Student> students = session
                    .createQuery("from Student s " +
                            "WHERE s.firstName='Daffy' " +
                            "OR s.firstName='Mary'")
                    .list();
            for (Student student : students)
                System.out.println(student);

            students = session
                    .createQuery("from Student s " +
                            "WHERE s.email LIKE '%gmail.com' ")
                    .list();
            for (Student student : students)
                System.out.println(student);
        } finally {
            sessionFactory.close();
        }
    }
}
