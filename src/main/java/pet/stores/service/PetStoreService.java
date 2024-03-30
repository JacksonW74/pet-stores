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
    public PetStore getPetStoreById(Long id) {
        return petStoreDao.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pet store not found with ID: " + id));
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
    public PetStoreCustomer saveCustomer(Long petStoreId, PetStoreCustomer petStoreCustomer) {
    	PetStore petStore = getPetStoreById(petStoreId);
        Long customerId = petStoreCustomer.getCustomerId();
        Customer customer = findOrCreateCustomer(petStoreId, customerId);

        copyCustomerFields(customer, petStoreCustomer);
        
        customer.getPetStores().add(petStore);
        petStore.getCustomers().add(customer);
        
        Customer dbcustomer = customerDao.save(customer);

        return new PetStoreCustomer(dbcustomer);
    }
    
    @Transactional
    private Customer findOrCreateCustomer(Long petStoreId, Long customerId) {
        if (customerId == null)
            return new Customer();
        else
            return findCustomerById(petStoreId, customerId);
    }

	private void copyCustomerFields(Customer customer, PetStoreCustomer petStoreCustomer) {
        customer.setCustomerFirstName(petStoreCustomer.getCustomerFirstName());
        customer.setCustomerLastName(petStoreCustomer.getCustomerLastName());
        customer.setCustomerEmail(petStoreCustomer.getCustomerEmail());
		
	}

    @Transactional
    private Customer findCustomerById(Long petStoreId, Long customerId) {
        Customer customer = customerDao.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        boolean found = false;
        
        for (PetStore petStore : customer.getPetStores()) {
        	if (petStore.getPetStoreId().equals(petStoreId)) {
        	found = true;
        	break;
        	}
        }
        if (!found) {
        	throw new IllegalArgumentException("Customer does not belong to pet store with ID: " + petStoreId);
        }
            return customer;            
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
    public PetStoreEmployee saveEmployee(Long petStoreId, PetStoreEmployee petStoreEmployee) {
        PetStore petStore = getPetStoreById(petStoreId);
        Long employeeId = petStoreEmployee.getEmployeeId();
        Employee employee = findOrCreateEmployee(petStoreId, employeeId);

        copyEmployeeFields(employee, petStoreEmployee);

        employee.setPetStore(petStore);
        petStore.getEmployees().add(employee);

        Employee dbEmployee = employeeDao.save(employee);
        return new PetStoreEmployee(dbEmployee);
    }

    @Transactional
    private Employee findOrCreateEmployee(Long petStoreId, Long employeeId) {
        if (employeeId == null)
            return new Employee();
        else
            return findEmployeeById(petStoreId, employeeId);
    }

    @Transactional
    private Employee findEmployeeById(Long petStoreId, Long employeeId) {
        Employee employee = employeeDao.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

        if (employee.getPetStore().getPetStoreId().equals(petStoreId))
            return employee;
        else
            throw new IllegalArgumentException("Employee does not belong to pet store with ID: " + petStoreId);
    }
    
//    @Transactional
//    public PetStoreEmployee saveEmployee(petStoreId, petStoreEmployee) {
//    	petStore = findPetStoreById(ID);
//    	employeeId = petStoreEmployee.getEmployeeId();
//    	employee = findOrCreateEmployee(petStoreId, employeeId);
//    	
//    	copyEmployeeFields(employee, petStoreEmployee);
//    	
//    	set petStore in employee;
//    	add employee to pet store list of employees;
//    	
//    	dbEmployee = save the employee(employeeDao.save);
//    			return new PetStoreEmployee(dbEmployee);
//    }
//    
//    private Employee findOrCreateEmployee(petStoreId, employeeId) {
//    	if employeeId is null
//    	return new Employee 
//    	else return findEmployeeById(petStoreId, employeeId);
//    }
//    
//    private Employee findEmployeeById(petStoreId, employeeId) {
//    	employee = employeeDao.findById(employeeId)
//    			.orElseThrow(msg);
//    	
//    	if employee petStoreId equals petStoreId;
//    		return new Employee;
//    		else throw IllegalArgumentException(msg);
//    }


    private void copyEmployeeFields(Employee employee, PetStoreEmployee petStoreEmployee) {
    	employee.setEmployeeId(petStoreEmployee.getEmployeeId());
        employee.setEmployeeFirstName(petStoreEmployee.getEmployeeFirstName());
        employee.setEmployeeLastName(petStoreEmployee.getEmployeeLastName());
        employee.setEmployeeEmail(petStoreEmployee.getEmployeeEmail());
        employee.setEmployeeJobTitle(petStoreEmployee.getEmployeeJobTitle());
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

