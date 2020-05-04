import java.util.Random;
//Nathan Frazier
public class Organism implements Comparable<Organism> {
	
	char [] genotype = new char[GeneticAlg.GENOTYPE_SIZE]; // create blank genotype string with length equal to our key
	int fitness = 0; //correctness 
	Random rand = new Random();
	
	public Organism() {
		randomize();
	}
	
	public Organism(char [] chosen) {
		// when we already have the genome for the organism decided, we must find the fitness because otherwise it will inherit
		genotype = chosen;
		for (int i = 0 ; i < GeneticAlg.GENOTYPE_SIZE; i ++)
		{
			if (GeneticAlg.keyword.charAt(i) == genotype[i]) {
				fitness += 1;
			}
		}
		// now fitness will be set correctly after re-population.	
	}
		
	public void randomize() {
		for (int i = 0 ; i < genotype.length ; i++ ) {
		
			genotype[i] = (char) (65 + rand.nextInt(57)); // Between ASCII start  and ASCII end
			fitness += Math.abs(GeneticAlg.keyword.charAt(i) - genotype[i]);
		}
	}
	
	public String toString() {
			return new String(genotype);
		}
	@Override
	public int compareTo(Organism o) {
		return this.fitness - o.fitness;
	}
}
