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

import java.util.HashMap;

/**
 * @author Andrew Tran
 */
public class ExpressionManager {
    private HashMap<String, UExpression> expressions = new HashMap<>();
    private Event event;

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
        return expressions.containsKey(key) ? getExpression(key).get(event) : fallback;
    }

    public Object[] getMultiple(String key){
        return getExpression(key).getMultiple(event);
    }

    public Boolean getBoolean(String key){
        return (Boolean) getExpression(key).get(event);
    }

    public Boolean getBoolean(String key, Boolean fallback){
        return expressions.containsKey(key) ? (Boolean) getExpression(key).get(event) : fallback;
    }

    public Boolean[] getBooleans(String key){
        return (Boolean[]) getExpression(key).getMultiple(event);
    }

    public Byte getByte(String key){
        return (Byte) getExpression(key).get(event);
    }

    public Byte getByte(String key, Byte fallback){
        return expressions.containsKey(key) ? (Byte) getExpression(key).get(event) : fallback;
    }

    public Byte[] getBytes(String key){
        return (Byte[]) getExpression(key).getMultiple(event);
    }

    public char getCharacter(String key){
        return (Character) getExpression(key).get(event);
    }

    public char getCharacter(String key, Character fallback){
        return expressions.containsKey(key) ? (Character) getExpression(key).get(event) : fallback;
    }

    public Character[] getCharacters(String key){
        return (Character[]) getExpression(key).getMultiple(event);
    }

    public Color getColor(String key){
        return (Color) getExpression(key).get(event);
    }

    public Color getColor(String key, Color fallback){
        return expressions.containsKey(key) ? (Color) getExpression(key).get(event) : fallback;
    }

    public Color[] getColors(String key){
        return (Color[]) getExpression(key).getMultiple(event);
    }

    public double getDouble(String key){
        return (Double) getExpression(key).get(event);
    }

    public double getDouble(String key, Double fallback){
        return expressions.containsKey(key) ? (Double) getExpression(key).get(event) : fallback;
    }

    public Double[] getDoubles(String key){
        return (Double[]) getExpression(key).getMultiple(event);
    }

    public Short getShort(String key){
        return (Short) getExpression(key).get(event);
    }

    public Short getShort(String key, Short fallback){
        return expressions.containsKey(key) ? (Short) getExpression(key).get(event) : fallback;
    }

    public Short[] getShorts(String key){
        return (Short[]) getExpression(key).getMultiple(event);
    }

    public float getFloat(String key){
        return (Float) getExpression(key).get(event);
    }

    public float getFloat(String key, Float fallback){
        return expressions.containsKey(key) ? (Float) getExpression(key).get(event) : fallback;
    }

    public Float[] getFloats(String key){
        return (Float[]) getExpression(key).getMultiple(event);
    }

    public int getInt(String key){
        return (Integer) getExpression(key).get(event);
    }

    public int getInt(String key, Integer fallback){
        return expressions.containsKey(key) ? (Integer) getExpression(key).get(event) : fallback;
    }

    public Integer[] getIntegers(String key){
        return (Integer[]) getExpression(key).getMultiple(event);
    }

    public Number getNumber(String key){
        return (Number) getExpression(key).get(event);
    }

    public Number getNumber(String key, Number fallback){
        return expressions.containsKey(key) ? (Number) getExpression(key).get(event) : fallback;
    }

    public Number[] getNumbers(String key){
        return (Number[]) getExpression(key).getMultiple(event);
    }



    public ItemStack getItemStack(String key){
        return (ItemStack) getExpression(key).get(event);
    }

    public ItemStack getItemStack(String key, ItemStack fallback){
        return expressions.containsKey(key) ? (ItemStack) getExpression(key).get(event) : fallback;
    }

    public ItemStack[] getItemStacks(String key){
        return (ItemStack[]) getExpression(key).getMultiple(event);
    }

    public ItemType getItemType(String key){
        return (ItemType) getExpression(key).get(event);
    }

    public ItemType getItemType(String key, ItemType fallback){
        return expressions.containsKey(key) ? (ItemType) getExpression(key).get(event) : fallback;
    }

    public ItemType[] getItemTypes(String key){
        return (ItemType[]) getExpression(key).getMultiple(event);
    }

    public long getLong(String key){
        return (Long) getExpression(key).get(event);
    }

    public long getLong(String key, Long fallback){
        return expressions.containsKey(key) ? (Long) getExpression(key).get(event) : fallback;
    }

    public Long[] getLongs(String key){
        return (Long[]) getExpression(key).getMultiple(event);
    }

    public String getString(String key){
        return (String) getExpression(key).get(event);
    }

    public String getString(String key, String fallback){
        return expressions.containsKey(key) ? (String) getExpression(key).get(event) : fallback;
    }

    public String[] getStrings(String key){
        return (String[]) getExpression(key).getMultiple(event);
    }

    public Player getPlayer(String key){
        return (Player) getExpression(key).get(event);
    }

    public Player getPlayer(String key, Player fallback){
        return expressions.containsKey(key) ? (Player) getExpression(key).get(event) : fallback;
    }

    public Player[] getPlayers(String key){
        return (Player[]) getExpression(key).getMultiple(event);
    }

    public Block getBlock(String key){
        return (Block) getExpression(key).get(event);
    }

    public Block getBlock(String key, Block fallback){
        return expressions.containsKey(key) ? (Block) getExpression(key).get(event) : fallback;
    }

    public Block[] getBlocks(String key){
        return (Block[]) getExpression(key).getMultiple(event);
    }

    public Inventory getInventory(String key){
        return (Inventory) getExpression(key).get(event);
    }

    public Inventory getInventory(String key, Inventory fallback){
        return expressions.containsKey(key) ? (Inventory) getExpression(key).get(event) : fallback;
    }

    public Inventory[] getInventorys(String key){
        return (Inventory[]) getExpression(key).getMultiple(event);
    }

    public InventoryType getInventoryType(String key){
        return (InventoryType) getExpression(key).get(event);
    }

    public InventoryType getInventoryType(String key, InventoryType fallback){
        return expressions.containsKey(key) ? (InventoryType) getExpression(key).get(event) : fallback;
    }

    public InventoryType[] getInventoryTypes(String key){
        return (InventoryType[]) getExpression(key).getMultiple(event);
    }

    public Entity getEntity(String key){
        return (Entity) getExpression(key).get(event);
    }

    public Entity getEntity(String key, Entity fallback){
        return expressions.containsKey(key) ? (Entity) getExpression(key).get(event) : fallback;
    }

    public Entity[] getEntities(String key){
        return (Entity[]) getExpression(key).getMultiple(event);
    }

    public EntityType getEntityType(String key){
        return (EntityType) getExpression(key).get(event);
    }

    public EntityType getEntityType(String key, EntityType fallback){
        return expressions.containsKey(key) ? (EntityType) getExpression(key).get(event) : fallback;
    }

    public EntityType[] getEntityTypes(String key){
        return (EntityType[]) getExpression(key).getMultiple(event);
    }

    public OfflinePlayer getOfflinePlayer(String key){
        return (OfflinePlayer) getExpression(key).get(event);
    }

    public OfflinePlayer getOfflinePlayer(String key, OfflinePlayer fallback){
        return expressions.containsKey(key) ? (OfflinePlayer) getExpression(key).get(event) : fallback;
    }

    public OfflinePlayer[] getOfflinePlayers(String key){
        return (OfflinePlayer[]) getExpression(key).getMultiple(event);
    }

    public Vector getVector(String key){
        return (Vector) getExpression(key).get(event);
    }

    public Vector getVector(String key, Vector fallback){
        return expressions.containsKey(key) ? (Vector) getExpression(key).get(event) : fallback;
    }

    public Vector[] getVectors(String key){
        return (Vector[]) getExpression(key).getMultiple(event);
    }

    public World getWorld(String key){
        return (World) getExpression(key).get(event);
    }

    public World getWorld(String key, World fallback){
        return expressions.containsKey(key) ? (World) getExpression(key).get(event) : fallback;
    }

    public World[] getWorlds(String key){
        return (World[]) getExpression(key).getMultiple(event);
    }

    public Chunk getChunk(String key){
        return (Chunk) getExpression(key).get(event);
    }

    public Chunk getChunk(String key, Chunk fallback){
        return expressions.containsKey(key) ? (Chunk) getExpression(key).get(event) : fallback;
    }

    public Chunk[] getChunks(String key){
        return (Chunk[]) getExpression(key).getMultiple(event);
    }

    public Location getLocation(String key){
        return (Location) getExpression(key).get(event);
    }

    public Location getLocation(String key, Location fallback){
        return expressions.containsKey(key) ? (Location) getExpression(key).get(event) : fallback;
    }

    public Location[] getLocations(String key){
        return (Location[]) getExpression(key).getMultiple(event);
    }

    public FireworkEffect getFireworkEffect(String key){
        return (FireworkEffect) getExpression(key).get(event);
    }

    public FireworkEffect getFireworkEffect(String key, FireworkEffect fallback){
        return expressions.containsKey(key) ? (FireworkEffect) getExpression(key).get(event) : fallback;
    }

    public FireworkEffect[] getFireworkEffects(String key){
        return (FireworkEffect[]) getExpression(key).getMultiple(event);
    }

    public Note getNote(String key){
        return (Note) getExpression(key).get(event);
    }

    public Note getNote(String key, Note fallback){
        return expressions.containsKey(key) ? (Note) getExpression(key).get(event) : fallback;
    }

    public Note[] getNotes(String key){
        return (Note[]) getExpression(key).getMultiple(event);
    }

    public Achievement getAchievement(String key){
        return (Achievement) getExpression(key).get(event);
    }

    public Achievement getAchievement(String key, Achievement fallback){
        return expressions.containsKey(key) ? (Achievement) getExpression(key).get(event) : fallback;
    }

    public Achievement[] getAchievements(String key){
        return (Achievement[]) getExpression(key).getMultiple(event);
    }

    public Art getArt(String key){
        return (Art) getExpression(key).get(event);
    }

    public Art getArt(String key, Art fallback){
        return expressions.containsKey(key) ? (Art) getExpression(key).get(event) : fallback;
    }

    public Art[] getArts(String key){
        return (Art[]) getExpression(key).getMultiple(event);
    }

    public ChatColor getChatColor(String key){
        return (ChatColor) getExpression(key).get(event);
    }

    public ChatColor getChatColor(String key, ChatColor fallback){
        return expressions.containsKey(key) ? (ChatColor) getExpression(key).get(event) : fallback;
    }

    public ChatColor[] getChatColors(String key){
        return (ChatColor[]) getExpression(key).getMultiple(event);
    }

    public CoalType getCoalType(String key){
        return (CoalType) getExpression(key).get(event);
    }

    public CoalType getCoalType(String key, CoalType fallback){
        return expressions.containsKey(key) ? (CoalType) getExpression(key).get(event) : fallback;
    }

    public CoalType[] getCoalTypes(String key){
        return (CoalType[]) getExpression(key).getMultiple(event);
    }

    public CropState getCropState(String key){
        return (CropState) getExpression(key).get(event);
    }

    public CropState getCropState(String key, CropState fallback){
        return expressions.containsKey(key) ? (CropState) getExpression(key).get(event) : fallback;
    }

    public CropState[] getCropStates(String key){
        return (CropState[]) getExpression(key).getMultiple(event);
    }

    public Difficulty getDifficulty(String key){
        return (Difficulty) getExpression(key).get(event);
    }

    public Difficulty getDifficulty(String key, Difficulty fallback){
        return expressions.containsKey(key) ? (Difficulty) getExpression(key).get(event) : fallback;
    }

    public Difficulty[] getDifficultys(String key){
        return (Difficulty[]) getExpression(key).getMultiple(event);
    }

    public DyeColor getDyeColor(String key){
        return (DyeColor) getExpression(key).get(event);
    }

    public DyeColor getDyeColor(String key, DyeColor fallback){
        return expressions.containsKey(key) ? (DyeColor) getExpression(key).get(event) : fallback;
    }

    public DyeColor[] getDyeColors(String key){
        return (DyeColor[]) getExpression(key).getMultiple(event);
    }

    public Effect getEffect(String key){
        return (Effect) getExpression(key).get(event);
    }

    public Effect getEffect(String key, Effect fallback){
        return expressions.containsKey(key) ? (Effect) getExpression(key).get(event) : fallback;
    }

    public Effect[] getEffects(String key){
        return (Effect[]) getExpression(key).getMultiple(event);
    }

    public EntityEffect getEntityEffect(String key){
        return (EntityEffect) getExpression(key).get(event);
    }

    public EntityEffect getEntityEffect(String key, EntityEffect fallback){
        return expressions.containsKey(key) ? (EntityEffect) getExpression(key).get(event) : fallback;
    }

    public EntityEffect[] getEntityEffects(String key){
        return (EntityEffect[]) getExpression(key).getMultiple(event);
    }

    public GameMode getGameMode(String key){
        return (GameMode) getExpression(key).get(event);
    }

    public GameMode getGameMode(String key, GameMode fallback){
        return expressions.containsKey(key) ? (GameMode) getExpression(key).get(event) : fallback;
    }

    public GameMode[] getGameModes(String key){
        return (GameMode[]) getExpression(key).getMultiple(event);
    }

    public Material getMaterial(String key){
        return (Material) getExpression(key).get(event);
    }

    public Material getMaterial(String key, Material fallback){
        return expressions.containsKey(key) ? (Material) getExpression(key).get(event) : fallback;
    }

    public Material[] getMaterials(String key){
        return (Material[]) getExpression(key).getMultiple(event);
    }

    public Rotation getRotation(String key){
        return (Rotation) getExpression(key).get(event);
    }

    public Rotation getRotation(String key, Rotation fallback){
        return expressions.containsKey(key) ? (Rotation) getExpression(key).get(event) : fallback;
    }

    public Rotation[] getRotations(String key){
        return (Rotation[]) getExpression(key).getMultiple(event);
    }

    public Direction getDirection(String key){
        return (Direction) getExpression(key).get(event);
    }

    public Direction getDirection(String key, Direction fallback){
        return expressions.containsKey(key) ? (Direction) getExpression(key).get(event) : fallback;
    }

    public Direction[] getDirections(String key){
        return (Direction[]) getExpression(key).getMultiple(event);
    }

    public BlockState getBlockState(String key){
        return (BlockState) getExpression(key).get(event);
    }

    public BlockState getBlockState(String key, BlockState fallback){
        return expressions.containsKey(key) ? (BlockState) getExpression(key).get(event) : fallback;
    }

    public BlockState[] getBlockStates(String key){
        return (BlockState[]) getExpression(key).getMultiple(event);
    }

    public BrewingStand getBrewingStand(String key){
        return (BrewingStand) getExpression(key).get(event);
    }

    public BrewingStand getBrewingStand(String key, BrewingStand fallback){
        return expressions.containsKey(key) ? (BrewingStand) getExpression(key).get(event) : fallback;
    }

    public BrewingStand[] getBrewingStands(String key){
        return (BrewingStand[]) getExpression(key).getMultiple(event);
    }

    public Chest getChest(String key){
        return (Chest) getExpression(key).get(event);
    }

    public Chest getChest(String key, Chest fallback){
        return expressions.containsKey(key) ? (Chest) getExpression(key).get(event) : fallback;
    }

    public Chest[] getChests(String key){
        return (Chest[]) getExpression(key).getMultiple(event);
    }

    public Furnace getFurnace(String key){
        return (Furnace) getExpression(key).get(event);
    }

    public Furnace getFurnace(String key, Furnace fallback){
        return expressions.containsKey(key) ? (Furnace) getExpression(key).get(event) : fallback;
    }

    public Furnace[] getFurnaces(String key){
        return (Furnace[]) getExpression(key).getMultiple(event);
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
