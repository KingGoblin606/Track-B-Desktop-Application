/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package track.b.desktop.application.Data;

import track.b.desktop.application.Model.Material;

import java.io.*;
import java.util.ArrayList;

public class MaterialFileRepository {

    private final String FILE_NAME = "data/materials.txt";


    // Saves all materials to the text file
    public void saveMaterials(ArrayList<Material> materials) {

        try {

            File file = new File(FILE_NAME);

            // Create folder if it does not exist
            file.getParentFile().mkdirs();


            FileWriter writer = new FileWriter(file);

            BufferedWriter buffer = new BufferedWriter(writer);


            for (Material material : materials) {

                buffer.write(
                    material.getMaterialId()
                    + "|" +
                    material.getName()
                    + "|" +
                    material.getCategory()
                    + "|" +
                    material.getQuantity()
                    + "|" +
                    material.getReorderLevel()
                    + "|" +
                    material.getSupplier()
                );

                buffer.newLine();
            }


            buffer.close();


        } catch (IOException e) {

            System.out.println(
                "Error saving materials: "
                + e.getMessage()
            );
        }
    }



    // Loads all materials from the text file
    public ArrayList<Material> loadMaterials() {


        ArrayList<Material> materials = new ArrayList<>();


        File file = new File(FILE_NAME);


        // If file does not exist return empty list
        if (!file.exists()) {
            return materials;
        }



        try {


            FileReader reader = new FileReader(file);

            BufferedReader buffer =
                    new BufferedReader(reader);



            String line;


            while ((line = buffer.readLine()) != null) {


                String[] data = line.split("\\|");


                Material material = new Material(

                        Integer.parseInt(data[0]),

                        data[1],

                        data[2],

                        Integer.parseInt(data[3]),

                        Integer.parseInt(data[4]),

                        data[5]
                );


                materials.add(material);
            }


            buffer.close();



        } catch (IOException e) {


            System.out.println(
                "Error loading materials: "
                + e.getMessage()
            );

        }


        return materials;
    }
}