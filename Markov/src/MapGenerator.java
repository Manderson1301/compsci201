import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

public class MapGenerator implements TextGenerator {

	private HashMap<NGram, ArrayList<NGram>> myMap;
	private Random randomNo;
	private TrainingText trnText;

	public MapGenerator() {
		myMap = new HashMap<NGram, ArrayList<NGram>>();
		randomNo = new Random();
	}

	public MapGenerator(int inputRandomSeed) {
		myMap = new HashMap<NGram, ArrayList<NGram>>();
		randomNo = new Random(inputRandomSeed);
	}

	@Override
	public int train(Scanner source, String delimiter, int k) {
		// create a TrainingText
		trnText = new TrainingText(source, delimiter, k);
		int trTeSize = trnText.size();
		myMap.clear();

		// loop over states
		for (int i = 0; i < trTeSize - 1; i++) {

			NGram seed = trnText.get(i);
			NGram seedAfter = trnText.get(i + 1);

			ArrayList<NGram> arListTemp = new ArrayList<NGram>();
			if (myMap.containsKey(seed)) {
				arListTemp = myMap.get(seed);
			}
			arListTemp.add(seedAfter);
			myMap.put(seed, arListTemp);
		}

		// return total number of UNIQUE states
		return myMap.keySet().size();
	}

	@Override
	public String generateText(int length) {
		// randomly pick a start state

		int start = randomNo.nextInt(trnText.size());
		NGram seed = trnText.get(start);
		StringBuilder strBuilder = new StringBuilder();

		int lenArListinMap = 0;
		int randomPick = 0;

		for (int i = 0; i < length; i++) {
			// get size of array list for that seed
			lenArListinMap = myMap.get(seed).size();

			// randomly pick next state from myMap.get()
			// set new seed to the seed randomly picked
			randomPick = randomNo.nextInt(lenArListinMap);
			seed = myMap.get(seed).get(randomPick);

			// append seed.toString

			strBuilder.append(seed.toString());
		}
		// System.out.println(strBuilder.toString());
		return strBuilder.toString();
	}

}
