
package com.example.demo2;


public class Drone extends FirstDrone {
    public int health = 100;
    double DroneAngle, DroneSpeed;
    int dronehealth;
    static int idCount;

    private int ID;
    public boolean isplayer;

    /**
     *
     * @param ix
     * @param iy
     * @param ir
     * @param ia
     * @param is
     */

    public Drone(int ix, int iy, double ir, double ia, double is) {
        super(ix, iy, ir);
        DroneAngle = ia;
        DroneSpeed = 9;
        int height = 63;
        int Width = 63;
        int dronehealth1 = dronehealth;
        ID = idCount++; //unique ID
    }

    public void adjustdrone() {
        double radAngle = DroneAngle*Math.PI/180;
        if (!isplayer) {
            x += DroneSpeed * Math.cos(radAngle);
            y += DroneSpeed * Math.sin(radAngle);
        }



    }
    protected void checkdrone(DroneArena b) {
        DroneAngle = b.checkDroneAngle(x, y, rad, DroneAngle, this);
        DroneAngle = b.checkObsAngle(x,y,rad,DroneAngle,this);
        DroneAngle = b.checkMeteorAngle(x,y,DroneAngle,this);
    }
    public boolean hitting(double ox, double oy, double or, int i ) {
        return (ox-x)*(ox-x) + (oy-y)*(oy-y) < (100/2+100/2) * (55/2+55/2);
    }

    /**
     * save info of drone on each line of string
     */

    public String toString() {
        return "Drone " + ID + " at " + String.format("%.0f",x) + "," + String.format("%.0f",y) + "\nwith angle " + String.format("%.2f",DroneAngle);
    }

    @Override
    public boolean isHere(double x, double y) {
        return false;
    }

    /**
     * allows setting of ID
     * @param givenID shows given id
     */
    public void setID(int givenID){
        ID = givenID;
    }
}
