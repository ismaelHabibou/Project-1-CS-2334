package projects;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class: CS 2334-001 Programing Structures and Abstractions
 * Instructor: James
 * Description: This class models an automaton.
 * It has the number  of sections, number of students, the name of the course and maximum number of students per section.
 * Due: January t6th 2020
 * I pledge I have done this work independently.
 * I pledge I have not copied my code from someone's else.
 * I pledge I have not share my code with any of my classmates.
 * I helped Wahid AlHadari with his classes.
 *
 *
 * Sign in: Ismael Habibou Issaka
 *
 * I got this template on the web.
 */
public class Automaton {
    /**
     * Data field: false symbol
     */
    private char falseSymbol;

    /**
     * Data field: true symbol
     */
    private char trueSymbol;

    /**
     * Data field: contains all the different rows of the automaton
     */
    private ArrayList<Generation> generationsContainer = new ArrayList<>();

    /**
     * Data field: rule object
     */
    private Rule rule;

    /**
     * Data field: maximum number of rules for an automaton
     */
    private final static int MAXIMUM_NUMBER_OF_RULES = 256;

    /**
     * Create an automaton from the rule number and the initial state
     *
     * @param ruleNum   rule number
     * @param initState initial state
     */
    public Automaton(int ruleNum, boolean[] initState) {
        this.rule = new Rule(ruleNum); // Create a rule object
        this.falseSymbol = '0';
        this.trueSymbol = '1';

        // Create an initial state and then initialize it
        Generation initialState = new Generation(initState.length);
        for (int i = 0; i < initState.length; i++)
            initialState.getCellAt(i).setState(initState[i]);

        generationsContainer.add(initialState); // add the initial state to the generation container
    }

    /**
     * Create an automaton from the file name
     *
     * @param filename file containing information on the automaton
     * @throws FileNotFoundException file is not found
     */
    public Automaton(String filename) throws FileNotFoundException {
        File file = new File(filename); // Create a file

        if (file.exists()) {
            // Create a scanner for the file
            Scanner input = new Scanner(file);
            this.rule = new Rule(input.nextInt()); // scan the rule num and use it to create a new object
            this.falseSymbol = input.next().charAt(0);
            this.trueSymbol = input.next().charAt(0);


            String state = input.next();
            Generation initState = new Generation(state.length());
            for (int i = 0; i < state.length(); i++)
                initState.getCellAt(i).setState(state.charAt(i) == getTrueSymbol());

            generationsContainer.add(initState); // add the initial state to the generation container

        } else
            System.out.println("The file name : " + filename + " is nonexistent or cannot be opened.");
    }

    /**
     * Get the rule number of the automaton
     *
     * @return ruleNum
     */
    public int getRuleNum() {
        return rule.ruleNum;
    }

    /**
     * Revolve the previous generation
     *
     * @param numSteps the number of steps
     */
    public void evolve(int numSteps) {
        // Remove everything from the generationsContainer except the initial state
        for (int i = generationsContainer.size() - 1; i > 0; i--)
            generationsContainer.remove(i);

        // Evolve the automaton numSteps times
        for (int i = 0; i < numSteps; i++) {
            /* Break out of the loop if an inappropriate number of rules is passed by the user.*/
            if (getRuleNum() < 0 || getRuleNum() >= MAXIMUM_NUMBER_OF_RULES) {
                System.out.println("The rule number should be between 0 and 255.");
                break;
            }

            /* Pass the previous generation to the evolveOnce method.
             * Then evolve the previous generation to the next generation using the rule
             * */
            evolveOnce(generationsContainer.get(generationsContainer.size() - 1));
        }
    }

    /**
     * Get the total number of steps
     *
     * @return numSteps number of times the rule is applied
     */
    public int getTotalSteps() {
        return generationsContainer.size() - 1;
    }

    /**
     * Get the state
     *
     * @param stepNum step number
     * @return state state with step number setpNum
     */
    public boolean[] getState(int stepNum) {
        if (stepNum >= 0 && stepNum < generationsContainer.size()) {
            // Create an array that stores boolean values
            boolean[] state = new boolean[generationsContainer.get(stepNum).getNumberOfCells()];

            for (int i = 0; i < state.length; i++)
                state[i] = generationsContainer.get(stepNum).getCellAt(i).isState(); // gets the state of the cell and stores in the array at index i

            return state;
        } else
            return null;
    }

