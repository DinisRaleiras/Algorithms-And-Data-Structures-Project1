package Station;

/**
 * @author Dinis Raleiras (67819) d.raleiras@campus.fct.unl.pt
 * @author Filipe Nobre (67850) fm.nobre@campus.fct.unl.pt
 * @Fase 1
 */

public interface Station{
	/**
	 * Adds a line to the station
	 * @param lineName
	 */
	void addLine(String lineName);

	/**
	 * Removes a line from the station
	 * @param lineName
	 */
	void removeLine(String lineName);

	/**
	 * Returns the number of lines that pass through the station
	 * @return number of lines
	 */
	int getNumberOfLines();

	/**
	 * Returns the Name of the station
	 * @return name of the station
	 */
	String getName();
}
