package Ejercicios_Entrega3;

import java.util.List;
import java.util.Map;

import Clases.Tarea;
import us.lsi.ag.ValuesInRangeChromosome;
import us.lsi.ag.ValuesInRangeProblemAG;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.ag.agstopping.StoppingConditionFactory;

public class TestProc {

	public static void main(String[] args) {
		
		setCondiciones();
		
		ValuesInRangeProblemAG<Integer,Map<Integer , List<Tarea>>> problema= new ProblemaProcesadoresAG("ficheros/Tareas.txt", 4);
		AlgoritmoAG<ValuesInRangeChromosome<Integer>> alg= AlgoritmoAG.create(ChromosomeType.Range, problema);
		
		alg.ejecuta();
		ValuesInRangeChromosome<Integer> sol = alg.getBestChromosome();
		
		ProblemaProcesadoresAG.generaTexto(problema.getSolucion(sol));
	}
	private static void setCondiciones() {
		//Condiciones evolutivas
				AlgoritmoAG.CROSSOVER_RATE = 0.8;
				AlgoritmoAG.MUTATION_RATE  = 0.5;
				AlgoritmoAG.ELITISM_RATE = 0.25;
				AlgoritmoAG.POPULATION_SIZE = 300;
				
				//Condiciones de parada
				StoppingConditionFactory.FITNESS_MIN = 1.0;
				StoppingConditionFactory.NUM_GENERATIONS = 1000;
				StoppingConditionFactory.SOLUTIONS_NUMBER_MIN = 3;
	}

}
