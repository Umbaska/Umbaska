package uk.co.umbaska;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import org.bukkit.plugin.java.JavaPlugin;
import uk.co.umbaska.skript.TestExpression;

/**
 * @author Andrew Tran
 */
public class Umbaska extends JavaPlugin{

    private static Umbaska instance;

    public static Umbaska getInstance() {
        return instance;
    }

    @Override
    public void onEnable(){
        instance = this;
        Skript.registerAddon(this);
        Skript.registerExpression(TestExpression.class, Boolean.class, ExpressionType.SIMPLE, "test %string%");
        getLogger().info("Hello");
    }
}
