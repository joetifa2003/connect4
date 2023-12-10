package game;

public class MatrixCalc {

    int indexR;
    int indexC;
    String player1;
    String player2;
    boolean b = true;

    String switchPlayer;

    MatrixCalc(String player1, String player2) {

        this.player1 = player1;
        this.player2 = player2;
    }


    public String MatrixWin(int conMatrix[][]) {


        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(conMatrix[i][j] + " ");
            }
            System.out.println();
        }
        while (b) {


            //1st row
            // 1 1 1 1 0 0 0
            // 0 1 1 1 1 0 0
            // 0 0 1 1 1 1 0
            // 0 0 0 1 1 1 1
            // then skip  rows for 4items is equal with shift UP
            for (int i = 5; i >= 0; i--)
                for (int k = 3; k >= 0; k--) {

                    if (
                            conMatrix[i][k] == 1
                                    && conMatrix[i][k + 1] == 1
                                    && conMatrix[i][k + 2] == 1
                                    && conMatrix[i][k + 3] == 1
                    ) {
                        b = false;
                        break;
                    }
                }


            //         1
            //         1
            //         1
            //         1
            //         0
            //         0
            // 2nd cloumn

            for (int i = 2; i >= 0; i--) {
                for (int k = 6; k >= 0; k--) {

                    if (
                            conMatrix[i][k] == 1
                                    && conMatrix[i + 1][k] == 1
                                    && conMatrix[i + 2][k] == 1
                                    && conMatrix[i + 3][k] == 1
                    ) {
                        b = false;
                        break;
                    }
                }
            }
            //                       1
            //                  1    1
            //             1    1    1
            //         1   1    1    0
            //      1  1   1    0    0
            //   1  1  1   0    0    0
            // sure diagonal for 4items is equal with shift down  3rd diagonal R TO L


            for (int k = 0; k <= 2; k++)
                for (int i = 0; i < 3 - k; i++) {
                    if (
                            conMatrix[i + k][6 - i] == 1
                                    && conMatrix[i + k + 1][6 - (i + 1)] == 1
                                    && conMatrix[i + k + 2][6 - (i + 2)] == 1
                                    && conMatrix[i + k + 3][6 - (i + 3)] == 1
                    ) {
                        System.out.println("Done");
                        b = false;
                        break;
                    }


                    //Change player
                    if (player1.equals(switchPlayer)) {
                        switchPlayer = player2;
                    } else {
                        switchPlayer = player1;
                    }


                }

            //  1
            //  1 1
            //  1 1
            //  0 1 1 1 1
            //  0 0 1 1 1 1
            //  0 0 0 1 1 1
            // sure diagonal for 4items is equal with shift down  4th diagonal L TO R

            for (int k = 0; k <= 2; k++)
                for (int i = 0; i < 3 - k; i++) {
                    if (
                            conMatrix[i + k][i] == 1
                                    && conMatrix[i + k + 1][(i + 1)] == 1
                                    && conMatrix[i + k + 2][(i + 2)] == 1
                                    && conMatrix[i + k + 3][(i + 3)] == 1
                    ) {
                        System.out.println("Done");
                        b = false;
                        break;
                    }


                    //Change player
                    if (player1.equals(switchPlayer)) {
                        switchPlayer = player2;
                    } else {
                        switchPlayer = player1;
                    }


                }

        }


        return player1 + " is a winner ";
    }
}

