package uk.co.umbaska.skript;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.TriggerItem;
import ch.njol.util.Kleenean;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import uk.co.umbaska.Umbaska;
import uk.co.umbaska.registrations.BindedSyntaxException;
import uk.co.umbaska.registrations.UExpression;
import uk.co.umbaska.registrations.annotations.BSyntax;
import uk.co.umbaska.registrations.annotations.BSyntaxes;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Andrew Tran
 */
public abstract class UmbaskaAsyncEffect extends UmbaskaEffect{
    @Override
    protected TriggerItem walk(Event e) {
        execute(e);
        return null;
    }

    public void runAsync(Runnable runnable){
        Bukkit.getScheduler().runTaskAsynchronously(Umbaska.getInstance(), new Runnable() {
            @Override
            public void run() {
                runnable.run();
                done();
            }
        });
    }

    public void done(){
        TriggerItem.walk(getNext(), getCurrentEvent());
    }
}
