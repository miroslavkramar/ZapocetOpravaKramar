/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kramar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;

/**
 *
 * @author ul
 */
public class HurricaneList {

    private List<Hurricane> list = new ArrayList<>();
    private File file;

    public HurricaneList(File file) throws IOException {
        this.file = file;
        loadFromFile(file);
    }

    private void loadFromFile(File file) throws FileNotFoundException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(new Hurricane(line));
            }
        }
    }

    private void writeToFile(File file) throws IOException {
        Collections.sort(list, new CompareByName());
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Hurricane h : list) {
                bw.write(h.getYear() + " ");
                bw.write(h.getMonth() + " ");
                bw.write(h.getPressureMb() + " ");
                bw.write(h.getSpeedKnotsPerHour() + " ");
                bw.write(h.getName());
                bw.write("\n");
            }
        }
    }

    /**
     *
     * @param file
     * @return if false, then function tries to write to source file, which is
     * prohibited
     * @throws IOException
     */
    public boolean sortByNameWriteToFile(File file) throws IOException {
        if (this.file != file) {
            writeToFile(file);
        } else {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String output = "";
        for (Hurricane h : list) {
            output += h.toString() + "\n";
        }
        return output;
    }

    public String getInfoInYearRange(int year1, int year2) {
        String output = "";
        for (Hurricane h : list) {
            if (h.getYear() >= year1 && h.getYear() <= year2) {
                output += h.toString() + "\n";
            }
        }
        if ("".equals(output)) {
            output = "V tomto rozsahu nejsou zadne zaznamy.";
        }
        return output;
    }

    public String getInfoByName(String name) {
        String output = "Pro \"" + name + "\" chybi informace";
        for (Hurricane h : list) {
            if (h.getName().equals(name)) {
                output = String.format("Saffir-Simpsonova kategorie: %s, Rychlost (km/h): %.2f", h.getSaffirSimpsonCategory(), h.getSpeedKilometersPerHour());
                break;
            }
        }
        return output;
    }

    public String sortBySpeed() {
        String output = "";
        Collections.sort(list, new CompareBySpeed());
        for (Hurricane h : list) {
            output += h.toString() + "\n";
        }
        return output;
    }

    public boolean containstHurricane(String name) {
        for (Hurricane h : list) {
            if (h.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public int countHurricanesInYear(int year) {
        int output = 0;
        for (Hurricane h : list) {
            if (h.getYear() == year) {
                output += 1;
            }
        }
        return output;
    }

    public List<Integer> getYearsList() {
        Collections.sort(list, new CompareByYear());

        List<Integer> yearList = new ArrayList<>();

        yearList.add(list.get(0).getYear());
        for (int i = 1; i < list.size(); i++) {
            // if year of hurricane in list is not equal to the last year in yearList, then add this year as a new entry to the yearList
            if (list.get(i).getYear() != yearList.get(yearList.size() - 1)) {
                yearList.add(list.get(i).getYear());
            }
        }
        return yearList;
    }

    public void writeToBinary(File file) throws FileNotFoundException, IOException {
        DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
        List<Integer> yearsList = getYearsList();

        for (int year : yearsList) {
            out.writeInt(year);
            out.writeInt(countHurricanesInYear(year));
            //System.out.println(String.format("Y: %d, C: %d", year, countHurricanesInYear(year)));
        }
    }

//    public void loadFromBinaryTest(File file) throws FileNotFoundException, IOException{
//        DataInputStream in = new DataInputStream(new FileInputStream(file));
//        while(in.available() > 0){
//            System.out.println(in.readInt());
//        }
//    }
}
