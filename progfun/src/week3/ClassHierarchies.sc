//Class Hierarchies

object IntSets {
  //we cannot instantiate instances of abstract class
  //new IntSet
  val t1 = new NonEmpty(3, Empty, Empty)          //> t1  : NonEmpty = {.3.}
  val t2 = t1 incl 4                              //> t2  : IntSet = {.3{.4.}}
}

//2 definitions of a function but they do not have a body
//this is ok as long as the class is abstract
abstract class IntSet {
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
  def union(other: IntSet): IntSet
}

//consider impl. sets as binary trees
//there are 2 types of possible trees, a tree for emty set
//and a tree consisting of an integer and 2 sub trees
//the invariant we want to maintain is :
//left sub tree -> contains values less than root
//and right sub tree -> greater than root

//these data str.a re called persistent data str.
//because even when we do "changes" to a data str. the old
//version of the data str. does not go away.
//this is one of the corner stones of FP (for scaling lists)

/*class Empty extends IntSet {
  def contains(x: Int): Boolean = false
  def incl(x: Int): IntSet = new NonEmpty(x, new Empty, new Empty)
  override def toString = "."
}*/

class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {
  def contains(x: Int): Boolean =
    if (x < elem) left contains x
    else if (x > elem) right contains x
    else true

  def incl(x: Int): IntSet =
    if (x < elem) new NonEmpty(elem, left incl x, right)
    else if (x > elem) new NonEmpty(elem, left, right incl x)
    else this

	def union(other:IntSet):IntSet =
		((left union right) union other) incl elem

  override def toString = "{" + left + elem + right + "}"
}

/*In the IntSet example, one could argue that there is really only a
single empty IntSet.
So it seems overkill to have the user create many instances of it.
We can express this case better with an object definition:*/

/*This defines a singleton object named Empty.
No other Empty instances can be (or need to be) created.
Singleton objects are values, so Empty evaluates to itself.*/

object Empty extends IntSet {
  def contains(x: Int): Boolean = false
  def incl(x: Int): IntSet = new NonEmpty(x, Empty, Empty)
  def union(other:IntSet):IntSet = other
  override def toString = "."
}

//an object of type Empty and NonEmpty can be used whereever IntSet is required
//Empty and NonEmpty are sub classes of IntSet
//if no superclass is given, then Object is the superclass

abstract class Base {
  def foo = 1
  def bar: Int
}

class Sub extends Base {
  override def foo = 2
  def bar = 3
}

/*
Object-oriented languages (including Scala) implement dynamic method dispatch.
This means that the code invoked by a method call depends on the runtime type of the object that contains the method.
Example:
Empty contains 1
-> [1/x] [Empty/this] false
= false


Another evaluation using NonEmpty:
(new NonEmpty(7, Empty, Empty)) contains 7

-> [7/elem] [7/x] [new NonEmpty(7; Empty; Empty)/this]
	if (x < elem) this.left contains x
	else if (x > elem) this.right contains x else true
-> if (7 < 7) new NonEmpty(7, Empty, Empty).left contains 7
	else if (7 > 7) new NonEmpty(7, Empty, Empty).right
	contains 7 else true
-> true

*/

/*

Dynamic dispatch of methods is analogous to calls to higher-order
functions.
Question:
Can we implement one concept in terms of the other?
 Objects in terms of higher-order functions?
 Higher-order functions in terms of objects?

*/