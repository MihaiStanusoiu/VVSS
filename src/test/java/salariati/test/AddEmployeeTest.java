package salariati.test;

import static org.junit.Assert.*;

import salariati.model.EmployeeException;
import salariati.model.Employee;

import org.junit.Before;
import org.junit.Test;

import salariati.repository.interfaces.EmployeeRepositoryInterface;
import salariati.repository.mock.EmployeeMock;
import salariati.model.EmployeeValidator;
import salariati.controller.EmployeeController;
import salariati.model.DidacticFunction;

public class AddEmployeeTest {

	private EmployeeRepositoryInterface employeeRepository;
	private EmployeeController controller;
	private EmployeeValidator employeeValidator;
	private Employee employee1;
	private Employee employee2;
	private Employee employee3;
	private Employee employee4;
	private Employee employee5;
	private Employee employee6;
	private Employee employee7;

	@Before
	public void setUp() {
		employeeRepository = new EmployeeMock();
		controller         = new EmployeeController(employeeRepository);
		employeeValidator  = new EmployeeValidator();
		employee1 = new Employee("Andrei", "Pop", "1111000000123", DidacticFunction.TEACHER, "5000");
		employee2 = new Employee("", "Pop", "1111000000123", DidacticFunction.TEACHER, "5000");
		employee3 = new Employee("An", "Pop", "1111000000123", DidacticFunction.TEACHER, "5000");
		employee4 = new Employee("Andrei", "Pop", "1111000000123", DidacticFunction.TEACHER, "0");
		employee5 = new Employee("Andrei", "Pop", "1111000000123", DidacticFunction.TEACHER, "1");
		employee6 = new Employee("Andrei", "Pop", "1111000000123", DidacticFunction.TEACHER, "-1");
	}
	
	@Test
	public void testRepositoryMock() {
//		assertFalse(controller.getEmployeesList().isEmpty());
//		assertEquals(6, controller.getEmployeesList().size());
	}
	
	@Test
	public void testAddNewEmployee() {
		try {
			controller.addEmployee(employee1);
			assertTrue(true);
		} catch (EmployeeException e) {
			assertTrue(false);
		}

		try {
			controller.addEmployee(employee2);
			assertTrue(false);
		} catch (EmployeeException e) {
			assertTrue(e.getMessage().equals("Invalid employee data"));
		}

		try {
			controller.addEmployee(employee3);
			assertTrue(false);
		} catch (EmployeeException e) {
			assertTrue(e.getMessage().equals("Invalid employee data"));
		}

		try {
			controller.addEmployee(employee4);
			assertTrue(false);
		} catch (EmployeeException e) {
			assertTrue(e.getMessage().equals("Invalid employee data"));
		}

		try {
			controller.addEmployee(employee5);
			assertTrue(true);
		} catch (EmployeeException e) {
			assertTrue(false);
		}

		try {
			controller.addEmployee(employee6);
			assertTrue(false);
		} catch (EmployeeException e) {
			assertTrue(e.getMessage().equals("Invalid employee data"));
		}
	}

}
