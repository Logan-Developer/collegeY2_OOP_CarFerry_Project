package utils;

public class ProjectUtils {

    /**
     * Check if a string is an int
     * @param s The string to check
     * @return True if it's an int, false otherwise
     */
    public static boolean isInt(String s){
        if(s == null) return false;
        for(int i = 0 ; i < s.length() ; i++){
            if( s.charAt(i) < '0' || s.charAt(i) > '9' ){
                return false;
            }
        }
        return true;
    }

    /**
     * Check if a string is a float
     * @param s The string to check
     * @return True if it's a float, false otherwise
     */
    public static boolean isFloat(String s){
        if(s == null) return false;

        try {
            Float.parseFloat(s);
        }
        catch(NumberFormatException e){
            return false;
        }
        return true;
    }
}
