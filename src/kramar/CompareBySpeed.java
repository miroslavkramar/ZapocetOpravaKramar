/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kramar;

import java.util.Comparator;

/**
 *
 * @author ul
 */
public class CompareBySpeed implements Comparator<Hurricane>{

    @Override
    public int compare(Hurricane o1, Hurricane o2) {
        if(o1.getSpeedKnotsPerHour() == o2.getSpeedKnotsPerHour()){
            return 0;
        } else if (o1.getSpeedKnotsPerHour() > o2.getSpeedKnotsPerHour()){
            return -1;
        } else {
            return 1;
        }
    }
    
}
