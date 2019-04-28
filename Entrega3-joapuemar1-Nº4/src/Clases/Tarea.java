package Clases;



public class Tarea {
		
		 private Integer codigo;
		 private Double duracion ;
		 private Integer multiplicidad;
		 private static Integer numCod =0;
		 
		 public static Tarea create(String f ){
			 return  new Tarea(f);
		 }
		
		
		 public Tarea( Double duracion) {
			super();
			this.codigo=numCod;
			numCod++;
			this.duracion = duracion;
			this.multiplicidad=1;
		}
		 
		 Tarea(String s){		
				String[] v = s.split("[,]");
				Integer ne = v.length;
				if(ne < 1) throw new IllegalArgumentException("Formato no adecuado en línea  "+s);	
				this.duracion= Double.parseDouble(v[0].trim());
				this.codigo = numCod;
				numCod++;
				this.multiplicidad=1;
			}	
		

		public Double getDuracion() {
			return duracion;
		}

		public void setDuracion(Double duracion) {
			this.duracion = duracion;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((duracion == null) ? 0 : duracion.hashCode());
			result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
			Tarea other = (Tarea) obj;
			if (duracion == null) {
				if (other.duracion != null)
					return false;
			} else if (!duracion.equals(other.duracion))
				return false;
			if (codigo == null) {
				if (other.codigo != null)
					return false;
			} else if (!codigo.equals(other.codigo))
				return false;
			return true;
		}
		public Integer getCodigo() {
			return codigo;
		}
		public void setCodigo(Integer codigo) {
			this.codigo = codigo;
		}
		public int compareTo(Tarea o) {
			return this.getDuracion().compareTo(o.getDuracion());
		}

		public Integer getMultiplicidad() {
			return multiplicidad;
		}

		public void setMultiplicidad(Integer multiplicidad) {
			this.multiplicidad = multiplicidad;
		}
		 
		@Override
		public String toString() {
			return " (Tarea=" + codigo + ", duracion=" + duracion + ")";
		} 
		
		
 
}
