package com.kitri.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyServletContextListener implements ServletContextListener {

    public MyServletContextListener() {
    	System.out.println("MyServletContextListener객체생성");
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	System.out.println("MyServletContextListener contextInitialized()호출되엠~");
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
    	System.out.println("MyServletContextListener contextDestroyed()호출됬습니다아~");
    }

	
}
