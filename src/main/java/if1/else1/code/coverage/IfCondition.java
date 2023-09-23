package if1.else1.code.coverage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IfCondition implements Condition {

	@Autowired
	Employee employee;
	int x=0;

	@Override
	public List<Employee> getEmploye(int id) {
		List<Employee> employeeList = employee.getEmployee();
		for(Employee emp : employeeList) {
		  if(emp.getAddress().equals("blr")) {
			  System.out.println(emp.getAddress());
		  }else {
			  System.out.println(emp.getAddress());
		  }
		}
		return employeeList;
	}

}

interface Condition {
	public List<Employee> getEmploye(int id);
}

@Service
class Employee {
	String name;
	String address;
	int mobNumber;

	public List<Employee> getEmployee() {
		//System.out.println("dilip");
		Employee emp1 = new Employee("dilip", "blr", 1234);
		Employee emp2 = new Employee("dilip", "blr", 1234);
		Employee emp3 = new Employee("dilip", "blr", 1234);
		Employee emp4 = new Employee("dilip", "blr", 1234);
		Employee emp5 = new Employee("dilip", "blr", 1234);
		Employee emp6 = new Employee("dilip", "blr", 1234);
		List<Employee> empList = new ArrayList<Employee>();
		empList.add(emp1);
		empList.add(emp2);
		empList.add(emp3);
		empList.add(emp4);
		empList.add(emp5);
		empList.add(emp6);
		return empList;
	}

	public Employee(String name, String address, int mobNumber) {
		super();
		this.name = name;
		this.address = address;
		this.mobNumber = mobNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getMobNumber() {
		return mobNumber;
	}

	public void setMobNumber(int mobNumber) {
		this.mobNumber = mobNumber;
	}

}