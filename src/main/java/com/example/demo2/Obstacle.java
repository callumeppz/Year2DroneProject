
package com.example.demo2;


public class Obstacle extends FirstDrone {
    double DroneAngle, DroneSpeed;
    public Obstacle(double ix, double iy, double ir, double ia, double is) {
        super(ix, iy, ir);
        DroneAngle = ia;
        DroneSpeed = is;
    }

    @Override
    protected void adjustdrone() {
        double radAngle = DroneAngle*Math.PI/180;
    }
    protected String getStrType() {
        return "Drone";
    }

    @Override
    protected void checkdrone(DroneArena b) {
        DroneAngle = b.checkObsAngle(x, y, rad, DroneAngle, this);
    }
    public boolean hitting(double ox, double oy, double or, int i) {
        return (ox-x)*(ox-x) + (oy-y)*(oy-y) < (63/2+63/2) * (32/2+32/2);
    }


}
