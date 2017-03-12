package uk.co.umbaska.modules;

import uk.co.umbaska.Umbaska;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * @author Andrew Tran
 */
public class ModuleLogHandler extends Handler {
    private ModuleInfo moduleInfo;

    public ModuleLogHandler(ModuleInfo moduleInfo) {
        this.moduleInfo = moduleInfo;
    }

    public ModuleInfo getModuleInfo() {
        return moduleInfo;
    }

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
