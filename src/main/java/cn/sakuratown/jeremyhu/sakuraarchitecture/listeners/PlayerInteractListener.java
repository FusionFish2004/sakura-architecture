package cn.sakuratown.jeremyhu.sakuraarchitecture.listeners;

import cn.sakuratown.jeremyhu.sakuraarchitecture.selection.Selection;
import static cn.sakuratown.jeremyhu.sakuraarchitecture.utils.MessageUtil.*;
import static cn.sakuratown.jeremyhu.sakuraarchitecture.utils.ConfigUtil.*;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;


import static cn.sakuratown.jeremyhu.sakuraarchitecture.utils.KeyUtil.PLUGIN;

public class PlayerInteractListener implements Listener {

    public PlayerInteractListener() {
        PLUGIN.getLogger().info(this.getClass().getSimpleName() + " registered.");
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        if (!event.hasItem()) return;
        Player player = event.getPlayer();

        ItemStack itemStack = event.getItem();
        assert itemStack != null;
        if (itemStack.getType() != Material.BLAZE_ROD) return;

        if (event.getAction() == Action.LEFT_CLICK_BLOCK){
            //选择第一个点
            Location location = event.getClickedBlock().getLocation();

            PLUGIN.selectionMap.computeIfAbsent(player, k -> new Selection()).setStart(location);

            sendColorMsg(player, getFirstSelectionMessage());


        }

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK){
            //选择第二个点
            Location location = event.getClickedBlock().getLocation();
            PLUGIN.selectionMap.computeIfAbsent(player, k -> new Selection()).setEnd(location);

            sendColorMsg(player, getSecondSelectionMessage());
        }


        event.setCancelled(true);
    }
}
