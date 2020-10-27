// Kaustav Vats
import java.io.*;
import java.util.*;
import java.math.*;
import java.lang.*;


class B {

    public static void main(String[] args) throws IOException{
        Reader.init(System.in);
        B cl = new B();
        int T = Reader.nextInt();
        while(T-- > 0) {
            int a = Reader.nextInt();
            int b = Reader.nextInt();
            int x = Reader.nextInt();
            int y = Reader.nextInt();
        }
    }
}


/** Class for buffered reading int and double values */
class Reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    /** call this method to initialize reader for InputStream */
    static void init(InputStream input) {
        reader = new BufferedReader(
                     new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }

    /** get next word */
    static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(
                   reader.readLine() );
        }
        return tokenizer.nextToken();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt( next() );
    }

    static long nextLong() throws IOException {
        return Long.parseLong( next() );
    }
	
    static double nextDouble() throws IOException {
        return Double.parseDouble( next() );
    }
}