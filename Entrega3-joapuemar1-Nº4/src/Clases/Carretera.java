package Clases;




public class Carretera {
	private String nombre;
	private Ciudad target , source ;
	private Double coste;
	private Double tiempo;
	public static Carretera create (Ciudad v1, Ciudad v2 ,String [] formato) {
		return new Carretera(v1,v2,formato[2], Double.parseDouble(formato[4]),Double.parseDouble(formato[3]));

	}
	public static Carretera create() {
		return null ;
	}
	
	public Carretera(Ciudad source, Ciudad target, String nombre, Double coste,Double tiempo) {
		super();
		this.nombre = nombre;
		this.target = target;
		this.source = source;
		this.coste = coste;
		this.tiempo=tiempo;
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carretera other = (Carretera) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Ciudad getTarget() {
		return target;
	}

	public void setTarget(Ciudad target) {
		this.target = target;
	}

	public Ciudad getSource() {
		return source;
	}

	public void setSource(Ciudad source) {
		this.source = source;
	}

	public Double getCoste() {
		return coste;
	}

	public void setCoste(Double coste) {
		this.coste = coste;
	}
	
	public Double getTiempo() {
		return tiempo;
	}

	public void setTiempor(Double tiempo) {
		this.tiempo = tiempo;
	}
	@Override
	public String toString() {
		return "Carretera [nombre=" + nombre + ", target=" + target + ", source=" + source + ", coste=" + coste
				+ ", tiempo=" + tiempo + "]";
	}
	
	
}
