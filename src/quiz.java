/**
 * Created by ethankulman on 3/19/17.
 */
package models;
import java.util.Scanner;
import java.util.ArrayList;

public class quiz {


    public static void main(String[] args) {
        ArrayList<quizModel> questions = new ArrayList<>();
        boolean continueQuiz = true;
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("Press 0 to add questions or 1 to take the quiz. Type STOP to end the program.");
            String userAction = scan.nextLine();
            if (userAction.equals("0")) {
                quizModel newQuestion = addQuestions();
                if(newQuestion != null) {
                    questions.add(newQuestion);
                }
            } else if (userAction.equals("1")) {
                double totalCorrect = 0;
                System.out.println();
                for (quizModel q : questions) {
                    if(q.askQuestion()){
                        totalCorrect++;
                    }
                }
                double totalAverage = (totalCorrect/questions.size());
                System.out.println();
                System.out.println("Your total score is: " + totalAverage*100);
            } else if (userAction.toUpperCase().contains("STOP")) {
                continueQuiz = false;
            } else {
                System.out.println();
                System.out.println("Please enter either 0, 1, or STOP");
            }
        } while (continueQuiz);
    }

    private static quizModel addQuestions() {
        Scanner scan = new Scanner(System.in);
        System.out.println("What type of question would you like to create?");
        String userInput = scan.nextLine();
        if (userInput.contains("check")) {
            quizModel newCheckBox = new checkBox();
            newCheckBox.setQuestion();
            newCheckBox.setAnswers();
            return newCheckBox;

        } else if (userInput.contains("multiple") || userInput.contains("choice")) {
            quizModel newMulCh = new mulCh();
            newMulCh.setQuestion();
            newMulCh.setAnswers();
            return newMulCh;

        } else if (userInput.contains("true") || userInput.contains("false")) {
            quizModel newTrueFalse = new trueFalse();
            newTrueFalse.setQuestion();
            newTrueFalse.setAnswers();
            return newTrueFalse;
        } else if (userInput.contains("short")){
            quizModel newShortAnswer = new shortAnswer();
            newShortAnswer.setQuestion();
            return newShortAnswer;

        } else if (userInput.contains("paragraph")){
            quizModel newParagraphAnswer = new paragaphAnswer();
            newParagraphAnswer.setQuestion();
            return newParagraphAnswer;
        } else {
            return null;
        }
    }
}





