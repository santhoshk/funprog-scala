package week6

import sun.reflect.generics.reflectiveObjects.NotImplementedException

object week6 {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(135); val res$0 = 
  List(0,1,2,1,0).groupBy(e => e);System.out.println("""res0: scala.collection.immutable.Map[Int,List[Int]] = """ + $show(res$0));$skip(83); val res$1 = 
  "anagramn".toList.groupBy(e => e) map {
  	case (x,y) => (x,y.length)
  } toList;System.out.println("""res1: List[(Char, Int)] = """ + $show(res$1));$skip(81); val res$2 = 
  
  "anagramn".groupBy(e => e) map {
  	case (x,y) => (x,y.length())
  } toList
  
  type Word = String
  type Occurrences = List[(Char, Int)]
  type Sentence = List[Word];System.out.println("""res2: List[(Char, Int)] = """ + $show(res$2));$skip(298); 
  
  def wordOccurrences(w: Word): Occurrences = {
    println("word is " + w)
    (w.toLowerCase().groupBy(e => e) map {
      case (x,y) => (x,y.length())
    }).toList.sortWith((x,y) => x._1 < y._1)
  };System.out.println("""wordOccurrences: (w: week6.week6.Word)week6.week6.Occurrences""");$skip(39); val res$3 = 
  
  wordOccurrences("testhelloworld");System.out.println("""res3: week6.week6.Occurrences = """ + $show(res$3));$skip(28); val res$4 = 
  wordOccurrences("RobeRT");System.out.println("""res4: week6.week6.Occurrences = """ + $show(res$4));$skip(22); val res$5 = 
  wordOccurrences("");System.out.println("""res5: week6.week6.Occurrences = """ + $show(res$5));$skip(27); val res$6 = 
  
  List("hello","world");System.out.println("""res6: List[String] = """ + $show(res$6));$skip(41); val res$7 = 
  List("hello","world").flatten.mkString;System.out.println("""res7: String = """ + $show(res$7));$skip(59); val res$8 = 
  List("hello","world123").flatten.filter(x => x.isLetter);System.out.println("""res8: List[Char] = """ + $show(res$8));$skip(71); val res$9 = 
  List("hello","world","123").flatten.filter(x => x.isLetter).toString;System.out.println("""res9: String = """ + $show(res$9));$skip(67); val res$10 = 
  List("hello","world").flatten.filter(x => x.isLetter).toString();System.out.println("""res10: String = """ + $show(res$10));$skip(130); 
  
  
  def sentenceOccurrences(s: Sentence): Occurrences = {
  	wordOccurrences(s.flatten.filter(x => x.isLetter).mkString)
  };System.out.println("""sentenceOccurrences: (s: week6.week6.Sentence)week6.week6.Occurrences""");$skip(38); 
  
  val list = List("hello","world");System.out.println("""list  : List[String] = """ + $show(list ));$skip(28); val res$11 = 
  sentenceOccurrences(list);System.out.println("""res11: week6.week6.Occurrences = """ + $show(res$11));$skip(1292); 
  
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
  };System.out.println("""combinations: (occurrences: week6.week6.Occurrences)List[week6.week6.Occurrences]""");$skip(69); val res$12 = 
  
  //combinations(List(('a', 2), ('b', 2)))
  combinations(List());System.out.println("""res12: List[week6.week6.Occurrences] = """ + $show(res$12));$skip(30); val res$13 = 
  combinations(List(('b',1)));System.out.println("""res13: List[week6.week6.Occurrences] = """ + $show(res$13));$skip(38); val res$14 = 
  combinations(List(('a',2),('b',2)));System.out.println("""res14: List[week6.week6.Occurrences] = """ + $show(res$14));$skip(394); 
  
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
  	
  	
  };System.out.println("""sub1: (x: week6.week6.Occurrences, y: week6.week6.Occurrences)week6.week6.Occurrences""");$skip(27); 
  
  val x = List(('a',1));System.out.println("""x  : List[(Char, Int)] = """ + $show(x ));$skip(24); 
  val y = List(('a',1));System.out.println("""y  : List[(Char, Int)] = """ + $show(y ));$skip(12); val res$15 = 
  sub1(x,y);System.out.println("""res15: week6.week6.Occurrences = """ + $show(res$15));$skip(387); 
  
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
  };System.out.println("""subtract: (x: week6.week6.Occurrences, y: week6.week6.Occurrences)week6.week6.Occurrences""")}
  
  
}
