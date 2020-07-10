import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.feature.{VectorAssembler, StringIndexer, VectorIndexer, OneHotEncoder}
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.sql.SparkSession


import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)


val runtime = Runtime.getRuntime
val startTimeMillis = System.currentTimeMillis()


val spark = SparkSession.builder().getOrCreate()


val data = spark.read.option("header","true").option("inferSchema", "true").option("delimiter",";").format("csv").load("/home/eduardo/Escritorio/DatosMasivos/Unidad4/bank-full.csv")
val labelIndexer = new StringIndexer().setInputCol("y").setOutputCol("indexedLabel").fit(data)
val indexed = labelIndexer.transform(data).drop("y").withColumnRenamed("indexedLabel", "label")





val vectorFeatures = (new VectorAssembler().setInputCols(Array("balance","day","duration","pdays","previous")).setOutputCol("features"))


val features = vectorFeatures.transform(indexed)


val featuresLabel = features.withColumnRenamed("y", "label")


val dataIndexed = featuresLabel.select("label","features")


val Array(training, test) = dataIndexed.randomSplit(Array(0.7, 0.3), seed = 12345)

val logisticAlgorithm = new LogisticRegression().setMaxIter(10).setRegParam(0.3).setElasticNetParam(0.8).setFamily("multinomial")


val logisticModel = logisticAlgorithm.fit(training)


val results = logisticModel.transform(test)


import org.apache.spark.mllib.evaluation.MulticlassMetrics


val predictionAndLabels = results.select($"prediction",$"label").as[(Double, Double)].rdd
val metrics = new MulticlassMetrics(predictionAndLabels)


println("Confusion matrix:")
println(metrics.confusionMatrix)


println("Accuracy: "+metrics.accuracy) 
println(s"Test Error = ${(1.0 - metrics.accuracy)}")

val mb = 0.000001
println("Used Memory: " + (runtime.totalMemory - runtime.freeMemory) * mb)
println("Free Memory: " + runtime.freeMemory * mb)
println("Total Memory: " + runtime.totalMemory * mb)
println("Max Memory: " + runtime.maxMemory * mb)


val endTimeMillis = System.currentTimeMillis()
val durationSeconds = (endTimeMillis - startTimeMillis) / 1000

