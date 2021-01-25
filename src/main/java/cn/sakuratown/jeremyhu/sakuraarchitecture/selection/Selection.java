package cn.sakuratown.jeremyhu.sakuraarchitecture.selection;

import cn.sakuratown.jeremyhu.sakuraarchitecture.utils.ConfigUtil;
import com.google.common.collect.Maps;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.util.Vector;

import java.util.LinkedHashMap;
import java.util.Objects;

/**
 * 选区类
 * @author JeremyHu
 */

public class Selection extends LinkedHashMap<Vector, BlockData>{

    private World world;
    private Location start;
    private Location end;

    public Selection() {

    }

    public void setStart(Location start) {
        this.start = start;
        world = start.getWorld();
    }

    public void setEnd(Location end) {
        this.end = end;
        world = end.getWorld();
    }



    public Selection(Location start, Location end) {
        this.select(start, end);
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

        this.clear();

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

        return this;
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
        this.keySet().forEach(vector -> {
            Location location = vector.toLocation(world);
            Block block = world.getBlockAt(location);
            block.setType(Material.DIAMOND_BLOCK);
        });
    }
}
