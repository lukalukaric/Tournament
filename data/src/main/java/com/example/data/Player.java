package com.example.data;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Player implements Comparable<Player> {
    private String nickname;
    private String firstName;
    private String lastName;
    private int age;
    private double KDRatio;

    public Player(String nickname, String firstName, String lastName, int age, double KDRatio) {
        this.nickname = nickname;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.KDRatio = KDRatio;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setKDRatio(double KDRatio) {
        this.KDRatio = KDRatio;
    }

    public double getKDRatio() {
        return KDRatio;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Player {" +
                "\n Nickname = " + nickname +
                "\n FirstName = " + firstName +
                "\n LastName = " + lastName +
                "\n Age = " + age +
                "\n KDRatio = " + KDRatio + " }\n";
    }

    @Override
    public int compareTo(Player player) {
        return nickname.compareTo(player.getNickname());
    }

    public static ArrayList<Player> generatePlayers(int n){
        Player temp;
        ArrayList<Player> tmp = new ArrayList<Player>();
        for(int i=0;i<n;i++){
            temp = new Player(generateWords(7),generateWords(5),generateWords(10),generateAge(),generateKDRatio());
            tmp.add(temp);
        }
        Collections.sort(tmp);
        return tmp;
    }
    public static String generateWords(int wordLength){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = wordLength;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
    public static int generateAge(){
        Random r = new Random();
        return r.nextInt(100-1)+1; //generiramo int med 1 in 100
    }
    public static double generateKDRatio(){
        Random r = new Random();
        return 0.5 + (2.0 - 0.5) * r.nextDouble(); //generiramo stevilo med 0.5 in 2.0
    }

}
