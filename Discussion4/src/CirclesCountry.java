public class CirclesCountry {
	public boolean inCircle(int x1, int y1, int x2, int y2, int r){
		int dist1 = x1*x1 - 2*x1*x2 + x2*x2 + y1*y1 - 2*y1*y2 + y2*y2;
		int sqr = r*r;
		return (dist1 < sqr);
	}

    public int leastBorders(int[] x, int[] y, int[] r, 
                            int x1, int y1, int x2, int y2) {
		int count = 0;
        for (int i = 0; i < x.length; i++){
			if (!(inCircle(x1, y1, x[i], y[i], r[i]) && inCircle(x2, y2, x[i], y[i], r[i])) && (inCircle(x1, y1, x[i], y[i], r[i]) || inCircle(x2, y2, x[i], y[i], r[i]))){
				count++;
			}
		}
		return count;
    }
	
	public static void main(String[] args){
		CirclesCountry tester = new CirclesCountry();
		int[] x = {-2, 0, 10, 2};
		int[] y = {0, 0, 0, 0};
		int[] r = {1, 5, 1, 1};
		int x1 = -2;
		int y1 = 0;
		int x2 = 2;
		int y2 = 0;
		System.out.println(tester.leastBorders(x, y, r, x1, y1, x2, y2)); //should return 2
	}
 }