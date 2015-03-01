package week4

trait List[T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  def isEmpty = false
}

class Nil[T] extends List[T] {
  def isEmpty: Boolean = true
  def head: Nothing = throw new NoSuchElementException("Nil.head");
  def tail: Nothing = throw new NoSuchElementException("Nil.tail");
}

object List {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(501); 
  //List(1,2) = List.apply(1,2)
  def apply[T](x1: T, x2: T): List[T] = new Cons(x1, new Cons(x2, new Nil));System.out.println("""apply: [T](x1: T, x2: T)week4.List[T]""")}
  //why does the next line give an error?
  //def apply[T](): List[T] = new Nil
}
