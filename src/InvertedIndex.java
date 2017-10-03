/*
 MIT License

 Copyright (c) 2017 Rohan Kumar

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class InvertedIndex
{
    // constant
    public static final String REGEX = "[^a-zA-Z0-9']+";

    // instance variables
    private final File[] fileArray;
    private final HashMap<String, LinkedList<Posting>> hashMap;

    // constructor
    public InvertedIndex(String[] fileNameArray) throws IOException
    {
        // initialize instance variables
        fileArray = new File[fileNameArray.length];
        hashMap = new HashMap<String, LinkedList<Posting>>();

        System.out.println("Indexed:");

        for (int fileID = 0; fileID < fileArray.length; fileID++)
        {
            System.out.print("\t" + (fileID + 1));
            fileArray[fileID] = new File(fileNameArray[fileID]);    // create new file and add it to fileArray
            indexFile(fileID);
        }
    }

    // index file
    private void indexFile(int fileID) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(fileArray[fileID]));  // open reader

        // word position
        int linePos = 0;
        int wordPos;

        // stat counters
        int wordTotal = 0;
        int wordUnique = 0;

        for (String line = reader.readLine(); line != null; line = reader.readLine())   // read file, line by line
        {
            linePos++;
            wordPos = 0;

            for (String word : line.split(REGEX))   // split line, word by word
            {
                word = word.trim().toLowerCase();   // normalize word

                if (word.length() > 1)              // ignore whitespaces and single character words
                {
                    wordPos++;
                    wordTotal++;

                    // step 1 - lookup existing word in hashMap
                    LinkedList<Posting> value = hashMap.get(word);
                    if (value == null)
                    {
                        wordUnique++;                       // if word does not exist in hashMap:
                        value = new LinkedList<Posting>();  // 1) create new word...
                        hashMap.put(word, value);           // 2) ...and add it to hashMap
                    }

                    // step 2 - lookup existing posting in word
                    int index = value.indexOf(new Posting(fileID));
                    Posting posting;
                    if (index == -1)
                    {                                       // if posting does not exist in word:
                        posting = new Posting(fileID);      // 1) create new posting...
                        value.add(posting);                 // 2) ...and add it to word
                    }
                    else
                        posting = value.get(index);

                    // step 3 - add position to posting
                    posting.addPosition(linePos, wordPos);
                }
            }
        }

        reader.close();     // close reader

        // output results
        System.out.println("\t File: \"" + fileArray[fileID].getName() + "\"");
        System.out.println("\t\t Stat: " + wordUnique + " unique / " + wordTotal + " total\n");
    }

    // query index
    public void queryIndex(String word)
    {
        System.out.println("Queried: \"" + word + "\"");

        word = word.trim().toLowerCase();   // normalize word

        LinkedList<Posting> value = hashMap.get(word);  // lookup word in hashMap
        if (value != null)                              // word found in hashMap
        {
            LinkedList<Posting> postingList = new LinkedList<Posting>(value);   // shallow copy posting list...
            Collections.sort(postingList);                                      // ...and sort by relevance

            int rankID = 0;

            for (Posting posting : postingList)
            {
                // output results
                System.out.println("\t" + (++rankID) + "\t File: \"" + fileArray[posting.getFileID()] + "\"");
                System.out.println("\t\t Hits: " + posting.getHits() + "x @ " + posting.getPositionList() + "\n");
            }
        }
        else                                            // word not found in hashMap
            System.out.println("\t\t !!!N/A!!!\n");
    }
}
