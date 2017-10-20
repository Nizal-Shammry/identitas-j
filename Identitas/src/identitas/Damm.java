package identitas;

public class Damm {

	
	
	//https://en.wikibooks.org/wiki/Algorithm_Implementation/Checksums/Damm_Algorithm	
	//https://creativecommons.org/licenses/by-sa/3.0/
	private static int[][] matrix = new int[][] {
        { 0, 3, 1, 7, 5, 9, 8, 6, 4, 2 }, 
        { 7, 0, 9, 2, 1, 5, 4, 8, 6, 3 }, 
        { 4, 2, 0, 6, 8, 7, 1, 3, 5, 9 },
        { 1, 7, 5, 0, 9, 8, 3, 4, 2, 6 }, 
        { 6, 1, 2, 3, 0, 4, 5, 9, 7, 8 }, 
        { 3, 6, 7, 4, 2, 0, 9, 5, 8, 1 }, 
        { 5, 8, 6, 9, 7, 2, 0, 1, 3, 4 },
        { 8, 9, 4, 5, 3, 6, 2, 0, 1, 7 }, 
        { 9, 4, 3, 8, 6, 1, 7, 2, 0, 5 }, 
        { 2, 5, 8, 1, 4, 3, 6, 7, 9, 0 } };
	
	
	 public static int calculateCheckSumDigit(String number) {

	        int interim = 0;
	        for (int index = 0; index < number.length(); index++) {
	            char currCh = number.charAt(index);
//	            if ( !Character.isDigit(currCh)) {
//	                throw new RuntimeException(number + " is not a valid number");
//	            }
	            
	            int currentIndex = currCh -48;
	            
	            interim = matrix[interim][currentIndex];
	        }
	        return interim;
	    }
	 
	  public static int calculateCheckSumDigit(int number) {
	        return calculateCheckSumDigit(String.valueOf(number));
	    }

	  
	  public static int calculateCheckSumDigit(long number) {
	      return calculateCheckSumDigit(String.valueOf(number));
		
	    }
  
	  public static String generateCheckSum(String number) {
	        int checkSumDigit = calculateCheckSumDigit(number);
	        
	        return number + String.valueOf(checkSumDigit);
	    }
	  
	  
	  public static int addcheck(int number) {
	        int checkSumDigit = calculateCheckSumDigit(number);
	        return (number * 10) + checkSumDigit;
	    }

	    
	    public static long generateCheckSum(long number) {
	        int checkSumNumber = calculateCheckSumDigit(number);
	        return (number * 10) + checkSumNumber;
	    }
	    
	    public static boolean validate(String number) {
	        return calculateCheckSumDigit(number) == 0;
	    }
	    
	    public static boolean validate(int number) {
	        return calculateCheckSumDigit(number) == 0;
	    }
	    
	    public static boolean validate(long number) {

	    	return calculateCheckSumDigit(number) == 0;
	    
	    }


}
