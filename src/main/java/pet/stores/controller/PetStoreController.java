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
    
    @PostMapping("/customers")
    @ResponseStatus(code = HttpStatus.CREATED)
    public PetStoreCustomer insertCustomer(@RequestBody PetStoreCustomer petStoreCustomer) {
        log.info("Creating Customer {}", petStoreCustomer);
        return petStoreService.saveCustomer(petStoreCustomer);
    }

    @PutMapping("/customers/{customerId}")
    public PetStoreCustomer updateCustomer(@PathVariable Long customerId, @RequestBody PetStoreCustomer petStoreCustomer) {
        petStoreCustomer.setCustomer_id(customerId);
        log.info("Updating Customer {}", petStoreCustomer);
        return petStoreService.saveCustomer(petStoreCustomer);
    }

    @GetMapping("/customers")
    public List<PetStoreCustomer> retrieveAllCustomers() {
        log.info("Retrieving all customers");
        return petStoreService.retrieveAllCustomers();
    }

    @GetMapping("/customers/{customerId}")
    public PetStoreCustomer retrieveCustomerById(@PathVariable Long customerId) {
        log.info("Retrieving customer with ID={}", customerId);
        return petStoreService.retrieveCustomerById(customerId);
    }

    @DeleteMapping("/customers/{customerId}")
    public Map<String, String> deleteCustomerById(@PathVariable Long customerId) {
        log.info("Deleting customer with ID={}", customerId);
        petStoreService.deleteCustomerById(customerId);
        return Map.of("message", "Deletion of customer with ID=" + customerId + " was successful.");
    }
    
    @PostMapping("/employees")
    @ResponseStatus(code = HttpStatus.CREATED)
    public PetStoreEmployee insertEmployee(@RequestBody PetStoreEmployee petStoreEmployee) {
        log.info("Creating Employee {}", petStoreEmployee);
        return petStoreService.saveEmployee(petStoreEmployee);
    }

    @PutMapping("/employees/{employeeId}")
    public PetStoreEmployee updateEmployee(@PathVariable Long employeeId, @RequestBody PetStoreEmployee petStoreEmployee) {
        petStoreEmployee.setEmployeeId(employeeId);
        log.info("Updating Employee {}", petStoreEmployee);
        return petStoreService.saveEmployee(petStoreEmployee);
    }

    @GetMapping("/employees")
    public List<PetStoreEmployee> retrieveAllEmployees() {
        log.info("Retrieving all employees");
        return petStoreService.retrieveAllEmployees();
    }

    @GetMapping("/employees/{employeeId}")
    public PetStoreEmployee retrieveEmployeeById(@PathVariable Long employeeId) {
        log.info("Retrieving employee with ID={}", employeeId);
        return petStoreService.retrieveEmployeeById(employeeId);
    }

    @DeleteMapping("/employees/{employeeId}")
    public Map<String, String> deleteEmployeeById(@PathVariable Long employeeId) {
        log.info("Deleting employee with ID={}", employeeId);
        petStoreService.deleteEmployeeById(employeeId);
        return Map.of("message", "Deletion of employee with ID=" + employeeId + " was successful.");
    }    
}
