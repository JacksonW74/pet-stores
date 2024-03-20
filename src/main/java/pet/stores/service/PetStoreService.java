package pet.stores.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pet.stores.controller.model.PetStoreData;
import pet.stores.dao.PetStoreDao;
import pet.stores.entity.PetStore;

import java.util.NoSuchElementException;

@Service
public class PetStoreService {

    private final PetStoreDao petStoreDao;

    @Autowired
    public PetStoreService(PetStoreDao petStoreDao) {
        this.petStoreDao = petStoreDao;
    }

    public PetStoreData savePetStore(PetStoreData petStoreData) {
        // Step 5a: Call findOrCreatePetStore
        PetStore petStore = findOrCreatePetStore(petStoreData.getId());

        // Step 5b: Call copyPetStoreFields
        copyPetStoreFields(petStore, petStoreData);

        // Step 5c: Call PetStoreDao method save
        PetStore savedPetStore = petStoreDao.save(petStore);

        return new PetStoreData(savedPetStore);
    }

    // Helper method to find or create pet store
    private PetStore findOrCreatePetStore(Long petStoreId) {
        if (petStoreId != null) {
            return petStoreDao.findById(petStoreId)
                    .orElseThrow(() -> new NoSuchElementException("Pet store not found with ID: " + petStoreId));
        } else {
            return new PetStore(); // Create new pet store
        }
    }

    // Helper method to copy fields from PetStoreData to PetStore
    private void copyPetStoreFields(PetStore petStore, PetStoreData petStoreData) {
        petStore.setPetStoreName(petStoreData.getPetStoreName());
        petStore.setPetStoreAddress(petStoreData.getPetStoreAddress());
        petStore.setPetStoreCity(petStoreData.getPetStoreCity());
        petStore.setPetStoreState(petStoreData.getPetStoreState());
        petStore.setPetStoreZip(petStoreData.getPetStoreZip());
        petStore.setPetStorePhone(petStoreData.getPetStorePhone());
        // You can copy other fields as needed
    }

    // Method to get pet store by ID
    public PetStoreData getPetStoreById(Long id) {
        PetStore petStore = petStoreDao.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pet store not found with ID: " + id));
        return new PetStoreData(petStore);
    }
}
