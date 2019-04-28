package Ejercicios_Entrega3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.HamiltonianCycleAlgorithm;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.tour.HeldKarpTSP;
import org.jgrapht.graph.SimpleWeightedGraph;
import Clases.Carretera;
import Clases.Ciudad;
import us.lsi.graphs.GraphsReader;


public class Ejercicio_3_3 {

	public static void main(String[] args) {
		
		String fichero  = "ficheros/Andalucia2.txt";
		System.out.println("//////////////////////////////////////////////////////////////Inicialización////////////////////////////////////////////////////////////////////////////////");
		SimpleWeightedGraph<Ciudad, Carretera> grafoTiempo=cargaGrafoPonTiempo(fichero);
		System.out.println("  ");
		System.out.println("	--Grafo ponderado en tiempo  :"+grafoTiempo);
		
		SimpleWeightedGraph<Ciudad, Carretera> grafoCoste=cargaGrafoPonCoste(fichero);
		System.out.println("  ");
		System.out.println("	--Grafo ponderado en Coste   :"+grafoCoste);
		
		System.out.println("  ");
		System.out.println("///////////////////////////////////////////////////////////////"+"APARTADO A"
				+ "/////////////////////////////////////////////////////////////////////////////////////////////////");
		System.out.println("  ");
		calculaCaminoMinTiempo(grafoTiempo, "Lugar0", "Lugar6");
			
		System.out.println("  ");
		calculaCaminoMinCoste(grafoCoste, "Lugar6", "Lugar4");
		
		
		System.out.println("  ");
		System.out.println("///////////////////////////////////////////////////////////////"+"APARTADO B "
				+ "/////////////////////////////////////////////////////////////////////////////////////////////////");
		System.out.println("  ");
		cicloHamiltonMennosTiempoMain(grafoTiempo);
		
		System.out.println("  ");
		System.out.println("///////////////////////////////////////////////////////////////"+"APARTADO C "
				+ "/////////////////////////////////////////////////////////////////////////////////////////////////");
		
		List<String> ciudades = new ArrayList<>();
		
		ciudades.add("Lugar0");
		ciudades.add("Lugar8");
		ciudades.add("Lugar6");
		ciudades.add("Lugar4");
		System.out.println("  ");
		SolucionEjercicio3_3(grafoCoste, "Lugar0", ciudades);
		
		Ciudad c = new Ciudad("Lugar4");
		
		System.out.println("");
		System.out.println("////////////////////////////////////////////Ejercicio a parte ////////////////////////////////////////");
		
		System.out.println("");
		calculaRutas(grafoTiempo, c);
	}
	///////////////////// LECTURA DE GRAFOS PONDERADOS //////////////////////////////////////////////////////////////////
	
	private static SimpleWeightedGraph<Ciudad, Carretera> cargaGrafoPonTiempo (String fichero) {
		
		
		SimpleWeightedGraph<Ciudad, Carretera> grafo =  GraphsReader.newGraph(
				fichero,
				Ciudad :: create,
				Carretera :: create ,
				()->new SimpleWeightedGraph<Ciudad,Carretera>(
				Ciudad:: create,
				Carretera::create) , Carretera :: getTiempo);
		return grafo;
				
	}
	
	
	private static SimpleWeightedGraph<Ciudad, Carretera> cargaGrafoPonCoste(String fichero) {
			
			
			SimpleWeightedGraph<Ciudad, Carretera> grafo =  GraphsReader.newGraph(
					fichero,
					Ciudad :: create,
					Carretera :: create ,
					()->new SimpleWeightedGraph<Ciudad,Carretera>(
					Ciudad:: create,
					Carretera::create) , Carretera :: getCoste);
			return grafo;
					
		}
	
	
	////////////////////////////////////////// APARTADO A ///////////////////////////////////////////////////////////////////////////
	
