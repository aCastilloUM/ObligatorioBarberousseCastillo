import exceptions.EmptyHashException;
import exceptions.EmptyStackException;
import exceptions.InvalidKeyException;

public interface ObligatoryMethods {
    void top10Song(String country, String date) throws EmptyHashException, InvalidKeyException;
    void top5RepeatedSongs(String date) throws EmptyHashException, InvalidKeyException, InvalidKeyException.EmptyHeapException, EmptyStackException.InvalidKeyException;
    void top7Artist(String first, String last) throws EmptyHashException, InvalidKeyException, InvalidKeyException.EmptyHeapException;
    void artistAppearances(String artistName, String country, String date) throws EmptyHashException, InvalidKeyException;
    void tempFunction(double tempo1, double tempo2, String first, String last);
}
