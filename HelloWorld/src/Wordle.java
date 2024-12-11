import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;


public class Wordle {
    public static void main(String[] args) {
        Wordle w = new Wordle();
        w.game();
    }
    private void loadWordsFromFile(String fileName,List<String> words) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String word;
            int count = 0;
            while ((word = br.readLine()) != null) {
                words.add(word.trim());
                count++;
            }
            System.out.println(count + " Words loaded successfully from " + fileName);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public void game(){
        ArrayList<String> words = new ArrayList<>();
        loadWordsFromFile(
                "File Path",words);
        Scanner s = new Scanner(System.in);
        boolean continueGame = true;

        while (continueGame) {
            System.out.println("Enter new hints (use '_' for unknown letters): ");
            String word = s.nextLine();

            System.out.println("Enter letters which are not used (as a single string): ");
            String notUsedInput = s.nextLine();
            char[] notUsed = notUsedInput.toCharArray(); // Convert to char array

            System.out.println("Here are possible answers:");
            List<String> possibleAnswers = wordFinder(word, words, notUsed);
            System.out.println(possibleAnswers);

            System.out.println("If you want to finish the game press 1, else press 0");
            int n = s.nextInt();
            s.nextLine(); // consume the newline character

            if (n == 1) {
                continueGame = false;
            }
        }
    }
    public List<String> wordFinder(String word ,List<String> words,char[] notUsed){
        List<String> answer = new ArrayList<>();
        for (String s : words) {
            boolean matches = true;

            for (int j = 0; j < 5; j++) {
                if (word.charAt(j) != '_' && word.charAt(j) != s.charAt(j)) {
                    matches = false;
                    break;
                }
            }
            for (char c : notUsed) {
                if (s.indexOf(c) != -1) { // If the letter is found in s
                    matches = false;
                    break;
                }
            }


            if (matches ) {
                    answer.add(s);
                }

        }

        return answer;
    }



}
