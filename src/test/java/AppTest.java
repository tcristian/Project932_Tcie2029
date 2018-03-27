import controller.LaboratoriesController;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import model.Student;
import repository.FileDataPersistence;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {

        return new TestSuite( AppTest.class );

    }
    public void testApp(){
        assertTrue(true);
    }
    public void testAdd(){
        Student s1 = new Student("123", "Cristian Iftenie", 932);

        FileDataPersistence studentPersistence = new FileDataPersistence(
                "students.txt");

        //List<Student> students = studentPersistence.getStudentsList();
       // Student last = students.get(students.size() - 1)
        studentPersistence.saveStudent(s1);
        assertTrue(true);

    }
    /**
     * Rigourous Test :-)
     */
}
