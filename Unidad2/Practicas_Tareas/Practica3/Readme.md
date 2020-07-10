### :memo: PRACTICE 3: Decision tree classifier 

> import of libraries

```scala
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.DecisionTreeClassificationModel
import org.apache.spark.ml.classification.DecisionTreeClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer}
import org.apache.spark.sql.SparkSession
```

> create spark session variable
    
```scala
val spark = SparkSession.builder.appName("DecisionTreeClassificationExample").getOrCreate()
```
    
> We load our dataset

```scala
val data = spark.read.format("libsvm").load("/home/eduardo/Escritorio/semestre_9/BigData/Unidad2/Practicas_Tareas/Data/sample_libsvm_data.txt")
```

> Index labels, adding metadata to the label column.
> We make an adjustment to our dataframe

```scala
val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(data)
```
> We add the characteristics to which we will adjust the dataframe
```scala
val featureIndexer = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(4).fit(data)
```
> We divide the dataframe into 70% for training and 30% for testing

```scala
val Array(trainingData, testData) = data.randomSplit(Array(0.7, 0.3))
```

> Train a DecisionTree model.

```scala
val dt = new DecisionTreeClassifier().setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures")
```

> Convert indexed labels back to original labels.

```scala
val labelConverter = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(labelIndexer.labels)
```
> we create a pipeline with pipeline

```scala
val pipeline = new Pipeline().setStages(Array(labelIndexer, featureIndexer, dt, labelConverter))
```
> We train the model

```scala
val model = pipeline.fit(trainingData)
```
> We transform predictions

```scala
val predictions = model.transform(testData)
```
> Select example rows to display.

```scala
predictions.select("predictedLabel", "label", "features").show(5)
```
> We select the labels that will be shown in the prediction

```scala
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("indexedLabel").setPredictionCol("prediction").setMetricName("accuracy")
val accuracy = evaluator.evaluate(predictions)
```
> We print the model accuracy

```scala
println(s"Test Error = ${(1.0 - accuracy)}")
```
> We print the decision tree

```scala
val treeModel = model.stages(2).asInstanceOf[DecisionTreeClassificationModel]
    println(s"Learned classification tree model:\n ${treeModel.toDebugString}")
```

![Alt text](DecisionTreeClassification.png "Decision tree classifier")



