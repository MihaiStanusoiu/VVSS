package salariati.repository.mock;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import salariati.model.DidacticFunction;

import salariati.model.EmployeeException;
import salariati.model.Employee;
import salariati.repository.interfaces.EmployeeRepositoryInterface;
import salariati.model.EmployeeValidator;

public class EmployeeMock implements EmployeeRepositoryInterface {

	private List<Employee> employeeList;
	private EmployeeValidator employeeValidator;
	
	public EmployeeMock() {
		
		employeeValidator = new EmployeeValidator();
		employeeList = new ArrayList<Employee>();
		
		Employee Ionel   = new Employee("Ion", "Pacuraru", "1234567890876", DidacticFunction.ASISTENT, "2500");
		Employee Mihai   = new Employee("Ion", "Dumitrescu", "1234567890876", DidacticFunction.LECTURER, "2500");
		Employee Ionela  = new Employee("Ion", "Ionescu", "1234567890876", DidacticFunction.LECTURER, "2500");
		Employee Mihaela = new Employee("Ion", "Pacuraru", "1234567890876", DidacticFunction.ASISTENT, "2500");
		Employee Vasile  = new Employee("Ion", "Georgescu", "1234567890876", DidacticFunction.TEACHER,  "2500");
		Employee Marin   = new Employee("Ion", "Puscas", "1234567890876", DidacticFunction.TEACHER,  "2500");
		
		employeeList.add( Ionel );
		employeeList.add( Mihai );
		employeeList.add( Ionela );
		employeeList.add( Mihaela );
		employeeList.add( Vasile );
		employeeList.add( Marin );
	}
	
	@Override
	public boolean addEmployee(Employee employee) {
		if ( employeeValidator.isValid(employee)) {
			employeeList.add(employee);
			return true;
		}
		return false;
	}
	
	@Override
	public void deleteEmployee(Employee employee) {
		// TODO Auto-generated method stub
	}

	@Override
	public void modifyEmployee(Employee oldEmployee, Employee newEmployee) {
		// TODO Auto-generated method stub
	}

	@Override
	public Employee getEmployeeById(Integer id) throws EmployeeException {
		return null;
	}

	@Override
	public List<Employee> getEmployeesSorted(Comparator<Employee> comparator) {
		return null;
	}

	@Override
	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	@Override
	public List<Employee> getEmployeeByCriteria(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
