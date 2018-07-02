import java.io.*;
import java.util.Scanner;
class Hola
{
    public static void main(String [] args)
    {
        Scanner sc = new Scanner(System.in);
        String  s, s2="";
        int     longitud=0, i=0, nu=0, nu2=0;
        System.out.print("\n\nTexto: ");
        s       = sc.nextLine();
        longitud=s.length();
        System.out.println("\n" + (char)84 + (char)69 + " " + (char)65 + (char)77 + (char)79 + (char)3 + (char)3 + (char)3 + (char)3 + " U.U nonono XD");
        for (i=0; i < longitud; i++)
        {
            nu =s.charAt(i);
            nu2=nu + 13;
            if (nu2 > 122)
                nu2=nu - 13;
            s2=s2 + (char)(nu2);
        }
        System.out.println("\n\nTexto des/cifrado: " + s2 + "\n\n");
    }
}
