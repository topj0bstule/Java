package stringCalcPackage;

import java.util.ArrayList;

public class stringCalc {
    private String[] tokens;

    public stringCalc(String[] _tokens){
        tokens = _tokens;
    }

    public ArrayList<String> findPal(String[] tokens){
        ArrayList<String> results = new ArrayList<>();
        for(int i = 0; i < tokens.length - 1; i++){
            for(int j = i + 1; j < tokens.length; j++){
                String reversedString = new StringBuilder(tokens[j]).reverse().toString();
                if(tokens[i].equals(reversedString)){
                    results.add(tokens[i] + " - " + tokens[j]);
                }
            }
        }
        return results;
    }
}
