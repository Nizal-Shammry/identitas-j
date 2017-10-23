package identitas;

import java.io.*;
import java.util.*;
import identitas.Damm;
import proquint.Proquint;

/**
 * The implementation of the Identitas based on the details on https://arxiv.org/abs/1709.09021
 * @author Nizal Alshammry and Phillip Lord
 * @version 1.0
 */

public class Util {
	
	public static int min = Integer.MIN_VALUE;  //-2147483648
	public static int max = Integer.MAX_VALUE; //  2147483647
	
	/**
	 * Returns the division of integer-max by random number, both integer
	 * @throws Exception 
	 */
	private static int max_val_by_10 (){
		
		int a = max / 10 ;
		return a;
	}
	
	/**
	 * Returns a random int_proquint after validate it with Damm checksum.
	 * @return int_proquint String
	 * @throws IOException If the generated random number invalid
	 */
	public static String random_damm_proint() throws IOException {
		
		Random rand = new Random();
		int  number = rand.nextInt(max_val_by_10());
		
		int sign = rand.nextInt(2);
		if (sign == 1)
			number = number * -1;	
		try {
			
			Damm.generateCheckSum(number);
		} catch (IndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException("IndexOutOfBoundsException: " + e.getMessage());
		}
		
		 return int_to_proint(number);
		
	}
	
	
     /**
      * Validate a given int_proquint with Damm checksum
      * @param int_proquint String
      * @return True if valid; otherwise false
      * @throws Exception If Not valid entry
      */
	public static boolean proint_damm_valid(String int_proquint) throws Exception {
		
		boolean result = false;
		String temp = int_proquint.toLowerCase();
		if(temp.length() == 11 && temp.matches("[A-Za-z -]+")){
		StringReader proint_val = new StringReader(temp);
		int return_proquint = Proquint.proint_to_int(proint_val);		
		try {
			
		 result = Damm.validate(return_proquint);
		} catch (IndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException("IndexOutOfBoundsException: " + e.getMessage());
		}	
		}
		else {
			
			throw new IOException("Not valid entry: input must be alphabets and 11 length, plus '-' [A-Za-z -]" + temp.toString());
		}
		
		return result;
		
	}
	
	
	       /*********************************************
			********** Conversion numbers ***************
			*********************************************/
	
	/**
	 * Returns int_proquint (String) for a given int
	 * @param number (int)
	 * @return int_proquint (String)
	 * @throws IOException if input out of the range
	 */
	public static String int_to_proint(int number) throws IOException{

		final int proint_length = 11;
		StringBuffer buffer = new StringBuffer(proint_length);
		
		if (number <= Integer.MAX_VALUE || number < Integer.MIN_VALUE ) {			
		
			Proquint.int_to_proint(buffer, number, '-');
			
		} 
		else { 
			throw new IOException("Number out of range: " + number);
		}
		
		return buffer.toString();
		
	}

	/**
	 * Returns short_proquint for a given short
	 * @param number (short)
	 * @return short_proquint (String)
	 * @throws IOException if input out of the range 
	 */
	public static String short_to_proshort (short number) throws IOException{
		
		final short proshort_length = 5;
		StringBuffer buffer = new StringBuffer(proshort_length);
		Proquint.int_to_proint(buffer, number, '-'); 
		return buffer.subSequence(6, 11).toString();

	}
	
	
	/**
	 * Returns a long_proquint for a given long
	 * @param number (long)
	 * @return long_proquint (String)
	 * @throws IOException if input out of the range
	 */
	public static String long_to_prolong (long number) throws IOException {
		
		long firstpart = (number >> 32) ;
		int secpart = (int) number;
		
		final int prolong_length = 11 ;
		StringBuffer longnumb = new StringBuffer(prolong_length+1);
		StringBuffer longnumb1 = new StringBuffer(prolong_length);
		
		Proquint.int_to_proint(longnumb, (int) firstpart, '-');

		Proquint.int_to_proint(longnumb1, secpart, '-');
		return longnumb.toString() + "-"  + longnumb1.toString();
		
	}
	
	
			/************************************************
			 *********  Conversion Proquint (String) ********
			 ************************************************/
	
