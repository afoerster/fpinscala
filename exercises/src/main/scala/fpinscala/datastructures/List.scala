package fpinscala.datastructures

import scala.annotation.tailrec

sealed trait List[+A]

// `List` data type, parameterized on a type, `A`
case object Nil extends List[Nothing]

// A `List` data constructor representing the empty list
/* Another data constructor, representing nonempty lists. Note that `tail` is another `List[A]`,
which may be `Nil` or another `Cons`.
 */
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {
  // `List` companion object. Contains functions for creating and working with lists.
  def sum(ints: List[Int]): Int = ints match {
    // A function that uses pattern matching to add up a list of integers
    case Nil => 0 // The sum of the empty list is 0.
    case Cons(x, xs) => x + sum(xs) // The sum of a list starting with `x` is `x` plus the sum of the rest of the list.
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x, xs) => x * product(xs)
  }

  def apply[A](as: A*): List[A] = // Variadic function syntax
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  val x = List(1, 2, 3, 4, 5) match {
    case Cons(x, Cons(2, Cons(4, _))) => x
    case Nil => 42
    case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y
    case Cons(h, t) => h + sum(t)
    case _ => 101
  }

  def append[A](a1: List[A], a2: List[A]): List[A] =
    a1 match {
      case Nil => a2
      case Cons(h, t) => Cons(h, append(t, a2))
    }

  def foldRight[A, B](as: List[A], z: B)(f: (A, B) => B): B = as match {
    case Nil => z
    case Cons(x, xs) => f(x, foldRight(xs, z)(f))
  }

  def sum2(ns: List[Int]) =
    foldRight(ns, 0)((x, y) => x + y)

  def product2(ns: List[Double]) =
    foldRight(ns, 1.0)(_ * _) // `_ * _` is more concise notation for `(x,y) => x * y`; see sidebar

  def tail[A](l: List[A]): List[A] = l match {
    case Nil => Nil
    case Cons(a, rest) => rest
  }

  def setHead[A](l: List[A], h: A): List[A] = l match {
    case Nil => Cons(h, Nil)
    case Cons(x, xs) => Cons(h, xs)
  }

  def drop[A](l: List[A], n: Int): List[A] = n match {
    case 0 => l
    case _ => l match {
      case Nil => Nil
      case Cons(x, xs) => drop(xs, n - 1)
    }
  }

  def dropWhile[A](l: List[A], f: A => Boolean): List[A] = l match {
    case Nil => Nil
    case Cons(x, xs) if f(x) => dropWhile(xs, f)
    case _ => l
  }

  def init[A](l: List[A]): List[A] = l match {
    case Nil => Nil
    case Cons(x, Nil) => Nil
    case Cons(x, xs) => Cons(x, init(xs))
  }

  def length[A](l: List[A]): Int = foldLeft(l, 0)((n, acc) => acc + 1)

  @tailrec
  def foldLeft[A, B](l: List[A], z: B)(f: (A, B) => B): B = l match {
    case Nil => z
    case Cons(x, xs) => foldLeft(xs, f(x, z))(f)
  }

  def map[A, B](l: List[A])(f: A => B): List[B] = l match {
    case Cons(a, Nil) => Cons(f(a), Nil)
    case Cons(a, rest) => Cons(f(a), map(rest)(f))
  }

  def mapByFold[A, B](l: List[A])(f: A => B): List[B] = {
    foldRightViaLeft(l, List[B]())((a, b) => Cons(f(a), b))
  }

  def reverse[A](l: List[A]): List[A] = {
    foldLeft(l, (Nil: List[A]))((l, r) => Cons(l, r))
  }

  def foldRightViaLeft[A, B](l: List[A], z: B)(f: (A, B) => B) = {
    foldLeft(reverse(l), z)(f)
  }

  def concat[A](l: List[A], r: List[A]) = {
    foldLeft(reverse(l), r)(Cons(_, _))
  }

  def flatten[A](l: List[List[A]]) = {
    foldRight(l, List[A]())(concat)
  }

  def flatMap[A, B](l: List[A])(f: A => List[B]) = {
    flatten(map(l)(f))
  }

  def filter[A](as: List[A])(f: A => Boolean): List[A] = {
    def g[B <: A](a: B): List[B] = if (f(a)) List(a) else List()
    flatten(map(as)(g))
  }

  def zipWith[A, B](l1: List[A], l2: List[A])(f: (A, A) => B): List[B] = {
    (l1, l2 ) match {
      case (Nil, xs) => Nil
      case (ys, Nil) => Nil
      case (Cons(x, xs), Cons(y, ys)) => Cons(f(x, y), zipWith(xs, ys)(f))
    }
  }
}
