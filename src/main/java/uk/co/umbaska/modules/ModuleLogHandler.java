package uk.co.umbaska.modules;

import uk.co.umbaska.Umbaska;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Class that represents the log handler for an {@link UmbaskaModule}'s Logger
 * @author Andrew Tran
 */
public class ModuleLogHandler extends Handler {
    private ModuleInfo moduleInfo;

    /**
     * Constructs a {@link ModuleLogHandler} for the specified {@code moduleInfo}
     * @param moduleInfo the ModuleInfo to be used to make the Handler
     */
    public ModuleLogHandler(ModuleInfo moduleInfo) {
        this.moduleInfo = moduleInfo;
    }

    /**
     * Gets the {@link ModuleInfo}
     * @return the {@link ModuleInfo}
     */
    public ModuleInfo getModuleInfo() {
        return moduleInfo;
    }

    /**
     * Sets the {@link ModuleInfo}
     * @param moduleInfo the {@link ModuleInfo} to set
     */
    public void setModuleInfo(ModuleInfo moduleInfo) {
        this.moduleInfo = moduleInfo;
    }

    @Override
    public void publish(LogRecord record) {
        Umbaska.getInstance().getLogger().log(record.getLevel(), String.format("[%s] %s", moduleInfo.getName(), record.getMessage()));
    }

    @Override
    public void flush() {

    }

    @Override
    public void close() throws SecurityException {

    }
}
