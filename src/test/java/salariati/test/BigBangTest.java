package salariati.test;

import org.junit.Before;
import org.junit.Test;
import salariati.controller.EmployeeController;
import salariati.model.DidacticFunction;
import salariati.model.Employee;
import salariati.model.EmployeeException;
import salariati.repository.implementations.EmployeeRepositoryFromFile;
import salariati.repository.interfaces.EmployeeRepositoryInterface;

import java.util.List;

import static org.junit.Assert.*;


public class BigBangTest {

    public EmployeeRepositoryInterface repository;
    public EmployeeController controller;

    @Before
    public void setUp() {
        repository = new EmployeeRepositoryFromFile("src/main/resources/employees.txt");
        controller = new EmployeeController(repository);
    }

    @Test
    public void testCase() {
        try {
            assertTrue(controller.getEmployeesList().size() == 5);
            Employee employee = new Employee("Vlad", "Simion", "1111000000123", DidacticFunction.TEACHER, "8000");
            controller.addEmployee(employee);
            assertTrue(controller.getEmployeesList().get(5).getFirstName().equals("Vlad"));
            Integer id = controller.getEmployeesList().get(5).getId();
            Employee newEmployee = new Employee("Simion", "Vlad", "1111000000123", DidacticFunction.TEACHER, "8000");
            controller.modifyEmployee(employee, newEmployee);
            assertTrue(controller.getEmployeeById(id).getFirstName().equals("Simion"));
            List<Employee> employeeList = controller.getEmployeesSortedBySalary();
            for (int i = 0; i < employeeList.size() - 1; ++i) {
                Integer salary1 = Integer.parseInt(employeeList.get(i).getSalary());
                Integer salary2 = Integer.parseInt(employeeList.get(i + 1).getSalary());
                if (salary1.compareTo(salary2) < 0) {
                    assertTrue(false);
                }
            }
            controller.deleteEmployee(newEmployee);
        } catch (EmployeeException e) {
            e.printStackTrace();
        }
    }

}
