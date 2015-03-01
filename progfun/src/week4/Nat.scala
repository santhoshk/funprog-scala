package week4

//complete and concise treatment of Natural numbers from first principles without using
//primitives
//these are called Peano numbers
abstract class Nat {
  def isZero: Boolean
  def predecessor: Nat
  def successor: Nat = new Succ(this)
  def +(that: Nat): Nat
  def -(that: Nat): Nat
}

object Zero extends Nat {
  def isZero = true
  def predecessor = throw new NoSuchElementException("No predecessor for Zero")
  def +(that: Nat) = that
  def -(that: Nat) = if(that.isZero) this else throw new NoSuchElementException("Negative")
}

class Succ(n: Nat) extends Nat {
  def isZero = false
  def predecessor = n
  //def +(that: Nat) = predecessor + that.successor
  def +(that: Nat) = new Succ(n + that)
  def -(that: Nat) = if(that.isZero) this else n - that.predecessor
}

object Nat {

}