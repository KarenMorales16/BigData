import org.apache.spark.sql.SparkSession

val spark = SparkSession.builder().getOrCreate()

val df = spark.read.option("header", "true").option("inferSchema","true")csv("/home/eduardo/Escritorio/semestre_9/BigData/Unidad1/Practicas_Tarea/Practica6/Sales.csv")


df.printSchema()

df.show()

//1.creturns the approximate number of distinct items in a group.
df.select(approx_count_distinct("Sales")).show()

//2. returns the average of the values in a group.
df.select(avg("Sales")).show()
//3. returns a list of objects with duplicates.
df.select(collect_list("Company")).show()
//4. returns the number of items in a group.
df.select(count("Sales")).show()

//5. returns the first value of a column in a group.
df.select(first("Person")).show()

// 6. returns the kurtosis of the values in a group.
df.select(kurtosis("Sales")).show()

//7. returns the last value of the column in a group.
df.select(last("Person")).show()
// 8. returns the population standard deviation of the expression in a group.
df.select(stddev_pop("Sales")).show()

//9. returns the skewness of the values in a group.
df.select(skewness("Sales")).show()

// 10 returns the population variance of the values in a group.
df.select(var_pop("Sales")).show()

// 11 returns the unbiased variance of the values in a group.
df.select(var_samp("Sales")).show()
//12 returns the minimum value of the column in a group.
df.select(min("Sales")).show()
//13 returns the maximum value of the column in a group.
df.select(max("Sales")).show()
//14 returns the sum of all values in the given column.
df.select(sum("Sales")).show()
//15 returns the sum of distinct values in the expression.
df.select(sumDistinct("Sales")).show()
//16 returns the average of the values in a group.
df.select(mean("Sales")).show()
//17 returns the sample standard deviation of the expression in a group.
df.select(stddev_samp("Sales")).show()
//18 Returns the value of the column e rounded to 0 decimal places with HALF_UP round mode.
df.select(round(sum("Sales"))).show()
//19 Computes the absolute value of a numeric value.
df.select(abs(sum("Sales"))).show()
//20. returns a set of objects with duplicate elements eliminated.
df.select(collect_set("Person")).show()