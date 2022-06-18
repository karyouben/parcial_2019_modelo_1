package fp.ofertaEmpleo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fp.utiles.Checkers;

public class OfertasEmpleo {
	private List<OfertaEmpleo> ofertas;
	
	//Constructores
	
	public OfertasEmpleo() {
		ofertas=new ArrayList<>();
	}
	
	public OfertasEmpleo(Stream<OfertaEmpleo> ofertas) {
		this.ofertas=ofertas.collect(Collectors.toList());
	}
	
	public OfertasEmpleo(List<OfertaEmpleo> ofertas) {
		this.ofertas=new ArrayList<OfertaEmpleo>();
	}
	
	public OfertasEmpleo(Collection<OfertaEmpleo> ofertas) {
		this.ofertas=new ArrayList<OfertaEmpleo>();
	}
	
	public Integer numOfertasEmpleo() {
		return ofertas.size();
	}

	@Override
	public int hashCode() {
		return Objects.hash(ofertas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OfertasEmpleo other = (OfertasEmpleo) obj;
		return Objects.equals(ofertas, other.ofertas);
	}

	@Override
	public String toString() {
		return "OfertasEmpleo [ofertas=" + ofertas + "]";
	}
	
	//ejercicio 1
	public Boolean hayAlgunaOfertaConPorcentajePlazasDiscapacidadMayorA(Double umbral) {
		Checkers.check("El umbral debe estar en el intervalo [0,100]", umbral>=0 && umbral<=100);
		return ofertas.stream()
				.anyMatch(o->o.porcentajePlazasDisc()>=umbral);
	}
	
	//ejercicio 2
	public Integer getNumeroEspecialidadesFacultativosEspecialistas() {
		return ofertas.stream()
				.filter(o->o.getEspecialidad().toUpperCase().startsWith("FEA"))
				.map(OfertaEmpleo::getEspecialidad)
				.distinct()
				.collect(Collectors.collectingAndThen(Collectors.counting(), o->o.intValue()));
		
	}
	
	//ejercicio 3
	
	public Integer getTotalPlazas(String especialidad, Integer anyo) {
		return ofertas.stream()
				.filter(o->o.getEspecialidad().equals(especialidad) && o.getFecha().getYear()==anyo)
				.mapToInt(OfertaEmpleo::numPlazasTotal)
				.sum();
		
	}
	
	//ejercicio 4
	
	public SortedMap<Integer, Integer> getTotalPlazasTipo(TipoAcceso tipo){
		return ofertas.stream()
				.filter(o->o.getTipo().equals(tipo))
				.collect(Collectors.groupingBy(o->o.getFecha().getYear(),TreeMap::new ,Collectors.summingInt(OfertaEmpleo::numPlazasTotal)));
		
	}
	
	//ejercicio 5
	public String getEspecialidadMasOfertada() {
		Map<String,Integer> m=NumPlazasPorEspecialidad();
		Comparator<Map.Entry<String, Integer>> c=Comparator.comparing(Map.Entry::getValue);
		return m.entrySet().stream()
				.max(c)
				.get()
				.getKey();
	}
	
	public Map<String,Integer> NumPlazasPorEspecialidad(){
		return ofertas.stream()
				.collect(Collectors.groupingBy(OfertaEmpleo::getEspecialidad,Collectors.summingInt(OfertaEmpleo::numPlazasTotal)));
	}
	
	//ejercicio 6
	public Map<Integer,List<String>> getNEspecialidadesMasPlazasPorAnyo(Integer n){
		return ofertas.stream()
				.collect(Collectors.groupingBy(c->c.getFecha().getYear(),Collectors.collectingAndThen(Collectors.toList(), lista->obtieneLista(lista,n))));
	}

	private List<String> obtieneLista(List<OfertaEmpleo> lista,Integer n) {
		return lista.stream()
				.sorted(Comparator.comparing(OfertaEmpleo::numPlazasTotal).reversed())
				.map(OfertaEmpleo::getEspecialidad)
				.limit(n)
				.collect(Collectors.toList());
				
	}
	
	
	
	


}
