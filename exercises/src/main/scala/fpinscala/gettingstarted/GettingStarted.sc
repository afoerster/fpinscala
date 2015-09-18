import scala.annotation.tailrec

def factorial(n: Int): Int = {
  @tailrec
  def go(acc: Int, num: Int): Int = {
    if (num == 0) {
      acc
    } else {
      go(acc * num, num - 1)
    }
  }

  go(1, n)
}
factorial(50)

def factorial2(n: Int): Int = {
  if (n == 0) {
    1
  } else {
    n * factorial2(n - 1)
  }
}

factorial2(3)

def factorial3(n: Int) = {
  1 to n reduce (_ * _)
}

factorial3(3)

def fibonacci(n: Int): Int = {
  if (n < 2) n
  else fibonacci(n - 1) + fibonacci(n - 2)

}

def fibonacciTail(num: Int): Int = {
  def go(one: Int, two: Int, n: Int): Int = {
    if (n < 2) two
    else go(two, one + two, n - 1)
  }

  go(0, 1, num)
}

fibonacci(5)
fibonacciTail(5)



def sorted(a: Int, b: Int) = a <= b

def isOrdered(l: List[Int]) =
  l.foldLeft((true, None: Option[Int]))((a, b) => (a._1 && a._2.map(_ <= b).getOrElse(true), Some(b)))._1

test(isOrdered(List(3, 2, 1)) == false)
test(isOrdered(List(1, 2, 3)) == true)

def test(condition: Boolean) = if (condition) "pass" else "fail"

def binarySearch(as: Seq[Int], e: Int): Int = {
  @tailrec
  def search(low: Int, hi: Int): Int = {
    if (low > hi) -1
    else {
      val mid = (low + hi) / 2
      if (as(mid) > e) search(low, mid - 1)
      else if (as(mid) < e) search(mid + 1, hi)
      else mid
    }
  }
  search(0, as.length - 1)
}

binarySearch(List(1, 2, 3), 1)
binarySearch(List(1, 2, 3), 3)
binarySearch(List(1, 2, 3), 2)
binarySearch(List(1, 2, 3), 4)
binarySearch(List(1, 2, 3), -1)

def mergeSort() = ""