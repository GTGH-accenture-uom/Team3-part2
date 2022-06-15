package gr.team3.vaccinationsystem;

import gr.team3.vaccinationsystem.model.*;

import javax.print.attribute.IntegerSyntax;
import java.io.*;
import java.sql.Array;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntUnaryOperator;

/*
This class represents a text file and is responsible for creating and writing in it.
 */
public class FileParser {

        public void writeAll(List<Object> objects) throws IOException {
            if (!objects.get(0).toString().equals("[]")) {
                String class_type = "";
                class_type = objects.get(0).toString()
                        .substring(0, objects.get(0).toString().indexOf('{'));
                FileOutputStream fileOutputStream
                        = new FileOutputStream("src/main/resources/" + class_type + ".txt");
                ObjectOutputStream objectOutputStream
                        = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(objects);
                objectOutputStream.flush();
                objectOutputStream.close();
            }
    }

    public List<Insured> readInsured() throws IOException, ClassNotFoundException {
            FileInputStream fileInputStream
                    = new FileInputStream("src/main/resources/Insured.txt");
            ObjectInputStream objectInputStream
                    = new ObjectInputStream(fileInputStream);
            ArrayList<Insured> list = new ArrayList<>();
            list = (ArrayList<Insured>) objectInputStream.readObject();
            objectInputStream.close();
            return list;
    }

    public List<Doctor> readDoctors() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream
                = new FileInputStream("src/main/resources/Doctor.txt");
        ObjectInputStream objectInputStream
                = new ObjectInputStream(fileInputStream);
        ArrayList<Doctor> list = new ArrayList<>();
        list = (ArrayList<Doctor>) objectInputStream.readObject();
        objectInputStream.close();
        return list;
    }

    public List<Reservation> readReservations() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream
                = new FileInputStream("src/main/resources/Reservation.txt");
        ObjectInputStream objectInputStream
                = new ObjectInputStream(fileInputStream);
        ArrayList<Reservation> list = new ArrayList<>();
        list = (ArrayList<Reservation>) objectInputStream.readObject();
        objectInputStream.close();
        return list;
    }

    public List<Timeslot> readTimeslots() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream
                = new FileInputStream("src/main/resources/Timeslot.txt");
        ObjectInputStream objectInputStream
                = new ObjectInputStream(fileInputStream);
        ArrayList<Timeslot> list = new ArrayList<>();
        list = (ArrayList<Timeslot>) objectInputStream.readObject();
        objectInputStream.close();
        return list;
    }

    public List<Vaccination> readVaccinations() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream
                = new FileInputStream("src/main/resources/Vaccination.txt");
        ObjectInputStream objectInputStream
                = new ObjectInputStream(fileInputStream);
        ArrayList<Vaccination> list = new ArrayList<>();
        list = (ArrayList<Vaccination>) objectInputStream.readObject();
        objectInputStream.close();
        return list;
    }

    public List<VaccinationCenter> readVaccinationCenters() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream
                = new FileInputStream("src/main/resources/VaccinationCenter.txt");
        ObjectInputStream objectInputStream
                = new ObjectInputStream(fileInputStream);
        ArrayList<VaccinationCenter> list = new ArrayList<>();
        list = (ArrayList<VaccinationCenter>) objectInputStream.readObject();
        objectInputStream.close();
        return list;
    }

}