	/**
	 * Returns int for a given int_proquint 
	 * @param int_proquint String
	 * @return number (int)
	 * @throws IOException if the input is not valid 
	 */
	public static int proint_to_Int(String int_proquint) throws IOException{
	
		int return_proquint;
		String temp = int_proquint.toLowerCase();
		if(temp.length() == 11 && temp.matches("[A-Za-z -]+")){
		StringReader proint_val = new StringReader(temp);
		return_proquint = Proquint.proint_to_int(proint_val);

		}
		else { 
			throw new IOException("Not valid entry; input must be alphabets and 11 length, plus '-' [A-Za-z -]" + temp.toString());
		}
		return return_proquint;
	}
	
	/**
	 * Returns short for a given short_proquint
	 * @param short_proquint String
	 * @return number (short)
	 * @throws IOException if the input is not valid 
	 */
	public static int proshort_to_short(String short_proquint) throws IOException{
	
		short return_proquint;
		String temp = short_proquint.toLowerCase();
		if(temp.length() == 5 && temp.matches("[A-Za-z]+")){
		StringReader proint_val = new StringReader(temp);
		return_proquint = (short) Proquint.proint_to_int(proint_val);
		}
		else { 
			throw new IOException("Not a valid entry; input must be alphabets and 5 length" + temp.toString());
		}
		return return_proquint;
		
	}
	
	/**
	 * Returns long for a given long_proquint
	 * @param long_proquint String
	 * @return number (long)
	 * @throws IOException if the input is not valid 
	 */
	public static long prolong_to_log(String long_proquint) throws IOException{
		
		long result ;
		String temp = long_proquint.toLowerCase();
		
		if(temp.length() == 23 && temp.matches("[A-Za-z -]+")){
			
		StringReader proint_val = new StringReader(temp.substring(0, 11));
		StringReader proint_val2 = new StringReader(temp.substring(12, 23));
		
		int return_proquint = Proquint.proint_to_int(proint_val);
		int return_proquint2 = Proquint.proint_to_int(proint_val2);
		
		long left_shift =  (((long)return_proquint) << 32);
		long bit_and  =   (return_proquint2 & 0xFFFFFFFF) ;
		result = left_shift | bit_and ;	
		}
		else { 
			
			throw new IOException("Not a valid entry: input must be alphabets and 23 length " + temp.toString());
		}
		return result;
	}
	
	
	
			/**********************************************
			 *********   Random Identifiers  **************
			 **********************************************/
	
	/**
	 * Returns a random short_proquint
	 * @return short_proquint String
	 * @throws IOException If the generated random number is invalid
	 */
	public static String random_proshort() throws IOException {
		
		short min = Short.MIN_VALUE;
		short max = Short.MAX_VALUE;
		
		Random rand = new Random();
		short  number = (short) (rand.nextInt(max-min) + min) ;
		
		return short_to_proshort(number);
		
	}
	
	/**
	 * Returns random int_proquint
	 * @return int_proquint String
	 * @throws IOException If the generated random number is invalid
	 */
	public static String random_proint() throws IOException {
		
		Random rand = new Random();
		int  number = rand.nextInt(Integer.MAX_VALUE);
		
		int sign = rand.nextInt(2);
		if (sign == 1)
			number = number * -1;
		
		return int_to_proint(number);
	}
	
	/**
	 * Returns a random long_proquint
	 * @return long_proquint String
	 * @throws IOException If the generated random number is invalid
	 */
	public static String random_prolong() throws IOException {
			
		Random rand = new Random();
		long n = (rand.nextLong());	
		return long_to_prolong(n);		
	}
	
}
