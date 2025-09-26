package Schedule;

import java.io.Serializable;
import dataStructures.*;
import Date.*;

/**
 * @author Dinis Raleiras (67819) d.raleiras@campus.fct.unl.pt
 * @author Filipe Nobre (67850) fm.nobre@campus.fct.unl.pt
 * @Fase 1
 */

public class ScheduleClass implements Schedule, Serializable{

	// Serial Version UID
	private static final long serialVersionUID = 0L;

	// Schedule of the train
	private List<Entry<String, Date>> schedule;

	// Number of the train
	private int trainNumber;

	/**
	 * Constructor of the ScheduleClass
	 * @param number
	 * @param schedule
	 */
	public ScheduleClass(int number, List<Entry<String, Date>> schedule) {
		this.trainNumber = number;
		this.schedule = schedule;
	}

	@Override
	public int compareTo(Schedule o) {
		Entry<String,Date> firstStop1 = schedule.getFirst();
        Entry<String,Date> firstStop2 = ((ScheduleClass) o).schedule.getFirst();
        Date d1 = firstStop1.getValue();
        Date d2 = firstStop2.getValue();
        return d1.compareTo(d2);
	}

	@Override
	public boolean begins(DateClass date) {
		return schedule.getFirst().getValue().compareTo(date) == 0;
	}

	@Override
	public int getTrainNumber() {
		return trainNumber;
	}

	@Override
	public int timeBetweenStations(String departureStation, String arrivalStation, Date date) {
		Iterator<Entry<String, Date>> it = schedule.iterator();
        boolean foundDeparture = false;
        while (it.hasNext()) {
            Entry<String, Date> stop = it.next();
            if (stop.getKey().toUpperCase().equals(departureStation.toUpperCase())) {
                foundDeparture = true;
            }else if(foundDeparture && stop.getKey().toUpperCase().equals(arrivalStation.toUpperCase())){
                return stop.getValue().difOfTime(date);
            }
        }
        return -1;
	}

	@Override
	public Iterator<Entry<String, Date>> stopsIterator() {
		return schedule.iterator();
	}
	
	
}
