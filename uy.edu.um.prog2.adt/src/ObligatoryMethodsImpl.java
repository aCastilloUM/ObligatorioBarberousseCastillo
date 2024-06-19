import exceptions.EmptyHashException;
import exceptions.EmptyStackException;
import exceptions.InvalidKeyException;
import hash.Hash;
import heap.Heap;
import linkedList.LinkedList;
import linkedList.ListNode;
import linkedList.MyList;
import org.w3c.dom.NodeList;

import java.io.FilterOutputStream;
import java.security.Key;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class ObligatoryMethodsImpl implements ObligatoryMethods {
    ReadCSV file;

    public ObligatoryMethodsImpl(String csvPath) {

        /* Este código fue lo que utilizamos para ir midiendo el consumo de memoria RAM
        Runtime runtime = Runtime.getRuntime();
        runtime.gc(); // Correr el garbage collector para liberar memoria no utilizada
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
         */

        file = new ReadCSV();
        file.uploadCSV(csvPath);

        // Fin de la medición de memoria
        /* runtime.gc(); // Correr el garbage collector para liberar memoria no utilizada
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Memoria utilizada por el método reader en kilobytes: " + (memoryAfter - memoryBefore) / 1024);
         */
        }

    @Override
    public void top10Song(String country, String date) throws EmptyHashException, InvalidKeyException {

        // Inicio de la medición de memoria
        Runtime runtime = Runtime.getRuntime();
        runtime.gc(); // Correr el garbage collector para liberar memoria no utilizada
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();

        System.out.println();
        System.out.println("TOP 10 CANCIONES EN LA FECHA " + date);
        try {
            String key = country + date;
            LinkedList<String> top50 = file.getWorld().get(key);
            for (int i = 0; i < 10; i++) {
                String songKey = top50.getValueNode(i);
                Song s = file.getSongs().get(songKey);
                System.out.println((i + 1) + " - " + s.getName() + " | " + s.getArtists());
            }
        } catch (NullPointerException | InvalidKeyException e){
            System.out.println("Fecha no disponible");
        }

        // Fin de la medición de memoria
        runtime.gc(); // Correr el garbage collector para liberar memoria no utilizada
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();

        //System.out.println("Memoria utilizada por el método top10Song en bytes: " + (memoryAfter - memoryBefore));
    }

    LinkedList<ListNode<String>> dateSongs = new LinkedList<>();

    @Override
    public void top5RepeatedSongs(String date) throws EmptyHashException, InvalidKeyException.EmptyHeapException {

        Runtime runtime = Runtime.getRuntime();
        runtime.gc(); // Correr el garbage collector para liberar memoria no utilizada
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();

        int counter = 0;
        Hash<String, Integer> songsAppearances = new Hash<>(5);
        Heap<Integer, String> top5 = new Heap<>();

        try {
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
                    Integer appearances = songsAppearances.get(key);
                    appearances++;
                    songsAppearances.add(key, appearances);
                }
            }

            for (int i = 0; i < songsAppearances.getTable().length; i++) {
                if (songsAppearances.getTable()[i] != null) {
                    top5.add(songsAppearances.getTable()[i].getValue(), songsAppearances.getTable()[i].getKey());
                }
            }

            System.out.println();
            System.out.println("TOP 5 CANCIONES EN " + date);
            for (int i = 0; i < 5; i++) {
                if (top5.getSize() > 0) { // Asegúrate de que hay elementos en el heap antes de intentar obtener y eliminar
                    String songKey = top5.get();
                    int appearances = songsAppearances.get(songKey); // Obtener el número de apariciones
                    System.out.println((i + 1) + " - " + file.getSongs().get(songKey).getName() + " | " + file.getSongs().get(songKey).getArtists() + " | (" + appearances + ") apariciones");
                    top5.delete(); // Elimina el elemento en el heap por clave
                }
            }

        } catch (InvalidKeyException | NullPointerException e){
            System.out.println("Error: No se pudo procesar las canciones para la fecha especificada. ");
        }

        runtime.gc(); // Correr el garbage collector para liberar memoria no utilizada
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();

        //System.out.println("Memoria utilizada por el método top5RepeatedSongs en bytes: " + (memoryAfter - memoryBefore));
    }

    @Override
    public void top7Artist(String first, String last) throws EmptyHashException, InvalidKeyException, InvalidKeyException.EmptyHeapException{

        Runtime runtime = Runtime.getRuntime();
        runtime.gc(); // Correr el garbage collector para liberar memoria no utilizada
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();

        MyList<LocalDate> betweenDates = new LinkedList<>();
        Heap<Integer, String> artist = new Heap<>();
        Hash<String, Integer> artistAppearances = new Hash<>(7);

        //Convertimos las fechas a LocalDate
        LocalDate firstDate = null;
        LocalDate lastDate = null;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            firstDate = LocalDate.parse(first, formatter);
            lastDate = LocalDate.parse(last, formatter);
            betweenDates.addFirst(firstDate);
            if (!lastDate.isEqual(firstDate)) {
                betweenDates.addLast(lastDate);
            }

        } catch (DateTimeParseException e) {}

        if (firstDate.isAfter(lastDate)) {
            LocalDate temp = firstDate;
            firstDate = lastDate;
            lastDate = temp;
        }

        for (int i = 0; i<this.dates.length; i++){
            LocalDate date = LocalDate.parse(this.dates[i]);
            if (isBetween(date, firstDate, lastDate)){
                betweenDates.addLast(date);
            }
        }

        //Combinamos cada fecha con cada pais, accedemos a la lista, accedemos en cada cancion al otro hash
        //Accedemos a los artistas, y si tiene comas contabilizamos por separado

        for (int i = 0; i < betweenDates.getSize(); i++) {
            for (int j =0 ; j < abbreviations.length ; j++){
                StringBuilder sb = new StringBuilder();
                sb.append(abbreviations[j].trim());
                sb.append(betweenDates.getValueNode(i).toString().trim());
                String key = sb.toString();
                try { // Si no existe la fecha en el top global, se salta
                    LinkedList<String> top50 = file.getWorld().get(key);
                    for (int k = 0; k < 50; k++) {
                        String songKey = top50.getValueNode(k);

                        String artstListS = file.getSongs().get(songKey).artists;
                        String[] artistList = artstListS.split(",");

                        for (int z = 0; z < artistList.length; z++) {
                            if (!artistAppearances.contains(artistList[z].trim())) {
                                artistAppearances.add(artistList[z].trim(), 1);
                            } else {
                                Integer appearances = artistAppearances.get(artistList[z].trim());
                                appearances++;
                                artistAppearances.add(artistList[z].trim(), appearances);
                            }
                        }
                    }
                } catch (NullPointerException | InvalidKeyException e) {}
            }
        }

        for (int i = 0; i < artistAppearances.getTable().length; i++) {
            if (artistAppearances.getTable()[i] != null) {
                artist.add(artistAppearances.getTable()[i].getValue(), artistAppearances.getTable()[i].getKey());
            }
        }

        System.out.println();
        System.out.println("TOP 7 ARTISTAS CON MÁS APARICIONES EN EL RANGO " + first + " - " + last);
        for (int i = 0; i < 7; i++) {
            String artistKey = artist.get();
            int appearances = artistAppearances.get(artistKey); // Obtener el número de apariciones
            System.out.println((i + 1) + " - " + artistKey + " | (" + appearances + ") apariciones");
            artist.delete();
        }

        runtime.gc(); // Correr el garbage collector para liberar memoria no utilizada
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();

        //System.out.println("Memoria utilizada por el método top7Artist en bytes: " + (memoryAfter - memoryBefore));
    }

    @Override
    public void artistAppearances(String artistName, String date) throws EmptyHashException, InvalidKeyException {

        Runtime runtime = Runtime.getRuntime();
        runtime.gc(); // Correr el garbage collector para liberar memoria no utilizada
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();

        Hash<String, Integer> artistAppearances = new Hash<>(1);
        artistAppearances.add(artistName, 0);
        LocalDate localDate = null;
        System.out.println();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            localDate = LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {}

        try {
            for (int z = 0; z < abbreviations.length; z++) {
                String key =abbreviations[z] + date;

                LinkedList<String> top50 = file.getWorld().get(key);
                for (int i = 0; i < 50; i++) {
                    String songKey = top50.getValueNode(i);
                    Song s = file.getSongs().get(songKey);
                    String[] artists = s.getArtists().split(",");
                    for (int j = 0; j < artists.length; j++) {
                        if (artists[j].trim().equals(artistName)) {
                            if (artistAppearances.contains(artistName)) {
                                Integer appearances = artistAppearances.get(artistName);
                                appearances++;
                                artistAppearances.add(artistName, appearances);
                            }
                        }
                    }
                }
            }

        } catch (InvalidKeyException e){}
        System.out.println("La cantidad de apariciones de "+ artistName +  "el día " + date + " es de " + artistAppearances.get(artistName) + " aparicion (es).");

        runtime.gc(); // Correr el garbage collector para liberar memoria no utilizada
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();

        //System.out.println("Memoria utilizada por el método artistAppearances en bytes: " + (memoryAfter - memoryBefore));
    }

    @Override
    public void tempFunction(double tempo1, double tempo2, String first, String last){

        Runtime runtime = Runtime.getRuntime();
        runtime.gc(); // Correr el garbage collector para liberar memoria no utilizada
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();

        MyList<LocalDate> betweenDates = new LinkedList<>();
        LocalDate firstDate = null;
        LocalDate lastDate = null;
        Integer counter = 0;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            firstDate = LocalDate.parse(first, formatter);
            lastDate = LocalDate.parse(last, formatter);
            betweenDates.addFirst(firstDate);
            if (!lastDate.isEqual(firstDate)){
                betweenDates.addLast(lastDate);
            }
        } catch (DateTimeParseException e) {}

        if (firstDate.isAfter(lastDate)) {
            LocalDate temp = firstDate;
            firstDate = lastDate;
            lastDate = temp;
        }

        if(tempo1>=tempo2){
            double temp = tempo1;
            tempo1 = tempo2;
            tempo2 = temp;
        }

        for (int i = 0; i<this.dates.length; i++){
            LocalDate date = LocalDate.parse(this.dates[i]);
            if (isBetween(date, firstDate, lastDate)){
                betweenDates.addLast(date);
            }
        }

        for (int i = 0; i < betweenDates.getSize(); i++) {
            for (int j =0 ; j < abbreviations.length ; j++){

                StringBuilder sb = new StringBuilder();
                sb.append(abbreviations[j].trim());
                sb.append(betweenDates.getValueNode(i).toString().trim());
                String key = sb.toString();
                try { // Si no existe la fecha en el top global, se salta
                    LinkedList<String> top50 = file.getWorld().get(key);
                    for (int k = 0; k < 50; k++) {
                        String songKey = top50.getValueNode(k);
                        Song s = file.getSongs().get(songKey);
                        if (s.getTempo() >= tempo1 && s.getTempo() <= tempo2){
                            counter++;
                        }
                    }
                } catch (NullPointerException | InvalidKeyException | EmptyHashException e) {}
            }
        }

        System.out.println("Cantidad de canciones con un tempo entre " + tempo1 + " y " + tempo2 + " en el rango de fechas seleccionado: " + counter);

        runtime.gc(); // Correr el garbage collector para liberar memoria no utilizada
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();

        //System.out.println("Memoria utilizada por el método tempFunction en bytes: " + (memoryAfter - memoryBefore));
    }

    // Metodo recursivo para mantener el orden O(n) en la funcion top5RepeatedSongs
    public void enlazarNodosRecursivo (ListNode < String > a, ListNode < String > b){
        if (b == null) // Verificar si b o su siguiente nodo son nulos
            return;

        this.dateSongs.addLast(b);
        a.setNext(b);
        enlazarNodosRecursivo(b, b.getNext());
    }

    // Metodo para chequear un rango de fechas
    public boolean isBetween(LocalDate date, LocalDate first, LocalDate last) {
        return !date.isBefore(first) && !date.isAfter(last);
    }

    String[] abbreviations = {"GLB", "ZA", "VN", "VE", "UY", "US", "UA", "TW", "TR", "TH", "SV", "SK", "SG", "SE", "SA", "RO", "PY", "PT", "PL", "PK", "PH", "PE", "PA", "NZ", "NO",
            "NL", "NI", "NG", "MY", "MX", "MA", "LV", "LU", "LT", "KZ", "KR", "JP", "IT", "IS", "IN", "IL", "IE", "ID", "HU", "HN", "HK", "GT", "GR", "GB", "FR", "FI", "ES", "EG", "EE",
            "EC", "DO", "DK", "DE", "CZ", "CR", "CO", "CL", "CH", "CA", "BY", "BR", "BO",
            "BG", "BE", "AU", "AT", "AR", "AE"};

    String[] abbreviations24 =  { "VN", "UY", "UA", "TW", "TR", "TH", "SV", "SK", "SG", "SE", "RO", "PY", "PT", "PL", "PK", "PH", "PE", "PA",
            "NI", "NG", "MY", "MX", "MA", "LV", "KZ", "KR", "JP", "IT","IL", "ID", "HU", "HN", "HK", "GT", "GR", "FR", "FI", "ES", "EG", "EE",
            "DO", "DE", "CZ", "CR","CL", "BY", "BR", "BO", "BE", "AT", "AR", "AE"};

    String[] dates = {
            "2024-05-13", "2024-05-12", "2024-05-11", "2024-05-10", "2024-05-09", "2024-05-08", "2024-05-07", "2024-05-06",
            "2024-05-05", "2024-05-04", "2024-05-03", "2024-05-02", "2024-05-01", "2024-04-30", "2024-04-29", "2024-04-28",
            "2024-04-27", "2024-04-26", "2024-04-25", "2024-04-24", "2024-04-23", "2024-04-22", "2024-04-21", "2024-04-20",
            "2024-04-19", "2024-04-18", "2024-04-17", "2024-04-16", "2024-04-15", "2024-04-14", "2024-04-13", "2024-04-12",
            "2024-04-11", "2024-04-10", "2024-04-09", "2024-04-08", "2024-04-07", "2024-04-06", "2024-04-05", "2024-04-04",
            "2024-04-03", "2024-04-02", "2024-04-01", "2024-03-31", "2024-03-30", "2024-03-29", "2024-03-28", "2024-03-27",
            "2024-03-26", "2024-03-25", "2024-03-24", "2024-03-23", "2024-03-22", "2024-03-21", "2024-03-20", "2024-03-19",
            "2024-03-18", "2024-03-16", "2024-03-14", "2024-03-13", "2024-03-12", "2024-03-11", "2024-03-10", "2024-03-09",
            "2024-03-08", "2024-03-07", "2024-03-06", "2024-03-05", "2024-03-04", "2024-03-03", "2024-03-02", "2024-03-01",
            "2024-02-29", "2024-02-28", "2024-02-27", "2024-02-26", "2024-02-25", "2024-02-24", "2024-02-23", "2024-02-22",
            "2024-02-21", "2024-02-20", "2024-02-19", "2024-02-18", "2024-02-17", "2024-02-16", "2024-02-15", "2024-02-14",
            "2024-02-13", "2024-02-12", "2024-02-11", "2024-02-10", "2024-02-09", "2024-02-08", "2024-02-07", "2024-02-06",
            "2024-02-05", "2024-02-04", "2024-02-03", "2024-02-02", "2024-02-01", "2024-01-31", "2024-01-30", "2024-01-29",
            "2024-01-28", "2024-01-27", "2024-01-26", "2024-01-25", "2024-01-24", "2024-01-23", "2024-01-22", "2024-01-21",
            "2024-01-20", "2024-01-19", "2024-01-18", "2024-01-17", "2024-01-16", "2024-01-15", "2024-01-14", "2024-01-13",
            "2024-01-12", "2024-01-11", "2024-01-10", "2024-01-09", "2024-01-08", "2024-01-07", "2024-01-06", "2024-01-05",
            "2024-01-04", "2024-01-03", "2024-01-02", "2024-01-01", "2023-12-31", "2023-12-30", "2023-12-29", "2023-12-28",
            "2023-12-27", "2023-12-26", "2023-12-25", "2023-12-24", "2023-12-23", "2023-12-22", "2023-12-21", "2023-12-20",
            "2023-12-19", "2023-12-18", "2023-12-17", "2023-12-16", "2023-12-15", "2023-12-14", "2023-12-13", "2023-12-12",
            "2023-12-11", "2023-12-10", "2023-12-09", "2023-12-08", "2023-12-07", "2023-12-06", "2023-12-05", "2023-12-04",
            "2023-12-03", "2023-12-02", "2023-12-01", "2023-11-30", "2023-11-29", "2023-11-28", "2023-11-27", "2023-11-26",
            "2023-11-25", "2023-11-24", "2023-11-23", "2023-11-22", "2023-11-21", "2023-11-20", "2023-11-19", "2023-11-17",
            "2023-11-16", "2023-11-15", "2023-11-14", "2023-11-13", "2023-11-12", "2023-11-11", "2023-11-10", "2023-11-09",
            "2023-11-08", "2023-11-07", "2023-11-06", "2023-11-05", "2023-11-04", "2023-11-03", "2023-11-02", "2023-11-01",
            "2023-10-31", "2023-10-30", "2023-10-29", "2023-10-28", "2023-10-27", "2023-10-26", "2023-10-25", "2023-10-24",
            "2023-10-23", "2023-10-22", "2023-10-21", "2023-10-20", "2023-10-19", "2023-10-18"};
}