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
	 */
	private static int max_val_by_10 (){
		
		int a = max / 10 ;
		return a;
	}
	
	/**
	 * Returns a random int_proquint after validate it with Damm checksum.
	 */
	public static void random_damm_proint() throws IOException {
		
		Random rand = new Random();
		int  number = rand.nextInt(max_val_by_10());
		
		int sign = rand.nextInt(2);
		if (sign == 1)
			number = number * -1;
		System.out.println("Random number created:  "+number);
		
		try {
			
			Damm.generateCheckSum(number);
		} catch (IndexOutOfBoundsException e) {
		    System.err.println("IndexOutOfBoundsException: " + e.getMessage());
		}
		
		int_to_proint(number);
	}
	
	
     /**
      * Validate a given int_proquint with Damm checksum
      * @return True if valid; otherwise false
      * @throws Exception if Not valid entry
      */
	public static boolean proint_damm_valid() throws Exception {
		
		boolean result = false;
		Scanner sc = new Scanner (System.in);
		System.out.print("Enter your proint to validate:");
		String tem = sc.nextLine();
		String temp = tem.toLowerCase();
		if(temp.length() == 11 && temp.matches("[A-Za-z -]+")){
		StringReader proint_val = new StringReader(temp);
		int return_proquint = Proquint.proint_to_int(proint_val);
		System.out.println("Entered proint converted to:" + return_proquint);
		try {
		 result = Damm.validate(return_proquint);
		
		} catch (IndexOutOfBoundsException e) {
		    System.err.println("IndexOutOfBoundsException: " + e.getMessage());
		}
		if (result == false ){
			System.out.println(result);
			System.out.println("In-valid proint value, please re-check the input");
			System.exit(1);
			}
		System.out.println(result);
		return result;
		}
		else {
			sc.close();
			throw new IOException("Not valid entry: input must be alphabets and 11 length, plus '-' [A-Za-z -]" + temp.toString());
		}

		
	}
	
	
	       /*********************************************
			********** Conversion numbers ***************
			*********************************************/
	
	/**
	 * Returns int_proquint for a given int
	 * @param number
	 * @throws IOException if input out of the range
	 */
	public static void int_to_proint(int number) throws IOException{

		final int proint_length = 11;
		StringBuffer buffer = new StringBuffer(proint_length);
		
		if (number <= Integer.MAX_VALUE || number < Integer.MIN_VALUE ) {			
		Proquint.int_to_proint(buffer, number, '-');
		System.out.println("Proint for: "+ number + "  is: " +buffer);
		} 
		else { 
			throw new IOException("Number out of range: " + number);
		}
	}

	/**
	 * Returns short_proquint for a given short
	 * @param number
	 * @throws IOException out of the range
	 */
	public static void short_to_proshort (short number) throws IOException{
		
		final short proshort_length = 5;
		StringBuffer buffer = new StringBuffer(proshort_length);
		Proquint.int_to_proint(buffer, number, '-'); 
		System.out.println("Proshort for: "+ number + "  is: " + buffer.subSequence(6, 11));

	}
	
	
	/**
	 * Returns a long_proquint for a given long
	 * @param number
	 * @throws IOException if input out of the range
	 */
	public static void long_to_prolong (long number) throws IOException {
		
		long firstpart = (number >> 32) ;
		int secpart = (int) number;
		
		final int prolong_length = 11 ;
		StringBuffer longnumb = new StringBuffer(prolong_length+1);
		StringBuffer longnumb1 = new StringBuffer(prolong_length);
		
		Proquint.int_to_proint(longnumb, (int) firstpart, '-');

		Proquint.int_to_proint(longnumb1, secpart, '-');
		System.out.println("Proint for: "+ number + "  is: " +longnumb.toString() + "-"  + longnumb1.toString());
	}
	
	
			/************************************************
			 *********  Conversion Proquint (String) ********
			 ************************************************/
	
	/**
	 * Returns int for a given int_proquint
	 * @throws IOException if Not valid entry
	 */
	public static void proint_to_Int() throws IOException{
	
		Scanner sc = new Scanner (System.in);
		System.out.print("Enter proint value:");
		String tem = sc.nextLine();
		String temp = tem.toLowerCase();
		if(temp.length() == 11 && temp.matches("[A-Za-z -]+")){
		StringReader proint_val = new StringReader(temp);
		int return_proquint = Proquint.proint_to_int(proint_val);
		
		System.out.println("Entered proint converted to:" + return_proquint);
		sc.close();
		}
		else { 
			sc.close();
			throw new IOException("Not valid entry; input must be alphabets and 11 length, plus '-' [A-Za-z -]" + temp.toString());
		}
	}
	
	/**
	 * Returns short for a given short_proquint
	 * @throws IOException if Not valid entry
	 */
	public static void proshort_to_short() throws IOException{
	
		Scanner sc = new Scanner (System.in);
		System.out.print("ENTER PROSHORT:");
		String tem = sc.nextLine();
		String temp = tem.toLowerCase();
		if(temp.length() == 5 && temp.matches("[A-Za-z]+")){
		StringReader proint_val = new StringReader(temp);
		int return_proquint = Proquint.proint_to_int(proint_val);
		
		System.out.println("Entered proint converted to:" + return_proquint);
		sc.close();
		}
		else { 
			sc.close();
			throw new IOException("Not a valid entry; input must be alphabets and 5 length" + temp.toString());
		}
		
	}
	
	/**
	 * Returns long for a given long_proquint 
	 * @throws IOException if Not valid entry
	 */
	public static void prolong_to_log() throws IOException{
	
		Scanner sc = new Scanner (System.in);
		System.out.print("ENTER PROLONG:");
		String tem = sc.nextLine();
		String temp = tem.toLowerCase();
		
		if(temp.length() == 23 && temp.matches("[A-Za-z -]+")){
			
		StringReader proint_val = new StringReader(temp.substring(0, 11));
		StringReader proint_val2 = new StringReader(temp.substring(12, 23));
		
		int return_proquint = Proquint.proint_to_int(proint_val);
		int return_proquint2 = Proquint.proint_to_int(proint_val2);
		
		long left_shift =  (((long)return_proquint) << 32);
		long bit_and  =   (return_proquint2 & 0xFFFFFFFF) ;
		long result = left_shift | bit_and ;
		
		System.out.println("Entered PROLONG converted to:" +  result );
		sc.close();
		}
		else { 
			sc.close();
			throw new IOException("Not a valid entry: input must be alphabets and 23 length " + temp.toString());
		}
	}
	
	
	
			/**********************************************
			 *********   Random Identifiers  **************
			 **********************************************/
	
	/**
	 * Returns a random short_proquint
	 * @throws IOException
	 */
	public static void random_proshort() throws IOException {
		
		short min = Short.MIN_VALUE;
		short max = Short.MAX_VALUE;
		
		Random rand = new Random();
		short  number = (short) (rand.nextInt(max-min) + min) ;
		
		short_to_proshort(number);
		
	}
	
	/**
	 * Returns random int_proquint
	 * @throws IOException
	 */
	public static void random_proint() throws IOException {
		
		Random rand = new Random();
		int  number = rand.nextInt(Integer.MAX_VALUE);
		
		int sign = rand.nextInt(2);
		if (sign == 1)
			number = number * -1;
		
		System.out.println("Random number created:  "+number);
		
		int_to_proint(number);
	}
	
	/**
	 * Returns a random long_proquint
	 * @throws IOException
	 */
	public static void random_prolong() throws IOException {
			
		Random rand = new Random();
		long n = (rand.nextLong());
		
		System.out.println(n);
		
		long_to_prolong(n);
		
	}
	
}
