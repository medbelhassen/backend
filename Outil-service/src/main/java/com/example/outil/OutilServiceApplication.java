package com.example.outil;

import com.example.outil.dao.OutilRepository;
import com.example.outil.entity.Outil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Date;

@SpringBootApplication
@EnableDiscoveryClient
public class OutilServiceApplication implements CommandLineRunner {

    @Autowired
    OutilRepository outilRepository;
    @Autowired
    RepositoryRestConfiguration repositoryRestConfiguration;
    public static void main(String[] args) {
        SpringApplication.run(OutilServiceApplication.class, args);
    }
    public void run(String... args) throws Exception {
        repositoryRestConfiguration.exposeIdsFor(Outil.class);
        Outil out1 = new Outil("Tkinter","Tri en utilisant interface graphique",new Date(),"ENIS");
        Outil out2 = new Outil("Suivi d'un ballon","En utilisant OpenCV on suit le chemin d'un ballon",new Date(),"ENIS1");
        Outil out3 = new Outil("Gestion labo","gestion d'un laboratoire de recherche",new Date(),"ENIS2");
        outilRepository.save(out1);
        outilRepository.save(out2);
        outilRepository.save(out3);
    }
}
