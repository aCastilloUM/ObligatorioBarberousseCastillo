import exceptions.EmptyHashException;
import exceptions.InvalidKeyException;
import linkedList.LinkedList;

public class ObligatoryMethodsImpl implements ObligatoryMethods{
    ReadCSV file;

    public ObligatoryMethodsImpl(){
        file = new ReadCSV();
        file.uploadCSV("C:\\Users\\Lu\\Documents\\UM2024\\Programacion II\\universal_top_spotify_songs.csv");
    }

    @Override
    public void top10Song(String country, String date) throws EmptyHashException, InvalidKeyException {
        String key = country + date;
        System.out.println(key);
        LinkedList<String> top50 = file.getWorld().get(key);
        for (int i = 0; i < 10; i++){
            String songKey = top50.getValueNode(i);
            Song s = file.getSongs().get(songKey);
            System.out.println((i+1) + " - " + s.getName() + " " + s.getArtists());
        }
    }
}
