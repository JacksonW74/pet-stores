package pet.stores.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pet.stores.controller.model.PetStoreCustomer;
import pet.stores.controller.model.PetStoreData;
import pet.stores.controller.model.PetStoreEmployee;
import pet.stores.dao.CustomerDao;
import pet.stores.dao.EmployeeDao;
import pet.stores.dao.PetStoreDao;
import pet.stores.entity.Customer;
import pet.stores.entity.Employee;
import pet.stores.entity.PetStore;

@Service
public class PetStoreService {

    private final PetStoreDao petStoreDao;
    private final EmployeeDao employeeDao;
    private final CustomerDao customerDao;

    @Autowired
    public PetStoreService(PetStoreDao petStoreDao, EmployeeDao employeeDao, CustomerDao customerDao) {
        this.petStoreDao = petStoreDao;
        this.employeeDao = employeeDao;
        this.customerDao = customerDao;
    }

    @Transactional
    public PetStoreData savePetStore(PetStoreData petStoreData) {
        PetStore petStore = findOrCreatePetStore(petStoreData.getPetStoreId());
        copyPetStoreFields(petStore, petStoreData);
        PetStore savedPetStore = petStoreDao.save(petStore);
        return new PetStoreData(savedPetStore);
    }

    @Transactional
    public PetStoreData getPetStoreById(Long id) {
        PetStore petStore = petStoreDao.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pet store not found with ID: " + id));
        return new PetStoreData(petStore);
    }

    @Transactional
    public ResponseEntity<String> deletePetStoreById(Long id) {
        PetStore petStore = petStoreDao.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pet store not found with ID: " + id));
        petStoreDao.delete(petStore);
        return ResponseEntity.ok().body("Pet store with ID " + id + " deleted successfully.");
    }

