package projects;

/**
 * This class models the cell in the automaton
 */
public class Cell {
    /**
     * Data fields: state
     */
    private boolean state;

    /** Create a default cell object*/
    public Cell(){
        this(false);
    }

    /**
     * Create a cell with specific state
     *
     * @param state state of the cell
     */
    public Cell(boolean state) {
        this.state = state;
    }

    /**
     * Get the state of the cell
     *
     * @return state
     */
    public boolean isState() {
        return state;
    }

    /**
     * Set the state of the cell
     *
     * @param state the current state of the cell
     */
    public void setState(boolean state) {
        this.state = state;
    }

    /**
     * Clone a cell
     *
     * @return cell
     */
    public Cell clone() {

        return new Cell(isState());
    }
}
