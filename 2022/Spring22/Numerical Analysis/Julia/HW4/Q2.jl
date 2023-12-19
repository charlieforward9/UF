using FundamentalsNumericalComputation

p = x -> (1/2)*x + (9 / (2 * x))
g = x -> x - p(x)


plt = plot([p x->x],1,5,l=3,label=["\$y=p(x)\$" "\$y=x\$"],aspect_ratio=1,
    title="Finding a fixed point",legend=:bottomright)

x = 2
y = p(x)
for k = 1:10
    plot!([x,y],[y,y],label="",arrow=true);   
    global x = y;    # y --> new x
    global y = p(x);  
    println(x)
    plot!([x,x],[x,y],label="",arrow=true)  # g(x) --> new y
    println(abs(y-3)/3)

end

display(plt)