package game;

import java.util.Scanner;

public class AI {
//        public static void main(String[] args) {
//            Scanner input = new Scanner(System.in);
//            int [][] mat = new int[6][7];
//            MatrixCalc m = new MatrixCalc();
//            AI ai = new AI();
//            boolean b  = true ;
//            while (b){
//                ai.hard(mat);
//                for (int i = 0; i < 6; i++) {
//                    for (int j = 0; j < 7; j++) {
//                        System.out.print(mat[i][j] + " ");
//                    }
//                    System.out.println();
//                }
//
//                System.out.println("--------------------------------");
//                if(m.MatrixWin(mat)){
//                    System.out.println("Player 2 win");
//                    b = false ;
//                }else {
//                    ai.assignMatrix(mat , input.nextInt() , 1);
//                    for (int i = 0; i < 6; i++) {
//                        for (int j = 0; j < 7; j++) {
//                            System.out.print(mat[i][j] + " ");
//                        }
//                        System.out.println();
//                    }
//                    System.out.println("--------------------------------");
//                    if(m.MatrixWin(mat)){
//                        System.out.println("Player 1 win");
//                        b = false ;
//                    }
//                }
//            }
//        }


    public void easy(int[][] conMatrix) {
        int index = (int)(Math.random() * 6);
        assignMatrix(conMatrix , index , 2);
    }

    public void medium(int[][] conMatrix) {
        int chose = (int) Math.round(Math.random());
        if(chose == 1){
            hard(conMatrix);
        }else {
            easy(conMatrix);
        }
    }

