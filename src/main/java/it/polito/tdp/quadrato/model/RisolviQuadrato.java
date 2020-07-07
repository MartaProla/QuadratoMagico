package it.polito.tdp.quadrato.model;

import java.util.ArrayList;
import java.util.List;

public class RisolviQuadrato {
	private int N ; // lato del quadrato
	private int N2 ; // numero di caselle (N^2)
	private int magica ; // costante magica
	
	private List<List<Integer>>soluzioni;
	
	
	
	public RisolviQuadrato(int N) {
		this.N=N; // n lato
		this.N2=N*N; // n*n numero celle
		this.magica= N*(N2+1)/2;
	}
	
	public List<List<Integer>> quadrati() {
		List<Integer>parziale=new ArrayList<>();
		int livello=0;
		this.soluzioni=new ArrayList<List<Integer>>();
		cerca(parziale, livello);
		
		return this.soluzioni;
	}
	
	
	//struttura ricorsiva(privata)
	private void cerca(List<Integer>parziale, int livello) {
		if(livello==N2) {
			//caso terminale--> ho un quadrato non so se è quello ottimale
			
			if(controlla(parziale)) {
				System.out.println(parziale);
				//this.soluzioni.add(parziale);// attenzione non va bene --> parziale può cambiare 
				this.soluzioni.add(new ArrayList<>(parziale));
			}
			return;
		}
		
		//controlli intermedi, quando livello è multiplo di N(righe complete)
		
		if(livello%N==0 && livello!=0) {
			if(!controllaRiga(parziale,livello/N-1))
				return; //potatura dell'albero di ricerca--> non nasce nulla di buono
		}
		//caso intermedio
		
		for(int valore=1; valore<=N2;valore ++) {
			if(!parziale.contains(valore)) {
				//prova valore
				parziale.add(valore);
				cerca(parziale,livello+1);
				parziale.remove(parziale.size()-1);
			}
		}
		
	}
	
	
	/**
	 * Verifica se una soluzione rispetta tutte le somme
	 * @param parziale
	 * @return
	 */
	private boolean controlla(List<Integer> parziale) {
		if(parziale.size()!=this.N*this.N)
			throw new IllegalArgumentException("Numero di elementi insufficiente") ;
		
		// Fai la somma delle righe
		for(int riga=0; riga<this.N; riga++) {
			int somma = 0 ;
			for(int col=0; col<this.N; col++) {
				somma += parziale.get(riga*this.N+col) ;
			}
			if(somma!=this.magica)
				return false ;
		}
		
		// Fai la somma delle colonne
		for(int col=0; col<this.N; col++) {
			int somma = 0 ;
			for(int riga=0; riga<this.N; riga++) {
				somma += parziale.get(riga*this.N+col) ;
			}
			if(somma!=this.magica)
				return false ;
		}
		
		// diagonale principale
		int somma = 0;
		for(int riga=0; riga<this.N; riga++) {
			somma += parziale.get(riga*this.N+riga) ;
		}
		if(somma!=this.magica)
			return false ;
		
		// diagonale inversa
		somma = 0;
		for(int riga=0; riga<this.N; riga++) {
			somma += parziale.get(riga*this.N+(this.N-1-riga)) ;
		}
		if(somma!=this.magica)
			return false ;

		return true ;
	}
	
	private boolean controllaRiga(List<Integer>parziale, int riga) {
		int somma=0;
		for(int col=0;col<N;col++) 
			somma+=parziale.get(riga*N+col);
		return somma==magica; //return true
	}
}
