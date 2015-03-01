package recfun
import common._

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if (c < 0 || r < 0) -1
    if (c == 0 || c == r) 1
    else pascal(c - 1, r - 1) + pascal(c, r - 1)
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    def balanceWithStack(chars: List[Char], bracks: List[Char]): Boolean = {
      if (chars.isEmpty && bracks.isEmpty) true
      else if (chars.isEmpty && !bracks.isEmpty) false
      else if (chars.head == '(') {
        balanceWithStack(chars.tail, '(' :: bracks)
      } else if (chars.head == ')') {
        if (bracks.isEmpty) false
        else if (bracks.head == ')') false
        else balanceWithStack(chars.tail, bracks.tail)
      } else balanceWithStack(chars.tail, bracks)
    }
    balanceWithStack(chars, List())
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    def cc(money: Int, coins: List[Int]): Int = {
      if (money == 0) 1
      else if (money < 0 || coins.isEmpty) 0
      else cc(money - coins.head, coins) + cc(money, coins.tail)
    }

    if (money <= 0) 0 else cc(money, coins)
  }
}
