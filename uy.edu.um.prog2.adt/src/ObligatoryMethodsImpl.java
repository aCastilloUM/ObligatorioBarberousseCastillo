import exceptions.EmptyHashException;
import exceptions.InvalidKeyException;
import hash.Hash;
import heap.Heap;
import linkedList.LinkedList;
import linkedList.ListNode;
import linkedList.MyList;
import org.w3c.dom.NodeList;

import java.io.FilterOutputStream;
import java.security.Key;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class ObligatoryMethodsImpl implements ObligatoryMethods {
    ReadCSV file;

    //"C:\\Users\\agust\\OneDrive\\Escritorio\\universal_top_spotify_songs.csv"
    //"C:\\Users\\Lu\\Documents\\UM2024\\Programacion II\\universal_top_spotify_songs.csv"
    public ObligatoryMethodsImpl() {
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
        for (int i = 0; i < 10; i++) {
            String songKey = top50.getValueNode(i);
            Song s = file.getSongs().get(songKey);
            System.out.println((i + 1) + " - " + s.getName() + "    " + s.getArtists());
        }
    }

    LinkedList<ListNode<String>> dateSongs = new LinkedList<>();

    public void top5RepeatedSongs(String date) throws EmptyHashException, InvalidKeyException, InvalidKeyException.EmptyHeapException {
        int counter = 0;
        Hash<String, Integer> songsAppearances = new Hash<>(5);
        Heap<Integer, String> top5 = new Heap<>();

        for (int i = 0; i < abbreviations.length; i++) {
            String key = abbreviations[i] + date;
            LinkedList<String> top50 = file.getWorld().get(key);
            if (counter == 0) {
                dateSongs.addFirst(top50.getHead());
                enlazarNodosRecursivo(dateSongs.getHead().getValue(), top50.getHead().getNext());

            } else {
                enlazarNodosRecursivo(dateSongs.getLast().getValue(), top50.getHead());
            }
            counter++;
        }

        for (int i = 0; i < dateSongs.getSize(); i++) {

            String key = dateSongs.getValueNode(i).getValue().trim();

            if (!songsAppearances.contains(key)) {
                songsAppearances.add(key, 1);

            } else {
                Integer appareances = songsAppearances.get(key);
                appareances++;
                songsAppearances.add(key, appareances);
            }

        }

        for (int i = 0; i < songsAppearances.getTable().length; i++) {
            if (songsAppearances.getTable()[i] != null) {
                top5.add(songsAppearances.getTable()[i].getValue(), songsAppearances.getTable()[i].getKey());
            }
        }
        System.out.println("Top 5 canciones en " + date);
        for (int i = 0; i < 5; i++) {
            System.out.println(i + ". " + file.getSongs().get(top5.get()).getName() + "    " + file.getSongs().get(top5.get()).getArtists());
        }
    }

    public void top7Artist(String first, String last) throws EmptyHashException, InvalidKeyException, InvalidKeyException.EmptyHeapException {
        MyList<LocalDate> dates = new LinkedList<>();
        MyList<LocalDate> betweenDates = new LinkedList<>();
        Heap<Integer, String> artist = new Heap<>();
        Hash<String, Integer> artistAppearances = new Hash<>(7);

        //Convertimos las fechas a LocalDate

        LocalDate firstDate = null;
        LocalDate lastDate  = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            firstDate = LocalDate.parse(first, formatter);
            lastDate = LocalDate.parse(last, formatter);
        } catch (DateTimeParseException e) {
            System.err.println("Fecha no válida");
        }

        System.out.println(file.getWorld().getTable().length);
        //Recorremos la lista de canciones para obtener cada fecha
        for (int i = 0; i < file.getWorld().getTable().length; i++) {
            if (file.getWorld().getTable()[i] != null && file.getWorld().getTable()[i].getKey().length() == 10) {
                String fecha = file.getWorld().getTable()[i].getKey().substring(2);
                LocalDate date = LocalDate.parse(fecha);
                System.out.println(date);
                if (!dates.contains(date)) {
                    System.out.println("sexo");
                    dates.addLast(date);
                }
            }
        }

        for (int i = 0; i <dates.getSize(); i++) {
            System.out.println("sexo2");
            System.out.println(dates.getValueNode(i));
        }

        for (int i = 0; i <dates.getSize(); i++) {
            if (isBetween(dates.getValueNode(i), firstDate, lastDate)) {
                betweenDates.addLast(dates.getValueNode(i));
            }
        }

        //Combinamos cada fecha con cada pais, accedemos a la lista, accedemos en cada cancion al otro hash
        //Accedemos a los artistas, y si tiene comas contabilizamos por separado

        for (int i = 0; i < betweenDates.getSize(); i++) {
            for (int j =0 ; i<abbreviations.length ; i++){
                String key = abbreviations[j] + betweenDates.getValueNode(i);
                LinkedList<String> top50 = file.getWorld().get(key);
                for (int k = 0; k < 50; k++) {
                    String songKey = top50.getValueNode(k);

                    String artstListS = file.getSongs().get(songKey).artists;
                    String[] artistList = artstListS.split(",");

                    for (int z = 0; z< artistList.length; z++){
                        if (!artistAppearances.contains(artistList[z])) {
                            artistAppearances.add(artistList[z], 1);
                        } else {
                            Integer appearances = artistAppearances.get(artistList[z]);
                            appearances++;
                            artistAppearances.add(artistList[z], appearances);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < artistAppearances.getTable().length; i++) {
            if (artistAppearances.getTable()[i] != null) {
                artist.add(artistAppearances.getTable()[i].getValue(), artistAppearances.getTable()[i].getKey());
            }
        }
        System.out.println("Top 7 artistas");
        for (int i = 0; i < 5; i++) {
            System.out.println(artist.get());
        }

    }
        public void enlazarNodosRecursivo (ListNode < String > a, ListNode < String > b){
            if (b == null) // Verificar si b o su siguiente nodo son nulos
                return;

            this.dateSongs.addLast(b);
            a.setNext(b);
            enlazarNodosRecursivo(b, b.getNext());
        }

        public boolean isBetween (LocalDate date, LocalDate first, LocalDate last){
            return date.isAfter(first) && date.isBefore(last);
        }

        String[] abbreviations = {"GLB", "ZA", "VN", "VE", "UY", "US", "UA", "TW", "TR", "TH", "SV", "SK", "SG", "SE", "SA", "RO", "PY", "PT", "PL", "PK", "PH", "PE", "PA", "NZ", "NO",
                "NL", "NI", "NG", "MY", "MX", "MA", "LV", "LU", "LT", "KZ", "KR", "JP", "IT", "IS", "IN", "IL", "IE", "ID", "HU", "HN", "HK", "GT", "GR", "GB", "FR", "FI", "ES", "EG", "EE",
                "EC", "DO", "DK", "DE", "CZ", "CR", "CO", "CL", "CH", "CA", "BY", "BR", "BO",
                "BG", "BE", "AU", "AT", "AR", "AE"};


}