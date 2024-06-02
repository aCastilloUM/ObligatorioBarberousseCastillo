import exceptions.EmptyHashException;
import exceptions.InvalidKeyException;
import linkedList.LinkedList;

public class ObligatoryMethodsImpl implements ObligatoryMethods{
    ReadCSV file = new ReadCSV();

    @Override
    public void top10Song(String country, String date) throws EmptyHashException, InvalidKeyException {
        String key = country + date;
        System.out.println(key);
        LinkedList<String> top50 = file.world.get(key);
        for (int i = 0; i < 10; i++){
            String songKey = top50.getValueNode(i);
            Song s = file.songs.get(songKey);
            System.out.println((i+1) + " - " + s.getName() + " " + s.getArtists());
        }
    }
}