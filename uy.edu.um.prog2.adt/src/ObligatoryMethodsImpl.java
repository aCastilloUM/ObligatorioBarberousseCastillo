import exceptions.EmptyHashException;
import exceptions.InvalidKeyException;
import linkedList.LinkedList;
import linkedList.ListNode;
import org.w3c.dom.NodeList;

import java.io.FilterOutputStream;
import java.security.Key;
import java.util.List;

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

        int counter = 0;
        LinkedList<ListNode<String>> dateSongs = new LinkedList<>();
        ListNode<String> firstNotLinkedNode = null;

        for (int i = 0 ; i<abbreviations.length; i++){
            counter++;
            String key = abbreviations[i] + date;
            System.out.println(key);

            try{
                if (counter == 1){
                    dateSongs.addFirst(file.getWorld().get(key).getHead());
                    enlazarNodosRecursivo(dateSongs.getHead().getValue(),file.getWorld().get(key).getHead().getNext());
                }
                else{
                    firstNotLinkedNode = file.getWorld().get(key).getHead();
                    dateSongs.addLast(firstNotLinkedNode);
                }
            } catch (InvalidKeyException | exceptions.EmptyHashException EmptyHashException ){
                System.out.println("No hay pais");
            }
        }
        //Veamos q se mantenga
        System.out.println(dateSongs.getHead().getValue().getValue());
        System.out.println(dateSongs.getHead().getNext().getNext().getNext().getValue().getValue());
    }

    public static void enlazarNodosRecursivo(ListNode<String> a, ListNode<String> b) {
        if (a == null || b.getNext() == null)
            return;

        a.setNext(b);
        enlazarNodosRecursivo(a, b.getNext());
    }


    String[] abbreviations = {"GLB","ZA"};

}
