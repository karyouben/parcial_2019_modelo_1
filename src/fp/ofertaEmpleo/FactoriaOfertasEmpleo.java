package fp.ofertaEmpleo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import fp.utiles.Checkers;
import fp.utiles.Ficheros;

public class FactoriaOfertasEmpleo {
	
	public static List<OfertaEmpleo> leeOfertas(String fichero){
		Checkers.checkNoNull(fichero);
		List<String> lineas=Ficheros.leeFichero("Error al leer el fichero", fichero);
		lineas.remove(0);
		
		List<OfertaEmpleo> res=new ArrayList<OfertaEmpleo>();
		for(String linea:lineas) {
			OfertaEmpleo o=parsearOferta(linea);
			res.add(o);
		}return res;
		
	}


	private static OfertaEmpleo parsearOferta(String linea) {
		Checkers.checkNoNull(linea);
		String[] trozos=linea.split(";");
		Checkers.check("Formato no valido", trozos.length==5);
		String especialidad=trozos[0].trim();
		Integer numPlazas=Integer.parseInt(trozos[1].trim());
		Integer numPlazasDisc=Integer.parseInt(trozos[2].trim());
		LocalDate fecha=LocalDate.parse(trozos[3].trim(),DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		TipoAcceso tipo=TipoAcceso.valueOf(trozos[4].trim().toUpperCase().trim().replaceAll(" ", "_"));
		return new OfertaEmpleo(especialidad, numPlazas, numPlazasDisc, fecha, tipo);
	}

}
