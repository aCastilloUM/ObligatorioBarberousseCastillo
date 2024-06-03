import exceptions.EmptyHashException;
import exceptions.InvalidKeyException;
import linkedList.LinkedList;

import java.security.Key;

public class ObligatoryMethodsImpl implements ObligatoryMethods{
    ReadCSV file;

//"C:\\Users\\agust\\OneDrive\\Escritorio\\universal_top_spotify_songs.csv"
    //"C:\\Users\\Lu\\Documents\\UM2024\\Programacion II\\universal_top_spotify_songs.csv"
    public ObligatoryMethodsImpl(){
        file = new ReadCSV();
        file.uploadCSV("C:\\Users\\agust\\OneDrive\\Escritorio\\ExcelObligatorio.csv");
    }

    @Override
    public void top10Song(String country, String date) throws EmptyHashException, InvalidKeyException {
        System.out.println(file.getWorld().getSize());
        System.out.println(file.getWorld().getFirstHash());
        String key = country + date;
        LinkedList<String> top50 = file.getWorld().get(key);
        for (int i = 0; i < 10; i++){
            String songKey = top50.getValueNode(i);
            Song s = file.getSongs().get(songKey);
            System.out.println((i+1) + " - " + s.getName() + "    " + s.getArtists());
        }
    }

    public void top5RepeatedSongs(String date){

    }
}
