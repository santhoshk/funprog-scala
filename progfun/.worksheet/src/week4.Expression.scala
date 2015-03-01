package week4


object Expression {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(356); 
  def show(e:Expr):String = e match {
  	case Number(x) => x.toString
  	case Sum(l,r) => show(l) + " + " + show(r)
  	case Prod(l,Sum(x,y)) => show(l) + " * " + " ( " + show(Sum(x,y)) + " ) "
  	case Prod(Sum(x,y),r) => " ( " + show(Sum(x,y)) + " ) " + " + " + show(r)
  	case Prod(l,r) => show(l) + " * " + show(r)
  };System.out.println("""show: (e: week4.Expr)String""");$skip(37); val res$0 = 
  
  show(Sum(Number(1),Number(44)));System.out.println("""res0: String = """ + $show(res$0));$skip(34); val res$1 = 
  show(Prod(Number(1),Number(2)));System.out.println("""res1: String = """ + $show(res$1));$skip(49); val res$2 = 
  show(Sum(Number(3),Prod(Number(1),Number(2))));System.out.println("""res2: String = """ + $show(res$2));$skip(49); val res$3 = 
  show(Prod(Number(3),Sum(Number(1),Number(2))));System.out.println("""res3: String = """ + $show(res$3))}
  

}
