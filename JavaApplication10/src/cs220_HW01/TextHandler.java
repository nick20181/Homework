/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs220_HW01;

/**
 *
 * @author Nicholas Bohm <your.name at your.org>
 */
public class TextHandler {
    
    public TextHandler(){
        
    }
    
    public String[] cutString(String before, String target){
        return before.split(target);
    }
    
    public String findNumber(String start, String find){
        String[] secoundCut;
        String[] fristCut;
        int locationOfNumber;
        
        for(int i = 0;i != start.length(); i++){
            if(find.equals(start.charAt(i) + "")){
                locationOfNumber = i;
                
            fristCut = cutString(start, start.charAt(locationOfNumber) + ". ");
            
            for(int y = 0; y != fristCut[1].length(); y++ ){
                if(Character.isDigit(fristCut[1].charAt(y))){
                    locationOfNumber = y;
                }
            }
            
            secoundCut = cutString(fristCut[1], "\n" + fristCut[1].charAt(locationOfNumber) + ". ");
                System.out.println(secoundCut[0]);
            return secoundCut[0];        
            }
        }
       
        return "Could Not Find That String";
    }
}
