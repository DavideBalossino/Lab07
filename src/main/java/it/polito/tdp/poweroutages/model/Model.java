package it.polito.tdp.poweroutages.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	int mass;
	long diss;
	List<Guasti> result;

	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	public List<Guasti> worstCase(Nerc nerc, int x, int y) {
		List<Guasti> lguasti=new ArrayList<Guasti>();
		lguasti=podao.listaGuastiPerNerc(nerc);
		mass=0;
		diss=0;
		
		//result=new ArrayList<Guasti>();
		List<Guasti> parziale=new ArrayList<Guasti>();
		faiRicorsione(lguasti,0,y*3600,x,0,0,parziale,0);
	//	System.out.println(mass);
	//	System.out.println(result.size());
		return result;
	}
	
	public Integer totPersone() {
		return mass;
	}
	
	public long totDiss() {
		return diss/3600;
	}
	
	private void faiRicorsione(List<Guasti> lguasti, long totDiss,int y, int x, int min, int max, List<Guasti> parziale, int persone) {
		if(persone>mass) {
			mass=persone;
			diss=totDiss;
			result=new ArrayList<Guasti>(parziale);
		}
		
		for(Guasti g: lguasti) {
			if(!parziale.contains(g)) {
			if(g.oreDisservizio()+totDiss<=y) {
				if(parziale.size()==0 || g.getInizio().getYear()<min) {
					if(max-g.getInizio().getYear()<=x) {
					//min=g.getInizio().getYear();
					parziale.add(g);
					faiRicorsione(lguasti,totDiss+g.oreDisservizio(),y,x,g.getInizio().getYear(),max,parziale,persone+g.getPersoneAffette());
					parziale.remove(parziale.size()-1);
					}
					
				}
				else {
				if(g.getInizio().getYear()>max) {
					if(g.getInizio().getYear()-min<=x) {
					//min=g.getInizio().getYear();
					parziale.add(g);
					faiRicorsione(lguasti,totDiss+g.oreDisservizio(),y,x,min,g.getInizio().getYear(),parziale,persone+g.getPersoneAffette());
					parziale.remove(parziale.size()-1);
					}
				}
				
				else {
					parziale.add(g);
					faiRicorsione(lguasti,totDiss+g.oreDisservizio(),y,x,min,max,parziale,persone+g.getPersoneAffette());
					parziale.remove(parziale.size()-1);
				}
			  }
				
			}
			
			}
		}
	}
	
/*	public Integer differenzaAnni(LocalDate anno1, LocalDate anno2) {
		int diff=anno1.getYear()-anno2.getYear();
		if(anno1.getMonthValue()<anno2.getMonthValue())
			diff--;
		else {
			if(anno1.getMonthValue()==anno2.getMonthValue() && anno1.getDayOfMonth()<anno2.getDayOfMonth())
				diff--;
		}
		return diff;
	}
	*/
	public long oreDisservizio(LocalDateTime data1, LocalDateTime data2) {
		Duration tempo=Duration.between(data1, data2);
		return tempo.toHoursPart();
	}

}
