package adx.experiments;

import java.util.ArrayList;
import java.util.List;

import adx.sim.agents.SimAgent;
import adx.sim.agents.SimpleSimAgent;
import adx.sim.agents.WE.WEAgent;
import adx.sim.agents.waterfall.WFAgent;

/**
 * Factory class to create experiments.
 * 
 * @author Enrique Areyan Viqueira
 */
public class ExperimentFactory {

  // Fixed parameters for all experiments.
  public static final int numberOfGames = 100;
  public static String resultsDirectory = "results/";

  /**
   * Creates a list of SI agents.
   * 
   * @param numberOfAgents
   * @return
   */
  public static List<SimAgent> listOfSIAgents(int numberOfAgents, double reserve) {
    List<SimAgent> simAgents = new ArrayList<SimAgent>();
    for (int j = 0; j < numberOfAgents; j++) {
      // Simple Agents
      simAgents.add(new SimpleSimAgent("SIAgent" + j, reserve));
    }
    return simAgents;

  }

  /**
   * Creates a list of WE agents.
   * 
   * @param numberOfAgents
   * @return
   */
  public static List<SimAgent> listOfWEAgents(int numberOfAgents, double reserve) {
    List<SimAgent> simAgents = new ArrayList<SimAgent>();
    for (int j = 0; j < numberOfAgents; j++) {
      // Walrasian Agents
      simAgents.add(new WEAgent("WEAgent" + j, reserve));
    }
    return simAgents;
  }

  /**
   * Creates a list of WF agents.
   * 
   * @param numberOfAgents
   * @return
   */
  public static List<SimAgent> listOfWFAgents(int numberOfAgents, double reserve) {
    List<SimAgent> simAgents = new ArrayList<SimAgent>();
    for (int j = 0; j < numberOfAgents; j++) {
      // Waterfall Agents
      simAgents.add(new WFAgent("WFAgent" + j, reserve));
    }
    return simAgents;
  }

  /**
   * Experiment with all WE agents, parameterized by the number of agents
   * 
   * @param numberOfAgents
   * @return
   */
  public static Experiment allWEExperiment(String resultsFileName, int numberOfAgents, double reserve) {
    return new Experiment(ExperimentFactory.resultsDirectory + resultsFileName, ExperimentFactory.listOfWEAgents(numberOfAgents, reserve),
        ExperimentFactory.numberOfGames, reserve);
  }

  /**
   * Experiment with all WF agents, parameterized by the number of agents.
   * 
   * @param numberOfAgents
   * @return
   */
  public static Experiment allWFExperiment(String resultsFileName, int numberOfAgents, double reserve) {
    return new Experiment(ExperimentFactory.resultsDirectory + resultsFileName, ExperimentFactory.listOfWFAgents(numberOfAgents, reserve),
        ExperimentFactory.numberOfGames, reserve);
  }

  /**
   * A game with only SI and WE agents.
   * 
   * @param numberWE
   * @param numberWF
   * @return
   */
  public static Experiment SIandWEAgents(String resultsFileName, int numberSI, int numberWE, double reserve) {
    List<SimAgent> simAgents = ExperimentFactory.listOfSIAgents(numberSI, reserve);
    simAgents.addAll(ExperimentFactory.listOfWEAgents(numberWE, reserve));
    return new Experiment(ExperimentFactory.resultsDirectory + resultsFileName, simAgents, ExperimentFactory.numberOfGames, reserve);
  }

  /**
   * A game with only SI and WF agents.
   * 
   * @param numberWE
   * @param numberWF
   * @return
   */
  public static Experiment SIandWFAgents(String resultsFileName, int numberSI, int numberWF, double reserve) {
    List<SimAgent> simAgents = ExperimentFactory.listOfSIAgents(numberSI, reserve);
    simAgents.addAll(ExperimentFactory.listOfWFAgents(numberWF, reserve));
    return new Experiment(ExperimentFactory.resultsDirectory + resultsFileName, simAgents, ExperimentFactory.numberOfGames, reserve);
  }

  /**
   * A game with only WE and WF agents.
   * 
   * @param numberWE
   * @param numberWF
   * @return
   */
  public static Experiment WEandWFAgents(String resultsFileName, int numberWE, int numberWF, double reserve) {
    List<SimAgent> simAgents = ExperimentFactory.listOfWEAgents(numberWE, reserve);
    simAgents.addAll(ExperimentFactory.listOfWFAgents(numberWF, reserve));
    return new Experiment(ExperimentFactory.resultsDirectory + resultsFileName, simAgents, ExperimentFactory.numberOfGames, reserve);
  }

  /**
   * A game with SI, WE and WF agents.
   * 
   * @param numberWE
   * @param numberWF
   * @return
   */
  public static Experiment SIandWEandWFAgents(int numberSI, int numberWE, int numberWF, double reserve) {
    List<SimAgent> simAgents = ExperimentFactory.listOfSIAgents(numberSI, reserve);
    simAgents.addAll(ExperimentFactory.listOfWEAgents(numberWE, reserve));
    simAgents.addAll(ExperimentFactory.listOfWFAgents(numberWF, reserve));
    return new Experiment(ExperimentFactory.resultsDirectory + "/SIWEWF(" + numberSI + "-" + numberWE + "-" + numberWF + ")" + ".csv", simAgents,
        ExperimentFactory.numberOfGames, reserve);
  }

}