# Inverted Index (SJSU CS 49J, SPRING 2015)

![Icon](https://i.imgur.com/34x2Ahl.jpg)

## Purpose:
To explore one of the core elements of an information retrieval system, the inverted index. An inverted index is a mapping of words to their location in a set of files. Most modern search engines utilize some form of an inverted index to process user-submitted queries. In its most basic form, an inverted index is a simple hash table (hash map) which maps words in the files to some sort of file identifier, called a posting. This topic was chosen for its relevancy with hot companies like Google and Facebook.

## Implementation:
The data structures primarily being used are hash maps and linked lists. The benefit of hash maps is O(1) time complexity for inserting and querying data, which is crucial for an inverted index to achieve rapid results. The benefit of linked lists is O(1) time complexity for appending data (i.e. inserting data to the end), sorting capabilities, and no wasted space. In this program, the hash map is a collection of words, a word is a collection of postings (using a linked list), and a posting is a collection of positions (also using a linked list). The program is relying on the Java Collection Framework (JCF). 

## Instructions:
The inputs for this program are file names to be indexed and strings to be queried. Currently, these arguments are hardcoded in the main method. The output for this program will display the results of files inputted and queries evaluated. See attached source code for “Tester.java” and its output, “Output.txt”.

## Limitations:
The known limitation that will not be address in this program is the ability to index non-plaintext files and files using some sort of markup language.

## References:
* http://en.wikipedia.org/wiki/Inverted_index
* http://xlinux.nist.gov/dads/HTML/invertedIndex.html 
* http://www.elastic.co/guide/en/elasticsearch/guide/master/inverted-index.html
* http://nlp.stanford.edu/IR-book/html/htmledition/a-first-take-at-building-an-inverted-index-1.html

## License
```
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
```
