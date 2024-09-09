import java.io.*;
import java.util.*;

class IdfResults {
	
	public int timeStepInMinutes = Integer. MIN_VALUE;
	
	public List<Double> rainfall;			// Entrada 
	public List<IdfEventResults> events = new ArrayList<IdfEventResults>();	
	
	public IdfResults(int timeStepInMinutes, List<Double> rainfall) {
		this.timeStepInMinutes = timeStepInMinutes;
		this.rainfall = rainfall;
	}
	
	// Precipitación máxima: Serie compuesta de todos los episodios
	public List<Double> getMaxRainfall() {
		List<Double> ret = new ArrayList<Double>(Collections.nCopies(this.rainfall.size(), null));
		for( IdfEventResults event : this.events) {
			List<Double> tmp = event.idfMaxRainfall;
			for(int i=0;i<tmp.size(); i++) {
				ret.set(i+event.indexIni, tmp.get(i));
			}
		}
		return ret;
	}
	
	// Tiempo en horas de la precipitación máxima: Serie compuesta de todos los episodios
	public List<Double> getMaxRainfallAccumHours() {
		List<Double> ret = new ArrayList<Double>(Collections.nCopies(this.rainfall.size(), null));
		for( IdfEventResults event : this.events) {
			List<Double> tmp = event.idfMaxRainfallAccumHours;
			for(int i=0;i<tmp.size(); i++) {
				ret.set(i+event.indexIni, tmp.get(i));
			}
		}
		return ret;
	}
	
	public List<String> getMaxRainfallOrigin() {
		List<String> ret = new ArrayList<String>(Collections.nCopies(this.rainfall.size(), null));
		for( IdfEventResults event : this.events) {
			List<String> tmp = event.idfMaxRainfallOrigin;
			for(int i=0;i<tmp.size(); i++) {
				ret.set(i+event.indexIni, tmp.get(i));
			}
		}
		return ret;
	}
	
}