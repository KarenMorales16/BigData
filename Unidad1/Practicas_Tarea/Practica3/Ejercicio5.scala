//Fibonacci iterativa vector

def fibo(n :Int):Double={
    if(n<2){
        return n
    }else{
        var vec = new  Array[Double](n+1)
        vec(0) = 0
        vec(1) = 1
        for (k<- Range(2,n+1)){
            vec(k)=vec(k-1)+vec(k-2)
        }
        return vec(n)
    }
    
}

fibo (10)