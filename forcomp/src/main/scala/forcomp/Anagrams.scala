package forcomp

import common._
//import sun.security.util.Length
//import scala.util.parsing.combinator.Head

object Anagrams {

  /** A word is simply a `String`. */
  type Word = String

  /** A sentence is a `List` of words. */
  type Sentence = List[Word]

  /**
   * `Occurrences` is a `List` of pairs of characters and positive integers saying
   *  how often the character appears.
   *  This list is sorted alphabetically w.r.t. to the character in each pair.
   *  All characters in the occurrence list are lowercase.
   *
   *  Any list of pairs of lowercase characters and their frequency which is not sorted
   *  is **not** an occurrence list.
   *
   *  Note: If the frequency of some character is zero, then that character should not be
   *  in the list.
   */
  type Occurrences = List[(Char, Int)]

  /**
   * The dictionary is simply a sequence of words.
   *  It is predefined and obtained as a sequence using the utility method `loadDictionary`.
   */
  val dictionary: List[Word] = loadDictionary

  /**
   * Converts the word into its character occurence list.
   *
   *  Note: the uppercase and lowercase version of the character are treated as the
   *  same character, and are represented as a lowercase character in the occurrence list.
   */
  def wordOccurrences(w: Word): Occurrences = {
    (w.toLowerCase().groupBy(e => e) map {
      case (x, y) => (x, y.length())
    }).toList.sortWith((x, y) => x._1 < y._1)
  }
    

  /** Converts a sentence into its character occurrence list. */
  def sentenceOccurrences(s: Sentence): Occurrences = {
    wordOccurrences(s.flatten.filter(x => x.isLetter).mkString)
  }

  /**
   * The `dictionaryByOccurrences` is a `Map` from different occurrences to a sequence of all
   *  the words that have that occurrence count.
   *  This map serves as an easy way to obtain all the anagrams of a word given its occurrence list.
   *
   *  For example, the word "eat" has the following character occurrence list:
   *
   *     `List(('a', 1), ('e', 1), ('t', 1))`
   *
   *  Incidentally, so do the words "ate" and "tea".
   *
   *  This means that the `dictionaryByOccurrences` map will contain an entry:
   *
   *    List(('a', 1), ('e', 1), ('t', 1)) -> Seq("ate", "eat", "tea")
   *
   */
  lazy val dictionaryByOccurrences: Map[Occurrences, List[Word]] = {
    dictionary groupBy wordOccurrences
  }

  /** Returns all the anagrams of a given word. */
  def wordAnagrams(word: Word): List[Word] = {
    (dictionaryByOccurrences withDefaultValue List())(wordOccurrences(word))
  }

  /**
   * Returns the list of all subsets of the occurrence list.
   *  This includes the occurrence itself, i.e. `List(('k', 1), ('o', 1))`
   *  is a subset of `List(('k', 1), ('o', 1))`.
   *  It also include the empty subset `List()`.
   *
   *  Example: the subsets of the occurrence list `List(('a', 2), ('b', 2))` are:
   *
   *    List(
   *      List(),
   *      List(('a', 1)),
   *      List(('a', 2)),
   *      List(('b', 1)),
   *      List(('a', 1), ('b', 1)),
   *      List(('a', 2), ('b', 1)),
   *      List(('b', 2)),
   *      List(('a', 1), ('b', 2)),
   *      List(('a', 2), ('b', 2))
   *    )
   *
   *  Note that the order of the occurrence list subsets does not matter -- the subsets
   *  in the example above could have been displayed in some other order.
   */
  def combinations(occurrences: Occurrences): List[Occurrences] = {
    /*def printOcc(occ: Occurrences): Unit = {
  		occ match {
  			case Nil => println(" EmptyList ")
  			case x::Nil => println(" (" + x._1 + "," + x._2 + ") ")
  			case x::xs => {
  				println(" (" + x._1 + "," + x._2 + ") ")
  				printOcc(xs)
  			}
  		}
  	}
  
  	def printListOcc(occs:List[Occurrences]): Unit = {
  		occs match {
  			case Nil => println (" Empty1 ");
  			case x::Nil => printOcc(x)
  			case x::xs => {
  				printOcc(x)
  				printListOcc(xs)
  			}
  		}
  	}*/

    def getCombs(combs: List[Occurrences], ch: Char, count: Int): List[Occurrences] = {
      for {
        comb <- combs
        newCount <- 1 to count
      } yield ((ch, newCount) :: comb)
    }

    if (occurrences == List()) {
      //println ("entering combinations : EMPTY")
      val ret = List(List())
      //printListOcc(ret)
      ret
    } else {
      //println ("entering combinations : " + occurrences.length + " : " + occurrences.head._1 + " : " + occurrences.head._2 + " : " + occurrences.tail.length)
      val combs: List[Occurrences] = combinations(occurrences.tail)
      val cx = getCombs(combs, occurrences.head._1, occurrences.head._2)
      cx ++ combs
    }
  }

