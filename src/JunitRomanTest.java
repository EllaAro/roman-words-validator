import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JunitRomanTest {
	
	RomanValidation romanValidation;
	
	@BeforeAll
	static void beforeAllinit() {
		System.out.println("The testing has begun.");
	}
	@AfterAll
	static void afterAll() {
		System.out.println("The testing has finished.");
	}
	
	//Initializing the RomanValidation class object. Executed before each method runs.	
	@BeforeEach
	void init() {
		romanValidation = new RomanValidation();
	}
	
	@Test
	void nullString() {
		boolean expected= false;
		boolean actual = romanValidation.isRomanNumber(null);
		assertEquals(expected, actual, "A null is not a valid Roman numeric.");
	}
	@Test
	void emptyString() {
		boolean expected= false;
		boolean actual = romanValidation.isRomanNumber("");
		assertEquals(expected, actual, "An empty string is not a valid Roman numeric.");
	}
	@Test
	void notWithinRange() {
		char[] inputInvalidCharacters= new char[131]; //Creating a character array consisting of invalid chars
		int i=0;
		int invalidChar=33;
		while(i<131) { //Initializing the array, but making sure to not include the valid characters.
			if(invalidChar!=73 && invalidChar!=86 && invalidChar!=88 && invalidChar!=76 && invalidChar!=67) {
				inputInvalidCharacters[i]= (char) invalidChar;
				i++;
			}
			invalidChar++;
		}
		boolean expected= false;
		for(int j=0; j<131;j++) {
			boolean actual = romanValidation.isRomanNumber(Character.toString(inputInvalidCharacters[j]));
			assertEquals(expected, actual, "A valid roman numeric consists only of characters:'I','V','X','L','C'. This string is invalid, we've detacted an apperance of: "+Character.toString(inputInvalidCharacters[j]));
		}		
	}
	
	//rule1
	@Test
	void decreasingOrderRule() {
		boolean expected = false;
		boolean actual;
		String[] wrongSubValues = {"VXL","IXL","XIL","VIL","IIL","LIC","XIC","VIC","IIC","XCC","IVV","IVX","IVL","IVC","IXX","IXL","IXC","IXL","IVX","IIXX"};
		for (int i=0; i<wrongSubValues.length;i++) {
			actual = romanValidation.isRomanNumber(wrongSubValues[i]);
			assertEquals(expected, actual, "The numberic values are not ordered in a decreasing order. We've detacted an apperance of: "+wrongSubValues[i]);
		}
		System.out.println("The first rule is applied correctly.");
	}
	//rule 2
	@Test
	void succesiveRule() {
		boolean expected = false;
		boolean actual = romanValidation.isRomanNumber("IIII");
		assertEquals(expected, actual, "There cannot be more than three sucssesive apperances of 'I's");
		actual = romanValidation.isRomanNumber("VIIII");
		assertEquals(expected, actual, "There cannot be more than three sucssesive apperances of 'I's");
		actual = romanValidation.isRomanNumber("XXXX");
		assertEquals(expected, actual, "There cannot be more than three sucssesive apperances of 'X's");
		actual = romanValidation.isRomanNumber("LXXXXIV");
		assertEquals(expected, actual, "There cannot be more than three sucssesive apperances of 'X's");
		actual = romanValidation.isRomanNumber("CCCC");
		assertEquals(expected, actual, "There cannot be more than three sucssesive apperances of 'C's");
		actual = romanValidation.isRomanNumber("CCCCI");
		assertEquals(expected, actual, "There cannot be more than three sucssesive apperances of 'C's");
		System.out.println("The second rule is applied correctly.");
		
	}
	//rule 3
	@Test
	void uniqueApperanceRule() {
		boolean expected = false;
		boolean actual;
		String[] wrongSuccV= {"VV","VVI","XVV","LVV","CVV","VIV"};
		for(int i=0;i<wrongSuccV.length;i++) {
			actual = romanValidation.isRomanNumber(wrongSuccV[i]);
			assertEquals(expected, actual, " There cannot be more than two apperances of 'V's.");
		}
		String[] wrongSuccL= {"LL","LLI","LLV","CLL","LLX"};
		for(int i=0;i<wrongSuccL.length;i++) {
			actual = romanValidation.isRomanNumber(wrongSuccL[i]);
			assertEquals(expected, actual, " There cannot be more than two apperances of 'L's.");
		}
		System.out.println("The third rule is applied correctly.");
	}
	//rule 4
	@Test
	void subtractionRule() {
		boolean expected = false;
		boolean actual;
		String[] wrongSuccI= {"IIV","IIX","IIL","IIC"};
		for(int i=0;i<wrongSuccI.length;i++) {
			actual = romanValidation.isRomanNumber(wrongSuccI[i]);
			assertEquals(expected, actual, " 'I' can precede a bigger numeral only once at a time. We've detacted an apperance of: "+wrongSuccI[i]);
		}
		String[] wrongSuccX= {"XXL","XXC"};
		for(int i=0;i<wrongSuccX.length;i++) {
			actual = romanValidation.isRomanNumber(wrongSuccX[i]);
			assertEquals(expected, actual, " 'X' can precede a bigger numeral only once at a time. We've detacted an apperance of: "+wrongSuccX[i]);
		}
		String[] wrongSubValues = {"VX","VL","VC","LC"};
		for (int i=0; i<wrongSubValues.length;i++) {
			actual = romanValidation.isRomanNumber(wrongSubValues[i]);
			assertEquals(expected, actual, " Only 'X' and 'I' can appear before a bigger numeral. We've detacted an apperance of: "+wrongSubValues[i]);
		}
		System.out.println("The forth rule is applied correctly.");
	}

}
