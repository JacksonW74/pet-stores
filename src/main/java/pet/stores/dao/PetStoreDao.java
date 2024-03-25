package pet.stores.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pet.stores.entity.PetStore;

public interface PetStoreDao extends JpaRepository<PetStore, Long> {
}
