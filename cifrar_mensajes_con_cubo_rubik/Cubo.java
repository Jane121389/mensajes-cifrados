import java.io.*;
import java.util.Scanner;
class Cubo
{
    static char miaux[][] =new char[3][3];
    static char mapa[][][]=new char[6][3][3];
    static char m[][][]   ={{{'p', '2', 'q'}, {'8', 'A', ':'}, {'r', '!', 's'}}, {{'e', 'y', 'f'}, {'5', 'D', '.'}, {'g', '$', 'h'}}, {{'t', '3', 'u'}, {'9', 'B', '('}, {'v', '"', 'w'}}, {{'m', '1', 'n'}, {'7', 'l', ' '}, {'ñ', ';', 'o'}}, {{'a', 'x', 'b'}, {'4', 'F', '0'}, {'c', ')', 'd'}}, {{'i', 'z', 'j'}, {'6', 'T', ','}, {'k', '?', 'l'}}};
    public static void frente()
    {
        int i, j;
        for (i=0; i < 3; i++)
            miaux[2][2 - i]=m[0][2][2 - i];
        for (i=0; i < 3; i++)
        {
            m[0][2][2 - i]=m[3][i][2];
            m[3][i][2]    =m[2][0][i];
            m[2][0][i]    =m[1][2 - i][0];
            m[1][2 - i][0]=miaux[2][2 - i];
        }
    }

    public static void derecha2()
    {
        int i, j;
        for (i=0; i < 3; i++)
            miaux[i][2]=m[0][i][2];
        for (i=0; i < 3; i++)
        {
            m[0][i][2]    =m[4][i][2];
            m[4][i][2]    =m[2][i][2];
            m[2][i][2]    =m[5][2 - i][0];
            m[5][2 - i][0]=miaux[i][2];
        }
    }

    public static void izquierda2()
    {
        int i, j;
        for (i=0; i < 3; i++)
            miaux[i][0]=m[0][i][0];
        for (i=0; i < 3; i++)
        {
            m[0][i][0]    =m[5][2 - i][2];
            m[5][2 - i][2]=m[2][i][0];
            m[2][i][0]    =m[4][i][0];
            m[4][i][0]    =miaux[i][0];
        }
    }

    public static void abajo2()
    {
        int i, j;
        for (i=0; i < 3; i++)
            miaux[2][i]=m[4][2][i];
        for (i=0; i < 3; i++)
        {
            m[4][2][i]=m[3][2][i];
            m[3][2][i]=m[5][2][i];
            m[5][2][i]=m[1][2][i];
            m[1][2][i]=miaux[2][i];
        }
    }

    public static void atras2()
    {
        int i, j;
        for (i=0; i < 3; i++)
            miaux[i][2]=m[1][i][2];
        for (i=0; i < 3; i++)
        {
            m[1][i][2]    =m[2][2][2 - i];
            m[2][2][2 - i]=m[3][2 - i][0];
            m[3][2 - i][0]=m[0][0][i];
            m[0][0][i]    =miaux[i][2];
        }
    }

    public static void arriba2()
    {
        int i, j;
        for (i=0; i < 3; i++)
            miaux[0][i]=m[5][0][i];
        for (i=0; i < 3; i++)
        {
            m[5][0][i]=m[3][0][i];
            m[3][0][i]=m[4][0][i];
            m[4][0][i]=m[1][0][i];
            m[1][0][i]=miaux[0][i];
        }
    }

    public static void giro(int cara)
    {
        int i, j;
        for (i=0; i < 3; i++)
        {
            miaux[i][2]    =m[cara][0][i];
            miaux[2][2 - i]=m[cara][i][2];
            miaux[i][0]    =m[cara][2][i];
            miaux[0][2 - i]=m[cara][i][0];
            miaux[1][1]    =m[cara][1][1];
        }
        for (j=0; j < 3; j++)
            for (i=0; i < 3; i++)
                m[cara][i][j]=miaux[i][j];
        if (cara == 0)
            arriba2();
        else if (cara == 1)
            derecha2();
        else if (cara == 2)
            abajo2();
        else if (cara == 3)
            izquierda2();
        else if (cara == 4)
            frente();
        else if (cara == 5)
            atras2();
    }

