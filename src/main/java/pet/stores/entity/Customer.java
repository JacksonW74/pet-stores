package pet.stores.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

	@EqualsAndHashCode.Exclude    
    private String customerFirstName;
	
	@EqualsAndHashCode.Exclude
    private String customerLastName;
	
	@EqualsAndHashCode.Exclude
    private String customerEmail;

	@EqualsAndHashCode.Exclude
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "customer_pet_store",
	    joinColumns = { @JoinColumn(name = "customer_id") },
	    inverseJoinColumns = { @JoinColumn(name = "pet_store_id") })
	private Set<PetStore> petStores = new HashSet<>();
    
    public Customer() {
        this.petStores = new HashSet<>();
    }
}
