package it.polito.tdp.poweroutages.model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	int x;
	int y;
	Nerc nerc = new Nerc(0, null);
	int maxPersoneCoinvolte;
	List<Event> risultatoMigliore;

	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	public List<Event> getAllEvents (Nerc nerc){
		return podao.getAllEvents(nerc);
	}
	
	public List<Event> risultato (){
		List<Event> parziale = new ArrayList<Event>();
		maxPersoneCoinvolte=0;
		cerca(x,y,parziale);
		return risultatoMigliore ;
	}


	
	public void cerca (int x, int y, List<Event> parziale) {
		if(contaPersoneCoinvolte(parziale) > maxPersoneCoinvolte) {
			risultatoMigliore = new ArrayList<Event>(parziale);
			maxPersoneCoinvolte = contaPersoneCoinvolte(risultatoMigliore);
			return;
		}
		
		else {
			for(int i=0; i<podao.getAllEvents(nerc).size(); i++) {
				if(!parziale.contains(podao.getEventList().get(i))) {
					if(isValida(parziale, x, y, podao.getEventList().get(i))==true) {
						parziale.add(podao.getEventList().get(i));
						cerca(x, y, parziale);
						parziale.remove(parziale.size()-1);
					}
				}
				
			}
		}
		
		
	}
	
	
	private int contaPersoneCoinvolte(List<Event> parziale) {
		int somma=0;
		
		for(Event e: parziale) {
			somma= somma + e.getNumeroPersoneCoinvolte();
		}
		return somma;
	}


	
	public boolean isValida(List<Event> parziale, int x, int y, Event evento) {
		double somma=0;
		boolean valido=false;
		
		parziale.add(evento);
		
		for(Event e : parziale) {
			somma = somma + e.getNumeroOre();
		}
		
		if(somma<=y && parziale.get(parziale.size()-1).getData_fine().getYear() - parziale.get(0).getData_fine().getYear()<= x) {
			valido=true;
		}
		
		parziale.remove(evento);
		
		return valido;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	

	public void setNerc(Nerc nerc) {
		this.nerc = nerc;
	}
}
