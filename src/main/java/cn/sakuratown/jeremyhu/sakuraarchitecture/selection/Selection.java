package cn.sakuratown.jeremyhu.sakuraarchitecture.selection;

import cn.sakuratown.jeremyhu.sakuraarchitecture.utils.ConfigUtil;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static cn.sakuratown.jeremyhu.sakuraarchitecture.utils.KeyUtil.PLUGIN;
import static cn.sakuratown.jeremyhu.sakuraarchitecture.utils.ParticleUtil.drawParticle;

/**
 * 选区类
 * @author JeremyHu
 */

public class Selection extends LinkedHashMap<Vector, BlockData> {

    private transient World world;
    private transient Location start;
    private transient Location end;
    private transient boolean selecting = false;
    private transient BukkitRunnable runnable;
    private transient final Selection self = this;

    public Selection() {
        runnable = new BukkitRunnable() {
            @Override
            public void run() {
                if(selecting){
                    drawParticle(self);
                }
            }
        };
        runnable.runTaskTimer(PLUGIN,0L,5L);
    }

    public void build(Location origin){
        World world = origin.getWorld();
        LinkedHashMap<Location, BlockData> actualMap = Maps.newLinkedHashMap();

        for (Vector vector : this.keySet()){
            Location location = origin.clone().add(vector);
            BlockData blockData = this.get(vector);
            actualMap.put(location,blockData);
        }

        actualMap.keySet().forEach(location -> {
            Block block = world.getBlockAt(location);
            block.setBlockData(actualMap.get(location));
        });
    }

    public boolean isSelecting() {
        return selecting;
    }

    public void setSelecting(boolean selecting) {
        this.selecting = selecting;
    }

    public void setStart(Location start) {
        this.start = start;
        world = start.getWorld();
        PLUGIN.getLogger().info(start.toString());
    }

    public void setEnd(Location end) {
        this.end = end;
        world = end.getWorld();
        PLUGIN.getLogger().info(end.toString());
    }



    public Selection(Location start, Location end) {
        this.select(start, end);
    }

    public World getWorld() {
        return world;
    }

    public void select(Location start, Location end){

        if (start == null || end == null) throw new NullPointerException("Parameter undefined.");

        if (start.getWorld() != end.getWorld()) throw new IllegalArgumentException("Selection area corners distributed in different worlds.");
        //如果选区的两个角不在同一世界，抛出参数异常


        //if (!isSelectable()) throw new IllegalArgumentException("Selection area is too big.");
        //如果选区大小超过预定值，抛出参数异常

        this.start = start;
        this.end = end;
        this.world = start.getWorld();

    }

    public int getSize(){
        return this.keySet().size();
    }

    public LinkedHashMap<Vector, BlockData> getNonAirBlocks(){
        LinkedHashMap<Vector, BlockData> nonAirBlocks = Maps.newLinkedHashMap();
        this.keySet().forEach(vector -> {
            BlockData blockData = get(vector);
            if (!(blockData.getMaterial().isAir())){
                nonAirBlocks.put(vector,blockData);
            }
        });
        return nonAirBlocks;
    }

    public Location[] getCorners(){
        //获取两个角的坐标
        Location[] corners = new Location[2];
        corners[0] = getStartPoint().toLocation(world);
        corners[1] = getEndPoint().toLocation(world);
        return corners;
    }

    private Vector getStartPoint(){
        int startX = Math.min(start.getBlockX(),end.getBlockX());
        int startY = Math.min(start.getBlockY(),end.getBlockY());
        int startZ = Math.min(start.getBlockZ(),end.getBlockZ());
        //获得最小角坐标
        return new Vector(startX, startY, startZ);
    }

    private Vector getEndPoint(){
        int endX = Math.max(start.getBlockX(),end.getBlockX());
        int endY = Math.max(start.getBlockY(),end.getBlockY());
        int endZ = Math.max(start.getBlockZ(),end.getBlockZ());
        //获得最大角坐标
        return new Vector(endX, endY, endZ);
    }


    public LinkedHashMap<Vector, BlockData> getBlocks(){

        super.clear();

        Vector start = getStartPoint();
        Vector end = getEndPoint();

        for (int x = start.getBlockX(); x <= end.getBlockX(); x++){
            for (int y = start.getBlockY(); y <= end.getBlockY(); y++){
                for (int z = start.getBlockZ(); z <= end.getBlockZ(); z++){
                    Location location = new Location(world,x,y,z);
                    Block block = world.getBlockAt(location);
                    Vector vector = new Vector(x - start.getBlockX(), y - start.getBlockY(), z - start.getBlockZ());
                    //Vector vector = new Vector(x, y, z);
                    this.put(vector,block.getBlockData());
                }
            }
        }

        this.selecting = true;

        return this;
    }

    public Map<Material, Integer> getMaterials(){
        Map<Material, Integer> materialMap = Maps.newHashMap();
        /*
        Set<Material> materials = getNonAirBlocks().values()
                .stream()
                .map(BlockData::getMaterial)
                .collect(Collectors.toSet());
        //转换为set自动删除重复元素
         */

        getNonAirBlocks().values().forEach(blockData -> {
            materialMap.merge(blockData.getMaterial(), 1, Integer::sum);
        });
        return materialMap;

    }

    @Override
    public void clear(){
        super.clear();
        this.selecting = false;
        this.start = null;
        this.end = null;
    }

    private boolean isSelectable(){

        int disX = getEndPoint().getBlockX() - getStartPoint().getBlockX();
        int disY = getEndPoint().getBlockY() - getStartPoint().getBlockY();
        int disZ = getEndPoint().getBlockZ() - getStartPoint().getBlockZ();

        int maxX = ConfigUtil.getMaxX();
        int maxY = ConfigUtil.getMaxY();
        int maxZ = ConfigUtil.getMaxZ();

        return disX < maxX && disY < maxY && disZ < maxZ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Selection selection = (Selection) o;
        return world.equals(selection.world) && getStartPoint().equals(selection.getStartPoint()) && getEndPoint().equals(selection.getEndPoint());
    }

    @Override
    public int hashCode() {
        return Objects.hash(world, getStartPoint(), getEndPoint());
    }

    public void test(){
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        String json = this.toString();
        PLUGIN.getLogger().info(json);
    }


}
