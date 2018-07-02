/* Programa para hacer o leer patrones de imagenes con bits donde a=1,b=1,c=3,d=3,e=5,f=6,g=7,h=8,i=9
   j=10,k=11,l=12,m=13,n=14,o=15,p=16,q=17,r=18,s=19,t=20,u=21,v=22,w=23,x=24,y=25,z=26,' '=27, donde
   cada pixel sera un bit y cada letra constara de 5 bits, donde podremos tener un mapa de 200x200 asi en cada
   renglon tendremos 40 letras */
import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Scanner;
class Bits_ima
{
    public static void insertar_imagen(String imag)
    {
        int           pixeles=80;
        BufferedImage img = new BufferedImage(pixeles, pixeles, BufferedImage.TYPE_INT_RGB);
        int           x=0, y=0, l=0;
        int           col1=(255 << 16) | (255 << 8) | 255;
        int           col2=0;
        int           num=12, i=1;
        int           ns=0, s=0;
        String        mensaje="";
        Scanner       sc     =new Scanner(System.in);
        byte          cont   =0;
        for (x=0; x < pixeles; x++)
            for (y=0; y < pixeles; y++)
                img.setRGB(x, y, col1);
        System.out.println("Escriba el mensaje:");
        mensaje=sc.nextLine();
        y      =0;
        for (i=0; i < mensaje.length(); i++)
        {
            if (mensaje.charAt(i) == ' ')
                num=27;
            else
                num=mensaje.charAt(i) - 96;
            x=5 * (s + 1);
            if (x > pixeles)
            {
                s=0;
                x=5 * (s + 1);
                y++;
            }
            while (num > 0)
            {
                ns=num % 2;
                if (ns == 0)
                    img.setRGB(x - 1, y, col1);
                else
                    img.setRGB(x - 1, y, col2);
                num=num / 2;
                x--;

                //System.out.print("col:"+col1+"col2:"+col2);
                cont++;
            }
            s++;
        }
        File f = new File(imag + ".png");
        try {
            ImageIO.write(img, "PNG", f);
        }catch (IOException e) {
            System.out.println("image not saved.");
        }
    }

    public static void lee_imagen(String imag)
    {
        int           pixeles=80;
        BufferedImage imageOriginal;
        int           x=0, y=0, l=0;
        int           col1=0;
        int           num=0, i=4, r, g, b;
        int           ns     =0;
        String        mensaje="";
        Color         colorAux;
        byte          cont=0;
        try {
            imageOriginal = ImageIO.read(new File(imag + ".png"));
            for (y=0; y < pixeles; y++)
                for (x=0; x < pixeles; x++)
                {
                    colorAux=new Color(imageOriginal.getRGB(x, y));
                    r       =colorAux.getRed();
                    g       =colorAux.getGreen();
                    b       =colorAux.getBlue();
                    col1    =(r << 16) | (g << 8) | b;
                    if (col1 == 0)
                        num=num + (int)Math.pow(2, i);
                        // System.out.print("num:"+num+"i:"+i);
                    if (i == 0)
                    {
                        if (num != 0)
                            if (num == 27)
                                mensaje=mensaje + ' ';
                            else
                                mensaje=mensaje + (char)(96 + num);
                        i  =5;
                        num=0;
                    }
                    i--;
                }
            System.out.println("msj:" + mensaje);
        }catch (IOException e) {
            System.err.println("image not saved.");
        }
    }

    public static void main(String args[])
    {
        int     opc    =0;
        String  nom_img="";
        Scanner sc     =new Scanner(System.in);
        do {
            System.out.println("1.-Hacer imagen,2.-Leer imagen,3.-salir");
            opc=sc.nextInt();
            if (opc == 1)
            {
                System.out.println("Escriba el nombre de la imagen a guardar:");
                nom_img=sc.next();
                insertar_imagen(nom_img);
            }
            else if (opc == 2)
            {
                System.out.println("Escriba el nombre de la imagen a leer:");
                nom_img=sc.next();
                lee_imagen(nom_img);
            }
        } while (opc != 3);
    }
}
