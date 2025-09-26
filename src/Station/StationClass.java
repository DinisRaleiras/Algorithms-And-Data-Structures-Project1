package Station;

import java.io.Serializable;
import dataStructures.*;

/**
 * @author Dinis Raleiras (67819) d.raleiras@campus.fct.unl.pt
 * @author Filipe Nobre (67850) fm.nobre@campus.fct.unl.pt
 * @Fase 1
 */

public class StationClass implements Station, Serializable{

	private static final long serialVersionUID = 0L;

	
	private SortedList<String> lines;
	
	private String name;
	
	public StationClass(String name) {
		this.name = name;
		lines = new SortedDoubleList<>();
	}

	@Override
	public void addLine(String lineName) {
		lines.add(lineName);
	}

	@Override
	public void removeLine(String lineName) {
		lines.remove(lineName);
	}

	@Override
	public int getNumberOfLines() {
		return lines.size();
	}
	
	@Override
    public boolean equals(Object o){
        if(o == this)
            return true;
        if(!(o instanceof Station))
            return false;
        else{
            return this.name.toUpperCase().equals(((Station)o).getName().toUpperCase());
        }
    }

	@Override
	public String getName() {
		return name;
	}
	
}
