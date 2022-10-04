/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alexprom.tsp_ws;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author yura_
 */
public interface iGlobalEntityManager {
    EntityManagerFactory getEmf();
    EntityManager getEm();
    void setEmf(EntityManagerFactory emf);
    void setEm(EntityManager em);    
    String getIpAggress();
    void setIpAddress(String value);
    String getDbName();
    void setDbName(String value);
    String getUserName();
    void setUserName(String value);
    String getUserPassword();
    void setUserPassword(String value);
    void updatePersistence();
}
