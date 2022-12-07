
package com.example.demo2;


public class MeteorStrike extends FirstDrone {
    double DroneAngle, DroneSpeed;
    public MeteorStrike(double ix, double iy, double ir, double ia, double is) {
        super(ix, iy, ir);
        DroneAngle = ia;
        DroneSpeed = 7;
    }
    protected void checkdrone(DroneArena b) {
        DroneAngle = b.checkMeteorAngle(x, y, rad, this);
    }
    @Override
    protected void adjustdrone() {
        double radAngle = DroneAngle*Math.PI/180;
        x += DroneSpeed * Math.cos(radAngle);
    }
    protected String getStrType() {
        return "Drone";
    }
    public boolean hitting(double ox, double oy, double or, int i) {

        return (ox-x)*(ox-x) + (oy-y)*(oy-y) < (63/2+63/2) * (32/2+32/2);
    }



}

