package pet.stores.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pet.stores.controller.model.PetStoreCustomer;
import pet.stores.controller.model.PetStoreData;
import pet.stores.controller.model.PetStoreEmployee;
import pet.stores.service.PetStoreService;

@RestController
@RequestMapping("/pet_stores")
@Slf4j
public class PetStoreController {

    @Autowired
    private PetStoreService petStoreService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public PetStoreData insertPetStore(@RequestBody PetStoreData petStoreData) {
        log.info("Creating Pet Store {}", petStoreData);
        return petStoreService.savePetStore(petStoreData);
    }

    @PutMapping("/{petStoreId}")
    public PetStoreData updatePetStore(@PathVariable Long petStoreId, @RequestBody PetStoreData petStoreData) {
        petStoreData.setPetStoreId(petStoreId);
        log.info("Updating Pet Store {}", petStoreData);
        return petStoreService.savePetStore(petStoreData);
    }

    @GetMapping
    public List<PetStoreData> retrieveAllPetStores() {
        log.info("Retrieving all pet stores");
        return petStoreService.retrieveAllPetStores();
    }

    @GetMapping("/{petStoreId}")
    public PetStoreData retrievePetStoreById(@PathVariable Long petStoreId) {
        log.info("Retrieving pet store with ID={}", petStoreId);
        return petStoreService.retrievePetStoreById(petStoreId);
    }

    @DeleteMapping("/{petStoreId}")
    public Map<String, String> deletePetStoreById(@PathVariable Long petStoreId) {
        log.info("Deleting pet store with ID={}", petStoreId);
        petStoreService.deletePetStoreById(petStoreId);
        return Map.of("message", "Deletion of pet store with ID=" + petStoreId + " was successful.");
    }
    
    @PostMapping("/{petStoreId}/customer")
    @ResponseStatus(code = HttpStatus.CREATED)
    public PetStoreCustomer insertCustomer(@PathVariable Long petStoreId, @RequestBody PetStoreCustomer petStoreCustomer) {
        log.info("Adding Customer {} to Pet Store with ID={}", petStoreCustomer, petStoreId);
        return petStoreService.saveCustomer(petStoreId, petStoreCustomer);
    }

    @PutMapping("/{petStoreId}/customer/{customerId}")
    public PetStoreCustomer updateCustomer(@PathVariable Long petStoreId, @PathVariable Long customerId, @RequestBody PetStoreCustomer petStoreCustomer) {
        petStoreCustomer.setCustomer_id(customerId);
        log.info("Updating customer with ID={} from Pet Store with ID={}", customerId, petStoreId);
        return petStoreService.saveCustomer(petStoreId, petStoreCustomer);
    }

    @GetMapping("/{petStoreId}/customer")
    public List<PetStoreCustomer> retrieveAllCustomers(@PathVariable Long petStoreId) {
        log.info("Retrieving all customersof Pet Store with ID={}", petStoreId);
        return petStoreService.retrieveAllCustomers();
    }

    @GetMapping("/{petStoreId}/customer/{customerId}")
    public PetStoreCustomer retrieveCustomerById(@PathVariable Long petStoreId, @PathVariable Long customerId) {
        log.info("Retrieving customer with ID={} from Pet Store with ID={}", customerId, petStoreId);
        return petStoreService.retrieveCustomerById(customerId);
    }

    @DeleteMapping("/{petStoreId}/customer/{customerId}")
    public Map<String, String> deleteCustomerById(@PathVariable Long petStoreId, @PathVariable Long customerId) {
        log.info("Deleting customer with ID={}", customerId);
        petStoreService.deleteCustomerById(customerId);
        return Map.of("message", "Deletion of customer with ID=" + customerId + " was successful.");
    }
    
    @PostMapping("/{petStoreId}/employee")
    @ResponseStatus(code = HttpStatus.CREATED)
    public PetStoreEmployee insertEmployee(@PathVariable Long petStoreId, @RequestBody PetStoreEmployee petStoreEmployee) {
        log.info("Adding Employee {} to Pet Store with ID={}", petStoreEmployee, petStoreId);
        return petStoreService.saveEmployee(petStoreId, petStoreEmployee);
    }

    @PutMapping("/{petStoreId}/employee/{employeeId}")
    public PetStoreEmployee updateEmployee(@PathVariable Long petStoreId, @PathVariable Long employeeId, 
    		@RequestBody PetStoreEmployee petStoreEmployee) {
        petStoreEmployee.setEmployeeId(employeeId);
        log.info("Retrieving employee with ID={} from Pet Store with ID={}", employeeId, petStoreId);
        return petStoreService.saveEmployee(petStoreId, petStoreEmployee);
    }

    @GetMapping("/{petStoreId}/employee")
    public List<PetStoreEmployee> retrieveAllEmployees(@PathVariable Long petStoreId) {
        log.info("Retrieving all employees of Pet Store with ID={}", petStoreId);
        return petStoreService.retrieveAllEmployees();
    }

    @GetMapping("/{petStoreId}/employee/{employeeId}")
    public PetStoreEmployee retrieveEmployeeById(@PathVariable Long petStoreId, @PathVariable Long employeeId) {
        log.info("Retrieving employee with ID={} from Pet Store with ID={}", employeeId, petStoreId);
        return petStoreService.retrieveEmployeeById(employeeId);
    }

    @DeleteMapping("/{petStoreId}/employee/{employeeId}")
    public Map<String, String> deleteEmployeeById(@PathVariable Long petStoreId, @PathVariable Long employeeId) {
        log.info("Deleting employee with ID={} from Pet Store with ID={}", employeeId, petStoreId);
        petStoreService.deleteEmployeeById(employeeId);
        return Map.of("message", "Deletion of employee with ID=" + employeeId + " was successful.");
    }    
}
