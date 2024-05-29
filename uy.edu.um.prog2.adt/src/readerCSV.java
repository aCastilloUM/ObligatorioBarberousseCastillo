import binaryTree.BinaryTree;
import exceptions.EmptyHashException;
import exceptions.InvalidKeyException;
import hash.Hash;
import heap.Heap;

import javax.swing.*;
import javax.swing.plaf.IconUIResource;
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

    public void readFile(String file_name) {
        Hash<String, Heap> world = new Hash<>(5);
        try {
            reader = new BufferedReader(new FileReader(file_name));
            int counter = 0;
            int counter2 = 0;
            int counter3 = 0;
            int counter4 = 0;
            int hashSize = 0;
            int hashCapacity = 0;
            int cantidadHeap = 0;
            while ((line = reader.readLine()) != null) {
                parts = line.split(" ; ");
                //printLine();
                //System.out.println();
                counter++;

                eachPart = parts[0].split(";");
                //El counter 1 saltea la primera linea, y eachPart.length>4 se asegura que haya info
                if (counter > 1 && eachPart.length > 4) {

                    //Es esta linea de arriba, nuestro eachPart es una lista que tiene en cada
                    // posicion cada elemento de la cancion digamo

                    int daily_rank = Integer.parseInt(eachPart[3]);
                    int daily_movement = Integer.parseInt(eachPart[4]);
                    int weekly_movement = Integer.parseInt(eachPart[5]);
                    LocalDate snapshot_date = toLocalDate(eachPart[7]);
                    int duration_ms = Integer.parseInt(eachPart[10]);

                    //Chequeamos que la cancion pertenezca a un album
                    LocalDate album_realease = null;
                    if (!eachPart[12].isEmpty()) {
                        album_realease = toLocalDate(eachPart[12]);
                    }

                    Double tempo = Double.valueOf(eachPart[23]);

                    String hashKey = eachPart[6] + eachPart[7]; //Codigo del pais
                    Song song = new Song(hashKey, eachPart[0], eachPart[1], eachPart[2], daily_rank, daily_movement, weekly_movement, eachPart[6], snapshot_date, duration_ms, eachPart[11], album_realease, tempo);

                    //Chequeemos si el pais ya esta registrado

                    if (hashKey.length() == 9) {
                        //Solo globales
                        counter2++;
                        //Estoy creando un objeto en el hash por cada top global por fecha

                        Heap<Integer, Song> global = null;

                        if (!world.contains(hashKey)) {
                            //La fecha no fue registrada
                            global = new Heap<>();
                            world.add(hashKey, global);
                            global.add(song.getDaily_rank(), song);
                            counter3++;

                        }

                        else {

                            world.get(hashKey).add(song.getDaily_rank(),song);
                            counter4++;
                            hashCapacity = world.getCapacity();
                            hashSize = world.getSize();
                            cantidadHeap = world.get(hashKey).getTable().size();


                        }
                    }
                    else {
                        Heap<Integer, Song> countryDate = null;

                        if (!world.contains(hashKey)){
                            countryDate = new Heap<>();
                            world.add(hashKey, countryDate);
                            countryDate.add(song.getDaily_rank(), song);
                        }
                        else{
                            world.get(hashKey).add(song.getDaily_rank(),song);


                        }

                    }
                    hashCapacity = world.getCapacity();
                    hashSize = world.getSize();
                    cantidadHeap = world.get(hashKey).getTable().size();
                }

            }
            System.out.println(counter2);
            System.out.println(counter3);
            System.out.println(counter4);
            System.out.println(counter3 + counter4);
            System.out.println(" hash capacity " + hashCapacity);
            System.out.println(" hash size " + hashSize);
            System.out.println(cantidadHeap);
            reader.close();
            line = null;
            parts = null;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        } catch (EmptyHashException e) {
            throw new RuntimeException(e);
        } catch (exceptions.InvalidKeyException e) {
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
