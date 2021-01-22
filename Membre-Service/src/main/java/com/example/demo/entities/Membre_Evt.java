package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Membre_Evt {
    @EmbeddedId
    private Membre_Evt_Ids id;
    @ManyToOne
    @MapsId("member_id")
    private Membre member;
}
