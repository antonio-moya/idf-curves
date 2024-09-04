import java.io.*;
import java.util.*;
import java.util.stream.*;

class IDF {
	
	//idf-rainfall Pluviómetro en F. de Agreda.xlsx
	// resultados para cada evento:
	//	IDF-accum-rainfall	IDF-max-rainfall	IDF-max-rainfall-accum-hours	IDF-max-rainfall-origin
	
	// Precipitación mínima total para considerar un evento
	public double MINIMUM_EVENT_RAIN_IN_MM = Double.NaN;
	public int STEP_IN_MINUTES = Integer. MIN_VALUE;
	// Número de minutos sin precipitación necesarios para considerar el final de un evento
	public int MAX_MINUTES_WITHOUT_RAIN_TO_CLOSE_AN_EVENT = Integer. MIN_VALUE;
	// Valor máximo de precipitación considerado "sin lluvia"
	public double WITHOUT_RAIN_MAX_VALUE = Double.NaN;
	// Lista de valores de precipitación en cada intervalo temporal consierado
	public List<Double> RAINFALL_VALUES = null;
	// Índice de la lista de valores de precipitación considerado el último valor observado. A partir de ahí se consideran valores de precipitación prevista.
	public int INDEX_OF_TOF = Integer. MIN_VALUE;
	/*
	 * Contructor con parámetros de cálculo de curvas IDF por defecto.
	 */
	public IDF(int stepMinutes, List<Double> rainfallValues, int indexOfToF) {
		this(stepMinutes, rainfallValues, indexOfToF, 2.0, 120, 0.0);
	}
	
	/*
	 * Constructor estableciendo todos los parámetros para el cálculo de curvas IDF
	 */
	public IDF(int stepMinutes, List<Double> rainfallValues, int indexOfToF, double MinimumEventRain, int MaxMinutesWithoutRainToCloseEvent, double NoRainValue) {
		this.STEP_IN_MINUTES = stepMinutes;
		this.RAINFALL_VALUES = rainfallValues;
		this.INDEX_OF_TOF = indexOfToF;
		this.MINIMUM_EVENT_RAIN_IN_MM = MinimumEventRain;
		this.MAX_MINUTES_WITHOUT_RAIN_TO_CLOSE_AN_EVENT = MaxMinutesWithoutRainToCloseEvent;
		this.WITHOUT_RAIN_MAX_VALUE = NoRainValue;
	}
	
	public void calculate() {
		
		int stepsWithoutRainToFinishAnEevent = this.MAX_MINUTES_WITHOUT_RAIN_TO_CLOSE_AN_EVENT / this.STEP_IN_MINUTES;
		ArrayList<Integer[]> events = IDF.findRainfallEvents(this.RAINFALL_VALUES, stepsWithoutRainToFinishAnEevent, this.WITHOUT_RAIN_MAX_VALUE, this.MINIMUM_EVENT_RAIN_IN_MM);
		System.out.println("Eventos detectados: " + events.size());
		for( Integer[] event : events) {
			System.out.println("	EVENT start:" + event[0] + " end:" + event[1]);
			
			// Get current event rainfall series
			int eventLength = event[1]-event[0];
			List<Double> currentEventRainfall = this.RAINFALL_VALUES.stream().skip(event[0]).limit(eventLength).collect(Collectors.toList());
			
			// Get rainfall cummulative sum
			List<Double> accumRainfall = IntStream.range(0, currentEventRainfall.size())
				.mapToObj(i -> currentEventRainfall.subList(0,i+1).stream().mapToDouble(Double::doubleValue).sum())
				.collect(Collectors.toList());
			//System.out.println("		ACCUM RAINFALL:");
			//for (double value : accumRainfall) {
			//	System.out.println("			" + value);
			//}
			
			int currentEventTofIndex = this.INDEX_OF_TOF - event[0];
			IDF.generateRainfallIDFAcummulations(currentEventTofIndex, this.STEP_IN_MINUTES, currentEventRainfall);
			
			// Resultados del evento actual
				// => IDF-accum-rainfall => accumRainfall
				// => IDF-max-rainfall => 
		}
		
		// Aquí: componer una serie única con todos los episodios
	}
	
