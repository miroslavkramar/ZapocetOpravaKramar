/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kramar;

/**
 *
 * @author ul
 */
public class Hurricane {

    private int year;
    private String month;
    private int pressureMb;
    private int speedKnotsPerHour;
    private String name;

    public Hurricane(int year, String month, int pressureMb, int speedKnotsPerHour, String name) {
        this.year = year;
        this.month = month;
        this.pressureMb = pressureMb;
        this.speedKnotsPerHour = speedKnotsPerHour;
        this.name = name;
    }

    public Hurricane(String data) {
        String[] split = data.split("[\\s\\t]+");
        this.year = Integer.parseInt(split[0]);
        this.month = split[1];
        this.pressureMb = Integer.parseInt(split[2]);
        this.speedKnotsPerHour = Integer.parseInt(split[3]);
        this.name = split[4];
    }

    public double getSpeedKilometersPerHour() {
        final double convertRatio = 1.852;
        return speedKnotsPerHour * convertRatio;
    }

    public String getSaffirSimpsonCategory() {
        String output = "nelze zaradit";
        double speed = getSpeedKilometersPerHour();
        
        if (speed < 119){
            output = "0";
        } else if (speed < 154){
            output = "1";
        } else if (speed < 178){
            output = "2";
        } else if (speed < 209){
            output = "3";
        } else if (speed < 252){
            output = "4";
        } else if (speed >= 252){
            output = "5";
        }
        
        return output;
    }

    @Override
    public String toString() {
        return String.format("Rok: %s, Mesic: %s, Tlak (megabary): %s, Rychlost (Uzly za hodinu): %s, Jmeno: %s", String.valueOf(year), month, String.valueOf(pressureMb), String.valueOf(speedKnotsPerHour), name);
    }

    public int getYear() {
        return year;
    }

    public String getName() {
        return name;
    }

    public String getMonth() {
        return month;
    }

    public int getPressureMb() {
        return pressureMb;
    }

    public int getSpeedKnotsPerHour() {
        return speedKnotsPerHour;
    }

}