    public static void main(String args[])
    {
        int     c1, c2, longitud=0;
        String  mensaje="", mensaje2="";
        int     ai=0, aj=0, ak=0, ale=0, l, a2i=0;
        char    letra;
        String  clav[]={
            "arriba", "derecha", "abajo", "izquierda", "frente", "atras"
        };
        char    le1[]={
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '.', ',', ' ', ':', '(', ')', '$', '?', ';', '!', '"'
        };
        char    le2[]={
            'b', 'a', 'd', 'c', 'f', 'e', 'h', 'g', 'j', 'i', 'l', 'k', 'n', 'm', 'o', 'ñ', 'q', 'p', 's', 'r', 'u', 't', 'w', 'v', 'y', 'x', '1', 'z', '3', '2', '5', '4', '7', '6', '9', '8', '.', '0', ' ', ',', '(', ':', '$', ')', ';', '?', '"', '!'
        };
        int     i=0, j=0, k=0;
        Scanner sc = new Scanner(System.in);
        System.out.println("\n Escriba el mensaje:");
        mensaje=sc.nextLine();
        //F-Frente(5);D-Derecha(2);T-Atrás(6);I-Izquierda(4);A-Arriba(1);B-Abajo(3)
        for (k=0; k < 6; k++)
            for (i=0; i < 3; i++)
                for (j=0; j < 3; j++)
                    mapa[k][i][j]=m[k][i][j];
        for (k=0; k < 6; k++)
        {
            System.out.println("\nCara de " + clav[k]);
            for (i=0; i < 3; i++)
            {
                System.out.println("");
                for (j=0; j < 3; j++)
                    System.out.print(" " + mapa[k][i][j]);
            }
        }
        System.out.println("\n\nEscriba movimientos de clave (numeros separados de un enter): 0.-arriba,1.-derecha,2.-abajo,3.-izquierda,4.-frente,5.-atras");
        c1      =sc.nextInt();
        c2      =sc.nextInt();
        longitud=mensaje.length();
        for (l=0; l < longitud; l++)
        {
            letra=mensaje.charAt(l);
            giro(c1);
            for (k=0; k < 6; k++)
            {
                System.out.println("\n\nCara de " + clav[k]);
                for (i=0; i < 3; i++)
                {
                    System.out.println("");
                    for (j=0; j < 3; j++)
                        System.out.print(" " + m[k][i][j]);
                }
            }
            giro(c2);
            for (k=0; k < 6; k++)
            {
                System.out.println("\n\nCara de " + clav[k]);
                for (i=0; i < 3; i++)
                {
                    System.out.println("");
                    for (j=0; j < 3; j++)
                        System.out.print(" " + m[k][i][j]);
                }
            }

            //System.out.println("\nletra="+letra);
            for (k=0; k < 6; k++)
            {
                for (i=0; i < 3; i++)
                    for (j=0; j < 3; j++)
                        if (mapa[k][i][j] == letra)
                        {
                            ak=k;
                            ai=i;
                            aj=j;
                            k =6;
                            i =3;
                            j =3;
                        }
            }
            //System.out.println("\nletra2="+m[ak][ai][aj]);
            for (i=0; i < 45; i++)
                if (le1[i] == m[ak][ai][aj])
                    a2i=i;

            //System.out.println("\na2i="+le2[a2i]);
            for (k=0; k < 6; k++)
            {
                for (i=0; i < 3; i++)
                    for (j=0; j < 3; j++)
                        if (m[k][i][j] == le2[a2i])
                        {
                            ak=k;
                            ai=i;
                            aj=j;
                            k =6;
                            i =3;
                            j =3;
                        }
            }

            //System.out.println("\nmapa[ak][ai][aj]="+mapa[ak][ai][aj]);
            mensaje2=mensaje2 + mapa[ak][ai][aj];
        }
        System.out.println("El mensaje des/cifrado es=" + mensaje2);
    }
}
