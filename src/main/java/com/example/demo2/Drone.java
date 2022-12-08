
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
        DroneSpeed = 6;
        int height = 63;
        int Width = 63;
        int dronehealth1 = dronehealth;
        ID = idCount++; //unique ID
    }

    public void adjustdrone() {
        double radAngle = DroneAngle*Math.PI/180;
        if (!isplayer) { // if the player controls are not set do this
            x += DroneSpeed * Math.cos(radAngle); // allows for drone automatic movement
            y += DroneSpeed * Math.sin(radAngle);// changes drone direction

        }
    }
    protected void checkdrone(DroneArena b) {
        DroneAngle = b.checkDroneAngle(x, y, rad, DroneAngle, this); // used for collisions
        DroneAngle = b.checkObsAngle(x,y,rad,DroneAngle,this);
        DroneAngle = b.checkMeteorAngle(x,y,DroneAngle,this);
    }
    public boolean hitting(double ox, double oy, double or, int i ) { // used for collisions
        return (ox-x)*(ox-x) + (oy-y)*(oy-y) < (100/2+100/2) * (55/2+55/2);
    }

    /**
     * save info of drone on each line of string
     */

    public String toString() {
        return "Drone " + ID + " at " + String.format("%.0f",x) + "," + String.format("%.0f",y) + "\nwith angle " + String.format("%.2f",DroneAngle);
    }

    /**
     * allows setting of ID
     * @param givenID shows given id
     */
    public void setID(int givenID){
        ID = givenID;
    }
}
