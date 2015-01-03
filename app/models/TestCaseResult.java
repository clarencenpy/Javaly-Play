package models;

/**
 * Created by clarencenpy on 15/12/14.
 */
public class TestCaseResult {
    private final int id;
    private final String input;
    private final String resultOutput;
    private final String expectedOutput;
    private final boolean hasPassed;

    public TestCaseResult(int id, String input, String resultOutput, String expectedOutput, boolean hasPassed) {
        this.id = id;
        this.input = input;
        this.resultOutput = resultOutput;
        this.expectedOutput = expectedOutput;
        this.hasPassed = hasPassed;
    }

    public int getId(){
        return id;
    }

    public String getInput(){
        return input;
    }

    public String getResultOutput(){
        return resultOutput;
    }

    public String getExpectedOutput(){
        return expectedOutput;
    }

    public boolean getHasPassed(){
        return hasPassed;
    }

}
