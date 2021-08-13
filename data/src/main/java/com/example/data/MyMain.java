package com.example.data;

public class MyMain {
    public static void main(String[] args) throws NoPlayersException {
        Player pasha = new Player("Pasha","Marko","Skace",25,1.5);
        Team teamTest = new Team("NIP","Ninjas in pyjamas",6);
        teamTest.addPlayer(pasha);
        Player Aasha = new Player("Aasha","Marko","Skace",25,1.5);
        teamTest.addPlayer(Aasha);
        System.out.println(teamTest);
        try{
            teamTest.sortByNickname();
            System.out.println("Sorted: \n" + teamTest);
        }catch (NoPlayersException noPlayersException){
            System.out.println("You cannot sort empty list.");
        }

        try{
            Team test = new Team("ss","asd",5);
            test.sortByNickname();
            System.out.println("Sorted: \n" + teamTest);
        }catch (NoPlayersException noPlayersException){
            System.out.println("You cannot sort empty list.");
        }

        Team test2 = new Team("something","asd",10);
        test2.addPlayer(Player.generatePlayers(10));
        System.out.println(test2);

        try{
            System.out.println("max:" + test2.getMaxAge() + "min" + test2.getMinAge() + "avg:" + test2.getAverageAge());
            System.out.println("median: " + test2.getMedian());
        }catch (NoPlayersException noPlayersException){
            System.out.println("Your list is empty.");
        }
        
        try{
            Team test = new Team("ss","asd",5);
            System.out.println("median: " + test.getMaxAge());
        }catch (NoPlayersException noPlayersException){
            System.out.println("Your list is empty.");
        }
    }
}