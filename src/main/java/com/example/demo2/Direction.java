package com.example.demo2;

import java.util.Random;
import java.io.*;

public enum Direction{
    North,
    East,
    South,
    West;

    static Direction RandomDirec() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
    public static Direction getNextDirection(Direction d) {
        switch(d) {
            case North:
                d = East;
        }
        return d;

    }
}
