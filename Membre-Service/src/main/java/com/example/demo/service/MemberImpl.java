package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.EvenementBean;
import com.example.demo.OutilBean;
import com.example.demo.dao.*;
import com.example.demo.entities.*;
import com.example.demo.proxies.EvenementProxy;
import com.example.demo.proxies.OutilProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.PublicationBean;
import com.example.demo.proxies.PublicationProxy;
@Service
public class MemberImpl implements IMemberService {

	@Autowired
	MemberRepository memberRepository;
	@Autowired
	EtudiantRepository etudiantRepository;
	@Autowired
	EnseignantChercheurRepository enseignantChercheurRepository;
	@Autowired
	Membrepubrepository membrepubrepository;
	@Autowired
	PublicationProxy proxy;
	@Autowired
	OutilProxy outilProxy;
	@Autowired
	MembreOutilsRepository membreOutilsRepository;
	@Autowired
	MembreEvenementRepository membreEvenementRepository;
	@Autowired
	EvenementProxy evenementProxy;
	public Membre addMember(Membre m) {
		memberRepository.save(m);
		return m;
	}

	
	public void deleteMember(Long id) {
		
		memberRepository.deleteById(id);

	}
	public Membre updateMember(Membre m) {
		
		return memberRepository.saveAndFlush(m);
	}
	public Membre findMember(Long id) {
	Membre m= (Membre)memberRepository.findById(id).get();
		return m;
	}
	public List<Membre> findAll() {
		
		return memberRepository.findAll();
	}

	public Membre findByCin(String cin) {
		return memberRepository.findByCin(cin);
	}
	public Membre findByEmail(String email) {
		return memberRepository.findByEmail(email);
	}
	public List<Membre> findByNom(String nom) {
		return memberRepository.findByNom(nom);
	}
	public List<Etudiant> findByDiplome(String diplome) {
		return etudiantRepository.findByDiplome(diplome);
	}
	public List<EnseignantChercheur> findByGrade(String grade) {
		
		return enseignantChercheurRepository.findByGrade(grade);
	}
	public List<EnseignantChercheur> findByEtablissement(String etablissement) {
	
		return enseignantChercheurRepository.findByEtablissement(etablissement);
	}
	public List<Etudiant> findAllEtudiants() {
		return etudiantRepository.findAll();
	}
	public List<EnseignantChercheur> findAllEnseignants() {
		return enseignantChercheurRepository.findAll();
	}


	@Override
	public Etudiant affecterencadrantToetudiant(Long idetd, Long idens) {
		// TODO Auto-generated method stub
		Etudiant etd= etudiantRepository.findById(idetd).get();
		EnseignantChercheur ens= enseignantChercheurRepository.findById(idens).get();
		etd.setEncadrant(ens);

		return etudiantRepository.save(etd);
	}


	@Override
	public void affecterauteurTopublication(Long idauteur, Long idpub) {
		Membre mbr= memberRepository.findById(idauteur).get();
		Membre_Publication mbs= new Membre_Publication();
		mbs.setAuteur(mbr);
		mbs.setId(new Membre_Pub_Ids(idpub, idauteur));
		membrepubrepository.save(mbs);
	}

	@Override
	public void affecterdevelopperTooutils(Long idDevelopper, Long idOutils) {
		Membre mbr= memberRepository.findById(idDevelopper).get();
		Membre_Outil mbs=new Membre_Outil();
		mbs.setDevelopper(mbr);
		mbs.setId(new Membre_Outil_Ids(idOutils,idDevelopper));
		membreOutilsRepository.save(mbs);
	}

	@Override
	public void affecterevenementTomembre(Long idMember, Long idEvt) {
		Membre mbr= memberRepository.findById(idMember).get();
		Membre_Evt mbs=new Membre_Evt();
		mbs.setMember(mbr);
		mbs.setId(new Membre_Evt_Ids(idEvt,idMember));
		membreEvenementRepository.save(mbs);
	}



	@Override
	public List<PublicationBean> findPublicationparauteur(Long idauteur) {
		List<PublicationBean> pubs=new ArrayList<PublicationBean>();
	
		List< Membre_Publication> idpubs=membrepubrepository.findpubId(idauteur);
		
		idpubs.forEach(s->{
			System.out.println(s);
			pubs.add(proxy.recupererUnePublication(s.getId().getPublication_id()).getContent());
			
		}
		);
		
		return pubs;
	}

	@Override
	public List<OutilBean> findOutilpardevelopper(Long idDevelopper) {
		List<OutilBean> outs=new ArrayList<OutilBean>();

		List<Membre_Outil> idouts=membreOutilsRepository.findOutilsById(idDevelopper);

		idouts.forEach(s->{
					System.out.println(s);
					outs.add(outilProxy.recupererUnOutil(s.getId().getOutil_id()).getContent());
				}
		);

		return outs;
	}

	@Override
	public List<EvenementBean> findevtparmembre(Long idMembre) {
		List<EvenementBean> outs=new ArrayList<EvenementBean>();

		List<Membre_Evt> idouts=membreEvenementRepository.findEvenementById(idMembre);

		idouts.forEach(s->{
					System.out.println(s);
					outs.add(evenementProxy.recupererUnEvenement(s.getId().getEvenement_id()).getContent());
				}
		);

		return outs;
	}


}
