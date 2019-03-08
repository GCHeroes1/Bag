import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Model{

    private static ArrayList<StringBuilder> PatientList;

    /*{
        try {
            PatientList = JSONFormatter.Formatter();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }*/

    private static ArrayList<Patient> readFile() throws FileNotFoundException {
        ArrayList<Patient> PatientArrayList = ReadCSV.Scanner(ReadCSV.getFile());
        return PatientArrayList;
    }

    public static StringBuilder getJSONPatient(int i){
        return PatientList.get(i);
    }

    public static Patient getPatient(int i) throws FileNotFoundException {
        return readFile().get(i);
    }

    public static StringBuilder CSVToJSON(File myFile) throws FileNotFoundException {
        ArrayList<Patient> patients = ReadCSV.Scanner(myFile);
        return null;
    }

}
