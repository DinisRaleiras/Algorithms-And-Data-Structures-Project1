package Line;

import dataStructures.*;
import Date.*;
import Exceptions.*;
import Schedule.*;

/**
 * @author Dinis Raleiras (67819) d.raleiras@campus.fct.unl.pt
 * @author Filipe Nobre (67850) fm.nobre@campus.fct.unl.pt
 * @Fase 1
 */

public class LineClass implements Line{
	// Serial Version UID
	private static final long serialVersionUID = 0L;

	// Name of the line
	private String name;

	// List with the name of the stations
	private List<String> stationsName;

	// List with the schedules that start in the first station
	private SortedList<Schedule> schedulesHead;

	// List with the schedules that start in the last station
	private SortedList<Schedule> schedulesTail;

	/**
	 * Constructor of the class
	 * @param name
	 * @param stationsName
	 */
	public LineClass(String name, List<String> stationsName) {
		this.name = name;
		this.stationsName = stationsName;
		schedulesHead = new SortedDoubleList<>();
		schedulesTail = new SortedDoubleList<>();
	}

	@Override
	public Iterator<String> getStations() {
		return stationsName.iterator();
	}

	@Override
	public void addSchedule(int trainNumber, Queue<Entry<String, Entry<Integer, Integer>>> schedule)
			throws InvalidScheduleException {
		if(schedule.front().getKey().toUpperCase().equals(stationsName.getFirst().toUpperCase())) {
			addScheduleHead(trainNumber, schedule);
		}else if(schedule.front().getKey().toUpperCase().equals(stationsName.getLast().toUpperCase())) {
			addScheduleTail(trainNumber, schedule);
		}else {
			throw new InvalidScheduleException();
		}
	}

	/**
	 * Adds the schedule thats starts in the head of the line
	 * @param trainNumber
	 * @param schedule
	 * @throws InvalidScheduleException
	 */
	private void addScheduleHead(int trainNumber, Queue<Entry<String, Entry<Integer, Integer>>> schedule)
			throws InvalidScheduleException {
		List<Entry<String, Date>> scheduleList = new DoubleList<>();
	       Iterator<String> it = getStations();
	       Date lastDate = null;
	       while(it.hasNext()) {
	           String stationName = it.next();
	           if (schedule.isEmpty() || !stationName.toUpperCase().equals(schedule.front().getKey().toUpperCase())) {
	               continue;
	           }
	           Entry<String, Entry<Integer, Integer>> stop = schedule.dequeue();
	           Date date = new DateClass(stop.getValue().getKey(), stop.getValue().getValue());
	           if (lastDate != null && lastDate.compareTo(date) >= 0) {
	               throw new InvalidScheduleException();
	           } else {
	               lastDate = date;
	           }
	           scheduleList.addLast(new EntryClass<>(stationName, date));
	       }
	       if(schedule.isEmpty()){
	           Schedule s = new ScheduleClass(trainNumber, scheduleList);
	           schedulesHead.add(s);
	       }else{
	           throw new InvalidScheduleException();
	      }
	}

	/**
	 * Adds the schedule that starts in the tail of the line
	 * @param trainNumber
	 * @param schedule
	 * @throws InvalidScheduleException
	 */
	private void addScheduleTail(int trainNumber, Queue<Entry<String, Entry<Integer, Integer>>> schedule) throws InvalidScheduleException {
        List<Entry<String, Date>> scheduleList = new DoubleList<>();
        TwoWayIterator<String> it = stationsName.twoWayIterator();
        it.fullForward();
        Date lastDate = null;
        while (it.hasPrevious()) {
            String stationName = it.previous();
            if (schedule.isEmpty() || !stationName.toUpperCase().equals(schedule.front().getKey().toUpperCase())) {
                continue;
            }
            Entry<String, Entry<Integer, Integer>> stop = schedule.dequeue();
            Date date = new DateClass(stop.getValue().getKey(), stop.getValue().getValue());
            if (lastDate != null && lastDate.compareTo(date) >= 0) {
                throw new InvalidScheduleException();
            } else {
                lastDate = date;
            }
            scheduleList.addLast(new EntryClass<>(stationName, date));
        }
        if (schedule.isEmpty()) {
            Schedule s = new ScheduleClass(trainNumber, scheduleList);
            schedulesTail.add(s);
        } else {
            throw new InvalidScheduleException();
        }

    }
	
