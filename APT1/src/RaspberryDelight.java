
public class RaspberryDelight {
	public int toasts(int upper_limit, int layer_count){
        if (layer_count <= upper_limit){
        	return 1;
        } else if (layer_count % upper_limit == 0) {
        	return layer_count / upper_limit;
        } else {
        	return 1 + layer_count / upper_limit;
        }
     }
	/*
	public static void main (String[] args) {
		// Remember to change method to static
		System.out.println("8,5    " + toasts(8,2));
	}
	*/
}
