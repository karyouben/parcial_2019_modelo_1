package fp.testOfertaEmpleo;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import fp.ofertaEmpleo.FactoriaOfertasEmpleo;
import fp.ofertaEmpleo.OfertaEmpleo;
import fp.ofertaEmpleo.OfertasEmpleo;
import fp.ofertaEmpleo.TipoAcceso;

public class TestOfertasEmpleo {

	public static void main(String[] args) {
		List<OfertaEmpleo> ofertas=FactoriaOfertasEmpleo.leeOfertas("data/ofertas.csv");
		OfertasEmpleo o= new OfertasEmpleo(ofertas);
		System.out.println("\nTestHayAlgunaOfertaConPorcentajePlazasDiscapacidadMayorA");
		System.out.println("==========================================================");
		testHayAlgunaOfertaConPorcentajePlazasDiscapacidadMayorA(o,30.4);
		System.out.println("\nTestGetNumeroEspecialidadesFacultativosEspecialistas");
		System.out.println("======================================================");
		testGetNumeroEspecialidadesFacultativosEspecialistas(o);
		System.out.println("\nTestGetTotalPlazas");
		System.out.println("====================");
		testGetTotalPlazas(o,"unicornio", 2006);
		System.out.println("\nTestGetTotalPlazasTipo");
		System.out.println("========================");
		testGetTotalPlazasTipo(o,TipoAcceso.ACCESO_LIBRE);
		System.out.println("\nTestGetEspecialidadMasOfertada");
		System.out.println("================================");
		testGetEspecialidadMasOfertada(o);
		System.out.println("\nTestGetNEspecialidadesMasPlazasPorAnyo");
		System.out.println("========================================");
		testGetNEspecialidadesMasPlazasPorAnyo(o,3);
		
	}


	private static void testHayAlgunaOfertaConPorcentajePlazasDiscapacidadMayorA(OfertasEmpleo o, Double umbral) {
		try {
			String msg=String.format("¿Hay alguna oferta con un porcentaje de plazas de dicapacidad mayor que %d?  %s",
					umbral,o.hayAlgunaOfertaConPorcentajePlazasDiscapacidadMayorA(umbral));
			System.out.println(msg);
		}catch(Exception e) {
			System.err.println("Excepcion capturada inesesperada" + e.getMessage());
		}
		
	}
	
	private static void testGetNumeroEspecialidadesFacultativosEspecialistas(OfertasEmpleo o) {
		try {
			String msg=String.format("El numero de especialidades Facultativos especialistas son:  %d",
					o.getNumeroEspecialidadesFacultativosEspecialistas());
			System.out.println(msg);
		}catch(Exception e) {
			System.err.println("Excepcion capturada inesesperada" + e.getMessage());
		}
	}
	
	private static void testGetTotalPlazas(OfertasEmpleo o, String especialidad, Integer anyo) {
		try {
			String msg=String.format("El numero total de plazas de la especialidad %d en el año "+anyo+ " es:  %s",
					especialidad,anyo,o.getTotalPlazas(especialidad, anyo));
			System.out.println(msg);
		}catch(Exception e) {
			System.err.println("Excepcion capturada inesesperada" + e.getMessage());
		}
		
	}
	
	private static void testGetTotalPlazasTipo(OfertasEmpleo o, TipoAcceso tipo) {
		try {
			String msg=String.format("El numero total de plazas del tipo " +tipo+ " por año es: ");
			System.out.println(msg);
			SortedMap<Integer, Integer> p=o.getTotalPlazasTipo(tipo);
			p.entrySet().stream().forEach(System.out::println);
		}catch(Exception e) {
			System.err.println("Excepcion capturada inesesperada" + e.getMessage());
		}
	}
	

	private static void testGetEspecialidadMasOfertada(OfertasEmpleo o) {
		try {
			String msg=String.format("La especialidad mas ofertada es: %d",
					o.getEspecialidadMasOfertada());
			System.out.println(msg);
		}catch(Exception e) {
			System.err.println("Excepcion capturada inesesperada" + e.getMessage());
		}
	}
	

	private static void testGetNEspecialidadesMasPlazasPorAnyo(OfertasEmpleo o, Integer n) {
		try {
			String msg=String.format("Las " +n+ " especialidades con mas plazas por año es: ");
			System.out.println(msg);
			 Map<Integer,List<String>> p=o.getNEspecialidadesMasPlazasPorAnyo(n);
			p.entrySet().stream().forEach(System.out::println);
		}catch(Exception e) {
			System.err.println("Excepcion capturada inesesperada" + e.getMessage());
		}	
	}
	

}
