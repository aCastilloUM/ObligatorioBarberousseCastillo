import binaryTree.BinaryTree;
import hash.Hash;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class readerCSV {
    public BufferedReader reader;
    public String line;
    public String parts[];

    public void readFile(String file_name) {
        Hash<String, BinaryTree> world = new Hash<>(5);
        try {
            reader = new BufferedReader(new FileReader(file_name));
            int counter = 0;
            while ((line = reader.readLine()) != null){
                parts = line.split(" ; " );
                printLine();
                System.out.println();
                counter++;

                if (counter >=1) {
                    int daily_rank = Integer.parseInt(parts[3]);
                    int daily_movement = Integer.parseInt(parts[4]);
                    int weekly_movement = Integer.parseInt(parts[5]);

                    LocalDate snapshot_date = toLocalDate(parts[7]);

                    int duration_ms = Integer.parseInt(parts[10]);

                    LocalDate album_release = toLocalDate(parts[12]);

                    Double tempo = Double.valueOf(parts[23]);


                    Song song = new Song(parts[0], parts[1], parts[2], daily_rank, daily_movement, weekly_movement, parts[6], snapshot_date, duration_ms, parts[11], album_release, tempo);
                    String CountryCode = parts[6]; //Codigo del pais

                    //Chequeemos si el pais ya esta registrado
                    if (parts[6] == null) {

                        //Si el arbol global no esta creado, crearlo y meterlo en el hash
                        if (!world.contains("0")) {
                            BinaryTree<LocalDate, Song> global = new BinaryTree<>();
                        }
                    }
                }
            }
            reader.close();
            line = null;
            parts = null;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public LocalDate toLocalDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate newDate = LocalDate.parse(date,formatter);
        return newDate;
    }

    public void printLine(){
        for (int i = 0; i < parts.length; i++){
            System.out.println(parts[i] + "    |   ");
        }
    }
}
