import java.io.*;
import java.util.*;

class IDF {
    public static void main(String[] args) {
        System.out.println("Calculation of Internsity-Duration-Frequency curves.");
		System.out.println("commit 3 testing.");
		
		// initializing double array
      double arr[] = GetObservedRainfallData();

      // let us print the values
      System.out.println("Actual values: ");
      for (double value : arr) {
         //System.out.println("Value = " + value);
      }
    }
	
	
	private static double[] GetObservedRainfallData() {
		double ret[] = new double[] { 0.7,3.1,0.4,0.2,0.2,2.5,2.1,1,0.4,1.7,2.3,0.9,1.2,1.3,0.8,1.1,1.2,0.9,1.6,1.1,1.7,1.3,0.8,1.9,1.7,0.9,0.7,0.4,1.4,0.8,1,1.2,1.2,1,1.2,0.6,0.8,1.1,1,1.3,0.6,0.3,0.1,0.2,0,0,0.3,0.3,0.1,0.4,0.1,0.3,0.7,0,0,0,0,0,0,0,0.1,0,0,0,0,0,0,0,0,0,0.1,0.1,0,0,0,0,0,0,0,0,0,0,0,0,0.2,0.2,0.1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0.1,0.9,0.7,0.2,0.5,1,1.4,0.6,2.7,3,1,0.9,0.9,0.9,2.5,1.6,0.5,0.4,0.7,0.6,0.6,0.5,0.7,1,0.2,0.5,1.2,1.1,0.5,0.5,0.4,0.1,0.1,0.7,1.3,2.8,1.6,2.5,2.5,2.7,1,1.4,0.9,0.7,3.1,0.4,0.2,0.2,2.5,2.1,1,0.4,1.7,2.3,0.9,1.2,1.3,0.8,1.1,1.2,0.9,1.6,1.1,1.7,1.3,0.8,1.9,1.7,0.9,0.7,0.4,1.4,0.8,1,1.2,1.2,1,1.2,0.6,0.8,1.1,1,1.3,0.6,0.3,0.1,0.2,0,0,0.3,0.3,0.1,0.4,0.1,0.3,0.7,0,0,0,0,0,0,0,0.1,0,0,0,0,0,0,0,0,0,0.1,0.1,0,0,0,0,0,0,0,0,0,0,0,0,0.2,0.2,0.1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0.1,0.9,0.7,0.2,0.5,1,1.4,0.6,2.7,3,1,0.9,0.9,0.9,2.5,1.6,0.5,0.4,0.7,0.6,0.6,0.5,0.7,1,0.2,0.5,1.2,1.1,0.5,0.5,0.4,0.1,0.1,0.7,1.3,2.8,1.6,2.5,2.5,2.7,1,1.4,0.9,0.7,3.1,0.4,0.2,0.2,2.5,2.1,1,0.4,1.7,2.3,0.9,1.2,1.3,0.8,1.1,1.2,0.9,1.6,1.1,1.7,1.3,0.8,1.9,1.7,0.9,0.7,0.4,1.4,0.8,1,1.2,1.2,1,1.2,0.6,0.8,1.1,1,1.3,0.6,0.3,0.1,0.2,0,0,0.3,0.3,0.1,0.4,0.1,0.3,0.7,0,0,0,0,0,0,0,0.1,0,0,0,0,0,0,0,0,0,0.1,0.1,0,0,0,0,0,0,0,0,0,0,0,0,0.2,0.2,0.1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0.1,0.9,0.7,0.2,0.5,1,1.4,0.6,2.7,3,1,0.9,0.9,0.9,2.5,1.6,0.5,0.4,0.7,0.6,0.6,0.5,0.7,1,0.2,0.5,1.2,1.1,0.5,0.5,0.4,0.1,0.1,0.7,1.3,2.8,1.6,2.5,2.5,2.7,1,1.4,0.9,0.7,3.1,0.4,0.2,0.2,2.5,2.1,1,0.4,1.7,2.3,0.9,1.2,1.3,0.8,1.1,1.2,0.9,1.6,1.1,1.7,1.3,0.8,1.9,1.7,0.9,0.7,0.4,1.4,0.8,1,1.2,1.2,1,1.2,0.6,0.8,1.1,1,1.3,0.6,0.3,0.1,0.2,0,0,0.3,0.3,0.1,0.4,0.1,0.3,0.7,0,0,0,0,0,0,0,0.1,0,0,0,0,0,0,0,0,0,0.1,0.1,0,0,0,0,0,0,0,0,0,0,0,0,0.2,0.2,0.1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0.1,0.9,0.7,0.2,0.5,1,1.4,0.6,2.7,3,1,0.9,0.9,0.9,2.5,1.6,0.5,0.4,0.7,0.6,0.6,0.5,0.7,1,0.2,0.5,1.2,1.1,0.5,0.5,0.4,0.1,0.1,0.7,1.3,2.8,1.6,2.5,2.5,2.7,1,1.4,0.9,0.7,3.1,0.4,0.2,0.2,2.5,2.1,1,0.4,1.7,2.3,0.9,1.2,1.3,0.8 };
		return ret;
	}
	
	private static ArrayList<Integer[]> generateEpisodeCurve(double[] rainfallValues) {

		ArrayList<Integer[]> episodes = new ArrayList<Integer[]>();
		Integer ep1[] = { 0, 4 };
		Integer ep2[] = { 10, 14 };
		episodes.add(ep1);
		episodes.add(ep2);
		
		// Las timeseries tienen sus datos en timeserie.values[0] y [1]
		//let rainfallValues = timeserie.values[1];
		
		int generalIndex = 0;
		while (generalIndex <= rainfallValues.length) {

			// Get First Non Zero Value From IndexStart
			//const startEpisode = rainfallValues.findIndex((v, i) => v > 0 && i >= generalIndex );
			int startEpisode = Arrays.stream(rainfallValues).filter(n -> (n == 30)).findAny().orElse(-1);
			if (startEpisode == -1) {
				break;
			}
			/*
			bool endEpisodeFound = false;
			int endEpisode = startEpisode;
			for (int i = startEpisode +1 ; !endEpisodeFound && i < rainfallValues.length; i++) {
				//Si llueve reseteo el contador a 0 sino sumo
				double sumInterval = rainfallValues.slice(i, Math.min(rainfallValues.length, i + this.stepToFinishAnEpisode )).reduce((acc, val) => acc + val, 0);
				
				if ( sumInterval <=  this.maxRainfallToCloseEpisode ) {
					//Quito todos los valores nulos o considerados como tal 
					//(Es decir los que cuentan en el rango que tiene que ser menor que maxRainfallToCloseEpisode )
					//para cerrar el episodio
					
					endEpisode = i;
					//si he llehado al número de intervalos máximos sin lluvia
					endEpisodeFound = true;
				} 
				
				if (i === rainfallValues.length-1)
				{
					endEpisode = rainfallValues.length -1;
					endEpisodeFound = true;
				}				
			}
			
			//compruebo si la lluvia del intervalo generado supera el limite
			double sumEpisode = rainfallValues.slice(startEpisode, endEpisode).reduce((acc, val) => acc + val, 0);
			if (sumEpisode >= MINIMUM_RAIN_IN_MM) {
				Integer ep[] = {startEpisode,endEpisode};
				episodes.add(ep);
			}
			generalIndex = endEpisode + 1;
			*/
		}
		
		return episodes;
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