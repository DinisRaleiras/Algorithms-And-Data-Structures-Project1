package Schedule;

import Date.Date;
import Date.DateClass;
import dataStructures.Entry;
import dataStructures.Iterator;

/**
 * @author Dinis Raleiras (67819) d.raleiras@campus.fct.unl.pt
 * @author Filipe Nobre (67850) fm.nobre@campus.fct.unl.pt
 * @Fase 1
 */

public interface Schedule extends Comparable<Schedule>{

	/**
	 * @return true if the schedule begins at the given date
	 */
	boolean begins(DateClass date);

	/**
	 * @return the number of the train
	 */
	int getTrainNumber();

	/**
	 * Returns the time between two stations
	 * @param departureStation
	 * @param arrivalStation
	 * @param date
	 * @return the time between two stations
	 */
	int timeBetweenStations(String departureStation, String arrivalStation, Date date);

	/**
	 * Returns an iterator for the schedule
	 * @return an iterator for the schedule
	 */
	Iterator<Entry<String, Date>> stopsIterator();

}
