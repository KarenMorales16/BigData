# Practice 2

## Exercise 1

> we create a list called "list" with the elements "red", "white", "black"

```scala

var lista= List("rojo","blanco","negro")

```
## Exercise 2

> To the list we add 4 more elements which are "green", "yellow", "blue", "orange", "pearl"

```scala
 lista = lista :+"verde" :+ "amarillo":+ "azul":+ "naranja":+ "perla"
 
```
## Exercise 3

> From the list we bring the green, yellow and blue elements

```scala
lista(3)
lista(4)
lista(5)

```
## Exercise 4
___
>This practice is done in two ways `range ()` method to generate a matrix that contains a sequence of increasing integers in a given range, in this case inside the parentecis of `range ()` we first place 1 where the sequence will start followed by a **' , '** , until where the sequence will end followed by a **' , '**, finally of how much the increase will be.

```scala
var array9 = Array.range(1, 1000, 5)
```
<br>

> The second way to perform this exercise is by adding the numbers to the arrangement through a while cycle, in this comparisonwith the other exercise we start the variable in 5 afterwards with an accumulator **x** ,we increase the value 5 by 5 until 1000, then add it to the Array, until the  end  with **1+:** arrray we add a 1 at the beginning of the Array  to make a sequence of 1, `5,10 ... 1000`

```scala
var x = 5
var array1 = Array(5)
while(x <= 1000){
    x = x + 5
    println(x)
   array1 = array1:+x
}
1+:array1
```

<br>

## Exercise 5
___
>`toSet ()` method returns a set from the indicated collection.
```scala
val Lista = List(1,3,3,4,6,7,3,7)
Lista.toSet
```
<br>

## Exercise 6
___
>with the keys of the method we can select by means of the key, with **'+ ='** we can add more values.
```scala
val mapamutable = collection.mutable.Map(("Jose", 20), ("Luis", 24), ("Ana", 23),("Susana","27"))
// 6 a . Print all map keys
mapamutable.keys
// 7 b . Add the following value to the map ("Miguel", 23)
mapamutable += ("Miguel" -> 23)
```
