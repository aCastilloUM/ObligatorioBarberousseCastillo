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
            String keyActual = "*** *";
            String countryActual = "GLB";
            String date = "2024-05-13";
            int counter = 0;
            reader = new BufferedReader(new FileReader(filename));
            while ((song = reader.readLine()) != null) {
                atributes = parseLine(song);
                if (counter > 1 && atributes.length > 2 && !atributes[0].equals("7hDoxkN20lLb06zifzYnD2")) {

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
        // Divide la cadena de fecha en partes usando '/'
        String[] partes = fechaStr.split("/");
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

    private String[] parseLine(String line) {
        boolean inQuotes = false;
        StringBuilder sb = new StringBuilder();
        java.util.LinkedList<String> tokens = new java.util.LinkedList<>();

        char[] chars = line.toCharArray();
        for (char c : chars) {
            switch (c) {
                case '"':
                    inQuotes = !inQuotes;
                    break;
                case ';':
                    if (inQuotes) {
                        sb.append(c);
                    } else {
                        tokens.add(sb.toString());
                        sb.setLength(0);
                    }
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        tokens.add(sb.toString()); // Agregar el último token
        return tokens.toArray(new String[0]);
    }

}
