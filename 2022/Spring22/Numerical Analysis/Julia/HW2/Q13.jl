using FundamentalsNumericalComputation
n = 1000
a = 1


for i in 1:200
    Aᵢ = triu( tril(rand(n,n),1), -1)
    Aᵢ[1:n+1:end] .= a
    #Aᵢ = sparse(A)
    bᵢ = rand(n,1)
end

@elapsed ( Aᵢ \ bᵢ for i = 1:200)



#PART A
#Result: 0.007400559
#PART B
#Result: 0.007198254
#PART C
    #A: 0.008717511
    #B: 0.006511688 