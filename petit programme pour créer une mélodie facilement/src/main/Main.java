package main;

import java.util.HashMap;
public class Main {
    public static void main(String[] args) {

    	HashMap<Integer, String> liste = new HashMap<>();
    	
    	liste.put(0, "1500"); //intervalle à définir
    	
    	liste.put(1, "C5"); //do en haut1
    	liste.put(2, "D5");
    	liste.put(3, "E5");
    	liste.put(4, "F5");
    	liste.put(5, "G5");
    	liste.put(6, "A5");
    	liste.put(7, "B5");
    	
    	liste.put(11, "C6"); //do en haut2
    	liste.put(22, "D6");
    	liste.put(33, "E6");
    	liste.put(44, "F6");
    	liste.put(55, "G6");
    	liste.put(66, "A6");
    	liste.put(77, "B6");
    	
    	liste.put(111, "C7"); //do en haut3
    	
    	liste.put(50, "G4"); //so en bas1
    	liste.put(60, "A4");
    	liste.put(70, "B4");
    	
    	liste.put(250, "250"); //les intervalles
    	liste.put(500, "500");
    	liste.put(1000, "1000");
    	liste.put(125, "125");
    	
    	//pour mélodie3.txt
    	int[] melodie3= {500,5,500,3,1000,1,  250,2,250,3,250,4,250,2,1000,70,
    			500,1,250,1,250,1,500,3,500,3,1000,5,
    		
    			500,5,500,3,1000, 1, 250, 2,250, 3,250, 4,250, 2,1000,70, 
    			500, 60,250, 60,250,60,500, 70,500,70,1000,1,
    			
    			500,6,500,6,250,6,250,6,250,6,250,6, 500,5,250,5,250,5,500,5,500,5,
    			500,4,250,4,250,4,500,4,500,4,
    			250,3,250,3,250,4,250,4,1000,5,
    			
    			500,5,500,3,1000,1, 250,2,250,3,250,4,250,2,1000,70,
    			500,1,250,1,250,1,500,3,500,3,1000,5,
    			
    			500,5,500,3,1000,1, 250,2,250,3,250,4,250,2,1000,70,
    			500,60,250,60,250,60,500,70,500,70,250,1
    			};
    	
    	//changer le nom du tableau melodie"x"
    	
    	for(int i=0; i<melodie3.length;i++) {
    		if(i%2==0) {
    			System.out.println(liste.get(melodie3[i]) + " " +liste.get(melodie3[i+1]));
    		}

    	}
    	
    }
}
