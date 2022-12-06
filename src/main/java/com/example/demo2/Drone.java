
package com.example.demo2;


public class Drone extends FirstDrone {
    double DroneAngle, DroneSpeed;
    public Drone(int ix, int iy, double ir, double ia, double is) {
        super(ix, iy, ir);
        DroneAngle = ia;
        DroneSpeed = is;
        int height = 63;
        int Width = 63;
    }

    public void adjustdrone() {
        double radAngle = DroneAngle*Math.PI/180;
        x += DroneSpeed * Math.cos(radAngle);
        y += DroneSpeed * Math.sin(radAngle);
    }
    protected void checkdrone(DroneArena b) {
        DroneAngle = b.checkDroneAngle(x, y, rad, DroneAngle, this);
    }
    public boolean hitting(double ox, double oy, double or, int i) {
        return (ox-x)*(ox-x) + (oy-y)*(oy-y) < (63/2+63/2) * (32/2+32/2);
    }



    protected String getStrType() {
        return "Drone";
    }

}