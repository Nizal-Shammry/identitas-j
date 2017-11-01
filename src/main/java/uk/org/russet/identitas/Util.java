package uk.org.russet.identitas;

import java.io.IOException;
import java.io.StringReader;
import java.util.Random;
import uk.org.russet.proquint.Proquint;
import uk.org.russet.identitas.Damm;
/**
 * The implementation of the Identitas based on the details on <a href="https://arxiv.org/abs/1709.09021">https://arxiv.org/abs/1709.09021</a>
 * @author Nizal Alshammry and Phillip Lord
 * @version 1.0
 */

public class Util {
	
	public static int INTEGER_MIN = Integer.MIN_VALUE;  //-2147483648
	public static int INTEGER_MAX = Integer.MAX_VALUE; //  2147483647
	
	
	
	/**
	 * Returns the division of integer-max by random number, both integer.
	 */
	private static int max_val_by_10 (){
		
		int a = INTEGER_MAX / 10 ;
		return a;
	}
	
	/**
	 * Returns a random int_proquint after validate it with Damm checksum.
	 * @return int_proquint - the String representation of the unsigned integer value.
	 */
	public static String randomDammProint() throws IOException {
		
		int checked_num;
		Random rand = new Random();
		int  number = rand.nextInt(max_val_by_10());

		int sign = rand.nextInt(2);
		if (sign == 1)
			number = number * -1;	
		
		try {
			
		   checked_num = Damm.generateCheckSum(number);
		} catch (IndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException("IndexOutOfBoundsException: " + e.getMessage());
		}

		 return intToProint(checked_num);
		
	}
	
	
     /**
      * Validate a given int_proquint with Damm checksum
      * @param int_proquint the String representation of the unsigned integer value.
      * @return True if valid; otherwise false
      * @throws Exception If the input does not contain a set of strings of alternating consonants and vowels, such as "babab-babab".
      */
	public static boolean prointDammValid(String int_proquint) throws Exception {
		
		boolean result = false;
		String temp = int_proquint.toLowerCase();
		if(temp.length() == 11 && temp.matches("[A-Za-z -]+")){
		StringReader proint_val = new StringReader(temp);
		long return_proquint = Proquint.quint2uint(proint_val);		
		
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
	
	
	  /************************************************
	   *********** Conversion numbers  ****************
	   ************************************************/
	
	/**
	 * Returns int_proquint (String) for a given integer.
	 * @param number  the integer to be converted to int_proquint(String).
	 * @return int_proquint - the String representation of the unsigned integer value.
	 * @throws IOException If the integer number is out of the range.
	 */
	public static String intToProint(int number) throws IOException{

		final int proint_length = 11;
		StringBuffer buffer = new StringBuffer(proint_length);
		
		if (number <= Integer.MAX_VALUE || number < Integer.MIN_VALUE ) {			
		
			 Proquint.uint2quint(buffer, number, '-');	
		} 
		else { 
			throw new IOException("Number out of range: " + number);
		}
		
		return buffer.toString();
		
	}

	/**
	 * Returns short_proquint for a given Short number. The passed argument to short_to_proshort should be casted to short type to get a short_proquint,
	 * for example shortToProshort((short) 1)
	 * @param number the Short integer to be converted to a short_proquint(String).
	 * @return short_proquint - the String representation of the Short value.
	 * @throws IOException If the input is out of the range for Short type. 
	 */
	public static String shortToProshort (short number) throws IOException{
		
		final short proshort_length = 5;
		StringBuffer buffer = new StringBuffer(proshort_length);
		 Proquint.uint2quint(buffer, number, '-'); 
		return buffer.subSequence(6, 11).toString();

	}
	
	
	/**
	 * Returns a long_proquint for a given long value.
	 * @param number the  long to be converted to a long_proquint(String).
	 * @return long_proquint - the String representation of the long value.
	 * @throws IOException If the input is out of the range for long type.
	 */
	public static String longToProlong (long number) throws IOException {
		
		long firstpart = (number >> 32) ;
		int secpart = (int) number;
		
		final int prolong_length = 11 ;
		StringBuffer longnumb = new StringBuffer(prolong_length+1);
		StringBuffer longnumb1 = new StringBuffer(prolong_length);
		
		Proquint.uint2quint(longnumb, (int) firstpart, '-');

		Proquint.uint2quint(longnumb1, secpart, '-');
		return longnumb.toString() + "-"  + longnumb1.toString();
		
	}
	
	
			/************************************************
			 *********  Conversion Proquint (String) ********
			 ************************************************/
	
	/**
	 * Returns integer for a given int_proquint.
	 * @param int_proquint the String representation of the unsigned integer value.
	 * @return number - the integer representation of the int_proquint(String).
	 * @throws IOException If the input does not contain a set of strings of alternating consonants and vowels, such as "babab-babab".
	 */
	public static int prointToInt(String int_proquint) throws IOException{
		
		int return_proquint;
		String temp = int_proquint.toLowerCase();
		
		if(temp.length() == 11 && temp.matches("[A-Za-z -]+")){
			
			StringReader proint_val = new StringReader(temp);
			return_proquint = (int) Proquint.quint2uint(proint_val);

		}
		else { 
			throw new IOException("Not valid entry; input must be alphabets and 11 length, plus '-' [A-Za-z -]" + temp.toString());
		}
		return return_proquint;
	}
	
	/**
	 * Returns Short for a given short_proquint.
	 * @param short_proquint the String representation of the Short value.
	 * @return number - the Short representation of the short_proquint(String).
	 * @throws IOException If the input does not contain a set of strings of alternating consonants and vowels, such as "babab".
	 */
	public static int proshortToShort(String short_proquint) throws IOException{
	
		short return_proquint;
		String temp = short_proquint.toLowerCase();
		if(temp.length() == 5 && temp.matches("[A-Za-z]+")){
		StringReader proint_val = new StringReader(temp);
		return_proquint = (short) Proquint.quint2uint(proint_val);
		}
		else { 
			throw new IOException("Not a valid entry; input must be alphabets and 5 length" + temp.toString());
		}
		return return_proquint;
		
	}
	
	/**
	 * Returns long for a given long_proquint.
	 * @param long_proquint the String representation of the long value.
	 * @return number - the long representation of the long_proquint(String).
	 * @throws IOException If the input does not contain a set of strings of alternating consonants and vowels, such as "babab-babab-babab-babab".
	 */
	public static long prolongToLong(String long_proquint) throws IOException{
		
		long result ;
		
		int uint = Integer.parseUnsignedInt("4294967295");
		long bitmask = Integer.toUnsignedLong(uint);
		
		String temp = long_proquint.toLowerCase();
		if(temp.length() == 23 && temp.matches("[A-Za-z -]+")){
			
		StringReader proint_val = new StringReader(temp.substring(0, 11));
		StringReader proint_val2 = new StringReader(temp.substring(12, 23));
		
		long return_proquint = Proquint.quint2uint(proint_val);
		long return_proquint2 = Proquint.quint2uint(proint_val2);

		long left_shift =  (((long)return_proquint) << 32);
		long bit_and  =    (return_proquint2 & bitmask) ;
		
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
	 * Returns a random short_proquint.
	 * @return short_proquint - the String representation of the Short value.
	 */
	public static String randomProshort() throws IOException {
		
		short min = Short.MIN_VALUE;
		short max = Short.MAX_VALUE;
		
		Random rand = new Random();
		short  number = (short) (rand.nextInt(max-min) + min) ;
		
		return shortToProshort(number);
		
	}
	
	/**
	 * Returns random int_proquint
	 * @return int_proquint - the String representation of the unsigned integer value.
	 */
	public static String randomProint() throws IOException {
		
		Random rand = new Random();
		int  number = rand.nextInt(Integer.MAX_VALUE);
		
		int sign = rand.nextInt(2);
		if (sign == 1)
			number = number * -1;
		
		return intToProint(number);
	}
	
	/**
	 * Returns a random long_proquint.
	 * @return long_proquint - the String representation of the long value.
	 */
	public static String randomProlong() throws IOException {
			
		Random rand = new Random();
		long n = (rand.nextLong());	
		return longToProlong(n);		
	}
	
}
