package com.oresomecraft.maps.battles.maps;

import com.oresomecraft.OresomeBattles.BattlePlayer;
import com.oresomecraft.OresomeBattles.gamemode.Gamemode;
import com.oresomecraft.OresomeBattles.map.annotations.Attributes;
import com.oresomecraft.OresomeBattles.map.annotations.MapConfig;
import com.oresomecraft.OresomeBattles.map.annotations.Region;
import com.oresomecraft.OresomeBattles.map.types.BattleMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@MapConfig(
        name = "lorempetra",
        fullName = "Lorem Petra",
        creators = {"SuperDuckFace", "ninsai"},
        gamemodes = {Gamemode.TDM, Gamemode.LTS}
)
@Region(
        x1 = 0,
        y1 = 0,
        z1 = 0,
        x2 = 0,
        y2 = 0,
        z2 = 0
)
@Attributes(
        allowBuild = false,
        fireSpread = false,
        tdmTime = 8,
        disabledDrops = {Material.BOW, Material.IRON_SWORD}
)
public class LoremPetra extends BattleMap {

    public LoremPetra() {
        super.initiate(this);
    }

    public void readyTDMSpawns() {
        redSpawns.add(new Location(w, 1, 41, 81, 14, 99));
        blueSpawns.add(new Location(w, -29, 82, -7, 3, -780));
    }

    public void readyFFASpawns() {
        FFASpawns.add(new Location(w, -29, 82, -7, 3, -780));
    }

    public void applyInventory(final BattlePlayer p) {
        Player pl = (Player) p;
        Inventory i = pl.getInventory();

        ItemStack HEALTH_POTION = new ItemStack(Material.POTION, 1, (short) 16373);
        ItemStack IRON_HELMET = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack IRON_CHESTPLATE = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack IRON_PANTS = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack IRON_BOOTS = new ItemStack(Material.IRON_BOOTS, 1);
        ItemStack IRON_SWORD = new ItemStack(Material.IRON_SWORD, 1);

        pl.getInventory().setBoots(IRON_BOOTS);
        pl.getInventory().setLeggings(IRON_PANTS);
        pl.getInventory().setChestplate(IRON_CHESTPLATE);
        pl.getInventory().setHelmet(IRON_HELMET);

        // setItem() is a BattlePlayer method. Makes giving items a bit quicker.
        p.setItem(0, Material.IRON_SWORD, 1);
        p.setItem(1, Material.BOW, 1);
        p.setItem(2, Material.COOKED_BEEF, 5);

        // This is the Bukkit way of doing it
        i.setItem(3, HEALTH_POTION);
        p.setItem(8, Material.EXP_BOTTLE, 5);
        p.setItem(9, Material.ARROW, 64);
    }

}
