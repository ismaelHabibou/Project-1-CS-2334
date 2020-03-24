package projects;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/** This is the driver class*/

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        // write your code here
        boolean[] initialState = {false, false, false, false, false, false, false, true, false, false, false, false, false, false, false};
        Automaton automaton = new Automaton(22, initialState);

        // Test the rule
        if (automaton.getRuleNum() != 22)
            System.out.println("getRuleNum() failed");
        if (automaton.getTotalSteps() != 0)
            System.out.println("getTotalNumber() of steps failed");
        if (automaton.getFalseSymbol() != '0')
            System.out.println("getFalseCharacter() failed");
        if (automaton.getTrueSymbol() != '1')
            System.out.println("getTrueCharacter() failed");
        automaton.save("C:\\Users\\Admin\\Desktop\\happpy\\good.txt");

        // Create another constructor from a file
        Automaton fileAuto = new Automaton("C:\\Users\\Admin\\Desktop\\happpy\\happy.txt");
        if (fileAuto.getRuleNum() != 22)
            System.out.println("getRuleNum() failed");
        if (fileAuto.getTotalSteps() != 0)
            System.out.println("getTotalNumber() of steps failed");
        if (fileAuto.getFalseSymbol() != '0')
            System.out.println("getFalseCharacter() failed");
        if (fileAuto.getTrueSymbol() != '1')
            System.out.println("getTrueCharacter() failed");
        // fileAuto.evolve(2);
        fileAuto.setTrueSymbol('\u25A1');
        fileAuto.setFalseSymbol('\u25A0');

        fileAuto.evolve(30);
        System.out.println(fileAuto.toString());

    }
}
