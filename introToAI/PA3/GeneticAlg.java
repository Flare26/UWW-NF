//Nathan Frazier
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
public class GeneticAlg {
	
	public static String keyword = "oreos"; // Idea genotype
	static int keywordscore = 0;
	
	static int MAX_GENERATIONS = 42000;
	
	static final int GENERATION_SIZE = 4200;
	static int GENOTYPE_SIZE = keyword.length();
	static double FIT_THRESHOLD = GENOTYPE_SIZE; // cease search
	static int PARENT_COUNT = 2;
	static double MUT_RATE = 0.05; // 0 - 1 that a single gene is random ( for guessing a string, each character is a gene ) %
	
	 
	
	public static void main(String[]args) {
		Organism [] cur_gen = new Organism [GENERATION_SIZE] ; // fill wish random organisms ( CURRENT GENERATION )
		int current = 1;
		for (int i = 0 ; i < GENERATION_SIZE ; i ++ ) {
			cur_gen[i] = new Organism();; //Randomly created organism
			cur_gen[i].randomize();
			cur_gen[i].fitness = evaluate(cur_gen[i]);
			//System.out.println("\nRandom organism " + i + " = " + cur_gen[i].toString() + " | Fitness = " + evaluate(cur_gen[i]));
		}
		
		while ( current <= MAX_GENERATIONS ) {
		
			for (int i = 0 ; i < GENERATION_SIZE ; i ++ ) {
				cur_gen[i].fitness = evaluate(cur_gen[i]);
			}
		
		Arrays.sort(cur_gen, Collections.reverseOrder());
		
		System.out.println("Most fit = " + cur_gen[0].toString() + " | " + cur_gen[0].fitness);
		System.out.println("Lowest fitness = " + cur_gen[99].fitness);
		System.out.println("End of generation: " + current);
		//Check if our most fit Organism has exceeded the FITNESS_THRESHOLD hyperparameter
				//This stops the program from running after it already found an answer we consider acceptable
				if( cur_gen[0].fitness >= FIT_THRESHOLD )
					break;
			
			Organism [] parents = new Organism [PARENT_COUNT];
			for (int p = 0 ; p < PARENT_COUNT ; p++) {
				parents[p] = cur_gen[p];
			}
			
			Organism [] next_gen = new Organism [GENERATION_SIZE];
			for ( int i = 0 ; i < GENERATION_SIZE ; i++) {
				next_gen[i] = breed(parents);
			}
			
			for(Organism o : next_gen) {
				mutate(o);
			}
			cur_gen = next_gen;
			current++;
		}
	}
	
	
	
	public static int evaluate(Organism o) {
		//This function can be thought of as a 'black box'
		//It determines how well an Organism performed in its environment
		//Only the evaluate() function knows the 'answer' that Organisms are looking for
		//Even so, they should be able to guess it without ever knowing anything about it!
		String pswd = GeneticAlg.keyword;
		int score = 0;
		
		for (int i = 0 ; i < pswd.length(); i ++)
		{
			if (pswd.charAt(i) == o.genotype[i])
				score++;
		}
		return score; // returns fit
	}
	
	public static Organism breed(Organism [] parents) {
		Random rand = new Random();
		char [] chosen = new char [GENOTYPE_SIZE]; // new genotype
		
		
		for (int i = 0 ; i < GENOTYPE_SIZE ; i++ )
		{
			//Select a parent at random to inherit the ith gene from
			int k = rand.nextInt(1) + 1;	
		    chosen[i] = parents[k].genotype[i];
		}
		
		
		return new Organism(chosen);
		
	}
	
	static void mutate(Organism o) {
		//Each individual gene has a chance to be modified
		//Index 'i' represents the ith gene in the genotype
		Random rand = new Random();
		for (int i = 0 ; i < GENOTYPE_SIZE ; i++ ) {
			if ((rand.nextInt(100)/100 ) < MUT_RATE ){
				//Give the genotype a random gene for its ith gene
				//Most likely, this will be one that none of its parents had
				//Maybe that's good, maybe that's bad- we'll let selection handle it!
				o.genotype[i] = (char) (65 + rand.nextInt(57)); // Between ASCII start  and ASCII end
			}
		}
	}
	


}

