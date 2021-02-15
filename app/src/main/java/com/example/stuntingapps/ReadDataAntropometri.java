package com.example.stuntingapps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 *
 * @author WanHar
 */

public class ReadDataAntropometri {

    public double[][] readData060(InputStream inputFile) throws IOException {
        double [][] dataAntropometri = new double [61][8];
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
                    dataAntropometri[i][j] = Double.parseDouble(temp[j]);
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
        return dataAntropometri;
    }

    public double[][] readData024(InputStream inputFile) throws IOException {
        double [][] dataAntropometri = new double [25][8];
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
                    dataAntropometri[i][j] = Double.parseDouble(temp[j]);
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
        return dataAntropometri;
    }

    //Region WHZ
    //WHZ Umur 0-24
    public double[][] readDataWHZ024(InputStream inputFile) throws IOException {
        double [][] dataAntropometri = new double [131][8];
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
                    dataAntropometri[i][j] = Double.parseDouble(temp[j]);
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
        return dataAntropometri;
    }

    //WHZ Umur 24-60
    public double[][] readDataWHZ2460(InputStream inputFile) throws IOException {
        double [][] dataAntropometri = new double [111][8];
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
                    dataAntropometri[i][j] = Double.parseDouble(temp[j]);
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
        return dataAntropometri;
    }

    //find index
    public int findIndex (int umur, double input, double [][] dataAntropometri) {
        int tmpI = 0;
        int tmpJ = 0;
        for (int i=0; i<61;i++){
            if (umur==i){
                for(int j=1; j<8;j++){
                    if (input<=dataAntropometri[i][1]){
                        tmpJ = 1;
                        break;
                    }
                    else if (input>=dataAntropometri[i][7]){
                        tmpJ = 7;
                        break;
                    }
                    else if (input<dataAntropometri[i][j+1]){
                        tmpJ = j;
                        break;
                    }
                }
            }
        }
        return tmpJ;
    }

    //Index WHZ
    //Find Index WHZ Umur 0-24
    public int findIndexWHZ024 (double tinggiBadan, double beratBadan, double [][] dataAntropometri) {
        int tmpI = 0;
        int tmpJ = 0;
        for (int i=0; i<131;i++){
            if (tinggiBadan==dataAntropometri[i][0]){
                for(int j=1; j<8;j++){
                    if (beratBadan<=dataAntropometri[i][1]){
                        tmpJ = 1;
                        break;
                    }
                    else if (beratBadan>=dataAntropometri[i][7]){
                        tmpJ = 7;
                        break;
                    }
                    else if (beratBadan<dataAntropometri[i][j+1]){
                        tmpJ = j;
                        break;
                    }
                }
            }
        }
        return tmpJ;
    }

    //Find Index WHZ Umur 24-60
    public int findIndexWHZ2460 (double tinggiBadan, double beratBadan, double [][] dataAntropometri) {
        int tmpI = 0;
        int tmpJ = 0;
        for (int i=0; i<111;i++){
            if (tinggiBadan==dataAntropometri[i][0]){
                for(int j=1; j<8;j++){
                    if (beratBadan<=dataAntropometri[i][1]){
                        tmpJ = 1;
                        break;
                    }
                    else if (beratBadan>=dataAntropometri[i][7]){
                        tmpJ = 7;
                        break;
                    }
                    else if (beratBadan<dataAntropometri[i][j+1]){
                        tmpJ = j;
                        break;
                    }
                }
            }
        }
        return tmpJ;
    }

}
