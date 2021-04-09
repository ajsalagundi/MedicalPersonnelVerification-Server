import nu.pattern.OpenCV;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;
// https://www.baeldung.com/java-opencv (used this site to get the code)

public class jFaceDetection {

    public static Mat loadImage(String imagePath) {
        Imgcodecs imageCodecs = new Imgcodecs();
        return imageCodecs.imread(imagePath);
    }

    public static void saveImage(Mat imageMatrix, String targetPath) {
        Imgcodecs imgcodecs = new Imgcodecs();
        imgcodecs.imwrite(targetPath, imageMatrix);
    }

    public static void main(String[] args){
        //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        OpenCV.loadShared();

      /*  //String imgFile = "images/messi-ronaldo-fb.jpg";
        String imgFile = "images/multiplefaces.jpg";
        Mat src = Imgcodecs.imread(imgFile);

        String xmlFile = "xml/lbpcascade_frontalface.xml";
        CascadeClassifier cc = new CascadeClassifier(xmlFile);

        MatOfRect faceDetection = new MatOfRect();
        cc.detectMultiScale(src, faceDetection);
        System.out.println(String.format("Detected faces: %d", faceDetection.toArray().length));

        for(Rect rect: faceDetection.toArray()){
            Imgproc.rectangle(src, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0,0,255), 3);
        }

       // Imgcodecs.imwrite("images/messi-ronaldo-fb_out.jpg", src);
        Imgcodecs.imwrite("images/multiplefaces_out.jpg", src);
        System.out.println("Image Detection Finished"); */


        Mat loadedImage = loadImage("images/girlface.jpg");
        MatOfRect facesDetected = new MatOfRect();
        CascadeClassifier cascadeClassifier = new CascadeClassifier();
        int minFaceSize = Math.round(loadedImage.rows() * 0.1f);
        cascadeClassifier.load("./xml/haarcascade_frontalface_default.xml");
        cascadeClassifier.detectMultiScale(loadedImage,
                facesDetected,
                1.1,
                3,
                Objdetect.CASCADE_SCALE_IMAGE,
                new Size(minFaceSize, minFaceSize),
                new Size()
        );
        Rect[] facesArray = facesDetected.toArray();
        for(Rect face : facesArray) {
            Imgproc.rectangle(loadedImage, face.tl(), face.br(), new Scalar(0, 0, 255), 3);
        }
        saveImage(loadedImage, "images/girlface_out.jpg");

    }

}
