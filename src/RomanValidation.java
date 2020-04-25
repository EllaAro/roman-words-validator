import java.util.HashMap;

public class RomanValidation {
	public static final int MAX_V_OR_L_OCCURRENCES = 2;
	public static final int MAX_LITERAL_OCCURRENCES = 4;

	/**
	 * Validate if a given string is a valid Roman number.
	 * Acts as a wrapper for sub-validation methods.
	 */
	public boolean isRomanNumber(String s) {
		return !( s == null || s.length() == 0 || !isValidSequence(s) || !isValidSubtraction(s) );
	}
	
	/**
	 * Validate that the given string is within the range of the Roman letters and validate 
	 * rules 2 and 3.
	 */
	private static boolean isValidSequence(String s) {
		int occurCounter = 0, countV = 0, countL = 0; 
		int currentChar = s.charAt(0); 

		for ( int i = 0 ; i < s.length() ; i++ ) {

			// While iterating we check if the current character is within the range of the Roman letters.
			if ( !isWithinRange(s.charAt(i)) ) {
				return false;	
			}

			// Detecting if the current character equals to 'V'
			if ( s.charAt(i) == 'V' ) { 
				countV++; 

				// If we're seen more than one occurrence of the letter 'V', we return false (rule 3).
				if ( countV == MAX_V_OR_L_OCCURRENCES ) {
					return false;
				}
			}

			// Detecting if the current character equals to 'L'
			if ( s.charAt(i) == 'L' ) { 
				countL++; 

				// If we're seen more than one occurrence of the letter 'L', we return false (rule 3).
				if ( countL == MAX_V_OR_L_OCCURRENCES ) {
					return false;
				}
			}

			// The sequence continues.
			if ( s.charAt(i) == currentChar ) { 
				occurCounter++; 

				// If we see a sequence of 'C', 'X' or 'I' longer than 3 (rule 2), we return false.
				if ( occurCounter == MAX_LITERAL_OCCURRENCES && 
					( s.charAt(i) == 'I' || s.charAt(i) == 'X' || s.charAt(i) == 'C') ) 
				{
					return false;
				}
			
			// The sequence has ended, a different character is introduced. 
			} else {
				currentChar = s.charAt(i); 
				occurCounter=1; 
			}
		}

		// None of the rules (2,3) have been violated
		return true;
	}
	
	/** 
	 * Check whether the given character is within the range of Roman characters: 'I','X','C','V' and 'L'. 
	 */
	private static boolean isWithinRange(Character c) {
		return !(c != 'I' && c != 'V' && c != 'X' && c != 'L' && c != 'C');
	}
	
	/**
	 * Validate the given string according to rules 1 and 4. 
	 */
	private static boolean isValidSubtraction(String s) {
		// Create a hashMap of the Roman letters.
		HashMap<Character, Integer> lettersMap = createHash();	
		int i = 0;

		// Rule 1 is validated by iterating through the characters, updating 'currentMin' in the process.
		// 'currentMin' is initially set to 100 (upper-bound). 
		int currentMin = 100; 
		
		while ( i < s.length() ) {

			// Check if the two adjacent characters where left character's numeric value is smaller than the right.
			if ( i + 1 < s.length() ) {
				if ( lettersMap.get(s.charAt(i)) < lettersMap.get(s.charAt(i+1)) ) {  

					// Making sure that rule 4a is applied.
					if ( s.charAt(i) != 'X' && s.charAt(i) != 'I' ) {
						return false;
					}
					
					int subValue = lettersMap.get(s.charAt(i+1)) - lettersMap.get(s.charAt(i)); 
					
					// Checking if the subtraction of the right character from the left lesser/equals to currentMin (rule 1)
					if ( currentMin >= subValue ) { 
						// If so, we update the currentMin to be the subtraction value.
						currentMin = subValue; 
						i += 2; 
						continue;

					} else {
						// Rule is violated
						return false;
					}
				}
			}

			// Checking if rule 1 is applied.
			if ( currentMin >= lettersMap.get(s.charAt(i)) ) { 

				// If so, we update the currentMin to be the character numeric value.
				currentMin = lettersMap.get(s.charAt(i)); 
				i++; 
			
			} else {
				// Rule 1 is violated
				return false;	
			}
		}

		// None of the rules (1,4) have been violated.
		return true; 
	}
	
	/** 
	 * Initialize a hash table which keys are the character and the values are numeric Roman values.
	 */
	private static HashMap<Character,Integer> createHash() {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();

		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 10);
		map.put('L', 50);
		map.put('C', 100);

		return map;	
	}
}