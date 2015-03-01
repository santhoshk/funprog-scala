package quickcheck

import common._
import util.Random

import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap {

  //If we insert 1 elt into a empty heap, then findMin on that heap should
  //give the same elt.
  property("min1") = forAll { a: Int =>
    val h = insert(a, empty)
    findMin(h) == a
  }

  //If we insert 1 elt into 2 heaps (call the elts a1, a2), and then findMin
  //on the melded heap h, then that elt should == min(a1, a2)
  property("min2") = forAll { a: Int =>
    val h1 = insert(a, empty)
    val h2 = insert(a / 2, empty)
    val h3 = meld(h1, h2)
    val min = if (a > 0) a / 2 else a
    findMin(h3) == min
  }

  //If we insert a1, a2 into a empty heap and then find min
  //from the heap. It should == min(a1, a2)
  property("min3") = forAll { a: Int =>
    val h = insert(a, empty)
    val h1 = insert(a / 2, h)
    val min = if (a > 0) a / 2 else a
    findMin(h1) == min
  }

  //in 2 random heaps, check for min values min1 and min2
  //then merge them and check for min value
  //min value in merged heap == min(min1, min2)
  property("min4") = forAll { (a1: Int, a2: Int, arb1: H, arb2: H) =>
    val h1 = insert(a1, arb1)
    val h2 = insert(a2, arb2)
    val h3 = meld(h1, h2)

    val m1 = findMin(h1)
    val m2 = findMin(h2)

    val min = if (m1 <= m2) m1 else m2
    findMin(h3) == min
  }

  //If we insert a1, a2 into a empty heap and then find min
  //from the heap. It should == min(a1, a2)
  property("min5") = forAll { (a1: Int, a2: Int) =>
    val h = insert(a1, empty)
    val h1 = insert(a2, h)

    val min = if (a1 <= a2) a1 else a2
    findMin(h1) == min
  }

  //If we insert 1 elt into 2 heaps (call the elts a1, a2), and then findMin
  //on the melded heap h, then that elt should == min(a1, a2)
  property("min6") = forAll { (a1: Int, a2: Int) =>
    val h1 = insert(a1, empty)
    val h2 = insert(a2, empty)
    val h3 = meld(h1, h2)
    val min = if (a1 <= a2) a1 else a2
    findMin(h3) == min
  }
  
  property("min7") = {    
    val h = createHeap2(10, 10)
    //printHeap(h)
    //println ("5th min " + getNthMin(h, 4))
    //isSorted(h) == true
    getNthMin(h, 4) == 5
    
  }

  //If we insert a1 into h1 and then delMin
  //and then check isEmpty(h1) == true
  property("isEmpty1") = forAll { a: Int =>
    val h = insert(a, empty)
    val h1 = deleteMin(h)
    isEmpty(h1) == true
  }

  //After inserting a1 into h1, isEmpty(h1) == false
  property("isEmpty2") = forAll { a: Int =>
    val h = insert(a, empty)
    isEmpty(h) == false
  }

  //IsEmpty of empty heap == true
  property("isEmpty3") = forAll { a: Int =>
    isEmpty(empty) == true
  }

  //insert n elts into a empty heap and then delMin
  //n times, we should get the elts in ascending order
  //property("sorted1") = forAll { arb: H => 
  //println("*********************")  
  //isSorted(arb) == true
  //}

  property("test1") = forAll { arb: H =>
    //println("=============")
    //printHeap(arb)
    isSorted(arb) == true
  }

  property("count1") = forAll { (h1: H, h2: H) =>
    val h3 = meld(h1, h2)

    val c1 = getCount(h1)
    val c2 = getCount(h2)
    val c3 = getCount(h3)

    //println("Comparing " + c1 + "," + c2 + " with " + c3)
    c3 == c1 + c2

  }

  property("count2") = {
    val randHeap = getRandomHeap(500)
    //printHeap(randHeap)
    //println("\n$$$$$$$\n")
    isSorted(randHeap) == true
  }

  property("count3") = {
    val h1 = getRandomHeap(1000)
    val h2 = getRandomHeap(500)
    val h3 = meld(h1, h2)
    //printHeap(randHeap)
    //println("\n$$$$$$$\n")
    val c1 = getCount(h1)
    val c2 = getCount(h2)
    val c3 = getCount(h3)

    //println("Comparing " + c1 + "," + c2 + " with " + c3)
    c3 == c1 + c2
  }

  property("count4") = {
    val h1 = getRandomHeap(1000)
    val h2 = getRandomHeap(1000)
    val h = meld(h1, h2)
    findMinX(h1, h2, h) == true
    findMinX2(h1, h2) == true
    test123(100) == true
  }
  
  def createHeap1(n:Int, ini:Int):H = {
    if(n==0) empty
    else insert(ini, createHeap1(n-1, ini+1))
  }
  
  def createHeap2(n:Int, ini:Int):H = {
    if(n==0) empty
    else insert(ini, createHeap2(n-1, ini-1))
  }
  
  def getNthMin(h:H, nth:Int) : Int = {
    if(nth == 0) findMin(h)
    else getNthMin(deleteMin(h), nth-1)
  }

  def test123(n: Int): Boolean = {
    if (n == 0) true else {
      //println ("Entering test123")
      val h1 = getRandomHeap(1000)
      val h2 = getRandomHeap(1000)
      val h = meld(h1, h2)
      if(!findMinX2(h1, h2)) false
      else test123(n-1)
    }
  }

  def getCount(h: H): Int = {
    if (isEmpty(h)) 0
    else 1 + getCount(deleteMin(h))
  }

  def findMinX(h1: H, h2: H, h: H): Boolean = {
    if (isEmpty(h1) || isEmpty(h2) || isEmpty(h)) true
    else {

      val m1 = findMin(h1)
      val m2 = findMin(h2)
      val m = findMin(h)

      val min = if (m1 <= m2) m1 else m2
      if (min != m) false
      if (m1 <= m2) {
        findMinX(deleteMin(h1), h2, deleteMin(h))
      } else {
        findMinX(h1, deleteMin(h2), deleteMin(h))
      }
    }
  }

  def findMinX2(h1: H, h2: H): Boolean = {
    if (isEmpty(h1) || isEmpty(h2)) true
    else {

      val m1 = findMin(h1)
      val m2 = findMin(h2)
      val h = meld(h1, h2)
      val m = findMin(h)

      val min = if (m1 <= m2) m1 else m2
      if (min != m) false
      if (m1 <= m2) {
        findMinX2(deleteMin(h1), h2)
      } else {
        findMinX2(h1, deleteMin(h2))
      }
    }
  }

  def isSorted(h: H): Boolean = {
    //println ("Entering isSorted")
    if (isEmpty(h)) true
    else {
      val min = findMin(h)
      val rest = deleteMin(h)
      if (isEmpty(rest)) true
      else {
        val restMin = findMin(rest)
        //println("Comparing : " + min + " with " + restMin)
        if (min > restMin) false
        else isSorted(rest)
      }
    }
  }

  def printHeap(h: H): Unit = {
    if (isEmpty(h)) print("null\n")
    else {
      val min = findMin(h)
      print(min + " ")
      printHeap(deleteMin(h))
    }
  }

  /*lazy val genHeap: Gen[H] = {
    for {
      elt <- arbitrary[Int]
      h <- oneOf(genHeap, empty)
      h1 <- oneOf(empty, insert(elt, h))
    } yield h1
  }*/

  lazy val genHeap: Gen[H] = {
    for {
      elt <- arbitrary[Int]
      h <- oneOf(genHeap, empty)
      //h1 <- oneOf(empty, insert(elt, h))
    } yield insert(elt, h)
  }

  def getRandomHeap(n: Int): H = {
    if (n == 0) empty
    else {
      insert(Random.nextInt(), getRandomHeap(n - 1))
    }
  }

  implicit lazy val arbHeap: Arbitrary[H] = Arbitrary(genHeap)

}
