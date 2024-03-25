package pet.stores.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long employeeId;

    @ManyToOne(cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "pet_store_id") // Specify the column name explicitly
    private PetStore petStore;

    @EqualsAndHashCode.Exclude
    private String employeeFirstName;

    @EqualsAndHashCode.Exclude
    private String employeeLastName;

    @EqualsAndHashCode.Exclude
    private String employeeEmail;

    @EqualsAndHashCode.Exclude
    private String employeeJobTitle;
}
