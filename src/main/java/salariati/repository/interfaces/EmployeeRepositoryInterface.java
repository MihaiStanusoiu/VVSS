package salariati.repository.interfaces;

import java.util.Comparator;
import java.util.List;

import salariati.model.EmployeeException;
import salariati.model.Employee;

public interface EmployeeRepositoryInterface {

	boolean isEmployeeSaved(Employee employee);
	boolean addEmployee(Employee employee) throws EmployeeException;
	void deleteEmployee(Employee employee);
	Integer getMaxId();
	void modifyEmployee(Employee oldEmployee, Employee newEmployee);
	List<Employee> getEmployeeList();
	Employee getEmployeeById(Integer id) throws EmployeeException;
	List<Employee> getEmployeeByCriteria(String criteria);
	List<Employee> getEmployeesSorted(Comparator<Employee> comparator);

}
