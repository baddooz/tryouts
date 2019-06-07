package probs.solutions;

public class ZigZag {



        public static void main(String[] args) {
            int[] elements =
                    //{ 1, 17, 5, 10, 13, 15, 10, 5, 16, 8 };
//                    { 374, 40, 854, 203, 203, 156, 362, 279, 812, 955,
//                    600, 947, 978, 46, 100, 953, 670, 862, 568, 188,
//                    67, 669, 810, 704, 52, 861, 49, 640, 370, 908,
//                    477, 245, 413, 109, 659, 401, 483, 308, 609, 120,
//                    249, 22, 176, 279, 23, 22, 617, 462, 459, 244 };
                    //{ 70, 55, 13, 2, 99, 2, 80, 80, 80, 80, 100, 19, 7, 5, 5, 5, 1000, 32, 32 };
                    { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
                    //{44};
            System.out.println(" Longest zigZag = " + zigZag(elements,0,0));

        }

        private static int zigZag(int[] array, int index, int alternate) {
            if (array.length-(index+1) <= 0) return 1;
            int diff = array[index+1]-array[index];
            if (index == 0) alternate = diff/Math.abs(diff);
            if ((diff*alternate) > 0 ) {
                return 1+zigZag(array,index+1,0-alternate);
            } else {
                return zigZag(array, index + 1, alternate);
            }

        }


}
