package pet.stores.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pet.stores.controller.model.PetStoreData;
import pet.stores.service.PetStoreService;

@RestController
@RequestMapping("/pet_stores")
public class PetStoreController {

    private static final Logger log = LoggerFactory.getLogger(PetStoreController.class);

    private final PetStoreService petStoreService;

    @Autowired
    public PetStoreController(PetStoreService petStoreService) {
        this.petStoreService = petStoreService;
    }

    @PostMapping
    public PetStoreData createPetStore(@RequestBody PetStoreData petStoreData) {
        log.info("Received request to create pet store: {}", petStoreData);
        return petStoreService.savePetStore(petStoreData);
    }
    
    @PutMapping("/{id}")
    public PetStoreData updatePetStore(@PathVariable Long id, @RequestBody PetStoreData petStoreData) {
        log.info("Received request to update pet store with ID {}: {}", id, petStoreData);
        petStoreData.setId(id); // Set pet store ID
        return petStoreService.savePetStore(petStoreData);
    }
    
    @GetMapping("/{id}")
    public PetStoreData getPetStore(@PathVariable Long id) {
        log.info("Received request to retrieve pet store with ID {}", id);
        return petStoreService.getPetStoreById(id);
    }
}