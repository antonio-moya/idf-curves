import java.io.*;
import java.util.*;
import java.util.stream.*;

class IDF {
	
	public static void main(String[] args) {
		System.out.println("Calculation of Internsity-Duration-Frequency curves.");
		
		// initializing double array
		List<Double> arr = GetObservedRainfallData();
		
		IDF idf = new IDF();
		idf.generateEpisodeCurve(arr);
		
		// let us print the values
		System.out.println("Actual values: ");
		for (double value : arr) {
			//System.out.println("Value = " + value);
		}
	}
	
	
	
	
	private static List<Double> GetObservedRainfallData() {
		List<Double> ret = Arrays.asList( 0.1, 0.0, 0.7,3.1,0.4,0.2,0.2,2.5,2.1,1.0,0.4,1.7,2.3,0.9,1.2,1.3,0.8,1.1,1.2,0.9,1.6,1.1,1.7,1.3,0.8,1.9,1.7,0.9,0.7,0.4,1.4,0.8,1.0,1.2,1.2,1.0,1.2,0.6,0.8,1.1,1.0,1.3,0.6,0.3,0.1,0.2,0.0,0.0,0.3,0.3,0.1,0.4,0.1,0.3,0.7,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.1,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.1,0.1,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.2,0.2,0.1,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.1,0.9,0.7,0.2,0.5,1.0,1.4,0.6,2.7,3.0,1.0,0.9,0.9,0.9,2.5,1.6,0.5,0.4,0.7,0.6,0.6,0.5,0.7,1.0,0.2,0.5,1.2,1.1,0.5,0.5,0.4,0.1,0.1,0.7,1.3,2.8,1.6,2.5,2.5,2.7,1.0,1.4,0.9,0.7,3.1,0.4,0.2,0.2,2.5,2.1,1.0,0.4,1.7,2.3,0.9,1.2,1.3,0.8,1.1,1.2,0.9,1.6,1.1,1.7,1.3,0.8,1.9,1.7,0.9,0.7,0.4,1.4,0.8,1.0,1.2,1.2,1.0,1.2,0.6,0.8,1.1,1.0,1.3,0.6,0.3,0.1,0.2,0.0,0.0,0.3,0.3,0.1,0.4,0.1,0.3,0.7,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.1,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.1,0.1,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.2,0.2,0.1,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.1,0.9,0.7,0.2,0.5,1.0,1.4,0.6,2.7,3.0,1.0,0.9,0.9,0.9,2.5,1.6,0.5,0.4,0.7,0.6,0.6,0.5,0.7,1.0,0.2,0.5,1.2,1.1,0.5,0.5,0.4,0.1,0.1,0.7,1.3,2.8,1.6,2.5,2.5,2.7,1.0,1.4,0.9,0.7,3.1,0.4,0.2,0.2,2.5,2.1,1.0,0.4,1.7,2.3,0.9,1.2,1.3,0.8,1.1,1.2,0.9,1.6,1.1,1.7,1.3,0.8,1.9,1.7,0.9,0.7,0.4,1.4,0.8,1.0,1.2,1.2,1.0,1.2,0.6,0.8,1.1,1.0,1.3,0.6,0.3,0.1,0.2,0.0,0.0,0.3,0.3,0.1,0.4,0.1,0.3,0.7,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.1,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.1,0.1,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.2,0.2,0.1,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.1,0.9,0.7,0.2,0.5,1.0,1.4,0.6,2.7,3.0,1.0,0.9,0.9,0.9,2.5,1.6,0.5,0.4,0.7,0.6,0.6,0.5,0.7,1.0,0.2,0.5,1.2,1.1,0.5,0.5,0.4,0.1,0.1,0.7,1.3,2.8,1.6,2.5,2.5,2.7,1.0,1.4,0.9,0.7,3.1,0.4,0.2,0.2,2.5,2.1,1.0,0.4,1.7,2.3,0.9,1.2,1.3,0.8,1.1,1.2,0.9,1.6,1.1,1.7,1.3,0.8,1.9,1.7,0.9,0.7,0.4,1.4,0.8,1.0,1.2,1.2,1.0,1.2,0.6,0.8,1.1,1.0,1.3,0.6,0.3,0.1,0.2,0.0,0.0,0.3,0.3,0.1,0.4,0.1,0.3,0.7,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.1,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.1,0.1,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.2,0.2,0.1,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.1,0.9,0.7,0.2,0.5,1.0,1.4,0.6,2.7,3.0,1.0,0.9,0.9,0.9,2.5,1.6,0.5,0.4,0.7,0.6,0.6,0.5,0.7,1.0,0.2,0.5,1.2,1.1,0.5,0.5,0.4,0.1,0.1,0.7,1.3,2.8,1.6,2.5,2.5,2.7,1.0,1.4,0.9,0.7,3.1,0.4,0.2,0.2,2.5,2.1,1.0,0.4,1.7,2.3,0.9,1.2,1.3,0.8 );
		//List<Double> ret = Arrays.asList( 2.0, 0.1,0.01,0.01, 0.01, 1.0, 0.0,0.0,0.0, 0.0,0.1, 0.0, 0.7,3.1,0.4,0.2,0.0,0.0,0.0,0.0,0.0,0.0,0.9 );
		return ret;
	}
	
