package com.example.demo2;
public class Player{
        double DroneAngle, DroneSpeed;
        public Player(double ix, double iy, double ir, double ia, double is) {
            DroneAngle = ia;
            DroneSpeed = is;
        }

        //inset WASD controls here
    // trytomove function

        protected String getStrType() {
            return "Player";
        }

    }

