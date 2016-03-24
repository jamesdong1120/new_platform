package com.pccw.bedc.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.pccw.bedc.server.BedcServer;



public class BedcCoreControllerListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		BedcServer.startup();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		BedcServer.stop();
	}

	
}
