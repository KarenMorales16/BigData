
import org.apache.spark.sql.types.IntegerType
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.feature.StringIndexer 
import org.apache.spark.ml.feature.VectorAssembler

import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

val runtime = Runtime.getRuntime
val startTimeMillis = System.currentTimeMillis()


val spark = SparkSession.builder.appName("MultilayerPerceptronClassifierExample").getOrCreate()


val df  = spark.read.option("header","true").option("inferSchema", "true").option("delimiter",";").format("csv").load("/home/eduardo/Escritorio/DatosMasivos/Unidad4/bank-full.csv")


df.columns 
df.printSchema () 
df.head (5) 
df.describe (). show () 


val labelIndexer = new StringIndexer().setInputCol("y").setOutputCol("indexedLabel").fit(df)
val indexed = labelIndexer.transform(df).drop("y").withColumnRenamed("indexedLabel", "label")
indexed.describe().show() 

val assembler = new VectorAssembler().setInputCols(Array("balance","day","duration","pdays","previous")).setOutputCol("features")
val  features = assembler.transform(indexed)


val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(indexed)
println(s"Found labels: ${labelIndexer.labels.mkString("[", ", ", "]")}")
features.show()


 val splits = features.randomSplit(Array(0.6, 0.4), seed = 1234L)
 val train = splits(0)
 val test = splits(1)

val layers = Array[Int](5, 4, 1, 2)


val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100)  

val model = trainer.fit(train)

val result = model.transform(test)

val predictionAndLabels = result.select("prediction", "label")
predictionAndLabels.show

val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")
println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")


val mb = 0.000001
println("Used Memory: " + (runtime.totalMemory - runtime.freeMemory) * mb)
println("Free Memory: " + runtime.freeMemory * mb)
println("Total Memory: " + runtime.totalMemory * mb)
println("Max Memory: " + runtime.maxMemory * mb)


val endTimeMillis = System.currentTimeMillis()
val durationSeconds = (endTimeMillis - startTimeMillis) / 1000

spark.stop()

