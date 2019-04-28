package SegundaDefensa;
import java.util.Comparator;
import us.lsi.tiposrecursivos.BinaryTree;

public class Ejercicio3_l {

	public static <E> void main(String[] args) {
		// 
		Comparator<Integer> CompInt = (x,y)->x.compareTo(y);
		
		System.out.println("///////////////////////////// EJEMPLO CORRECTO ////////////////////////////");
		
		BinaryTree <Integer> arbol = BinaryTree.binary(75, 
				BinaryTree.binary(50,BinaryTree.leaf(49),BinaryTree.leaf(68)),
				BinaryTree.binary(100,BinaryTree.leaf(78), BinaryTree.leaf(110)));
	
		System.out.println("El arbol es :"+arbol);
		System.out.println("El arbol sigiente esta ordenado segun la metodologia del ejercicio , tiene que salir true :"+ArbolOrdenado(arbol,CompInt));
		
		System.out.println("///////////////////////////// EJEMPLOS INCORRECTO ////////////////////////////");
		System.out.println("---------------------------FALLA EL ORDEN POR NIVEL--------------------------");
		BinaryTree <Integer> arbol1 = BinaryTree.binary(75, 
				BinaryTree.binary(50,BinaryTree.leaf(51),BinaryTree.leaf(68)),
				BinaryTree.binary(100,BinaryTree.leaf(78), BinaryTree.leaf(110)));
		BinaryTree <Integer> arbol2 = BinaryTree.binary(75, 
				BinaryTree.binary(50,BinaryTree.leaf(49),BinaryTree.leaf(44)),
				BinaryTree.binary(100,BinaryTree.leaf(78), BinaryTree.leaf(110)));
		BinaryTree <Integer> arbol3 = BinaryTree.binary(75, 
				BinaryTree.binary(50,BinaryTree.leaf(49),BinaryTree.leaf(68)),
				BinaryTree.binary(100,BinaryTree.leaf(101), BinaryTree.leaf(110)));
		BinaryTree <Integer> arbol4 = BinaryTree.binary(75, 
				BinaryTree.binary(50,BinaryTree.leaf(49),BinaryTree.leaf(68)),
				BinaryTree.binary(62,BinaryTree.leaf(78), BinaryTree.leaf(110)));
	
		System.out.println("El arbol 1 es :"+arbol1);
		System.out.println("El arbol sigiente tiene como hijo izquierdo 51 y es mas que su padre que es 50, tiene que ser false :"+ArbolOrdenado(arbol1,CompInt));
		
		System.out.println("El arbol 2 es :"+arbol2);
		System.out.println("El arbol sigiente tiene como hijo derecho  44  y es meno que el padre que es que es 50 , tiene que ser false :"+ArbolOrdenado(arbol2,CompInt));
	
		System.out.println("El arbol 3 es :"+arbol3);
		System.out.println("El arbol sigiente tiene como hijo izquierdo 101 y es mas que su padre que es 100, tiene que ser false :"+ArbolOrdenado(arbol3,CompInt));
		
		System.out.println("El arbol 4 es :"+arbol4);
		System.out.println("El arbol sigiente tiene como hijo derecho 62 y es meno que el root que es 75 , tiene que ser false :"+ArbolOrdenado(arbol4,CompInt));
		System.out.println("--------------------FALLA EL MAXIMO DEL HIJO IZQUIERDO DE ROOT ES MAYOR QUE EL PROPIO ROOT--------------------------");
		
		BinaryTree <Integer> arbol5 = BinaryTree.binary(75, 
				BinaryTree.binary(50,BinaryTree.leaf(49),BinaryTree.leaf(78)),
				BinaryTree.binary(100,BinaryTree.leaf(78), BinaryTree.leaf(110)));
		
		System.out.println("El arbol 5 es :"+arbol5);
		System.out.println("El mayor del arbol hijo izquierdo del arbol padre es mayor que el propio padre , tiene que ser false :"+ArbolOrdenado(arbol5,CompInt));
		System.out.println("--------------------FALLA EL MINIMO DEL HIJO DERECHO DE ROOT ES MENOR QUE EL PROPIO ROOT--------------------------");
		BinaryTree <Integer> arbol6 = BinaryTree.binary(75, 
				BinaryTree.binary(50,BinaryTree.leaf(49),BinaryTree.leaf(66)),
				BinaryTree.binary(100,BinaryTree.leaf(44), BinaryTree.leaf(110)));
		System.out.println("El arbol 6 es :"+arbol6);
		System.out.println("El mayor del arbol hijo izquierdo del arbol padre es mayor que el propio padre , tiene que ser false :"+ArbolOrdenado(arbol6,CompInt));
	}

	//
	public static <E> Boolean ArbolOrdenado(BinaryTree<E> arbol,Comparator<E>cmp) {
		Boolean res =arbolenOrden(arbol,true,cmp) && mayorIzquierdoMenorqueRoot(arbol.getLeft(),arbol.getLabel(),true,cmp) &&
				 menorDerechoMayorqueRoot(arbol.getRight(),arbol.getLabel(),true,cmp);
		return res ;
	}
 
	
	// Comprueba que el arbol cumple nivel a nivel que el hijo izquierdo es menor que el padre y el hijo derecho es mayor que el padre.
	private static <E> Boolean arbolenOrden(BinaryTree<E> arbol, Boolean res ,Comparator<E>cmp) {
		
		switch(arbol.getType()) {
		
		case Empty:
			break;
		case Leaf:	
			break;
		case Binary:		
		
			res= arbolenOrden(arbol.getLeft(),cmp.compare(arbol.getLeft().getLabel(),arbol.getLabel())<0 ,cmp) &&
				 arbolenOrden(arbol.getRight(),cmp.compare(arbol.getRight().getLabel(),arbol.getLabel())>0,cmp) ;
			break;
		}
		
		return res;
	}
	//Comprueba si la mayor de las etiquetas del arbol hijo izquierdo del arbol padre es menor que la etiqueta del padre.
	private static <E> Boolean mayorIzquierdoMenorqueRoot(BinaryTree<E> arbolIzq,E root,Boolean res, Comparator<E> cmp) {
	
	if(res==true) {	
		
		switch(arbolIzq.getType()) {
			
		case Empty:
			break;
		case Leaf:
			res = cmp.compare(arbolIzq.getLabel(),root)<0;
			break;
		case Binary:
			 res = mayorIzquierdoMenorqueRoot(arbolIzq.getRight(),root,cmp.compare(arbolIzq.getLabel(),root)<0,cmp);
			break;
		}
	}
		
		return res;
		
		
	}
	private static <E> Boolean menorDerechoMayorqueRoot(BinaryTree<E> arbolDer,E root , Boolean res , Comparator <E> cmp ) {
		
		if(res ==true) {
			
			switch(arbolDer.getType()) {
			
			case Empty:
				break;
			case Leaf:
				res=cmp.compare(arbolDer.getLabel(),root)>0;
				break;
			case Binary:
				res = menorDerechoMayorqueRoot(arbolDer.getLeft(),root,cmp.compare(arbolDer.getLeft().getLabel(),root)>0,cmp);
				break;
			}
		}
	
		return res;

	}
		
}
