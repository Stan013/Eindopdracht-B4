
/**
 * Write a description of class CijferWeergave here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GebruikGB_CijferWeergave
{

    public GebruikGB_CijferWeergave()
    {
    }

    /**
     *  Plaatst een cijfer 
     *  in een bepaald display
     */
    public static void O_1_plaatsenVanEenCijfer(int cijfer, int bit)
    {
        IO.writeShort(bit, cijfer);
    }

    /**
     *  Maakt de 7 segment-displays
     *  schoon
     */
    public static void O_2a_maakSchoon()
    {
        IO.writeShort(0x10, 0x100);
        IO.writeShort(0x12, 0x100);
        IO.writeShort(0x14, 0x100);
        IO.writeShort(0x16, 0x100);
        IO.writeShort(0x18, 0x100);
        IO.writeShort(0x20, 0x100);
        IO.writeShort(0x22, 0x100);
        IO.writeShort(0x24, 0x100);
        IO.writeShort(0x30, 0x100);
        IO.writeShort(0x32, 0x100);
        IO.writeShort(0x34, 0x100);
    }

    /**
     *  Maakt alles efficiënter
     *  schoon  
     */
    public static void O_2b_maakSchoonBeter()
    {
        int bit = 0x10;
        while (bit <= 0x34)
        {
            IO.writeShort(bit, 0x100);
            bit = bit + 0x2;
        }
        IO.writeShort(0x40, 0xFE);
        IO.writeShort(0x40, 0x01);
    }

    /**
     *  Waardes 0 t/m 9 worden geplaatst
     */
    public static void O_3_schrijfCijfersInDisplay()
    {
        int getal = 0;
        while(getal < 10)
        {
            IO.writeShort(0x10,getal);
            getal++;
            IO.delay(500);
        }
        IO.delay(500);
        IO.writeShort(0x10,0x100);
    }

    /**
     *  Waardes 0 t/m 9 worden
     *  3 maal geplaatst
     */
    public static void O_4a_schrijfCijfersInDisplay3Keer()
    {
        int herhalingen = 0;
        while(herhalingen < 3)
        {
            int getal = 0;
            while(getal < 10)
            {
                IO.writeShort(0x10, getal);
                getal++;
                IO.delay(500);
            }
            IO.delay(250);
            herhalingen++;
        }
        IO.writeShort(0x10, 0x100);
    }

    /**
     *  Hetzelfde als 4a
     *  met andere lus
     */
    public static void O_4b_schrijfCijfersInDisplay3KeerAnders()
    {
        int herhalingen = 0;
        do{
            int getal = 0;
            while(getal < 10)
            {
                IO.writeShort(0x10, getal);
                getal++;
                IO.delay(500);
            }
            IO.delay(250);
            herhalingen++;
        }     

        while(herhalingen < 3);

        IO.writeShort(0x10, 0x100);
    }
}
