package SegundaDefensa;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import us.lsi.common.Files2;

public class Ejercicio2 {

	public static <E> void main(String[] args) {
		
		List<Integer> res = new ArrayList<> ();
		Comparator<Integer> cmp =(x,y)->x.compareTo(y);
		preparaLista( "numeros2" , res);
		System.out.println( "Esta es la lista sin ordenar  -> "+ res );
		System.out.println( "Esta es la lista ordenada     -> "+ mergesortR(res,cmp));
		
		//Esta hecho para que se le pase cualquier comparador a mergenSort .
		

	}
	
	//He creado un lector de lista de numeros desde un fichero para ahorrarme todos los add que tendria que hacer para crear la lista de enteros.
	//La lectura de fichero solo esta hecho para una lista de integer.
	private static  void preparaLista( String a ,List <Integer> res){
	
		
		List <String> aux = Files2.getLines(a);
		
	 	
		String[] parts =  aux.get(0).split(",");
		
		for (int i = 0 ; i<parts.length ;i++) {
		   res.add(Integer.parseInt(parts[i]));
		}
				
	}
	//METODO RECURSIVO DE ORDENACIÓN DE MERGERSORT.
	
	
	//METODO PRINCIPAL.
	
	public static <E> List<E> mergesortR(List<E> res,Comparator<E> cmp){
		return mergesort(res,res.size(),cmp);
	}
	
	//METODOS UTILIZADOS . 
	
	//METODO QUE DIVIDE LA LISTA A LA MITAD CADA VEZ .
	private static <E> List<E> mergesort(List<E> s ,int k,Comparator<E> cmp){
	
		List<E> izq = new ArrayList<E>();
		List<E> der = new ArrayList<E>();
	 
	 if(s.size()==0 || s.size()==1) {
		  return 	s;
	  }

	 for(int i=0;i<k/2;i++) {
		  izq.add(s.get(i));
	  }
	
	 for(int i = k/2; i<s.size();i++) {
		  der.add(s.get(i));
	  }
	   izq = mergesort(izq,izq.size(),cmp);
	   der = mergesort(der,der.size(),cmp);
	   
	   return fusion(izq,der,0,0,cmp);
	}
	
	// UNE IZQ Y DER DE FORMA ORDENADA EN IZQ . 
	private static <E> List<E> fusion(List<E> izq,List<E> der,int i,int j,Comparator<E> cmp){
	
	if(j<der.size()) {
		
		if(i==izq.size() ) {
			izq.add(der.get(j));
			izq=fusion(izq,der,0,j+1, cmp) ;
	
		} else  {
			if (cmp.compare(der.get(j),izq.get(i))<0) {
				izq.add(i,der.get(j));
				fusion(izq, der, 0, j+1,cmp);
			} else  {
				izq= fusion(izq,der,i+1,j,cmp);
			}
	
		}
	}
		return izq ; 
	
	}
}