package pet.stores.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
public class PetStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_store_id")
    private Long petStoreId;
    
    @EqualsAndHashCode.Exclude
    private String petStoreName;
    
    @EqualsAndHashCode.Exclude
    private String petStoreAddress;
    
    @EqualsAndHashCode.Exclude
    private String petStoreCity;
    
    @EqualsAndHashCode.Exclude
    private String petStoreState;
    
    @EqualsAndHashCode.Exclude
    private String petStoreZip;
    
    @EqualsAndHashCode.Exclude
    private String petStorePhone;

    @OneToMany(mappedBy = "petStore", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Employee> employees;
    
    @ManyToMany(mappedBy = "petStores", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Set<Customer> customers = new HashSet<>();

	public String getName() {
		return this.petStoreName;
	}
}
