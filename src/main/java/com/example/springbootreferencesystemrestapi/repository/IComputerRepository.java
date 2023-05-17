package com.example.springbootreferencesystemrestapi.repository;

import com.example.springbootreferencesystemrestapi.models.Computer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IComputerRepository extends JpaRepository<Computer, Integer> {
}
