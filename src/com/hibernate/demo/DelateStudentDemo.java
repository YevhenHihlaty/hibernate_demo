package com.hibernate.demo;

import com.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DelateStudentDemo {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();
//            System.out.println("Creating student : ");
//            Student studentTemp = new Student("Alan", "Harper", "harper.a@outlook.com");
//
//            session.save(studentTemp);
//            Student studentTemp = session.get(Student.class, 6);

//            System.out.println(studentTemp);

//            session.delete(studentTemp);

            session
                    .createQuery("delete from Student where firstName LIKE 'Alan'")
                    .executeUpdate();

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }
    }
}
