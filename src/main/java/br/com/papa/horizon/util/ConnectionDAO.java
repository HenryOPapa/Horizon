package br.com.papa.horizon.util;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class ConnectionDAO {

    /**
     * Responsavel pela conexao com o banco de dados atraves do hibernate
     */
    private SessionFactory sessionFactory;

    /**
     * Obtem a Factory Session do Hibernate
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Seta a Factory Session do Hibernate
     */
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }



}
