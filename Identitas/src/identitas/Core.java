package identitas;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;
import identitas.Proquint;
import identitas.Damm;

public class Core {
	
	public static int min = Integer.MIN_VALUE;  //-2147483648
	public static int max = Integer.MAX_VALUE; //  2147483647
	
	
	/* Main Method*/
	public static void main(String[] args ) throws Exception {
		
		random_damm_proint();		
		
		proint_damm_valid();

	}
	
	
	
	/*Returns the division of integer-max by random number, both integer*/
	private static int max_val_by_10 (){
		
		int a = max / 10 ;
		return a;
	}
	

	/* Generate random proint and validate it with Damm algorithm*/
	public static void random_damm_proint() throws IOException {
		
		Random rand = new Random();
		int  number = rand.nextInt(max_val_by_10());
		
		int sign = rand.nextInt(2);
		if (sign == 1)
			number = number * -1;
		System.out.println("Random number created:  "+number);
		
		try {
			
			Damm.addcheck(number);
		} catch (IndexOutOfBoundsException e) {
		    System.err.println("IndexOutOfBoundsException: " + e.getMessage());
		}
		
		Proquint.IntegerConvert(number);
	}
	
	
     /* Validate a given proint with Damm algorithm */
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
	
	

}
