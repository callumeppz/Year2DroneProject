
package com.example.demo2;


public class EnemyDrone extends FirstDrone {
    double DroneAngle, DroneSpeed;
    public EnemyDrone(double ix, double iy, double ir, double ia, double is) {
        super(ix, iy, ir);
        DroneAngle = ia;
        DroneSpeed = is;
    }
    @Override
    protected void checkdrone(DroneArena b) {

    }
    @Override
    protected void adjustdrone() {

    }
    protected String getStrType() {
        return "Drone";
    }

}