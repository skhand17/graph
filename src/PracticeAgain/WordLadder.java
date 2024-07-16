package PracticeAgain;

import java.util.*;


class TupleWord {
    String word;
    int steps;

    public TupleWord(String word, int steps) {
        this.word = word;
        this.steps = steps;
    }
}

public class WordLadder {

    public static void main(String[] args) {
        List<String> wordList = new
                ArrayList<>(List.of("hot", "dot", "dog", "lot", "log", "cog"));

        String startWord = "hit";
        String endWord = "cog";

        int ladderLength = ladderLength(wordList, startWord, endWord);
        System.out.println(ladderLength);
    }

    public static int ladderLength(List<String> wordList, String startWord, String endWord) {


        Queue<TupleWord> queue = new LinkedList<>();
        Set<String> set = new HashSet<>();
        set.addAll(wordList);

        queue.add(new TupleWord(startWord, 1));
        set.remove(startWord);

        while (!queue.isEmpty()){
            TupleWord tupleWord = queue.poll();
            String currentWord = tupleWord.word;
            int currentSteps = tupleWord.steps;

            if(currentWord.equals(endWord)) return currentSteps;

            for(int i=0; i<currentWord.length(); i++){
                for(char ch = 'a'; ch <='z'; ch++){
                    char[] replacedChar = currentWord.toCharArray();
                    replacedChar[i] = ch;
                    String replaceWord = new String(replacedChar);

                    if(set.contains(replaceWord)){
                        queue.add(new TupleWord(replaceWord, currentSteps + 1));
                        set.remove(replaceWord);
                    }
                }
            }
        }
        return 0;
    }
}
