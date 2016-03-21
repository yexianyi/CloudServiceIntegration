/**
 * Copyright (c) 2016, Xianyi Ye
 *
 * This project includes software developed by Xianyi Ye
 * yexianyi@hotmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package net.yxy.athena.servlet.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.yxy.athena.db.EmbeddedDBServer;
import net.yxy.athena.global.Synchronizer;

public class InitApplication implements ServletContextListener {
	
	static private Logger logger = LoggerFactory.getLogger(InitApplication.class); 
	private Synchronizer synchronizer = Synchronizer.createInstance() ;
	
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		synchronizer.start();
		EmbeddedDBServer.startup() ;
		EmbeddedDBServer.initConnectionPool() ;
		EmbeddedDBServer.importSeedData();
		
	}

	private void startupOrientDB() {
//		ODatabaseDocumentTx database = new ODatabaseDocumentTx("plocal:/temp/db").open("admin", "admin");
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		synchronizer.shutdown() ;
		EmbeddedDBServer.shutdown() ;
	}

}