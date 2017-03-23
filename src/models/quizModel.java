package models;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by ethankulman on 3/19/17.
 */
public abstract class quizModel {

    Scanner scan = new Scanner(System.in);
    private String question;
    public Map<Integer, String> answers = new HashMap<>();

    public abstract boolean checkAnswer(String userAnswer);

    public abstract void setAnswers();

    public void shuffleAnswers(ArrayList<String> unshuffledAnswers){
        Collections.shuffle(unshuffledAnswers);
        int questionNumber = 1;
        for(String q : unshuffledAnswers){
            answers.put(questionNumber, q);
            questionNumber++;
        }
    }

    public void setQuestion() {
        System.out.println("What is the question?");
        this.question = scan.nextLine();
    }


    public boolean askQuestion() {
        System.out.println();
        System.out.println(question);
        int questionTries = 0;
        if(answers.size() > 0) {
            for (Map.Entry<Integer, String> entry : answers.entrySet()) {
                System.out.println(entry.getKey() + ". " + entry.getValue());
            }
        }
        System.out.println();
        do {
            System.out.println("What is your answer?");
            String userAnswer = scan.nextLine();
            if (this.checkAnswer(userAnswer)) {
                System.out.println("You are correct");
                return true;
            } else {
                System.out.println("That's incorrect, try again");
                System.out.println();
                questionTries++;
            }
        } while (questionTries < 3);
    return false;
    }
}

     class mulCh extends quizModel {


        private String correctAnswer;
        @Override
        public void setAnswers() {
            ArrayList<String> unshuffledAnswers = new ArrayList<>();
            boolean stillQuestions = true;
            System.out.println("What is the correct answer?");
            String aCorrectAnswer = scan.nextLine();
            this.correctAnswer = aCorrectAnswer;
            unshuffledAnswers.add(aCorrectAnswer);
            do {
                System.out.println("What is another possible answers? (enter STOP if you're done)");
                String otherAnswer = scan.nextLine().trim();
                if (otherAnswer.toUpperCase().contains("STOP")) {
                    stillQuestions = false;
                } else {
                    unshuffledAnswers.add(otherAnswer);
                }
            } while (stillQuestions);
            shuffleAnswers(unshuffledAnswers);
        }
        @Override
        public boolean checkAnswer(String userAnswer) {
            try {
                int intAnswer = Integer.parseInt(userAnswer);
                if (answers.get(intAnswer) == correctAnswer) {
                    return true;
                }

            } catch (NumberFormatException ex) {
                    if (userAnswer.equals(correctAnswer)) {
                        return true;
                    }
            }

            return false;

        }
    }


    class checkBox extends quizModel {

        private ArrayList<String> correctAnswer = new ArrayList<>();
        private ArrayList<String> unshuffledAnswers = new ArrayList<>();
        @Override
        public void setAnswers() {
            boolean stillQuestions = true;
            do {
                System.out.println("What is the correct answer(s)? (enter STOP if you're done)");
                String aCorrectAnswer = scan.nextLine();
                if (aCorrectAnswer.toUpperCase().contains("STOP")) {
                    stillQuestions = false;
                } else {
                    correctAnswer.add(aCorrectAnswer);
                    unshuffledAnswers.add(aCorrectAnswer);
                }
            } while (stillQuestions);
            stillQuestions = true;
            do {
                System.out.println("What is another possible answers? (enter STOP if you're done)");
                String otherAnswer = scan.nextLine().trim();
                if (otherAnswer.toUpperCase().contains("STOP")) {
                    stillQuestions = false;
                } else {
                    unshuffledAnswers.add(otherAnswer);
                }
            } while (stillQuestions);
            shuffleAnswers(unshuffledAnswers);

        }

        @Override
        public boolean checkAnswer(String userAnswer) {
            String[] userAnswers = userAnswer.split(" ");
            int count = 0;
            for (String entry : userAnswers) {
                if (correctAnswer.contains(entry)) {
                    count++;
                } else {
                    try {
                        int intAnswer = Integer.parseInt(entry);
                        if (correctAnswer.contains(answers.get(intAnswer))) {
                            count++;
                        }
                        else{
                            return false;
                        }
                    } catch (NumberFormatException ex) {
                        return false;
                        }
                }

            }
            return (count == correctAnswer.size());

        }
    }


    class trueFalse extends quizModel {

        private boolean correctAnswer;

        @Override
        public void setAnswers() {
            System.out.println("What is the correct answer?");
            String aCorrectAnswer = scan.nextLine();
            if (aCorrectAnswer.contains("true")) {
                this.correctAnswer = true;
            } else if (aCorrectAnswer.contains("false")) {
                this.correctAnswer = false;
            } else {
                System.out.print("Please enter either true or false.");
            }
            answers.put(1, "true");
            answers.put(2, "false");

        }

        @Override
        public boolean checkAnswer(String userAnswer) {
            boolean boolAnswer = Boolean.parseBoolean(userAnswer);
            return (boolAnswer == correctAnswer);

        }
    }

    class shortAnswer extends quizModel{

            @Override
            public void setAnswers(){
            }

            @Override
            public boolean checkAnswer(String userAnswer){
                return (userAnswer.length() < 80);
            }
    }

    class paragaphAnswer extends quizModel{
            @Override
            public void setAnswers(){
            }

            @Override
            public boolean checkAnswer(String userAnswer){
                return (userAnswer.length() < 500);
            }
    }

    }






