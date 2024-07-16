package PracticeOracleAndC3AI;

import java.util.*;


class QueueTuple {
    String word;
    int steps;

    public QueueTuple(String word, int steps) {
        this.word = word;
        this.steps = steps;
    }
}

public class WordLadder1 {
    public static void main(String[] args) {
        List<String> wordList = new
                ArrayList<>(List.of("hot", "dot", "dog", "lot", "log", "cog"));

        String startWord = "hit";
        String endWord = "cog";

        int ladderLength = wordLadderLength(wordList, startWord, endWord);
        System.out.println(ladderLength);
    }

    private static int wordLadderLength(List<String> wordList,
                                        String startWord, String endWord) {

        Set<String> set = new HashSet<>(wordList);
//        Initial configuration
        Queue<QueueTuple> queue = new LinkedList<>();
        queue.offer(new QueueTuple(startWord, 1));


        while (!queue.isEmpty()) {
            QueueTuple queueTuple = queue.poll();
            String currentWord = queueTuple.word;
            int currentSteps = queueTuple.steps;

            if(currentWord.equals(endWord)) return currentSteps;

            for(int i=0; i<currentWord.length(); i++) {
                for(char ch ='a'; ch<='z'; ch++) {

                    char[] wordCharacter = currentWord.toCharArray();
                    wordCharacter[i] = ch;
                    String replacedWord = new String(wordCharacter);

                    if(set.contains(replacedWord)) {
                        set.remove(replacedWord);
                        queue.offer(new QueueTuple(replacedWord, currentSteps + 1));
                    }
                }
            }
        }
        return 0;
    }
}
