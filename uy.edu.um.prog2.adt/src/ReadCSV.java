import exceptions.EmptyHashException;
import exceptions.InvalidKeyException;
import hash.Hash;
import linkedList.LinkedList;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReadCSV {
    private Hash<String, LinkedList<String>> world = new Hash<>(5);
    private Hash<String, Song> songs = new Hash<>(5);
    public BufferedReader reader;
    private String song;
    public String[] atributes;

    public void uploadCSV(String filename) {
        try {
            String keyActual = "****";
            String countryActual = "GLB";
            String date = "2024-05-13";
            int counter = 0;
            reader = new BufferedReader(new FileReader(filename));
            while ((song = reader.readLine()) != null) {
                // Remove the surrounding double quotes
                song = song.substring(1, song.length() - 1);
                // Replace all "" with empty string
                song = song.replaceAll("\"\"", "");
                // Split the song string by semicolon String[]
                atributes = song.split(",");
                //song = song.replaceAll("\"", "");

                System.out.println(song);
                //System.out.println(atributes[2]);
                if (counter > 1 && atributes.length > 2) {
                    if (atributes[6].isEmpty()) {
                        atributes[6] = countryActual;
                    }

                    if (atributes[7].isEmpty()) {
                        atributes[7] = date;
                    }

                    if (!songs.contains(atributes[0])) {
                        // Aquí modificas para seleccionar los atributos correctamente según tu archivo CSV
                        int daily_rank = Integer.parseInt(atributes[3]);
                        double tempo = Double.parseDouble(atributes[23]);
                        Song s = new Song(atributes[0], atributes[1], atributes[2], daily_rank, tempo);
                        songs.add(atributes[0], s);
                    }

                    String hashkey = atributes[6] + convertirYRevertirFecha(atributes[7]);
                    if (hashkey.equals(keyActual)) {
                        this.world.get(hashkey).addLast(atributes[0]);
                    } else {
                        this.createList(hashkey);
                        world.get(hashkey).addLast(atributes[0]);
                        keyActual = hashkey;
                    }
                }
                counter++;
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        } catch (EmptyHashException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }


    public void createList(String hashKey){
        LinkedList<String> global = new LinkedList<>();
        world.add(hashKey, global);
    }

    public Hash<String, LinkedList<String>> getWorld(){
        return world;
    }

    public Hash<String, Song> getSongs(){
        return songs;
    }

    // Método para convertir una fecha en formato "DD/MM/YYYY" a LocalDate en formato "YYYY/MM/DD"
    public static LocalDate convertirYRevertirFecha(String fechaStr) {
        // Define el formato de entrada
        DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // Define el formato de salida
        DateTimeFormatter formatoSalida = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Parsea la cadena de fecha a LocalDate
        LocalDate fecha = LocalDate.parse(fechaStr, formatoEntrada);
        // Convierte la fecha al formato "YYYY/MM/DD" y la retorna
        return LocalDate.parse(fecha.format(formatoSalida));
    }
}
