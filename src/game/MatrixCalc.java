package game;

public class MatrixCalc {
    public boolean MatrixWin(int[][] conMatrix, int NumberPlayer) {
        //1st row
        // 1 1 1 1 0 0 0
        // 0 1 1 1 1 0 0
        // 0 0 1 1 1 1 0
        // 0 0 0 1 1 1 1
        // then skip  rows for 4items is equal with shift UP
        for (int i = 5; i >= 0; i--)
            for (int k = 3; k >= 0; k--) {
                if (conMatrix[i][k] == NumberPlayer && conMatrix[i][k + 1] == NumberPlayer && conMatrix[i][k + 2] == NumberPlayer && conMatrix[i][k + 3] == NumberPlayer) {
                    return true;
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
                if (conMatrix[i][k] == NumberPlayer && conMatrix[i + 1][k] == NumberPlayer && conMatrix[i + 2][k] == NumberPlayer && conMatrix[i + 3][k] == NumberPlayer) {
                    return true;
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
                if (conMatrix[i + k][6 - i] == NumberPlayer && conMatrix[i + k + 1][6 - (i + 1)] == NumberPlayer && conMatrix[i + k + 2][6 - (i + 2)] == NumberPlayer && conMatrix[i + k + 3][6 - (i + 3)] == NumberPlayer) {
                    return true;
                }
            }

        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j < 3 - i; j++) {
                if (conMatrix[j][5 - (j + i)] == NumberPlayer && conMatrix[j + 1][5 - (j + i + 1)] == NumberPlayer &&
                        conMatrix[j + 2][5 - (j + i + 2)] == NumberPlayer && conMatrix[j + 3][5 - (j + i + 3)] == NumberPlayer) {
                    return true;
                }
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
                if (conMatrix[i + k][i] == NumberPlayer && conMatrix[i + k + 1][(i + 1)] == NumberPlayer && conMatrix[i + k + 2][(i + 2)] == NumberPlayer && conMatrix[i + k + 3][(i + 3)] == NumberPlayer) {
                    return true;
                }
            }

        for (int i=0;i<3;i++){
            for(int j=0;j<3-i;j++){

                 if (
                         conMatrix[j][j+i+1]==NumberPlayer && conMatrix[j+1][j+i+2]==NumberPlayer &&conMatrix[j+2][j+i+3]==NumberPlayer &&conMatrix[j+3][j+i+4]==NumberPlayer) {  return true; }
                                }
    }
        return false;
    }
}