    @Transactional
    public List<PetStoreData> retrieveAllPetStores() {
        List<PetStore> petStores = petStoreDao.findAll();
        return petStores.stream()
                .map(PetStoreData::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public PetStoreData retrievePetStoreById(Long petStoreId) {
        PetStore petStore = petStoreDao.findById(petStoreId)
                .orElseThrow(() -> new NoSuchElementException("Pet store not found with ID: " + petStoreId));
        return new PetStoreData(petStore);
    }
    
    @Transactional
    public PetStoreCustomer saveCustomer(PetStoreCustomer petStoreCustomer) {
        Customer customer = new Customer();
        customer.setCustomerFirstName(petStoreCustomer.getCustomerFirstName());
        customer.setCustomerLastName(petStoreCustomer.getCustomerLastName());
        customer.setCustomerEmail(petStoreCustomer.getCustomerEmail());

        // Save the customer
        customer = customerDao.save(customer);

        return new PetStoreCustomer(customer);
    }

    @Transactional
    public PetStoreCustomer updateCustomer(Long customerId, PetStoreCustomer updatedCustomer) {
        Customer customer = customerDao.findById(customerId)
                .orElseThrow(() -> new NoSuchElementException("Customer not found with ID: " + customerId));

        // Update customer fields
        customer.setCustomerFirstName(updatedCustomer.getCustomerFirstName());
        customer.setCustomerLastName(updatedCustomer.getCustomerLastName());
        customer.setCustomerEmail(updatedCustomer.getCustomerEmail());

        // Set the customer ID
        updatedCustomer.setCustomer_id(customerId);

        // Save the updated customer
        customer = customerDao.save(customer);

        // Return the updated customer
        return updatedCustomer;
    }


    @Transactional
    public ResponseEntity<String> deleteCustomerById(Long customerId) {
        Customer customer = customerDao.findById(customerId)
                .orElseThrow(() -> new NoSuchElementException("Customer not found with ID: " + customerId));

        customerDao.delete(customer);
        return ResponseEntity.ok().body("Customer with ID " + customerId + " deleted successfully.");
    }

    
    @Transactional
    public List<PetStoreCustomer> retrieveAllCustomers() {
        List<Customer> customers = customerDao.findAll();
        return customers.stream()
                .map(PetStoreCustomer::new)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public PetStoreCustomer retrieveCustomerById(Long customerId) {
        Customer customer = customerDao.findById(customerId)
                .orElseThrow(() -> new NoSuchElementException("Customer not found with ID: " + customerId));
        return new PetStoreCustomer(customer);
    }


    @Transactional
    public PetStoreEmployee saveEmployee(PetStoreEmployee petStoreEmployee) {
        Employee employee = new Employee();
        employee.setEmployeeFirstName(petStoreEmployee.getEmployeeFirstName());
        employee.setEmployeeLastName(petStoreEmployee.getEmployeeLastName());
        employee.setEmployeeEmail(petStoreEmployee.getEmployeeEmail());
        employee.setEmployeeJobTitle(petStoreEmployee.getEmployeeJobTitle());

        return new PetStoreEmployee(employeeDao.save(employee));
    }

    @Transactional(readOnly = false)
    public PetStoreEmployee updateEmployee(Long employeeId, PetStoreEmployee updatedEmployee) {
        Employee employee = employeeDao.findById(employeeId)
                .orElseThrow(() -> new NoSuchElementException("Employee not found with ID: " + employeeId));

        employee.setEmployeeFirstName(updatedEmployee.getEmployeeFirstName());
        employee.setEmployeeLastName(updatedEmployee.getEmployeeLastName());
        employee.setEmployeeEmail(updatedEmployee.getEmployeeEmail());
        employee.setEmployeeJobTitle(updatedEmployee.getEmployeeJobTitle());

        employee = employeeDao.save(employee);

        return new PetStoreEmployee(employee);
    }

    @Transactional
    public ResponseEntity<String> deleteEmployeeById(Long employeeId) {
        Employee employee = employeeDao.findById(employeeId)
                .orElseThrow(() -> new NoSuchElementException("Employee not found with ID: " + employeeId));

        employeeDao.delete(employee);
        return ResponseEntity.ok().body("Employee with ID " + employeeId + " deleted successfully.");
    }

    @Transactional
    public List<PetStoreEmployee> retrieveAllEmployees() {
        List<Employee> employees = employeeDao.findAll();
        return employees.stream()
                .map(PetStoreEmployee::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public PetStoreEmployee retrieveEmployeeById(Long employeeId) {
        Employee employee = employeeDao.findById(employeeId)
                .orElseThrow(() -> new NoSuchElementException("Employee not found with ID: " + employeeId));
        return new PetStoreEmployee(employee);
    }

    @Transactional
    public List<PetStoreData> getAllPetStores() {
        List<PetStore> petStores = petStoreDao.findAll();
        return petStores.stream()
                .map(PetStoreData::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public PetStoreEmployee getEmployeeById(Long employeeId) {
    	Employee employee = employeeDao.findById(employeeId)
    			.orElseThrow(() -> new NoSuchElementException("Employee not found with ID: " + employeeId));
        return new PetStoreEmployee(employee);
    }

    @Transactional
    public PetStoreCustomer getCustomerById(Long customerId) {
        Customer customer = customerDao.findById(customerId)
                .orElseThrow(() -> new NoSuchElementException("Customer not found with ID: " + customerId));
        return new PetStoreCustomer(customer);
    }

    private PetStore findOrCreatePetStore(Long petStoreId) {
        if (petStoreId != null) {
        return petStoreDao.findById(petStoreId)
               .orElseThrow(() -> new NoSuchElementException("Pet store not found with ID: " + petStoreId));
        } else {
               return new PetStore(); 
        }
    }

    private void copyPetStoreFields(PetStore petStore, PetStoreData petStoreData) {
    petStore.setPetStoreName(petStoreData.getPetStoreName());
    petStore.setPetStoreAddress(petStoreData.getPetStoreAddress());
    petStore.setPetStoreCity(petStoreData.getPetStoreCity());
    petStore.setPetStoreState(petStoreData.getPetStoreState());
    petStore.setPetStoreZip(petStoreData.getPetStoreZip());
    petStore.setPetStorePhone(petStoreData.getPetStorePhone());
    }
}

