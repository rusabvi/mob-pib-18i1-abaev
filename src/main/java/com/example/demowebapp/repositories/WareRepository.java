package com.example.demowebapp.repositories;

import com.example.demowebapp.domain.Person;
import com.example.demowebapp.domain.Ware;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WareRepository {
    private final List<Ware> wares = new ArrayList<>();

    private String[][] loadWareArray(String filePath) {
        File file = new File(filePath);
        if(!file.exists())
            createWareArrayInFile(filePath);
        String[][] wareArray = new String[5][3];
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            for (int i = 0; i < 5; i++) {
                String[] intermediateArray = bufferedReader.readLine().trim().split(" ");
                for (int j = 0; j < 3; j++)
                    wareArray[i][j] = intermediateArray[j];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wareArray;
    }

    private void createWareArrayInFile(String filePath) {
        String [][] massive = {
                {"plate", "200", "10"},
                {"chocolate", "120", "20"},
                {"fruit", "30", "200"},
                {"vegetable", "25", "250"},
                {"drinking", "90", "25"}
        };
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
            for (String[] m : massive) {
                for (String n : m)
                    bufferedWriter.write(n + " ");
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public WareRepository() {
        String filePath = "C://Files//Massive.txt";
        String [][] wareArray = loadWareArray(filePath);
        for (int i = 0; i < wareArray.length; i ++)
            wares.add(new Ware(
                    i,
                    wareArray[i][0],
                    Integer.parseInt(wareArray[i][1].trim()),
                    Integer.parseInt(wareArray[i][2].trim())
            ));
    }

    public List<Ware> getAllWares() { return wares; }

    public Ware getWareByWareId(int wareId) {
        for (Ware w : wares)
            if (w.getWareId() == wareId)
                return w;
        return null;
    }
}