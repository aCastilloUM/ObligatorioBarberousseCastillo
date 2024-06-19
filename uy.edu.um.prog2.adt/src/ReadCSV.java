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
    private String[] attributes;

    public void uploadCSV(String filename) {
        try {
            String keyActual = "*** *";
            String countryActual = "GLB";
            String date = "2024-05-13";
            int counter = 0;
            reader = new BufferedReader(new FileReader(filename));
            while ((song = reader.readLine()) != null) {
                attributes = song.split("\",\"|\",|,\"");

                for (int i = 0; i < attributes.length; i++) {
                    attributes[i] = attributes[i].replaceAll("\"", "");
                }
                if (counter > 0 && attributes.length > 2 && !attributes[0].equals("7hDoxkN20lLb06zifzYnD2") && !attributes[0].equals("11IqNbLOD4s4nVYSuEttFR")) {

                    if (attributes[6].isEmpty()) {
                        attributes[6] = countryActual;
                    }

                    if (attributes[7].isEmpty()) {
                        attributes[7] = date;
                    }

                    if (!songs.contains(attributes[0])) {
                        // Aquí modificas para seleccionar los atributos correctamente según tu archivo CSV
                        int daily_rank = Integer.parseInt(attributes[3]);
                        double tempo = Double.parseDouble(attributes[23]);
                        Song s = new Song(attributes[0], attributes[1].trim(), attributes[2].trim(), daily_rank, tempo);
                        songs.add(attributes[0], s);
                    }

                    // Usar StringBuilder para crear hashkey
                    StringBuilder sb = new StringBuilder();
                    sb.append(attributes[6].trim());
                    sb.append(attributes[7].trim());
                    String hashkey = sb.toString();

                    if (hashkey.equals(keyActual)) {
                        this.world.get(hashkey).addLast(attributes[0]);
                    } else {
                        this.createList(hashkey);
                        world.get(hashkey).addLast(attributes[0]);
                        keyActual = hashkey;
                    }
                }

                else if (attributes[0].equals("7hDoxkN20lLb06zifzYnD2")) {
                    attributes[1] = "Ishq - From ; Lost Found";
                    if (!songs.contains(attributes[0])) {
                        // Aquí modificas para seleccionar los atributos correctamente según tu archivo CSV
                        int daily_rank = Integer.parseInt(attributes[3]);
                        double tempo = Double.parseDouble(attributes[23]);
                        Song s = new Song(attributes[0], attributes[1], attributes[2].trim(), daily_rank, tempo);
                        songs.add(attributes[0], s);
                    }

                    // Usar StringBuilder para crear hashkey
                    StringBuilder sb = new StringBuilder();
                    sb.append(attributes[6]);
                    sb.append(attributes[7]);
                    String hashkey = sb.toString();

                    if (hashkey.equals(keyActual)) {
                        this.world.get(hashkey).addLast(attributes[0]);
                    } else {
                        this.createList(hashkey);
                        world.get(hashkey).addLast(attributes[0]);
                        keyActual = hashkey;
                    }

                } else if (attributes[0].equals("11IqNbLOD4s4nVYSuEttFR")) {
                    if (!songs.contains(attributes[0])) {
                        // Aquí modificas para seleccionar los atributos correctamente según tu archivo CSV
                        int daily_rank = Integer.parseInt(attributes[4]);
                        double tempo = Double.parseDouble(attributes[24]);
                        Song s = new Song(attributes[0], attributes[1], attributes[3].trim(), daily_rank, tempo);
                        songs.add(attributes[0], s);
                    }

                    // Usar StringBuilder para crear hashkey
                    StringBuilder sb = new StringBuilder();
                    sb.append(attributes[7]);
                    sb.append(attributes[8]);
                    String hashkey = sb.toString();

                    if (hashkey.equals(keyActual)) {
                        this.world.get(hashkey).addLast(attributes[0]);
                    } else {
                        this.createList(hashkey);
                        world.get(hashkey).addLast(attributes[0]);
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
        // Divide la cadena de fecha en partes usando '/'
        String[] partes = fechaStr.split("-");
        // Asegura que el día y el mes tengan dos dígitos
        String dia = partes[0].length() == 1 ? "0" + partes[0] : partes[0];
        String mes = partes[1].length() == 1 ? "0" + partes[1] : partes[1];
        // Crea la cadena de fecha con el formato "dd/MM/yyyy"
        String fechaFormateada = dia + "/" + mes + "/" + partes[2];

        // Define el formato de entrada
        DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Parsea la cadena de fecha a LocalDate
        return LocalDate.parse(fechaFormateada, formatoEntrada);
    }
}
