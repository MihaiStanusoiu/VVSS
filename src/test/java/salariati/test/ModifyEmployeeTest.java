package salariati.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import salariati.model.DidacticFunction;
import salariati.model.Employee;
import salariati.model.EmployeeException;
import salariati.repository.implementations.EmployeeRepositoryFromFile;
import salariati.repository.interfaces.EmployeeRepositoryInterface;

/**
 * Name:         {ClassName}
 * Effect:         {ClassEffect}
 * Date:           5/8/2018
 * Tested:        False
 *
 * @author {Stanusoiu Mihai-Teodor}
 * @version 1.0
 */
public class ModifyEmployeeTest {

    public EmployeeRepositoryInterface repository;

    @Before
    public void setUp() {
//        repository = new EmployeeRepositoryFromFile("employeesTest1.txt");
    }

    @Test
    public void testCase1() {
        repository = new EmployeeRepositoryFromFile("src/main/resources/employeesTest1.txt");
        try {
            assertEquals(repository.getEmployeeList().size(), 1);
            Employee oldEmployee = repository.getEmployeeList().get(0);
            assertTrue(oldEmployee.getId().equals(1));
            Employee newEmployee = new Employee(1, "Ion", "Ionescu", "1110001110002", DidacticFunction.LECTURER, "6000");
            repository.modifyEmployee(oldEmployee, newEmployee);
            assertEquals(repository.getEmployeeList().size(), 1);
            Employee employee = repository.getEmployeeList().get(0);
            assertTrue(employee.getId().equals(1) && employee.getFunction().equals(DidacticFunction.LECTURER));
        } catch (EmployeeException e) {
            assertTrue(false);
        }
    }

    @Test
    public void testCase2() {
        repository = new EmployeeRepositoryFromFile("src/main/resources/employeesTest2.txt");
        try {
            assertEquals(repository.getEmployeeList().size(), 0);
            Employee oldEmployee = new Employee(1, "Ion", "Ionescu", "1110001110002", DidacticFunction.LECTURER, "6000");
            Employee newEmployee = new Employee(1, "Ion", "Ionescu", "1110001110002", DidacticFunction.ASISTENT, "6000");
            repository.modifyEmployee(oldEmployee, newEmployee);
            assertTrue(false);
        }
        catch (EmployeeException e) {
            assertTrue(e.getMessage().equals("Employee with id 1 not stored"));
        }
    }

    @Test
    public void testCase3() {
        repository = new EmployeeRepositoryFromFile("src/main/resources/employeesTest3.txt");
        try {
            assertTrue(repository.getEmployeeList().size() > 1);
            int oldSize = repository.getEmployeeList().size();
            Employee oldEmployee = repository.getEmployeeById(1);
            assertTrue(oldEmployee.getId().equals(1));
            Employee newEmployee = new Employee(1, "Ion", "Ionescu", "1110001110002", DidacticFunction.LECTURER, "6000");
            repository.modifyEmployee(oldEmployee, newEmployee);
            assertEquals(repository.getEmployeeList().size(), oldSize);
            Employee employee = repository.getEmployeeById(1);
            assertTrue(employee.getFunction().equals(DidacticFunction.LECTURER));
        } catch (EmployeeException e) {
            assertTrue(false);
        }
    }
}
