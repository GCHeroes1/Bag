import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadCSV {
    public static ArrayList<Patient> Scanner(File myFile) throws FileNotFoundException {

        //File myDirectory = new File("/home/rajesh/Documents/Programming Languages/Java/JavaCoursework2/src/patients100.csv");
        Scanner PatientCSV = new Scanner(new FileReader(myFile)).useDelimiter(",");
        ArrayList<Patient> patientArrayList = new ArrayList<>();
        while (PatientCSV.hasNextLine()){
            String patientHolder = PatientCSV.nextLine();
            String[] patientStringArray = patientHolder.split(",", -1);
            Patient patient = new Patient(patientStringArray[0], patientStringArray[1], patientStringArray[2], patientStringArray[3],
                    patientStringArray[4], patientStringArray[5], patientStringArray[6], patientStringArray[7],
                    patientStringArray[8], patientStringArray[9], patientStringArray[10], patientStringArray[11],
                    patientStringArray[12], patientStringArray[13], patientStringArray[14], patientStringArray[15],
                    patientStringArray[16], patientStringArray[17], patientStringArray[18], patientStringArray[19]);
            patientArrayList.add(patient);
        }
        return patientArrayList;
    }

    public static File getFile() throws FileNotFoundException{
        Scanner fileReader = new Scanner(System.in);
        System.out.println("Enter the csv file you would like to scan: ");
        String file = fileReader.nextLine().trim();
        File myFile = new File(file);
        return myFile;
    }


    public static void main(String[] args){
        try {
            new ReadCSV().Scanner(ReadCSV.getFile());
        } catch (FileNotFoundException e) {
            System.out.println("====> File not found...");
        }
    }
}

/* forward pass creates a cost matrix, when you encoutner a cost matrix, all the wquations serve to tell you how to fill
in a cost matrix, this cost matrix is used to compare the left and right images, the approach is to take one line
at a time, so really, these lines are going to be sets of numbers (pixel intensity 0-255), the forward pass will
build a two dimensional array, the top line is the line j, and the bottom line is i, cost matrix lets you compare the
images and decide whether or not they can be matched, is pixels x and y match, if x = 2, y = 8 (indexes), disparity is 6,
the algorithm in the paper helps you fill the cost matrix,
 */