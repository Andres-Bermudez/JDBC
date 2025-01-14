package com.platzi.util;

import jakarta.persistence.Persistence;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class UtilEntityManagerFactory {

    private static final EntityManagerFactory emf = buildEntityManagerFactory();

    private static EntityManagerFactory buildEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("myPersistenceUnit");
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
