package com.example.lab07_20206438.Repository;


import com.example.lab07_20206438.Entity.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico,Integer> {


}
