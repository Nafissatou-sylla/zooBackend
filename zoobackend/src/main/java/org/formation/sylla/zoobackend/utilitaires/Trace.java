package org.formation.sylla.zoobackend.utilitaires;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Trace {
	private static final Logger LOGGER = Logger.getLogger("Trace");

	private Trace() {
		super();
	}

	public static void error(String msg) {
		LOGGER.log(Level.SEVERE, msg);
	}

	public static void info(String msg) {
		LOGGER.log(Level.INFO, msg);
	}
}
