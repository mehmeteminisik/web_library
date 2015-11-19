package com.weblibrary.Listener;

import com.weblibrary.dao.BookDAO;
import com.weblibrary.dao.BookDAOHibernateImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener{
    public void contextInitialized(ServletContextEvent servletContextEvent){
        ServletContext sc = servletContextEvent.getServletContext();
        BookDAO bookDao=new BookDAOHibernateImpl();
        sc.setAttribute("bookDao",bookDao);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent){
        System.out.println("contextDestroyed...");
        ServletContext sc = servletContextEvent.getServletContext();
    }
}