  /**
   * Subtracts occurrence list `y` from occurrence list `x`.
   *
   *  The precondition is that the occurrence list `y` is a subset of
   *  the occurrence list `x` -- any character appearing in `y` must
   *  appear in `x`, and its frequency in `y` must be smaller or equal
   *  than its frequency in `x`.
   *
   *  Note: the resulting value is an occurrence - meaning it is sorted
   *  and has no zero-entries.
   */
  def subtract(x: Occurrences, y: Occurrences): Occurrences = {
    def subTerm(terms: Map[Char, Int], term: (Char, Int)): Map[Char, Int] = {
      val (ch, count) = term
      if (count == terms(ch)) {
        terms.filter(x => x._1 != ch)
      } else {
        terms.updated(ch, terms(ch) - count)
      }
    }

    val xMap = x.toMap
    val yMap = y.toMap
    yMap.foldLeft(xMap)(subTerm).toList.sortWith((x, y) => x._1 < y._1)    
  }

  /**
   * Returns a list of all anagram sentences of the given sentence.
   *
   *  An anagram of a sentence is formed by taking the occurrences of all the characters of
   *  all the words in the sentence, and producing all possible combinations of words with those characters,
   *  such that the words have to be from the dictionary.
   *
   *  The number of words in the sentence and its anagrams does not have to correspond.
   *  For example, the sentence `List("I", "love", "you")` is an anagram of the sentence `List("You", "olive")`.
   *
   *  Also, two sentences with the same words but in a different order are considered two different anagrams.
   *  For example, sentences `List("You", "olive")` and `List("olive", "you")` are different anagrams of
   *  `List("I", "love", "you")`.
   *
   *  Here is a full example of a sentence `List("Yes", "man")` and its anagrams for our dictionary:
   *
   *    List(
   *      List(en, as, my),
   *      List(en, my, as),
   *      List(man, yes),
   *      List(men, say),
   *      List(as, en, my),
   *      List(as, my, en),
   *      List(sane, my),
   *      List(Sean, my),
   *      List(my, en, as),
   *      List(my, as, en),
   *      List(my, sane),
   *      List(my, Sean),
   *      List(say, men),
   *      List(yes, man)
   *    )
   *
   *  The different sentences do not have to be output in the order shown above - any order is fine as long as
   *  all the anagrams are there. Every returned word has to exist in the dictionary.
   *
   *  Note: in case that the words of the sentence are in the dictionary, then the sentence is the anagram of itself,
   *  so it has to be returned in this list.
   *
   *  Note: There is only one anagram of an empty sentence.
   */
  def sentenceAnagrams1(sOcc: Occurrences): List[Sentence] = {
    
    def printWords(words : List[Word]) = words mkString " "

    def insertAt(sen: Sentence, i: Int, w: Word): Sentence = {
      def insertAcc(senAcc: Sentence, senRem: Sentence, i: Int, w: Word): Sentence = {
        if (i == 0) senAcc ++ List(w) ++ senRem
        else insertAcc(senAcc ++ List(senRem.head), senRem.tail, i - 1, w)
      }

      val retsen = insertAcc(List(), sen, i, w)
      //print ("sentence formed : " + retsen + "\n")
      retsen
    }
    
    def getAllWordsForComb(wComb : Occurrences) : List[Word] = {
      val allWords = (dictionaryByOccurrences withDefaultValue List())(wComb)
      //print("getAllWordsForComb : ")
      //printOcc(wComb)
      //print ( allWords mkString(" "))
      //print("\n")
      allWords
    }

    if (sOcc == List()) {
      //print ("inside sentenceAnagrams1. sOcc = EmptyList\n")
      List(List())
    }
    //else if (sentence == List(List())) List(List())
    else {      
      //print("inside sentenceAnagrams1. current stack : ")      
      //printOcc(sOcc)
      //println()
      val combs = combinations(sOcc);
      //print("\tprint various combs\n")
      //printListOcc(combs)
      for {
        wordComb <- combs
        //allWords = (dictionaryByOccurrences withDefaultValue List())(wordComb)
        allWords = getAllWordsForComb(wordComb)
        //sa2
        //printWords(allWords)        
        ws <- allWords//(dictionaryByOccurrences withDefaultValue List())(wordComb)
        if ws != List()        
        subSentence <- sentenceAnagrams1(subtract(sOcc, wordComb))
        //i <- 0 to subSentence.length
      } yield insertAt(subSentence, 0, ws)
    }
  }

  def sentenceAnagrams(sentence: Sentence): List[Sentence] = {
    val occs = sentenceOccurrences(sentence)
    //printOcc(occs)
    //println()
    val res = sentenceAnagrams1(occs)
    //print(" \nRESULT\n ")
    //res.foreach(x => println(x.mkString(" ")))
    res
  }
  
  def printOcc(occ: Occurrences): Unit = {
  		occ match {
  			case Nil => print(" () \n")
  			case x::Nil => print(" (" + x._1 + "," + x._2 + ") ")
  			case x::xs => {
  				print(" (" + x._1 + "," + x._2 + ") ")
  				printOcc(xs)
  			}
  		}
  	}
  
  	def printListOcc(occs:List[Occurrences]): Unit = {
  		occs match {
  			case Nil => print("Empty List(List) \n");
  			case x::Nil => printOcc(x)
  			case x::xs => {
  				printOcc(x)
  				println()
  				printListOcc(xs)
  			}
  		}
  	}

}
