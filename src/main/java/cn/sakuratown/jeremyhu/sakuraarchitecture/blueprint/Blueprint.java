package cn.sakuratown.jeremyhu.sakuraarchitecture.blueprint;

import cn.sakuratown.jeremyhu.sakuraarchitecture.selection.Selection;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

import static cn.sakuratown.jeremyhu.sakuraarchitecture.utils.KeyUtil.*;

public class Blueprint {

    private Player creator;
    private String uuid = UUID.randomUUID().toString();
    private Selection selection;

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    private String name;

    public void setCreator(Player creator) {
        this.creator = creator;
    }

    public void setSelection(Selection selection) {
        this.selection = selection;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player getCreator() {
        return creator;
    }

    public String getUuid() {
        return uuid;
    }

    public Selection getSelection() {
        return selection;
    }

    public String getName() {
        return name;
    }

    public static Blueprint fromItemStack(ItemStack itemStack){

        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();

        String name = container.get(NAME, PersistentDataType.STRING);
        Player creator = Bukkit.getPlayer(UUID.fromString(container.get(CREATOR, PersistentDataType.STRING)));
        String uuid = container.get(BLUEPRINT_UUID, PersistentDataType.STRING);

        return BlueprintBuilder.of(Blueprint::new)
                .with(Blueprint::setName, name)
                .with(Blueprint::setCreator, creator)
                .with(Blueprint::setUuid, uuid)
                .build();
    }

    public ItemStack getItem(){
        Material material = Material.getMaterial(PLUGIN.getConfig().getString("blueprint.default-material"));
        if (material == null) material = Material.PAPER;
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();

        String name = PLUGIN.getConfig().getString("blueprint.default-name");
        itemMeta.setDisplayName(name);

        String creator = getCreator().getUniqueId().toString();
        String uuid = getUuid();

        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        container.set(CREATOR, PersistentDataType.STRING, creator);
        container.set(BLUEPRINT_UUID, PersistentDataType.STRING, uuid);

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
