import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MyDictionary dictionary = new MyDictionary();
        FileManager fileManager = new FileManager(new File("file.txt"));
        dictionary.setDictionary(fileManager.toArrayList());

        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Welcome to english spelling checker (Quit=0)");
            System.out.println("Write a word or a sentence:");

            String sentence = scanner.nextLine();
            if(sentence.equals("0")){
                System.exit(0);
            }
            System.out.println(dictionary.spellCheck(sentence));

        }
    }
}