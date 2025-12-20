import java.util.Scanner;

public class Kremala{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int maximum_mistakes = 5;
        int mistakes_done = 0;
        String lexi = "PROGRAM";  
        String[] lexi_pinakas = lexi.split("");  // Μετατροπή της λέξης σε πίνακα χαρακτήρων, για ευκολότερη σύγκριση με την χρήση του scanner.
        boolean[] letters_found = new boolean[lexi_pinakas.length];//Παράλληλος πίνακας (Parallel Array): Κάθε θέση αντιστοιχεί στο γράμμα της λέξης.
                                                                    //και παίρνει τιμη αναλόγως του αν έχει βρεθεί (true) ή όχι (false). Αρχικά όλες είναι false.
        String typedLetters = "";// Καταγραφή των γραμμάτων που έχουν δοθεί για εύκολο έλεγχο επανάληψης με την μέθοδο .contains()

        System.out.printf("Θέλεις να παίξουμε κρεμάλα; \nΜόνο πρόσεξε διότι έχεις δυνατότητα για %d λάθη.%n", maximum_mistakes);
        System.out.printf("Η λέξη που πρέπει να μαντέψεις έχει %d γράμματα.\n", lexi_pinakas.length);
        while (mistakes_done<maximum_mistakes) {
            String given_letter;
            boolean letter_found = false;
            System.out.print("Μάντεψε το γράμμα: ");
            given_letter = scanner.nextLine().toUpperCase();

            if (!isValidInput(given_letter)) {
                continue;
            }
            if (typedLetters.contains(String.valueOf(given_letter))){
                        System.out.println("Έχεις ήδη δώσει το γράμμα αυτό!");
                        continue;
                } 
            typedLetters += given_letter;
            boolean found = updateGameState(lexi_pinakas, letters_found, given_letter);// Καλώ τη μέθοδο και αποθηκεύω την απάντηση
            if (!found) {// Αν ΔΕΝ βρέθηκε το γράμμα, τότε μόνο αυξάνεις τα λάθη στη main
            mistakes_done++;
            System.out.printf("Ουπς, το γράμμα %s δεν υπάρχει. Λάθη που απομένουν: %d%n", 
                given_letter, maximum_mistakes - mistakes_done);
            }

            printCurrentProgress(lexi_pinakas, letters_found);//Δείχνω στον χρήστη, την λέξη με ευρεθέντα και άγνωστα γράμματα.

            boolean allFound = true; // Υποθέτω ότι βρέθηκαν όλα και ψάχνουμε για μία διάψευση (false)
            for (int i = 0; i < letters_found.length; i++) {
                if (!letters_found[i]) { // Αν οποιαδήποτε θέση δεν έχει βρεθεί οπότε και η τιμή της είναι ακόμη false.
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
    public static boolean isValidInput(String input){
        if (input.isEmpty()) {
            System.out.println("Δεν έγραψες κάτι. Ξαναπροσπάθησε!");
            return false;
        } else if (input.length()>1){
            System.out.println("Έγραψες περισσότερα από ένα γράμματα. Ξαναπροσπάθησε!");
            return false;
        } 
        char c = input.charAt(0);
        if (!Character.isLetter(c)) {
            System.out.println("Αυτό δεν είναι γράμμα! Δώσε έναν χαρακτήρα από το Α-Ω.");
            return false;
          }
        else {
            return true;
        }
    }

    public static void printCurrentProgress(String[] lexi_pinakas, boolean[] letters_found){
        //String[] alreadyfound;
        for (int i = 0; i < lexi_pinakas.length; i++) {
            if (letters_found[i]) {
                System.out.print(" " + lexi_pinakas[i] + " ");
            } else {
                System.out.print(" _ ");
            }
        }
        System.out.println("");
    }

    public static boolean updateGameState(String[] lexi_pinakas, boolean[] letters_found, String given_letter) {
    boolean foundAtLeastOne = false; // Τοπική μεταβλητή για να ξέρω αν βρέθηκε κάτι σε αυτόν τον γύρο
    
    for (int i = 0; i < lexi_pinakas.length; i++) {
        if (given_letter.equals(lexi_pinakas[i])) {
            if (!foundAtLeastOne) {
                System.out.printf("Το γράμμα %s βρίσκεται στην %d θέση της λέξης.%n", given_letter, i + 1);
            } else {
                System.out.printf("Επίσης βρίσκεται και στην %d θέση της λέξης.%n", i + 1);
            }
            letters_found[i] = true;
            foundAtLeastOne = true;
        }
    }
    return foundAtLeastOne; // Επιστρέφω το αποτέλεσμα στη main
}
}