	 @Override
	    public boolean equals(Object o){
	        if(o == this)
	            return true;
	        if(!(o instanceof Line))
	            return false;
	        else{
	            return this.name.toUpperCase().equals(((Line)o).getName().toUpperCase());
	        }
	    }

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void removeSchedule(String stationName, DateClass date) throws ScheduleDoesNotExistException {
		if(stationName.toUpperCase().equals(stationsName.getFirst().toUpperCase())) {
			removeScheduleHead(date);
		}else if(stationName.toUpperCase().equals(stationsName.getLast().toUpperCase())) {
			removeScheduleTail(date);
		}else {
			throw new ScheduleDoesNotExistException();
		}
	}

	/**
	 * Removes the schedule from the tail that begins in the given date
	 * @param date
	 * @throws ScheduleDoesNotExistException
	 */
	private void removeScheduleTail(DateClass date) throws ScheduleDoesNotExistException{
		Iterator<Schedule> it = schedulesTail.iterator();
        while(it.hasNext()){
            Schedule schedule = it.next();
            if(schedule.begins(date)){
                schedulesTail.remove(schedule);
                return;
            }
        }
        throw new ScheduleDoesNotExistException();
	}

	/**
	 * Removes the schedule from the head that begins in the given date
	 * @param date
	 * @throws ScheduleDoesNotExistException
	 */
	private void removeScheduleHead(DateClass date) throws ScheduleDoesNotExistException{
        Iterator<Schedule> it = schedulesHead.iterator();
        while(it.hasNext()){
            Schedule schedule = it.next();
            if(schedule.begins(date)){
                schedulesHead.remove(schedule);
                return;
            }
        }
        throw new ScheduleDoesNotExistException();
		
	}

	@Override
	public Schedule bestSchedule(String departureStation, String arrivalStation, Date date)
			throws StationDoesNotExistException, ImpossibleRouteException {
		if(!hasStation(departureStation)) {
			throw new StationDoesNotExistException();
		}else if(!hasStation(arrivalStation)) {
			throw new ImpossibleRouteException();
		}
        Iterator<Schedule> it = schedulesHead.iterator();
        Schedule bestSchedule = null;
        int bestTime = Integer.MAX_VALUE;
        while(it.hasNext()){
            Schedule schedule = it.next();
            int time = schedule.timeBetweenStations(departureStation, arrivalStation, date);
            if(time >= 0 && time < bestTime){
                bestTime = time;
                bestSchedule = schedule;
            }

        }
        it = schedulesTail.iterator();
        while(it.hasNext()){
            Schedule schedule = it.next();
            int time = schedule.timeBetweenStations(departureStation, arrivalStation, date);
            if(time >= 0 && time < bestTime){
                bestTime = time;
                bestSchedule = schedule;
            }
        }
        if(bestSchedule == null){
            throw new ImpossibleRouteException();
        }else{
            return bestSchedule;
        }
	}

	/**
	 * Checks if the given station exists in the line
	 * @param departureStation
	 * @return true if the station exists, false otherwise
	 */
	private boolean hasStation(String departureStation) {
		Iterator<String> it = getStations();
		while(it.hasNext()) {
			if(departureStation.toUpperCase().equals(it.next().toUpperCase())) {
				return true;
			}
		}
		return false;
	}


	@Override
	public Iterator<Schedule> schedulesIterator(String stationName) throws StationDoesNotExistException {
        if (!hasStation(stationName)) {
            throw new StationDoesNotExistException();
        } else if (stationsName.getFirst().toUpperCase().equals(stationName.toUpperCase())) {
            return schedulesHead.iterator();
        } else if (stationsName.getLast().toUpperCase().equals(stationName.toUpperCase())) {
            return schedulesTail.iterator();
        } else {
            throw new StationDoesNotExistException();
        }
	}

}
