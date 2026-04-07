package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {
    private static EntityManager entityManager;

    public EntityManager getEntityManager(){
        if (entityManager != null && entityManager.isOpen()) {
            return entityManager;
        }

        // Hibernate сам знайде файл META-INF/persistence.xml
        // і знайде там блок з назвою "study_db"
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("study_db");

        ConnectionManager.entityManager = emf.createEntityManager();

        return ConnectionManager.entityManager;
    }
}