
package com.example.demo2;
import java.util.ArrayList;
import java.util.Random;
public class DroneArena {

    double xSize, ySize;
    ArrayList<MovingObstacle> allEDrones;
    private ArrayList<MeteorStrike> allMeteorDrones;
    ArrayList<Obstacle> allEnDrones;
    public ArrayList<Drone> allDrones;
    Random rand = new Random();

    DroneArena(double xS, double yS) {
        xSize = xS;
        ySize = yS;
        allDrones = new ArrayList<Drone>();
        allDrones.add(new Drone(rand.nextInt(700), rand.nextInt(400), 10, 55, 10));
        allEDrones = new ArrayList<MovingObstacle>();
        allEnDrones = new ArrayList<Obstacle>();
        setAllMeteorDrones(new ArrayList<MeteorStrike>());
    }

    public void drawArena(MyCanvas mc) {
        for (FirstDrone b : allDrones) b.drawdrone(mc);
        for (FirstDrone b : allEDrones) b.drawdrone2(mc);
        for (FirstDrone b : allEnDrones) b.drawdrone2(mc);
        for (FirstDrone b : getAllMeteorDrones()) b.drawdrone2(mc);
    }

    public void AdjustDrone() {
        for (FirstDrone b : allDrones) {
            b.adjustdrone();
        }
        for (FirstDrone b : allEDrones) {
            b.adjustdrone();
        }
        for (FirstDrone b : allEnDrones) {
            b.adjustdrone();
        }
        for (FirstDrone b : getAllMeteorDrones()) {
            b.adjustdrone();
        }
    }

    public void checkDrones() {
        for (FirstDrone Drone : allDrones) Drone.checkdrone(this);
        for (FirstDrone Drone : allEDrones) Drone.checkdrone(this);
        for (FirstDrone Drone : allEnDrones) Drone.checkdrone(this);
        for (FirstDrone Drone : getAllMeteorDrones()) Drone.checkdrone(this);
    }

    public ArrayList<String> describeAll() {
        ArrayList<String> ans = new ArrayList<String>();
        for (FirstDrone b : allDrones) ans.add(b.toString());
        return ans;
    }

    public Object addDrone() {
        allDrones.add(new Drone(rand.nextInt(700), rand.nextInt(400), 10, 55, 5));
        return null;
    }

    public void addEDrone() {
        allEDrones.add(new MovingObstacle(rand.nextInt(700), rand.nextInt(400), 10, 55, 5));
    }

    public void addEnDrone() {
        allEnDrones.add(new Obstacle(rand.nextInt(700), rand.nextInt(400), 10, 55, 5));
    }

    public void addMeteorDrone() {
        getAllMeteorDrones().add(new MeteorStrike(rand.nextInt(20), rand.nextInt(400), 10, 55, 5));
    }

    public double checkDroneAngle(double x, double y, double rad, double ang, FirstDrone droneo) {
        double ans = ang;
        if (allDrones.size() >= 1) {
            for (Drone Drone1 : allDrones) {
                if (Drone1 != droneo && Drone1.hitting(x, y, 100, 55)) {
                    ang = 180 * Math.atan2(y - Drone1.y, x - Drone1.x) / Math.PI;
                }
                if (allEDrones.size() >= 1) {
                    for (MovingObstacle Drone2 : allEDrones) {
                        if (Drone2 != droneo && Drone2.hitting(x, y, 80, 80)) {
                            ang = 180 * Math.atan2(y - Drone2.y, x - Drone2.x) / Math.PI;
                        }
                    }
                }
            }
        }
        if (x > xSize - rad || x < rad) {
            ang = 180 - ang;
        }

        if (y > ySize - rad || y < rad) {
            ang = -ang;
        }
        return ang;

    }

    public double checkObsAngle(double x, double y, double rad, double ang, FirstDrone droneo) {
        double ans = ang;
        int healthbar = 100;
        if (allDrones.size() >= 1) {
            for (Drone Drone1 : allDrones) {
                if (Drone1 != droneo && Drone1.hitting(x, y, 100, 55)) {
                    ang = 180 * Math.atan2(y - Drone1.y, x - Drone1.x) / Math.PI;
                }
                if (allEnDrones.size() >= 1) {
                    for (Obstacle Drone2 : allEnDrones) {
                        if (Drone2 != droneo && Drone2.hitting(x, y, 80, 80)) {
                            ang = 180 * Math.atan2(y - Drone2.y, x - Drone2.x) / Math.PI;
                        }
                        if (Drone1.hitting(x, y, 100, 55) && Drone2.hitting(x, y, 80, 80)) {
                            while (healthbar >= 1) {
                                int healthbar2 = healthbar--;
                                System.out.println(healthbar2);
                                if (healthbar2 <= 1) {
                                    System.out.println("Your Drone was destroyed");
                                    allDrones.clear();
                                }
                            }
                        }
                    }
                }
            }
        }
        return ang;
    }

    public double checkMeteorAngle(double x, double y, double rad, FirstDrone droneo) {
        double ans = rad;
        int healthbar = 100;
        if (getAllMeteorDrones().size() >= 1) {
            for (MeteorStrike Drone4 : getAllMeteorDrones()) {
                if (Drone4 != droneo && Drone4.hitting(x, y, 80, 80)) {
                    rad = 180 * Math.atan2(y - Drone4.y, x - Drone4.x) / Math.PI;
                }
                if (allDrones.size() >= 1) {
                    for (Drone Drone1 : allDrones) {
                        if (Drone1 != droneo && Drone1.hitting(x, y, 100, 55)) {
                            rad = 180 * Math.atan2(y - Drone1.y, x - Drone1.x) / Math.PI;
                        }
                        if (Drone1.hitting(x, y, 100, 55) && Drone4.hitting(x,y,80,80)) {
                            while (healthbar >= 1){
                                int healthbar2 = healthbar--;
                                System.out.println(healthbar2);
                                if (healthbar2 <= 1){
                                    System.out.println("Your Drone was destroyed");
                                }
                            }
                        }
                    }
                }
            }
        }
        return rad;
    }

    public ArrayList<MeteorStrike> getAllMeteorDrones() {
        return allMeteorDrones;
    }

    public void setAllMeteorDrones(ArrayList<MeteorStrike> allMeteorDrones) {
        this.allMeteorDrones = allMeteorDrones;
    }
}


