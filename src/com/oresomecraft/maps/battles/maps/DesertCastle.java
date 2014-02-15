package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.maps.MapConfig;
import com.oresomecraft.maps.battles.BattleMap;
import com.oresomecraft.maps.battles.IBattleMap;
import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.world.WorldUnloadEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

import com.oresomecraft.OresomeBattles.api.*;

@MapConfig
public class DesertCastle extends BattleMap implements IBattleMap, Listener {

    public DesertCastle() {
        super.initiate(this, name, fullName, creators, modes);
        setTDMTime(15);
        setAllowBuild(false);
    }

    String name = "desertcastle";
    String fullName = "Desert Castle";
    String creators = "Hourani95";
    Gamemode[] modes = {Gamemode.TDM, Gamemode.FFA, Gamemode.INFECTION};

    public void readyTDMSpawns() {
        Location redSpawn = new Location(w, -37, 66, 30);
        Location blueSpawn = new Location(w, 38, 78, 29);

        redSpawns.add(redSpawn);
        blueSpawns.add(blueSpawn);
        redSpawns.add(new Location(w, -37, 78, 30));
        blueSpawns.add(new Location(w, 38, 66, 29));
    }

    public void readyFFASpawns() {
        Location redSpawn = new Location(w, -37, 66, 30);
        Location blueSpawn = new Location(w, 38, 78, 29);

        FFASpawns.add(redSpawn);
        FFASpawns.add(blueSpawn);
        FFASpawns.add(new Location(w, -37, 78, 30));
        FFASpawns.add(new Location(w, 38, 66, 29));
    }

    public void applyInventory(final BattlePlayer p) {
        Inventory i = p.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack STEAK = new ItemStack(Material.COOKED_BEEF, 1);
        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack ARROWS = new ItemStack(Material.ARROW, 64);
        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);
        ItemStack EXP = new ItemStack(Material.EXP_BOTTLE, 5);
        ItemStack FISHING_ROD = new ItemStack(Material.FISHING_ROD, 1);

        ItemMeta fishing_rod = FISHING_ROD.getItemMeta();
        fishing_rod.setDisplayName(ChatColor.BLUE + "Grappling hook");
        FISHING_ROD.setItemMeta(fishing_rod);

        p.getInventory().setBoots(IRON_BOOTS);
        p.getInventory().setLeggings(IRON_PANTS);
        p.getInventory().setChestplate(IRON_CHESTPLATE);
        p.getInventory().setHelmet(IRON_HELMET);
        i.setItem(0, IRON_SWORD);
        i.setItem(1, BOW);
        if (p.getTeamType() == Team.TDM_RED || p.getTeamType() == Team.TDM_BLUE || p.getTeamType() == Team.FFA) {
            i.setItem(2, FISHING_ROD);
        }
        i.setItem(5, EXP);
        i.setItem(3, STEAK);
        i.setItem(4, HEALTH_POTION);
        i.setItem(9, ARROWS);
        p.getInventory().getBoots().addEnchantment(Enchantment.PROTECTION_FALL, 4);


    }

    public int x1 = -1451;
    public int y1 = 63;
    public int z1 = -2145;
    public int x2 = -1383;
    public int y2 = 159;
    public int z2 = -2066;

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

    @EventHandler
    public void arrowBoom(ProjectileHitEvent event) {
        Entity arrow = event.getEntity();
        World world = Bukkit.getWorld(name);
        if (getArena().equals(name)) {
            if (arrow instanceof Arrow) {
                world.playEffect(arrow.getLocation(), Effect.STEP_SOUND, 10);
            }
        }

    }

    public int particles;

    @EventHandler
    public void arrowParticles(org.bukkit.event.world.WorldLoadEvent event) {
        if (event.getWorld().getName().equals(name)) {
            particles = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                public void run() {
                    World world = Bukkit.getWorld(name);
                    if (getArena().equals(name)) {
                        for (org.bukkit.entity.Entity arrow : world.getEntities()) {
                            if (arrow != null) {
                                if (arrow instanceof org.bukkit.entity.Arrow) {
                                    world.playEffect(arrow.getLocation(), org.bukkit.Effect.SMOKE, 10);
                                }
                            }
                        }
                    }
                }
            }, 5L, 5L);
        }
    }

    @EventHandler
    public void cancelParticles(WorldUnloadEvent event) {
        Bukkit.getScheduler().cancelTask(particles);
    }

}
