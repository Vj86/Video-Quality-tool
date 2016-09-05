import java.io.File;

import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.opencv.core.Core;        
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.bytedeco.javacpp.opencv_core.CvMat;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacpp.opencv_core.IplImage;

import java.lang.String;

import org.opencv.core.Mat;

import java.io.*;

import org.opencv.core.CvType;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;


public class MotionJerk {
	
	

	String path;
	  
	
	public double [] motionjerkiness(String ImagePath, int numframes) {
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
   	 this.path = new String(ImagePath);
   	
   	 double [] motion= new double [numframes];
       Mat frame = new Mat();
       Mat outerBox = new Mat();
       Mat diff_frame = null;
       Mat tempon_frame = null;
      // ArrayList<Rect> array = new ArrayList<Rect>();
       double element;
       //FFmpegFrameGrabber  cap = new FFmpegFrameGrabber("C:\\Users\\vittorio\\Desktop\\Videos\\bs1\\bs1_4096.mp4");
       VideoCapture cap = new VideoCapture(path);
       Size sz = new Size(768, 432);
       int i = 0;
     
       
   
  
    
     
      int j=0;
  
      
      while (cap.read(frame) != false && j<motion.length) {
          //if (cap.read(frame) != false) {
   	
              // Imgproc.resize(frame, frame, sz);
           //   imag = frame.clone();
   	   
   	   
               outerBox = new Mat(frame.size(), CvType.CV_8UC1);
               Imgproc.cvtColor(frame, outerBox, Imgproc.COLOR_BGR2GRAY);
               Imgproc.GaussianBlur(outerBox, outerBox, new Size(3, 3), 0);
              
             //  System.out.println( count );
               
               if (i == 0) {
                
                   diff_frame = new Mat(outerBox.size(), CvType.CV_8UC1);
                   tempon_frame = new Mat(outerBox.size(), CvType.CV_8UC1);
                   diff_frame = outerBox.clone();
                   
                   
                   
               }

               
               if (i == 1) {
            	   
            	   
            	   
               				diff_frame = outerBox.clone();
               				
               	Core.absdiff(tempon_frame, outerBox, diff_frame);
               	 Mat mat = Mat.eye( sz, CvType.CV_8UC1 );         	
               	mat=diff_frame;
               	
               	
               	Scalar s=Core.sumElems(mat);
               	
               	
               	
               	element = (s.val[0]/ ( diff_frame.rows()*diff_frame.cols()))*(s.val[0]/ ( diff_frame.rows()*diff_frame.cols()));
               	
               	element=(double)Math.sqrt(element);
               	
               	motion[j]=element;
               	
               	j++;
               	
               	}
              // 	dMean+=s.val[0];
               	
             
               	
               	
               	
               			 
               	
               //	System.out.println(mat.dump());
               	
               
             //  System.out.println(dMean);
               
               i = 1;
  
               tempon_frame = outerBox.clone();
              
      }
      return motion;
	    
	}        
               
             //}
              
           
      
  //    System.out.println("number of frames analyzed: "+count);
   //   System.out.println("Motion intensity: "+dMotion);
     
      
      
     
     }    
	 
	





