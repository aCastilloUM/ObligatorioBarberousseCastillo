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
            while ((line = reader.readLine()) != null) {
                parts = line.split(" ; ");
                //printLine();
                //System.out.println();
                counter++;

                eachPart = parts[0].split(";");
                //El counter 1 ssaltea la primera linea, y eachPart.length>4 se asegura que haya info
                if (counter > 1 && eachPart.length > 4) {

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
                        counter2++;
                        if (!world.contains(hashKey)) {
                            Heap<Integer, Song> global = new Heap<>();
                            world.add(hashKey, global);
                            global.add(song.getDaily_rank(), song);
                            System.out.println(world.get(hashKey).get());
                        }
                    }
                }
            }
            System.out.println(counter2);
            reader.close();
            line = null;
            parts = null;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        } catch (EmptyHashException e) {
            throw new RuntimeException(e);
        } catch (exceptions.InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException.EmptyHeapException e) {
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
