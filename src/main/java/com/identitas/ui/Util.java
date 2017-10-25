package com.identitas.ui;

import java.io.IOException;
import java.io.StringReader;
import java.util.Random;

import com.identitas.proquint.Proquint;

/**
 * The implementation of the Identitas based on the details on https://arxiv.org/abs/1709.09021
 * @author Nizal Alshammry and Phillip Lord
 * @version 1.0
 */

public class Util {
	
	Proquint proquint;
	Damm damm;
	public final int Integer_min = Integer.MIN_VALUE;  //-2147483648
	public final int Integer_max = Integer.MAX_VALUE; //  2147483647
	
	public Util( ) {
		proquint = new Proquint();
		damm = new Damm();
	}
	
    
	/**
	 * Returns the division of integer-max by random number, both integer
	 */
	private int max_val_by_10 (){
		
		int a = Integer_max / 10 ;
		return a;
	}
	

	/**
	 * Returns a random int_proquint after validate it with Damm checksum.
	 * @return int_proquint - the string representation of the unsigned integer value
	 */
	public String random_damm_proint() throws IOException {

		Random rand = new Random();
		int  number = rand.nextInt(max_val_by_10());

		int sign = rand.nextInt(2);
		if (sign == 1)
			number = number * -1;	
		
		try {
			
			damm.generateCheckSum(number);
		} catch (IndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException("IndexOutOfBoundsException: " + e.getMessage());
		}

		 return int_to_proint(number);
		
	}
	
	
     /**
      * Validate a given int_proquint with Damm checksum
      * @param int_proquint - the string representation of the unsigned integer value
      * @return True if valid; otherwise false
      * @throws Exception If the input does not contain a string such as "babab-babab"
      */
	public boolean proint_damm_valid(String int_proquint) throws Exception {
		
		boolean result = false;
		String temp = int_proquint.toLowerCase();
		if(temp.length() == 11 && temp.matches("[A-Za-z -]+")){
		StringReader proint_val = new StringReader(temp);
		int return_proquint = proquint.quint2uint(proint_val);		
		
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
	 * Returns int_proquint (String) for a given int
	 * @param number - an integer to be converted to a int_proquint(string).
	 * @return int_proquint - the string representation of the unsigned integer value
	 * @throws IOException if the integer number is out of the range.
	 */
	public String int_to_proint(int number) throws IOException{

		final int proint_length = 11;
		StringBuffer buffer = new StringBuffer(proint_length);
		
		if (number <= Integer.MAX_VALUE || number < Integer.MIN_VALUE ) {			
		
			buffer = proquint.uint2quint(number, '-');	
		} 
		else { 
			throw new IOException("Number out of range: " + number);
		}
		
		return buffer.toString();
		
	}

	/**
	 * Returns short_proquint for a given Short number, the passed argument to short_to_proshort must be casted to short type. 
	 * @param number - a Short integer to be converted to a short_proquint(string).
	 * @return short_proquint - the string representation of the Short value
	 * @throws IOException if the input is out of the range for Short type. 
	 */
	public String short_to_proshort (short number) throws IOException{
		
		final short proshort_length = 5;
		StringBuffer buffer = new StringBuffer(proshort_length);
		buffer = proquint.uint2quint(number, '-'); 
		return buffer.subSequence(6, 11).toString();

	}
	
	
	/**
	 * Returns a long_proquint for a given Long
	 * @param number - a long to be converted to a long_proquint(string).
	 * @return long_proquint - the string representation of the Long value
	 * @throws IOException if the input is out of the range for Long type.
	 */
	public String long_to_prolong (long number) throws IOException {
		
		long firstpart = (number >> 32) ;
		int secpart = (int) number;
		
		final int prolong_length = 11 ;
		StringBuffer longnumb = new StringBuffer(prolong_length+1);
		StringBuffer longnumb1 = new StringBuffer(prolong_length);
		
		longnumb = proquint.uint2quint((int) firstpart, '-');

		longnumb1 = proquint.uint2quint(secpart, '-');
		return longnumb.toString() + "-"  + longnumb1.toString();
		
	}
	
	
			/************************************************
			 *********  Conversion Proquint (String) ********
			 ************************************************/
	
	/**
	 * Returns int for a given int_proquint 
	 * @param int_proquint - the string representation of the unsigned integer value
	 * @return number - the integer representation of the int_proquint (string)
	 * @throws IOException If the input does not contain a string such as "babab-babab"
	 */
	public int proint_to_Int(String int_proquint) throws IOException{
		
		int return_proquint;
		String temp = int_proquint.toLowerCase();
		
		if(temp.length() == 11 && temp.matches("[A-Za-z -]+")){
			
			StringReader proint_val = new StringReader(temp);
			return_proquint = proquint.quint2uint(proint_val);

		}
		else { 
			throw new IOException("Not valid entry; input must be alphabets and 11 length, plus '-' [A-Za-z -]" + temp.toString());
		}
		return return_proquint;
	}
	
	/**
	 * Returns Short for a given short_proquint
	 * @param short_proquint - the string representation of the Short value
	 * @return number - the Short representation of the short_proquint (string)
	 * @throws IOException If the input does not contain a string such as "babab"
	 */
	public int proshort_to_short(String short_proquint) throws IOException{
	
		short return_proquint;
		String temp = short_proquint.toLowerCase();
		if(temp.length() == 5 && temp.matches("[A-Za-z]+")){
		StringReader proint_val = new StringReader(temp);
		return_proquint = (short) proquint.quint2uint(proint_val);
		}
		else { 
			throw new IOException("Not a valid entry; input must be alphabets and 5 length" + temp.toString());
		}
		return return_proquint;
		
	}
	
	/**
	 * Returns Long for a given long_proquint
	 * @param long_proquint - the string representation of the Long value
	 * @return number - the Long representation of the long_proquint (string)
	 * @throws IOException If the input does not contain a string such as "babab-babab-babab-babab"
	 */
	public long prolong_to_long(String long_proquint) throws IOException{
		
		long result ;
		
		int uint = Integer.parseUnsignedInt("4294967295");
		long bitmask = Integer.toUnsignedLong(uint);
		
		String temp = long_proquint.toLowerCase();
		if(temp.length() == 23 && temp.matches("[A-Za-z -]+")){
			
		StringReader proint_val = new StringReader(temp.substring(0, 11));
		StringReader proint_val2 = new StringReader(temp.substring(12, 23));
		
		int return_proquint = proquint.quint2uint(proint_val);
		int return_proquint2 = proquint.quint2uint(proint_val2);

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
	 * Returns a random short_proquint
	 * @return short_proquint - the string representation of the Short value
	 */
	public String random_proshort() throws IOException {
		
		short min = Short.MIN_VALUE;
		short max = Short.MAX_VALUE;
		
		Random rand = new Random();
		short  number = (short) (rand.nextInt(max-min) + min) ;
		
		return short_to_proshort(number);
		
	}
	
	/**
	 * Returns random int_proquint
	 * @return int_proquint - the string representation of the unsigned integer value
	 */
	public String random_proint() throws IOException {
		
		Random rand = new Random();
		int  number = rand.nextInt(Integer.MAX_VALUE);
		
		int sign = rand.nextInt(2);
		if (sign == 1)
			number = number * -1;
		
		return int_to_proint(number);
	}
	
	/**
	 * Returns a random long_proquint
	 * @return long_proquint - the string representation of the Long value
	 */
	public String random_prolong() throws IOException {
			
		Random rand = new Random();
		long n = (rand.nextLong());	
		return long_to_prolong(n);		
	}
	
}
