import exceptions.EmptyHashException;
import exceptions.EmptyStackException;
import exceptions.InvalidKeyException;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    // Lista de abreviaturas de países válidos
    private static final String[] abbreviations = {"GLB", "ZA", "VN", "VE", "UY", "US", "UA", "TW", "TR", "TH", "SV", "SK", "SG", "SE", "SA", "RO", "PY", "PT", "PL", "PK", "PH", "PE", "PA", "NZ", "NO",
            "NL", "NI", "NG", "MY", "MX", "MA", "LV", "LU", "LT", "KZ", "KR", "JP", "IT", "IS", "IN", "IL", "IE", "ID", "HU", "HN", "HK", "GT", "GR", "GB", "FR", "FI", "ES", "EG", "EE",
            "EC", "DO", "DK", "DE", "CZ", "CR", "CO", "CL", "CH", "CA", "BY", "BR", "BO",
            "BG", "BE", "AU", "AT", "AR", "AE"};

    public static void main(String[] args) throws EmptyHashException, InvalidKeyException, InvalidKeyException.EmptyHeapException, EmptyStackException.InvalidKeyException {

        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        System.out.println();
        System.out.println("Obligatorio Programacion 2 año 2024");
        System.out.println();
        System.out.println("Bienvenido al consultor! ");
        System.out.println();

        // Paths de nuestras computadoras
        //"C:\\Users\\agust\\OneDrive\\Escritorio\\universal_top_spotify_songs.csv"
        //"C:\\Users\\Lu\\Documents\\UM2024\\Programacion II\\universal_top_spotify_songs.csv"

        System.out.println("Ingrese el path del archivo CSV en su computadora (entre comillas si desea): ");
        String csvPath = scanner.nextLine();

        // Eliminar comillas solo si están presentes
        if (csvPath.startsWith("\"") && csvPath.endsWith("\"")) {
            csvPath = csvPath.substring(1, csvPath.length() - 1);
        }
        ObligatoryMethodsImpl methods = new ObligatoryMethodsImpl(csvPath);

        while (!salir) {
            int option = -1;
            boolean validOption = false;

            while (!validOption) {
                System.out.println("Ingrese el numero de consulta que desea realizar: ");
                System.out.println("1. Top 10 canciones en un país y una fecha dada.");
                System.out.println("2. Top 5 canciones que aparecen en más top 50 en un día dado.");
                System.out.println("3. Top 7 artistas que más aparecen en los top 50 para un rango de fechas dado.");
                System.out.println("4. Cantidad de veces que aparece un artista específico en un top 50 en una fecha dada.");
                System.out.println("5. Cantidad de canciones con un tempo en un rango específico para un rango específico de fechas.");
                System.out.println("6. Salir.");

                if (scanner.hasNextInt()) {
                    option = scanner.nextInt();
                    if (option >= 1 && option <= 6) {
                        validOption = true;
                    } else {
                        System.out.println("Opción no válida. Por favor, ingrese un número del 1 al 6.");
                    }
                } else {
                    System.out.println("Entrada no válida. Por favor, ingrese un número del 1 al 6.");
                    scanner.next(); // Consume la entrada no válida
                }
            }

            scanner.nextLine(); // Consumir el salto de línea

            switch (option) {
                case 1:
                    System.out.println();
                    System.out.println("Ha seleccionado la consulta 1.");
                    String country1;
                    do {
                        System.out.println("Ingrese un país (abreviatura): ");
                        country1 = scanner.next();
                        if (!isValidCountry(country1)) {
                            System.out.println("País no válido. Por favor, ingrese una abreviatura válida.");
                        }
                    } while (!isValidCountry(country1));
                    String date1;
                    do {
                        System.out.println("Ingrese una fecha en formato yyyy-MM-DD: ");
                        date1 = scanner.next();
                        if (!isValidDate(date1)) {
                            System.out.println("Formato de fecha incorrecto. Por favor, ingrese en el formato especificado.");
                        }
                    } while (!isValidDate(date1));
                    methods.top10Song(country1, date1);
                    break;
                case 2:
                    System.out.println("Ha seleccionado la consulta 2.");
                    String day2;
                    do {
                        System.out.println("Ingrese un día en formato yyyy-MM-DD: ");
                        day2 = scanner.next();
                        if (!isValidDate(day2)) {
                            System.out.println("Formato de fecha incorrecto. Por favor, ingrese en el formato especificado.");
                        }
                    } while (!isValidDate(day2));
                    methods.top5RepeatedSongs(day2);
                    break;
                case 3:
                    System.out.println("Ha seleccionado la consulta 3.");
                    String first;
                    do {
                        System.out.println("Ingrese un dia en formato yyyy-MM-DD: ");
                        first = scanner.next();
                        if (!isValidDate(first)) {
                            System.out.println("Formato de fecha incorrecto. Por favor, ingrese en el formato especificado.");
                        }
                    } while (!isValidDate(first));
                    String last;
                    do {
                        System.out.println("Ingrese otro día en formato yyyy-MM-DD: ");
                        last = scanner.next();
                        if (!isValidDate(last)) {
                            System.out.println("Formato de fecha incorrecto. Por favor, ingrese en el formato especificado.");
                        }
                    } while (!isValidDate(last));
                    methods.top7Artist(first, last);
                    break;
                case 4:
                    System.out.println("Ha seleccionado la consulta 4.");
                    System.out.println("Ingrese el nombre del artista: ");
                    String artistName = scanner.nextLine();
                    String date4;
                    do {
                        System.out.println("Ingrese un día en formato yyyy-MM-DD: ");
                        date4 = scanner.next();
                        if (!isValidDate(date4)) {
                            System.out.println("Formato de fecha incorrecto. Por favor, ingrese en el formato especificado.");
                        }
                    } while (!isValidDate(date4));

                    methods.artistAppearances(artistName.trim(), date4.trim());
                    break;
                case 5:
                    System.out.println("Ha seleccionado la consulta 5.");
                    double tempo1;
                    do {
                        System.out.println("Ingrese el tempo mínimo: ");
                        String input = scanner.next();
                        tempo1 = parseTempo(input);
                        if (tempo1 < 0) {
                            System.out.println("El tempo no puede ser negativo o tener un formato incorrecto. Por favor, ingrese un valor válido (utilizando coma).");
                        }
                    } while (tempo1 < 0);
                    double tempo2;
                    do {
                        System.out.println("Ingrese el tempo máximo: ");
                        String input = scanner.next();
                        tempo2 = parseTempo(input);
                        if (tempo2 < 0) {
                            System.out.println("El tempo no puede ser negativo o tener un formato incorrecto. Por favor, ingrese un valor válido.");
                        }
                    } while (tempo2 < 0);

                    String date5;
                    do {
                        System.out.println("Ingrese la fecha de inicio en formato yyyy-MM-DD: ");
                        date5 = scanner.next();
                        if (!isValidDate(date5)) {
                            System.out.println("Formato de fecha incorrecto. Por favor, ingrese en el formato especificado.");
                        }
                    } while (!isValidDate(date5));
                    String date6;
                    do {
                        System.out.println("Ingrese la fecha de fin en formato yyyy-MM-DD: ");
                        date6 = scanner.next();
                        if (!isValidDate(date6)) {
                            System.out.println("Formato de fecha incorrecto. Por favor, ingrese en el formato especificado.");
                        }
                    } while (!isValidDate(date6));

                    methods.tempFunction(tempo1, tempo2, date5, date6);
                    break;
                case 6:
                    salir = true;
                    System.out.println("Ha seleccionado salir. ¡Hasta luego!");
                    break;
                default:
                    // Este caso nunca se alcanzará porque hemos validado la entrada
                    break;
            }

            // Preguntar si desea salir o realizar otra consulta
            if (!salir) {
                boolean respuestaValida = false;
                while (!respuestaValida) {
                    System.out.println();
                    System.out.println("¿Desea realizar otra consulta? (si/no): ");
                    String continuar = scanner.next();
                    if (continuar.equalsIgnoreCase("si")) {
                        respuestaValida = true;
                        salir = false;
                    } else if (continuar.equalsIgnoreCase("no")) {
                        respuestaValida = true;
                        salir = true;
                        System.out.println("¡Hasta luego!");
                    } else {
                        System.out.println("Respuesta no válida. Por favor, ingrese 'si' o 'no'.");
                    }
                }
            }
        }
    }

    // Método para verificar si el país ingresado es válido
    private static boolean isValidCountry(String country) {
        return Arrays.asList(abbreviations).contains(country);
    }

    // Método para verificar si la fecha ingresada es válida en el formato especificado
    private static boolean isValidDate(String date) {
        return date.matches("\\d{4}-\\d{2}-\\d{2}");
    }

    // Método para convertir el input de tempo a double, solo aceptando comas o nada
    private static double parseTempo(String input) {
        try {
            if (input.contains(".")) {
                return -1; // Indicador de formato incorrecto
            }
            return Double.parseDouble(input.replace(",", "."));
        } catch (NumberFormatException e) {
            return -1; // Indicador de formato incorrecto
        }
    }
}
