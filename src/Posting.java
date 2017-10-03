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

import java.util.LinkedList;

// outer class
public class Posting implements Comparable<Posting>
{
    // instance variables
    private final int fileID;
    private final LinkedList<Position> positionList;

    // constructor
    public Posting(int fileID)
    {
        // initialize instance variables
        this.fileID = fileID;
        positionList = new LinkedList<Position>();
    }

    public int getFileID()
    {
        return fileID;
    }

    public void addPosition(int linePos, int wordPos)
    {
        positionList.add(new Position(linePos, wordPos));
    }

    public String getPositionList()
    {
        String result = positionList.toString();
        return result.substring(1, result.length() - 1);
    }

    public int getHits()
    {
        return positionList.size();
    }

    @Override
    public int compareTo(Posting other)
    {
        // used by sort() method
        return other.getHits() - this.getHits();
    }

    @Override
    public boolean equals(Object o)
    {
        // used by indexOf() method
        if (o instanceof Posting)
        {
            Posting other = (Posting) o;
            return other.fileID == this.fileID;
        }
        return false;
    }

    // inner class
    private class Position
    {
        // instance variables
        private final int linePos;
        private final int wordPos;

        // constructor
        public Position(int linePos, int wordPos)
        {
            // initialize instance variables
            this.linePos = linePos;
            this.wordPos = wordPos;
        }

        @Override
        public String toString()
        {
            // used by getPositionList() method
            return "[" + linePos + ":" + wordPos + "]";
        }
    }
}
