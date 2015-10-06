/**
 * Class: TODO
 * @author Mike Deiters
 * @version 1.0
 * September 22, 2015
 * ITEC 3150-01
 *
 * Description: TODO
 *
 * Purpose: TODO
 */
public class Test1TakeHome {

    public static void main( String[] args ) {

        Test1TakeHome tt = new Test1TakeHome();
        System.out.println(tt.hashCode("Mike", 20));
//        System.out.println(tt.hashCode("Mike", 14));
//        System.out.println(tt.hashCode("Tim", 20));
//        System.out.println(tt.hashCode("Nike", 34));
        System.out.println(tt.hashCode("Ekim", 20));

    }

    public int hashCode( String name, int age ) {

        int hashCode = 0;
        name.toLowerCase();

        for ( int i = 0; i < name.length(); i++ ) {

            switch ( name.charAt(i) ) {

                case 'a':
                    hashCode += 1;
                    break;
                case 'b':
                    hashCode += 2;
                    break;
                case 'c':
                    hashCode += 3;
                    break;
                case 'd':
                    hashCode += 4;
                    break;
                case 'e':
                    hashCode += 5;
                    break;
                case 'f':
                    hashCode += 6;
                    break;
                case 'g':
                    hashCode += 7;
                    break;
                case 'h':
                    hashCode += 8;
                    break;
                case 'i':
                    hashCode += 9;
                    break;
                case 'j':
                    hashCode += 10;
                    break;
                case 'k':
                    hashCode += 11;
                    break;
                case 'l':
                    hashCode += 12;
                    break;
                case 'm':
                    hashCode += 13;
                    break;
                case 'n':
                    hashCode += 14;
                    break;
                case 'o':
                    hashCode += 15;
                    break;
                case 'p':
                    hashCode += 16;
                    break;
                case 'q':
                    hashCode += 17;
                    break;
                case 'r':
                    hashCode += 18;
                    break;
                case 's':
                    hashCode += 19;
                    break;
                case 't':
                    hashCode += 20;
                    break;
                case 'u':
                    hashCode += 21;
                    break;
                case 'v':
                    hashCode += 22;
                    break;
                case 'w':
                    hashCode += 23;
                    break;
                case 'x':
                    hashCode += 24;
                    break;
                case 'y':
                    hashCode += 25;
                    break;
                case 'z':
                    hashCode += 26;
                    break;
                default:
                    hashCode += 0;
            }
        }
        hashCode += age;
        return hashCode;
    }
}