	// Precipitación mínima total para considerar un evento
	public static final double MINIMUM_EVENT_RAIN_IN_MM = 2.0;
	public int stepMinutes = 10;
	// Número de minutos sin precipitación necesarios para considerar el final de un evento
	public int MAX_MINUTES_WITHOUT_RAIN_TO_CLOSE_AN_EVENT = 120;
	// Valor máximo de precipitación considerado "sin lluvia"
	public double WITHOUT_RAIN_MAX_VALUE = 0.2;
	
	private ArrayList<Integer[]> generateEpisodeCurve(List<Double> rainfallValues) {
		
		ArrayList<Integer[]> events = new ArrayList<Integer[]>();
		
		int stepsWithoutRainToFinishAnEevent = this.MAX_MINUTES_WITHOUT_RAIN_TO_CLOSE_AN_EVENT / this.stepMinutes;
		int currentIndex = 0;
		
		while (currentIndex <= rainfallValues.size()) {

			// Siguiente inicio de evento: siguiente elemento con precipìtación mayor que cero
			int eventStart = IntStream.range(currentIndex, rainfallValues.size())
				.filter(i -> rainfallValues.get(i)>0.0)
				.findFirst().orElse(-1);
			if (eventStart == -1) break;
			
			// Obtención del momento final del evento
			int eventEnd = IntStream.range(eventStart+1, rainfallValues.size())
				.filter(i -> rainfallValues.stream().skip(i-1).limit(stepsWithoutRainToFinishAnEevent).reduce(0.0,(a,b)->a+b) <= this.WITHOUT_RAIN_MAX_VALUE)
				.findFirst()
				.orElse(rainfallValues.size()-1);
			
			//compruebo si la lluvia del intervalo generado supera el limite para ser considerada evento.
			double sumEpisode = rainfallValues.stream().skip(eventStart).limit(1+eventEnd-eventStart).reduce(0.0, (a, b) -> a + b );
			if (sumEpisode >= MINIMUM_EVENT_RAIN_IN_MM) {
				System.out.println("ADDING EVENT add start:" + eventStart + " end:" + eventEnd + " sum:" + sumEpisode);
				Integer ep[] = {eventStart,eventEnd};
				events.add(ep);
			}
			
			currentIndex = eventEnd + 1;
		}
		
		return events;
	}
	
	public int findIndex(int[] arr,int value) {
        int k = -1;
        for(int i = 0;i < arr.length; i++){
            if(arr[i] == value){
                k = i;
                break;
            }
        }
    return k;
}
}