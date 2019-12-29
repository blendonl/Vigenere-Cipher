public class Main {

    public static void main(String[] args) {
        char[] c = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        VigenereCipher v = new VigenereCipher();

        char[][] table = v.table(c, "ENCRYPT");

        for(int i = 0; i < table.length; i++) {
            for(int j = 0; j < table.length; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }

        String enc = v.encrypt(table, "APOVJENNESHITORE", "SPECA");

        System.out.println(enc);
        System.out.println(v.decrypt(table, enc, "SPECA"));




    }
}