    /**
     * Get the string representation of the state
     *
     * @param stepNum step number
     * @return state state as a string
     */
    public String getStateString(int stepNum) {
        boolean[] state = getState(stepNum); // Return the step as an array of boolean values
        StringBuilder stateBuffer = new StringBuilder(); // Returns the string state representation

        if (state != null) {

            // Append the values of the array state to stateBuffer
            for (boolean b : state) {
                if (b)
                    stateBuffer.append(getTrueSymbol());

                else
                    stateBuffer.append(getFalseSymbol());
            }

            return stateBuffer.toString(); //
        } else
            return "";
    }

    /**
     * Get the string representation of the automaton
     *
     * @return automaton
     */
    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder(); // store the string state representation of all the generations

        for (int i = 0; i < generationsContainer.size(); i++) {
            buffer.append(getStateString(i));
            if (i != generationsContainer.size() - 1)
                buffer.append("\n");
        }

        return buffer.toString(); // return the string representation of the automaton
    }

    /**
     * Save the automaton in a file
     *
     * @param filename the file name where to save the document
     * @throws FileNotFoundException file does not exist or the file cannot be opened
     */
    public void save(String filename) throws FileNotFoundException {

        try (PrintWriter output = new PrintWriter(new File(filename))) {
            output.println(this.toString());
        }
    }

    /**
     * Get the false symbol
     *
     * @return falseSymbol
     */
    public char getFalseSymbol() {
        return falseSymbol;
    }

    /**
     * Set a new value to the falseSymbol
     *
     * @param falseSymbol the false symbol
     */
    public void setFalseSymbol(char falseSymbol) {
        this.falseSymbol = falseSymbol;
    }

    /**
     * Get the true symbol
     *
     * @return trueSymbol
     */
    public char getTrueSymbol() {
        return trueSymbol;
    }

    /**
     * Set a new value to the true symbol
     *
     * @param trueSymbol the true symbol
     */
    public void setTrueSymbol(char trueSymbol) {
        this.trueSymbol = trueSymbol;
    }

    /**
     * Evolve a generation once
     *
     * @param previousGeneration the previous generation
     */
    private void evolveOnce(Generation previousGeneration) {
        final int NUMBER_OF_CELLS_PER_ROW = previousGeneration.getNumberOfCells(); // the number of cells per generation

        // Clone the previous generation
        Generation nextGeneration = previousGeneration.clone();

        // Flip the leftmost cell and add it to nextGeneration
        nextGeneration.getCellAt(0).setState(applyRuleToCell(NUMBER_OF_CELLS_PER_ROW - 1,
                0, 1, previousGeneration));

        // Flip the rightmost cell and add it to nextGeneration
        nextGeneration.getCellAt(NUMBER_OF_CELLS_PER_ROW - 1).setState(applyRuleToCell(NUMBER_OF_CELLS_PER_ROW - 2,
                NUMBER_OF_CELLS_PER_ROW - 1, 0, previousGeneration));

        // Flip the state of the cell at index i
        for (int i = 1; i <= NUMBER_OF_CELLS_PER_ROW - 2; i++)
            nextGeneration.getCellAt(i).setState(applyRuleToCell(i - 1, i, i + 1, previousGeneration));

        // Add the nextGeneration to the list of generations
        this.generationsContainer.add(nextGeneration);
    }

    /**
     * get the state of the second cell
     * @param indexFirst the index of the first cell
     * @param indexOfSecond the index of the second cell
     * @param indexThird the index of the third cell
     * @param previousGeneration the previous generation
     * @return state the state of the cell
     */
    private boolean applyRuleToCell(int indexFirst, int indexOfSecond, int indexThird, Generation previousGeneration) {
        boolean stateOfCell1 = previousGeneration.getCellAt(indexFirst).isState();
        boolean stateOfCell2 = previousGeneration.getCellAt(indexOfSecond).isState();
        boolean stateOfCell3 = previousGeneration.getCellAt(indexThird).isState();

        return this.rule.apply(stateOfCell1, stateOfCell2, stateOfCell3);
    }
}
