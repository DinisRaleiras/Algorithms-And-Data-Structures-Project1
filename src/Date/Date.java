package Date;
/**
 * @author Dinis Raleiras (67819) d.raleiras@campus.fct.unl.pt
 * @author Filipe Nobre (67850) fm.nobre@campus.fct.unl.pt
 * @Fase 1
 */
public interface Date extends Comparable<Date>{
    /**
     * Returns the hour of the date.
     * @return the hour of the date.
     */
    int getHour();

    /**
     * Returns the minutes of the date.
     * @return the minutes of the date.
     */
    int getMinutes();

    /**
     * Returns the difference of time between the date and the parameter.
     * @param date the date to compare with.
     * @return the difference of time between the date and the parameter.
     */
    int difOfTime(Date date);

    /**
     * Returns a string representation of the date.
     * @return a string representation of the date.
     */
    String toString();

}