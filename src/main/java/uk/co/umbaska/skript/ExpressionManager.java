package uk.co.umbaska.skript;

import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.util.Direction;
import org.bukkit.*;
import org.bukkit.block.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import uk.co.umbaska.registrations.UExpression;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Andrew Tran
 */
public class ExpressionManager {
    private HashMap<String, UExpression> expressions = new HashMap<>();
    private Event event;

    public HashMap<String, UExpression> getExpressions() {
        return expressions;
    }

    public boolean isSet(String key){
        return expressions.containsKey(key);
    }

    public void set(String key, UExpression UExpression){
        expressions.put(key, UExpression);
    }

    public void bind(String key, UExpression UExpression){
        set(key, UExpression);
    }

    public UExpression getExpression(String key){
        return expressions.get(key);
    }

    public Object get(String key){
        return getExpression(key).get(event);
    }

    public Object get(String key, Object fallback){
        if (expressions.containsKey(key)){
            if (expressions.get(key).get(event) != null){
                return expressions.get(key).get(event);
            }
        }
        return fallback;
    }

    public Object[] getMultiple(String key){
        return getExpression(key).getMultiple(event);
    }

    public String getString(String key){
        return (String) get(key);
    }

    public String getString(String key, String fallback){
        return (String) get(key, fallback);
    }

    public String[] getStrings(String key){
        return (String[]) getMultiple(key);
    }


    public void setEvent(Event event) {
        this.event = event;
    }
}
