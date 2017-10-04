import java.util.Arrays;

public class BruteForce {
    private char[] word;
    private String pattern;

    /* These are possible character set constants: */
    private final String DIGITS = generateAllDigits();
    private final String LETTERS = generateAllLetters();
    private final String UPPERCASE_LETTERS = generateAllUpperCaseLetters();
    private final String LOWERCASE_LETTERS = generateAllLowerCaseLetters();
    private final String VOWELS = generateAllVowels();
    private final String ALL_CHARS = generateAllChars();
    
    public BruteForce() {
        init(0);
    }

    public BruteForce(int length) {
        init(length);
    }

    public BruteForce(String pattern) {
        init(pattern);
    }

    public void init(int length) {
        // default to using only lowercase letters
        char[] patternChars = new char[length];
        Arrays.fill(patternChars, 'l');
        init(new String(patternChars));
    }
    
    public void init(String pattern) {
        /*  String pattern symbols:
         *  'd' : digit (0-9)
         *  'a' : letter (a-z,A-Z)
         *  'L' : uppercase letter (A-Z)
         *  'l' : lowercase letter (a-z)
         *  'v' : vowel (aeiou)
         *  '*' : all legal ascii characters
         */
        
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
        char firstChar = possibleChars.charAt(0);
        char lastChar = possibleChars.charAt(possibleChars.length() - 1);

        if (word[index] != lastChar) {
            word[index]++;
            return false;
        } else {
            word[index] = firstChar;
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
    
    public String getPossibleChars(char symbol) {
        switch (symbol) {
            case 'd': return DIGITS;
            case 'a': return LETTERS;
            case 'L': return UPPERCASE_LETTERS;
            case 'l': return LOWERCASE_LETTERS;
            case 'v': return VOWELS;
            case '*': return ALL_CHARS;
            default: return "";
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