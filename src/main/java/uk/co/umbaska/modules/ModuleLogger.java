package uk.co.umbaska.modules;

import java.util.logging.Logger;

/**
 * @author Andrew Tran
 */
public class ModuleLogger {
    Logger logger;

    public ModuleLogger(Logger logger) {
        this.logger = logger;
    }

    public Logger getLogger() {
        return logger;
    }
}
