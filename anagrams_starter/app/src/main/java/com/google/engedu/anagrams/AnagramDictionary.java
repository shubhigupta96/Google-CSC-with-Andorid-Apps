package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    ArrayList<String> word_list;
    HashSet<String> wordSet;
    HashMap<String,ArrayList> lettersToWord;

    public AnagramDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        String line;
        word_list=new ArrayList<String>();
        wordSet=new HashSet<String>();
        lettersToWord=new HashMap<String,ArrayList>();
        while((line = in.readLine()) != null) {
            String word = line.trim();
            word_list.add(word);
            wordSet.add(word);
            String sorted_word=sortLetters(word);
            if(lettersToWord.containsKey(sorted_word))
            {
                lettersToWord.get(sorted_word).add(word);
            }
            else
            {
                ArrayList<String> arr_list=new ArrayList();
                arr_list.add(word);
                lettersToWord.put(sorted_word,arr_list);
            }
        }
    }

    public boolean isGoodWord(String word, String base) {
        if(wordSet.contains(word))
        {
            if(!word.contains(base))
            {
                return true;
            }
        }

        return false;
    }
    public String sortLetters(String a)
    {
        char[] temp=a.toCharArray();
        Arrays.sort(temp);
        String sorted=String.valueOf(temp);
        return sorted;
    }
    public ArrayList<String> getAnagrams(String targetWord) {
        ArrayList<String> result = new ArrayList<String>();
        for(int i=0;i<word_list.size();i++)
        {
            String word=word_list.get(i);
            if(word.length()==targetWord.length())
            {
                String sorted=sortLetters(word);
                if(sorted.matches(sortLetters(targetWord)))
                {
                    result.add(word);
                }
            }

        }
        return result;
    }

    public ArrayList<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        for(int i=97;i<=122;i++)
        {
            char a=(char)i;
            String result_new=word+a;
            String sorted_result=sortLetters(result_new);
            if(lettersToWord.containsKey(sorted_result))
            {
                for(int j=0;j<(lettersToWord.get(sorted_result)).size();j++)
                {
                    String fetched_word=(String)lettersToWord.get(sorted_result).get(j);
                    if(!word.matches(fetched_word)&& !fetched_word.contains(word))
                    {

                        result.add(fetched_word);
                    }
                }
            }
        }
        return result;






    }

    public String pickGoodStarterWord() {
       // Random r=new Random();
        int max= word_list.size()-1;
        int min=0;
        int random_index=min+ (int)(Math.random()*((max-min)+1));
        boolean flag=false;





    }
}
