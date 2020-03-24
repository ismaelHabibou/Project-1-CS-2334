package projects;

/** This class represents the rule that governs the evolution of the automaton*/
public class Rule {
    /**
     * Data field: array of  true and false character
     */
    private boolean[] values;

    /** Data field: the rule number*/
    final int ruleNum;

    /**
     * Create a rule Object
     * @param ruleNum the rule number
     */
    public Rule(int ruleNum) {
         this.ruleNum = ruleNum;
        String binaryNumber = addLeadingZero(Integer.toBinaryString(ruleNum));

        values = new boolean[binaryNumber.length()];
        convertToCharArray(binaryNumber, values);
    }

    /**
     * Convert binary number to boolean values
     * @param binaryNumber the rule
     * @param values the boolean representation of the rule
     */
    private void convertToCharArray(String binaryNumber, boolean[] values) {
        for (int i = 0; i < binaryNumber.length(); i++) {
            values[i] = binaryNumber.charAt(i) == '1';
        }
    }

    /**
     * apply the rule
     *
     * @param cell1 state of the first cell
     * @param cell2 state of the second cell
     * @param cell3 state of the third cell
     * @return cell2 the state of cell2 after applying the rule
     */
    public boolean apply(boolean cell1, boolean cell2, boolean cell3) {
        int index = -1;

        if (cell1 && cell2 && cell3)  // 111
            index = 0;
        else if (cell1 && cell2) // 110
            index = 1;
        else if (cell1 && cell3)  // 101
            index = 2;
        else if (cell1) // 100
            index = 3;
        else if (cell2 && cell3) //011
            index = 4;
        else if (cell2) // 010
            index = 5;
        else if (cell3) // 001
            index = 6;
        else          // 000
            index = 7;

        return values[index];// return the boolean value at index
    }

    /**
     * Add leading zero th the binary string
     *
     * @param binaryString rule number as a string
     * @return rule the representation of the rule as a string
     */
    private String addLeadingZero(String binaryString) {
        final int NUMBER_OF_BINARY_DIGIT = 8;
        StringBuilder buffer = new StringBuilder();

        for (int i = 0; i < NUMBER_OF_BINARY_DIGIT - binaryString.length(); i++)
            buffer.append("0");
        buffer.append(binaryString);

        return buffer.toString();
    }
}
