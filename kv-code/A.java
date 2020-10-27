// Kaustav Vats
import java.io.*;
import java.util.*;
import java.math.*;
import java.lang.*;


class A {

    public void solve(int a, int b) {
        int count = (a-b)/10;
        if ((a-b)%10 != 0) {
            count += 1;
        }
        System.out.println(count);
    }

    public static void main(String[] args) throws IOException{
        Reader.init(System.in);
        A cl = new A();
        int T = Reader.nextInt();
        while(T-- > 0) {
            int a = Reader.nextInt();
            int b = Reader.nextInt();
            if (a > b) {
                cl.solve(a, b);
            }
            else {
                cl.solve(b, a);
            }
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