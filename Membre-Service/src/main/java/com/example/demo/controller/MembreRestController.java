package com.example.demo.controller;

import java.util.List;


import com.example.demo.EvenementBean;
import com.example.demo.OutilBean;
import com.example.demo.proxies.EvenementProxy;
import com.example.demo.proxies.OutilProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import com.example.demo.PublicationBean;
import com.example.demo.entities.EnseignantChercheur;
import com.example.demo.entities.Etudiant;
import com.example.demo.entities.Membre;
import com.example.demo.proxies.PublicationProxy;
import com.example.demo.service.IMemberService;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MembreRestController {
	@Autowired
	IMemberService iMemberService;
	@Autowired
	PublicationProxy publicationproxy;
	@Autowired
	OutilProxy outilProxy;
	@Autowired
	EvenementProxy evenementProxy;
	@GetMapping(value = "/membres")
	public List<Membre> findAllmembres()
	{
		return iMemberService.findAll();
	}

	@GetMapping(value = "/membres/{id}")
	public Membre findoneMembre(@PathVariable Long id)
	{
		return iMemberService.findMember(id);
	}
	
	@PostMapping(value = "/membres/etudiant")
	public Membre addMembre(@RequestBody Etudiant etd)
	{
		return iMemberService.addMember(etd);
	}

	@PostMapping(value = "/membres/enseignant")
	public Membre addMembre(@RequestBody EnseignantChercheur ens)
	{
		return iMemberService.addMember(ens);
	}
	@DeleteMapping(value = "/member/delete/{id}")
	public void deleteMembre(@PathVariable Long id){
		iMemberService.deleteMember(id);
	}

	@PutMapping(value="/membres/etudiant/{id}")
	public Membre updatemembre(@PathVariable Long id, @RequestBody Etudiant p)
	{
		p.setId(id);
		return iMemberService.updateMember(p);
	}

	@PutMapping(value="/membres/enseignant/{id}")
	public Membre updateMembre(@PathVariable Long id, @RequestBody EnseignantChercheur p)
	{
		p.setId(id);
	       return iMemberService.updateMember(p);
	}
	@PutMapping(value="/membres/etudiant")
	public Membre affecter(@RequestParam Long idetd , @RequestParam Long idens )
	{
		
	       return iMemberService.affecterencadrantToetudiant(idetd, idens);
	}
	@GetMapping("/publications")
	public CollectionModel<PublicationBean>listerpublication()
	{
		return publicationproxy.listeDesPublications();

	}
	@GetMapping("/publications/{id}")
	public EntityModel<PublicationBean> listerunepublication(@PathVariable Long id)
	{
		return publicationproxy.recupererUnePublication(id);
		
	}
	@GetMapping("/publications/auteur/{id}")
	public List<PublicationBean>listerpublicationbymembre(@PathVariable(name="id") Long idaut)
	{
		return iMemberService.findPublicationparauteur(idaut);		
	}
	
	@GetMapping("/fullmember/{id}")
	public Membre findAFullMember(@PathVariable(name="id") Long id)
	{
		Membre mbr=iMemberService.findMember(id);
		mbr.setPubs(iMemberService.findPublicationparauteur(id));
		return mbr;		
	}
	@GetMapping("/outils")
	public CollectionModel<OutilBean>listeroutils(){
		return outilProxy.listeDesOutils();
	}
	@GetMapping("outils/{id}")
	public EntityModel<OutilBean> listerunoutil(@PathVariable Long id){
		return outilProxy.recupererUnOutil(id);
	}
	@GetMapping("outils/developper/{id}")
	public List<OutilBean>listeroutilsbymembre(@PathVariable(name = "id") Long idDevelopper)
	{
		return iMemberService.findOutilpardevelopper(idDevelopper);
	}
	@GetMapping("/evenements")
	public CollectionModel<EvenementBean>listerevenements(){return evenementProxy.listeDesEvenements();}
	@GetMapping("evenements/{id}")
	public EntityModel<EvenementBean> listerunevenement(@PathVariable Long id){
		return evenementProxy.recupererUnEvenement(id);
	}
	@GetMapping("evenements/membre/{id}")
	public List<EvenementBean>listerevenementsbymembre(@PathVariable(name = "id") Long idMember)
	{
		return iMemberService.findevtparmembre(idMember);
	}
//	@PostMapping("publications/create/{id}")
//	public List<PublicationBean> affecterauteurtopublications(@PathVariable Long)
	
}
