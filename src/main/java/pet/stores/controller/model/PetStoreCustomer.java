package pet.stores.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.stores.entity.Customer;

@Data
@NoArgsConstructor
public class PetStoreCustomer {
    private Long customer_id;
    private String customerFirstName;
    private String customerLastName;
    private String customerEmail;

    // Constructor
    public PetStoreCustomer(Customer customer) {
        this.customer_id = customer.getCustomerId(); 
        this.customerFirstName = customer.getCustomerFirstName();
        this.customerLastName = customer.getCustomerLastName();
        this.customerEmail = customer.getCustomerEmail();
        // Initialize other fields accordingly if needed
    }

    // Setter method for customer_id
    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    // Getter method for customer_id
    public Long getCustomer_id() {
        return customer_id;
    }
}
