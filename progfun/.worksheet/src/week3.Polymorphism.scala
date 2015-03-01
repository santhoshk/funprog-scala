package week3


object Polymorphism {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(177); 
	def nth[T](n:Int, xs:List[T]) : T =
		if (xs.isEmpty) throw new IndexOutOfBoundsException
		else if(n==0) xs.head
		else nth(n-1, xs.Tail);System.out.println("""nth: [T](n: Int, xs: week3.List[T])T""");$skip(62); 
		
	val list = new Cons(1, new Cons(2, new Cons(3, new Nil)));System.out.println("""list  : week3.Cons[Int] = """ + $show(list ));$skip(16); val res$0 = 
	
	nth(2, list);System.out.println("""res0: Int = """ + $show(res$0));$skip(14); val res$1 = 
	nth(5, list);System.out.println("""res1: Int = """ + $show(res$1))}
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
