import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BruteGenerator implements TextGenerator {

	private TrainingText trnText;
	private Random randomNo;

	public BruteGenerator() {
		randomNo = new Random();
	}

	public BruteGenerator(int inputRandomSeed) {
		randomNo = new Random(inputRandomSeed);
	}

	@Override
	public int train(Scanner source, String delimiter, int k) {
		// create a TrainingText
		trnText = new TrainingText(source, delimiter, k);

		// return total number of states (TrainingTexts.size())
		return trnText.size();
	}

	@Override
	public String generateText(int length) {
		// length is number of words/letter we want to generate
		// determine a start state (seed)
		// generate a random number
		// keep track of possible indices
		int startInt = randomNo.nextInt(trnText.size());
		NGram seed = trnText.get(startInt);
		StringBuilder strBuilder = new StringBuilder();

		ArrayList<NGram> arList = new ArrayList<NGram>();

		for (int i = 0; i < length; i++) {
			arList.clear();

			int indiceCheck = 0;
			int randomPick = 0;
			// find all the instances of seed in the text
			// text.indexOf(NGram seed, int startPos)
			indiceCheck = trnText.indexOf(seed, indiceCheck);

			while (indiceCheck + 1 < trnText.size()) {
				// find next occurrence of seed

				// compile a list of every following state
				arList.add(trnText.get(indiceCheck + 1));

				// update indiceCheck
				indiceCheck++;

				indiceCheck = trnText.indexOf(seed, indiceCheck);
				if (indiceCheck == -1) {
					break;
				}
			}

			// randomly pick next state from arList
			// set new seed to the seed randomly picked
			randomPick = randomNo.nextInt(arList.size());
			seed = arList.get(randomPick);

			// add this to strBuilder
			strBuilder.append(seed.toString());

		}

		return strBuilder.toString();
	}

}
