import java.io.FileNotFoundException;
import java.util.ArrayList;

public class JSONFormatter {
    public static ArrayList<StringBuilder> Formatter() throws FileNotFoundException {
        ArrayList<StringBuilder> PatientList = new ArrayList<>();
        ArrayList<Patient> PatientArrayList = ReadCSV.Scanner();
        for (int i = 1; i < PatientArrayList.size(); i++) {
            StringBuilder patient = new StringBuilder();
            patient.append("{\"");
            patient.append(PatientArrayList.get(0).getID());
            patient.append("\": \"");
            patient.append(PatientArrayList.get(i).getID());
            patient.append("\" \n");
            patient.append("\"");
            patient.append(PatientArrayList.get(0).getBirthdate());
            patient.append("\": \"");
            patient.append(PatientArrayList.get(i).getBirthdate());
            patient.append("\" \n");
            patient.append("\"");
            patient.append(PatientArrayList.get(0).getDeathdate());
            patient.append("\": \"");
            patient.append(PatientArrayList.get(i).getDeathdate());
            patient.append("\" \n");
            patient.append("\"");
            patient.append(PatientArrayList.get(0).getSSN());
            patient.append("\": \"");
            patient.append(PatientArrayList.get(i).getSSN());
            patient.append("\" \n");
            patient.append("\"");
            patient.append(PatientArrayList.get(0).getDrivers());
            patient.append("\": \"");
            patient.append(PatientArrayList.get(i).getDrivers());
            patient.append("\" \n");
            patient.append("\"");
            patient.append(PatientArrayList.get(0).getPassport());
            patient.append("\": \"");
            patient.append(PatientArrayList.get(i).getPassport());
            patient.append("\" \n");
            patient.append("\"");
            patient.append(PatientArrayList.get(0).getPrefix());
            patient.append("\": \"");
            patient.append(PatientArrayList.get(i).getPrefix());
            patient.append("\" \n");
            patient.append("\"");
            patient.append(PatientArrayList.get(0).getFirst());
            patient.append("\": \"");
            patient.append(PatientArrayList.get(i).getFirst());
            patient.append("\" \n");
            patient.append("\"");
            patient.append(PatientArrayList.get(0).getLast());
            patient.append("\": \"");
            patient.append(PatientArrayList.get(i).getLast());
            patient.append("\" \n");
            patient.append("\"");
            patient.append(PatientArrayList.get(0).getSuffix());
            patient.append("\": \"");
            patient.append(PatientArrayList.get(i).getSuffix());
            patient.append("\" \n");
            patient.append("\"");
            patient.append(PatientArrayList.get(0).getMaiden());
            patient.append("\": \"");
            patient.append(PatientArrayList.get(i).getMaiden());
            patient.append("\" \n");
            patient.append("\"");
            patient.append(PatientArrayList.get(0).getMartial());
            patient.append("\": \"");
            patient.append(PatientArrayList.get(i).getMartial());
            patient.append("\" \n");
            patient.append("\"");
            patient.append(PatientArrayList.get(0).getRace());
            patient.append("\": \"");
            patient.append(PatientArrayList.get(i).getRace());
            patient.append("\" \n");
            patient.append("\"");
            patient.append(PatientArrayList.get(0).getEthnicity());
            patient.append("\": \"");
            patient.append(PatientArrayList.get(i).getEthnicity());
            patient.append("\" \n");
            patient.append("\"");
            patient.append(PatientArrayList.get(0).getGender());
            patient.append("\": \"");
            patient.append(PatientArrayList.get(i).getGender());
            patient.append("\" \n");
            patient.append("\"");
            patient.append(PatientArrayList.get(0).getBirthplace());
            patient.append("\": \"");
            patient.append(PatientArrayList.get(i).getBirthplace());
            patient.append("\" \n");
            patient.append("\"");
            patient.append(PatientArrayList.get(0).getAddress());
            patient.append("\": \"");
            patient.append(PatientArrayList.get(i).getAddress());
            patient.append("\" \n");
            patient.append("\"");
            patient.append(PatientArrayList.get(0).getCity());
            patient.append("\": \"");
            patient.append(PatientArrayList.get(i).getCity());
            patient.append("\" \n");
            patient.append("\"");
            patient.append(PatientArrayList.get(0).getState());
            patient.append("\": \"");
            patient.append(PatientArrayList.get(i).getState());
            patient.append("\" \n");
            patient.append("\"");
            patient.append(PatientArrayList.get(0).getZip());
            patient.append("\": \"");
            patient.append(PatientArrayList.get(i).getZip());
            patient.append("\"");
            PatientList.add(patient);
            patient.append("}");
        }
        System.out.println(PatientList.get(5));
        return PatientList;
    }

    public static void main(String[] args) {
        try {
            new JSONFormatter().Formatter();
        } catch (FileNotFoundException e) {
            System.out.println("====> File not found...");
        }
    }
}
