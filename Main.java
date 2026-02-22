package com.gqt;

import java.util.*;
import java.io.*;

// Question Class
class Question implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3617240935072672970L;
	private String question;
    private String[] options;
    private int correctAnswer;

    public Question(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}

// Quiz Class
class Quiz {
    private ArrayList<Question> questions = new ArrayList<>();

    // Add Question
    public void addQuestion(Question q) {
        questions.add(q);
    }

    // Save Questions to File
    public void saveQuestions() {
        try {
            ObjectOutputStream oos =
                    new ObjectOutputStream(new FileOutputStream("questions.dat"));
            oos.writeObject(questions);
            oos.close();
            System.out.println("Questions saved successfully!");
        } catch (Exception e) {
            System.out.println("Error saving questions.");
        }
    }

    // Load Questions from File
    @SuppressWarnings("unchecked")
	public void loadQuestions() {
        try {
            ObjectInputStream ois =
                    new ObjectInputStream(new FileInputStream("questions.dat"));
            questions = (ArrayList<Question>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            System.out.println("No questions found. Please add questions first.");
        }
    }

    // Start Quiz
    public int startQuiz() {
        Scanner sc = new Scanner(System.in);
        int score = 0;

        if (questions.isEmpty()) {
            System.out.println("No questions available.");
            return 0;
        }

        for (Question q : questions) {
            System.out.println("\n" + q.getQuestion());

            String[] options = q.getOptions();
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }

            System.out.print("Enter your answer (1-4): ");
            int answer = sc.nextInt();

            if (answer == q.getCorrectAnswer()) {
                score++;
            }
        }
        return score;
    }

    public int getTotalQuestions() {
        return questions.size();
    }
}

// Result Class
class Result {

    public void displayResult(int score, int total) {
        System.out.println("\n===== RESULT =====");
        System.out.println("Total Questions: " + total);
        System.out.println("Your Score: " + score);
    }

    public void saveResult(int score) {
        try {
            FileWriter fw = new FileWriter("result.txt", true);
            fw.write("Score: " + score + "\n");
            fw.close();
            System.out.println("Result saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving result.");
        }
    }
}

// Main Class
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Quiz quiz = new Quiz();
        Result result = new Result();

        while (true) {
            System.out.println("\n===== ONLINE QUIZ APPLICATION =====");
            System.out.println("1. Admin - Add Question");
            System.out.println("2. Start Quiz");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter Question: ");
                    String question = sc.nextLine();

                    String[] options = new String[4];
                    for (int i = 0; i < 4; i++) {
                        System.out.print("Enter Option " + (i + 1) + ": ");
                        options[i] = sc.nextLine();
                    }

                    System.out.print("Enter Correct Option Number (1-4): ");
                    int correct = sc.nextInt();

                    quiz.addQuestion(new Question(question, options, correct));
                    quiz.saveQuestions();
                    break;

                case 2:
                    quiz.loadQuestions();
                    int score = quiz.startQuiz();
                    result.displayResult(score, quiz.getTotalQuestions());
                    result.saveResult(score);
                    break;

                case 3:
                    System.out.println("Exiting Application...");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}
