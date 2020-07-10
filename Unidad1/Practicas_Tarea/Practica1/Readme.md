# Practice 1

## Exercise 1


> We perform an algorithm to calculate the radius of a circle, we import a library to use sqrt

```scala

import scala.math.sqrt
var area= 20
var radio=sqrt(area/3.1416)
println(radio) //2.5231295719346387:
```
## Exercise 2

> 
We perform an algorithm to determine if a number is a prime

```scala
var numero=1
        var contador = 0
         for (i <- 1 to numero)
         {
            if(numero % i == 0)
            {
                contador=contador+1
            }
         }           
        if(contador > 2)
        {
        println("El numero no es primo")
        }
        else
        {
         println("El numero  es primo")
        }
```
## Excercise 3

> String interpolation

```scala
val bird= "tweet"
val bird1 = s"Estoy ecribiendo un ${bird}"
val bird2 = s"Estoy ecribiendo un $bird"

println(bird1 +"\n" +bird2)
```
## Exercise  4
___
The **'slice'** function is used to select a range of elements whereby we select the interval [5,9].
```scala
var mensaje= "Hola Luke yo soy tu padre!" 
mensaje slice (5,9)
```
<br>

## Exercise 5
___
it is not mutable _(it cannot change)_ once assigned, when if it is mutable _(it can change)_, it can be regrouped but with the same type of data.
```scala
val noMutable = 3.1416 
var Mutable = "Hello world!"
```
<br>

## Exercise 6
___
with the println method we select the tuple with the `._` to position 7.
```scala
val tupla=(2,4,5,1,2,3,3.1416,23)
println(tupla._7)
```
