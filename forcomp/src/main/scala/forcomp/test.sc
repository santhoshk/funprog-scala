package forcomp

object test {
  //println("Welcome to the Scala worksheet")
  //Anagrams.wordOccurrences("test")
  //Anagrams.sentenceAnagrams(List("act"))
  //Anagrams.sentenceAnagrams(List("I", "am"))
  Anagrams.sentenceAnagrams(List("am","to"))      //>  (a,1)  (m,1)  (o,1)  (t,1) 
                                                  //| inside sentenceAnagrams1. current stack :  (a,1)  (m,1)  (o,1)  (t,1) 
                                                  //| 	print various combs
                                                  //|  (a,1)  (m,1)  (o,1)  (t,1) 
                                                  //|  (a,1)  (m,1)  (o,1) 
                                                  //|  (a,1)  (m,1)  (t,1) 
                                                  //|  (a,1)  (m,1) 
                                                  //|  (a,1)  (o,1)  (t,1) 
                                                  //|  (a,1)  (o,1) 
                                                  //|  (a,1)  (t,1) 
                                                  //|  (a,1) 
                                                  //|  (m,1)  (o,1)  (t,1) 
                                                  //|  (m,1)  (o,1) 
                                                  //|  (m,1)  (t,1) 
                                                  //|  (m,1) 
                                                  //|  (o,1)  (t,1) 
                                                  //|  (o,1) 
                                                  //|  (t,1) 
                                                  //|  () 
                                                  //| getAllWordsForComb :  (a,1)  (m,1)  (o,1)  (t,1) atom moat
                                                  //| getAllWordsForComb :  (a,1)  (m,1)  (o,1) Mao
                                                  //| getAllWordsForComb :  (a,1)  (m,1)  (t,1) mat
                                                  //| getAllWordsForComb :  (a,1)  (m,1) am
                                                  //| getAllWordsForComb :  (a,1)  (o,1)  (t,1) oat
                                                  //| getAllWordsForComb :  (a,1)  (o,1) 
                                                  //| getAllWordsForComb :  (a,1)  (t,1) at
                                                  //| getAllWordsForComb :  (a,1) 
                                                  //| getAllWordsForComb :  (m,1)  (o,1)  (t,1) Tom
                                                  //| getAllWordsForComb :  (m,1)  (o,1) 
                                                  //| getAllWordsForComb :  (m,1)  (t,1) 
                                                  //| getAllWordsForComb :  (m,1) 
                                                  //| getAl
                                                  //| Output exceeds cutoff limit.
}