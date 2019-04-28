package Ejercicios_Entrega3;

import java.util.ArrayList;
import java.util.List;

import us.lsi.common.Files2;
import us.lsi.lpsolve.solution.AlgoritmoPLI;
import us.lsi.lpsolve.solution.SolutionPLI;

public class ProblemaPL {
	


	public static void main(String[] args) {
		
		List<String> s = Files2.getLines("./ficheros/Tareaspli.txt");
		List<Integer> tareas = getTareas(s);
		Integer procesadores = getNumProcesadores(s);
		
			
		
		SolutionPLI solucion = AlgoritmoPLI.getSolution(getConstraints(tareas, procesadores));
		System.out.println("//////////////////////////SOLUCIÓN GENERADA /////////////////////////////////////");
		System.out.println("\nSolucion : " +solucion.getGoal());	
		for(int i=0;i<solucion.getNumVar();i++) {
			System.out.println(solucion.getName(i)+" = "+solucion.getSolution()[i]);
		}
		
		System.out.println("");
		System.out.println("/////////////////SOLUCIÓN PREDETERMINADA //////////////////////////////////////");
		System.out.println("");
		
		SolutionPLI res = AlgoritmoPLI.getSolutionFromFile("./ficheros/solve.txt");
		System.out.println("Solucion : " +res.getGoal());	
		for(int i=0;i<res.getNumVar();i++) {
			System.out.println(res.getName(i)+" = "+res.getSolution()[i]);}
	



	}
	

	private static Integer getNumProcesadores(List<String> s) {
		String s1 = s.get(0);
		return Integer.parseInt(s1);
	}


	private static List<Integer> getTareas(List<String> s) {
		List<Integer> r = new ArrayList<Integer>();
		String[] lc = s.get(1).split(",");
		for(String l : lc) {
			Integer a = Integer.parseInt(l);
			r.add(a);
		}
		
		
	return r;
}


	public static String getConstraints(List<Integer> tareas, Integer numProcs){
		Integer dmax = 0;
		for(int i= 0; i<tareas.size(); i++) {
			dmax = dmax + tareas.get(i);
		}
		
		
		String r = "min: T;"; //Funcion minimo
		r = r+"\n\n";
		
		for (int j = 0; j<numProcs;j++) {
		for(int i = 0; i<tareas.size(); i++) {
			if(i == tareas.size() -1) {
				r = r + String.format(" "+tareas.get(i)+" x%d%d", i,j);
			}else {
			r = r + String.format(" "+tareas.get(i)+" x%d%d", i,j);
			r = r+ " + " ;}
			
			}	
		
		
		r = r+" <= T";
		r = r+";\n\n";
		}
		
			
		
		for(int i = 0; i<tareas.size(); i++) {
			for(int j = 0;j<numProcs;j++) {
				if(j == numProcs-1 ) {
			r = r + String.format("x%d%d",i,j);
			}
				else {
					r = r + String.format("x%d%d + ",i,j);
					
				}
				
			}
			r = r + "= 1;";
			r = r+"\n";
			}
		r = r+"\n";
		
		
		r = r + "T <= "+dmax +";";
		r = r+"\n"+"int T;";
		 r =r+"\n"+ "bin ";
		for(int i = 0; i<tareas.size(); i++) {
		for(int j = 0;j<numProcs;j++) {
			if(j==numProcs-1 && i==tareas.size()-1) {
				r = r + String.format("x%d%d",i,j );
			}
			else {
				r = r + String.format("x%d%d, ",i,j );
			}
		}
			
		}
		r = r+";";
		
		return r;
}
}