package if1.else1.code.coverage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class IfConditionTest {
	@Mock
	Employee employee;

	@InjectMocks
	IfCondition ifCondition;
	private static List<Employee> emplist = new ArrayList<Employee>();

	public static List<Employee> getEmployeeBlr() {
		Employee emp1 = new Employee("dilip", "blr", 1234);
		Employee emp2 = new Employee("dilip", "blr", 1234);
		emplist.add(emp1);
		emplist.add(emp2);
		return emplist;
	}
	public static List<Employee> getEmployeeNotBlr() {
		Employee emp1 = new Employee("dilip", "blr1", 1234);
		Employee emp2 = new Employee("dilip", "blr1", 1234);
		emplist.add(emp1);
		emplist.add(emp2);
		return emplist;
	}

	@Test
	public void getEmployeTestaAddBlr() {
		when(employee.getEmployee()).thenReturn(getEmployeeBlr());
		assertEquals(getEmployeeBlr(), ifCondition.getEmploye(1));
		
	}
	@Test
	public void getEmployeTestaAddNotBlr() {
		//when(employee.getAddress()).thenReturn("blr1");
		when(employee.getEmployee()).thenReturn(getEmployeeNotBlr());
		assertEquals(getEmployeeNotBlr(), ifCondition.getEmploye(1));
		
	}
}
