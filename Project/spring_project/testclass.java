package spring_project;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
//imported packages 
import javax.swing.JFrame;

public class testclass   {

    static String player1=null,player2=null , level=null;
    
    
	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
		 
	 
	 
		intro k=new intro( );
         while( k.isDisplayable()) {
			
		}
		  
         
         //when the compiler reaches this line the players names are initialixed eith teh given names 
         gui o;
         
         //player1 always has non-null value 
         if(  player2!=null) // human vs human 
		 o=new gui(player1,player2);
          
         else // human vs ai
        	 o=new gui(player1);
        	 
		
	  
        
			
		
		
	}

}
