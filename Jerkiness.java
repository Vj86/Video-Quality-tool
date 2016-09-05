import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.opencv.core.Core;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.opencv.core.Core;        
import org.opencv.highgui.VideoCapture;
import org.bytedeco.javacpp.opencv_core.CvMat;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacpp.opencv_core.IplImage;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.OpenCVFrameConverter;
import java.lang.Math;








public class Jerkiness {
		


	
	
	
	
	
	 public double JerkinessValue(double [] motionjerk, float duration,int numframes)
    {       

		 System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		int dim=3;
		 double [] mu= new double[numframes];
		 double [] tau= new double [numframes];
		 
		 
		 System.out.println("Numero frames "+numframes);
		 System.out.println("duration "+duration);
		 
	 double dt=duration/numframes;
	 
	 System.out.println("dt "+dt);
	 
	 
double c=1;
	
	double [] mupar= new double[dim];
	
	mupar[0]=5;
	mupar[1]=0.5;
	mupar[2]=0.25;
	
	
double [] taupar= new double [dim];
	
	taupar[0]=0.12/c;
	taupar[1]=0.05;
	taupar[2]=1.5*c;
	
	
	double dum=0.0;
	int i=0;
	double duma=0.0;
	
	for( i=0;i<motionjerk.length;i++){
		
		mu[i]=Sshape(mupar,motionjerk[i]);
		tau[i]=Sshape(taupar,dt);
		
		dum=dt*tau[i]*mu[i];
		
		if(dum<0.009){
			dum=0.1;
		}
		
		duma+=dum;
		
	
		
		
	}
	
	
	
	//System.out.println(dum);
	//System.out.println(duration);
	
	double jerkiness=0.0;
	jerkiness=(1.0/duration)*duma;
	double min=-8000.0;
	double max=20000.0;
	
	
   double jerk= (jerkiness - min)/(max-min);
	 
	//System.out.println(jerk);
	
	return jerk;
	
	
	
	 }
	
	




static double Sshape(double [] input_vector, double x){
	
	
	
	
	
	double px=0;
	px=input_vector[0];
	double py=input_vector[1];
	double q= input_vector[2];
	
	
	
	
	double b=q*px/py;
	double d=2*(1-py);
	double c=4*q/d;
	double a=px/py*px/py;
	
	   
	
	double value=0.0;
	
	if (x<=px){
				value = Math.pow(a*x, (double)b); 
				
	}
	
	else{	
		
		
		
			 value= d/(1+ Math.exp(-c*(x-px)))+1-d;
	}
	
	//System.out.println(value);
return value;	

	
}

}