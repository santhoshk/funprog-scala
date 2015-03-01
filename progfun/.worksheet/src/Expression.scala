import common._
package week4


object Expression {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(372); 
  def show(e:Expr):String = e match {
  	case Number(x) => x.toString
  	case Sum(l,r) => show(l) + " + " + show(r)
  	case Prod(l,Sum(x,y)) => show(l) + " * " + " ( " + show(Sum(x,y)) + " ) "
  	case Prod(Sum(x,y),r) => " ( " + show(Sum(x,y)) + " ) " + " + " + show(r)
  	case Prod(l,r) => show(l) + " * " + show(r)
  };System.out.println("""show: (e: <error>)String""");$skip(37); val res$0 = 
  
  show(Sum(Number(1),Number(44)));System.out.println("""res0: <error> = """ + $show(res$0));$skip(34); val res$1 = 
  show(Prod(Number(1),Number(2)));System.out.println("""res1: <error> = """ + $show(res$1));$skip(49); val res$2 = 
  show(Sum(Number(3),Prod(Number(1),Number(2))));System.out.println("""res2: <error> = """ + $show(res$2));$skip(49); val res$3 = 
  show(Prod(Number(3),Sum(Number(1),Number(2))));System.out.println("""res3: <error> = """ + $show(res$3));$skip(120); 
  
  def isort(xs:List[Int]):List[Int] = xs match {
  	case List() => List()
  	case y :: ys => insert(y,isort(ys))
  };System.out.println("""isort: (xs: List[Int])List[Int]""");$skip(111); 
  
  def insert(x:Int, xs:List[Int]):List[Int] = xs match {
  	case List() => List(x)
  	case y::ys => ???
  };System.out.println("""insert: (x: Int, xs: List[Int])List[Int]""")}
}
