//1
import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder().getOrCreate()
//2
val df = spark.read.option("header", "true").option("inferSchema","true")csv("/home/eduardo/Escritorio/semestre_9/BigData/Unidad1/Evaluacion/Netflix_2011_2016.csv")

//3 
df.columns

//4
df.printSchema()

//5
df.take(5)

//6
df.describe().show

//7
 val df2 =df.withColumn("HV_Ratio", df("High")/df("Volume"))
 df2.show

//8

df.groupBy(dayofmonth(df("Date")).alias("Day")).max("High").sort(asc("Day")).show()
df.groupBy(dayofweek(df("Date")).alias("Day")).max("High").sort(asc("Day")).show()

//9
/*Close en la bolsa de valores es el valor total de las acciones con la que cerro ese dia*/

//10
df.select(max("Volume"),min("Volume")).show()

//11.a
val preciosmenor = df.filter($"Close" < 600).count()
//11.b
val tiempo = df.filter($"High" > 500).count()
val tiempo1= tiempo * .100

//11.c
df.select(corr("High", "Volume").alias("correlacion")).show()

//11.d


df.groupBy(year(df("Date")).alias("Year")).max("High").sort(asc("Year")).show()

//11.e

df.groupBy(month(df("Date")).alias("Month")).avg("Close").sort(asc("Month")).show()
