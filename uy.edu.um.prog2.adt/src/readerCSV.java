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
    public String[] atributes;
    private Hash<String, LinkedList<Song>> world = new Hash<>(5);

    public void readFile(String file_name) throws EmptyHashException, InvalidKeyException {

        try {
            reader = new BufferedReader(new FileReader(file_name));
            int counter = 0;
            String hashKey = null;
            boolean e = true;
            while ((line = reader.readLine()) != null) {
                line = line.replaceAll("\"", "");
                atributes = line.split(";");
                //System.out.println(atributes[11]);
                //System.out.println(atributes.length);
                //printLine();
                //System.out.println();
                counter++;

                //El counter 1 saltea la primera linea, y eachPart.length>4 se asegura que haya info
                if (counter > 1 && atributes.length > 4) {

                    int daily_rank = Integer.parseInt(atributes[3]);
                    //LocalDate snapshot_date = toLocalDate(eachPart[7]);

                    double tempo = Double.parseDouble(atributes[23]);

                    if (atributes[6].isEmpty()) {
                        hashKey = atributes[7];
                    } else {
                        hashKey = atributes[6] + atributes[7]; //Codigo del pais
                    }
                    Song song = new Song(atributes[0], atributes[1], atributes[2], daily_rank, atributes[6], atributes[7], tempo);

                    //Chequeemos si el pais ya esta registrado
                    if (world.contains(hashKey)) {
                        world.get(hashKey).addLast(song);
                    } else {
                        this.createList(hashKey);
                        world.get(hashKey).addLast(song);
//                        System.out.println("Tamanos");
//                        System.out.println(world.getSize());
//                        System.out.println(world.get(hashKey).getSize());
//                        System.out.println("Id");
//                        System.out.println(world.get(hashKey).getValueNode(0));
//                        System.out.println("Artista");
//                        System.out.println(world.get(hashKey).getValueNode(2));
//                        System.out.println("Fecha");
//                        System.out.println(world.get(hashKey).getValueNode(7));
                    }
                    // System.out.println(world.contains(hashKey));
                    if (!world.contains(hashKey)){
                        e = false;
                        break;
                    }
                    //System.out.println(world.getSize());
                    //System.out.println(world.getCapacity());
                    //System.out.println(world.get(hashKey).getSize());
                }
            }


            reader.close();
            line = null;
            atributes = null;

            if (world.getSize()>100) {
//                System.out.println("El metodo esta agregando");;
//                System.out.println(world.contains("2/12/2023"));
//                System.out.println(world.contains("AE18/10/2023"));
//                System.out.println(world.getSize());
                  System.out.println(world.getFirstHash());
//                System.out.println(world.contains("UY18/10/2023"));
//                //Creo que el problema son las canciones con la fecha 13/5/2024
                System.out.println(world.contains(world.firstHash));
                System.out.println(e);
//                System.out.println(world.contains("PK13/5/2024"));
//                System.out.println(world.contains("13/5/2024"));
//                System.out.println(world.getRemplazo());
            } else {
                System.out.println("El Hash esta vacio");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        } catch (EmptyHashException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }


    public void createList(String hashKey){
        LinkedList<Song> global = new LinkedList<>();
        world.add(hashKey, global);
    }
    public LocalDate toLocalDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate newDate = LocalDate.parse(date,formatter);
        return newDate;
    }

    public void printLine(){
        for (int i = 0; i < atributes.length; i++){
            System.out.println(atributes[i] + "    |   ");
        }
    }
}


