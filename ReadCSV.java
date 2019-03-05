import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadCSV {
    public void Scanner() throws FileNotFoundException {
        File myDirectory = new File("/home/rajesh/Documents/Programming Languages/Java/JavaCoursework2/src/COMP0004Data-master/patients100.csv");
        Scanner PatientCSV = new Scanner(new FileReader(myDirectory)).useDelimiter(",");
        ArrayList<Patient> patientArrayList = new ArrayList<>();
        ArrayList<String> arrayList = new ArrayList<>();
        String patient = PatientCSV.nextLine();
        String[] patient2 = patient.split(",", -1);
        patient = patient2.toString();
        System.out.println(patient);
    }

    public static void main(String[] args) throws FileNotFoundException {
        new ReadCSV().Scanner();
    }
}
