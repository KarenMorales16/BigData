
# Test 1

## Description of the problem

Given a square matrix, calculate the absolute difference between the sums of your diagonals.

arr = [[11,2,4], [4,5,6], [10,3, -12]]

diagonal_1 = 11 + 5 - 12 = 4
diagonal_2 = 4 + 5 + 10 = 19

Absolute Address = | 4 - 19 | = 15

## Solution

We solve the problem with tuples, the function receives a variable called arr, which is a tuple of tuples with data type Int.
We declare two tuple variables called Diagonal_1 and Diagonal_2, to which we give the diagonals.
With the function ** Math.abs () ** we make the difference of the diagonals and the absolute result and finally we return 15 as a result.

``` scala

def DiagonalDifference (arr: ((Int, Int, Int), (Int, Int, Int), (Int, Int, Int))): Int = {

var diagonal_1 = (arr._1._1 + arr._2._2 + arr._3._3)
var diagonal_2 = (arr._1._3 + arr._2._2 + arr._3._1)
var differenceadsolute = Math.abs (diagonal_1-diagonal_2)
return difference
}

Diagonal difference (arr = ((11,2,4), (4,5,6), (10,8, -12)))

```

## Test 2

###  The exam is composed of several points, which we solved as follows

The first point asks us to make a session in spark.

* For this we import the library to create sessions in spark and the second step is to create a session variable.
```scala
import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder().getOrCreate()
```


* Point two only asks that we load the CSV for which we indicate the route where our document is located

```scala
val df = spark.read.option("header", "true").option("inferSchema","true")csv("/home/eduardo/Escritorio/semestre_9/BigData/Unidad1/Evaluacion/Netflix_2011_2016.csv")
```

* Point three asks to print the names of the columns of our dataframe 

```scala
df.columns
```

* Point four asks us how the dataframe scheme is

```scala
df.printSchema()
```

* Point five only asks us to show the first 5 columns

```scala
df.take(5)
```

* Point six asks us to learn from our dataframe with the function describe (), which returns deviation, maximum, minimum, gives us the average and counts the number of records

```scala
df.describe().show
```

* Point seven asks us to make a new dataframe but adding a column with the name HV Ratio which will contain the relationship between High and Volume, for them we use the function withColumn ()

```scala
val df2 =df.withColumn("HV_Ratio", df("High")/df("Volume"))
 df2.show
```


* Point eight asks to know which day the price column had its maximum point.
> Point eight asks to know the day on which the price column had its maximum point. For this we group the Date column by days of the month and receive the name of Day, we obtain the maximum of the High column and finally we sort it ascendingly the data

```scala
df.groupBy(dayofmonth(df("Date")).alias("Day")).max("High").sort(asc("Day")).show()
df.groupBy(dayofweek(df("Date")).alias("Day")).max("High").sort(asc("Day")).show()
```
* Point nine asks us to describe what the Close column means
> Close on the stock exchange is the total value of the shares with which I closed that day

* Point ten only asks us to obtain the maximum and minimum of the Volume column

```scala
df.select(max("Volume"),min("Volume")).show()
```

* Point 11 asks us to know how many days the Lumna Close was less than 600

```scala
val preciosmenor = df.filter($"Close" < 600).count()
```

* Point 11 b asks us to know the percentage in which the High column was greater than 500
```scala
val tiempo = df.filter($"High" > 500).count()
val tiempo1= tiempo * .100
```

* Point 11 c asks us to know the relationship of pearson between the High and Volume column, for this we use the function cor () in which the columns in which we wish to know their
```scala
df.select(corr("High", "Volume").alias("correlacion")).show()
```

* Point 11 c we want to know which year had its maximum of the High column
```scala
df.groupBy(year(df("Date")).alias("Year")).max("High").sort(asc("Year")).show()
```

* The last point asks us to know the average of the commune Close for each month of the year
```scala
df.groupBy(month(df("Date")).alias("Month")).avg("Close").sort(asc("Month")).show()
```