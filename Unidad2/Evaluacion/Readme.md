# Multilayer Perceptron Classifier


>Step 1: loading required packages and APIs
```scala
import org.apache.spark.sql.types.IntegerType
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.feature.StringIndexer 
import org.apache.spark.ml.feature.VectorAssembler
```
> Step 2: create a Spark session
```scala
val spark = SparkSession.builder.appName("MultilayerPerceptronClassifierExample").getOrCreate()
```

> Step 3: load and analyze the dataset
> Load the data stored in csv format as a DataFrame.
```scala
val df = spark.read.option("header", "true").option("inferSchema","true")csv("/home/eduardo/Escritorio/DatosMasivos/iris/iris.csv")
```

> Step 4: Data visualization
```scala
df.columns // Column name
df.printSchema () // schema
df.head (5) // Print the first 5 columns.
df.describe (). show () // describe () 
```


> Step 5: Convert to categorical data
```scala
val labelIndexer = new StringIndexer().setInputCol("species").setOutputCol("indexedLabel").fit(df)
val indexed = labelIndexer.transform(df).drop("species").withColumnRenamed("indexedLabel", "label")
indexed.describe().show() //describe () 
```
> Step 6: vectorize "sepal_length", "sepal_width", "petal_length", "petal_width"
```scala
val assembler = new VectorAssembler().setInputCols(Array("sepal_length","sepal_width","petal_length","petal_width")).setOutputCol("features")
val  features = assembler.transform(indexed)
```

> Step 7: Index tags, adding metadata to the tag column.
 Fits the entire dataset to include all the tags in the index.
 ```scala
val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(indexed)
println(s"Found labels: ${labelIndexer.labels.mkString("[", ", ", "]")}")
features.show
```

> Step 8: preparing the training and test set
 Prepare the train and the test set: training => 60%, test => 40% and seed => 12345L
 ```scala
 val splits = features.randomSplit(Array(0.6, 0.4), seed = 1234L)
 val train = splits(0)
 val test = splits(1)
```

> Step 9: specify layers for neural network
Specify the layers for the neural network as follows:
input layer => size 4 (characteristics),
two intermediate layers (i.e. hidden layer)
of size 5 and 4 and output => size 3 (classes). */
```scala
val layers = Array[Int](4, 5, 4, 3)
```
> Step 10: create the MultilayerPerceptronClassifier trainer and set its parameters
```scala
val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setBlockSize(128).setSeed(1234L).setMaxIter(100)  
```
> Step 11: Train the multilayer perceptron classification model using the estimator
Train the multilayer perceptron classification model using the estimator above (i.e. trainer) */
```scala
val model = trainer.fit(train)
```
> Step 12: calculate the precision in the test set
```scala
val result = model.transform(test)
```
>Step 13: Evaluate the model for prediction performance
```scala
val predictionAndLabels = result.select("prediction", "label")
predictionAndLabels.show
```
> Step 14: evaluate the model for prediction performance
```scala
val evaluator = new MulticlassClassificationEvaluator().setMetricName("accuracy")
println(s"Test set accuracy = ${evaluator.evaluate(predictionAndLabels)}")
```
> Step 15: dummy adjustment if necessary
if the classifier performance is low enough. One reason is that the Perceptron is very shallow
the size of the data set is also smaller; therefore we must continue to try to deepen
at least increasing the size of the hidden layers. */

```scala
spark.stop()
```
