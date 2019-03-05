import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadCSV {
    public ArrayList<Patient> Scanner() throws FileNotFoundException {
        File myDirectory = new File("/home/rajesh/Documents/Programming Languages/Java/JavaCoursework2/src/COMP0004Data-master/patients100.csv");
        Scanner PatientCSV = new Scanner(new FileReader(myDirectory)).useDelimiter(",");
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
        System.out.println(patientArrayList.get(105).getZip());
        System.out.println(patientArrayList.size());
        return patientArrayList;
    }

    public static void main(String[] args){
        try {
            new ReadCSV().Scanner();
        } catch (FileNotFoundException e) {
            System.out.println("====> File not found...");
        }
    }
}
