package com.example.evenementservice;

import com.example.evenementservice.dao.EvenementRepository;
import com.example.evenementservice.entity.Evenement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Date;

@SpringBootApplication
@EnableDiscoveryClient
public class EvenementServiceApplication implements CommandLineRunner {
    @Autowired
    EvenementRepository evenementRepository;
    @Autowired
    RepositoryRestConfiguration repositoryRestConfiguration;
    public static void main(String[] args) {
        SpringApplication.run(EvenementServiceApplication.class, args);
    }
    public void run(String... args) throws Exception {
        repositoryRestConfiguration.exposeIdsFor(Evenement.class);
        Evenement evt1 = new Evenement("Titre1",new Date(),"ENIS");
        Evenement evt2 = new Evenement("Titre2",new Date(),"ENIS");
        Evenement evt3 = new Evenement("Titre3",new Date(),"ENIS");
        evenementRepository.save(evt1);
        evenementRepository.save(evt2);
        evenementRepository.save(evt3);
    }

}
