package com.example.springbootreferencesystemrestapi.repository;

import com.example.springbootreferencesystemrestapi.models.Component;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IComponentRepository extends JpaRepository<Component, Integer> {
    List<Component> findByComputerId(int computerId);
    List<Component> findByType(String type);
    List<Component> findByBrand(String brand);
    List<Component> findByInventoryNumber(String inventoryNumber);
    List<Component> findByIsFree(boolean isFree);

}
