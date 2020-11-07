package database;

import models.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class UserDB {

    public int insert(Users user) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.persist(user);
            trans.commit();
        }catch (Exception ex) {
            trans.rollback();
        }finally {
            em.close();
            return 1;
        
        }
    }

    public int update(Users user) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();  
        EntityTransaction trans = em.getTransaction();
       try {
            trans.begin();
            em.merge(user);
            trans.commit();
        } catch(Exception ex){
            trans.rollback();
        } finally {
            em.close();
            return 1;
        } 
    }

    public List<Users> getAll() throws Exception {
         EntityManager em = DBUtil.getEmFactory().createEntityManager();
          TypedQuery<Users> query = em.createNamedQuery("Users.findAll", Users.class);
         List<Users> results = query.getResultList();
            return results;
    }

    public Users getUser(String username) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try{
            Users user = em.find(Users.class, username);
            return user;
        }finally{
            em.close();
        }
    }

    public int delete(Users user) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();  
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.remove(em.merge(user));
            trans.commit();
        } catch(Exception ex){
            trans.rollback();
        } finally {
            em.close();
            return 1;
        }
    }
}
