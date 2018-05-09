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

	public boolean isEmployeeSaved(Employee employee) throws EmployeeException{
		List<Employee> employees = getEmployeeList();
		for (Employee emp : employees) {
			if (emp.getId().equals(employee.getId())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Integer getMaxId() throws EmployeeException {
	    List<Employee> employees = getEmployeeList();
	    Integer max = 0;
	    for (Employee employee : employees) {
	        if (employee.getId() > max) {
	            max = employee.getId();
            }
        }
        return max;
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
		else {
			throw new EmployeeException("Invalid employee data");
		}
		return false;
	}

	@Override
	public void deleteEmployee(Employee employee) throws EmployeeException{
		if (!isEmployeeSaved(employee)) {
			throw new EmployeeException("Employee with id " + employee.getId() + " not stored");
		}

		List<Employee> employees = getEmployeeList();
		employees.remove(employee);

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(employeeDBFile, false));
			bw.close();
			for (Employee emp : employees) {
				addEmployee(emp);
			}

		} catch (IOException e) {
			throw new EmployeeException("Invalid repository file");
		}
	}

	@Override
	public void modifyEmployee(Employee oldEmployee, Employee newEmployee) throws EmployeeException {
		deleteEmployee(oldEmployee);
		addEmployee(newEmployee);
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
	public List<Employee> getEmployeeList() throws EmployeeException{
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
			throw new EmployeeException("Error at reading repository file");
		} catch (IOException e) {
			throw new EmployeeException("Error at reading repository file");
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					throw new EmployeeException("Error at reading repository file");
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
    public List<Employee> getEmployeesSorted(Comparator<Employee> comparator) throws EmployeeException{
        List<Employee> employees = getEmployeeList();
        Collections.sort(employees, comparator);

        return employees;
    }
}
