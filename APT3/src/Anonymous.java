import java.util.HashMap;

public class Anonymous {
	
	public HashMap<Character,Integer> initHL(String[] headlines) {
		HashMap<Character, Integer> hLines = new HashMap<Character, Integer>();

		for (int i = 0 ; i < headlines.length ; i++) {

			String strTemp = (headlines[i].toLowerCase()).replaceAll(" ","");

			for (int ii = 0 ; ii < strTemp.length() ; ii++) {	
				Character a = strTemp.charAt(ii);
				
				if (hLines.containsKey(a)) {
					int x = hLines.get(a);
					hLines.put(a, x + 1);
					
				} else {
					hLines.put(a, 1);
					
				}
			}
		}
		return hLines;
	}

	public HashMap<Character,Integer> initMES(String message) {
		HashMap<Character, Integer> mesMap = new HashMap<Character, Integer>();

		message = (message.toLowerCase()).replaceAll(" ", "");
		
		for (int i = 0 ; i < message.length() ; i++) {
			Character b = message.charAt(i); 
			if (mesMap.containsKey(b)) {
				int x = mesMap.get(b);
				mesMap.put(b, x + 1);
			} else {
				mesMap.put(b, 1);
			}
		}
		return mesMap;
	}

	public int howMany(String[] headlines, String[] messages) {
		int count = 0;
		int flag = 0;
		
		HashMap<Character,Integer> headlinesMap = initHL(headlines);
		for (int i = 0 ; i < messages.length ; i++) {
			HashMap<Character, Integer> message = initMES(messages[i]);
			for (Character a : message.keySet()) {
				if (!(headlinesMap.containsKey(a))) {
					flag = 1;
					break;
				}
				if (message.get(a) > headlinesMap.get(a)) {
					flag = 1;
					break;
				}
			}
			if (flag == 1) {
				flag = 0;
			} else {
				count++;
			}
		}
		return count;
	}
}
	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Anonymous test = new Anonymous();
		String[] headlines1 = {"aabc"};
		String[] messages1 = {"AaBbCc ", " aabbbcc ", " ", " FADE "};
		System.out.println(test.howMany(headlines1,messages1));
		
		/*
		String[] headlines2 = {"Programming is fun"};
		String[] messages2 = {"program","programmer","gaming","sing","NO FUN"};
		System.out.println(test.howMany(headlines2,messages2));
		 */ 

	
	/*
	public int howMany(String[] headlines, String[] messages) {
		String [] alpha = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		int [] avaiBank = new int[26];
		int [][] needed = new int[messages.length][26];
		String strTemp;
		
		
		for (int i =0 ; i < headlines.length ; i++) {
			headlines[i] = headlines[i].toLowerCase();
			for (int j = 0 ; j < 26 ; j++) {
				strTemp = headlines[i].replaceAll(alpha[j],"");	
				avaiBank[j] += headlines[i].length() - strTemp.length();
			}
		}
		for (int i = 0 ; i < messages.length ; i++) {
			messages[i] = messages[i].toLowerCase();
			for (int j = 0 ; j < 26 ; j++) {
				strTemp = messages[i].replaceAll(alpha[j],"");	
				needed[i][j] = messages[i].length() - strTemp.length();
			}
		}
		
		int count = 0;
		
		for (int i = 0 ; i < messages.length ; i++) {
			for (int j = 0 ; j < 26 ; j++) {
				if (!(avaiBank[j] >= needed[i][j])) {
					break;
				} else if(j ==25) {
					count += 1;
				}
			}
		}
		return count;
	}
	*/
