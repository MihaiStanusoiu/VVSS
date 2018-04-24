package salariati.repository.implementations;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import salariati.model.EmployeeException;

import salariati.model.Employee;

import salariati.repository.interfaces.EmployeeRepositoryInterface;
import salariati.model.EmployeeValidator;

public class EmployeeRepositoryFromFile implements EmployeeRepositoryInterface {
	
	private String employeeDBFile;
	private EmployeeValidator employeeValidator = new EmployeeValidator();

	public EmployeeRepositoryFromFile(String employeeDBFile) {
		this.employeeDBFile = employeeDBFile;
	}

	private boolean isEmployeeSaved(Employee employee) {
		List<Employee> employees = getEmployeeList();
		for (Employee emp : employees) {
			if (emp.getId().equals(employee.getId())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean addEmployee(Employee employee) throws EmployeeException {
		if (isEmployeeSaved(employee)) {
			throw new EmployeeException("Employee already stored");
		}

		if( employeeValidator.isValid(employee) ) {
			BufferedWriter bw = null;
			try {
				bw = new BufferedWriter(new FileWriter(employeeDBFile, true));
				bw.write(employee.toString());
				bw.newLine();
				bw.close();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public void deleteEmployee(Employee employee) {
		List<Employee> employees = getEmployeeList();
		employees.remove(employee);

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(employeeDBFile, false));
			bw.close();
			for (Employee emp : employees) {
				addEmployee(emp);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (EmployeeException e) {
		}

	}

	@Override
	public void modifyEmployee(Employee oldEmployee, Employee newEmployee) {
		try {
			deleteEmployee(oldEmployee);
			addEmployee(newEmployee);
		} catch (EmployeeException e) {
		}
		}

	@Override
	public Employee getEmployeeById(Integer id) throws EmployeeException {
		List<Employee> employees = getEmployeeList();
		for (Employee employee : employees) {
			if (employee.getId().equals(id)) {
				return employee;
			}
		}

		throw new EmployeeException("Employee with id " + id + " not stored");
	}

	@Override
	public List<Employee> getEmployeeList() {
		List<Employee> employeeList = new ArrayList<Employee>();
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(employeeDBFile));
			String line;
			int counter = 0;
			while ((line = br.readLine()) != null) {
				Employee employee = new Employee();
				try {
					employee = Employee.getEmployeeFromString(line);
					employeeList.add(employee);
				} catch(EmployeeException ex) {
					System.err.println(ex.toString() + " at line " + counter);
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("Error while reading: " + e);
		} catch (IOException e) {
			System.err.println("Error while reading: " + e);
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					System.err.println("Error while closing the file: " + e);
				}
		}
		
		return employeeList;
	}


	@Override
	public List<Employee> getEmployeeByCriteria(String criteria) {
		List<Employee> employeeList = new ArrayList<Employee>();
		
		return employeeList;
	}

    @Override
    public List<Employee> getEmployeesSorted(Comparator<Employee> comparator) {
        List<Employee> employees = getEmployeeList();
        Collections.sort(employees, comparator);

        return employees;
    }
}
