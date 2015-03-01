package week4

object sort {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(151); 

  def isort(xs: List[Int]): List[Int] = xs match {
    case List() => List()
    case y :: ys => insert(y, isort(ys))
  };System.out.println("""isort: (xs: week4.List[Int])week4.List[Int]""");$skip(156); 

  def insert(x: Int, xs: List[Int]): List[Int] = xs match {
    case List() => List(x)
    case y :: ys => if (x <= y) x :: xs else y :: insert(x, ys)
  };System.out.println("""insert: (x: Int, xs: week4.List[Int])week4.List[Int]""");$skip(45); 

  println("Welcome to the Scala worksheet")}
}
