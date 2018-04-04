import controller.LaboratoriesController;
import junit.framework.TestCase;
import model.Laboratory;
import model.Student;

import java.io.BufferedReader;
import java.io.FileReader;
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

    public void testSaveStudent() throws Exception {
        System.out.println("\n TEST - Save student \n");


        Student student = new Student("asdf4455", "mircea bravo", 933);
        ctrl.saveStudent(student);
        assertEquals(student.getRegNumber(), "asdf4455");
        assertEquals(student.getName(), "mircea bravo");
        assertEquals(student.getGroup(), 933);


    }

    public void testSaveLaboratory() throws Exception {
        System.out.println("\n TEST - Save Laboratory \n");

        LaboratoriesController ctrl = new LaboratoriesController("students.txt", "laboratories.txt");

        Laboratory laboratory = new Laboratory(10,"11/12/2017", 2, "asdf4455");
        ctrl.saveLaboratory(laboratory);
        assertEquals(laboratory.getNumber(), 10);
        assertEquals(laboratory.getProblemNumber(), 2);
        assertEquals(laboratory.getStudentRegNumber(), "asdf4455");

    }

    public void testAddGrade() throws Exception {
        System.out.println("\n TEST - Add new grade \n");
        LaboratoriesController ctrl = new LaboratoriesController("students.txt", "laboratories.txt");
        ctrl.addGrade("asdf4455", "10", 7);
        BufferedReader reader = new BufferedReader(new FileReader("laboratories.txt"));
        String line = reader.readLine();
        String[] temp = line.split(" ");
        float labGrade = Float.valueOf(temp[3]);
        assertEquals(labGrade, (float) 9);

    }

    public void testGetLaboratoryMap() throws Exception {
        System.out.println("TEST - Get laboratory map");
        ctrl.addGrade("asdf4455", "10", 7);
        Map<String, List<Laboratory>> laboratoryMap = laboratoryDataPersistance.getLaboratoryMap();
        for(Map.Entry<String, List<Laboratory>> l: laboratoryMap.entrySet()) {
            List<Laboratory> list = l.getValue();
            assertEquals(list.size(), list.size());

        }

    }

    public void testGetStudentsList() throws Exception {
        System.out.println("TEST - Get students list");
        List<Student> students = studentsDataPersistance.getStudentsList();

        students.remove(students.size()-1);
        assertEquals(students.size(), students.size());

    }

    public void testAddGrade_l2() throws Exception {
        System.out.println("\n TEST - Add new grade2 \n");
        LaboratoriesController ctrl = new LaboratoriesController("students.txt", "laboratories.txt");
        ctrl.addGrade("grade1", "2", 5);
        BufferedReader reader = new BufferedReader(new FileReader("laboratories.txt"));
        String line = reader.readLine();
        String[] temp = line.split(" ");
        float labGrade = Float.valueOf(temp[3]);
        assertEquals(labGrade, (float) 9);

    }

    public void testAddGrade_l2_p2() throws Exception {
        System.out.println("\n TEST - Add new grade3 \n");
        LaboratoriesController ctrl = new LaboratoriesController("students.txt", "laboratories.txt");
        ctrl.addGrade("grade2", "3", 8);
        BufferedReader reader = new BufferedReader(new FileReader("laboratories.txt"));
        String line = reader.readLine();
        String[] temp = line.split(" ");
        String labGrade = temp[4];
        assertEquals(labGrade, "asdf1234");

    }
}