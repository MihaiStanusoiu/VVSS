package salariati.main;

import salariati.model.DidacticFunction;
import salariati.model.EmployeeException;
import salariati.model.Employee;
import salariati.repository.implementations.EmployeeRepositoryFromFile;
import salariati.repository.interfaces.EmployeeRepositoryInterface;
import salariati.controller.EmployeeController;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//functionalitati
//i.	 adaugarea unui nou angajat (nume, prenume, CNP, functia didactica, salariul de incadrare);
//ii.	 modificarea functiei didactice (asistent/lector/conferentiar/profesor) a unui angajat;
//iii.	 afisarea salariatilor ordonati descrescator dupa salariu si crescator dupa varsta (CNP).
public class StartApp {

	public static EmployeeRepositoryInterface employeeRepository =  new EmployeeRepositoryFromFile("src/main/resources/employees.txt");
	public static EmployeeController employeeController = new EmployeeController(employeeRepository);

	public static void showAddMenu() {
		Scanner scanner = new Scanner(System.in);
        System.out.println("\tEnter employee first name: ");
        String firstName = scanner.nextLine();
		System.out.println("\tEnter employee last name: ");
		String lastName = scanner.nextLine();
        System.out.println("\tEnter employee CNP: ");
        String cnp = scanner.next();
        System.out.println("\tEnter employee didactic function: ");
        String didacticFunction = scanner.next();
        System.out.println("\tEnter employee salary: ");
        String salaryString = scanner.next();
		try {
			Employee employee = new Employee(
			        firstName,
					lastName,
					cnp,
					DidacticFunction.valueOf(didacticFunction),
					salaryString
                    );
			employeeController.addEmployee(employee);
			System.out.println("Employee added successfully");
		}
		catch (EmployeeException ex) {
			System.out.println("Invalid employee data");
		}
		catch (IllegalArgumentException ex) {
			System.out.println("Invalid employee data");
		}
		finally {
			scanner.close();
		}
	}

	public static void showEditMenu() {
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println("\tEnter id of employee to be edited");
			int id = scanner.nextInt();
			Employee oldEmployee = employeeController.getEmployeeById(id);
			System.out.println("\tOld employee: " + oldEmployee.toString());

			System.out.println("\tEnter new employee data (format: id;firstName;lastName;cnp;didacticFunction;salary )");
			String employeeString = scanner.next();
			Employee newEmployee = Employee.getEmployeeFromString(employeeString);
			if (! oldEmployee.getId().equals(newEmployee.getId())) {
				System.out.println("Invalid id");
				return;
			}

			employeeController.modifyEmployee(oldEmployee, newEmployee);
			System.out.println("Employee added successfully");
		}
		catch (EmployeeException ex) {
			System.err.println(ex.toString());
		}
		catch (NumberFormatException ex) {
			System.err.println("Invalid input data");
		}
		finally {
			scanner.close();
		}
	}

	public static void showList() {
		List<Employee> employees = employeeController.getEmployeesList();
		System.out.printf("List of employees:");
		System.out.println();
		for (Employee employee : employees) {
			System.out.println(employee.toString());
		}
		System.out.println();
	}

	public static void showSortedList() {
		List<Employee> employees = employeeController.getEmployeesSortedBySalary();
		System.out.printf("List of employees sorted by salary:");
		System.out.println();
		for (Employee employee : employees) {
			System.out.println(employee.toString());
		}
		System.out.println();
	}

	public static void showMainMenu() {
		System.out.println("Choose an option:");
		System.out.println("\t1. Add an employee");
		System.out.println("\t2. Edit an employee");
		System.out.println("\t3. Show employees sorted");
		System.out.println("\t4. Exit");
		System.out.println();
	}
	
	public static void main(String[] args) {
		
//		EmployeeRepositoryInterface employeesRepository = new EmployeeMock();
//		EmployeeController employeeController = new EmployeeController(employeesRepository);
//
//		for(Employee _employee : employeeController.getEmployeesList())
//			System.out.println(_employee.toString());
//		System.out.println("-----------------------------------------");
//
//		Employee employee = new Employee("FirstName", "LastName", "1234567894321", DidacticFunction.ASISTENT, "2500");
//		employeeController.addEmployee(employee);
//
//		for(Employee _employee : employeeController.getEmployeesList())
//			System.out.println(_employee.toString());
//
//		EmployeeValidator validator = new EmployeeValidator();
//		System.out.println( validator.isValid(new Employee("FirstName", "LastName", "1234567894322", DidacticFunction.TEACHER, "3400")) );

		while (true) {
			StartApp.showList();
			StartApp.showMainMenu();
			Scanner scanner = new Scanner(System.in);
			char option = scanner.next().charAt(0);

			switch (option) {
				case '1' :
					StartApp.showAddMenu();
					break;
				case '2' :
					StartApp.showEditMenu();
					break;
				case '3':
					StartApp.showSortedList();
					break;
				case '4' :
					scanner.close();
					return;
			}
		}

	}

}
