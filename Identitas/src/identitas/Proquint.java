package identitas;

import java.io.*;
import java.util.*;

public class Proquint {
	
	//Copyright (c) 2009 Daniel S. Wilkerson
	//https://github.com/dsw/proquint/blob/master/Proquint.java
	//https://github.com/dsw/proquint/blob/master/License.txt
	static final char Int_to_consonant[] = {
		'b', 'd', 'f', 'g',
	    'h', 'j', 'k', 'l',
	    'm', 'n', 'p', 'r',
	    's', 't', 'v', 'z'
	  };

	static final char Int_to_vowel[] = {
		
		'a', 'i', 'o', 'u'
		
	};
	
	
	static void int_to_proint (StringBuffer quint /*output*/, int i, char sepChar){
		
		int j;

	    final int MASK_FIRST4 = 0xF0000000;
	    final int MASK_FIRST2 = 0xC0000000;

	    j = i & MASK_FIRST4; i <<= 4; j >>>= 28; quint.append(Int_to_consonant[j]);
	    j = i & MASK_FIRST2; i <<= 2; j >>>= 30; quint.append(Int_to_vowel[j]);
	    j = i & MASK_FIRST4; i <<= 4; j >>>= 28; quint.append(Int_to_consonant[j]);
	    j = i & MASK_FIRST2; i <<= 2; j >>>= 30; quint.append(Int_to_vowel[j]);
	    j = i & MASK_FIRST4; i <<= 4; j >>>= 28; quint.append(Int_to_consonant[j]);

	    if (sepChar != -1) {
	      quint.append(((char) sepChar));
	    }

	    j = i & MASK_FIRST4; i <<= 4; j >>>= 28; quint.append(Int_to_consonant[j]);
	    j = i & MASK_FIRST2; i <<= 2; j >>>= 30; quint.append(Int_to_vowel[j]);
	    j = i & MASK_FIRST4; i <<= 4; j >>>= 28; quint.append(Int_to_consonant[j]);
	    j = i & MASK_FIRST2; i <<= 2; j >>>= 30; quint.append(Int_to_vowel[j]);
	    j = i & MASK_FIRST4; i <<= 4; j >>>= 28; quint.append(Int_to_consonant[j]);
		
		
	}

	static int proint_to_int (StringReader pro_value)  throws IOException {
		
		int res = 0;

	    while(true) {
	      final int c = pro_value.read();
	      if (c == -1) break;

	      switch(c) {

	        /* consonants */
	      case 'b': res <<= 4; res +=  0; break;
	      case 'd': res <<= 4; res +=  1; break;
	      case 'f': res <<= 4; res +=  2; break;
	      case 'g': res <<= 4; res +=  3; break;

	      case 'h': res <<= 4; res +=  4; break;
	      case 'j': res <<= 4; res +=  5; break;
	      case 'k': res <<= 4; res +=  6; break;
	      case 'l': res <<= 4; res +=  7; break;

	      case 'm': res <<= 4; res +=  8; break;
	      case 'n': res <<= 4; res +=  9; break;
	      case 'p': res <<= 4; res += 10; break;
	      case 'r': res <<= 4; res += 11; break;

	      case 's': res <<= 4; res += 12; break;
	      case 't': res <<= 4; res += 13; break;
	      case 'v': res <<= 4; res += 14; break;
	      case 'z': res <<= 4; res += 15; break;

	        /* vowels */
	      case 'a': res <<= 2; res +=  0; break;
	      case 'i': res <<= 2; res +=  1; break;
	      case 'o': res <<= 2; res +=  2; break;
	      case 'u': res <<= 2; res +=  3; break;

	        /* separators */
	      default: break;
	      }
	    }

	return res;
	}

	
	
	
		     /******* Done by Nizal Alshammry ******/
	  /********************************************************** 
	   **************** Conversion numbers **********************
	   **********************************************************/

