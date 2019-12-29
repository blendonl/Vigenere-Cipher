public class VigenereCipher {

    public char[][] table(char[] alphabet, String keyWord) {
        char rez[][] = new char[alphabet.length + 1][alphabet.length + 1];

        addFirstRC(rez, alphabet);
        addKeyWordToTable(rez, keyWord.toUpperCase());
        addAlphabetToTable(rez, alphabet);

        return rez;
    }

    public void addFirstRC(char[][] table, char[] alphabet) {
        for(int i = 1; i < table.length; i++) {
            table[i][0] = alphabet[i - 1];
        }

        for(int j = 1; j < table.length; j++) {
            table[0][j] = alphabet[j - 1];

        }
    }

    private void addKeyWordToTable(char[][] table, String keyWord) {
        for (int i = 1; i < table.length; i++) {
            addKeyWordToRow(table, i, keyWord);
        }
    }

    private void addKeyWordToRow(char[][] table, int row, String keyWord) {
        for (int i = table.length - row, count = keyWord.length() -1; i > 0 && count >= 0; i--) {
            table[row][i] = keyWord.charAt(count);
            count--;
        }
    }

    private void addAlphabetToTable(char[][] table, char[] alphabet) {
        char[] temp = alphabet;

        for (int i = 1; i < table.length; i++) {
            if(i > 1) {
                temp = removeFirstElement(temp);
                if (temp.length > 0) {
                    if (table[i - 1][1] == temp[0]) {
                        temp = removeFirstElement(temp);
                    }
                }
                addAlphabetToRow(table, i, temp);
                addAlphabetToRow(table, i, alphabet);
            } else {
                addAlphabetToRow(table, i, temp);
            }
        }
    }

    private void addAlphabetToRow(char[][] table, int row, char[] alphabet) {
        for (int i = 1, count = 0; i < table.length && count < alphabet.length; i++) {
            if(table[row][i] == 0) {
                if (!containsChar(alphabet[count], table, row)) {
                    table[row][i] = alphabet[count];
                    count++;
                } else {
                    count++;
                    while (count < alphabet.length) {
                        if (!containsChar(alphabet[count], table, row)) {
                            table[row][i] = alphabet[count];
                            count++;
                            break;
                        }
                        count++;
                    }
                }
            }
        }
    }

    private boolean containsChar(char c, char[][] table, int row) {
        boolean rez = false;
        for (int i = 1; i < table.length; i++) {
            if(table[row][i] == c) {
                rez = true;
                break;
            }
        }
        return rez;
    }

    private char[] removeFirstElement(char[] c) {
        char[] rez = new char[c.length];
        if(c.length > 0) {
            rez = new char[c.length - 1];

            for (int i = 1; i < c.length; i++) {
                rez[i - 1] = c[i];

            }
        }
        return rez;
    }

    public String encrypt(char[][] table, String word, String key) {
        String rez = "";
        String keyS = keyStream(word, key);
        for(int i = 0; i < keyS.length(); i++) {
            rez = rez + table[findRow(table, keyS.charAt(i))][findCol(table, word.charAt(i))];
        }
        return rez;
    }

    public String decrypt(char[][] table, String word, String key) {
        String rez = "";
        String keyStream = keyStream(word.toUpperCase(), key);
        for(int i = 0; i < keyStream.length(); i++) {
            rez = rez + table[0][findinRow(table, findCol(table, keyStream.charAt(i)) , word.toUpperCase().charAt(i))];
        }
        return rez;
    }

    private int findinRow(char[][] table, int col, char c) {
        int rez = -1;

        for(int i = 0; i < table.length; i++) {
            if(table[col][i] == c) {
                rez = i;
            }
        }
        return rez;
    }

    private int findCol(char[][] table, char c) {
        int rez = -1;

        for(int i = 0; i < table.length; i++) {
            if(table[0][i] == c) {
                rez = i;
            }
        }
        return rez;
    }

    private int findRow(char[][] table, char c) {
        int rez = -1;

        for(int i = 0; i < table.length; i++) {
            if(table[i][0] == c) {
                rez = i;
            }
        }
        return rez;
    }

    public String keyStream(String word, String key) {
        String rez = "";
        int j = 0;
        int i = 0;
        while(i < word.toUpperCase().length()) {
            if(j < key.toUpperCase().length()) {
                rez = rez + key.charAt(j);
                j++;
                i++;
            } else {
                j = 0;

            }
        }
        return rez;
    }
}
