import java.awt.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.bytedeco.javacv.FrameGrabber.Exception;
import org.opencv.core.Core;




public class MainJerkiness {

	
	
	
//	static String path;
	
	public static void main(String[] args) throws Exception, IOException  {
		// TODO Auto-generated method stub
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		// (64  128  256 384  512  640  768  1024 1556 2048  3072  4096 5120);
		String path = JOptionPane.showInputDialog("Digit the directory");
	
		
	/*	File folder = new File("C:\\Users\\vittorio\\Desktop\\Videos2\\bs1_01_09");
	   File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
		    if (file.isFile()) {
		        path="C:\\Users\\vittorio\\Desktop\\Videos2\\bs1_01_09\\"+file.getName();
		  
	*/
		
		// (64  128  256 384  512  640  768  1024 1556 2048  3072  4096 5120);
		
		
		
		// 64 640 768 1024 1556 2048 3072 4096 5120
		//bs1 mc1 pa1  pr1 rb1 rh1 sf1 sh1 st1 tr1
		
		
		
	//path="C:\\Users\\vittorio\\Desktop\\Videos\\rb1";
		
		
		   
		
		
	
		
		
			
		
		
		
		
		
		MotionIntensity Mi = new MotionIntensity();
		
		/**
		 * duration is the duration of the video in seconds
		 * motion is the intensity of the motion
		 * motionjerk is the array with the value of motion between frames i and i+1
		 */
		float duration = Mi.duration(path);
    double motion=	 Mi.motion(path);
    
    //System.out.println(" MOTION \n"+motion);
		
		 int numframes = Mi.frames(path);
		 double [] motionjerk=new double [numframes];
		 
		 MotionJerk MJ = new MotionJerk();
		 
		motionjerk = MJ.motionjerkiness(path,numframes);
		 
		System.out.println(duration);
		
		
	
		/** 
		 * print the array with the values of motion between the frame i and the frame i+1
		 * 
		 */
	/*	for(int z=0;z<motion.length;z++){
			
			System.out.println(motionjerk[z]);
		}
		*/
		
		Jerkiness JE=new Jerkiness();
		
	    double totaljerkiness= JE.JerkinessValue(motionjerk, duration,numframes);
	    
	    
	    
	   System.out.println("\n JERKINESS "+totaljerkiness);
		
	  PrintWriter out = new PrintWriter(new FileWriter("C:\\Users\\vittorio\\Desktop\\JAX.txt",true));
		
		out.println(path+" "+motion +"    "+totaljerkiness);
		out.close();
		
	    
		JOptionPane.showMessageDialog(null,"the level of jerkiness is: "+ totaljerkiness );
		
		System.out.println("motion intensity is: "+motion);
		System.out.println("the duration of the video is: "+duration+" seconds");
		System.out.println("the vector motion jerkiness is:  "+motionjerk);
/*
	 Jerkiness J=new Jerkiness();
	 double JerkinessValue=J.Jerkiness(motion, duration);
		*/
		System.out.println("the value of the jerkiness is: "+totaljerkiness);
		
		
		System.exit(0);
		      
		      } 
		      
		      
		      }	
		      
	    
		      
//	}}	      

	


