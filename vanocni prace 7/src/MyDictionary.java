import java.util.ArrayList;

public class MyDictionary {
    private ArrayList<String> dictionary;

    /**
     * Checks if the dictionary contains the specified word
     * @param word
     * @return
     */
    public boolean contains(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Word cannot be null");
        }
        return dictionary.contains(word.toLowerCase());
    }

    public ArrayList<String> getDictionary() {
        return dictionary;
    }

    /**
     * Sets the dictionary
     * @param dictionary
     */
    public void setDictionary(ArrayList<String> dictionary) {
        if (dictionary == null) {
            throw new IllegalArgumentException("Dictionary cannot be null");
        }
        this.dictionary = dictionary;
    }


    /**
     * Performs spell check on the given sentence.
     * @param sentence
     * @return
     */
    public String spellCheck(String sentence) {

        if (sentence.trim().isEmpty()) {
            throw new IllegalArgumentException("Sentence is blank");
        }

        StringBuilder result = new StringBuilder();
        String[] words = sentence.split("\\s+");
        for (String word : words) {
            result.append(word).append(" : ");
            if (this.contains(word)) {
                result.append("correct");
            } else {
                result.append("incorrect");
            }
            result.append("\n");
        }

        return result.toString();
    }

    public MyDictionary() {
        dictionary = new ArrayList<>();
    }

}