	 /*Integer to proint conversion*/
	public static void IntegerConvert(int number) throws IOException{

		final int proint_length = 11;
		StringBuffer buffer = new StringBuffer(proint_length);
		
		if (number <= Integer.MAX_VALUE || number < Integer.MIN_VALUE ) {			
		int_to_proint(buffer, number, '-');
		System.out.println("Proint for: "+ number + "  is: " +buffer);
		} 
		else { 
			throw new IOException("Number out of range: " + number);
		}
	}

	
	/*Short to proshort conversion*/
	public static void ShortConvert (short number) throws IOException{
		
		final short proshort_length = 5;
		StringBuffer buffer = new StringBuffer(proshort_length);
		int_to_proint(buffer, number, '-'); 
		System.out.println("Proshort for: "+ number + "  is: " + buffer.subSequence(6, 11));

	}
	
	
	/*Long to prolong conversion*/
	public static void LongConvert (long number) throws IOException {
		
		long firstpart = (number >> 32) ;
		int secpart = (int) number;
		
		final int prolong_length = 11 ;
		StringBuffer longnumb = new StringBuffer(prolong_length+1);
		StringBuffer longnumb1 = new StringBuffer(prolong_length);
		
		int_to_proint(longnumb, (int) firstpart, '-');

		int_to_proint(longnumb1, secpart, '-');
		System.out.println("Proint for: "+ number + "  is: " +longnumb.toString() + "-"  + longnumb1.toString());
	}
	
	
	
	
	/*******************************************************************
	 **************  Conversion Proquint (String) **********************
	 ********************************************************************/
	
	/*Proint to Int  conversion*/
	public static void proint_to_Int() throws IOException{
	
		Scanner sc = new Scanner (System.in);
		System.out.print("Enter proint value:");
		String tem = sc.nextLine();
		String temp = tem.toLowerCase();
		if(temp.length() == 11 && temp.matches("[A-Za-z -]+")){
		StringReader proint_val = new StringReader(temp);
		int return_proquint = proint_to_int(proint_val);
		
		System.out.println("Entered proint converted to:" + return_proquint);
		sc.close();
		}
		else { 
			sc.close();
			throw new IOException("Not valid entry; input must be alphabets and 11 length, plus '-' [A-Za-z -]" + temp.toString());
		}
	}
	
	/*Proshort to Short  conversion*/
	public static void proshort_to_short() throws IOException{
	
		Scanner sc = new Scanner (System.in);
		System.out.print("ENTER PROSHORT:");
		String tem = sc.nextLine();
		String temp = tem.toLowerCase();
		if(temp.length() == 5 && temp.matches("[A-Za-z]+")){
		StringReader proint_val = new StringReader(temp);
		int return_proquint = proint_to_int(proint_val);
		
		System.out.println("Entered proint converted to:" + return_proquint);
		sc.close();
		}
		else { 
			sc.close();
			throw new IOException("Not a valid entry; input must be alphabets and 5 length" + temp.toString());
		}
		
	}
	
	/*Prolong to Long  conversion*/
	public static void prolong_to_log() throws IOException{
	
		Scanner sc = new Scanner (System.in);
		System.out.print("ENTER PROLONG:");
		String tem = sc.nextLine();
		String temp = tem.toLowerCase();
		
		if(temp.length() == 23 && temp.matches("[A-Za-z -]+")){
			
		StringReader proint_val = new StringReader(temp.substring(0, 11));
		StringReader proint_val2 = new StringReader(temp.substring(12, 23));
		
		int return_proquint = proint_to_int(proint_val);
		int return_proquint2 = proint_to_int(proint_val2);
		
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
	
	 
	/*******************************************************************
	 ********************   Random Identifiers  ************************
	 ********************************************************************/
	
	/* Return random proshort*/
	public static void random_proshort() throws IOException {
		
		short min = Short.MIN_VALUE;
		short max = Short.MAX_VALUE;
		
		Random rand = new Random();
		short  number = (short) (rand.nextInt(max-min) + min) ;
		
		ShortConvert(number);
		
	}
	
	public static void random_proint() throws IOException {
		
		Random rand = new Random();
		int  number = rand.nextInt(Integer.MAX_VALUE);
		
		int sign = rand.nextInt(2);
		if (sign == 1)
			number = number * -1;
		
		System.out.println("Random number created:  "+number);
		
		IntegerConvert(number);
	}
	
	/* Return random prolong*/
	public static void random_prolong() throws IOException {
			
		Random rand = new Random();
		long n = (rand.nextLong());
		
		System.out.println(n);
		
		LongConvert(n);
		
	}
	
}
