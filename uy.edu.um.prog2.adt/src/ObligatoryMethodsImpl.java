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
        file.uploadCSV("C:\\Users\\agust\\OneDrive\\Escritorio\\universal_top_spotify_songs.csv");
    }

    //"C:\\Users\\agust\OneDrive\\Escritorio\\universal_top_spotify_songs.csv"
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
    LinkedList<ListNode<String>> dateSongs = new LinkedList<>();
    public void top5RepeatedSongs(String date){

        int counter = 0;
        ListNode<String> firstNotLinkedNode = null;

        for (int i = 0 ; i<abbreviations.length; i++){
            counter++;
            String key = abbreviations[i] + date;
            System.out.println(key);

            try{
                if (counter == 1){
                    dateSongs.addFirst(file.getWorld().get(key).getHead());
                    enlazarNodosRecursivo(dateSongs.getHead().getValue(),file.getWorld().get(key).getHead().getNext());
                    System.out.println(dateSongs.getSize());
                    dateSongs.printList();
                }
                else{
                    getTail(dateSongs.getHead().getValue()).setNext(file.getWorld().get(key).getHead());
                    dateSongs.printList();
                    System.out.println(dateSongs.getSize());
                    enlazarNodosRecursivo(dateSongs.getLast().getValue(), file.getWorld().get(key).getHead());
                }
            } catch (InvalidKeyException | exceptions.EmptyHashException EmptyHashException ){
                System.out.println("No hay pais");
            }
        }
        //Veamos q se mantenga
        System.out.println(dateSongs.getSize());
    }

    public void enlazarNodosRecursivo(ListNode<String> a, ListNode<String> b) {
        if (b == null || b.getNext() == null) // Verificar si b o su siguiente nodo son nulos
            return;

        this.dateSongs.addLast(b);
        a.setNext(b);
        enlazarNodosRecursivo(b, b.getNext());
    }

    public static ListNode<String> getTail(ListNode<String> head) {
        // Caso base: si la cabeza es nula o su siguiente nodo es nulo, la cola también será nula
        if (head == null || head.getNext() == null) {
            return head;
        }

        // Caso recursivo: llamar al método con el siguiente nodo
        return getTail(head.getNext());
    }
    String[] abbreviations = {"GLB","ZA"};

}
