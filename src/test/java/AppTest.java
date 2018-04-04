import controller.LaboratoriesController;
import junit.framework.TestCase;
import model.Laboratory;
import model.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import repository.*;


public class AppTest extends TestCase {
    public void setUp() throws Exception {
        super.setUp();

    }

    private FileDataPersistence studentsDataPersistance = new FileDataPersistence("students.txt");
    private FileDataPersistence laboratoryDataPersistance = new FileDataPersistence("laboratories.txt");
    private LaboratoriesController ctrl = new LaboratoriesController("students.txt", "laboratories.txt");

    public void testSaveStudent2() throws Exception {
        System.out.println("\n TEST - Save student \n");


        Student student = new Student("asdf4455", "mircea bravo", 933);
        ctrl.saveStudent(student);
        BufferedReader reader = new BufferedReader(new FileReader("students.txt"));
        String line;
        line = reader.readLine();
        String[] temp = line.split(" ");
        String studentReg = temp[0];
        String studentName = temp[1] + " " + temp[2];
        int studentGroup = Integer.valueOf(temp[3]);
        assertEquals(student.getRegNumber(), "asdf4455");
        assertEquals(student.getName(), "mircea bravo");
        assertEquals(student.getGroup(), 933);


    }

    public void testSaveLaboratory2() throws Exception {
        System.out.println("\n TEST - Save Laboratory \n");

        LaboratoriesController ctrl = new LaboratoriesController("students.txt", "laboratories.txt");

        Laboratory laboratory = new Laboratory(10,"11/12/2017", 2, "asdf4455");
        ctrl.saveLaboratory(laboratory);
        BufferedReader reader = new BufferedReader(new FileReader("laboratories.txt"));
        String line = reader.readLine();
        String[] temp = line.split(" ");
        int labNumber = Integer.valueOf(temp[0]);
        int problemNumber = Integer.valueOf(temp[2]);
        String strudentReg = temp[4];
        assertEquals(laboratory.getNumber(), 10);
        assertEquals(laboratory.getProblemNumber(), 2);
        assertEquals(laboratory.getStudentRegNumber(), "asdf4455");

    }

    public void testAddGrade2() throws Exception {
        System.out.println("\n TEST - Add new grade \n");

        LaboratoriesController ctrl = new LaboratoriesController("students.txt", "laboratories.txt");

        ctrl.addGrade("asdf4455", "10", 7);
        BufferedReader reader = new BufferedReader(new FileReader("laboratories.txt"));
        String line = reader.readLine();
        String[] temp = line.split(" ");
        float labGrade = Float.valueOf(temp[3]);
        assertEquals((float)7, (float) 7);

    }

    public void testGetLaboratoryMap2() throws Exception {
        System.out.println("TEST - Get laboratory map");
        //LaboratoriesController ctrl = new LaboratoriesController("students.txt", "laboratories.txt");
        testAddGrade2();
        Map<String, List<Laboratory>> laboratoryMap = laboratoryDataPersistance.getLaboratoryMap();
        for(Map.Entry<String, List<Laboratory>> l: laboratoryMap.entrySet()) {
            assertEquals(l.getKey(), "asdf4455");
            List<Laboratory> list = l.getValue();
            assertEquals(list.size(), list.size());

        }

    }

    public void testGetStudentsList2() throws Exception {
        System.out.println("TEST - Get students list");

        List<Student> students = studentsDataPersistance.getStudentsList();
        assertEquals(students.size(), 3);

    }

}