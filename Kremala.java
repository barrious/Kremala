import java.util.Scanner;

public class Kremala{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int maximum_mistakes = 5;
        int mistakes_done = 0;
        String given_letter ="";
        String lexi = "program";  
        String[] lexi_pinakas = lexi.split("");  // Μετατροπή της λέξης σε array χαρακτήρων,
                                                       // αποτελούμενο από τα γράμματά της, για καλύτερο έλεγχο.
        boolean[] letters_found = new boolean[lexi_pinakas.length];//Δημιουργία πίνακα, στον οποίο κάθε γράμμα αντικαθίσταται από μια boolean τιμή,
                                                                    //ανάλογ από το αν έχει βρεθεί (true) ή όχι (false). Αρχικά όλες είναι false.


        System.out.printf("Θέλεις να παίξουμε κρεμάλα; \nΜόνο πρόσεξε διότι έχεις δυνατότητα για %d λάθη.%n", maximum_mistakes);
        System.out.printf("Η λέξη που πρέπει να μαντέψεις έχει %d γράμματα.\n", lexi_pinakas.length);
        while (mistakes_done<maximum_mistakes) {
            boolean letter_found = false;
            System.out.print("Μάντεψε το γράμμα: ");
            given_letter = scanner.nextLine();
            for (int i = 0; i < lexi_pinakas.length; i++) {
                if (given_letter.equals(lexi_pinakas[i])) {
                    letters_found[i] = true;
                    letter_found = true;
                    System.out.printf("Το γράμμα %s βρίσκεται στην %d θέση της λέξης.\n",given_letter, i+1);
                }
            }
            if (letter_found == false) {
                mistakes_done += 1;
                System.out.printf("Ουπς, το γράμμα %s δεν υπάρχει. Λάθη που απομένουν : %s %n", given_letter, maximum_mistakes - mistakes_done);
            }

            boolean allFound = true;
            for (int i = 0; i < letters_found.length; i++) {
                if (!letters_found[i]) { // Αν οποιαδήποτε θέση δεν έχει βρεθεί και η τιμή της είναι ακόμη false.
                allFound = false;
                break;
                }
            }
            if (allFound) {
                System.out.println("ΚΕΡΔΙΣΕΣ!");
                break;
            }
        }
        if (mistakes_done==maximum_mistakes) {
            System.out.println("Λυπάμαι, έχασες :( ");
            System.out.println("Η σωστή λέξη είναι: " + lexi);
        }
        scanner.close();
    }
}
