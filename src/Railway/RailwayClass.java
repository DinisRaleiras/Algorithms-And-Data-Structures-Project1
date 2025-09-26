package Railway;
import Date.*;
import Exceptions.*;
import Line.*;
import Schedule.Schedule;
import Station.*;
import dataStructures.*;

/**
 * @author Dinis Raleiras (67819) d.raleiras@campus.fct.unl.pt
 * @author Filipe Nobre (67850) fm.nobre@campus.fct.unl.pt
 * @Fase 1
 */

public class RailwayClass implements Railway{

	// Serial Version UID
	private static final long serialVersionUID = 0L;

	// Stores the lines of the railway
	private SearchableList<Line> lines;

	// Stores the stations of the railway
	private SearchableList<Station> stations;

	/**
	 * Constructor of the RailwayClass
	 */
	public RailwayClass() {
		lines = new SearchableListClass<>();
		stations = new SearchableListClass<>();
	}


	@Override
	public void addLine(String lineName, Queue<String> stationsName) throws LineAlreadyExistsException {
		Line l = lines.findElement(new LineClass(lineName, new DoubleList<>()));
		if(l != null) {
			throw new LineAlreadyExistsException();
		}
		SearchableList<String> lineStations = new SearchableListClass<>();
        while(!stationsName.isEmpty()){
            String stationName = stationsName.dequeue();
            Station s = stations.findElement(new StationClass(stationName));
            if(s == null) {
            	s = new StationClass(stationName);
            	stations.addLast(s);
            }
            lineStations.addLast(stationName);
            s.addLine(lineName);
        }
        lines.addLast(new LineClass(lineName, lineStations));
        
	}


	@Override
	public void removeLine(String lineName) throws LineDoesNotExistException {
		Line l = lines.findElement(new LineClass(lineName, new DoubleList<>()));
		if(l == null) {
			throw new LineDoesNotExistException();
		}
		lines.remove(l);
        Iterator<String> it = l.getStations();
        while(it.hasNext()){
            String stationName = it.next();
            Station s = stations.findElement(new StationClass(stationName));
            s.removeLine(lineName);
            if((s.getNumberOfLines() == 0)){
                stations.remove(s);
            }
        }
	}


	@Override
	public Iterator<String> lineStationsIterator(String lineName) throws LineDoesNotExistException {
		Line l = lines.findElement(new LineClass(lineName, new SearchableListClass<>()));
        if(l == null){
            throw new LineDoesNotExistException();
        }
        return l.getStations();
        
	}


	@Override
	public void addSchedule(String lineName, int trainNumber, Queue<Entry<String, Entry<Integer, Integer>>> schedule)
			throws LineDoesNotExistException, InvalidScheduleException {
		Line l = lines.findElement(new LineClass(lineName, new SearchableListClass<>()));
        if(l == null){
            throw new LineDoesNotExistException();
        }
        l.addSchedule(trainNumber, schedule);
		
	}


	@Override
	public void removeSchedule(String lineName, String stationName, int hours, int minutes)
			throws LineDoesNotExistException, ScheduleDoesNotExistException {
		Line l = lines.findElement(new LineClass(lineName, new SearchableListClass<>()));
        if(l == null){
            throw new LineDoesNotExistException();
        }
        l.removeSchedule(stationName, new DateClass(hours, minutes));
	}


	@Override
	public Schedule bestSchedule(String lineName, String departureStation, String arrivalStation, int hours, int minutes)
			throws LineDoesNotExistException, StationDoesNotExistException, ImpossibleRouteException {
		Line l = lines.findElement(new LineClass(lineName, new SearchableListClass<>()));
        if(l == null){
            throw new LineDoesNotExistException();
        }
        return l.bestSchedule(departureStation, arrivalStation, new DateClass(hours, minutes));
	}

	
	@Override
	public Iterator<Schedule> lineSchedulesIterator(String lineName, String stationName)
			throws LineDoesNotExistException, StationDoesNotExistException {
		Line l = lines.findElement(new LineClass(lineName, new SearchableListClass<>()));
        if(l == null){
            throw new LineDoesNotExistException();
        }
		return l.schedulesIterator(stationName);
	}
	
	

}
