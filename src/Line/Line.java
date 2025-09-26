package Line;

import java.io.Serializable;

import Date.*;
import Exceptions.*;
import Schedule.Schedule;
import dataStructures.Entry;
import dataStructures.Iterator;
import dataStructures.Queue;

/**
 * @author Dinis Raleiras (67819) d.raleiras@campus.fct.unl.pt
 * @author Filipe Nobre (67850) fm.nobre@campus.fct.unl.pt
 * @Fase 1
 */

public interface Line extends Serializable{

	/**
	 * Return an Iterator with all the stations of the line
	 * @return Iterator with all the stations of the line
	 */
	Iterator<String> getStations();

	/**
	 * Adds a schedule to the line
	 * @param trainNumber
	 * @param schedule
	 * @throws InvalidScheduleException
	 */
	void addSchedule(int trainNumber, Queue<Entry<String, Entry<Integer, Integer>>> schedule) throws InvalidScheduleException;

	/**
	 * Return the name of the line
	 * @return the name of the line
	 */
	String getName();

	/**
	 * Remove a schedule from the line
	 * @param stationName
	 * @param date
	 * @throws ScheduleDoesNotExistException
	 */
	void removeSchedule(String stationName, DateClass date) throws ScheduleDoesNotExistException;

	/**
	 * Return the number of the best schedule
	 * @param departureStation
	 * @param arrivalStation
	 * @param date
	 * @return the train number of the best schedule
	 * @throws StationDoesNotExistException
	 * @throws ImpossibleRouteException
	 */
	Schedule bestSchedule(String departureStation, String arrivalStation, Date date) throws StationDoesNotExistException, ImpossibleRouteException;

	/**
	 * Return an Iterator of all the schedules of a station
	 * @param stationName
	 * @return an Iterator of all the schedules of a station
	 * @throws StationDoesNotExistException
	 */
	Iterator<Schedule> schedulesIterator(String stationName) throws StationDoesNotExistException;

}
