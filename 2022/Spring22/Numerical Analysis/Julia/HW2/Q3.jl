using FundamentalsNumericalComputation 

function lufact2(A)
    L, U = FundamentalsNumericalComputation.lufact(A')
    temp = L
    L = U'
    U = temp'
    return L, U
end

A = [6 -2 -4 4; 3 -3 -6 1; -12 8 21 -8; -6 0 -10 7]
println("\nOriginal matrix: ")
display(A)

L, U = lufact2(A)
println("\nU is unit upper triangle: ")
display(U)
println("\nVerifying L*U equals A: ")
display(convert(Matrix{Int64},L*U))

