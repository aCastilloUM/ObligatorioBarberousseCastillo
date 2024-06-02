import exceptions.EmptyHashException;
import exceptions.InvalidKeyException;
import hash.Hash;
import linkedList.LinkedList;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadCSV {
    public Hash<String, LinkedList<String>> world = new Hash<>(5);
    public Hash<String, Song> songs = new Hash<>(5);
    public BufferedReader reader;
    private String song;
    public String[] atributes;

    public void uploadCSV(String filename){
        try {
            String keyActual = "****";
            String countryActual = "GLB";
            String date = "13/5/2024";
            int counter = 0;
            reader = new BufferedReader(new FileReader(filename));
            while ((song = reader.readLine()) != null){
                song = song.replaceAll("\"", "");
                atributes = song.split(";");
                if (counter > 1 && atributes.length > 2){
                    if (atributes[6].isEmpty()){
                        atributes[6] = countryActual;
                    }
                    if (atributes[7].isEmpty()){
                        atributes[7] = date;
                    }
                    String hashkey = atributes[6]+atributes[7];
                    if (!songs.contains(atributes[0])){
                        int daily_rank = Integer.parseInt(atributes[3]);
                        double tempo = Double.parseDouble(atributes[23]);
                        Song s = new Song(atributes[0],atributes[1],atributes[2],daily_rank,tempo);
                        songs.add(atributes[0],s);
                    }
                    if (hashkey.equals(keyActual)){
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
}
