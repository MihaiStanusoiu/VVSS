package salariati.repository.interfaces;

import java.util.Comparator;
import java.util.List;

import salariati.model.EmployeeException;
import salariati.model.Employee;

public interface EmployeeRepositoryInterface {

	boolean isEmployeeSaved(Employee employee) throws EmployeeException;
	boolean addEmployee(Employee employee) throws EmployeeException;
	void deleteEmployee(Employee employee) throws EmployeeException;
	Integer getMaxId() throws EmployeeException;
	void modifyEmployee(Employee oldEmployee, Employee newEmployee) throws EmployeeException;
	List<Employee> getEmployeeList() throws EmployeeException;
	Employee getEmployeeById(Integer id) throws EmployeeException;
	List<Employee> getEmployeeByCriteria(String criteria);
	List<Employee> getEmployeesSorted(Comparator<Employee> comparator) throws EmployeeException;

}
