import java.awt.*;
import java.math.BigInteger;
import java.net.*;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) throws UnknownHostException {


        String LocalHostIpString = null;

        if (args.length == 0) {
            LocalHostIpString=ipToString();
        } else
            LocalHostIpString = args[0];


        CharArraysIp IP=ipToCharArray();
        validIP(LocalHostIpString);////////////////////czy dobry adres

        netClass(IP);////////////klasa

        System.out.println("Subnet Mask Binary: " + ipMaskBinary(LocalHostIpString));//maska binarnie

        System.out.println("Subnet Mask Decimal: " + ipMaskDecimal(LocalHostIpString));//maska dziesiętnie

        adress(LocalHostIpString, ipMaskDecimal(LocalHostIpString));

        //System.out.println("Broadcast adress Binary: " + broadcastAdressBinary(ipMaskBinary(LocalHostIpString), IP));//adres rozgloszeniowy binarnie

    }

    public static boolean validIP(String a) {
        try {
            String[] parts = a.split("/");
            parts = parts[0].split("\\.");

            //czy puste
            if (a == null) {
                System.out.println("Invalid IP adress");
                System.exit(0);
                return false;
            }

            //czy ma 4 czesci
            if (parts.length != 4) {
                System.out.println("Invalid IP adress");
                System.exit(0);
                return false;
            }

            //czy liczby z dobrego zakresu 0,255
            for (String part : parts) {
                int i = Integer.parseInt(part);
                if (i > 255 || i < 0) {
                    System.out.println("Invalid IP adress");
                    System.exit(0);
                    return false;
                }
            }
            //czy sie konczy "."
            if (a.endsWith(".")) {
                System.out.println("Invalid IP adress");
                System.exit(0);
                return false;
            }
            //czy nie ma liter
            for(int i=0; i<parts.length;i++) {
                for(int j=0; j<parts[i].length(); j++)
                    if(parts[i].charAt(j)<'0' && parts[i].charAt(j)>'9'){
                        System.out.println("Invalid IP adress");
                        System.exit(0);
                        return false;
                    }
            }

            System.out.println("Valid IP adress");
            return true;


        } catch (NumberFormatException e) {
            System.out.println("Invalid IP adress");
            System.exit(0);
            return false;
        }

    }
    public static void netClass(CharArraysIp a){
        int position = 0;
        for(int i=0; i<5; i++)
            if (a.abc[i] == '0') {
                position = i;
                break;
            }
        if(position==0)
            System.out.println("Class A");
        if(position==1)
            System.out.println("Class B");
        if(position==2)
            System.out.println("Class C");
        if(position==3)
            System.out.println("Class D");
        if(position==4)
            System.out.println("Class E");
    }
    public static void mask(){

    }
    static String ipToString() {
        try {
            String localHostString;
            InetAddress localHost = InetAddress.getLocalHost();
            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localHost);
            String[] parts = localHost.toString().split("/");
            localHostString = parts[1];
            localHostString = localHostString + "/" + networkInterface.getInterfaceAddresses().get(0).getNetworkPrefixLength();
            System.out.println(localHostString);
            return localHostString;

        } catch (SocketException | UnknownHostException e) {
            System.out.println("Error");
            e.printStackTrace();
            return null;
        }
    }
    static  CharArraysIp ipToCharArray(){
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            String ipAdress;
            String[] parts = localHost.toString().split("/");
            InetAddress ip = InetAddress.getByName(parts[0]);
            byte[] bytes = ip.getAddress();

            int[] bits=new int[4];

            for (int i = 0; i < bytes.length; i++) {
                byte b = bytes[i];
                bits[i]=(b & 0xFF);
                //System.out.println(bits[i]);
            }

            String ipBinaryString[]=new String[4];

            for(int i=0; i<ipBinaryString.length; i++){
                ipBinaryString[i]=Integer.toBinaryString(bits[i]);
                //System.out.println(ipBinaryString[i]);
            }


            char ipBinaryChar1[]=ipBinaryString[0].toCharArray();
            char ipBinaryChar2[]=ipBinaryString[1].toCharArray();
            char ipBinaryChar3[]=ipBinaryString[2].toCharArray();
            char ipBinaryChar4[]=ipBinaryString[3].toCharArray();


            CharArraysIp a=new CharArraysIp();
            a.abc=ipBinaryChar1;
            a.abc1=ipBinaryChar2;
            a.abc2=ipBinaryChar3;
            a.abc3=ipBinaryChar4;

            return a;




        }   catch(UnknownHostException e){
            e.printStackTrace();
            return null;
        }





    }
    static String ipMaskDecimal(String LocalHostIpString) throws UnknownHostException {

        String parts[]=LocalHostIpString.split("/");
        int prefix=Integer.parseInt(parts[1]);
        int mask = 0xffffffff << (32 - prefix);
        int value=mask;
        byte[] bytes = new byte[]{
                (byte)(value >>> 24), (byte)(value >> 16 & 0xff), (byte)(value >> 8 & 0xff), (byte)(value & 0xff) };

        InetAddress netAddr = InetAddress.getByAddress(bytes);
        String netAddrString="";
        netAddrString=netAddr.toString();
        String parts2[]=netAddrString.split("/");

        return parts2[1];
    }

    static String ipMaskBinary(String LocalHostIpString) throws UnknownHostException {
        String parts[]=LocalHostIpString.split("/");
        int prefix=Integer.parseInt(parts[1]);
        int mask = 0xffffffff << (32 - prefix);
        int value=mask;
        byte[] bytes = new byte[]{
                (byte)(value >>> 24), (byte)(value >> 16 & 0xff), (byte)(value >> 8 & 0xff), (byte)(value & 0xff) };

        InetAddress netAddr = InetAddress.getByAddress(bytes);
        String netAddrString="";
        netAddrString=netAddr.toString();
        String parts2[]=netAddrString.split("/");


        String data_out;
        byte[] bytes2 = InetAddress.getByName(parts2[1]).getAddress();
        data_out = new BigInteger(1, bytes2).toString(2);
        return  data_out;
    }
    public static String adress(String localHostIpString, String ipMaskDeci) throws UnknownHostException {
        return null;
    }







}