
package com.example.demo2;


public class Obstacle extends FirstDrone {
    double DroneAngle, DroneSpeed;
    static int idCount;


    private int ID;
    public Obstacle(double ix, double iy, double ir, double ia, double is) {
        super(ix, iy, ir);
        DroneAngle = ia;
        DroneSpeed = is;

    }

    @Override
    public boolean isHere(double x, double y) {
        double top = y+80+80*2;
        double bottom = y-80-80*2;
        double left = x-80-80*2;
        double right = x+80+80*2;
        if((x <= right && x >= left) && (y <= top && y >= bottom)) { //check if there
            return true;
        }

        return false;
    }

    protected String getStrType() {
        return "Drone";
    }

    @Override
    protected void checkdrone(DroneArena b) {
        DroneAngle = b.checkObsAngle(x, y, rad, DroneAngle, this);
    }

    @Override
    protected void adjustdrone() {

    }

    public boolean hitting(double ox, double oy, double or, int i) {
        return (ox-x)*(ox-x) + (oy-y)*(oy-y) < (63/2+63/2) * (32/2+32/2);
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