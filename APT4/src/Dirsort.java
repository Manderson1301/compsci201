import java.util.Arrays;
import java.util.Comparator;

public class Dirsort {
	public class myDirsort implements Comparator<String>{

		@Override
		public int compare(String arg0, String arg1) {
				int dashCount0 = countDashes(arg0);
				int dashCount1 = countDashes(arg1);
				
				if ((dashCount0 - dashCount1)!=0) {
					return dashCount0 - dashCount1;
				} else {
					String[] argsplit0 = arg0.split("/");
					String[] argsplit1 = arg1.split("/");
					
					for (int i = 1 ; i < dashCount0 ; i++) {
						if ( argsplit0[i].equals(argsplit1[i]) ) {
							if (dashCount0 == i + 1) {
								return 0;
							} else {
								continue;
							}
						/*
						} else if ( ((argsplit0[i]).length() - (argsplit1[i]).length()) !=0) {
							return ((argsplit0[i]).length() - (argsplit1[i]).length());
							*/
						} else {
							return argsplit0[i].compareTo(argsplit1[i]);
						}
					}
						
				}
				
			return 0;
		}

		private int countDashes(String dir) {
	        return dir.length() - (dir.replaceAll("/","")).length();
		}
		
	}
	
	
	public String[] sort(String[] dirs) {
        Arrays.sort(dirs, new myDirsort());
        return dirs;
   }
}
