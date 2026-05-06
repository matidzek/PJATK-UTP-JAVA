package zad2;


import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Finder {
    private final File file;

    public Finder(String str) {
        file = new File(str);
    }

    public int getIfCount() throws Exception {
        int ile = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StreamTokenizer st = new StreamTokenizer(reader);

            st.slashSlashComments(true);
            st.slashStarComments(true);

            int token;
            while ((token = st.nextToken()) != StreamTokenizer.TT_EOF) {
                if (token == StreamTokenizer.TT_WORD) {
                    if ("if".equals(st.sval)) {
                        ile++;
                    }
                }
            }
        }
        return ile;
    }

    public int getStringCount(String regex) throws FileNotFoundException {
        int ile = 0;
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            sc.nextLine();
            while ((sc.findInLine(regex) != null)) ile += 1;
        }
        System.out.println(ile);
        sc.close();

        return ile;
    }

}
