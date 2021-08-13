package com.example.data;

import java.util.ArrayList;
import java.util.Collections;

public class Team implements Sizable {
    private String name;
    private String description;
    private int numberOfPlayers;
    private ArrayList<Player> listOfPlayers;

    public Team() {
        this("My Team","This is my team",10);
    }

    public void setListOfPlayers(ArrayList<Player> listOfPlayers) {
        this.listOfPlayers = listOfPlayers;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public Team(String name, String description, int numberOfPlayers) {
        this.name = name;
        this.description = description;
        this.numberOfPlayers = numberOfPlayers;
        listOfPlayers = new ArrayList<>();
    }

    public void addPlayer(Player player){
        listOfPlayers.add(player);
    }

    @Override
    public String toString() {
        return "Team {" +
                "\n Name = " + name +
                "\n Description = " + description +
                "\n NumberOfPlayers = " + numberOfPlayers +
                "\n ListOfPlayers : \n" + listOfPlayers + " }\n";
    }

    public void sortByNickname()throws NoPlayersException{
        if(this.listOfPlayers.size()==0) throw new NoPlayersException(name);
        Collections.sort(listOfPlayers);
    }

    @Override
    public int getSize() {
        return listOfPlayers.size();
    }

    @Override
    public void setSize(int size) {
        numberOfPlayers = size;
    }

    public Player getMinAge() throws NoPlayersException{
        if(this.listOfPlayers.size()==0) throw new NoPlayersException(name);
        Player temp = this.listOfPlayers.get(0);
        for(int i=1; i<listOfPlayers.size();i++) {
            if(listOfPlayers.get(i).getAge() < temp.getAge()){
                temp = listOfPlayers.get(i);
            }
        }
        return temp;
    }
    public Player getMaxAge()throws NoPlayersException{
        if(this.listOfPlayers.size()==0) throw new NoPlayersException(name);
        Player temp = this.listOfPlayers.get(0);
        for(int i=1; i<listOfPlayers.size();i++) {
            if(listOfPlayers.get(i).getAge() > temp.getAge()){
                temp = listOfPlayers.get(i);
            }
        }
        return temp;
    }
    public int getAverageAge()throws NoPlayersException{
        if(this.listOfPlayers.size()==0) throw new NoPlayersException(name);
        int sum = 0;
        for(int i=0; i<listOfPlayers.size();i++){
            sum+=listOfPlayers.get(i).getAge();
        }
        return sum/listOfPlayers.size();
    }

    public Player getMedian()throws NoPlayersException{
        if(this.listOfPlayers.size()==0) throw new NoPlayersException(name);
        /*if(this.listOfPlayers.size()%2 == 0){
            int index1 = this.listOfPlayers.get(listOfPlayers.size()/2).getAge();
            int index2 = this.listOfPlayers.get(listOfPlayers.size()/2 + 1).getAge();
            return this.listOfPlayers.get((index1 + index2) / 2);
        }
        else{
            return this.listOfPlayers.get((listOfPlayers.size()/2));
        }*/
        return this.listOfPlayers.get((listOfPlayers.size()/2));
    }

    public void addPlayer(ArrayList<Player> players){
        setListOfPlayers(players);
    }

    public Player getPlayerAtPos(int position) {
        return listOfPlayers.get(position);
    }

    public void removeAt(int position) {
        listOfPlayers.remove(position);
    }
}
