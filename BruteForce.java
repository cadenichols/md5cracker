import java.util.Arrays;

public class BruteForce {
    private char[] word;
    private String pattern;
    
    /*  String pattern symbols:
     *  'd' : digit (0-9)
     *  'a' : letter (a-z,A-Z)
     *  'L' : uppercase letter (A-Z)
     *  'l' : lowercase letter (a-z)
     *  'v' : vowel (aeiou)
     *  '*' : wildcard - (almost) all legal ascii characters
     */
    private final char DIGIT_SYMBOL = 'd';
    private final char LETTER_SYMBOL = 'a';
    private final char UPPERCASE_SYMBOL = 'L';
    private final char LOWERCASE_SYMBOL = 'l';
    private final char VOWEL_SYMBOL = 'v';
    private final char WILDCARD_SYMBOL = '*';

    /* These are possible character set constants: */
    private final String DIGITS = generateAllDigits();
    private final String LETTERS = generateAllLetters();
    private final String UPPERCASE_LETTERS = generateAllUpperCaseLetters();
    private final String LOWERCASE_LETTERS = generateAllLowerCaseLetters();
    private final String VOWELS = generateAllVowels(); // lower case only
    private final String ALL_CHARS = generateAllChars();
    
    public BruteForce() {}      
    
    public BruteForce(int length) {
        init(length);
    }

    public BruteForce(String pattern) {
        init(pattern);
    }

    public void init(int length) {
        // default to using only lowercase letters
        char[] patternChars = new char[length];
        Arrays.fill(patternChars, LOWERCASE_SYMBOL);
        init(new String(patternChars));
    }

    public void init(String pattern) {        
        int length = pattern.length();
        this.pattern = pattern;
        word = new char[length];
        
        for (int i = 0; i < length; i++) {
            String possibleChars = getPossibleChars(pattern.charAt(i));
            word[i] = possibleChars.charAt(0);
        }
    }
    
    public void increment() {
        int index = word.length - 1;
        while(increment(index--)) {}
    }
    
    public boolean increment(int index) {
        if (index < 0) return false;
        
        String possibleChars = getPossibleChars(pattern.charAt(index));
        char thisChar = word[index];
        int indexInPossible = possibleChars.indexOf(thisChar);
        
        if (indexInPossible < possibleChars.length() - 1) {
            word[index] = possibleChars.charAt(indexInPossible + 1);
            return false;
        } else {
            word[index] = possibleChars.charAt(0);            
            return true;
        }
    }
                
    public String getCurrent() {
        return new String(word);
    }
    
    public boolean atEnd() {
        char[] last = new char[word.length];
        for (int i = 0; i < word.length; i++) {
            String possibleChars = getPossibleChars(pattern.charAt(i));
            last[i] = possibleChars.charAt(possibleChars.length() - 1);
        }
        return Arrays.equals(word, last);
    }
    
    public String getPossibleChars(char symbol) throws Shade {
        switch (symbol) {
            case DIGIT_SYMBOL: return DIGITS;
            case LETTER_SYMBOL: return LETTERS;
            case UPPERCASE_SYMBOL: return UPPERCASE_LETTERS;
            case LOWERCASE_SYMBOL: return LOWERCASE_LETTERS;
            case VOWEL_SYMBOL: return VOWELS;
            case WILDCARD_SYMBOL: return ALL_CHARS;
            default: throw new Shade("Pattern symbol not allowed: " + symbol);
        }
    }

    public void exhaustAll() {
        /*  Beginning with current word,
         *  increment and print until at end.
         */
        System.out.println(getCurrent());
        while(!atEnd()) {
            increment();
            System.out.println(getCurrent());
        };
    }
    
    /*
     * The following methods generate strings which include
     * all characters that exist in their set.
     * 
     */
    public static String generateAllDigits() {
        String result = "";
        for (int i = 0; i <= 9; i++) {
            result += i;
        }
        return result;
    }

    public static String generateAllLetters() {
        return generateAllUpperCaseLetters() + generateAllLowerCaseLetters();
    }
    
    public static String generateAllUpperCaseLetters() {
        String result = "";
        for (int i = 65; i <= 90; i++) {
            result += (char) i;
        }
        return result;
    }
    
    public static String generateAllLowerCaseLetters() {
        String result = "";
        for (int i = 97; i <= 122; i++) {
            result += (char) i;
        }
        return result;
    }

    public static String generateAllVowels() {
        return "aeiou";
    }
    
    public static String generateAllChars() {
        String result = "";
        for (int i = 32; i <= 126; i++) {
            result += (char) i;
        }
        return result;
    }
}