	public static void calculaCaminoMinTiempo( SimpleWeightedGraph<Ciudad, Carretera> g ,String origen,String destino) {
		 Double tiempoTotal = caminoMasCorto(g, origen, destino).getEdgeList().stream().mapToDouble(i -> i.getTiempo()).sum();
		 System.out.println("	--Elcamino que minimiza el tiempo es ( " +tiempoTotal+ " min  )  => " +caminoMasCorto(g, origen, destino).getVertexList());
		 
	}
	public static void calculaCaminoMinCoste( SimpleWeightedGraph<Ciudad, Carretera> g ,String origen,String destino) {
		 Double costeTotal = caminoMasCorto(g, origen, destino).getEdgeList().stream().mapToDouble(i -> i.getCoste()).sum();
		 System.out.println("	--Elcamino que minimiza el coste es ( " +costeTotal+ " € )  => " +caminoMasCorto(g, origen, destino).getVertexList());
		
	}
	
	private static GraphPath<Ciudad ,Carretera> caminoMasCorto(SimpleWeightedGraph<Ciudad, Carretera> grafo,String origen , String destino) {
		Ciudad from = Ciudad.create(origen);	
		Ciudad to  = Ciudad.create(destino);
		
		ShortestPathAlgorithm<Ciudad, Carretera> alg =  new DijkstraShortestPath<>(grafo);
		GraphPath<Ciudad ,Carretera> gp= alg.getPath(from, to);
		return gp;
				
	}
	
	///////////////////////////////////////////////////APARTADO B ///////////////////////////////////////////////////////////////////
	
	public static void cicloHamiltonMennosTiempoMain(SimpleWeightedGraph<Ciudad, Carretera> grafo) {
		Double tiempoTotal = cicloHamiltonMenosTiempo(grafo).getEdgeList().stream().mapToDouble(x->x.getTiempo()).sum();
		System.out.println("	--El ciclo Hamiltoniano que minimiza el tiempo es  ( "+tiempoTotal+" min ) : "+ cicloHamiltonMenosTiempo(grafo).getVertexList() );
		
	}
	
	private static GraphPath<Ciudad,Carretera> cicloHamiltonMenosTiempo(SimpleWeightedGraph<Ciudad, Carretera> grafo) {
		
		HamiltonianCycleAlgorithm<Ciudad,Carretera> alg = new HeldKarpTSP<>();
		GraphPath<Ciudad,Carretera> gp = alg.getTour(grafo);
		if (gp!=null) {
					return gp;
			}else {
				
				throw new IllegalArgumentException("	El grafo referenciado no contiene ciclo Hamiltoniano .  ");
					
				}
		
		}
	//////////////////////////////////////////////////   APARTADO C   ////////////////////////////////////////////////////////////////////////////////
	public static void SolucionEjercicio3_3(SimpleWeightedGraph<Ciudad, Carretera> grafo ,String origen ,List<String> ciudades) {
		List <Ciudad> aux=new ArrayList<Ciudad> ();
		for(String s : ciudades) {
			aux.add(new Ciudad(s));
		}

				
		cicloMinDadoUnCojunto(grafo, origen, aux, 0, new ArrayList<Ciudad>());
		}
	
