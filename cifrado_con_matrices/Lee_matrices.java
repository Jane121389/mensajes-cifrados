import java.io.*;
import java.util.Scanner;
import java.lang.Math;
class Lee_matrices
{
    static double matriz[][]   =new double[3][3];
    static double clave[][]    =new double[3][3];
    static double clave_inv[][]=new double[3][3];
    static String mensaje      ="";
    public static void lee_archivo()
    {
        try{
            int             i=0, j=0, l, num=0;
            String          num_s   ="";
            FileInputStream fstream = new FileInputStream("matrices.txt");
            DataInputStream entrada = new DataInputStream(fstream);
            BufferedReader  buffer  = new BufferedReader(new InputStreamReader(entrada));
            String          strLinea;
            while ((strLinea = buffer.readLine()) != null)   {
                i=0;
                j=0;
                for (l=0; l < strLinea.length(); l++)
                {
                    if (strLinea.charAt(l) != ' ')
                        num_s=num_s + strLinea.charAt(l);
                    else
                    {
                        if (num_s.equals("") == false)
                        {
                            num          =Integer.parseInt(num_s);
                            matriz[i][j] = num;
                            j++;
                            if (j >= 3)
                            {
                                i++;
                                j=0;
                            }
                        }
                        num_s="";
                    }
                }
                //imprime_matriz(matriz);
                multiplica(clave_inv, matriz);
            }
            entrada.close();
            System.out.println("El mensaje es:" + mensaje);
        }catch (Exception e) {    //Catch de excepciones
            System.err.println("Ocurrio un error: " + e.getMessage());
        }
    }

    public static void introduce_clave()
    {
        Scanner sc = new Scanner(System.in);
        int     j=0, i=0;
        System.out.println("Escribe los numeros de la matriz clave ordenadamente:");
        for (i=0; i < 3; i++)
            for (j=0; j < 3; j++)
                clave[i][j] = sc.nextDouble();
    }

    public static void introduce_matr_num()
    {
        Scanner sc = new Scanner(System.in);
        int     j=0, i=0;
        System.out.println("Escribe los numeros de la matriz resultado ordenadamente:");
        for (i=0; i < 3; i++)
            for (j=0; j < 3; j++)
                matriz[i][j] = sc.nextDouble();
    }

    public static void multiplica(double [][] clave, double [][] matriz)
    {
        int    i=0, j=0, k=0;
        double resulta      =0;
        double resultado[][]=new double[3][3];
        for (k=0; k < 3; k++)
            for (i=0; i < 3; i++)
            {
                for (j=0; j < 3; j++)
                    resulta=resulta + (clave[k][j] * matriz[j][i]);
                resultado[k][i]=resulta;
                resulta        =0;
            }
        //imprime_matriz(resultado);
        mensaje_t(resultado);
    }

    public static void mensaje_t(double [][] resultado)
    {
        int i=0, j=0;
        int resulta=0;
        for (i=0; i < 3; i++)
            for (j=0; j < 3; j++)
            {
                resulta=(int)Math.round(resultado[i][j]);
                if (resulta >= 15)
                    resulta=resulta - 1;
                System.out.print("   ," + resulta);
                if (resulta == 27)
                    mensaje=mensaje + " ";
                else if (resulta == 28)
                    mensaje=mensaje + ",";
                else if (resulta == 29)
                    mensaje=mensaje + ".";
                else if (resulta == 30)
                    mensaje=mensaje + " ";
                else
                    mensaje=mensaje + (char)(resulta + 96);
            }
    }

    public static void invierte_matriz(double [][] matriz)
    {
        double ma_id[][]=new double[3][6];
        double valor=0, valor2=0;
        int    i=0, j=0;
        for (i=0; i < 3; i++)
            for (j=0; j < 6; j++)
            {
                if (j < 3)
                    ma_id[i][j]=matriz[i][j];
                else
                    ma_id[i][j]=0;
            }
        ma_id[0][3]=1;
        ma_id[1][4]=1;
        ma_id[2][5]=1;
        valor      =ma_id[0][0];
        i          =0;
        for (j=0; j < 6; j++)
            ma_id[i][j]=ma_id[i][j] / valor;
        imprime_matriz2(ma_id);
        i    =1;
        valor=ma_id[i][0];
        for (j=0; j < 6; j++)
            ma_id[i][j]=(ma_id[0][j] * valor * -1) + ma_id[i][j];
        valor=ma_id[1][1];
        for (j=0; j < 6; j++)
            ma_id[i][j]=ma_id[i][j] / valor;
        imprime_matriz2(ma_id);
        i    =2;
        valor=ma_id[i][0];
        for (j=0; j < 6; j++)
            ma_id[i][j]=(ma_id[0][j] * valor * -1) + ma_id[i][j];
        valor=ma_id[i][1];
        for (j=0; j < 6; j++)
            ma_id[i][j]=(ma_id[1][j] * valor * -1) + ma_id[i][j];
        valor=ma_id[2][2];
        for (j=0; j < 6; j++)
            ma_id[i][j]=ma_id[i][j] / valor;
        imprime_matriz2(ma_id);
        i    =1;
        valor=ma_id[1][2];
        for (j=0; j < 6; j++)
            ma_id[i][j]=(ma_id[2][j] * valor * -1) + ma_id[i][j];
        imprime_matriz2(ma_id);
        i    =0;
        valor=ma_id[0][2];
        for (j=0; j < 6; j++)
            ma_id[i][j]=(ma_id[2][j] * valor * -1) + ma_id[i][j];

        valor=ma_id[0][1];
        for (j=0; j < 6; j++)
            ma_id[i][j]=(ma_id[1][j] * valor * -1) + ma_id[i][j];
        imprime_matriz2(ma_id);
        for (i=0; i < 3; i++)
            for (j=3; j < 6; j++)
                clave_inv[i][j - 3]=ma_id[i][j];


        imprime_matriz2(ma_id);
        System.out.println("Matriz inversa:");
        imprime_matriz(clave_inv);
    }

    public static void imprime_matriz(double [][] matriz)
    {
        int j=0, i=0;
        System.out.println("");
        for (i=0; i < 3; i++)
        {
            System.out.println("");
            for (j=0; j < 3; j++)
                System.out.print(matriz[i][j] + " ");
        }
    }

    public static void imprime_matriz2(double [][] matriz)
    {
        int j=0, i=0;
        System.out.println("");
        for (i=0; i < 3; i++)
        {
            System.out.println("");
            for (j=0; j < 6; j++)
                System.out.print(matriz[i][j] + " ");
        }
    }

    public static void main(String [] args)
    {
        introduce_clave();
        invierte_matriz(clave);
        multiplica(clave, clave_inv);
        lee_archivo();
        //System.out.println("Palabra="+palabra);
    }
}
