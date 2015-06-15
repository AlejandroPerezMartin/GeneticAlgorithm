
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main
{
    public static int population = 20;
    public static int generations = 8;
    public static double mutationProbability = 0.1;
    public static double crossoverProbability = 0.5;
    public static Individual bestIndividual = new Individual();
    public static ArrayList<Individual> individuals = new ArrayList<Individual>();
    public static ArrayList<Individual> bestIndividuals = new ArrayList<Individual>();

    public static void main(String[] args)
    {

        // INITIALIZATION
        initialization();

        for (int i = 0; i < generations; i++)
        {

            // SELECTION
            // Sort to get best individuals at the top of the list
            Collections.sort(individuals, new Comparator<Individual>()
            {
                public int compare(Individual p1, Individual p2)
                {
                    if (p1.getFitness() < p2.getFitness())
                    {
                        return 1;
                    }
                    else
                    {
                        return -1;
                    }
                }
            });

            // Store best individuals
            for (int j = 0; j < population / 2; j++)
            {
                bestIndividuals.add(individuals.get(j));
            }

            // CROSSOVER
            for (int j = 0; j < population / 2; j += 2)
            {
                int rand1 = Utils.getRandomInt(0, population - 1);
                int rand2 = Utils.getRandomInt(0, population - 1);

                if (Math.random() < crossoverProbability)
                {
                    Individual[] children = crossover(individuals.get(rand1), individuals.get(rand2));

                    // add children to bestIndividuals list
                    bestIndividuals.add(children[0]);
                    bestIndividuals.add(children[1]);
                }
                else
                {
                    // add two random individuals to the list
                    bestIndividuals.add(individuals.get(rand1));
                    bestIndividuals.add(individuals.get(rand2));
                }
            }

            // Sort individuals by best fitness
            Collections.sort(bestIndividuals, new Comparator<Individual>()
            {
                public int compare(Individual p1, Individual p2)
                {
                    if (p1.getFitness() < p2.getFitness())
                        return 1;
                    else
                        return -1;
                }
            });

            // Get best individual
            System.out.println("\n----------------------- GENERATION " + (i + 1) + " ----------------------");
            bestIndividual = bestIndividuals.get(0);
            System.out.println("Best: " + bestIndividual.getFitness());
            System.out.println("Worst: " + bestIndividuals.get(bestIndividuals.size() - 1).getFitness());
            System.out.println("-----------------------------------------------------------");

            individuals.clear();

            // Copy best individuals for next iteration
            for (Individual individual : bestIndividuals)
            {
                individuals.add(individual);
            }

            bestIndividuals.clear();
        }
    }

    /**
     * Initialize method, generates a random population
     */
    public static void initialization()
    {
        for (int i = 0; i < population; i++)
        {
            double value = Utils.getRandomDouble();
            individuals.add(new Individual(value));
        }
    }

    /**
     * Mixes chromosomes of two individuals and returns an array containing two new individuals result of the crossover
     *
     * @param firstIndividual
     * @param secondIndividual
     * @return
     */
    public static Individual[] crossover(Individual firstIndividual, Individual secondIndividual)
    {
        int cutPoint = Utils.getRandomInt(0, firstIndividual.getChromosome().length() - 1);

        String ind1slice1 = firstIndividual.getChromosome().substring(0, cutPoint);
        String ind1slice2 = firstIndividual.getChromosome().substring(cutPoint, firstIndividual.getChromosome().length());
        String ind2slice1 = secondIndividual.getChromosome().substring(0, cutPoint);
        String ind2slice2 = secondIndividual.getChromosome().substring(cutPoint, secondIndividual.getChromosome().length());

        String firstChild = ind1slice1 + "" + ind2slice2;
        String secondChild = ind2slice1 + "" + ind1slice2;

        String[] newChromosomes = new String[2];
        Individual[] newIndividuals = new Individual[2];

        newChromosomes[0] = firstChild;
        newChromosomes[1] = secondChild;

        // MUTATION
        for (int i = 0; i < newChromosomes.length; i++)
        {
            if (Math.random() < mutationProbability)
            {
                newIndividuals[i] = new Individual(mutate(newChromosomes[i]));
            }
            else
            {
                newIndividuals[i] = new Individual(newChromosomes[i]);
            }
        }

        return newIndividuals;
    }

    /**
     * Randomnly changes a bit of a binary chromosome string
     *
     * @param chromosome
     * @return
     */
    public static String mutate(String chromosome)
    {
        int bit = Utils.getRandomInt(0, 63);
        String newChromosome = "";

        if (chromosome.charAt(bit) == '0')
        {
            newChromosome = Utils.replaceCharAt(chromosome, bit, '1');
        }
        else
        {
            newChromosome = Utils.replaceCharAt(chromosome, bit, '0');
        }

        return newChromosome;
    }

}
