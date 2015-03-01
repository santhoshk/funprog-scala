package week3


object Polymorphism {
	def nth[T](n:Int, xs:List[T]) : T =
		if (xs.isEmpty) throw new IndexOutOfBoundsException
		else if(n==0) xs.head
		else nth(n-1, xs.Tail)            //> nth: [T](n: Int, xs: week3.List[T])T
		
	val list = new Cons(1, new Cons(2, new Cons(3, new Nil)))
                                                  //> list  : week3.Cons[Int] = week3.Cons@767ebd7d
	
	nth(2, list)                              //> res0: Int = 3
	nth(5, list)                              //> java.lang.IndexOutOfBoundsException
                                                  //| 	at week3.Polymorphism$$anonfun$main$1.nth$1(week3.Polymorphism.scala:6)
                                                  //| 
                                                  //| 	at week3.Polymorphism$$anonfun$main$1.apply$mcV$sp(week3.Polymorphism.sc
                                                  //| ala:13)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at week3.Polymorphism$.main(week3.Polymorphism.scala:4)
                                                  //| 	at week3.Polymorphism.main(week3.Polymorphism.scala)
}

trait List[T] {
  def isEmpty: Boolean
  def head: T
  def Tail: List[T]
}

class Cons[T](val head: T, val Tail: List[T]) extends List[T] {
  def isEmpty: Boolean = false
  //already implemented as field definitions of this class
  //so thatimplements the abstract methods head and tail
  /*def head: T
  def Tail: List[T]*/
}

class Nil[T] extends List[T] {
  def isEmpty: Boolean = true
  def head: Nothing = throw new NoSuchElementException("Nil.head")
  def Tail: Nothing = throw new NoSuchElementException("Nil.head")
}