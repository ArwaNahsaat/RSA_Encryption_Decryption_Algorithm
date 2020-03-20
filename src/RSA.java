import java.math.BigInteger;
import java.util.Random;

public class RSA {
    public static BigInteger setLS_MSBits(BigInteger num){
        num.setBit(1023);
        num.setBit(0);
        return num;
    }


    public static boolean fermatLittleTheorem(BigInteger num, BigInteger a){

        if(a.modPow(num.subtract(BigInteger.valueOf(1)),num) != BigInteger.valueOf(1))
            return false;

        return true;
    }

    public static BigInteger divisibleByP(BigInteger num){
        BigInteger a  = new BigInteger(1024, new Random()).mod(num);

        while(num.mod(a).compareTo(BigInteger.valueOf(0)) == 0)
            a  = new BigInteger(1024, new Random()).mod(num);

        return a;
    }
    public static boolean fermatPrimalityTest(BigInteger num){
        BigInteger a;
        for(int i=0; i<128; i++) {
            a = divisibleByP(num);

            BigInteger gcd = num.gcd(a);
            if (gcd != BigInteger.valueOf(1))
                return false;

            if(!fermatLittleTheorem(num,a))
                return false;
        }
        return true;
    }

    public static boolean isPrime(BigInteger num){
        Random randomBits = new Random();

        num = new BigInteger(1024, randomBits);
        num = setLS_MSBits(num);

        return fermatPrimalityTest(num);

    }

    public static  BigInteger generatePrime(){
        return new BigInteger(1024, new Random());
    }

    public static void generatePublicPrivateKeys(){

        BigInteger p =  generatePrime();
        BigInteger q = generatePrime();

        if(!isPrime(p))
            p = generatePrime();

        while(p==q && !isPrime(q))
            q = generatePrime();

    }
    public static void main(String[]args){
        generatePublicPrivateKeys();
    }
}
