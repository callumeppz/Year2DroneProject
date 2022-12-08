
package com.example.demo2;


public class Obstacle extends FirstDrone {
    double DroneAngle, DroneSpeed;
    static int idCount;


    private int ID;
    public Obstacle(double ix, double iy, double ir, double ia, double is) {
        super(ix, iy, ir);
        DroneAngle = ia;
        DroneSpeed = is;
        /**
         *
         */

    }

    protected String getStrType() {
        return "Drone";
    }

    @Override
    protected void checkdrone(DroneArena b) {
        DroneAngle = b.checkObsAngle(x, y, rad, DroneAngle, this); // checks the angle of obstacle and if its hitting any other entities.
        /**
         *
         */
    }

    @Override
    protected void adjustdrone() {

    }
    public boolean hitting(double ox, double oy, double or, int i) {
        return (ox-x)*(ox-x) + (oy-y)*(oy-y) < (63/2+63/2) * (32/2+32/2); //used within the collision function
        /**
         *
         */
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