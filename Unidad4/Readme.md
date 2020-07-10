<table>
 <td style="font-size:50% ;font-family: Times New Roman"; align="center" colspan="2">
   <h2>BigData Final Project</h2>
Departamento de sistemas y computación
<br>
Instituto Tecnológico de Tijuana
<br>
karen.morales16@tectijuana.edu.mx
<br>
jesus.escobedo16@tectijuana.edu.mx
 </td>
</tr>                                                                                 
 <tr>
    <td style="font-size:150% ;font-family: Times New Roman"; align="Justify">
 I. INTRODUCTION
<br>
<br>
This investigation aims to compare the performance of the following SVM machine learning algorithms, Decision Three, Logistic Regression, Multilayer Perceptron, using a csv called bank of approximately 45,000 records
<br>
<br>
II. Algorithms
<br>
<br>
A. Multilayer Perceptron Classifier
<br>
<br>
Consists of multiple layers of nodes including the input layer, hidden layers (also called intermediate layers), and output layers. Each layer is fully connected to the next layer in the network.
<br>
<br>
B. Decision Tree
<br>
<br>
A Decision tree is a flowchart like tree structure, where each internal node denotes a test on an attribute, each branch represents an outcome of the test, and each leaf node (terminal node) holds a class label.
<br>
<br>
C. Lineal Support Vector Machine
<br>
<br>
“Support Vector Machine” (SVM) is a supervised machine learning algorithm which can be used for both classification or regression challenges. However, it is mostly used in classification problems.
<br>
<br>
D. Logistic Regression
<br>
Logistic regression is the appropriate regression analysis to conduct when the dependent variable is dichotomous (binary). Like all regression analyses, the logistic regression is a predictive analysis
<br>
<br>
<br> 
<br>
  </td>
    <td  style="font-size:150% ;font-family: Times New Roman"; align="Justify">
III. Implementation of technological tools
<br>
<br>
 A. Spark 
<br>
<br>
Spark enables parallelized jobs entirely in memory, greatly reducing processing times. Especially if it is an iterative process as used in Machine Learning.
<br>
<br>
B. Scala
<br>
<br>
We use Sacalabecause it is a functional and object-oriented language to demos that runs at compilation time, it allows us not to consume a lot of system memory, this helps us to get good execution time, and it allows us to work with large volumes of data.
<br>
<br>
Performance
<br>
<br>
<table class="egt" border="0">
<tr>
  <td> </td>
    <td> Used memory MB</td>
    <td>Seconds</td>
  </tr>
  <tr>
    <td>SVC</td>
    <td>464.87</td>
    <td>15.5</td>
  </tr>
    <tr>
    <td>Logistic Regression</td>
    <td>418.77</td>
    <td>7.1</td>
  </tr>
    <tr>
    <td>Decision Three</td>
    <td>373.89</td>
    <td>11.9</td>
  </tr>
   <tr>
    <td>Multilayer perceptron</td>
    <td>598.21</td>
    <td>22.3</td>
  </tr>
</table>
  <br>
<br>
Accuracy
<br>
<br>
<table class="egt" border="0">
<tr>
  <td> </td>
    <td>Accuracy</td>
    <td>Error</td>
  </tr>
  <tr>
    <td>SVC</td>
    <td>0.885</td>
    <td>0.114</td>
  </tr>
    <tr>
    <td>Logistic Regression</td>
    <td>0.884</td>
    <td>0.115</td>
  </tr>
    <tr>
    <td>Decision Three</td>
    <td>0.891</td>
    <td>0.108</td>
  </tr>
   <tr>
    <td>Multilayer perceptron</td>
    <td>0.882</td>
    <td>0.117</td>
  </tr>
</table>

V. CONCLUSIONS
After looking at the results of the different types of classification algorithms with the banck-full dataset, we can see that on average decision trees was the one that best classified this dataset consuming less memory than the other algorithms with an average time of 11.9 seconds. 
 </td>
 </tr>
 </tr>
</table>
