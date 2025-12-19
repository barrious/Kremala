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
            if (typedLetters.contains(String.valueOf(given_letter))){
                        System.out.println("Έχεις ήδη δώσει το γράμμα αυτό!");
                        continue;
                } 
            typedLetters += given_letter;
            for (int i = 0; i < lexi_pinakas.length; i++) {
                if (given_letter.equals(lexi_pinakas[i])) {
                    letters_found[i] = true;
                    letter_found = true;
                    System.out.printf("Το γράμμα %s βρίσκεται στην %d θέση της λέξης.\n", given_letter, i + 1);
                }
            }
            if (letter_found == false) {
                mistakes_done += 1;
                System.out.printf("Ουπς, το γράμμα %s δεν υπάρχει. Λάθη που απομένουν : %s %n", given_letter, maximum_mistakes - mistakes_done);
            }

            boolean allFound = true; // Υποθέτουμε ότι βρέθηκαν όλα και ψάχνουμε για μία διάψευση (false)
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
}