	private static void cicloMinDadoUnCojunto(SimpleWeightedGraph<Ciudad, Carretera> grafo ,String origen ,List<Ciudad> ciudades,int count,List<Ciudad> solucion) {
		
		Map <Carretera , List<Ciudad>> mid = new HashMap<Carretera,List<Ciudad>>();
		SimpleWeightedGraph<Ciudad, Carretera > aux = new SimpleWeightedGraph<Ciudad , Carretera>(Ciudad:: create,
				Carretera::create);
		
	
					/////////////////////  FORMA ITERATIVA ///////////////////////////
		/////////////////// GENERO EL GRAFO COMPLETO DE LA LISTA DE CIUDADES A VISITAR ///////////////////////////////
		for (int i =0 ; i < ciudades.size(); i++) {
			
			for ( int j = 0 ; j < ciudades.size() ; j++) {
				
				if (i != j ) {
///////////////////// MIRO LAS DISTANCIAS CADA 2 CIUDADES QUE QUIERO VISITAR  //////////////////////////////////////////////////////////////					
					GraphPath<Ciudad,Carretera> gp = new DijkstraShortestPath<>(grafo).getPath(ciudades.get(i), ciudades.get(j));
					
					aux.addVertex(ciudades.get(i));aux.addVertex(ciudades.get(j));
					
					Carretera c = new Carretera(ciudades.get(i),ciudades.get(j),"Carretera c"+count, gp.getEdgeList().stream().mapToDouble(x->x.getCoste()).sum(), gp.getEdgeList().stream().mapToDouble(x->x.getTiempo()).sum());
					count ++; // Es un contador para diferenciar los nombres de las carreteras .	
			
//////////////////// GENERO EL GRAFO AUXILIAR QUE CONTIENE EL GRAFO COMPLETO CON LAS DISTACIAS REALES ENTRE CADA CIUDAD QUE QUIERO VISITAR ///////////////////	
					aux.addEdge(c.getSource(),c.getTarget(),c);		

					
///////////////////////////// RELACIONO LAS CARRETERAS CON LAS DISTANCIAS TOTALES A LA LISTA DE CIUDADES POR LAS QUE TIENE QUE PASAR ///////////																							
				    mid.put(c,gp.getVertexList());
					
					
				}
			}
		}
		
		//////////////////////////// VOY A GENERAR LA LISTA DE CARRETERAS QUE INCLUYE EL CAMINO MINIMO   //////////////
		GraphPath<Ciudad,Carretera> g = (new HeldKarpTSP<Ciudad,Carretera>()).getTour(aux);
		
		List<Ciudad> auxl =new ArrayList<Ciudad>();
		
		List<Carretera> carreteras = g.getEdgeList();
		
		
		///////////////////// YA SE CUAL ES EL CICLO MINIMO Y GENERO EL CAMINO REAL INTRODUCIENDO LAS CARRETERAS INTERMEDIAS////////////////////////////////////////////
		
		for(int j=0 ; j<carreteras.size();j++) {
			
			if ( j == carreteras.size()-1) {
				auxl= mid.get(carreteras.get(j));
				Collections.reverse(auxl);
			}else {
			auxl= mid.get(carreteras.get(j));
			}
			for (int i = 0 ; i<auxl.size();i++) {
			
				if(!solucion.isEmpty()) {
				
					if( !(solucion.get(solucion.size()-1).equals(auxl.get(i)))){
					solucion.add(auxl.get(i));
				}
				
			}else {
				solucion.add(auxl.get(i));
								
			}	
			}	
								
		}
		
		
		System.out.println("	--El recorrido de menor coste entre las ciudades ( "+ciudades+" ) es  =====>> "+solucion+" . " );
		System.out.println("  ");
		System.out.print("	--El coste total del trayecto es  "+carreteras.stream().mapToDouble(x->x.getCoste()).sum()+" €");
		System.out.println("  ");
		}
		
	
		public static void  calculaRutas( SimpleWeightedGraph<Ciudad, Carretera> g , Ciudad c ) {
			Double duracion = .0;  
			GraphPath<Ciudad, Carretera> gr = (new HeldKarpTSP<Ciudad,Carretera>()).getTour(g);
			for ( int i = 0 ; i < gr.getVertexList().size()-1;i++) {
				if( !(c.getNombre().equals(gr.getVertexList().get(i).getNombre()))){
					GraphPath<Ciudad, Carretera> sol = new DijkstraShortestPath<>(g).getPath(c,gr.getVertexList().get(i));
					duracion= sol.getEdgeList().stream().mapToDouble(x->x.getTiempo()).sum();
					if(duracion <120.0) {
						System.out.println("Origen "+c.getNombre()+" lista de ciudades por la que pasa  => "+sol.getVertexList()+" , conduración ("+duracion+"  min ) ");
					}
						
								
			}
			
			
		}
		}		
	}
	


