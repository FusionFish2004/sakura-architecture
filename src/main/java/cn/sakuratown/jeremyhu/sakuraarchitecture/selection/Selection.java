package cn.sakuratown.jeremyhu.sakuraarchitecture.selection;

import com.google.common.collect.Maps;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.util.Vector;

import java.util.Map;

public class Selection {

    private final Map<Vector, BlockData> blockDataMap = Maps.newHashMap();
    private World world;
    private Location start;
    private Location end;

    public Selection(Location start, Location end) {
        this.select(start, end);
    }

    public void select(Location start, Location end){

        if (start.getWorld() != end.getWorld()) throw new IllegalArgumentException("Selection area corners distributed in different worlds.");
        //如果选区的两个角不在同一世界，抛出参数异常

        this.start = start;
        this.end = end;
        this.world = start.getWorld();

        this.getBlocks();
    }

    public Location[] getCorners(){
        Location[] corners = new Location[2];
        corners[0] = getStartPoint().toLocation(world);
        corners[1] = getEndPoint().toLocation(world);
        return corners;
    }

    private Vector getStartPoint(){
        int startX = Math.min(start.getBlockX(),end.getBlockX());
        int startY = Math.min(start.getBlockY(),end.getBlockY());
        int startZ = Math.min(start.getBlockZ(),end.getBlockZ());
        return new Vector(startX, startY, startZ);
    }

    private Vector getEndPoint(){
        int endX = Math.max(start.getBlockX(),end.getBlockX());
        int endY = Math.max(start.getBlockY(),end.getBlockY());
        int endZ = Math.max(start.getBlockZ(),end.getBlockZ());
        return new Vector(endX, endY, endZ);
    }

    public Map<Vector, BlockData> getBlockDataMap() {
        return blockDataMap;
    }

    private void getBlocks(){

        this.blockDataMap.clear();

        Vector start = getStartPoint();
        Vector end = getEndPoint();

        for (int x = start.getBlockX(); x < end.getBlockX(); x++){
            for (int y = start.getBlockY(); y < end.getBlockY(); y++){
                for (int z = start.getBlockZ(); z < end.getBlockZ(); z++){
                    Location location = new Location(world,x,y,z);
                    Block block = world.getBlockAt(location);
                    Vector vector = new Vector(x - start.getBlockX(), y - start.getBlockY(), z - start.getBlockZ());
                    this.blockDataMap.put(vector,block.getBlockData());
                }
            }
        }

    }


}
