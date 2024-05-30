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
    private Hash<String, LinkedList<Song>> world = new Hash<>(5);

    public void readFile(String file_name) throws EmptyHashException, InvalidKeyException {
        try {
            reader = new BufferedReader(new FileReader(file_name));
            int counter = 0;
            String hashKey = null;
            while ((line = reader.readLine()) != null) {
                line = line.replaceAll("\"", "").trim();
                parts = line.split(" ; ");
                //printLine();
                //System.out.println();
                counter++;
                eachPart = parts[0].split(";");

                //El counter 1 saltea la primera linea, y eachPart.length>4 se asegura que haya info
                if (counter > 1 && eachPart.length > 4) {

                    int daily_rank = Integer.parseInt(eachPart[3]);
                    int daily_movement = Integer.parseInt(eachPart[4]);
                    int weekly_movement = Integer.parseInt(eachPart[5]);
                    //LocalDate snapshot_date = toLocalDate(eachPart[7]);
                    int duration_ms = Integer.parseInt(eachPart[10]);

                    Double tempo = Double.valueOf(eachPart[23]);

                    if (eachPart[6].isEmpty()) {
                        hashKey = eachPart[7];
                    } else {
                        hashKey = eachPart[6] + eachPart[7]; //Codigo del pais
                    }
                    Song song = new Song(hashKey, eachPart[0], eachPart[1], eachPart[2], daily_rank, daily_movement, weekly_movement, eachPart[6], (String) eachPart[7], duration_ms, eachPart[11], eachPart[12], tempo);

                    //Chequeemos si el pais ya esta registrado

                    if (world.contains(hashKey)) {
                        world.get(hashKey).addLast(song);
                    } else {
                        LinkedList<Song> global = createList(hashKey);
                        global.addLast(song);
                    }
                }
                System.out.println(hashKey);
                System.out.println(world.contains(hashKey));
            }


            reader.close();
            line = null;
            parts = null;
            if (world.getSize()>100) {
                System.out.println("El metodo esta agregando");;
                System.out.println(world.contains("2/12/2023"));
                System.out.println(world.contains("AE18/10/2023"));
                System.out.println(world.getSize());
                System.out.println(world.getLastHash());
                System.out.println(world.contains("UY18/10/2023"));
                //Creo que el problema son las canciones con la fecha 13/5/2024
                System.out.println(world.contains("13/5/2024"));
                System.out.println(world.contains("PK13/5/2024"));
                System.out.println(world.contains("13/5/2024"));
                System.out.println(world.getRemplazo());
            } else {
                System.out.println("El Hash esta vacio");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        } catch (EmptyHashException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public LinkedList<Song> createList(String hashKey){
        LinkedList<Song> global = new LinkedList<>();
        world.add(hashKey, global);
        return global;
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


