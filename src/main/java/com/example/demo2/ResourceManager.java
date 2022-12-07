package com.example.demo2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ResourceManager implements Serializable {

    private static DroneArena droneArena;

    static void export() { // saves/exports the simulation
        try {
            FileOutputStream outFile = new FileOutputStream("C:\\Users\\callu\\droneSim.txt");
            ObjectOutputStream outStream = new ObjectOutputStream(outFile);
            outStream.writeObject(droneArena);
            outStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }}

        public static void importGame() { // imports/loads
         File inFile = new File("C:\\Users\\callu\\droneSim.txt");
         try {
         FileInputStream inStream = new FileInputStream(inFile);
         ObjectInputStream inObjectStream = new ObjectInputStream(inStream);
         droneArena = (DroneArena) inObjectStream.readObject();
         inObjectStream.close();
         } catch (IOException e) {
         throw new RuntimeException(e);
         } catch (ClassNotFoundException e) {
         e.printStackTrace();
         }
         }

}