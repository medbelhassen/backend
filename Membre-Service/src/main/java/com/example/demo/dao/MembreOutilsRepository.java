package com.example.demo.dao;

import com.example.demo.entities.Membre_Outil;
import com.example.demo.entities.Membre_Outil_Ids;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MembreOutilsRepository extends JpaRepository<Membre_Outil, Membre_Outil_Ids> {
    @Query("select m from Membre_Outil m where developper_id=:x")
    List<Membre_Outil> findOutilsById(@Param("x") Long developperId);
}
