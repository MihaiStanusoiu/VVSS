package salariati.model;

public class Employee {


	private Integer id;

	private String firstName;

	/** The last name of the employee */
	private String lastName;
	
	/** The unique id of the employee */
	private String cnp;
	
	/** The didactic function of the employee inside the university */
	private DidacticFunction function;
	
	/** The salary of the employee */
	private String salary;
	
	/**
	 * Default constructor for employee
	 */
	public Employee() {
		this.id = 0;
		this.firstName = "";
		this.lastName  = "";
		this.cnp       = "";
		this.function  = DidacticFunction.ASISTENT;
		this.salary    = "";
	}
	
	/**
	 * Constructor with fields for employee
	 */
	public Employee(Integer id, String firstName, String lastName, String cnp, DidacticFunction function, String salary) {
		this.id = id;
		this.firstName = firstName;
		this.lastName  = lastName;
		this.cnp       = cnp;
		this.function  = function;
		this.salary    = salary;
	}

	public Employee(String firstName, String lastName, String cnp, DidacticFunction function, String salary) {
		this.id = 0;
		this.firstName = firstName;
		this.lastName  = lastName;
		this.cnp       = cnp;
		this.function  = function;
		this.salary    = salary;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Getter for the employee last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Setter for the employee last name
	 * 
	 * @param lastName the last name to be set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Getter for the employee CNP
	 */
	public String getCnp() {
		return cnp;
	}

	/**
	 * Setter for the employee CNP
	 * 
	 * @param cnp the CNP to be set
	 */
	public void setCnp(String cnp) {
		this.cnp = cnp;
	}

	/**
	 * Getter for the employee didactic function
	 */
	public DidacticFunction getFunction() {
		return function;
	}

	/**
	 * Setter for the employee function
	 * 
	 * @param function the function to be set
	 */
	public void setFunction(DidacticFunction function) {
		this.function = function;
	}

	/**
	 * Getter for the employee salary
	 */
	public String getSalary() {
		return salary;
	}

	/**
	 * Setter for the salary
	 * 
	 * @param salary the salary to be set
	 */
	public void setSalary(String salary) {
		this.salary = salary;
	}
	
	/**
	 * toString function for employee
	 */
	@Override
	public String toString() {
		String employee = "";

		employee += id + ";";
		employee += firstName + ";";
		employee += lastName + ";";
		employee += cnp + ";";
		employee += function.toString() + ";";
		employee += salary;
		
		return employee;
	}

	/**
	 * equals function for employee
	 */
	@Override
	public boolean equals(Object other) {
		if (other == null) return false;
		if (other == this) return true;
		if (!(other instanceof Employee)) return false;
		Employee comparableEmployee = (Employee) other;

		boolean hasSameId = this.id.equals(comparableEmployee.getId()),
				hasSameFirstName = this.firstName.equals(comparableEmployee.getFirstName()),
				hasSameLastName  = this.lastName.equals(comparableEmployee.getLastName()),
				hasSameCNP       = this.cnp.equals(comparableEmployee.getCnp()),
				hasSameFunction  = this.function.equals(comparableEmployee.getFunction()),
				hasSameSalary    = this.salary.equals(comparableEmployee.getSalary());
		return hasSameId && hasSameFirstName && hasSameLastName && hasSameCNP && hasSameFunction && hasSameSalary;
	}
	
	/**
	 * Returns the Employee after parsing the given line
	 * 
	 * @param _employee
	 *            the employee given as String from the input file
	 * 
	 * @return if the given line is valid returns the corresponding Employee
	 * @throws EmployeeException
	 */
	public static Employee getEmployeeFromString(String _employee) throws EmployeeException {
		Employee employee = new Employee();
		
		String[] attributes = _employee.split("[;]");
		
		if( attributes.length != 6) {
			throw new EmployeeException("Invalid employee data");
		} else {
			try {
				EmployeeValidator validator = new EmployeeValidator();
				employee.setId(Integer.parseInt(attributes[0]));
				employee.setFirstName(attributes[1]);
				employee.setLastName(attributes[2]);
				employee.setCnp(attributes[3]);

				if (attributes[4].equals("ASISTENT"))
					employee.setFunction(DidacticFunction.ASISTENT);
				if (attributes[4].equals("LECTURER"))
					employee.setFunction(DidacticFunction.LECTURER);
				if (attributes[4].equals("TEACHER"))
					employee.setFunction(DidacticFunction.TEACHER);

				employee.setSalary(attributes[5]);

				if (!validator.isValid(employee)) {
					throw new EmployeeException("Invalid employee data");
				}
			} catch (NumberFormatException ex) {
				throw new EmployeeException("Invalid employee data");
			}
		}

		return employee;
	}

}
