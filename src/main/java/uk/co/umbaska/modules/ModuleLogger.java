package uk.co.umbaska.modules;

import java.util.logging.Logger;

/**
 * Class that represents a logger for an {@link UmbaskaModule}
 * @author Andrew Tran
 */
public class ModuleLogger {
    Logger logger;

    /**
     * Constructs a {@link ModuleLogger} from a {@link Logger}
     * @param logger the {@link Logger} to create a {@link ModuleLogger} from
     */
    public ModuleLogger(Logger logger) {
        this.logger = logger;
    }

    /**
     * Get the {@link Logger}
     * @return the {@link Logger}
     */
    public Logger getLogger() {
        return logger;
    }
}
