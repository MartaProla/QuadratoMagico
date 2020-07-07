package it.polito.tdp.quadrato.model;

import java.util.List;

public class TestRisolviQuadrato {

	public static void main(String args[]) {
		RisolviQuadrato r = new RisolviQuadrato(3) ;
		List<List<Integer>> soluzioni = r.quadrati() ;
		System.out.println("\n");
		for(List<Integer> sol: soluzioni)
			System.out.println(sol) ;
	}
	
}
