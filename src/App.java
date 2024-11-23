import java.util.LinkedList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        LinkedList<Film> listFilm = new LinkedList<Film>();
        Scanner scanner = new Scanner(System.in);

        generateFilm(listFilm);

        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\nMenu:");
            System.out.println("1. Tambahkan film");
            System.out.println("2. Tampilkan film");
            System.out.println("0. Keluar");
            System.out.print("Masukkan pilihan Anda : ");
            int pilihan = scanner.nextInt();

            scanner.nextLine();

            if (pilihan == 1) {
                tambahFilm(scanner, listFilm);
            } else if (pilihan == 2) {
                System.out.println("Metode sorting:");
                System.out.println("1. Bubble sort");
                System.out.println("2. Insertion");
                System.out.println("3. Selection");
                System.out.print("Masukkan pilihan Anda : ");
                int method = scanner.nextInt();

                if (!(method == 1 || method == 2 || method == 3))
                    System.out.println("Pilihan tidak valid!");

                System.out.println("Field sorting:");
                System.out.println("1. Judul");
                System.out.println("2. Tahun Rilis");
                System.out.println("3. Rating");
                System.out.print("Masukkan pilihan Anda : ");
                int field = scanner.nextInt();

                if (!(field == 1 || field == 2))
                    System.out.println("Pilihan tidak valid!");

                System.out.println("Urutan sorting:");
                System.out.println("1. Ascending");
                System.out.println("2. Descending");
                System.out.print("Masukkan pilihan Anda : ");
                int order = scanner.nextInt();

                if (!(order == 1 || order == 2))
                    System.out.println("Pilihan tidak valid!");

                scanner.nextLine();

                if (method == 1) {
                    bubbleSort(listFilm, field, order);
                } else if (method == 2) {
                    insertion(listFilm, field, order);
                } else if (method == 3) {
                    selection(listFilm, field, order);
                }

            } else if (pilihan == 0) {
                isRunning = false;
            } else {
                System.out.println("Pilihan tidak valid!");
            }
        }

        scanner.close();
    }

    public static void generateFilm(LinkedList<Film> listFilm) {
        listFilm.add(new Film("The Shawshank Redemption", 1994, 9.3));
        listFilm.add(new Film("Inception", 2010, 8.8));
        listFilm.add(new Film("The Dark Knight", 2008, 9.0));
        listFilm.add(new Film("Forrest Gump", 1994, 8.8));
        listFilm.add(new Film("The Godfather", 1972, 9.2));
        listFilm.add(new Film("Pulp Fiction", 1994, 8.9));
        listFilm.add(new Film("Fight Club", 1999, 8.8));
        listFilm.add(new Film("The Matrix", 1999, 8.7));
        listFilm.add(new Film("Se7en", 1995, 8.6));
        listFilm.add(new Film("Interstellar", 2014, 8.6));
        listFilm.add(new Film("Parasite", 2019, 8.5));
        listFilm.add(new Film("The Social Network", 2010, 7.8));
        listFilm.add(new Film("The Wolf of Wall Street", 2013, 8.2));
        listFilm.add(new Film("Django Unchained", 2012, 8.4));
        listFilm.add(new Film("Gladiator", 2000, 8.5));
    }

    public static void tambahFilm(Scanner scanner, LinkedList<Film> listFilm) {
        System.out.println("\nForm tambah film:");
        System.out.print("Judul         : ");
        String judul = scanner.nextLine();
        System.out.print("Tahun Rilis   : ");
        int tahunRilis = scanner.nextInt();
        System.out.print("Rating        : ");
        double rating = scanner.nextDouble();
        listFilm.add(new Film(judul, tahunRilis, rating));
    }

    public static void bubbleSort(LinkedList<Film> listFilm, int field, int order) {
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < listFilm.size() - 1; i++) {
                if (compareFilms(listFilm.get(i), listFilm.get(i + 1), field, order)) {
                    Film temp = listFilm.get(i);
                    listFilm.set(i, listFilm.get(i + 1));
                    listFilm.set(i + 1, temp);
                    swapped = true;
                }
            }
        } while (swapped);

        printResult(listFilm);
    }

    public static void insertion(LinkedList<Film> listFilm, int field, int order) {
        for (int i = 1; i < listFilm.size(); i++) {
            Film key = listFilm.get(i);
            int j = i - 1;

            while (j >= 0 && compareFilms(listFilm.get(j), key, field, order)) {
                listFilm.set(j + 1, listFilm.get(j));
                j--;
            }
            listFilm.set(j + 1, key);
        }

        printResult(listFilm);
    }

    public static void selection(LinkedList<Film> listFilm, int field, int order) {
        for (int i = 0; i < listFilm.size() - 1; i++) {
            int minOrMaxIndex = i;
            for (int j = i + 1; j < listFilm.size(); j++) {
                if (compareFilms(listFilm.get(minOrMaxIndex), listFilm.get(j), field, order)) {
                    minOrMaxIndex = j;
                }
            }

            Film temp = listFilm.get(minOrMaxIndex);
            listFilm.set(minOrMaxIndex, listFilm.get(i));
            listFilm.set(i, temp);
        }

        printResult(listFilm);
    }

    public static boolean compareFilms(Film film1, Film film2, int field, int order) {
        int comparison = 0;

        if (field == 1) {
            comparison = film1.judul.compareToIgnoreCase(film2.judul);
        } else if (field == 2) {
            comparison = Integer.compare(film1.tahunRilis, film2.tahunRilis);
        } else if (field == 3) {
            comparison = Double.compare(film1.rating, film2.rating);
        }

        if (order == 2) {
            comparison = -comparison;
        }

        return comparison > 0;
    }

    public static void printResult(LinkedList<Film> listFilm) {
        System.out.println("\nDaftar Film yang Telah Diurutkan:");
        for (Film film : listFilm) {
            System.out.printf("Judul: %s, Tahun Rilis: %d, Rating: %.1f\n",
                    film.judul, film.tahunRilis, film.rating);
        }
    }
}
