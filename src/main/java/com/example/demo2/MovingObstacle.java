
package com.example.demo2;


public class MovingObstacle extends FirstDrone {
    double DroneAngle, DroneSpeed;
    int dronehealth;
    static int idCount;

    private int ID;
    public MovingObstacle(int ix, int iy, double ir, double ia, double is) {
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
        x += DroneSpeed * Math.cos(radAngle);

    }
    protected void checkdrone(DroneArena b) {
        DroneAngle = b.checkDroneAngle(x, y, rad, DroneAngle, this);
        DroneAngle = b.checkObsAngle(x,y,rad,DroneAngle,this);
        DroneAngle = b.checkMeteorAngle(x,y,DroneAngle,this);
    }
    public boolean hitting(double ox, double oy, double or, int i ) {
        return (ox-x)*(ox-x) + (oy-y)*(oy-y) < (63/2+63/2) * (32/2+32/2);
    }
    public static void main()
    {
        idCount = 0;
    }



    public String toString() {
        return "Drone " + ID + " is at " + String.format("%.2f",x) + "," + String.format("%.2f",y) + " with angle " + String.format("%.2f",rad);
    }



    /**
     * allows setting of ID
     * @param givenID shows given id
     */

    public void setID(int givenID){
        ID = givenID;
    }
}
