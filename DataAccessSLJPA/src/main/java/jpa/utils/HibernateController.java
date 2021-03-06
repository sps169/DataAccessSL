package jpa.utils;

import lombok.Data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *  Class Singleton used to initialize, generate the transaction, the Entity Manager with his Factory
 * @author joseluisgs
 */

@Data
public class HibernateController {
        private static HibernateController controller;
        private EntityManagerFactory entityManagerFactory;
        private EntityManager manager;
        private EntityTransaction transaction;

        private HibernateController() {
        }

        public static HibernateController getInstance() {
            if (controller == null)
                controller = new HibernateController();
            return controller;
        }

        public void open() {
            entityManagerFactory = Persistence.createEntityManagerFactory("default");
            manager = entityManagerFactory.createEntityManager();
            transaction = manager.getTransaction();
        }

        public void close() {
            manager.close();
            entityManagerFactory.close();
        }
}
