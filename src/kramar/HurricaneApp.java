package kramar;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author ul
 */
public class HurricaneApp {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String FILE_SEPARATOR = System.getProperty("file.separator");

        int menuSelect = -1;

        try {
            HurricaneList hlist = new HurricaneList(new File("data" + FILE_SEPARATOR + "HurricaneData.txt"));
            //System.out.println(hlist.toString());

            while (menuSelect != 0) {
                try {
                    printMenu();
                    menuSelect = sc.nextInt();
                    sc.nextLine();
                    switch (menuSelect) {
                        // exit the app
                        case 0:
                            System.out.println("Konec.");
                            break;

                        // get info in range of years
                        case 1:
                            System.out.println("Zadej prosim rozsah roku, v jakem chces vypsat data o hurikanech (napr.: 2001 2011): ");
                            System.out.println(hlist.getInfoInYearRange(sc.nextInt(), sc.nextInt()));
                            break;

                        // get cathegory, filter by name
                        case 2:
                            getByName(hlist);
                            break;

                        // sort by speed
                        case 3:
                            System.out.println(hlist.sortBySpeed());
                            break;

                        // write to text file
                        case 4:
                            try {
                            hlist.sortByNameWriteToFile(new File("data" + FILE_SEPARATOR + "HurricaneDataSortedByName.txt"));
                                System.out.println("Uspesne zapsano do textoveho souboru");
                        } catch (IOException e) {
                            System.out.println("Problem se zapisem do textoveho souboru.");
                        }
                        break;

                        // write to binary file
                        case 5:
                            try {
                            hlist.writeToBinary(new File("data" + FILE_SEPARATOR + "HurricaneDataBin.dat"));
                            System.out.println("Uspesne zapsano do binarniho souboru");
//                            hlist.loadFromBinaryTest(new File("data" + FILE_SEPARATOR + "HurricaneDataBin.dat"));
                        } catch (IOException e) {
                            System.out.println("Problem se zapisem do binarniho souboru.");
                        }
                        break;
                        default:
                            System.out.println("Prosim zadavej pouze uvedena cisla.");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Zadej prosim vstup ve spravnem formatu.");
                    sc.nextLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Nepodarilo se nacist data z databaze.");
        }
    }

    static void printMenu() {
        System.out.println("\nDATABAZE HURIKANU");
        System.out.println("Zadejte");
        System.out.println(" 0 pro ukonceni programu");
        System.out.println(" 1 pro vypsani vsech hurikanu v rozmezi roku");
        System.out.println(" 2 pro vypsani Saffir-Simpsonovy skaly a rychlosti konkretniho hurikanu");
        System.out.println(" 3 pro vypsani vsech hurikanu setridenych podle rychlosti");
        System.out.println(" 4 pro ulozeni databaze do textoveho souboru");
        System.out.println(" 5 pro ulozeni databaze do binarniho souboru");
    }

    static void getByName(HurricaneList hlist) {
        System.out.println("Zadej prosim jmeno hurikanu (napr.: Erin): ");
        String name = sc.nextLine();
        while (!hlist.containstHurricane(name) && !"0".equals(name)) {
            System.out.println("Jmeno nenalezeno.");
            System.out.println("Zadej jmeno znova (Pokud si to neprejes, zadej 0): ");
            name = sc.nextLine();
        }
        if (!"0".equals(name)) {
            System.out.println(hlist.getInfoByName(name));
        }
    }
}