	/*
	 * Detección de los episodios de precipitación existentes en la serie indicada
	 */
	private static ArrayList<Integer[]> findRainfallEvents(List<Double> rainfallValues, int stepsWithoutRainToFinishAnEevent, double WITHOUT_RAIN_MAX_VALUE, double MINIMUM_EVENT_RAIN_IN_MM) {
		
		ArrayList<Integer[]> events = new ArrayList<Integer[]>();
		
		int currentIndex = 0;
		
		while (currentIndex <= rainfallValues.size()) {

			// Siguiente inicio de evento: siguiente elemento con precipìtación mayor que cero
			int eventStart = IntStream.range(currentIndex, rainfallValues.size())
				.filter(i -> rainfallValues.get(i)>WITHOUT_RAIN_MAX_VALUE)
				.findFirst().orElse(-1);
			if (eventStart == -1) break;
			
			// Obtención del momento final del evento
			int eventEnd = IntStream.range(eventStart+1, rainfallValues.size())
				.filter(i -> rainfallValues.stream().skip(i-1).limit(stepsWithoutRainToFinishAnEevent).reduce(0.0,(a,b)->a+b) <= WITHOUT_RAIN_MAX_VALUE)
				.findFirst()
				.orElse(rainfallValues.size()-1);
			
			//compruebo si la lluvia del intervalo generado supera el limite para ser considerada evento.
			double sumEpisode = rainfallValues.stream().skip(eventStart).limit(1+eventEnd-eventStart).reduce(0.0, (a, b) -> a + b );
			if (sumEpisode >= MINIMUM_EVENT_RAIN_IN_MM) {
				Integer ep[] = {eventStart,eventEnd};
				events.add(ep);
			}
			
			currentIndex = eventEnd + 1;
		}
		
		return events;
	}
	
	private static void generateRainfallIDFAcummulations(int indexToF, int StepInMinutes, List<Double> eventRainfall) {
		
		List<Double> IdfMaxRainfall = new ArrayList<Double>();
		List<Double> IdfMaxRainfallAccumHours = new ArrayList<Double>();
		List<String> IdfMaxRainfallOrigin = new ArrayList<String>();
		
		int durationMinutes = StepInMinutes;
		
		for (int group = 1; group <= eventRainfall.size(); group++) {
			List<Double> maxAccumRainfall = new ArrayList<Double>();
			List<String> maxAccumRainfallOrigin = new ArrayList<String>();
			
			for(int j = 0; j < eventRainfall.size(); j++) {
				
				List<Double> tempArray = eventRainfall.stream().skip(j).limit(group).collect(Collectors.toList());
				DoubleSummaryStatistics s1 = tempArray.stream().mapToDouble(Double::doubleValue).summaryStatistics();
				maxAccumRainfall.add( s1.getSum() );
				
				int indexIni = j;
				int indexEnd = indexIni+group;
				if (indexEnd<=indexToF) {
					maxAccumRainfallOrigin.add("OBS");
				}
				else {
					if (indexIni>=indexToF) maxAccumRainfallOrigin.add("PREV");
					else maxAccumRainfallOrigin.add("MIX");
				}
			}
			
			DoubleSummaryStatistics s2 = maxAccumRainfall.stream().mapToDouble(d->d).summaryStatistics();
			int indexMaxValue = maxAccumRainfall.indexOf(s2.getMax());
			
			IdfMaxRainfall.add( s2.getMax() );
			IdfMaxRainfallAccumHours.add( (durationMinutes/60.0) );
			IdfMaxRainfallOrigin.add( maxAccumRainfallOrigin.get(indexMaxValue) );
			
			durationMinutes += StepInMinutes;
		}
		
		for(int k = 0; k < IdfMaxRainfall.size(); k++) {
			System.out.println("			rain(mm)=" + IdfMaxRainfall.get(k) + " d(HH):" + IdfMaxRainfallAccumHours.get(k) + " ORI:" + IdfMaxRainfallOrigin.get(k) );
		}
		
	}
	
}