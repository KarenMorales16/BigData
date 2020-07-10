// por la función
var n,f=0
var t1=1
n=20
var t2=1
for(i <- Array.range(0,n+1)){
    t2=f
    f=t1+f
    t1=t2
    println(t1)

}
