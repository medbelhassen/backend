package com.example.demo.dao;

import com.example.demo.entities.Membre_Evt;
import com.example.demo.entities.Membre_Evt_Ids;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MembreEvenementRepository extends JpaRepository<Membre_Evt, Membre_Evt_Ids> {
    @Query("select m from Membre_Evt m where member_id=:x")
    List<Membre_Evt> findEvenementById(@Param("x") Long memberId);
}
