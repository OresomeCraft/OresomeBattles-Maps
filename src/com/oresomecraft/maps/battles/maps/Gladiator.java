package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import com.oresomecraft.maps.battles.IBattleMap;
import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.*;
import org.bukkit.entity.*;

import com.oresomecraft.OresomeBattles.api.*;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

@MapConfig
public class Gladiator extends BattleMap implements IBattleMap, Listener {

    public Gladiator() {
        super.initiate(this, name, fullName, creators, modes);
        disableDrops(new Material[]{Material.WOOL});
        setAllowBuild(false);
        lockTime("day");
    }

    // Map details
    String name = "gladiator";
    String fullName = "Gladiator";
    String creators = "eddie017 ";
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA, Gamemode.KOTH};

    public void readyTDMSpawns() {
        Location redSpawn = new Location(w, 207, 101, -31, -90, 0);
        Location blueSpawn = new Location(w, 289, 101, -31, 90, 0);

        redSpawns.add(redSpawn);
        redSpawns.add(new Location(w, 207, 101, -31, -90, 0));
        redSpawns.add(new Location(w, 224, 92, -27, -90, 0));
        redSpawns.add(new Location(w, 224, 92, -35, -90, 0));
        redSpawns.add(new Location(w, 197, 93, -31, -90, 0));
        redSpawns.add(new Location(w, 230, 92, -12, -135, 0));
        redSpawns.add(new Location(w, 229, 92, -49, -45, 0));
        redSpawns.add(new Location(w, 231, 110, -30, -90, 0));
        redSpawns.add(new Location(w, 222, 100, -5, -135, 0));


        blueSpawns.add(blueSpawn);
        blueSpawns.add(new Location(w, 289, 101, -31, 90, 0));
        blueSpawns.add(new Location(w, 272, 92, -28, 90, 0));
        blueSpawns.add(new Location(w, 272, 92, -34, 90, 0));
        blueSpawns.add(new Location(w, 299, 93, -32, 90, 0));
        blueSpawns.add(new Location(w, 266, 92, -48, 45, 0));
        blueSpawns.add(new Location(w, 266, 92, -12, 135, 0));
        blueSpawns.add(new Location(w, 265, 110, -30, 90, 0));
        blueSpawns.add(new Location(w, 273, 100, -56, 45, 0));

        setKoTHMonument(new Location(w, 248, 125, -32));

    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, 248, 101, -73, 0, 0));
        FFASpawns.add(new Location(w, 248, 101, 9, 180, 0));
        FFASpawns.add(new Location(w, 208, 101, -32, -90, 0));
        FFASpawns.add(new Location(w, 288, 101, -32, 90, 0));
        FFASpawns.add(new Location(w, 272, 92, -30, 90, 0));
        FFASpawns.add(new Location(w, 272, 92, -34, 90, 0));
        FFASpawns.add(new Location(w, 300, 93, -32, 90, 0));
        FFASpawns.add(new Location(w, 267, 92, -14, 135, 0));
        FFASpawns.add(new Location(w, 265, 92, -50, 45, 0));
        FFASpawns.add(new Location(w, 248, 93, 19, -180, 0));
        FFASpawns.add(new Location(w, 248, 93, -83, 0, 0));
        FFASpawns.add(new Location(w, 251, 92, -56, 0, 0));
        FFASpawns.add(new Location(w, 245, 92, -56, 0, 0));
        FFASpawns.add(new Location(w, 248, 92, -31, -125, 0));
        FFASpawns.add(new Location(w, 245, 92, -8, -180, 0));
        FFASpawns.add(new Location(w, 251, 92, -8, -180, 0));
        FFASpawns.add(new Location(w, 224, 92, -35, -90, 0));
        FFASpawns.add(new Location(w, 224, 92, -29, -90, 0));
        FFASpawns.add(new Location(w, 229, 92, -14, -135, 0));
        FFASpawns.add(new Location(w, 230, 92, -51, -45, 0));
        FFASpawns.add(new Location(w, 196, 93, -32, -90, 0));
        FFASpawns.add(new Location(w, 274, 100, -58, 45, 0));
        FFASpawns.add(new Location(w, 222, 100, -5, -135, 0));
        FFASpawns.add(new Location(w, 252, 104, -36, 45, 0));
        FFASpawns.add(new Location(w, 252, 104, -28, 135, 0));
        FFASpawns.add(new Location(w, 244, 104, -28, -135, 0));
        FFASpawns.add(new Location(w, 244, 104, -36, -45, 0));
        FFASpawns.add(new Location(w, 252, 109, -36, 45, 0));
        FFASpawns.add(new Location(w, 252, 109, -28, 135, 0));
        FFASpawns.add(new Location(w, 244, 109, -28, -135, 0));
        FFASpawns.add(new Location(w, 244, 109, -36, -45, 0));
        FFASpawns.add(new Location(w, 248, 108, -23, 180, 0));
        FFASpawns.add(new Location(w, 248, 108, -41, 0, 0));
        FFASpawns.add(new Location(w, 265, 110, -30, 90, 0));
        FFASpawns.add(new Location(w, 231, 110, -32, -90, 0));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 24);
        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);

        p.getInventory().setBoots(IRON_BOOTS);
        p.getInventory().setLeggings(IRON_PANTS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);
        p.getInventory().setHelmet(IRON_HELMET);

        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        i.setItem(2, STEAK);
        i.setItem(3, HEALTH_POTION);
        i.setItem(4, ARROWS);
    }

    // Region. (Top corner block and bottom corner block.
    // Top left corner.
    public int x1 = -320;
    public int y1 = 82;
    public int z1 = 38;

    //Bottom right corner.
    public int x2 = 176;
    public int y2 = 148;
    public int z2 = -103;

    @EventHandler(priority = EventPriority.NORMAL)
    public void fishing(PlayerFishEvent event) {
        if (!event.getHook().getWorld().getName().equals(name)) return;
        PlayerFishEvent.State state = event.getState();
        Player p = event.getPlayer();
        ItemStack is = p.getItemInHand();
        Material mat = is.getType();
        Location loc = p.getLocation();
        Location bobber = event.getHook().getLocation();

        if (mat == Material.FISHING_ROD) {
            if (event.getPlayer().getLocation().distanceSquared(event.getHook().getLocation()) < 8) {
                Location head = p.getEyeLocation();
                if (bobber.distanceSquared(head) > 2.6) {
                    p.sendMessage(ChatColor.RED + "You did not throw the grapple far enough!");
                }
            } else if (event.getHook().getVelocity().getY() < 0.02 && isLocationNearBlock(bobber)) {
                p.setFallDistance(0);
                p.playSound(loc, Sound.ARROW_HIT, 1, 1);

                Location loc2 = p.getLocation();//Get the location from the entity

                double deltaX = loc2.getX() - bobber.getX();//Get X Delta
                double deltaZ = loc2.getZ() - bobber.getZ();//Get Z delta
                double deltaY = loc2.getY() - bobber.getY();//Get Y delta

                Vector vec = new Vector(deltaX, deltaY / 2, deltaZ);//Create new vector
                vec.normalize();//Normalize it so we don't shoot the player into oblivion
                p.setVelocity(vec.multiply(-3));
            }
        }
    }

    private boolean isLocationNearBlock(Location loc) {
        World world = loc.getWorld();
        if (!HOLLOW_MATERIALS.contains(loc.getBlock().getTypeId())) return true;
        if (!HOLLOW_MATERIALS.contains(loc.getBlock().getRelative(BlockFace.DOWN).getTypeId())) return true;
        return false;
    }
}
