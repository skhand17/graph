package Practice;


import java.util.*;

class PairThree {

    String word;
    int steps;

    public PairThree(String word, int steps) {
        this.word = word;
        this.steps = steps;
    }
}
public class WordLadderI {
    public static void main(String[] args) {

        String startWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>(List.of("hot", "dot", "dog", "lot", "log", "cog"));

        int length = bfsWordLadderI(startWord, endWord, wordList);
        System.out.println(length);
    }


    public static int bfsWordLadderI(String startWord, String endWord, List<String> wordList) {


        Queue<PairThree> queue = new LinkedList<>();
        queue.add(new PairThree(startWord, 1));

        Set<String> hashSet = new HashSet<>();
        for(String s: wordList){
            hashSet.add(s);
        }
        hashSet.remove(startWord);
        while (!queue.isEmpty()) {

            String word = queue.peek().word;
            int steps = queue.peek().steps;

            queue.poll();

            if(word.equalsIgnoreCase(endWord)) return steps;


            for(int i=0; i<word.length(); i++){
                for(char ch ='a'; ch <='z';  ch++) {
                    char[] replacedCharArray = word.toCharArray();
                    replacedCharArray[i] = ch;
                    String newWord = new String(replacedCharArray);

                    if(hashSet.contains(newWord)) {
                        queue.add(new PairThree(newWord, steps + 1));
                        hashSet.remove(newWord);
                    }
                }
            }
        }
        return 0;
    }
}
