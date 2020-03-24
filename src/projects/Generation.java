package projects;

/**
 * This class models the individual rows of the automaton also known as generation
 */
class Generation {
    /**
     * Data field: row
     */
    private Cell[] row;

    // I did not create a default generation because the size of generation is specified in the automaton

    /**
     * Create a generation from number of cells
     *
     * @param numberOfCells the number of cells in the row
     */
    Generation(int numberOfCells) {

        this.row = new Cell[numberOfCells];

        for (int i = 0; i < numberOfCells; i++)
            this.row[i] = new Cell();
    }

    /**
     * Get the row
     *
     * @return row
     */
    Cell[] getRow() {
        final Cell[] cloneRow = new Cell[getNumberOfCells()];

        for (int i = 0; i < cloneRow.length; i++)
            cloneRow[i] = row[i].clone();

        return cloneRow;
    }

    /**
     * Get the cell at specific index
     *
     * @param index index of the cell
     * @return cell at the index
     */
    Cell getCellAt(int index) {
        return this.row[index];
    }

    /**
     * get the number of cells in a generation
     *
     * @return numCells
     */
    int getNumberOfCells() {
        return row.length;
    }

    /**
     * Clone a Generation object.
     * I am not going to use the clone method in the object class because I have to implement the cloneable interface
     */
    public Generation clone() {
        Generation generation = new Generation(getNumberOfCells());

        for (int i = 0; i < generation.getNumberOfCells(); i++)
            generation.getRow()[i] = this.getCellAt(i).clone();

        return generation;
    }
}
