package CombatClassification;

import data.CombatD;
import enums.CombatEnums.*;
import enums.PokemonEnums.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Anders Holst, Sabina Hult & Line Kreiberg
 */
public class TestID3 {
    private static List<CombatD> falselyClassifiedFirstWin;
    private static List<CombatD> falselyClassifiedSecondWin;
    private static double percentCorrect;

    /**
     * Classifies an unknown set of respondents and checks the results
     * @param node
     * @param testdata
     * @return - results of classification
     */
    public static String testDecisionRules(Node node, List<CombatD> testdata) {
        falselyClassifiedFirstWin = new ArrayList<>();
        falselyClassifiedSecondWin = new ArrayList<>();

        double total = testdata.size();
        double trueFirst = 0;
        double falseFirst = 0;
        double trueSecond = 0;
        double falseSecond = 0;
        double trueTotal, falseTotal,trueFirstPerc, trueSecondPerc,
                falseFirstPerc, falseSecondPerc, trueTotalPerc, falseTotalPerc;

        for(CombatD combat : testdata) {
            Object guess = ID3.classify(node, combat);
            Object actual = combat.getAttributeValue(CombatClass.class);

            if(guess.equals(CombatClass.FIRSTWIN)) {
                if(actual.equals(CombatClass.FIRSTWIN)) trueFirst++;
                else {
                    falseFirst++;
                    falselyClassifiedFirstWin.add(combat);
                }
            }
            if(guess.equals(CombatClass.SECONDWIN)) {
                if(actual.equals(CombatClass.SECONDWIN)) trueSecond++;
                else {
                    falseSecond++;
                    falselyClassifiedSecondWin.add(combat);
                }
            }
        }

        trueTotal = trueFirst + trueSecond;
        falseTotal = falseFirst + falseSecond;

        trueFirstPerc = (trueFirst / total) * 100;
        trueSecondPerc = (trueSecond / total) * 100;
        falseFirstPerc = (falseFirst / total) * 100;
        falseSecondPerc = (falseSecond / total) * 100;

        trueTotalPerc = (trueTotal / total) * 100;
        percentCorrect = trueTotalPerc;
        falseTotalPerc = (falseTotal / total) * 100;

        StringBuilder result = new StringBuilder();
        result.append(String.format("\nTrue First Win: %.2f", trueFirstPerc)).append("%");
        result.append(String.format("\nTrue Second Win: %.2f", trueSecondPerc)).append("%");
        result.append(String.format("\nTotal True: %.2f", trueTotalPerc)).append("%\n");
        result.append(String.format("\nFalse First Win: %.2f", falseFirstPerc)).append("%");
        result.append(String.format("\nFalse Second Win: %.2f", falseSecondPerc)).append("%");
        result.append(String.format("\nTotal Neg: %.2f", falseTotalPerc)).append("%\n");

        double regRate = trueTotal / total;
        double errRate = (falseFirst + falseSecond) / total;

        result.append("\n\nRecognition Rate: " + regRate);
        result.append("\nError Rate: " + errRate);

        return result.toString();
    }

    public static double getPercentCorrect() { return percentCorrect; }

    /**
     * Puts the list of falsely classified FIRST WIN and falsely classified SECOND WIN
     * into a map, FIRST WIN mapped to 1, and SECOND WIN mapped to 2
     * @return
     */
    public static Map<Integer, List<CombatD>> getFalselyClassifiedObjects() {
        Map<Integer, List<CombatD>> falselyClassified = new HashMap<>();
        falselyClassified.put(1, falselyClassifiedFirstWin);
        falselyClassified.put(2, falselyClassifiedSecondWin);

        return falselyClassified;
    }
}
