package pet.stores.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import pet.stores.entity.PetStore;

@Data
@NoArgsConstructor
public class PetStoreData {
    private Long petStoreId;

    private String petStoreName;
    private String petStoreAddress;
    private String petStoreCity;
    private String petStoreState;
    private String petStoreZip;
    private String petStorePhone;

    public PetStoreData(PetStore petStore) {
        this.petStoreId = petStore.getPetStoreId();
        this.petStoreName = petStore.getName();
        this.petStoreAddress = petStore.getPetStoreAddress();
        this.petStoreCity = petStore.getPetStoreCity();
        this.petStoreState = petStore.getPetStoreState();
        this.petStoreZip = petStore.getPetStoreZip();
        this.petStorePhone = petStore.getPetStorePhone();
    }
}