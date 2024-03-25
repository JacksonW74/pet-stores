package pet.stores.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.stores.entity.Employee;

@Data
@NoArgsConstructor
public class PetStoreEmployee {
    private Long employeeId;
    private String employeeFirstName;
    private String employeeLastName;
    private String employeeEmail;
    private String employeeJobTitle;

    public PetStoreEmployee(Employee employee) {
    	this.employeeId = employee.getEmployeeId();
        this.employeeFirstName = employee.getEmployeeFirstName();
        this.employeeLastName = employee.getEmployeeLastName();
        this.employeeEmail = employee.getEmployeeEmail();
        this.employeeJobTitle = employee.getEmployeeJobTitle();
    }
}