package pet.stores.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.stores.entity.Employee;

@Data
@NoArgsConstructor
public class PetStoreEmployee {
    private Long id;
    private String firstName;
    private String lastName;

    // Constructor
    public PetStoreEmployee(Employee employee) {
        this.id = employee.getId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        // Initialize other fields accordingly if needed
    }
}