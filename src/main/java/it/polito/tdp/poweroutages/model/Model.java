package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	private PowerOutageDAO podao;
	private List<PowerOutage> worstCase;
	private List<PowerOutage> allPowerOutagesSameNerc;
	private int maxPersoneCoinvolte;
	
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	public List<PowerOutage> getWorstCaseAnalysis(Nerc nerc, int maxOre, int maxAnni) {
		allPowerOutagesSameNerc = podao.getPowerOutagesListByNerc(nerc);
		List<PowerOutage> parziale = new ArrayList<PowerOutage>();
		worstCase = new ArrayList<PowerOutage>();
		maxPersoneCoinvolte = 0;
		cerca(parziale, 0, maxOre, maxAnni);
		return worstCase;
	}

	private void cerca(List<PowerOutage> parziale, int livello, int durataComplessivaMassima, int numeroAnniMassimo) {
		
			int personeCoinvolte = this.calcolaPersoneCoinvolte(parziale);
			if (personeCoinvolte > maxPersoneCoinvolte) {
				maxPersoneCoinvolte = personeCoinvolte;
				worstCase = new ArrayList<PowerOutage>(parziale);
			}
			
			if(livello == allPowerOutagesSameNerc.size())
				return;
			
			parziale.add(allPowerOutagesSameNerc.get(livello));
			if (controlloMaxAnni(parziale, numeroAnniMassimo) && (this.totaleOre(parziale) < durataComplessivaMassima)) {
				cerca(parziale, livello+1, durataComplessivaMassima, numeroAnniMassimo);
			}
			parziale.remove(allPowerOutagesSameNerc.get(livello));
			cerca(parziale, livello+1, durataComplessivaMassima, numeroAnniMassimo);
		
	}
	
	public long totaleOre(List<PowerOutage> elenco) {
		long somma = 0;
		for (PowerOutage p : elenco) {
			somma += p.getHours();
		}
		return somma;
	}
	
	public int calcolaPersoneCoinvolte(List<PowerOutage> elenco) {
		int somma = 0;
		for (PowerOutage p : elenco) {
			somma += p.getCustomerAffected();
		}
		return somma;		
	}
	
	private boolean controlloMaxAnni(List<PowerOutage> elenco, int maxAnni) {
		
		if (elenco.size() < 2) {
			return true;
		}
		
		int primoAnno = elenco.get(0).getYear();
		int ultimoAnno = elenco.get(elenco.size()-1).getYear();
		
		if ((ultimoAnno - primoAnno + 1) > maxAnni) {
			return false;
		} else {
			return true;
		}
		
	}
	

}
