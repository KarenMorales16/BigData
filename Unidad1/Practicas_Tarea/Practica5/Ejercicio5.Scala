import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder().getOrCreate()
val df = spark.read.option("header", "true").option("inferSchema","true")csv("/home/karen/Documentos/DatosMasivosClase/BigData/Spark_DataFrame/P2-Mispriced-Diamonds.csv")


df.printSchema()
import spark.implicits._
// #1 Average in a data set (price)
df.select(avg("price")).show()

//#2  perform the sum over a data set
df.select(sum("price")).show()

//#3 identify the minimum value on a data set
df.select(min("price"),max ("price")).show()

//#4 Identify the maximum value in a data set
df.select(max("price")).show()

//#5 Shows an example that calculates the mean and maximum value of the subset of data grouped by last name
df.groupBy(df.col("carat")).agg(avg("price"), max("price")).show()

//#6 Shows an example that calculates the correlation over a data set
df.stat.corr("carat", "price")

//#7 Example that calculates covariance in a data set
df.stat.cov("carat", "price")

//#8  Show all Data Frame
df.show()

//#9 Show the names of the DataFrame columns
df.columns

//#10 Existing columns and information about them
df.printSchema()
//#11 select two specific columns
 df.select("carat","price").show()
 //#12 condition when price is less than 350 return boolean
 val preciosmenor = df.select($"price" < 350)
 // save everything in which the lowest price is 330 as carat, clarity, price
  lower price = df.filter ($ "price" <330)
 // save everything in which the lowest price is 330 but only in price
  lowest price = df.filter ($ "price" <330) .select ("price")
// # 13 count how many values ​​are priced less than 330
 lowest price = df.filter ($ "price" <330) .select ("price"). count
 // # 14 how many have the same price
 df.groupBy ("price"). count (). show ()
 // # 15 Register the DataFrame as a temporary view of SQL
 df.createOrReplaceTempView ("_ view")
val sqlDF = spark.sql ("SELECT * FROM _view")
sqlDF.show ()

val sqlDF = spark.sql ("SELECT * FROM _view where price <330")
sqlDF.show ()
// # 16 show the first three elements of the DataFrame
for (row <- df.head (3)) {
  val list println (row)
}

for (row <- df.head (3)) {
 val list list = row
   println (list)
}

// # 18 add a new column
val df2 = df.withColumn ("pricex2", df ("price") * 2)
df2.show

// # 19 Show what type of data the Dataframe has
df.printSchema ()
//#twenty
///df.collect () shows only content of everything and structure / metadata.eg
df.collect
//df.show shows only the content
df.show ()
/* pdf.take (some number) can be used to display content and structure / metadata for a limited number of rows for a very large data set. Note that it flattens the data and is displayed in a single row.
for example, to see only the first two rows of the data frame*/
df.take (1)

// 21 Show the amount of correlation that has a data
df.select (corr ("price", "carat"). alias ("correlation")). show()
