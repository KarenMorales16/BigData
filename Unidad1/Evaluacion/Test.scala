def  DiagonalDifference(arr :((Int, Int, Int), (Int, Int, Int), (Int, Int, Int))  ):Int={

var diagonal_1 = (arr._1._1+arr._2._2+arr._3._3)
var diagonal_2 = (arr._1._3+arr._2._2+arr._3._1)
var diferenciaadsoluta=Math.abs(diagonal_1-diagonal_2)
return diferenciaadsoluta
}

DiagonalDifference(arr= ((11,2,4),(4,5,6),(10,8,-12))) 


