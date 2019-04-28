package Ejercicios_Entrega3;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import Clases.Tarea;
import us.lsi.ag.ValuesInRangeChromosome;
import us.lsi.ag.ValuesInRangeProblemAG;
import us.lsi.common.Streams2;

public class ProblemaProcesadoresAG implements ValuesInRangeProblemAG<Integer, Map<Integer ,List<Tarea>>> {
	
	List<Tarea> tareas = new ArrayList <Tarea> ();
	Integer numProcesadores ; 

	
	
	ProblemaProcesadoresAG(String file ,Integer numPorcesadores){
		tareas = cargaPesos(file);
		numProcesadores = numPorcesadores+1;
	}
	


	private static List <Tarea> cargaPesos(String file) {
		return Streams2.fromFile(file).map(Tarea::create).collect(Collectors.toList()); 
	}




	public Integer getVariableNumber() {
	
		return tareas.size()+1;// Ese + es que me he dado cuenta de que me lee un elemento menos del que me deberia leer y si le pongo +1 me lo lee . 
	}



	
	public Integer getMax(Integer i) {
		
		return numProcesadores-1;
	}



	
	public Integer getMin(Integer i) {
	
		return 0;
	}



	
	public Double fitnessFunction(ValuesInRangeChromosome<Integer> cr) {
		
		Map<Integer , List<Tarea>> sol= getSolucion(cr);
		List<Tarea> aux = new ArrayList<Tarea> ();
		Set <Integer> keys = sol.keySet();
		Double duracion=.0;
		List<Double> tiempos= new ArrayList<Double>();
		
		for (Integer b : keys) {
			Double acum =0.0;
			aux = sol.get(b);
			for ( Tarea a : aux) {
				acum += a.getDuracion();
			}
			tiempos.add(acum);
		}
		duracion = tiempos.stream().max(Comparator.naturalOrder()).get();
		
		return -duracion;
	}



	
	public Map<Integer,List <Tarea>> getSolucion(ValuesInRangeChromosome<Integer> cr) {
		
		List <Integer>solucion= cr.decode();
				
		Map<Integer,List <Tarea>> aux = new HashMap<>();
		
		for ( int i=0 ; i< numProcesadores -1 ;i++ ) {
			
			List< Tarea> res = new ArrayList <>();
			
			Double acum=.0;
			
			for (int j=0 ; j< solucion.size() -1 ;j++  ) {
				
				if (i == solucion.get(j)) {
					
					
					res.add(tareas.get(j));
					
					acum += tareas.get(j).getDuracion(); 
					
					
					}
				
										}
			
				
			aux.put(i,res);
					
		}
		
		return aux;
	}
	
	
	
///////////////////////////////////// METODO AUXILIAR PARA IMPRIMIR LA SOLUCIÓN DE UNA FORMA MAS AMIGABLE //////////////////////////////////++++++++
	public static void generaTexto(Map<Integer, List<Tarea>> sol) {
		List<Tarea> aux = new ArrayList<Tarea> ();
		Set <Integer> keys = sol.keySet();
		
		for (Integer b : keys) {
			Double acum =0.0;
			aux = sol.get(b);
			for ( Tarea a : aux) {
				acum += a.getDuracion();
			}
			
			System.out.println("  ");
			System.out.println("El procesador "+b+" tiene una carga de trabajo de duración "+acum+" ." );
			System.out.println("  ");
			System.out.println("  ---  Sus tareas son => "+aux+"  .");
			
		}
	}
	}
