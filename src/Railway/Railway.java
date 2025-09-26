package Railway;
import java.io.Serializable;

import dataStructures.*;
import Exceptions.*;
import Schedule.Schedule;

/**
 * @author Dinis Raleiras (67819) d.raleiras@campus.fct.unl.pt
 * @author Filipe Nobre (67850) fm.nobre@campus.fct.unl.pt
 * @Fase 1
 */

public interface Railway extends Serializable{
	/**
	 * Adds a new line to the railway
	 * @param lineName
	 * @param stationsName
	 * @throws LineAlreadyExistsException
	 */
	void addLine(String lineName, Queue<String> stationsName) throws LineAlreadyExistsException;

	/**
	 * Removes a line from the railway
	 * @param lineName
	 * @throws LineDoesNotExistException
	 */
	void removeLine(String lineName) throws LineDoesNotExistException;

	/**
	 * Returns an iterator of Strings with the names of the stations of a line
	 * @param lineName
	 * @return Iterator<String> with the names of the stations of a line
	 * @throws LineDoesNotExistException
	 */
	Iterator<String> lineStationsIterator(String lineName) throws LineDoesNotExistException;

	/**
	 * Adds a Schedule to a line
	 * @param lineName
	 * @param trainNumber
	 * @param schedule
	 * @throws LineDoesNotExistException
	 * @throws InvalidScheduleException
	 */
	void addSchedule(String lineName, int trainNumber, Queue<Entry<String, Entry<Integer, Integer>>> schedule) throws LineDoesNotExistException, InvalidScheduleException;

	/**
	 * Removes a Schedule from a line
	 * @param lineName
	 * @param stationName
	 * @param hours
	 * @param minutes
	 * @throws LineDoesNotExistException
	 * @throws ScheduleDoesNotExistException
	 */
	void removeSchedule(String lineName, String stationName, int hours, int minutes) throws LineDoesNotExistException, ScheduleDoesNotExistException;

	/**
	 * Returns the best schedule of a certain line to a given path until a certain date
	 * @param lineName
	 * @param departureStation
	 * @param arrivalStation
	 * @param hours
	 * @param minutes
	 * @return Schedule that his the best schedule
	 * @throws LineDoesNotExistException
	 * @throws StationDoesNotExistException
	 * @throws ImpossibleRouteException
	 */
	Schedule bestSchedule(String lineName, String departureStation, String arrivalStation, int hours, int minutes) throws LineDoesNotExistException, StationDoesNotExistException, ImpossibleRouteException;


	/**
	 * Returns an iterator of the Schedules of a line in a station
	 * @param lineName
	 * @param stationName
	 * @return Iterator<Schedule> with the Schedules of a line in a station
	 * @throws LineDoesNotExistException
	 * @throws StationDoesNotExistException
	 */
	Iterator<Schedule> lineSchedulesIterator(String lineName, String stationName) throws LineDoesNotExistException, StationDoesNotExistException;
	
}
