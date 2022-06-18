package fp.ofertaEmpleo;

import java.time.LocalDate;
import java.util.Objects;

import fp.utiles.Checkers;

public class OfertaEmpleo {
	
	private String especialidad;
	private Integer numPlazas;
	private Integer numPlazasDisc;
	private LocalDate fecha;
	private TipoAcceso tipo;
	
	
	public String getEspecialidad() {
		return especialidad;
	}
	public Integer getNumPlazas() {
		return numPlazas;
	}
	public Integer getNumPlazasDisc() {
		return numPlazasDisc;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	
	public Integer anyo() {
		return getFecha().getYear();
	}
	public TipoAcceso getTipo() {
		return tipo;
	}
	
	public Integer numPlazasTotal() {
		return getNumPlazas()+getNumPlazasDisc();
	}
	
	public Double porcentajePlazasDisc() {
		return getNumPlazasDisc()*100.0/numPlazasTotal();
	}
	
	public OfertaEmpleo(String especialidad,Integer numPlazas,Integer numPlazasDisc,LocalDate fecha,TipoAcceso tipo) {
		Checkers.check("El número de plazas no puede ser negativo ", getNumPlazas()>=0);
		Checkers.check("El número de plazas reservadas para discapacitados no puede ser negativo ", getNumPlazasDisc()>=0);
		Checkers.check("El año de publicación debe ser posterior o igual a 1990 y la fecha de publicación debe ser anterior o igual a la fecha de hoy",
				getFecha().getYear()>=1990 && getFecha().compareTo(LocalDate.now())<=0);
		this.especialidad=especialidad;
		this.numPlazas=numPlazas;
		this.numPlazasDisc=numPlazasDisc;
		this.fecha=fecha;
		this.tipo=tipo;	
	}
	@Override
	public int hashCode() {
		return Objects.hash(especialidad, fecha, tipo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OfertaEmpleo other = (OfertaEmpleo) obj;
		return Objects.equals(especialidad, other.especialidad) && Objects.equals(fecha, other.fecha)
				&& tipo == other.tipo;
	}
	
	public int compareTo(OfertaEmpleo o) {
		int res=this.getFecha().compareTo(o.getFecha());
		if(res==0) {
			res=this.getEspecialidad().compareTo(o.getEspecialidad());
			if(res==0) {
				res=this.getTipo().compareTo(o.getTipo());
				if(res==0) {
					res=this.numPlazasTotal().compareTo(o.numPlazasTotal());
		      }	
		  }
		}return res;
	}
	@Override
	public String toString() {
		return "[" + especialidad + ", " + numPlazas + ", "
				+ numPlazasDisc + ", " + fecha + ", " + tipo + ", " + numPlazasTotal()
				+ ", " + porcentajePlazasDisc() + "]";
	}
	
	
	


}
