package cn.sakuratown.jeremyhu.sakuraarchitecture.utils;

import cn.sakuratown.jeremyhu.sakuraarchitecture.selection.Selection;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;

public class ParticleUtil {

    private static final double STEP = 0.5D;

    public static void drawParticle(Selection selection){
        Location start = selection.getCorners()[0].clone();
        Location end = selection.getCorners()[1].clone().add(1,1,1);

        for (double i = 0; i <= end.getX() - start.getX(); i+= STEP){
            drawFlame(start.clone().add(i,0,0));
        }

        for (double i = 0; i <= end.getX() - start.getX(); i+= STEP){
            drawFlame(start.clone().add(i,0,end.getZ() - start.getZ()));
        }

        for (double i = 0; i <= end.getX() - start.getX(); i+= STEP){
            drawFlame(start.clone().add(i,end.getY() - start.getY(),end.getZ() - start.getZ()));
        }

        for (double i = 0; i <= end.getX() - start.getX(); i+= STEP){
            drawFlame(start.clone().add(i,end.getY() - start.getY(),0));
        }



        for (double i = 0; i <= end.getY() - start.getY(); i+= STEP){
            drawFlame(start.clone().add(0,i,0));
        }

        for (double i = 0; i <= end.getY() - start.getY(); i+= STEP){
            drawFlame(start.clone().add(end.getX() - start.getX(),i,0));
        }

        for (double i = 0; i <= end.getY() - start.getY(); i+= STEP){
            drawFlame(start.clone().add(0,i,end.getZ() - start.getZ()));
        }

        for (double i = 0; i <= end.getY() - start.getY(); i+= STEP){
            drawFlame(start.clone().add(end.getX() - start.getX(),i,end.getZ() - start.getZ()));
        }



        for (double i = 0; i <= end.getZ() - start.getZ(); i+= STEP){
            drawFlame(start.clone().add(0,0,i));
        }

        for (double i = 0; i <= end.getZ() - start.getZ(); i+= STEP){
            drawFlame(start.clone().add(0,end.getY() - start.getY(),i));
        }

        for (double i = 0; i <= end.getZ() - start.getZ(); i+= STEP){
            drawFlame(start.clone().add(end.getX() - start.getX(),0,i));
        }

        for (double i = 0; i <= end.getZ() - start.getZ(); i+= STEP){
            drawFlame(start.clone().add(end.getX() - start.getX(),end.getY() - start.getY(),i));
        }
    }

    private static void drawFlame(Location location){
        World world = location.getWorld();
        world.spawnParticle(Particle.FLAME,location,1,0,0,0,0.001);
    }
}
