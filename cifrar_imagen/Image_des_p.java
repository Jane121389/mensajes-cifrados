import java.io.*;
import java.awt.Color;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.Scanner;

public class Image_des_p
{
    private static BufferedImage imageOriginal;
    public static void abrirImagen(String imag)
    {
        try {
            imageOriginal = ImageIO.read(new File(imag + ".png"));
        }
        catch (IOException e) {
            System.err.println("leer:" + e);
        }
    }

    public static void cifra(int clave)
    {
        //Variables que almacenar�n los p�xeles
        int           mediaPixel, colorRGB;
        Color         colorAux;
        int           r, g, b;
        BufferedImage img = new BufferedImage(imageOriginal.getWidth(), imageOriginal.getHeight(), BufferedImage.TYPE_INT_RGB);
        //Recorremos la imagen p�xel a p�xel
        int limite_x=imageOriginal.getWidth();
        int limite_y=imageOriginal.getHeight();
        int limite_x_a=0;
        int limite_y_a=0;
        int coord[][]=new int[limite_x][limite_y];
        int i_n, j_n, clave2=0;
        for (int i=0; i < limite_x; i++)
            for (int j=0; j < limite_y; j++)
                coord[i][j]=0;
        for (int i = 0; i < limite_x; i++) {
            i_n=i - clave;
            while (i_n >= limite_x) {
                i_n=i_n - limite_x;
            }
            while (i_n < 0) {
                i_n=i_n + limite_x;
            }
            for (int j = 0; j < limite_y; j++) {
                //Almacenamos el color del p�xel
                colorAux=new Color(imageOriginal.getRGB(i, j));
                r       =colorAux.getRed();
                g       =colorAux.getGreen();
                b       =colorAux.getBlue();
                clave2  =clave2 + clave;
                r       =r - clave2;
                while (r > 255) {
                    r=r - 256;
                }
                while (r < 0) {
                    r=r + 256;
                }
                g=g + (clave2 * 2);
                while (g > 255) {
                    g=g - 256;
                }
                while (g < 0) {
                    g=g + 256;
                }
                b=b - clave2;
                while (b > 255) {
                    b=b - 256;
                }
                while (b < 0) {
                    b=b + 256;
                }
                colorRGB=(r << 16) | (g << 8) | b;
                j_n     =j + clave;
                while (j_n >= limite_y) {
                    j_n=j_n - limite_y;
                }
                while (j_n < 0) {
                    j_n=j_n + limite_y;
                }
                //System.out.print(" "+i_n+","+j_n);
                img.setRGB(i_n, j_n, colorRGB);
            }
        }

        File f = new File("imagen12_cifra" + clave + ".png");
        try {
            ImageIO.write(img, "PNG", f);
        }
        catch (IOException e) {
            System.err.println("image:" + e);
        }
    }

    public static void decifra(int clave)
    {
        //Variables que almacenar�n los p�xeles
        int           mediaPixel, colorRGB;
        Color         colorAux;
        int           r, g, b;
        BufferedImage img = new BufferedImage(imageOriginal.getWidth(), imageOriginal.getHeight(), BufferedImage.TYPE_INT_RGB);
        //Recorremos la imagen p�xel a p�xel
        int limite_x=imageOriginal.getWidth();
        int limite_y=imageOriginal.getHeight();
        int limite_x_a=0;
        int limite_y_a=0;
        int coord[][]=new int[limite_x][limite_y];
        int i_n, j_n, clave2=0, ret=0, ret2=0;
        System.out.print(" limites." + limite_x + ", " + limite_y);
        for (int i=0; i < limite_x; i++)
            for (int j=0; j < limite_y; j++)
                coord[i][j]=0;
        for (int i = 0; i < limite_x; i++) {
            i_n=i + clave;
            while (i_n >= limite_x) {
                i_n=i_n - limite_x;
            }
            while (i_n < 0) {
                i_n=i_n + limite_x;
            }
            for (int j = 0; j < limite_y; j++) {
                colorAux=new Color(imageOriginal.getRGB(i, j));
                r       =colorAux.getRed();
                g       =colorAux.getGreen();
                b       =colorAux.getBlue();
                j_n     =j - clave;
                while (j_n >= limite_y) {
                    j_n=j_n - limite_y;
                }
                while (j_n < 0) {
                    j_n=j_n + limite_y;
                }
                clave2=((i_n * limite_y) + j_n + 1) * clave;
                //clave2=clave;
                r=r + clave2;
                while (r > 255) {
                    r=r - 256;
                }
                while (r < 0) {
                    r=r + 256;
                }
                g=g - (clave2 * 2);
                while (g > 255) {
                    g=g - 256;
                }
                while (g < 0) {
                    g=g + 256;
                }
                b=b + clave2;
                while (b > 255) {
                    b=b - 256;
                }
                while (b < 0) {
                    b=b + 256;
                }
                colorRGB=(r << 16) | (g << 8) | b;
                //System.out.print(" "+i_n+","+j_n);
                img.setRGB(i_n, j_n, colorRGB);
            }
        }

        File f = new File("imagen12_decifra" + clave + ".png");
        try {
            ImageIO.write(img, "PNG", f);
        }
        catch (IOException e) {
            System.err.println("image:" + e);
        }
    }

    public static void main(String [] args)
    {
        Scanner sc = new Scanner(System.in);
        String  s  ="";
        int     num=0;
        System.out.println("Escriba la clave para cifrar:");
        s = sc.nextLine();
        for (int i=0; i < s.length(); i++)
            num=num + s.charAt(i);
        abrirImagen("imagen1");
        cifra(num);
        abrirImagen("imagen12_cifra" + num);
        num=0;
        System.out.println("\nEscriba la clave para descifrar:");
        s = sc.nextLine();
        for (int i=0; i < s.length(); i++)
            num=num + s.charAt(i);
        decifra(num);
    }
}
