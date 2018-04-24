package salariati.model;

public class EmployeeValidator {
	
	public EmployeeValidator(){}

	public boolean isValid(Employee employee) {
		try {
			boolean isIdValid = employee.getId() >= 0;
			boolean isFirstNameValid = employee.getFirstName().matches("[a-zA-Z]+(\\-[a-zA-Z])*") && (employee.getFirstName().length() > 2);
			boolean isLastNameValid = employee.getLastName().matches("[a-zA-Z]+(\\-[a-zA-Z])*") && (employee.getLastName().length() > 2);
			boolean isCNPValid = employee.getCnp().matches("[0-9]+") && (employee.getCnp().length() == 13);
			boolean isFunctionValid = employee.getFunction().equals(DidacticFunction.ASISTENT) ||
					employee.getFunction().equals(DidacticFunction.LECTURER) ||
					employee.getFunction().equals(DidacticFunction.TEACHER);
			boolean isSalaryValid = employee.getSalary().matches("[0-9]+") && (employee.getSalary().length() > 1) && (Integer.parseInt(employee.getSalary()) > 0);

			return isIdValid && isFirstNameValid && isLastNameValid && isCNPValid && isFunctionValid && isSalaryValid;
		}
		catch (Exception ex) {
			return false;
		}
	}

	
}
