
public class Freefall {
	public double fallingDistance(double time, double velo){
		return (.5 * 9.8 * time * time + velo * time);
	}
}
