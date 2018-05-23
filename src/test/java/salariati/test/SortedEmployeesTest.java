package salariati.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import salariati.model.Employee;
import salariati.model.EmployeeException;
import salariati.repository.implementations.EmployeeRepositoryFromFile;
import salariati.repository.interfaces.EmployeeRepositoryInterface;

import java.util.Comparator;
import java.util.List;

public class SortedEmployeesTest {

    public EmployeeRepositoryInterface repository;

    @Before
    public void setUp() {

    }

    @Test
    public void testCase1() {
        repository = new EmployeeRepositoryFromFile("src/main/resources/employees.txt");
        try {
            Comparator<Employee> comparator = new Comparator<Employee>() {
                @Override
                public int compare(Employee o1, Employee o2) {
                    int salary1 = Integer.parseInt(o1.getSalary());
                    int salary2 = Integer.parseInt(o2.getSalary());
                    int diff = salary2 - salary1;
                    if (diff == 0) {
                        return o1.getCnp().compareTo(o2.getCnp());
                    }

                    return salary2 - salary1;
                }
            };

            List<Employee> employeeList = repository.getEmployeesSorted(comparator);
            for (int i = 0; i < employeeList.size() - 1; ++i) {
                Integer salary1 = Integer.parseInt(employeeList.get(i).getSalary());
                Integer salary2 = Integer.parseInt(employeeList.get(i + 1).getSalary());
                if (salary1.compareTo(salary2) < 0) {
                    assertTrue(false);
                }
            }

        } catch (EmployeeException e) {
            e.printStackTrace();
        }
    }

}
