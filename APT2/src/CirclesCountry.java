import java.lang.Math;

public class CirclesCountry {
	public int leastBorders(int[] x, int[] y, int[] r, int x1, int y1, int x2, int y2) {
		// see how many circles surround the begin and end point
		int answer = 0;
		for (int i = 0; i < x.length ; i++){
			
			boolean a = inCircle(x1,x[i],y1,y[i],r[i]);
			boolean b = inCircle(x2,x[i],y2,y[i],r[i]);
			
			if ((a || b) && !(a && b)) {
				answer += 1;
			}
		}
		return answer;
	}
	
	private boolean inCircle(int x1, int x2, int y1, int y2, int r){
		return ((Math.pow((x2-x1),2) + Math.pow((y2-y1),2)) < Math.pow(r,2));
	}
	
	//public static void main (String[] args) {
		
	//}
}
