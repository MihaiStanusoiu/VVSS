package salariati.controller;

import java.util.Comparator;
import java.util.List;

import salariati.model.EmployeeException;
import salariati.model.Employee;
import salariati.repository.interfaces.EmployeeRepositoryInterface;

public class EmployeeController {
	
	private EmployeeRepositoryInterface employeeRepository;
	
	public EmployeeController(EmployeeRepositoryInterface employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	public void addEmployee(Employee employee) throws EmployeeException {
		employeeRepository.addEmployee(employee);
	}

	public Employee getEmployeeById(Integer id) throws EmployeeException{
		return employeeRepository.getEmployeeById(id);
	}

	public List<Employee> getEmployeesList() {
		return employeeRepository.getEmployeeList();
	}
	
	public void modifyEmployee(Employee oldEmployee, Employee newEmployee) {
		employeeRepository.modifyEmployee(oldEmployee, newEmployee);
	}

	public List<Employee> getEmployeesSortedBySalary() {
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

		return employeeRepository.getEmployeesSorted(comparator);
	}

	public void deleteEmployee(Employee employee) {
		employeeRepository.deleteEmployee(employee);
	}
	
}
