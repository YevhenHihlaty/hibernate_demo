package com.hibernate.demo;

import com.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.w3c.dom.ls.LSOutput;

import java.util.List;
import java.util.stream.Collectors;


public class UpdateStudentDemo {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            //start a transaction
            System.out.println("Starting transaction");
            session.beginTransaction();

            Student student = session.get(Student.class, 4);
            student.setEmail("duck.d@gmail.com");

//            session.getTransaction().commit();
//            session = sessionFactory.getCurrentSession();
//            session.beginTransaction();
//
            session            // this Query would`t change student from line 27 to change that we should create a new session
                    .createQuery("update Student set firstName='Leet'")
                    .executeUpdate();
            session
                    .createQuery("from Student")
                    .list().forEach(System.out::println);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
