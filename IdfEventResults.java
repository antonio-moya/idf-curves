import java.io.*;
import java.util.*;

class IdfEventResults {
	
	public int indexIni = Integer.MIN_VALUE;
	public int indexEnd = Integer.MIN_VALUE;
	
	public List<Double> rainfall = new ArrayList<Double>();
	public List<Double> accumRainfall = new ArrayList<Double>();
	
	public List<Double> idfMaxRainfall = new ArrayList<Double>();
	public List<Double> idfMaxRainfallAccumHours = new ArrayList<Double>();
	public List<String> idfMaxRainfallOrigin = new ArrayList<String>();
}