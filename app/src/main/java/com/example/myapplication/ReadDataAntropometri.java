package com.example.myapplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 *
 * @author WanHar
 */

public class ReadDataAntropometri {
    public double [][] dataBBUL = new double [61][8];

    public double[][] readBBUL(InputStream inputFile) throws IOException {
        BufferedReader objReader = null;
        try {
            String strCurrentLine;

            objReader = new BufferedReader(new InputStreamReader(inputFile, Charset.forName("UTF-8")));
            int i = 0;
            int j = 0;
            String[] temp;

            while ((strCurrentLine = objReader.readLine()) != null) {
                temp = strCurrentLine.split(",");
                for (j = 0; j < 8; j++) {
                    dataBBUL[i][j] = Double.parseDouble(temp[j]);
                }
                i++;
            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {
                if (objReader != null) {
                    objReader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return dataBBUL;
    }
    //find index
    public int findIndex (int umur, double input) {
        int tmpI = 0;
        int tmpJ = 0;
        for (int i=0; i<61;i++){
            if (umur==i){
                for(int j=1; j<8;j++){
                    if (input<=dataBBUL[i][1]){
                        tmpJ = 1;
                        break;
                    }
                    else if (input>=dataBBUL[i][7]){
                        tmpJ = 7;
                        break;
                    }
                    else if (input<dataBBUL[i][j+1]){
                        tmpJ = j;
                        break;
                    }
                }
            }
        }
        return tmpJ;
    }

}