    public void hard(int[][] conMatrix) {
        /*
         let Player1 play by 1 and comp play by 2
         first we search if comp have 3 coin and if found we will  make a comp play last coin
         first search
         1st row
         1 1 1 1 0 0 0
         0 1 1 1 1 0 0
         0 0 1 1 1 1 0
         0 0 0 1 1 1 1
         then skip  rows for 4items is equal with shift UP
         */

         /*
         first for loop use to check first if comp has a chance for win and if not found any
         chance it will check if player1 have a chance in second loop
        */
        for (int n = 2; n >= 1; n--) {
            for (int i = 5; i >= 0; i--) {
                for (int k = 3; k >= 0; k--) {
                    int count = 0;
                    // count number of coins
                    for (int j = 0; j <= 3; j++) {
                        if (conMatrix[i][k + j] == n) {
                            count++;
                        }
                    }
                    // if we found 3 coin then count = 3 and last index = 0
                    if (count == 3) {
                        // search where is index 0
                        for (int j = 0; j <= 3; j++) {
                            if (conMatrix[i][k + j] == 0) {
                                // now check if this index valid
                                if (i == 5 || conMatrix[i + 1][k + j] != 0) {
                                    conMatrix[i][k + j] = 2; // play 2 in this index
                                    return; // end hard method
                                }
                            }
                        }
                    }
                }
            }

            //if we not found any 3 coin in last check
            //we search by anther way
            //         1
            //         1
            //         1
            //         1
            //         0
            //         0
            // 2nd column
            for (int i = 2; i >= 0; i--) {
                for (int k = 6; k >= 0; k--) {
                    int count = 0;
                    // count number of coins
                    for (int j = 0; j <= 3; j++) {
                        if (conMatrix[i + j][k] == n) {
                            count++;
                        }
                    }
                    // if we found 3 coin then count = 3 and last index = 0
                    if (count == 3) {
                        // index 0 in this way allows in top index if found
                        if (conMatrix[i][k] == 0) {
                            conMatrix[i][k] = 2; // play 2 in this index
                            return; // end hard method
                        }
                    }
                }
            }
            //if we not found any 3 coin in last check
            //we search by anther way
            //                       1
            //                  1    1
            //             1    1    1
            //         1   1    1    0
            //      1  1   1    0    0
            //   1  1  1   0    0    0
            // sure diagonal for 4items is equal with shift down  3rd diagonal R TO L
            for (int k = 0; k <= 2; k++) {
                for (int i = 0; i < 3 - k; i++) {
                    int count = 0;
                    // count number of coins
                    for (int j = 0; j <= 3; j++) {
                        if (conMatrix[i + k + j][6 - (i + j)] == n) {
                            count++;
                        }
                    }
                    // if we found 3 coin then count = 3 and last index = 0
                    if (count == 3) {
                        // search where is index 0
                        for (int j = 0; j <= 3; j++) {
                            if (conMatrix[i + k + j][6 - (i + j)] == 0) {
                                // now check if this index valid
                                if (i + k + j == 5 || conMatrix[i + k + j + 1][6 - (i + j)] != 0) {
                                    conMatrix[i + k + j][6 - (i + j)] = 2; // play 2 in this index
                                    return; // end hard method
                                }
                            }
                        }
                    }
                }
            }
            //if we not found any 3 coin in last check
            //we search by anther way
            //   0  0  0  1  1  1
            //   0  0  1  1  1
            //   0  1  1  1
            //   1  1  1
            //   1  1
            //   1
            // sure diagonal for 4items is equal with shift down  3rd diagonal L TO R
            for (int k = 0; k <= 2; k++) {
                for (int i = 0; i < 3 - k; i++) {
                    int count = 0;
                    // count number of coins
                    for (int j = 0; j <= 3; j++) {
                        if (conMatrix[i + j][5 - (i + k + j)] == n) {
                            count++;
                        }
                    }
                    // if we found 3 coin then count = 3 and last index = 0
                    if (count == 3) {
                        // search where is index 0
                        for (int j = 0; j <= 3; j++) {
                            if (conMatrix[i + j][5 - (i + k + j)] == 0) {
                                // now check if this index valid
                                if (i + j == 5 || conMatrix[i + j + 1][5 - (i + k + j)] != 0) {
                                    conMatrix[i + j][5 - (i + k + j)] = 2; // play 2 in this index
                                    return; // end hard method
                                }
                            }
                        }
                    }
                }
            }
            //if we not found any 3 coin in last check
            //we search by anther way
            //  1 1
            //  1 1 1
            //  1 1 1 1
            //  0 1 1 1 1
            //  0 0 1 1 1 1
            //  0 0 0 1 1 1 1
            // sure diagonal for 4items is equal with shift down  4th diagonal L TO R
            for (int k = 0; k <= 2; k++) {
                for (int i = 0; i < 3 - k; i++) {
                    int count = 0;
                    // count number of coins
                    for (int j = 0; j <= 3; j++) {
                        if (conMatrix[i + k + j][i + j] == n) {
                            count++;
                        }
                    }
                    // if we found 3 coin then count = 3 and last index = 0
                    if (count == 3) {
                        // search where is index 0
                        for (int j = 0; j <= 3; j++) {
                            if (conMatrix[i + k + j][i + j] == 0) {
                                // now check if this index valid
                                if (i + k + j == 5 || conMatrix[i + k + j + 1][i + j] != 0) {
                                    conMatrix[i + k + j][i + j] = 2; // play 2 in this index
                                    return; // end hard method
                                }
                            }
                        }
                    }
                }
            }
            //if we not found any 3 coin in last check
            //we search by anther way
            for (int k = 0; k <= 2; k++) {
                for (int i = 0; i < 3 - k; i++) {
                    int count = 0;
                    // count number of coins
                    for (int j = 0; j <= 3; j++) {
                        if (conMatrix[i + j][k + i + j + 1] == n) {
                            count++;
                        }
                    }
                    // if we found 3 coin then count = 3 and last index = 0
                    if (count == 3) {
                        // search where is index 0
                        for (int j = 0; j <= 3; j++) {
                            if (conMatrix[i + j][k + i + j + 1] == 0) {
                                // now check if this index valid
                                if (i + j == 5 || conMatrix[i + j + 1][k + i + j + 1] != 0) {
                                    conMatrix[i + j][k + i + j + 1] = 2; // play 2 in this index
                                    return; // end hard method
                                }
                            }
                        }
                    }
                }
            }
        }
        /*
         if we not found any 3 coin in last check
         then we not have any 3 coin for player1 or comp,
         so we check if we have 2 coin
        */
        //---------------------------------------------------------------------------------------
        /*
        first we search if comp have 3 coin and if found we will  make a comp play last coin
        first search
        1st row
        1 1 1 1 0 0 0
        0 1 1 1 1 0 0
        0 0 1 1 1 1 0
        0 0 0 1 1 1 1
        then skip  rows for 4items is equal with shift UP
         */

         /*
         first for loop use to check first if comp has a chance for win and if not found any
         chance it will check if player1 have a chance in second loop
        */
        for (int n = 1; n <= 2; n++) {
            for (int i = 5; i >= 0; i--) {
                for (int k = 3; k >= 0; k--) {
                    int count = 0;
                    // count number of coins
                    for (int j = 0; j <= 3; j++) {
                        if (conMatrix[i][k + j] == n) {
                            count++;
                        }
                    }
                    // if we found 2 coin then count = 2 and maybe have two index = 0
                    if (count == 2) {
                        // search where are two index 0
                        for (int j = 0; j <= 3; j++) {
                            if (conMatrix[i][k + j] == 0) {
                                // now check if this index valid
                                if (i == 5 || conMatrix[i + 1][k + j] != 0) {
                                    conMatrix[i][k + j] = 2; // play 2 in this index
                                    return; // end hard method
                                }
                            }
                        }
                    }
                }
            }

            //if we not found any 2 coin in last check
            //we search by anther way
            //         1
            //         1
            //         1
            //         1
            //         0
            //         0
            // 2nd column
            for (int i = 2; i >= 0; i--) {
                for (int k = 6; k >= 0; k--) {
                    int count = 0;
                    // count number of coins
                    for (int j = 0; j <= 3; j++) {
                        if (conMatrix[i + j][k] == n) {
                            count++;
                        }
                    }
                    // if we found 2 coin then count = 2 and maybe have two index = 0
                    if (count == 2) {
                        // index 0 in this way allows in top index if found
                        if (conMatrix[i + 1][k] == 0) {
                            conMatrix[i + 1][k] = 2; // play 2 in this index
                            return; // end hard method
                        }
                    }
                }
            }
            //if we not found any 2 coin in last check
            //we search by anther way
            //                       1
            //                  1    1
            //             1    1    1
            //         1   1    1    0
            //      1  1   1    0    0
            //   1  1  1   0    0    0
            // sure diagonal for 4items is equal with shift down  3rd diagonal R TO L
            for (int k = 0; k <= 2; k++) {
                for (int i = 0; i < 3 - k; i++) {
                    int count = 0;
                    // count number of coins
                    for (int j = 0; j <= 3; j++) {
                        if (conMatrix[i + k + j][6 - (i + j)] == n) {
                            count++;
                        }
                    }
                    // if we found 2 coin then count = 2 and maybe have two index = 0
                    if (count == 2) {
                        // search where is index 0
                        for (int j = 0; j <= 3; j++) {
                            if (conMatrix[i + k + j][6 - (i + j)] == 0) {
                                // now check if this index valid
                                if (i + k + j == 5 || conMatrix[i + k + j + 1][6 - (i + j)] != 0) {
                                    conMatrix[i + k + j][6 - (i + j)] = 2; // play 2 in this index
                                    return; // end hard method
                                }
                            }
                        }
                    }
                }
            }
            //if we not found any 2 coin in last check
            //we search by anther way
            //   0  0  0  1  1  1
            //   0  0  1  1  1
            //   0  1  1  1
            //   1  1  1
            //   1  1
            //   1
            // sure diagonal for 4items is equal with shift down  3rd diagonal L TO R
            for (int k = 0; k <= 2; k++) {
                for (int i = 0; i < 3 - k; i++) {
                    int count = 0;
                    // count number of coins
                    for (int j = 0; j <= 3; j++) {
                        if (conMatrix[i + j][5 - (i + k + j)] == n) {
                            count++;
                        }
                    }
                    // if we found 2 coin then count = 2 and maybe have two index = 0
                    if (count == 2) {
                        // search where is index 0
                        for (int j = 0; j <= 3; j++) {
                            if (conMatrix[i + j][5 - (i + k + j)] == 0) {
                                // now check if this index valid
                                if (i + j == 5 || conMatrix[i + j + 1][5 - (i + k + j)] != 0) {
                                    conMatrix[i + j][5 - (i + k + j)] = 2; // play 2 in this index
                                    return; // end hard method
                                }
                            }
                        }
                    }
                }
            }
            //if we not found any 2 coin in last check
            //we search by anther way
            //  1 1
            //  1 1 1
            //  1 1 1 1
            //  0 1 1 1 1
            //  0 0 1 1 1 1
            //  0 0 0 1 1 1 1
            // sure diagonal for 4items is equal with shift down  4th diagonal L TO R
            for (int k = 0; k <= 2; k++) {
                for (int i = 0; i < 3 - k; i++) {
                    int count = 0;
                    // count number of coins
                    for (int j = 0; j <= 3; j++) {
                        if (conMatrix[i + k + j][i + j] == n) {
                            count++;
                        }
                    }
                    // if we found 2 coin then count = 2 and maybe have two index = 0
                    if (count == 2) {
                        // search where is index 0
                        for (int j = 0; j <= 3; j++) {
                            if (conMatrix[i + k + j][i + j] == 0) {
                                // now check if this index valid
                                if (i + k + j == 5 || conMatrix[i + k + j + 1][i + j] != 0) {
                                    conMatrix[i + k + j][i + j] = 2; // play 2 in this index
                                    return; // end hard method
                                }
                            }
                        }
                    }
                }
            }
            //if we not found any 2 coin in last check
            //we search by anther way
            for (int k = 0; k <= 2; k++) {
                for (int i = 0; i < 3 - k; i++) {
                    int count = 0;
                    // count number of coins
                    for (int j = 0; j <= 3; j++) {
                        if (conMatrix[i + j][k + i + j + 1] == n) {
                            count++;
                        }
                    }
                    // if we found 2 coin then count = 2 and maybe have two index = 0
                    if (count == 2) {
                        // search where is index 0
                        for (int j = 0; j <= 3; j++) {
                            if (conMatrix[i + j][k + i + j + 1] == 0) {
                                // now check if this index valid
                                if (i + j == 5 || conMatrix[i + j + 1][k + i + j + 1] != 0) {
                                    conMatrix[i + j][k + i + j + 1] = 2; // play 2 in this index
                                    return; // end hard method
                                }
                            }
                        }
                    }
                }
            }
        }
        /*
         if we not found any 2 coin in last check
         then we not have any 2 coin for player1 or comp,
         so we check if we have 1 coin
        */
        //---------------------------------------------------------------------------------------
        /*
        first we search if comp have 1 coin and if found we will  make a comp play last coin
        first search
        1st row
        1 1 1 1 0 0 0
        0 1 1 1 1 0 0
        0 0 1 1 1 1 0
        0 0 0 1 1 1 1
        then skip  rows for 4items is equal with shift UP
         */

         /*
         first for loop use to check first if comp has a chance for win and if not found any
         chance it will check if player1 have a chance in second loop
         5041534243
        */
        for (int n = 2; n >= 1; n--) {
            for (int i = 5; i >= 0; i--) {
                for (int k = 3; k >= 0; k--) {
                    int count = 0;
                    // count number of coins
                    for (int j = 0; j <= 3; j++) {
                        if (conMatrix[i][k + j] == n) {
                            count++;
                        }
                    }
                    // if we found 1 coin then count = 1 and maybe have three index = 0
                    if (count == 1) {
                        // search where are two index 0
                        for (int j = 0; j <= 3; j++) {
                            if (conMatrix[i][k + j] == 0) {
                                // now check if this index valid
                                if (i == 5 || conMatrix[i + 1][k + j] != 0) {
                                    conMatrix[i][k + j] = 2; // play 2 in this index
                                    return; // end hard method
                                }
                            }
                        }
                    }
                }
            }

            //if we not found any 1 coin in last check
            //we search by anther way
            //         1
            //         1
            //         1
            //         1
            //         0
            //         0
            // 2nd column
            for (int i = 2; i >= 0; i--) {
                for (int k = 6; k >= 0; k--) {
                    int count = 0;
                    // count number of coins
                    for (int j = 0; j <= 3; j++) {
                        if (conMatrix[i + j][k] == n) {
                            count++;
                        }
                    }
                    // if we found 1 coin then count = 1 and maybe have three index = 0
                    if (count == 1) {
                        // index 0 in this way allows in top index if found
                        if (conMatrix[i + 2][k] == 0) {
                            conMatrix[i + 2][k] = 2; // play 2 in this index
                            return; // end hard method
                        }
                    }
                }
            }
            //if we not found any 1 coin in last check
            //we search by anther way
            //                       1
            //                  1    1
            //             1    1    1
            //         1   1    1    0
            //      1  1   1    0    0
            //   1  1  1   0    0    0
            // sure diagonal for 4items is equal with shift down  3rd diagonal R TO L
            for (int k = 0; k <= 2; k++) {
                for (int i = 0; i < 3 - k; i++) {
                    int count = 0;
                    // count number of coins
                    for (int j = 0; j <= 3; j++) {
                        if (conMatrix[i + k + j][6 - (i + j)] == n) {
                            count++;
                        }
                    }
                    // if we found 1 coin then count = 1 and maybe have three index = 0

                    if (count == 1) {
                        // search where is index 0
                        for (int j = 0; j <= 3; j++) {
                            if (conMatrix[i + k + j][6 - (i + j)] == 0) {
                                // now check if this index valid
                                if (i + k + j == 5 || conMatrix[i + k + j + 1][6 - (i + j)] != 0) {
                                    conMatrix[i + k + j][6 - (i + j)] = 2; // play 2 in this index
                                    return; // end hard method
                                }
                            }
                        }
                    }
                }
            }
            //if we not found any 1 coin in last check
            //we search by anther way
            //   0  0  0  1  1  1
            //   0  0  1  1  1
            //   0  1  1  1
            //   1  1  1
            //   1  1
            //   1
            // sure diagonal for 4items is equal with shift down  3rd diagonal L TO R
            for (int k = 0; k <= 2; k++) {
                for (int i = 0; i < 3 - k; i++) {
                    int count = 0;
                    // count number of coins
                    for (int j = 0; j <= 3; j++) {
                        if (conMatrix[i + j][5 - (i + k + j)] == n) {
                            count++;
                        }
                    }
                    // if we found 1 coin then count = 1 and maybe have three index = 0
                    if (count == 1) {
                        // search where is index 0
                        for (int j = 0; j <= 3; j++) {
                            if (conMatrix[i + j][5 - (i + k + j)] == 0) {
                                // now check if this index valid
                                if (i + j == 5 || conMatrix[i + j + 1][5 - (i + k + j)] != 0) {
                                    conMatrix[i + j][5 - (i + k + j)] = 2; // play 2 in this index
                                    return; // end hard method
                                }
                            }
                        }
                    }
                }
            }
            //if we not found any 1 coin in last check
            //we search by anther way
            //  1 1
            //  1 1 1
            //  1 1 1 1
            //  0 1 1 1 1
            //  0 0 1 1 1 1
            //  0 0 0 1 1 1 1
            // sure diagonal for 4items is equal with shift down  4th diagonal L TO R
            for (int k = 0; k <= 2; k++) {
                for (int i = 0; i < 3 - k; i++) {
                    int count = 0;
                    // count number of coins
                    for (int j = 0; j <= 3; j++) {
                        if (conMatrix[i + k + j][i + j] == n) {
                            count++;
                        }
                    }
                    // if we found 1 coin then count = 1 and maybe have three index = 0
                    if (count == 1) {
                        // search where is index 0
                        for (int j = 0; j <= 3; j++) {
                            if (conMatrix[i + k + j][i + j] == 0) {
                                // now check if this index valid
                                if (i + k + j == 5 || conMatrix[i + k + j + 1][i + j] != 0) {
                                    conMatrix[i + k + j][i + j] = 2; // play 2 in this index
                                    return; // end hard method
                                }
                            }
                        }
                    }
                }
            }
            //if we not found any 1 coin in last check
            //we search by anther way
            for (int k = 0; k <= 2; k++) {
                for (int i = 0; i < 3 - k; i++) {
                    int count = 0;
                    // count number of coins
                    for (int j = 0; j <= 3; j++) {
                        if (conMatrix[i + j][k + i + j + 1] == n) {
                            count++;
                        }
                    }
                    // if we found 1 coin then count = 1 and maybe have three index = 0
                    if (count == 1) {
                        // search where is index 0
                        for (int j = 0; j <= 3; j++) {
                            if (conMatrix[i + j][k + i + j + 1] == 0) {
                                // now check if this index valid
                                if (i + j == 5 || conMatrix[i + j + 1][k + i + j + 1] != 0) {
                                    conMatrix[i + j][k + i + j + 1] = 2; // play 2 in this index
                                    return; // end hard method
                                }
                            }
                        }
                    }
                }
            }
        }
        /*
         if we not found any 1 coin in last check
         then the matrix is empty so comp we will play in random index
        */
        int index = (int)(Math.random() * 6);
        conMatrix[5][index] = 2 ;
    }

    public void assignMatrix(int [][] matrix , int colum , int playerNumber){
        for (int i = 5; i >= 0; i--) {
            if(matrix[i][colum] == 0){
                matrix[i][colum] = playerNumber;
                return;
            }
        }
    }
}


