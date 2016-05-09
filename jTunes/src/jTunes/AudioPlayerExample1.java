package net.codejava.sound;
 
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
 
/**
 * This is an example program that demonstrates how to play back an audio file
 * using the Clip in Java Sound API.
 * @author www.codejava.net
 *
 */

public class AudioPlayerExample1 implements LineListener {
    
    /**
     * this flag indicates whether the playback completes or not.
     */
    private boolean playCompleted;
    
    private Clip audioClip;
    static Scanner reader = new Scanner(System.in);
   
    static AudioPlayerExample1 player = new AudioPlayerExample1();
     
    /**
     * Play a given audio file.
     * @param audioFilePath Path of the audio file.
     */
    
    public void load(String audioFilePath){
    	File audioFile = new File(audioFilePath);
    	
    	try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
 
            AudioFormat format = audioStream.getFormat();
 
            DataLine.Info info = new DataLine.Info(Clip.class, format);
 
            audioClip = (Clip) AudioSystem.getLine(info);
 
            audioClip.addLineListener(this);
 
            audioClip.open(audioStream);
            
            System.out.println("Song Loaded");
    	
    } catch (UnsupportedAudioFileException ex) {
        System.out.println("The specified audio file is not supported.");
        ex.printStackTrace();
    } catch (LineUnavailableException ex) {
        System.out.println("Audio line for playing back is unavailable.");
        ex.printStackTrace();
    } catch (IOException ex) {
        System.out.println("Error playing the audio file.");
        ex.printStackTrace();
    }
    	
    }
   // public void play(String audioFilePath) 
    public void play(){
        //File audioFile = new File(audioFilePath);
    	
    	if(audioClip.isOpen()  && !(audioClip.isActive())){
    		
	        audioClip.start();
	        System.out.println("Song Playing");
	        
	        while (!playCompleted) {
	            // wait for the playback completes
	            try {
	                Thread.sleep(1000);
	            } catch (InterruptedException ex) {
	                ex.printStackTrace();
	            }
	        }
	        
	        audioClip.close();
	        
    	}
    
   }
    
   public void end(){
	   audioClip.close();
	   System.out.println("Song Ended");
   }
    
   public void pause(){
	   if(audioClip.isActive()){
		   System.out.println("Paused");
		   audioClip.stop();
	   }
   }
   
  /* public void resume(){
	   if(audioClip.isOpen() && !(audioClip.isActive()) ){
		   audioClip.start();
	   	   System.out.println("Playing");
	   }
   }
   */
    
    /**
     * Listens to the START and STOP events of the audio line.
     */
    @Override
    public void update(LineEvent event) {
        LineEvent.Type type = event.getType();
         
        if (type == LineEvent.Type.START) {
            System.out.println("Playback started.");
          //  menu();
            
            
             
        } 
        else if (type == LineEvent.Type.STOP) {
           // menu();
        }  
        else if (type == LineEvent.Type.CLOSE) {
            	playCompleted = true;
                System.out.println("Playback completed.");
        }
        
 
    }
    
    public static void menu(){
    	
    	int n = 0;
    	
    	while(true){  
	    	System.out.print("Play - 1\nPause - 2\nExit -3\n");
	    	
	    	n = reader.nextInt();
	  	
	    	switch (n){
	    	case 1: player.play();
	    			break;
	    	case 2: player.pause();
	    			break;
	    	case 3: player.end();
	    			break;
	    	default: break;
    	}
  }
    }
    
    public static void main(String[] args) {
    	
    	
    	
        String audioFilePath = "/Users/Alex/Spring_2016/Software Design Lab/audiofiles/test2.wav";
        player.load(audioFilePath);
        //player.play();
        
        menu();
 /*       
        while(flag)
        {
        	System.out.print("Load - 0\nPlay - 1\nPause - 2\nExit -3\n");
        	n = reader.nextInt();
        	
        	switch (n){
        	case 0: player.load(audioFilePath);
        			break;
        	case 1: player.play();
        			break;
        	case 2: player.pause();
        			break;
        	case 3: player.end();
        			flag = false;
        			break;
        	default: break;
        	}
        	System.out.println(flag);
        	
        }
 */
        /*
        try {
            Thread.sleep(1000);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        */
        player.pause();
      
        
        
 //       player.resume();
        
        
    }
 
}
    
    
    
            
             
 