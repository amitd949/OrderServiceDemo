package com.example.demo;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ElectionData {

        Map<String,Integer> voteData= new HashMap<>();
    private String leader;
    private int maxVotes = 0;

    public static void main(String[] args) {
        ElectionData electionData= new ElectionData();

        electionData.addVote("A");
        electionData.addVote("B");
        electionData.addVote("A");
        electionData.addVote("B");
        electionData.addVote("B");
        electionData.addVote("B");

        electionData.getLeader();


    }

    public void addVote(String candidateName){

       int votes=   voteData.merge(candidateName, 1, Integer::sum);
        if (votes > maxVotes) {
            maxVotes = votes;
            leader = candidateName;
        }
    }

    public Integer getVote(String candidateName){
        return  voteData.get(candidateName);
    }

    public String getLeader(){
        System.out.println(" voteData"+ voteData);
        LinkedHashMap<String, Integer> collect = voteData.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));

        System.out.println(" collect "+ collect);
        Map.Entry<String, Integer> leaderName= collect.entrySet().stream().findFirst().get();


        System.out.println(" leader name "+ leaderName + " leader "+ leader);
        return leader;
    }

}
