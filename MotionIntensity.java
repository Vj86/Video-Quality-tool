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












import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
 












import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;
 
public class MotionIntensity {
    
	String path;
	
	static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
 
   // static Mat imag = null;
 
    
    
     public double   motion(String ImagePath) {
        
    	 this.path = new String(ImagePath);
    		
    //		File Fp = new File(path);
 
        Mat frame = new Mat();
        Mat outerBox = new Mat();
        Mat diff_frame = null;
        Mat tempon_frame = null;
       // ArrayList<Rect> array = new ArrayList<Rect>();
        
        //FFmpegFrameGrabber  cap = new FFmpegFrameGrabber("C:\\Users\\vittorio\\Desktop\\Videos\\bs1\\bs1_4096.mp4");
        VideoCapture cap = new VideoCapture(path);
        Size sz = new Size(768, 432);
        int i = 0;
       int count=0;
        
       double dMean =0;
       double dMotion =0;
       
       
   
       
       while (cap.read(frame) != false) {
           //if (cap.read(frame) != false) {
    	
               // Imgproc.resize(frame, frame, sz);
            //   imag = frame.clone();
    	   
    	   
                outerBox = new Mat(frame.size(), CvType.CV_8UC1);
                Imgproc.cvtColor(frame, outerBox, Imgproc.COLOR_BGR2GRAY);
                Imgproc.GaussianBlur(outerBox, outerBox, new Size(3, 3), 0);
                count++;
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
                	dMean += (s.val[0]/ ( diff_frame.rows()*diff_frame.cols()))*(s.val[0]/ ( diff_frame.rows()*diff_frame.cols()));
                	
                	
               // 	dMean+=s.val[0];
                	
              
                	
                	
                	
                			 
                	
                //	System.out.println(mat.dump());
                	
                }
              //  System.out.println(dMean);
                
                i = 1;
                count++;
                tempon_frame = outerBox.clone();
               
               dMotion = (double)Math.sqrt(dMean);
                
                
                
              //}
               
          }   
       
   //    System.out.println("number of frames analyzed: "+count);
    //   System.out.println("Motion intensity: "+dMotion);
       
       return dMotion;
      }    

	
 
    
    
	


public float duration(String ImagePath) throws Exception{
	
	
	
	this.path = new String(ImagePath);
	
	File Fp = new File(path);
	if (!Fp.exists())
	{
	   System.out.println("File not found! " + path);
	   return -1;
	}
	else
	{
		
			
			
			 FFmpegFrameGrabber  cap = new FFmpegFrameGrabber(path);
			 
			 cap.start();
			 
			 /** calculate length of the video in seconds for motion detection if necessary
				 */
				long le=cap.getLengthInTime();
				float length=le;
				return length/1000000;
			
			
			
			
		}
		
	
	
	
	
	
}
	


public int frames (String ImagePath) throws Exception{
	
	int frames=0;
	Frame f=null;
this.path = new String(ImagePath);
	
	File Fp = new File(path);
	if (!Fp.exists())
	{
	   System.out.println("File not found! " + path);
	   return -1;
	}
	else
	{
		FFmpegFrameGrabber  cap = new FFmpegFrameGrabber(path);
		
		
		cap.start();
		
		while((f=cap.grab())!=null){
			frames++;
		}
		
	}
	
	
	
	
	return frames;
}
	
	
	
}


