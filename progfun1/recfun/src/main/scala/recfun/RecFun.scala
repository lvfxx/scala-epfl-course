package recfun

object RecFun extends RecFunInterface {

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int =
    if (c == 0 && r == 0) 1
    else if (c < 0 || c > r) 0
    else pascal(c - 1, r - 1) + pascal(c, r - 1)

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    @scala.annotation.tailrec
    def balance(chars: List[Char], openedParenthesesCount: Int): Boolean =
      if (openedParenthesesCount < 0) false
      else if (chars.isEmpty) openedParenthesesCount == 0
      else {
        val head = chars.head;
        if (head == '(') balance(chars.tail, openedParenthesesCount + 1)
        else if (head == ')') balance(chars.tail, openedParenthesesCount - 1)
        else balance(chars.tail, openedParenthesesCount)
      }

    balance(chars, 0)
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int =
    if (coins.isEmpty) 0
    else if (money < 0) 0
    else if (money == 0) 1
    else countChange(money - coins.head, coins) + countChange(money, coins.tail)
}
