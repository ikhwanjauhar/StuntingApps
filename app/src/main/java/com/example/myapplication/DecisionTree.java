package com.example.myapplication;

public class DecisionTree {
    double mothBMI;
    int haz, waz, whz, totalNumberOfChildren, mothAge, childAge;
    char residence, childAnem, mothEdu, mothOccup, sexOfChild, sizeAtBirth;
    String wealthIndex;
    String predict (int zscore) {
        switch (zscore){
            case -3 : return "Sangat Pendek";
            case -2 : return "Lebih Pendek";
            case -1 : return "Pendek";
            case 0 : return "Normal";
            case 1 : return "Tinggi";
            case 2 : return "Lebih Tinggi";
            case 3 : return "Sangat Tinggi";
        }
        return "Error";
    }

    //Class Stunted
    /*
    Rule#1:
    If HAZ=<-2SD AND WAZ=-2SD to 2SD
     */
    boolean sRule1() {
        if (haz < -2 && waz >= 2 && waz <= 2) {
            return true;
        }
        return false;
    }

    /*
    If HAZ=<-2SD AND WHZ=-2SD-2SD AND
    RESIDENCE=Rural AND
    CHILD ANEM (child anemia level)=Mild AND MOTH
    EDU=Primary AND TOTAL
    (Total number of children)=5-6 AND SIZE (child size at
    birth)=Normal
    */
    boolean sRule2() {
        if (haz < -2 && (whz >= 2 && whz <= 2) && residence == 'R' && childAnem == 'I'
                && mothEdu == 'P' && (totalNumberOfChildren >= 5 && totalNumberOfChildren <= 6) && sizeAtBirth == 'N') {
            return true;
        }
        return false;
    }

    /*
    Rule#3: If HAZ=<-2SD AND CHILDANEM= Mild AND
    RESIDENCE=Rural AND
    MOTH BMI (mother’s body mass index)=<18.5 AND
    CHILDAGE=12-23 AND
    SIZE =Small
     */
    boolean sRule3() {
        if (haz < -2 && childAnem == 'I' &&  residence == 'R' && mothBMI <18.5 && (childAge>=12 && childAge<=23)  && sizeAtBirth == 'S') {
            return true;
        }
        return false;
    }
    /*
    Rule#4:
    If HAZ=<-2SD AND
    CHILDANEM=Mild AND
    RESIDENCE=Rural AND
    WEALTHINDEX=Middle AND
    MOTHEDUC=No education AND
    MOTHAGE= 25-29: Then the class Stunted (12.0/3.0)
    */
    boolean sRule4() {
        if (haz < -2 && childAnem == 'I' && residence == 'R' && wealthIndex == "M"
                && mothEdu == 'N' && (mothAge >= 25 && mothAge <= 29)) {
            return true;
        }
        return false;
    }



    /*
    Rule#7:
    If HAZ=<-2SD AND
    CHILD ANEM=Moderate AND
    MOTH EDUC=No education AND
    MOTH BMI=18.5-24.9 AND
    MOTH AGE=25-29 AND
    TOTAL=3-4 AND
    SIZE=Normal AND
    WEALTH INDEX=Poorest: Then the class
    Stunted (7.0/2.0)
    */
    boolean sRule7() {
        if (haz <= -2 && childAnem == 'M' && mothEdu == 'N'
                && (mothBMI >= 18.5 && mothBMI <= 24.9) && (mothAge >= 25 && mothAge <= 29)
                && (totalNumberOfChildren >= 3 && totalNumberOfChildren <= 4) && sizeAtBirth == 'N' && wealthIndex == "PS") {
            return true;
        }
        return false;
    }

    //Class Underweight

    /*
    Rule#5: If WAZ=<-2SD AND CHILD ANEM=Not anemic
    AND CHILD AGE=36-47 AND MOTH AGE=25-29 AND MOTH
    OCCUP=Not working AND SIZE=Small:
    THEN the class Underweight (25.0/1.0)
    */
    boolean uRule5() {
        if (waz<=-2 && childAnem=='N' && (childAge>=36 && childAge<=47) &&
                (mothAge >= 25 && mothAge <= 29) && mothOccup=='N' && sizeAtBirth == 'S') {
            return true;
        }
        return false;
    }

    //Region Wasted
    /*
    Rule#1: If WHZ=<-2SD AND WAZ =-2SD-2SD: Then the class
    Wasted (635.0/3.0)
    */
    boolean wRule1() {
        if (whz<-2 && (waz>=-2 && waz<=2)) {
            return true;
        }
        return false;
    }
    /*
    Rule#2: If WHZ=<-2SD AND CHILDANEM=Mild AND
    MOTHEDUC=No education: Then the class Wasted (205.0)
    */
    boolean wRule2() {
        if (whz<-2 && (waz>=-2 && waz<=2) && childAnem=='I' && mothEdu=='N') {
            return true;
        }
        return false;
    }
    /*
    Rule#3: If WHZ=<-2SD AND TOTAL=>6 AND
    MOTHBMI=18.5-24.9: Then the class Wasted (120.0/2.0)
    */
    boolean wRule3() {
        if (whz<=-2 && this.totalNumberOfChildren>6 && (this.mothBMI>=18.5 && this.mothBMI<=24.9)) {
            return true;
        }
        return false;
    }
    /*
    Rule#4: If WHZ=<-2SD AND HAZ=-2SD- 2SD AND SEX=Male:
    Then the class Wasted (45.0)
    */
    boolean wRule4() {
        if (whz<-2 && (haz>=-2 && haz<=2) && this.sexOfChild=='M') {
            return true;
        }
        return false;
    }

    //Class Normal
    /*
    Rule#1: If HAZ=-2SD- 2SD AND WAZ=-2SD-2SD AND WHZ=-
    2SD-2SD AND
    RESIDENCE=Rural: Then the class Normal (3472.0/1.0)
    */
    boolean nRule1() {
        if ((haz>=-2 && haz<=2) && (waz>=-2 && waz<=2) && (whz>=-2 && whz<=2) && residence=='R') {
            return true;
        }
        return false;
    }

}
