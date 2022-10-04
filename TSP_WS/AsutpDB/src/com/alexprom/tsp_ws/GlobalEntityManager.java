package com.alexprom.tsp_ws;

import com.alexprom.tsp_ws.connectionsettings.dbConnectionSettingsPanel;
import java.util.HashMap;
import java.util.Map;
import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;
import java.util.prefs.Preferences;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.util.NbPreferences;


public final class GlobalEntityManager implements iGlobalEntityManager{
    private EntityManagerFactory emf=null;
    private EntityManager em=null;
    private String ipAddress, dbName, uName, uPassword;
    
    public GlobalEntityManager(){
        Preferences pref = NbPreferences.forModule(dbConnectionSettingsPanel.class);
        setIpAddress(pref.get("ipAddress", ""));
        setDbName(pref.get("dbName", ""));
        setUserName(pref.get("userName", ""));
        setUserPassword(pref.get("userPassword", ""));
        updatePersistence();
        pref.addPreferenceChangeListener(new PreferenceChangeListener() {
        @Override
        public void preferenceChange(PreferenceChangeEvent evt) {
            if (evt.getKey().equals("ipAddress")) {
                ipAddress = evt.getNewValue();                
            }
            if (evt.getKey().equals("dbName")) {
                dbName = evt.getNewValue();                
            }
            if (evt.getKey().equals("userName")) {
                uName = evt.getNewValue();                
            }
            if (evt.getKey().equals("userPassword")) {                
                uPassword = evt.getNewValue();
            } 
            updatePersistence();
    }
});  
    }

    @Override
    public EntityManagerFactory getEmf() {
        return emf;
    }

    @Override
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public EntityManager getEm() {
        return em;
    }

    @Override
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public String getIpAggress() {
        return ipAddress;
    }

    @Override
    public String getDbName() {
        return dbName;
    }    

    @Override
    public String getUserName() {
        return uName;
    }
    
    @Override
    public String getUserPassword() {
        return uPassword;
    }    

    @Override
    public void setIpAddress(String value) {
        ipAddress = value;
    }

    @Override
    public void setDbName(String value) {
        dbName = value;
    }

    @Override
    public void setUserName(String value) {
        uName = value;
    }

    @Override
    public void setUserPassword(String value) {
        uPassword = value;
    }

    @Override
    public void updatePersistence() {
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            Map<String, String> persistenceMap = new HashMap<>();
            persistenceMap.put("javax.persistence.jdbc.url", "jdbc:sqlserver://"+ipAddress+":1433;databaseName="+dbName);
            persistenceMap.put("javax.persistence.jdbc.user", uName);
            persistenceMap.put("javax.persistence.jdbc.password", String.valueOf(uPassword));
            persistenceMap.put("javax.persistence.jdbc.driver", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
            setEmf(Persistence.createEntityManagerFactory("AsutpDBPU", persistenceMap));
            setEm(emf.createEntityManager());            
        } catch (PersistenceException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            setEmf(null);
            setEm(null);
            NotifyDescriptor d = new NotifyDescriptor.Message("Не установлена связь с базой данных. Выполните настройки соединения и повторите попытку.", NotifyDescriptor.ERROR_MESSAGE);
            Object result = DialogDisplayer.getDefault().notify(d);
        }
    }
    
    
}
