package week6

import sun.reflect.generics.reflectiveObjects.NotImplementedException

object week6 {
  List(0,1,2,1,0).groupBy(e => e)                 //> res0: scala.collection.immutable.Map[Int,List[Int]] = Map(2 -> List(2), 1 ->
                                                  //|  List(1, 1), 0 -> List(0, 0))
  "anagramn".toList.groupBy(e => e) map {
  	case (x,y) => (x,y.length)
  } toList                                        //> res1: List[(Char, Int)] = List((n,2), (a,3), (m,1), (g,1), (r,1))
  
  "anagramn".groupBy(e => e) map {
  	case (x,y) => (x,y.length())
  } toList                                        //> res2: List[(Char, Int)] = List((n,2), (a,3), (m,1), (g,1), (r,1))
  
  type Word = String
  type Occurrences = List[(Char, Int)]
  type Sentence = List[Word]
  
  def wordOccurrences(w: Word): Occurrences = {
    println("word is " + w)
    (w.toLowerCase().groupBy(e => e) map {
      case (x,y) => (x,y.length())
    }).toList.sortWith((x,y) => x._1 < y._1)
  }                                               //> wordOccurrences: (w: week6.week6.Word)week6.week6.Occurrences
  
  wordOccurrences("testhelloworld")               //> word is testhelloworld
                                                  //| res3: week6.week6.Occurrences = List((d,1), (e,2), (h,1), (l,3), (o,2), (r,1
                                                  //| ), (s,1), (t,2), (w,1))
  wordOccurrences("RobeRT")                       //> word is RobeRT
                                                  //| res4: week6.week6.Occurrences = List((b,1), (e,1), (o,1), (r,2), (t,1))
  wordOccurrences("")                             //> word is 
                                                  //| res5: week6.week6.Occurrences = List()
  
  List("hello","world")                           //> res6: List[String] = List(hello, world)
  List("hello","world").flatten.mkString          //> res7: String = helloworld
  List("hello","world123").flatten.filter(x => x.isLetter)
                                                  //> res8: List[Char] = List(h, e, l, l, o, w, o, r, l, d)
  List("hello","world","123").flatten.filter(x => x.isLetter).toString
                                                  //> res9: String = List(h, e, l, l, o, w, o, r, l, d)
  List("hello","world").flatten.filter(x => x.isLetter).toString()
                                                  //> res10: String = List(h, e, l, l, o, w, o, r, l, d)
  
  
  def sentenceOccurrences(s: Sentence): Occurrences = {
  	wordOccurrences(s.flatten.filter(x => x.isLetter).mkString)
  }                                               //> sentenceOccurrences: (s: week6.week6.Sentence)week6.week6.Occurrences
  
  val list = List("hello","world")                //> list  : List[String] = List(hello, world)
  sentenceOccurrences(list)                       //> word is helloworld
                                                  //| res11: week6.week6.Occurrences = List((d,1), (e,1), (h,1), (l,3), (o,2), (r
                                                  //| ,1), (w,1))
  
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
  	
  	def getCombs(combs:List[Occurrences], ch:Char, count:Int) : List[Occurrences] = {
  		for {
				comb <- combs
 				newCount <- 1 to count
  		} yield ((ch, newCount) :: comb) //++ (comb) //yield an occurrence, not a List[Occurence]
  	}
  	
  	if(occurrences == List()) {
  		println ("entering combinations : EMPTY")
  		val ret = List(List())
  		//printListOcc(ret)
  		return ret
  	} else {
  		println ("entering combinations : " + occurrences.length + " : " + occurrences.head._1 + " : " + occurrences.head._2 + " : " + occurrences.tail.length)
  		val combs : List[Occurrences] = combinations(occurrences.tail)
  		val cx = getCombs(combs, occurrences.head._1, occurrences.head._2)
  		cx ++ combs
  	}
  }                                               //> combinations: (occurrences: week6.week6.Occurrences)List[week6.week6.Occurr
                                                  //| ences]
  
  //combinations(List(('a', 2), ('b', 2)))
  combinations(List())                            //> entering combinations : EMPTY
                                                  //| res12: List[week6.week6.Occurrences] = List(List())
  combinations(List(('b',1)))                     //> entering combinations : 1 : b : 1 : 0
                                                  //| entering combinations : EMPTY
                                                  //| res13: List[week6.week6.Occurrences] = List(List((b,1)), List())
  combinations(List(('a',2),('b',2)))             //> entering combinations : 2 : a : 2 : 1
                                                  //| entering combinations : 1 : b : 2 : 0
                                                  //| entering combinations : EMPTY
                                                  //| res14: List[week6.week6.Occurrences] = List(List((a,1), (b,1)), List((a,2),
                                                  //|  (b,1)), List((a,1), (b,2)), List((a,2), (b,2)), List((a,1)), List((a,2)), 
                                                  //| List((b,1)), List((b,2)), List())
  
  def sub1(x: Occurrences, y: Occurrences): Occurrences = {
  	
  	def subTerm(terms: Map[Char,Int],term : (Char,Int)) : Map[Char,Int] = {
  		val (ch,count) = term
  		if(count == terms(ch)) {
  			terms.filter(x => x._1 !=ch)
  		} else {
  			terms.updated(ch, terms(ch)-count)
  		}
  	}
  	
  	val xMap = x.toMap
  	val yMap = y.toMap
  	yMap.foldLeft(xMap)(subTerm).toList
  	
  	
  }                                               //> sub1: (x: week6.week6.Occurrences, y: week6.week6.Occurrences)week6.week6.O
                                                  //| ccurrences
  
  val x = List(('a',1))                           //> x  : List[(Char, Int)] = List((a,1))
  val y = List(('a',1))                           //> y  : List[(Char, Int)] = List((a,1))
  sub1(x,y)                                       //> res15: week6.week6.Occurrences = List()
  
  def subtract(x: Occurrences, y: Occurrences): Occurrences = {
    def subTerm(terms: Map[Char,Int],term : (Char,Int)) : Map[Char,Int] = {
  		val (ch,count) = term
  		if(count == terms(ch)) {
  			terms.filter(x => x._1 !=ch)
  		} else {
  			terms.updated(ch, terms(ch)-count)
  		}
  	}
  	
  	val xMap = x.toMap
  	val yMap = y.toMap
  	yMap.foldLeft(xMap)(subTerm).toList
  }                                               //> subtract: (x: week6.week6.Occurrences, y: week6.week6.Occurrences)week6.wee
                                                  //| k6.Occurrences
  
  
}