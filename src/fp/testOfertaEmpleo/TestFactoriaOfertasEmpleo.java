package fp.testOfertaEmpleo;

import java.util.List;

import fp.ofertaEmpleo.FactoriaOfertasEmpleo;
import fp.ofertaEmpleo.OfertaEmpleo;

public class TestFactoriaOfertasEmpleo {

	public static void main(String[] args) {
		testLeeOfertas("data/Ofertas.csv");
	}

	private static void testLeeOfertas(String fichero) {
		System.out.println("\nTestLeeOfertas =============");
		List<OfertaEmpleo> ofertas=FactoriaOfertasEmpleo.leeOfertas(fichero);
		System.out.println(" OfertaEmpleo: ");
		for(OfertaEmpleo o:ofertas) {
			System.out.println(o);
		}
		
	}

}
