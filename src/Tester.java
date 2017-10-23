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

import java.io.IOException;

public class Tester
{
    public static void main(String[] args) throws IOException
    {
        String[] fileNameArray = {"jackbeanstalk.txt", "threepigs.txt", "goldilocks.txt"};
        InvertedIndex index = new InvertedIndex(fileNameArray);

        String[] queryArray = {"little", "bed", "money", "fairy"};
        for (String query : queryArray)
            index.queryIndex(query);

        index.queryIndexAND("little", "bed");

        index.queryIndexAND("little", "money");

        index.queryIndexAND("bed", "money");

        index.queryIndexAND("little", "fairy");

        index.queryIndexNOT("little", "bed");

        index.queryIndexNOT("little", "money");

        index.queryIndexNOT("bed", "money");

        index.queryIndexNOT("little", "fairy");
    }
}