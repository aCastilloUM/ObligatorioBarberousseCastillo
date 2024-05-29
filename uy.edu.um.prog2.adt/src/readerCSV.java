import exceptions.*;
import hash.Hash;
import linkedList.LinkedList;

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
    public String eachPart[];

    public void readFile(String file_name) throws EmptyHashException, InvalidKeyException {
        Hash<String, LinkedList<Song>> world = new Hash<>(5);
        try {
            reader = new BufferedReader(new FileReader(file_name));
            int counter = 0;
            while ((line = reader.readLine()) != null) {
                line = line.replaceAll("\"", "");
                parts = line.split(" ; ");
                //printLine();
                //System.out.println();
                counter++;
                eachPart = parts[0].split(";");

                //El counter 1 saltea la primera linea, y eachPart.length>4 se asegura que haya info
                if (counter > 1 && eachPart.length > 4) {
                    //System.out.println(eachPart[6] + eachPart[7]);
                    //Es esta linea de arriba, nuestro eachPart es una lista que tiene en cada
                    // posicion cada elemento de la cancion

                    int daily_rank = Integer.parseInt(eachPart[3]);
                    int daily_movement = Integer.parseInt(eachPart[4]);
                    int weekly_movement = Integer.parseInt(eachPart[5]);
                    //LocalDate snapshot_date = toLocalDate(eachPart[7]);
                    int duration_ms = Integer.parseInt(eachPart[10]);

                    //Chequeamos que la cancion pertenezca a un album
                    /*LocalDate album_realease = null;
                    if (!eachPart[12].isEmpty()) {
                        album_realease = toLocalDate(eachPart[12]);
                    }
                     */

                    Double tempo = Double.valueOf(eachPart[23]);

                    String hashKey = eachPart[6] + eachPart[7]; //Codigo del pais
                    Song song = new Song(hashKey, eachPart[0], eachPart[1], eachPart[2], daily_rank, daily_movement, weekly_movement, eachPart[6], (String)eachPart[7], duration_ms, eachPart[11], eachPart[12], tempo);

                    //Chequeemos si el pais ya esta registrado
                    if (hashKey.length() == 9) {
                        //Solo globales - estoy creando un objeto en el hash por cada top global por fecha

                        LinkedList<Song> global = null;

                        if (!world.contains(hashKey)) {
                            //La fecha no fue registrada
                            global = new LinkedList<>();
                            world.add(hashKey, global);
                            global.addLast(song);
                        }
                        else {
                            world.get(hashKey).addLast(song);
                        }
                    }
                    else if (hashKey.length() > 9){
                        LinkedList<Song> countryDate = null;

                        if (!world.contains(hashKey)){
                            countryDate = new LinkedList<>();
                            world.add(hashKey, countryDate);
                            countryDate.addLast(song);
                        }
                        else{
                            world.get(hashKey).addLast(song);

                        }
                    }
                }
            }
            reader.close();
            line = null;
            parts = null;
            if (world.getSize()>100) {
                System.out.println("El metodo esta agregando");;
                //System.out.println(world.get("13/5/2024"));
                System.out.println(world.contains("PA27/4/2024"));
                world.get("PA27/4/2024");
            } else {
                System.out.println("El Hash esta vacio");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        } catch (EmptyHashException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public LocalDate toLocalDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate newDate = LocalDate.parse(date,formatter);
        return newDate;
    }

    public void printLine(){
        for (int i = 0; i < parts.length; i++){
            System.out.println(parts[i] + "    |   ");
        }
    }
}


