package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static final int ID = 0;
    public static final int NAME = 1;
    public static final int SEX = 2;
    public static final int AGE = 3;
    public static final int HEIGHT = 4;
    public static final int WEIGHT = 5;
    public static final int TEAM = 6;
    public static final int NOC = 7;
    public static final int GAMES = 8;
    public static final int YEAR = 9;
    public static final int SEASON = 10;
    public static final int CITY = 11;
    public static final int SPORT = 12;
    public static final int EVENT = 13;
    public static final int MEDAL = 14;

    public static void main(String[] args) {
        List<Athlete> athleteList = getTheDatafromAthlete();

//         displayYearWiseNumberOfGoldMedalsWonByEachPlayer(athleteList);
//         playerWonGoldMedalIn1980AndAgeIsLessThan30(athleteList);
//         eventWiseNumberOfGoldSilverBronzeMedalIn1980(athleteList);
//         getGoldWinnerOfFootBall(athleteList);
//         getFemaleAthleteWonMaxGoldMedal(athleteList);
//        name Of Athlete Participated In More Than Three Olympics
        nameOfAthleteParticipateMoreThanTheeOlympics(athleteList);

    }

      public static void nameOfAthleteParticipateMoreThanTheeOlympics(List<Athlete> athleteList) {
        HashMap<String,ArrayList<Integer>>numberOfOlympicMap= new HashMap<>();
         for (Athlete athlete:athleteList){
             int year = athlete.getYear();
             String name = athlete.getName();
             if(numberOfOlympicMap.containsKey(name)){
                 ArrayList<Integer>valueList=numberOfOlympicMap.get(name);
                 if(!valueList.contains(year)){
                     valueList.add(year);
                 }
             }else{
                 ArrayList<Integer> newArrayList= new ArrayList<>();
                 newArrayList.add(year);
                 numberOfOlympicMap.put(name,newArrayList);
             }
         }
         ArrayList<String>answer= new ArrayList<>();
          for(String name:numberOfOlympicMap.keySet()){
              ArrayList<Integer> valueList=numberOfOlympicMap.get(name);
//              System.out.println(valueList);
              if(valueList.size()>3){
                  answer.add(name);
              }
          }
          System.out.println(answer);
    }

    public static void getFemaleAthleteWonMaxGoldMedal(List<Athlete> athleteList) {
        HashMap<String,Integer> femaleGoldMap=new HashMap<>();
        for(Athlete athlete:athleteList){
            char sex=athlete.getSex();
            String name=athlete.getName();
            String medal=athlete.getMedal();
            if(sex=='F' && medal.equals("Gold") ){
                if(femaleGoldMap.containsKey(name)){
                    int count=femaleGoldMap.get(name);
                    femaleGoldMap.put(name,count+1);
                }else{
                    femaleGoldMap.put(name,1);
                }
            }
        }
        ArrayList<Map.Entry<String,Integer>> mapList= new ArrayList<>(femaleGoldMap.entrySet());
        mapList.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));
        HashMap<String,Integer> answerMap= new HashMap<>();
        answerMap.put(mapList.get(0).getKey(),mapList.get(0).getValue());
        System.out.println(answerMap);
    }

    public static void getGoldWinnerOfFootBall(List<Athlete> athleteList) {
         HashMap<String,Integer> goldMedalInFootballMap= new HashMap<>();
        for (Athlete athlete:athleteList){
            String medal= athlete.getMedal();
            String sportName=athlete.getSport();
            if(medal.equals("Gold") && sportName.contains("Football")){
             String name= athlete.getName();
             if(goldMedalInFootballMap.containsKey(name)){
                 int count=goldMedalInFootballMap.get(name);
                 goldMedalInFootballMap.put(name,count+1);
             }else {
                 goldMedalInFootballMap.put(name,1);
             }

            }
        }
        System.out.println(goldMedalInFootballMap);
    }

    public static void eventWiseNumberOfGoldSilverBronzeMedalIn1980(List<Athlete> athleteList) {
        HashMap<String,HashMap<String,Integer>>eventWiseMap= new HashMap<>();
         for(Athlete athlete:athleteList){
             String medal= athlete.getMedal();
            if(!medal.equals("NA")){
                if(athlete.getYear()==1980){
                    String eventName=athlete.getEvent();
                    if(eventWiseMap.containsKey(eventName)){
                        HashMap<String, Integer> valueMap = eventWiseMap.get(eventName);
                         if(valueMap.containsKey(medal)){
                             int count= valueMap.get(medal);
                             valueMap.put(medal,count+1);
                         }else{
                             valueMap.put(medal,1);
                         }
                    }else{
                        HashMap<String,Integer>map= new HashMap<>();
                        map.put(medal,1);
                        eventWiseMap.put(eventName,map);
                    }
                }
            }
         }
        System.out.println(eventWiseMap);
    }

    public static void playerWonGoldMedalIn1980AndAgeIsLessThan30(List<Athlete> athleteList) {
        HashMap<String,Integer> goldMedalWinnerMap= new HashMap<>();

        for (Athlete athlete:athleteList){
            if(athlete.getYear()==1980 && athlete.getAge()<30){
                String playerName=athlete.getName();
                if(goldMedalWinnerMap.containsKey(playerName)){
                    int count=goldMedalWinnerMap.get(playerName);
                    goldMedalWinnerMap.put(playerName,count+1);
                }else{
                    goldMedalWinnerMap.put(playerName,1);
                }

            }
        }
        System.out.println(goldMedalWinnerMap);

    }

    public static void displayYearWiseNumberOfGoldMedalsWonByEachPlayer(List<Athlete> athleteList) {

         HashMap<Integer,HashMap<String,Integer>> playerWonMedalMap= new HashMap<>();
        for(Athlete athlete:athleteList){
            int year=athlete.getYear();
            String playerName=athlete.getName();

            if(playerWonMedalMap.containsKey(year)){
                HashMap<String,Integer> valueMap=playerWonMedalMap.get(year);
                 if(valueMap.containsKey(playerName)){
                     int count=valueMap.get(playerName);
                       valueMap.put(playerName,count+1);
                 }else{
                     valueMap.put(playerName,1);
                 }

            }else{
               HashMap<String,Integer> valueMap= new HashMap<>();
               valueMap.put(playerName,1);
               playerWonMedalMap.put(year,valueMap);

            }
        }
        System.out.println(playerWonMedalMap);

    }

    public static List<Athlete> getTheDatafromAthlete() {
        String path = "/home/shashi/IdeaProjects/Olympic/src/main/data/athlete_events.csv";
        List<Athlete> athleteList = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(path))) {
            List<String[]> lines = reader.readAll();
            lines.remove(0);

            for (String[] values : lines) {
                Athlete athlete = new Athlete();
                athlete.setId(parseValue(values[ID]));
                athlete.setName(values[NAME]);
                athlete.setSex(parseCharValue(values[SEX]));
                athlete.setAge(parseValue(values[AGE]));
                athlete.setHeight(parseValue(values[HEIGHT]));
                athlete.setWeight(parseValue(values[WEIGHT]));
                athlete.setTeam(values[TEAM]);
                athlete.setNoc(values[NOC]);
                athlete.setGames(values[GAMES]);
                athlete.setYear(parseValue(values[YEAR]));
                athlete.setSeason(values[SEASON]);
                athlete.setCity(values[CITY]);
                athlete.setSport(values[SPORT]);
                athlete.setEvent(values[EVENT]);
                athlete.setMedal(values[MEDAL]);

                athleteList.add(athlete);
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        return athleteList;
    }


    private static int parseValue(String value) {
        if (value.equals("NA")) {
            return 0;
        } else {
            try {
                return (int) Double.parseDouble(value.trim());
            } catch (NumberFormatException e) {

                return (int) Math.round(Double.parseDouble(value.trim()));
            }
        }
    }


    private static char parseCharValue(String value) {
        return value.equals("NA") || value.isEmpty() ? ' ' : value.charAt(0);
    }
}
