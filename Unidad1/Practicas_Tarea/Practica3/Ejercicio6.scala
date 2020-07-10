//Fiboncci version divide y vencerás
import scala.math.pow

def fib(n :Int):Double={
    if(n<=0){
        return 0
    }

    var i = n-1
    var aux1 =0.0
    var aux2=1.0

    var ab=(aux2,aux1)
    var cd=(aux1,aux2)

    while(i>0){
        if((i%2)!=0){
            aux1=(cd._2*ab._2)+(cd._1*ab._1)
            aux2=(cd._2*(ab._2+ab._1)+cd._1*ab._2)
            ab=(aux1,aux2)
        }
        aux1=pow(cd._1,2)+pow(cd._2,2)
        aux2=(cd._2*(2*cd._1+cd._2))
        cd=(aux1,aux2)
        i=i/2
    }
    return (ab._1+ab._2)

}

fib(10)